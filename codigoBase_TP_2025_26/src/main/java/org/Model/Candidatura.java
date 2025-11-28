package org.Model;

import java.time.LocalDate;

public class Candidatura {
    // Dados obrigatórios
    private String nome;
    private LocalDate dataNascimento;
    private String habilitacoes; // "Licenciatura", "12º ano", etc.
    private String email;
    private String cc;
    private String genero;

    // Dados de gestão
    private LocalDate dataSubmissao;
    private Credenciais credenciais; // Geradas após submissão
    private int estado;
    private String justificacao;
    private LocalDate dataDecisao;
    private boolean aceite; // true = Aceite, false = Rejeitada


    public Candidatura(String nome, LocalDate dataNascimento, String habilitacoes, String email, String cc, String genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.habilitacoes = habilitacoes;
        this.email = email;
        this.cc = cc;
        this.genero = genero;

        this.dataSubmissao = LocalDate.now(); // Regista a data atual
        this.estado = 0; // Inicialmente "Submetida"
    }

    // Validação Local (Formato dos dados)
    public boolean valida() {
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome obrigatório.");
        if (cc == null || cc.length() != 9) throw new IllegalArgumentException("CC inválido (deve ter 9 dígitos).");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Email inválido.");
        if (dataNascimento == null) throw new IllegalArgumentException("Data de nascimento obrigatória.");

        // Verificar maioridade (Opcional, mas comum)
        if (dataNascimento.plusYears(18).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("O candidato deve ser maior de idade.");
        }
        return true;
    }

    public void setCredenciais(Credenciais credenciais) {
        this.credenciais = credenciais;
    }

    public void registarDecisao(boolean aceite, String justificacao, LocalDate dataDecisao) {
        this.aceite = aceite;
        this.justificacao = justificacao;
        this.dataDecisao = dataDecisao;
        this.estado = 1; // Marca como tratada
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



    // Getters para validação global
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