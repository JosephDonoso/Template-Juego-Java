package io.Snake.FactoryMethod;

public class LemonCreator extends FruitCreator {

    @Override
    public Fruit createFruit() {
        return new Lemon();
    }

}
