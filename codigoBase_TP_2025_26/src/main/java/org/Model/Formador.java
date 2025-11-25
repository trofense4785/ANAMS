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

    public Formador() {
        this.idFormador = gerarIdFormador(); // O ID é gerado na criação do objeto
    }

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

    public LocalDate getDataNascimento() {
        return dataNascimento;
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

    public String getAreaEspecializacao() {
        return areaEspecializacao;
    }


    public Credenciais getCredenciais() {
        return credenciais;
    }

    public void setIdFormador(String idFormador) {
        this.idFormador = idFormador;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
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

    public void setAreaEspecializacao(String areaEspecializacao) {
        this.areaEspecializacao = areaEspecializacao;
    }

    public void setCredenciais(Credenciais credenciais) {
        this.credenciais = credenciais;
    }
}