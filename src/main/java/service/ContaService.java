package service;

import model.Cliente;
import model.Conta;
import model.ContaCorrente;
import repository.ContaRepository;

public class ContaService {

    private ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    // Criar nova conta para um cliente existente
    public Conta criarConta(Cliente cliente, String numero) {

        if (contaRepository.numeroExiste(numero)) {
            System.out.println("Erro: já existe conta com esse número!");
            return null;
        }

        Conta conta = new ContaCorrente(numero, cliente);
        contaRepository.salvar(conta);

        System.out.println("Conta criada com sucesso!");
        return conta;
    }

    // Depositar
    public void depositar(String numeroConta, double valor) {
        Conta conta = contaRepository.buscarPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }

        conta.depositar(valor);
        System.out.println("Depósito realizado.");
    }

    // Sacar
    public void sacar(String numeroConta, double valor) {
        Conta conta = contaRepository.buscarPorNumero(numeroConta);

        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }

        if (!conta.sacar(valor)) {
            System.out.println("Erro: saldo insuficiente.");
            return;
        }

        System.out.println("Saque realizado.");
    }

    // Transferir
    public void transferir(String origem, String destino, double valor) {

        Conta contaOrigem = contaRepository.buscarPorNumero(origem);
        Conta contaDestino = contaRepository.buscarPorNumero(destino);

        if (contaOrigem == null || contaDestino == null) {
            System.out.println("Erro: conta de origem ou destino inexistente.");
            return;
        }

        if (!contaOrigem.sacar(valor)) {
            System.out.println("Erro: saldo insuficiente.");
            return;
        }

        contaDestino.depositar(valor);
        System.out.println("Transferência realizada com sucesso!");
    }

    // Listar contas
    public void listarContas() {
        for (Conta c : contaRepository.listarTodas()) {
            System.out.println(c);
        }
    }
}

