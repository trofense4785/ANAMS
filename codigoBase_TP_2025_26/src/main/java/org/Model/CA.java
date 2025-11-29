package org.Model;

public class CA {
    private String nome;
    private String email;
    private String cc;
    private String sigla;
    private String contacto;
    private Credenciais credenciais;

    public CA(String nome, String email, String cc, String sigla, String contacto) {
        if (cc == null || cc.length() != 9) throw new IllegalArgumentException("CC inválido (deve ter 9 dígitos).");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Email inválido.");

        this.nome = nome;
        this.email = email;
        this.cc = cc;
        this.sigla = sigla;
        this.contacto = contacto;
    }

    public void setCredenciais(Credenciais credenciais) {
        this.credenciais = credenciais;
    }

    // Getters para validações globais
    public String getEmail() { return email; }
    public String getCc() { return cc; }
    public String getNome() { return nome; }

    public String getContacto() {
        return contacto;
    }

    public String getSigla() {
        return sigla;
    }

    public Credenciais getCredenciais() {
        return credenciais;
    }
}