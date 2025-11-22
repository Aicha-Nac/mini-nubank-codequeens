package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;     // usar CPF ou ID gerado
    private String nome;
    private String cpf;
    private String email;
    private transient List<Conta> contas = new ArrayList<>(); // transient: não precisamos serializar referências duplicadas dependendo da escolha

    public Cliente() {}

    public Cliente(String id, String nome, String cpf, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.contas = new ArrayList<>();
    }

    // getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public void adicionarConta(Conta c) {
        if (contas == null) contas = new ArrayList<>();
        contas.add(c);
    }
    public List<Conta> getContas() {
        if (contas == null) contas = new ArrayList<>();
        return contas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Cliente{" + "id='" + id + '\'' + ", nome='" + nome + '\'' + ", cpf='" + cpf + '\'' + ", email='" + email + '\'' + '}';
    }
}









