package org.Model;

import java.time.LocalDate;
import java.util.UUID;

public class Inscricao {
    private String idInscricao;
    private LocalDate dataRealizacao;
    private String estado;

    private Curso curso;
    private Aluno aluno;

    public Inscricao(Aluno aluno, Curso curso) {
        this.aluno = aluno;
        this.curso = curso;


        this.idInscricao = "INS-" + UUID.randomUUID().toString().substring(0, 8);
        this.dataRealizacao = LocalDate.now();
        this.estado = "ativa";
    }

    public Curso getCurso() { return curso; }
    public String getEstado() { return estado; }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdInscricao() {
        return idInscricao;
    }

    @Override
    public String toString() {
        return String.format("Inscrição [%s] - Curso: %s (%s)", idInscricao, curso.getTitulo(), estado);
    }
}