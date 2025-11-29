package org.Model;

import java.time.LocalDate;

public class Candidatura {
    private String nome;
    private LocalDate dataNascimento;
    private String habilitacoes;
    private String email;
    private String cc;
    private String genero;


    private LocalDate dataSubmissao;
    private Credenciais credenciais;
    private int estado;
    private String justificacao;
    private LocalDate dataDecisao;
    private boolean aceite;


    public Candidatura(String nome, LocalDate dataNascimento, String habilitacoes, String email, String cc, String genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.habilitacoes = habilitacoes;
        this.email = email;
        this.cc = cc;
        this.genero = genero;

        this.dataSubmissao = LocalDate.now();
        this.estado = 0;
    }


    public boolean valida() {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome obrigatório.");
        if (cc == null || cc.length() != 9) throw new IllegalArgumentException("CC inválido (deve ter 9 dígitos).");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Email inválido.");
        if (dataNascimento == null) throw new IllegalArgumentException("Data de nascimento obrigatória.");


        if (dataNascimento.plusYears(18).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("O candidato deve ser maior de idade.");
        }
        return true;
    }

    public void setCredenciais(Credenciais credenciais) {
        this.credenciais = credenciais;
    }

    public Credenciais getCredenciais() {
        return credenciais;
    }

    public void registarDecisao(boolean aceite, String justificacao, LocalDate dataDecisao) {
        this.aceite = aceite;
        this.justificacao = justificacao;
        this.dataDecisao = dataDecisao;
        this.estado = 1;
    }

    public int getEstado() {
        return estado;
    }

    public boolean isAceite() {
        return aceite;
    }

    public String getJustificacao() {
        return justificacao;
    }




    public String getCc() { return cc; }
    public String getEmail() { return email; }
    public String getNome() { return nome; }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    @Override
    public String toString() {
        return String.format("Candidato: %s | CC: %s | Email: %s", nome, cc, email);
    }
}