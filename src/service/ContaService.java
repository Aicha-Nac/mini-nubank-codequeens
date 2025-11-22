package service;

import model.Conta;
import model.ContaCorrente;
import model.Cliente;
import repository.ContaRepository;
import repository.ClienteRepository;

public class ContaService {

    private ContaRepository contaRepo;
    private ClienteRepository clienteRepo;

    public ContaService(ContaRepository contaRepo, ClienteRepository clienteRepo) {
        this.contaRepo = contaRepo;
        this.clienteRepo = clienteRepo;
    }

    public Conta criarContaCorrente(String agencia, String cpfTitular) {
        Cliente titular = clienteRepo.buscarPorCpf(cpfTitular);
        if (titular == null) {
            System.out.println("Titular não encontrado.");
            return null;
        }
        ContaCorrente cc = new ContaCorrente(agencia, cpfTitular);
        boolean ok = contaRepo.salvar(cc);
        if (ok) {
            titular.adicionarConta(cc);
            System.out.println("Conta corrente criada. Número: " + cc.getNumero());
            return cc;
        } else {
            System.out.println("Erro ao criar conta (número duplicado).");
            return null;
        }
    }

    public boolean depositar(String numeroConta, double valor) {
        Conta c = contaRepo.buscarPorNumero(numeroConta);
        if (c == null) { System.out.println("Conta não encontrada."); return false; }
        return c.depositar(valor);
    }

    public boolean sacar(String numeroConta, double valor) {
        Conta c = contaRepo.buscarPorNumero(numeroConta);
        if (c == null) { System.out.println("Conta não encontrada."); return false; }
        return c.sacar(valor);
    }

    public boolean transferir(String origem, String destino, double valor) {
        Conta co = contaRepo.buscarPorNumero(origem);
        Conta cd = contaRepo.buscarPorNumero(destino);
        if (co == null || cd == null) { System.out.println("Conta origem/destino não encontrada"); return false; }
        if (!co.sacar(valor)) { System.out.println("Saldo insuficiente"); return false; }
        cd.depositar(valor);
        cd.registrarTransacao(new model.Transacao("TRANSFERENCIA_ENTRADA", valor, "Recebido por transferência"));
        co.registrarTransacao(new model.Transacao("TRANSFERENCIA_SAIDA", -valor, "Enviado por transferência"));
        Persistencia.salvarContas(contaRepo.listarTodas());
        return true;
    }
}

