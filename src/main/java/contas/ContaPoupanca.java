package contas;

import pessoas.Cliente;

public class ContaPoupanca extends Conta {

    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    public void renderJuros() {
        double juros = saldo * 0.003; // 0.3% ao mês (exemplo)
        saldo += juros;
    }
}

