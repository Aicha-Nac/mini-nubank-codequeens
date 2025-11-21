package service;

import pessoas.Cliente;
import java.io.*;
import java.util.List;

public class Persistencia {

    private static final String ARQUIVO = "clientes.dat";

    public static void salvarClientes(List<Cliente> clientes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(clientes);
            System.out.println("Clientes salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os clientes.");
            e.printStackTrace();
        }
    }

    public static List<Cliente> carregarClientes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO))) {
            return (List<Cliente>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de clientes não encontrado. Criando uma lista nova...");
            return new java.util.ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os clientes.");
            e.printStackTrace();
            return new java.util.ArrayList<>();
        }
    }
}


