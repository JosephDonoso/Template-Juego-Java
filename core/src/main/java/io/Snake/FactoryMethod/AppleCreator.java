package io.Snake.FactoryMethod;

public class AppleCreator extends FruitCreator{
    @Override
    public Fruit createFruit() {
        return new Apple();
    }

}
