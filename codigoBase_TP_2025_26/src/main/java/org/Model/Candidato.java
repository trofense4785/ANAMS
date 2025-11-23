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

    public Candidato(String nome, LocalDate dataNascimento, String habilitacoesAcademicas, String email, String numeroCC, String genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.habilitacoesAcademicas = habilitacoesAcademicas;
        this.email = email;
        this.numeroCC = numeroCC;
        this.genero = genero;
        this.decisao = null;
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public String getNumeroCC() { return numeroCC; }
    public String getEmail() { return email; }
    // ... (restantes getters)

    public DecisaoCandidatura getDecisao() { return decisao; }
    public void setDecisao(DecisaoCandidatura decisao) { this.decisao = decisao; }
}