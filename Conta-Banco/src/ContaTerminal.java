import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o numero da sua conta");
            int conta = scanner.nextInt();
        
        System.out.println("Digite sua agencia");
            String agencia = scanner.next();
            
        System.out.println("Digite seu nome completo");
            String nomeCompleto = scanner.next();

        System.out.println("Deposite um valor inicial para criar a conta");
            Double saldo = scanner.nextDouble();

        System.out.println("Olá " + nomeCompleto +", obrigado por criar uma conta em nosso banco, sua agência é "+ agencia +", conta "+ conta +" e seu saldo " + saldo + " já está disponível para saque");

        scanner.close();
    }
}
