package service;

public class AuthService {
    public boolean login(String cpf) {
        return cpf != null && !cpf.isEmpty();
    }
}

