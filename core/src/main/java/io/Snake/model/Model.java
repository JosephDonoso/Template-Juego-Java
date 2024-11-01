package io.Snake.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ArrayMap;

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
}
