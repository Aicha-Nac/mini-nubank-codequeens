
package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transacao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tipo; // DEPOSITO, SAQUE, TRANSFERENCIA, PIX
    private double valor;
    private LocalDateTime instante;
    private String descricao;

    public Transacao() {}

    public Transacao(String tipo, double valor, String descricao) {
        this.tipo = tipo;
        this.valor = valor;
        this.instante = LocalDateTime.now();
        this.descricao = descricao;
    }

    public String getTipo() { return tipo; }
    public double getValor() { return valor; }
    public LocalDateTime getInstante() { return instante; }
    public String getDescricao() { return descricao; }

    @Override
    public String toString() {
        return String.format("%s | %.2f | %s | %s", instante.toString(), valor, tipo, descricao);
    }
}

