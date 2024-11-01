package io.Snake.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Snake {
    Array<BodySnake> body;

    public Snake() {
        body = new Array<BodySnake>();
        body.add(new BodySnake());
    }

    public boolean checkCollisionWithApple(Apple apple) {
        if (body.get(0).getX() == apple.getX() && body.get(0).getY() == apple.getY()) {
            return true;
        }
        return false;
    }

    public boolean checkCollisionWithBorder(int maxWidth, int maxHeight){
        if(body.get(0).getX() < 0 || body.get(0).getX() >= ((int) maxWidth/body.get(0).getWidth()) || body.get(0).getY() < 0 || body.get(0).getY() >= ((int) maxWidth/body.get(0).getHeight())){
            return true;
        }
        return false;
    }

    public void addBody() {
        body.add(new BodySnake(body.get(body.size - 1).getX(), body.get(body.size - 1).getY()));
    }

    public boolean checkCollision() {
        for (int i = 1; i < body.size; i++) {
            if (body.get(0).getX() == body.get(i).getX() && body.get(0).getY() == body.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    public void move(int x, int y) {
        if(body.size > 1){
            body.pop();
            body.insert(0, new BodySnake(body.get(0).getX() + x, body.get(0).getY() + y));
        }
        else {
            body.get(0).setX(body.get(0).getX() + x);
            body.get(0).setY(body.get(0).getY() + y);
        }
    }

    public void render(SpriteBatch batch) {
        for (int i = 0; i < body.size; i++) {
            body.get(i).render(batch);
        }
    }
}
