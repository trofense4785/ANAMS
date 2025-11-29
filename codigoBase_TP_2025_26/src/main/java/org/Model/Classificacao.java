package org.Model;

public class Classificacao {
    private Aluno aluno;
    private Modulo modulo;
    private double nota;

    public Classificacao(Aluno aluno, Modulo modulo, double nota) {
        if (aluno == null || modulo == null) {
            throw new IllegalArgumentException("Aluno e Módulo são obrigatórios.");
        }

        this.aluno = aluno;
        this.modulo = modulo;
        setNota(nota);

    }

    public void setNota(double nota) {
        if (nota < 0 || nota > 20) {
            throw new IllegalArgumentException("A nota deve ser entre 0 e 20.");
        }
        this.nota = nota;
    }


    public double getNota() { return nota; }
    public Modulo getModulo() { return modulo; }

    public Aluno getAluno() {
        return aluno;
    }
}