package com.veiculo;

public class Carro {
    private boolean ligado = false;
    ;
    private int velocidade = 0;
    private int marcha = 0;

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getMarcha() {
        return marcha;
    }

    public void setMarcha(int marcha) {
        this.marcha = marcha;
    }

    public void acelelerar() {
        if(getVelocidade() == 120){
            setVelocidade(120);
            return;
        }
        this.velocidade++;
    }

    public void trocarMarcha() {
        int v = getVelocidade();
        if (v == 0) setMarcha(0);
        else if (v <= 20) setMarcha(1);
        else if (v <= 40) setMarcha(2);
        else if (v <= 60) setMarcha(3);
        else if (v <= 80) setMarcha(4);
        else if (v <= 100) setMarcha(5);
        else setMarcha(6);
    }


    public void ligarDesligar() {
        setLigado(!ligado);
    }

    public void frear() {
        if(getVelocidade() == 0){
            setVelocidade(0);
            return;
        }
        this.velocidade--;
    }

    public String virarEsquerdaDireta(int direcao) {
        if (this.getVelocidade() >= 41) {
            return "Capotou, velocidade muita alta para virar";
        } else if (this.getVelocidade() == 0) {
            return "Carro parado, para virar é necessario estar em movimento";
        } else {
            return direcao == 1 ? "Virou para a direita" : "Virou para a esquerda";
        }
    }
    public void status(){
        System.out.println("Velocidade: "+getVelocidade());
        System.out.println("Marcha: "+getMarcha());
        System.out.println("Carro ligado: "+isLigado()+"\n");
    }
}
