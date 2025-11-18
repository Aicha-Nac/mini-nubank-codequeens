import java.util.HashMap;
import java.util.Map;

public class Banco {

    private Map<Integer, Conta> contas = new HashMap<>();
    private int proximoNumero = 1;

    public Conta criarConta() {
        Conta c = new ContaCorrente(proximoNumero++);
        contas.put(c.getNumero(), c);
        return c;
    }
}

