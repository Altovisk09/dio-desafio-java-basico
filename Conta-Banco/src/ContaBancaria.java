public class ContaBancaria {
    private double saldo;
    private double chequeEspecial;
    private double chequeEspecialUsado = 0;

    public ContaBancaria(double depositoInicial) {
        this.saldo = depositoInicial;
        if (depositoInicial <= 500) {
            this.chequeEspecial = 50.0;
        } else {
            this.chequeEspecial = depositoInicial * 0.5;
        }
    }

    public void consultarSaldo() {
        System.out.printf("Saldo atual: R$ %.2f\n", saldo);
    }

    public void consultarChequeEspecial() {
        System.out.printf("Cheque especial disponível: R$ %.2f\n", chequeEspecial - chequeEspecialUsado);
    }

    public void verificarUsoChequeEspecial() {
        if (chequeEspecialUsado > 0) {
            System.out.printf("Você está usando R$ %.2f do cheque especial.\n", chequeEspecialUsado);
        } else {
            System.out.println("Você não está usando o cheque especial.");
        }
    }

    public void depositar(double valor) {
        System.out.printf("Depositando R$ %.2f...\n", valor);
        // Se houver uso de cheque especial, abate com taxa de 20%
        if (chequeEspecialUsado > 0) {
            double taxa = chequeEspecialUsado * 0.2;
            double totalCobrar = chequeEspecialUsado + taxa;

            if (valor >= totalCobrar) {
                valor -= totalCobrar;
                System.out.printf("R$ %.2f usados para cobrir o cheque especial (incluindo R$ %.2f de taxa).\n", totalCobrar, taxa);
                chequeEspecialUsado = 0;
            } else {
                double valorAbatido = valor / 1.2; // considerando que valor inclui os 20%
                chequeEspecialUsado -= valorAbatido;
                System.out.printf("R$ %.2f usados parcialmente para cobrir o cheque especial.\n", valor);
                valor = 0;
            }
        }
        saldo += valor;
        consultarSaldo();
    }

    public void sacar(double valor) {
        if (valor <= saldo) {
            saldo -= valor;
            System.out.printf("Saque de R$ %.2f realizado com sucesso.\n", valor);
        } else if (valor <= saldo + (chequeEspecial - chequeEspecialUsado)) {
            double restante = valor - saldo;
            saldo = 0;
            chequeEspecialUsado += restante;
            System.out.printf("Saque de R$ %.2f realizado com uso do cheque especial.\n", valor);
        } else {
            System.out.println("Saldo insuficiente, mesmo com o cheque especial.");
        }
    }

    public void pagarBoleto(double valor) {
        System.out.printf("Tentando pagar boleto de R$ %.2f...\n", valor);
        sacar(valor);
    }
}