package model;

import java.io.Serializable;

public abstract class Conta implements Transacionavel, Serializable {
    protected Cliente cliente;
    protected double saldo;

    public Conta(Cliente cliente) {
        this.cliente = cliente;
        this.saldo = 0.0;
    }

    public double getSaldo() { return saldo; }
    public Cliente getCliente() { return cliente; }
}

