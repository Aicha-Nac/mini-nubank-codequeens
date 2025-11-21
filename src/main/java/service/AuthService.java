package service;

import pessoas.Cliente;
import repository.ClienteRepository;

public class AuthService {

    private ClienteRepository clienteRepository;

    public AuthService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente login(String cpf) {
        Cliente cliente = clienteRepository.buscarPorCpf(cpf);

        if (cliente != null) {
            System.out.println("Login realizado com sucesso!");
            return cliente;
        } else {
            System.out.println("CPF não encontrado. Login falhou.");
            return null;
        }
    }
}

