package app;

import banco.Banco;
import pessoas.Cliente;
import contas.ContaCorrente;
import service.AuthService;
import repository.ClienteRepository;

public class Main {
    public static void main(String[] args) {

        ClienteRepository clienteRepository = new ClienteRepository();
        AuthService auth = new AuthService(clienteRepository);

        Cliente c1 = new Cliente("Aïcha", "123.456.789-00");
        clienteRepository.salvar(c1);

        Cliente logado = auth.login("123.456.789-00"); // login correto

        if (logado != null) {
            ContaCorrente cc = new ContaCorrente(logado);
            cc.depositar(100);
            System.out.println("Saldo após depósito: " + cc.getSaldo());
        }

        // login errado
        auth.login("000.000.000-00");
    }
}


