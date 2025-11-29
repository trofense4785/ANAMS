package org.Model;

public class Admin {
    private Credenciais credenciais;

    public Admin(String username, String password) {
        this.credenciais = new Credenciais(username, password);
    }


    public Credenciais getCredenciais() { return credenciais; }
    public void setCredenciais(Credenciais credenciais) { this.credenciais = credenciais; }
}
