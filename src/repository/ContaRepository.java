package repository;

import model.Conta;
import service.Persistencia;

import java.util.*;

public class ContaRepository {

    private List<Conta> listaContas;
    private Map<String, Conta> mapaPorNumero;

    public ContaRepository() {
        List<Conta> carregadas = Persistencia.carregarContas();
        if (carregadas != null) {
            this.listaContas = new ArrayList<>(carregadas);
        } else {
            this.listaContas = new ArrayList<>();
        }
        this.mapaPorNumero = new HashMap<>();
        for (Conta c : listaContas) {
            if (c.getNumero() != null) mapaPorNumero.put(c.getNumero(), c);
        }
    }

    public boolean salvar(Conta conta) {
        if (conta == null || conta.getNumero() == null) return false;
        if (mapaPorNumero.containsKey(conta.getNumero())) return false; // já existe
        listaContas.add(conta);
        mapaPorNumero.put(conta.getNumero(), conta);
        Persistencia.salvarContas(listaContas);
        return true;
    }

    public Conta buscarPorNumero(String numero) {
        return mapaPorNumero.get(numero);
    }

    public List<Conta> listarTodas() {
        return new ArrayList<>(listaContas);
    }

    public boolean removerPorNumero(String numero) {
        Conta c = mapaPorNumero.remove(numero);
        if (c == null) return false;
        listaContas.removeIf(x -> x.getNumero().equals(numero));
        Persistencia.salvarContas(listaContas);
        return true;
    }
}

