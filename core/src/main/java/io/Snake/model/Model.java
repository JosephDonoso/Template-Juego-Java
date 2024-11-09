package io.Snake.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Model {
     
    private int time;
    private int score;
    private Snake snake;
    private Apple apple;
    private String direction;
    private ArrayMap<String, int[]> directionsMap;
    private boolean isGameOver;

    public Model() {
        directionsMap = new ArrayMap<String, int[]>();
        directionsMap.put("UP", new int[]{0, 1});
        directionsMap.put("DOWN", new int[]{0, -1});
        directionsMap.put("LEFT", new int[]{-1, 0});
        directionsMap.put("RIGHT", new int[]{1, 0});
    }

    public void initNewGame() {
        snake = new Snake();
        apple = new Apple();
        isGameOver = false;
        score = 0;
        time = 0;
        direction = "RIGHT";
    }

    public void moveSnake() {
        snake.move(directionsMap.get(direction)[0], directionsMap.get(direction)[1]);
    }

    public void changeApplePosition(int maxWidth, int maxHeight) {
        apple.changePosition(maxWidth, maxHeight);
    }

    public void addBody() {
        snake.addBody();
    }

    public boolean checkCollision() {
        return snake.checkCollision();
    }

    public boolean checkCollisionWithApple() {
        return snake.checkCollisionWithApple(apple);
    }

    public boolean checkCollisionWithBorder(int maxWidth, int maxHeight) {
        return snake.checkCollisionWithBorder(maxWidth, maxHeight);
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }

    public int getTime() {
        return time;
    }

    public void addTime() {
        time++;
    }

    public void render(SpriteBatch batch) {
        apple.render(batch);
        snake.render(batch);
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    /* IMPORTANTE Las siguientes funciones sobre Bases de Datos requieren una configuración adicional:
    *  Debe asegurarase de que el archivo build.gradle tenga la dependencia de MySQL
    *  dentro de configure(subprojects). 
    *  La dependencia para JSON es útil en caso de recibir datos desde una API.
    *  Además debe asegurarse de subir la BD de la carpeta assets a su gestor de Bases de Datos local, con el mismo nombre.
    *  Yo usé XAMPP para MySQL, pero en teoría puede usar cualquier otro gestor de BD.
    */ 

    // Nueva función para subir el puntaje a la base de datos
    public void uploadScoreToDatabase() {
        String url = "jdbc:mysql://localhost:3306/Snake_DB";
        String user = "root";
        String password = "";

        try (
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement("INSERT INTO score (cant, date) VALUES (?, NOW())")
        ) {
            statement.setInt(1, score);
            statement.executeUpdate();
            System.out.println("Score subido a la base de datos: " + score);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Nueva función para cargar los 10 puntajes más altos desde la base de datos
    public Array<String> loadHighScores() {
        Array<String> highScores = new Array<>();
        String url = "jdbc:mysql://localhost:3306/Snake_DB";
        String user = "root";
        String password = "";

        try (
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT cant, date FROM score ORDER BY cant DESC LIMIT 10")
        ) {

            while (resultSet.next()) {
                int cant = resultSet.getInt("cant");
                String date = resultSet.getString("date");
                highScores.add("Score: " + cant + " Fecha: " + date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return highScores;
    }
}
