package io.Snake.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Apple {
    private int x;
    private int y;
    private int width = 30;
    private int height = 30;
    private Texture texture;

    public Apple() {
        x = 5;
        y = 5;
        texture = new Texture("red.png");
    }

    public Apple(int x, int y) {
        this.x = x;
        this.y = y;
        texture = new Texture("red.png");
    }

    public void changePosition(int maxWidth, int maxHeight) {
        x = (int) (Math.random() * ((int) maxWidth/width));
        y = (int) (Math.random() * ((int) maxHeight/height));
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x*width + 5, y*height + 5, width - 10, height - 10);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
