package org.Model;

public class Admin {
    private Credenciais credenciais;

    public Admin(String username, String password) {
        // Assume-se que o login e password são a informação necessária
        this.credenciais = new Credenciais(username, password);
    }

    // Getters and Setters
    public Credenciais getCredenciais() { return credenciais; }
    public void setCredenciais(Credenciais credenciais) { this.credenciais = credenciais; }
}
