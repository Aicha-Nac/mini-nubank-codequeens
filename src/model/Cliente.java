package model;

import java.util.Objects;

public class Cliente {

    private String id;
    private String nome;
    private String email;

    public Cliente() {}

    public Cliente(String id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // hashCode e equals (necessários para Set e Map)
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente other = (Cliente) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }



private List<Conta> contas = new ArrayList<>();

public void adicionarConta(Conta conta) {
    contas.add(conta);
}

public List<Conta> getContas() {
    return contas;
import java.util.List;
import java.util.ArrayList;



}



