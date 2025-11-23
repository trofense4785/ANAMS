package org.Model;

public class CA {
    private String sigla;
    private String nome;
    private String numeroCC;
    private String email;
    private String contacto;
    private Credenciais credenciais;

    public CA(String sigla, String nome, String numeroCC, String email, String contacto) {
        this.sigla = sigla;
        this.nome = nome;
        this.numeroCC = numeroCC;
        this.email = email;
        this.contacto = contacto;
        this.credenciais = null; // Definidas pelo sistema após o registo pelo Admin
    }

    // Getters and Setters
    public String getSigla() { return sigla; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    // ... (restantes getters)

    public Credenciais getCredenciais() { return credenciais; }
    public void setCredenciais(Credenciais credenciais) { this.credenciais = credenciais; }
}