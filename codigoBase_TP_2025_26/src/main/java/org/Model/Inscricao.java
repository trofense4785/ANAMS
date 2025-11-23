package org.Model;

import java.time.LocalDate;

public class Inscricao {
    private String idInscricao; // Atribuído automaticamente
    private LocalDate dataRealizacao;
    private Aluno aluno;
    private Curso curso;
    private int estado; // 0-"ativa", 1-"cancelada", 2-"concluída"

    public Inscricao(Aluno aluno, Curso curso) {
        this.idInscricao = gerarIdInscricao();
        this.dataRealizacao = LocalDate.now();
        this.aluno = aluno;
        this.curso = curso;
        this.estado = 0; // Por omissão, "ativa"
    }

    private String gerarIdInscricao() {
        return "INS" + System.currentTimeMillis();
    }

    // Getters and Setters
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    public Aluno getAluno() { return aluno; }
    public Curso getCurso() { return curso; }
    // ...
}
