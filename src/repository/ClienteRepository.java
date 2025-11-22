package repository;

import model.Cliente;

import java.util.*;

public class ClienteRepository {

    // === LIST : permite duplicatas, mantém ordem ===
    private List<Cliente> listaClientes = new ArrayList<>();

    // === SET : não permite duplicatas, ignora clientes com o mesmo ID ===
    private Set<Cliente> conjuntoClientes = new HashSet<>();

    // === MAP : chave = ID do cliente, valor = Cliente ===
    private Map<String, Cliente> mapaClientes = new HashMap<>();

    // -----------------------------
    // MÉTODOS CRUD USANDO LIST
    // -----------------------------
    public void adicionarClienteLista(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public List<Cliente> listarClientesLista() {
        return listaClientes;
    }

    public boolean removerClienteLista(String id) {
        return listaClientes.removeIf(c -> c.getId().equals(id));
    }

    // -----------------------------
    // MÉTODOS CRUD USANDO SET
    // -----------------------------
    public void adicionarClienteSet(Cliente cliente) {
        conjuntoClientes.add(cliente); // duplicates ignorados automaticamente
    }

    public Set<Cliente> listarClientesSet() {
        return conjuntoClientes;
    }

    public boolean removerClienteSet(String id) {
        return conjuntoClientes.removeIf(c -> c.getId().equals(id));
    }

    // -----------------------------
    // MÉTODOS CRUD USANDO MAP
    // -----------------------------
    public void adicionarClienteMap(Cliente cliente) {
        mapaClientes.put(cliente.getId(), cliente);
    }

    public Cliente buscarClienteMap(String id) {
        return mapaClientes.get(id);
    }

    public Map<String, Cliente> listarClientesMap() {
        return mapaClientes;
    }

    public Cliente removerClienteMap(String id) {
        return mapaClientes.remove(id);
    }
}

