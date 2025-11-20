package app;



import model.banco.Banco;
import service.Persistencia;

public class Main {
    public static void main(String[] args) {

        // tenta carregar o banco salvo
        Banco banco = Persistencia.carregarBanco();

        if (banco == null) {
            banco = new Banco("MeuMiniNubank");
            System.out.println("Nenhum banco encontrado. Criado um novo.");
        } else {
            System.out.println("Banco carregado com sucesso!");
        }

        // exibir menu simples
        banco.menuPrincipal();

        // salvar ao sair
        Persistencia.salvarBanco(banco);
        System.out.println("Dados salvos!");
    }
}

    }
}

