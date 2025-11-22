package service;

import model.Cliente;
import repository.ClienteRepository;

public class AuthService {

    private ClienteRepository clienteRepo;

    public AuthService(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public Cliente loginPorCpf(String cpf) {
        Cliente c = clienteRepo.buscarPorCpf(cpf);
        if (c == null) {
            System.out.println("Cliente não encontrado para CPF: " + cpf);
        } else {
            System.out.println("Login bem sucedido: " + c.getNome());
        }
        return c;
    }
}


