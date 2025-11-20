package repository;

import model.Conta;
import java.util.*;

public class ContaRepository {

    private List<Conta> contas = new ArrayList<>();

    // Salvar conta
    public void salvar(Conta conta) {
        contas.add(conta);
    }

    // Listar todas
    public List<Conta> listarTodas() {
        return contas;
    }

    // Buscar conta pelo número
    public Conta buscarPorNumero(String numero) {
        for (Conta c : contas) {
            if (c.getNumero().equals(numero)) {
                return c;
            }
        }
        return null;
    }

    // Verificar se número já existe
    public boolean numeroExiste(String numero) {
        return buscarPorNumero(numero) != null;
    }
}

