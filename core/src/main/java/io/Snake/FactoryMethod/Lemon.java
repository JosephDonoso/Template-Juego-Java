package io.Snake.FactoryMethod;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Lemon implements Fruit{
    private int x;
    private int y;
    private Texture texture;

    public Lemon() {
        x = 5;
        y = 5;
        texture = new Texture("lemon.png");
    }

    public Lemon(int x, int y) {
        this.x = x;
        this.y = y;
        texture = new Texture("lemon.png");
    }

    public void changePosition(int maxWidth, int maxHeight) {
        x = (int) (Math.random() * ((int) maxWidth/width));
        y = (int) (Math.random() * ((int) maxHeight/height));
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x*width + 5, y*height + 5, width - 10, height - 10);
    }

    public int getBonus() {
        return 500;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
