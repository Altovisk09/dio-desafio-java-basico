package com.petShop.model;

import com.petShop.model.pets.Dog;
import com.petShop.model.pets.Cat;
import com.petShop.model.pets.Pet;
import java.util.Random;

public class WashMachine {
    private boolean isClean = true;
    private int waterLevel = 0;
    private int shampooLevel = 0;
    private Pet currentPet;

    public boolean getClean() {
        return isClean;
    }

    public void setClean(boolean clean) {
        this.isClean = clean;
    }

    public int getWaterLevel() {
        return waterLevel;
    }

    public void setCurrentPet(){
        this.currentPet = null;
    }

    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    public int getShampooLevel() {
        return shampooLevel;
    }

    public void setShampooLevel(int shampooLevel) {
        this.shampooLevel = shampooLevel;
    }

    public String giveBath(Pet pet) {
        if (this.currentPet != null) {
            System.out.println("Máquina em uso por " + currentPet.getName());
            return "em_uso";
        }

        if (this.shampooLevel < 2 || this.waterLevel < 10) {
            System.out.println("\nNíveis insuficientes de shampoo ou água.\n");
            this.machineStatus();
            return "sem_recursos";
        }

        if (!this.isClean) {
            System.out.println("A máquina está suja. Limpe-a antes de lavar o pet.");
            return "suja";
        }

        this.waterLevel -= 10;
        this.shampooLevel -= 2;

        this.currentPet = pet;

        Random rand = new Random();
        int result = rand.nextInt(3) + 1;

        if (result == 1) {
            pet.setWashed(true);
            System.out.println(pet.getName() + " foi lavado.");
            this.currentPet = null;
            return "sucesso";
        } else {
            if(pet instanceof Dog) {
                Dog dog = (Dog) pet;
                System.out.print(dog.aggressiveBehavior());
            }else if(pet instanceof Cat){
                Cat cat = (Cat) pet;
                System.out.print(cat.aggressiveBehavior());
            }
            setClean(false);
            System.out.println("\nLavagem mal sucedida, maquina suja.");
            return "falha";
        }
    }

    public String refillWater() {
        if (this.waterLevel + 2 <= 30) {
            this.waterLevel += 2;
            System.out.println("O nível de água agora é: " + this.waterLevel);
            return("Nao_cheio");
        }
        System.out.println("Reservatorio de agua cheio.");
        return("reservatorio_cheio");
    }

    public String refillShampoo() {
        if (this.shampooLevel + 2 <= 10) {
            this.shampooLevel += 2;
            System.out.println("O nível de shampoo agora é: " + this.shampooLevel);
            return("Nao_cheio");
        }
        System.out.println("Reservatorio de shampoo cheio.");
        return("Reservatorio_cheio");
    }

    public String cleanMachine() {
        if (this.isClean) {
            System.out.println("A máquina já está limpa.");
            return "limpa";
        }

        if (this.waterLevel < 3 || this.shampooLevel < 1) {
            System.out.println("Não há recursos suficientes para limpar a máquina.");
            return "sem_recursos";
        }

        this.waterLevel -= 3;
        this.shampooLevel -= 1;
        this.isClean = true;
        this.machineStatus();
        return "sucesso_limpeza";
    }

    public void machineStatus() {
        System.out.println("\nNível de água atual: " + this.waterLevel +
                "\nNível de shampoo atual: " + this.shampooLevel +
                "\nLimpeza da maquina: " + (this.isClean ? "Limpa\n" : "Suja\n"));
    }

    public String retryBath() {
        if (this.currentPet == null) {
            System.out.println("Nenhum pet está na máquina para tentar novamente.");
            return "erro";
        }

        Random rand = new Random();
        int result = rand.nextInt(3) + 1;

        if (result == 1) {
            currentPet.setWashed(true);
            System.out.println(currentPet.getName() + " foi lavado com sucesso na segunda tentativa.");
            this.currentPet = null;
            return "sucesso";
        } else {
            if (currentPet instanceof Dog) {
                Dog dog = (Dog) currentPet;
                System.out.print(dog.aggressiveBehavior());
            } else if (currentPet instanceof Cat) {
                Cat cat = (Cat) currentPet;
                System.out.print(cat.aggressiveBehavior());
            }
            setClean(false);
            System.out.println("\nLavagem mal sucedida novamente. Máquina suja.");
            return "falha";
        }
    }

}
