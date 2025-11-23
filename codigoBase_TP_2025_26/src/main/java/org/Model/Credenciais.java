package org.Model;


public class Credenciais {
    private String login;
    private String password;

    public Credenciais(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Getters and Setters
    public String getLogin() { return login; }
    public String getPassword() { return password; }
    // ...
}