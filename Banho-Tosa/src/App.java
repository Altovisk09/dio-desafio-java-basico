import java.util.Scanner;
import com.petShop.model.WashMachine;
import com.petShop.model.pets.*;

public class App {
    public static void main(String[] args) {
        boolean inProcess = true;
        Scanner scanner = new Scanner(System.in);

        System.out.println("================================");
        System.out.println(" Bem-vindo ao Pet Shop Automatizado");
        System.out.println("================================");

        System.out.println("Seu pet é um cão(1) ou um gato(2)?");
        int tipoAnimal = lerOpcaoValida(scanner, "", 1, 2);

        System.out.println("Qual o nome do seu " + tipo(tipoAnimal) + "?");
        String petName = scanner.nextLine();

        System.out.println("Seu " + tipo(tipoAnimal) + " é agressivo?\nSim(1)  Não(2)");
        int agressividade = lerOpcaoValida(scanner, "", 1, 2);
        boolean isAgressive = agressividade == 1;

        Pet pet;
        if (tipoAnimal == 1) {
            pet = new Dog(petName, isAgressive);
        } else {
            pet = new Cat(petName, isAgressive);
        }

        WashMachine washMachine = new WashMachine();

        while (inProcess) {
            System.out.println("\n================================");
            System.out.println("===========   Menu  ============");
            System.out.println("================================");
            System.out.println("1 - Dar banho no pet");
            System.out.println("2 - Reabastecer água");
            System.out.println("3 - Reabastecer shampoo");
            System.out.println("4 - Limpar a máquina");
            System.out.println("5 - Ver status da máquina");
            System.out.println("9 - Sair");
            int option = lerOpcaoValida(scanner, "Digite uma opção: ", 1, 9);

            switch (option) {
                case 1:
                    boolean retry = true;

                    while (retry) {
                        String result = washMachine.giveBath(pet);

                        switch (result) {
                            case "sucesso":
                                retry = false;
                                break;

                            case "em_uso":
                                int respUso = lerOpcaoValida(scanner, "\nA máquina já está em uso. Tentar novamente? (1 - Sim / 2 - Voltar ao menu): ", 1, 2);
                                retry = respUso == 1;
                                break;

                            case "sem_recursos":
                                boolean resolverRecursos = true;
                                while (resolverRecursos) {
                                    int opcaoRecurso = lerOpcaoValida(scanner,
                                            "\nA máquina está com níveis baixos de recursos.\nO que deseja fazer?\n1 - Reabastecer água\n2 - Reabastecer shampoo\n3 - Limpar a máquina\n4 - Tentar dar banho novamente\n9 - Voltar ao menu\n",
                                            1, 9);

                                    switch (opcaoRecurso) {
                                        case 1:
                                            abastecerAgua(washMachine, scanner);
                                            break;
                                        case 2:
                                            abastecerShampoo(washMachine, scanner);
                                            break;
                                        case 3:
                                            limparMaquina(washMachine);
                                            break;
                                        case 4:
                                            resolverRecursos = false;
                                            break;
                                        case 9:
                                            retry = false;
                                            resolverRecursos = false;
                                            break;
                                        default:
                                            System.out.println("Opção inválida.");
                                    }
                                }
                                break;

                            case "suja":
                                int respLimpeza = lerOpcaoValida(scanner, "\nA máquina está suja. Deseja limpá-la agora? (1 - Sim / 2 - Voltar ao menu): ", 1, 2);
                                if (respLimpeza == 1) {
                                    String limpeza = washMachine.cleanMachine();
                                    if (limpeza.equals("sem_recursos")) {
                                        System.out.println("Recursos insuficientes para limpeza. Retornando ao menu.");
                                        retry = false;
                                    }
                                } else {
                                    retry = false;
                                }
                                break;

                            case "falha":
                                boolean tentarNovamente = true;
                                while (tentarNovamente) {
                                    int respFalha = lerOpcaoValida(scanner, "\nLavagem falhou. Deseja tentar novamente com o mesmo pet? (1 - Sim / 2 - Voltar ao menu): ", 1, 2);

                                    if (respFalha == 1) {
                                        String retryResult = washMachine.retryBath();
                                        if (retryResult.equals("sucesso")) {
                                            tentarNovamente = false;
                                            retry = false;
                                        } else if (retryResult.equals("falha")) {
                                            int repetir = lerOpcaoValida(scanner, "Deseja tentar mais uma vez? (1 - Sim / 2 - Voltar ao menu): ", 1, 2);
                                            if (repetir != 1) {
                                                tentarNovamente = false;
                                                retry = false;
                                            }
                                        } else {
                                            washMachine.setCurrentPet();
                                            tentarNovamente = false;
                                            retry = false;
                                        }
                                    } else {
                                        washMachine.setCurrentPet();
                                        tentarNovamente = false;
                                        retry = false;
                                    }
                                }
                                break;

                            default:
                                System.out.println("\nErro inesperado. Retornando ao menu.");
                                retry = false;
                                break;
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    abastecerAgua(washMachine, scanner);
                    break;
                case 3:
                    abastecerShampoo(washMachine, scanner);
                    break;
                case 4:
                    limparMaquina(washMachine);
                    break;
                case 5:
                    verStatusMaquina(washMachine);
                    break;
                case 9:
                    inProcess = false;
                    System.out.println("Encerrando o Pet Shop. Até a próxima!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }

            if (option != 9) {
                System.out.println("Pressione Enter para voltar ao menu...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    public static void abastecerAgua(WashMachine washMachine, Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            String resultado = washMachine.refillWater();
            if (resultado.equals("reservatorio_cheio")) {
                System.out.println("O reservatório está cheio. Retornando ao menu.");
                continuar = false;
            } else {
                int opcao = lerOpcaoValida(scanner, "Deseja encher o compartimento com mais água? Sim(1) Não(2): ", 1, 2);
                if (opcao == 2) {
                    continuar = false;
                }
            }
        }
    }

    public static void abastecerShampoo(WashMachine washMachine, Scanner scanner) {
        boolean continuar = true;
        while (continuar) {
            String resultado = washMachine.refillShampoo();
            if (resultado.equals("reservatorio_cheio")) {
                System.out.println("O reservatório está cheio. Retornando ao menu.");
                continuar = false;
            } else {
                int opcao = lerOpcaoValida(scanner, "Deseja encher o compartimento com mais shampoo? Sim(1) Não(2): ", 1, 2);
                if (opcao == 2) {
                    continuar = false;
                }
            }
        }
    }

    public static void limparMaquina(WashMachine washMachine) {
        String result = washMachine.cleanMachine();
        if (result.equals("limpa") || result.equals("sem_recursos")) {
            System.out.println("Retornando ao menu.\n");
        }
    }

    public static void verStatusMaquina(WashMachine washMachine) {
        washMachine.machineStatus();
    }

    public static String tipo(int opt) {
        return opt == 1 ? "Cachorro" : "Gato";
    }

    public static int lerOpcaoValida(Scanner scanner, String mensagem, int min, int max) {
        int opcao = -1;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensagem);
            try {
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    System.out.println("Entrada vazia. Digite um número.");
                    continue;
                }
                opcao = Integer.parseInt(entrada);
                if (opcao >= min && opcao <= max) {
                    valido = true;
                } else {
                    System.out.println("Opção fora do intervalo. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite apenas números.");
            }
        }
        return opcao;
    }
}
