package com.petShop.model.pets;

public class Cat extends Pet {

    public Cat(String name, boolean aggressive) {
        super(name, aggressive);
    }

    @Override
    public String aggressiveBehavior() {
        return "Arranha arranha";
    }
}
