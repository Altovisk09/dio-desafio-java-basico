import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {

        System.out.println("==================== PROCESSO SELETIVO ====================\n");
        ligarParaCandidatosSelecionados(selecaoCadidatos());
        System.out.println("\n==================== PROCESSO FINALIZADO ====================\n");
    };

    public static void analisarCandidatos(double salarioPretendido) {
        double salarioProposto = 2000.0;

        if (salarioPretendido > salarioProposto) {
            System.out.println("Ligar para candidato com contra proposta");
        } else {
            System.out.println("Aguardar demais candidatos finalizarem a candidatura");
        }
    };

    public static List<String> selecaoCadidatos() {
        String[] candidatos = { "Matheus", "Julia", "Kaylane", "Jamile", "Pedro", "Thales" };

        System.out.println("\nParticipantes do processo:\n" + Arrays.toString(candidatos));

        List<String> candidatosSelecionados = new ArrayList<>();
        double salarioProposto = 2000.0;

        System.out.println("\nFILTRANDO CANDIDATOS...\n");

        for (int i = 0; i < candidatos.length; i++) {
            String candidato = candidatos[i];
            Double salarioPretendido = salarioPretendido();
            String salarioFormatado = String.format("%.2f", salarioPretendido);

            if (salarioPretendido <= salarioProposto && candidatosSelecionados.size() <= 5) {
                candidatosSelecionados.add(candidato);
            } else {
                System.out.println("Candidato " + candidato + " RECUSADO (R$ " + salarioFormatado
                        + ") salario fora do escopo.");
            }
        }
        ;
        System.out.println("\nLista de candidatos selecionados:\n" + candidatosSelecionados);

        return candidatosSelecionados;
    };

    static void ligarParaCandidatosSelecionados(List<String> lista) {
        System.out.println("\n================ TENTANDO CONTATO COM APROVADOS =================");
        List<String> finalistas = new ArrayList<>();

        for (String candidato : lista) {
            int tentativasLigacao = 0;
            boolean atendeu = false;

            while (tentativasLigacao < 3 && !atendeu) {
                tentativasLigacao++;
                atendeu = atender();

                if (atendeu) {
                    atendeu = true;
                    System.out.println(
                            "\nCandidato " + candidato + " atendeu na " + tentativasLigacao + " tentativa, aprovado");
                    finalistas.add(candidato);
                }
            }
            if (!atendeu) {
                System.out.println("\nCandidato " + candidato + " não atendeu após 3 tentativas, desqualificado.");
            }
        }
        System.out.println("\n==================== FINALISTAS ====================\n");
        System.out.println(finalistas);

    };

    static double salarioPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    };

    static boolean atender() {
        return new Random().nextInt(3) == 1;
    };
}
