package model;

public class ContaCorrente extends Conta {
    private static final long serialVersionUID = 1L;

    private double tarifaSaque = 1.50;

    public ContaCorrente() { super(); }

    public ContaCorrente(String agencia, String titularCpf) {
        super(agencia, titularCpf);
    }

    @Override
    public boolean depositar(double valor) {
        if (valor <= 0) return false;
        saldo += valor;
        registrarTransacao(new Transacao("DEPOSITO", valor, "Depósito em conta corrente"));
        return true;
    }

    @Override
    public boolean sacar(double valor) {
        double total = valor + tarifaSaque;
        if (valor <= 0 || total > saldo) return false;
        saldo -= total;
        registrarTransacao(new Transacao("SAQUE", -valor, "Saque (tarifa aplicada)"));
        return true;
    }
}



