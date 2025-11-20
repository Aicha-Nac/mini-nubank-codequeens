package service;

import model.*;
import java.util.*;

public class ContaService {
    private Map<String, Conta> contas = new HashMap<>();

    public Conta criarConta(Cliente cliente) {
        Conta nova = new ContaCorrente(cliente);
        contas.put(cliente.getCpf(), nova);
        return nova;
    }
}

