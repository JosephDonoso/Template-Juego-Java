package io.Snake.Singleton;

import io.Snake.main.Main;
import io.Snake.screens.EndgameScreen;
import io.Snake.screens.PauseScreen;
import io.Snake.screens.TemplateScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input;

public class GameScreen extends TemplateScreen {

    private static GameScreen instance;
    private BitmapFont font;
    // Los estados del juego pueden crearse aquí o llegar desde el main junto con el parámetro Main game
    private Texture mapTexture;
    private int mapWidth = 300;
    private int mapHeight = 300;

    private GameScreen(Main game) {
        super(game);
        font = new BitmapFont();
        font.getData().setScale(2f);
        batch = new SpriteBatch();
        gameModel.initNewGame();
        mapTexture = new Texture("white.png");
        
    }

    public static GameScreen getInstance(Main game){
        if (instance == null){
            instance = new GameScreen(game);
        }
        return instance;
    }

    public static void resetInstance(Main game){
        instance = new GameScreen(game);;
    }

    @Override
    public void render(float delta) {
        // Actualizar la cámara
        actualizarCamara();

        // Limpiar la pantalla
        limpiarPantalla();

        // Actualizar el estado score
        gameModel.addScore();

        // TAMBIÉN ES POSIBLE AGREGAR UNA CLASE CONTROLADOR PARA MANEJAR LOS CAMBIOS DE DIRECCIONES
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP) && !gameModel.getDirection().equals("DOWN")) {
            gameModel.setDirection("UP");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && !gameModel.getDirection().equals("UP")) {
            gameModel.setDirection("DOWN");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT) && !gameModel.getDirection().equals("RIGHT")) {
            gameModel.setDirection("LEFT");
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT) && !gameModel.getDirection().equals("LEFT")) {
            gameModel.setDirection("RIGHT");
        }

        // Dibujar la pantalla de juego
        batch.begin();
        font.draw(batch, "Score: " + Integer.toString(gameModel.getScore()), Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 100);
        font.draw(batch, "Dirección: " + gameModel.getDirection(), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight() - 100);
        font.draw(batch, "Durante el juego", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 150);
        font.draw(batch, "Presione Esc para pausar", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 200);
        font.draw(batch, "Presione X para ir a la pantalla final", Gdx.graphics.getWidth()/2 - 300, Gdx.graphics.getHeight() - 250);
        
        // Aquí va el código para dibujar el juego cuando no está en pausa
        // Por ejemplo, dibujar la serpiente, la comida, etc.

        //Dibujar el mapa
        batch.draw(mapTexture, 0, 0, mapWidth, mapHeight);
        // Dibujar los elementos del juego
        gameModel.render(batch);
        // Mover la serpiente
        if (gameModel.getTime()%30 == 0){
            // Dejar que el modelo mueva la serpiente
            gameModel.moveSnake();
            // Checkear colisiones con la serpiente
            if (gameModel.checkCollision() || gameModel.checkCollisionWithBorder(mapWidth, mapHeight)){
                gameModel.setIsGameOver(true);
            }
            // Checkear colisiones con la manzana
            if (gameModel.checkCollisionWithFruit()){
                gameModel.addBody();
                gameModel.changeApplePosition(mapWidth, mapHeight);
            }
            
        }

        batch.end();

        // TAMBIÉN ES POSIBLE AGREGAR UNA CLASE CONTROLADOR PARA MANEJAR LOS CAMBIOS DE PANTALLA
        // Acción cuando se presiona la tecla Esc = cambia a la pantalla pause
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            // Lógica para pausar el juego
            game.setScreen(new PauseScreen(game));
            // NO SE HACE DISPOSE DE SUS ELEMENTOS PORQUE SE SEGUIRÁN UTILIZANDO
        }

        // Acción cuando se presiona la tecla X = cambia a la pantalla final
        if (Gdx.input.isKeyJustPressed(Input.Keys.X) || gameModel.getIsGameOver()) {
            // Lógica para ir a la pantalla final
            game.setScreen(new EndgameScreen(game));
            dispose();
        }
        
        gameModel.addTime();
    }

    @Override
    public void dispose() {
        super.dispose();
        font.dispose();
    }
}