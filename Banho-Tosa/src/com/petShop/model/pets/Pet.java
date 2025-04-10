package com.petShop.model.pets;

public abstract class Pet {
    private String name;
    private boolean washed = false;
    private boolean aggressive;

    public Pet(String name, boolean aggressive) {
        this.name = name;
        this.aggressive = aggressive;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public boolean getWashed() {
        return this.washed;
    }

    public void setWashed(boolean newWashed) {
        this.washed = newWashed;
    }

    public boolean isAggressive() {
        return this.aggressive;
    }

    public void setAggressive(boolean aggressive) {
        this.aggressive = aggressive;
    }

    public boolean getAggressive() {
        return this.aggressive;
    }


    public abstract String aggressiveBehavior();
}
