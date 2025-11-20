package service;

import model.Cliente;
import java.util.*;

public class ClienteService {
    private Map<String, Cliente> clientes = new HashMap<>();

    public Cliente cadastrar(String nome, String cpf) {
        Cliente c = new Cliente(nome, cpf);
        clientes.put(cpf, c);
        return c;
    }
}

