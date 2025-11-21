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

        // cria cliente novo e salva
        Cliente c1 = new Cliente("Aïcha", "123.456.789-00");
        clienteRepository.salvar(c1);

        // faz login (deve funcionar mesmo após fechar o programa)
        Cliente logado = auth.login("123.456.789-00");

        if (logado != null) {
            ContaCorrente cc = new ContaCorrente(logado);
            cc.depositar(200);
            System.out.println("Saldo: " + cc.getSaldo());
        }
    }
}

