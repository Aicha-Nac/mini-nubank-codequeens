package repository;

import model.Cliente;
import service.Persistencia;

import java.util.*;

public class ClienteRepository {

    private List<Cliente> listaClientes;
    private Set<String> cpfs; // garante unicidade
    private Map<String, Cliente> mapaPorCpf;

    public ClienteRepository() {
        // tenta carregar da persistência; se não existir, cria vazios
        List<Cliente> carregados = Persistencia.carregarClientes();
        if (carregados != null) {
            this.listaClientes = new ArrayList<>(carregados);
        } else {
            this.listaClientes = new ArrayList<>();
        }

        // construir set e map a partir da lista
        this.cpfs = new HashSet<>();
        this.mapaPorCpf = new HashMap<>();
        for (Cliente c : listaClientes) {
            if (c.getCpf() != null) {
                cpfs.add(c.getCpf());
                mapaPorCpf.put(c.getCpf(), c);
            }
        }
    }

    public boolean salvar(Cliente cliente) {
        if (cliente == null || cliente.getCpf() == null) return false;
        if (cpfs.contains(cliente.getCpf())) {
            return false; // já existe
        }
        listaClientes.add(cliente);
        cpfs.add(cliente.getCpf());
        mapaPorCpf.put(cliente.getCpf(), cliente);
        Persistencia.salvarClientes(listaClientes);
        return true;
    }

    public Cliente buscarPorCpf(String cpf) {
        return mapaPorCpf.get(cpf);
    }

    public List<Cliente> listarTodos() {
        return new ArrayList<>(listaClientes);
    }

    public boolean removerPorCpf(String cpf) {
        Cliente c = mapaPorCpf.remove(cpf);
        if (c == null) return false;
        cpfs.remove(cpf);
        listaClientes.removeIf(x -> x.getCpf().equals(cpf));
        Persistencia.salvarClientes(listaClientes);
        return true;
    }
}






