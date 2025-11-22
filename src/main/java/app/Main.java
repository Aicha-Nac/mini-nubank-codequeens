package app;

import banco.Banco;
import model.Cliente;
import repository.ClienteRepository;
import repository.ContaRepository;
import service.AuthService;
import service.ContaService;
import service.Persistencia;

import java.util.Scanner;
import util.Utils; // se você tiver util para gerar agência/numero; senão pode remover

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("MiniNubank");

        ClienteRepository clienteRepo = new ClienteRepository();
        ContaRepository contaRepo = new ContaRepository();

        AuthService auth = new AuthService(clienteRepo);
        ContaService contaService = new ContaService(contaRepo, clienteRepo);

        Scanner sc = new Scanner(System.in);
        boolean rodando = true;

        while (rodando) {
            System.out.println("\n=== MINI NUBANK ===");
            System.out.println("1 - Criar cliente");
            System.out.println("2 - Criar conta corrente");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Ver extrato");
            System.out.println("7 - Listar clientes");
            System.out.println("8 - Listar contas");
            System.out.println("0 - Salvar e sair");
            System.out.print("Opção: ");
            String op = sc.nextLine();

            switch (op) {
                case "1" -> {
                    System.out.print("ID (p.ex CPF): ");
                    String id = sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    Cliente novo = new Cliente(id, nome, cpf, email);
                    if (banco.registrarCliente(novo)) {
                        System.out.println("Cliente registrado.");
                    } else {
                        System.out.println("Cliente já existe.");
                    }
                }
                case "2" -> {
                    System.out.print("CPF do titular: ");
                    String cpf = sc.nextLine();
                    System.out.print("Agência (ou enter para 0001): ");
                    String agencia = sc.nextLine();
                    if (agencia.isBlank()) agencia = "0001";
                    var c = contaService.criarContaCorrente(agencia, cpf);
                    if (c != null) {
                        banco.adicionarConta(c);
                    }
                }
                case "3" -> {
                    System.out.print("Número da conta: ");
                    String nc = sc.nextLine();
                    System.out.print("Valor: ");
                    double v = Double.parseDouble(sc.nextLine());
                    contaService.depositar(nc, v);
                }
                case "4" -> {
                    System.out.print("Número da conta: ");
                    String nc = sc.nextLine();
                    System.out.print("Valor: ");
                    double v = Double.parseDouble(sc.nextLine());
                    contaService.sacar(nc, v);
                }
                case "5" -> {
                    System.out.print("Origem: ");
                    String o = sc.nextLine();
                    System.out.print("Destino: ");
                    String d = sc.nextLine();
                    System.out.print("Valor: ");
                    double val = Double.parseDouble(sc.nextLine());
                    contaService.transferir(o, d, val);
                }
                case "6" -> {
                    System.out.print("Número da conta: ");
                    String nc = sc.nextLine();
                    var conta = contaRepo.buscarPorNumero(nc);
                    if (conta == null) System.out.println("Conta não encontrada.");
                    else {
                        System.out.println("Extrato:");
                        conta.getExtrato().forEach(System.out::println);
                        System.out.printf("Saldo: %.2f%n", conta.getSaldo());
                    }
                }
                case "7" -> clienteRepo.listarTodos().forEach(System.out::println);
                case "8" -> contaRepo.listarTodas().forEach(System.out::println);
                case "0" -> {
                    Persistencia.salvarClientes(clienteRepo.listarTodos());
                    Persistencia.salvarContas(contaRepo.listarTodas());
                    System.out.println("Dados salvos. Até mais!");
                    rodando = false;
                }
                default -> System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}

