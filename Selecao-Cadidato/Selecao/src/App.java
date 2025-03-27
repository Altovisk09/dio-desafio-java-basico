import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        ligarParaCandidatosSelecionados(selecaoCadidatos());
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

        List<String> candidatosSelecionados = new ArrayList<>();
        double salarioProposto = 2000.0;

        for (int i = 0; i < candidatos.length; i++) {
            String candidato = candidatos[i];
            Double salarioPretendido = salarioPretendido();
            String salarioFormatado = String.format("%.2f", salarioPretendido);

            if (salarioPretendido <= salarioProposto && candidatosSelecionados.size() <= 5) {
                candidatosSelecionados.add(candidato);
                System.out.println("O candidato " + candidato + " foi selecionado para a vaga\n");
            } else {
                System.out.println("salario pretendido (" + salarioFormatado + ") fora do escopo da empresa.\n");
            }
        }
        ;

        System.out.println("\nLista de candidatos selecionados:\n" + candidatosSelecionados);

        return candidatosSelecionados;
    };

    static void ligarParaCandidatosSelecionados(List<String> lista) {
        for (String candidato : lista) {
            int tentativasLigacao = 0;
            boolean atendeu = false;

            while (tentativasLigacao < 3 && !atendeu) {
                tentativasLigacao++;
                atendeu = atender();

                if (atendeu) {
                    atendeu = true;
                    System.out.println(
                            "\nCandidato " + candidato + " atendeu na " + tentativasLigacao + "tentativa, aprovado");
                } 
            }
            if (!atendeu) {
                System.out.println("\nCandidato " + candidato + " não atendeu após 3 tentativas, desqualificado.");
            }
        }

    };

    static double salarioPretendido() {
        return ThreadLocalRandom.current().nextDouble(1800, 2200);
    };

    static boolean atender() {
        return new Random().nextInt(3) == 1;
    };
}
