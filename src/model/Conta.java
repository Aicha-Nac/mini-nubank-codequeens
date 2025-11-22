package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Conta implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String numero;     // chave única da conta
    protected String agencia;
    protected double saldo;
    protected String titularCpf; // armazenamos CPF para referência
    protected List<Transacao> extrato = new ArrayList<>();

    public Conta() {}

    public Conta(String agencia, String titularCpf) {
        this.numero = UUID.randomUUID().toString().substring(0,8); // gera número simples
        this.agencia = agencia;
        this.titularCpf = titularCpf;
        this.saldo = 0.0;
    }

    public String getNumero() { return numero; }
    public String getAgencia() { return agencia; }
    public double getSaldo() { return saldo; }
    public String getTitularCpf() { return titularCpf; }
    public List<Transacao> getExtrato() { return extrato; }

    protected void registrarTransacao(Transacao t) {
        extrato.add(t);
    }

    public abstract boolean depositar(double valor);
    public abstract boolean sacar(double valor);

    @Override
    public String toString() {
        return String.format("Conta{num=%s, ag=%s, titular=%s, saldo=%.2f}", numero, agencia, titularCpf, saldo);
    }
}

