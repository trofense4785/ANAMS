package org.Model;

public class CA {
    private String sigla;
    private String nome;
    private String numeroCC;
    private String email;
    private String contacto;
    private Credenciais credenciais;

    public CA() {
    }

    public CA(String sigla, String nome, String numeroCC, String email, String contacto) {
        this.sigla = sigla;
        this.nome = nome;
        this.numeroCC = numeroCC;
        this.email = email;
        this.contacto = contacto;
        this.credenciais = null; // Definidas pelo sistema após o registo pelo Admin
    }

    // Getters and Setters
    public String getSigla() {
        return sigla;
    }
    public String getNome() {
        return nome;
    }

    public String getNumeroCC() {
        return numeroCC;
    }

    public String getEmail() {
        return email;
    }

    public String getContacto() {
        return contacto;
    }

    public Credenciais getCredenciais() {
        return credenciais;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNumeroCC(String numeroCC) {
        this.numeroCC = numeroCC;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public void setCredenciais(Credenciais credenciais) { this.credenciais = credenciais; }
}