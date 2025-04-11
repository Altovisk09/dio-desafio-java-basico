import com.veiculo.Carro;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carro carro = new Carro();
        boolean noCarro = true;

        System.out.println("Você está no banco do motorista.");
        carro.status();

        while (noCarro) {
            String input = validarEntrada("Comando (W A S D E .): ", Set.of("W", "A", "S", "D", "E", "."));

            switch (input) {
                case "E":
                    carro.ligarDesligar();
                    System.out.println(carro.isLigado() ? "Carro ligado." : "Carro desligado.");
                    break;

                case "W":
                    if (carro.isLigado()) {
                        carro.acelelerar();
                        carro.trocarMarcha();
                        System.out.println("Acelerando... Velocidade: " + carro.getVelocidade() + " km/h");
                    } else {
                        System.out.println("O carro está desligado. Ligue-o com 'E'.");
                    }
                    break;

                case "S":
                    if (carro.isLigado()) {
                        carro.frear();
                        carro.trocarMarcha();
                        if (carro.getVelocidade() > 0) {
                            System.out.println("Freando... Velocidade: " + carro.getVelocidade() + " km/h");
                        } else {
                            System.out.println("Parado.");
                        }
                    } else {
                        System.out.println("O carro está desligado.");
                    }
                    break;

                case "A":
                    if (carro.isLigado()) {
                        String resposta = carro.virarEsquerdaDireta(0);
                        System.out.println(resposta);
                        if (resposta.contains("Capotou")) {
                            noCarro = false;
                        }
                    } else {
                        System.out.println("Ligue o carro para virar.");
                    }
                    break;

                case "D":
                    if (carro.isLigado()) {
                        String resposta = carro.virarEsquerdaDireta(1);
                        System.out.println(resposta);
                        if (resposta.contains("Capotou")) {
                            noCarro = false;
                        }
                    } else {
                        System.out.println("Ligue o carro para virar.");
                    }
                    break;

                case ".":
                    noCarro = false;
                    System.out.println("Você saiu do carro.");
                    break;
            }

            if (noCarro) {
                System.out.println();
                carro.status();
            }
        }

        scanner.close();
    }

    public static String validarEntrada(String mensagem, Set<String> opcoesValidas) {
        Scanner scanner = new Scanner(System.in);
        String entrada;

        while (true) {
            System.out.print(mensagem);
            entrada = scanner.nextLine().trim().toUpperCase();

            if (opcoesValidas.contains(entrada)) {
                return entrada;
            } else {
                System.out.println("Entrada inválida. Tente novamente.");
            }
        }
    }
}
