package banco;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Cliente;
import model.Conta;
import repository.ClienteRepository;
import repository.ContaRepository;

public class Banco {

    private String nome;

    // Coleções obrigatórias do projeto
    private Map<String, Conta> contasPorNumero;     // Map: número → conta
    private Set<Cliente> clientesRegistrados;       // Set: clientes únicos

    // Repositórios
    private ClienteRepository clienteRepository;
    private ContaRepository contaRepository;

    public Banco(String nome) {
        this.nome = nome;

        this.contasPorNumero = new HashMap<>();
        this.clientesRegistrados = new HashSet<>();

        this.clienteRepository = new ClienteRepository();
        this.contaRepository = new ContaRepository();
    }

    // -------------------------------
    // MÉTODOS DE CLIENTE
    // -------------------------------

    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null) return false;

        boolean novo = clientesRegistrados.add(cliente);
        if (novo) {
            clienteRepository.salvar(cliente);
            return true;
        }
        return false;
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.buscarPorCpf(cpf);
    }

    public Set<Cliente> listarClientes() {
        return new HashSet<>(clientesRegistrados);
    }

    // -------------------------------
    // MÉTODOS DE CONTA
    // -------------------------------

    public boolean adicionarConta(Conta conta) {
        if (conta == null) return false;

        String numero = conta.getNumero();

        if (!contasPorNumero.containsKey(numero)) {
            contasPorNumero.put(numero, conta);
            contaRepository.salvar(conta);
            return true;
        }
        return false;
    }

    public Conta buscarConta(String numero) {
        return contasPorNumero.get(numero);
    }

    public Map<String, Conta> listarContas() {
        return new HashMap<>(contasPorNumero);
    }

    // -------------------------------
    // GETTERS
    // -------------------------------

    public String getNome() {
        return nome;
    }

}
