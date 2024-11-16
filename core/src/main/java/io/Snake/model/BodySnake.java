package io.Snake.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BodySnake {
    private int x;
    private int y;
    private int width = 30;
    private int height = 30;
    private Texture texture;

    public BodySnake() {
        x = 0;
        y = 0;
        texture = new Texture("snakeSkin.png");
    }

    public BodySnake( int x, int y) {
        this.x = x;
        this.y = y;
        texture = new Texture("snakeSkin.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x*width, y*height, width, height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }

}
