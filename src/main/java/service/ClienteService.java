package service;

import model.Cliente;
import repository.ClienteRepository;

public class ClienteService {

    private ClienteRepository clienteRepository;

    // Construtor recebe o repositório (injeção de dependência)
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Criar um cliente novo
    public Cliente criarCliente(String nome, String cpf) {

        if (clienteRepository.cpfExiste(cpf)) {
            System.out.println("Erro: já existe cliente com este CPF.");
            return null;
        }

        Cliente cliente = new Cliente(nome, cpf);
        clienteRepository.salvar(cliente);

        System.out.println("Cliente criado com sucesso!");
        return cliente;
    }

    // Buscar cliente por CPF
    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.buscarPorCpf(cpf);
    }

    // Listar todos
    public void listarClientes() {
        for (Cliente c : clienteRepository.listarTodos()) {
            System.out.println(c);
        }
    }
}

