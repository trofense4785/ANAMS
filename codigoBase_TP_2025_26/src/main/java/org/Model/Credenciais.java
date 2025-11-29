package org.Model;


public class Credenciais {
    private String username;
    private String password;

    public Credenciais(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // ----------------------------------------

    @Override
    public String toString() {
        return "User: " + username + " | Pass: " + password;
    }
}