package service;

import model.Cliente;
import model.Conta;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String DIR = "data";
    private static final String ARQ_CLIENTES = DIR + "/clientes.ser";
    private static final String ARQ_CONTAS = DIR + "/contas.ser";

    // salvar / carregar clientes
    public static void salvarClientes(List<Cliente> clientes) {
        try {
            new File(DIR).mkdirs();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQ_CLIENTES))) {
                oos.writeObject(clientes);
            }
            System.out.println("Clientes salvos em " + ARQ_CLIENTES);
        } catch (IOException e) {
            System.err.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Cliente> carregarClientes() {
        File f = new File(ARQ_CLIENTES);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Cliente>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar clientes: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // salvar / carregar contas
    public static void salvarContas(List<Conta> contas) {
        try {
            new File(DIR).mkdirs();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQ_CONTAS))) {
                oos.writeObject(contas);
            }
            System.out.println("Contas salvas em " + ARQ_CONTAS);
        } catch (IOException e) {
            System.err.println("Erro ao salvar contas: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Conta> carregarContas() {
        File f = new File(ARQ_CONTAS);
        if (!f.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            return (List<Conta>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar contas: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

