
    import java.util.Scanner;

import model.Cliente;
import repository.ClienteRepository;
import repository.ContaRepository;
import service.ClienteService;
import service.ContaService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Criar repositórios
        ClienteRepository clienteRepo = new ClienteRepository();
        ContaRepository contaRepo = new ContaRepository();

        // Criar services
        ClienteService clienteService = new ClienteService(clienteRepo);
        ContaService contaService = new ContaService(contaRepo);

        int opcao;

        do {
            System.out.println("\n=== MINI BANCO DIGITAL ===");
            System.out.println("1 - Criar cliente");
            System.out.println("2 - Criar conta");
            System.out.println("3 - Depositar");
            System.out.println("4 - Sacar");
            System.out.println("5 - Transferir");
            System.out.println("6 - Listar clientes");
            System.out.println("7 - Listar contas");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {

                case 1: // criar cliente
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    clienteService.criarCliente(nome, cpf);
                    break;

                case 2: // criar conta
                    System.out.print("CPF do cliente: ");
                    String cpfCliente = sc.nextLine();
                    Cliente cliente = clienteService.buscarPorCpf(cpfCliente);

                    if (cliente == null) {
                        System.out.println("Cliente não encontrado!");
                        break;
                    }

                    System.out.print("Número da conta: ");
                    String numero = sc.nextLine();
                    contaService.criarConta(cliente, numero);
                    break;

                case 3: // depósito
                    System.out.print("Número da conta: ");
                    String contaDeposito = sc.nextLine();
                    System.out.print("Valor: ");
                    double valDep = sc.nextDouble();
                    contaService.depositar(contaDeposito, valDep);
                    break;

                case 4: // saque
                    System.out.print("Número da conta: ");
                    String contaSaque = sc.nextLine();
                    System.out.print("Valor: ");
                    double valSaq = sc.nextDouble();
                    contaService.sacar(contaSaque, valSaq);
                    break;

                case 5: // transferência
                    System.out.print("Conta origem: ");
                    String origem = sc.nextLine();
                    System.out.print("Conta destino: ");
                    String destino = sc.nextLine();
                    System.out.print("Valor: ");
                    double valTransf = sc.nextDouble();
                    contaService.transferir(origem, destino, valTransf);
                    break;

                case 6:
                    clienteService.listarClientes();
                    break;

                case 7:
                    contaService.listarContas();
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        sc.close();
    }
}

    
    
    }
}
