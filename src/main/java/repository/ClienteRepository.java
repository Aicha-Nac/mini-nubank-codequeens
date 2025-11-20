package repository;

import model.Cliente;
import java.util.*;

public class ClienteRepository {

    // Lista que armazena todos os clientes
    private List<Cliente> clientes = new ArrayList<>();

    // Adiciona um cliente
    public void salvar(Cliente cliente) {
        clientes.add(cliente);
    }

    // Retorna todos os clientes
    public List<Cliente> listarTodos() {
        return clientes;
    }

    // Busca cliente pelo CPF
    public Cliente buscarPorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null; // não achou
    }

    // Verifica se já existe cliente com CPF X
    public boolean cpfExiste(String cpf) {
        return buscarPorCpf(cpf) != null;
    }
}

