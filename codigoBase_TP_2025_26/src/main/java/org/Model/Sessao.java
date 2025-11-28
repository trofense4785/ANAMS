package org.Model;

public class Sessao {

    private static Sessao instance;
    private String emailUsuarioLogado; // Guarda o email de quem está logado

    private Sessao() {}

    public static Sessao getInstance() {
        if (instance == null) {
            instance = new Sessao();
        }
        return instance;
    }

    // Método que o teu Controller está a tentar chamar
    public String getEmailUsuarioLogado() {
        return emailUsuarioLogado;
    }

    // Método para usares no Login (para definir quem entrou)
    public void login(String email) {
        this.emailUsuarioLogado = email;
    }

    public void logout() {
        this.emailUsuarioLogado = null;
    }
}
