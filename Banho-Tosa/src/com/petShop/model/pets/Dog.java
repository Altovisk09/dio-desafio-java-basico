package com.petShop.model.pets;

public class Dog extends Pet{

    public Dog(String name, boolean aggressive) {
        super(name, aggressive);
    }

    @Override
    public String aggressiveBehavior() {
        return "Morde morde";
    }
}
