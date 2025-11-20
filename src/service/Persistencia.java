package service;

import java.io.*;
import java.util.Map;

public class Persistencia {

    public static void salvar(Object obj, String caminho) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(caminho));
        out.writeObject(obj);
        out.close();
    }

    public static Object carregar(String caminho) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(caminho));
        Object obj = in.readObject();
        in.close();
        return obj;
    }
}

