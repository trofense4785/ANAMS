package org.Model;

import java.time.LocalDate;

public class Candidato {
    private String nome;
    private LocalDate dataNascimento;
    private String habilitacoesAcademicas;
    private String email;
    private String numeroCC;
    private String genero;
    private DecisaoCandidatura decisao;

    public Candidato() {
    }

    public Candidato(String nome, LocalDate dataNascimento, String habilitacoesAcademicas, String email, String numeroCC, String genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.habilitacoesAcademicas = habilitacoesAcademicas;
        this.email = email;
        this.numeroCC = numeroCC;
        this.genero = genero;
        this.decisao = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getHabilitacoesAcademicas() {
        return habilitacoesAcademicas;
    }

    public void setHabilitacoesAcademicas(String habilitacoesAcademicas) {
        this.habilitacoesAcademicas = habilitacoesAcademicas;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCC() {
        return numeroCC;
    }

    public void setNumeroCC(String numeroCC) {
        this.numeroCC = numeroCC;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public DecisaoCandidatura getDecisao() { return decisao; }
    public void setDecisao(DecisaoCandidatura decisao) { this.decisao = decisao; }


}