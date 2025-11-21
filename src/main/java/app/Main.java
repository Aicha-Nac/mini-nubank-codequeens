package app;

import banco.Banco;
import pessoas.Cliente;
import contas.ContaCorrente;
import contas.ContaPoupanca;

public class Main {
    public static void main(String[] args) {

        Banco banco = new Banco();

        Cliente c1 = new Cliente("Aïcha", "123.456.789-00");
        ContaCorrente cc = new ContaCorrente(c1);

        banco.adicionarConta(cc);

        cc.depositar(100);
        System.out.println("Saldo CC: " + cc.getSaldo());

        cc.sacar(50);
        System.out.println("Saldo após saque: " + cc.getSaldo());

        // Criando poupança só para testar
        ContaPoupanca cp = new ContaPoupanca(c1);
        cp.depositar(200);
        cp.renderJuros();
        System.out.println("Saldo Poupança com juros: " + cp.getSaldo());
    }
}
