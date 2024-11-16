package io.Snake.FactoryMethod;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Fruit {

    static final int width = 30;
    static final int height = 30;

    public void changePosition(int maxWidth, int maxHeight);

    public void render(SpriteBatch batch);

    public int getBonus();

    public int getX();

    public int getY();
}
