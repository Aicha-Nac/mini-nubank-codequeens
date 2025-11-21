package contas;

import pessoas.Cliente;

public class ContaCorrente extends Conta {

    public ContaCorrente(Cliente cliente) {
        super(cliente);
    }

    @Override
    public boolean sacar(double valor) {
        double taxa = 1.50; // taxa fixa
        double total = valor + taxa;

        if (total <= saldo) {
            saldo -= total;
            return true;
        }
        return false;
    }
}

