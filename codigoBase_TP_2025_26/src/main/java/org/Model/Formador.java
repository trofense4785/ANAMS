package org.Model;

import java.time.LocalDate;

public class Formador {
    private String idFormador; // Gerado automaticamente
    private String nome;
    private LocalDate dataNascimento;
    private String numeroCC;
    private String email;
    private String contacto;
    private String areaEspecializacao;
    private Credenciais credenciais;

    public Formador(String nome, LocalDate dataNascimento, String numeroCC, String email, String contacto, String areaEspecializacao) {
        this.idFormador = gerarIdFormador();
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.numeroCC = numeroCC;
        this.email = email;
        this.contacto = contacto;
        this.areaEspecializacao = areaEspecializacao;
        this.credenciais = null; // Definidas pelo sistema após o registo pelo CA
    }

    private String gerarIdFormador() {
        return "FOR" + System.currentTimeMillis();
    }

    // Getters and Setters
    public String getIdFormador() {
        return idFormador;
    }

    public String getNome() {
        return nome;
    }

    public String getAreaEspecializacao() {
        return areaEspecializacao;
    }
    // ... (restantes getters)

    public Credenciais getCredenciais() {
        return credenciais;
    }

    public void setCredenciais(Credenciais credenciais) {
        this.credenciais = credenciais;
    }
}