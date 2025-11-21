package repository;

import pessoas.Cliente;
import service.Persistencia;
import java.util.List;

public class ClienteRepository {

    private List<Cliente> clientes;

    public ClienteRepository() {
        this.clientes = Persistencia.carregarClientes();
    }

    public void salvar(Cliente cliente) {
        clientes.add(cliente);
        Persistencia.salvarClientes(clientes);
    }

    public Cliente buscarPorCpf(String cpf) {
        for (Cliente c : clientes) {
            if (c.getCpf().equals(cpf)) {
                return c;
            }
        }
        return null;
    }

    public List<Cliente> listarTodos() {
        return clientes;
    }
}

