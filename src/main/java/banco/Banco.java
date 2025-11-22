package banco;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import model.Cliente;
import model.Conta;
import repository.ClienteRepository;
import repository.ContaRepository;

/**
 * Banco: camada orquestradora que mantém Map e Set obrigatórios e usa repositórios.
 */
public class Banco {

    private String nome;
    private Map<String, Conta> contasPorNumero;
    private Set<Cliente> clientesRegistrados;
    private ClienteRepository clienteRepository;
    private ContaRepository contaRepository;

    public Banco(String nome) {
        this.nome = nome;
        this.contasPorNumero = new HashMap<>();
        this.clientesRegistrados = new HashSet<>();
        this.clienteRepository = new ClienteRepository();
        this.contaRepository = new ContaRepository();

        // inicializar coleções a partir dos repositórios carregados (opcional)
        for (var c : contaRepository.listarTodas()) {
            contasPorNumero.put(c.getNumero(), c);
        }
        for (var cl : clienteRepository.listarTodos()) {
            clientesRegistrados.add(cl);
        }
    }

    public boolean registrarCliente(Cliente cliente) {
        if (cliente == null || cliente.getCpf() == null) return false;
        boolean novo = clientesRegistrados.add(cliente);
        if (novo) clienteRepository.salvar(cliente);
        return novo;
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return clienteRepository.buscarPorCpf(cpf);
    }

    public boolean adicionarConta(Conta conta) {
        if (conta == null || conta.getNumero() == null) return false;
        if (contasPorNumero.containsKey(conta.getNumero())) return false;
        contasPorNumero.put(conta.getNumero(), conta);
        contaRepository.salvar(conta);
        return true;
    }

    public Conta buscarConta(String numero) {
        return contasPorNumero.get(numero);
    }

    public Map<String, Conta> listarContas() {
        return new HashMap<>(contasPorNumero);
    }

    public Set<Cliente> listarClientes() {
        return new HashSet<>(clientesRegistrados);
    }

    public String getNome() { return nome; }
}





  
