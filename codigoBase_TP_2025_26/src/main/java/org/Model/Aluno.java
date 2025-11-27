package org.Model;

import java.time.LocalDate;

public class Aluno {
    private String codigoAluno; // Atribuído automaticamente (requisito 'e')
    private String nome;
    private LocalDate dataNascimento;
    private String numeroCC;
    private String email;
    private String contacto; // Assumindo que o contacto é derivado do candidato/matrícula
    private String habilitacoesAcademicas;
    private String genero;
    private Credenciais credenciais; // Atribuídas após aceitação da candidatura

    // Construtor usado pelo Controller/Service para criar um Aluno a partir de um Candidato
    public Aluno(Candidatura candidato, Credenciais credenciais) {
        this.codigoAluno = gerarCodigoAluno();
        this.nome = candidato.getNome();
        this.dataNascimento = candidato.getDataNascimento();
        this.numeroCC = candidato.getNumeroCC();
        this.email = candidato.getEmail();
        this.contacto = null; // Ajustar se o contacto for obrigatório no candidato
        this.habilitacoesAcademicas = candidato.getHabilitacoesAcademicas();
        this.genero = candidato.getGenero();
        this.credenciais = credenciais;
    }

    private String gerarCodigoAluno() {
        return "ALU" + System.currentTimeMillis();
    }

    // Getters and Setters
    public String getCodigoAluno() { return codigoAluno; }
    public String getNome() { return nome; }
    // ... (restantes getters)

    public Credenciais getCredenciais() { return credenciais; }
    public void setCredenciais(Credenciais credenciais) { this.credenciais = credenciais; }
}