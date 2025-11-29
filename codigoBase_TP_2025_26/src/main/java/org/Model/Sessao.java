package org.Model;

public class Sessao {

    private static Sessao instance;
    private String emailUsuarioLogado;
    private Sessao() {}

    public static Sessao getInstance() {
        if (instance == null) {
            instance = new Sessao();
        }
        return instance;
    }


    public String getEmailUsuarioLogado() {
        return emailUsuarioLogado;
    }


    public void login(String email) {
        this.emailUsuarioLogado = email;
    }

    public void logout() {
        this.emailUsuarioLogado = null;
    }
}
