package org.Model;

public class Classificacao {
    private Aluno aluno;
    private Modulo modulo;
    private double nota; // Nota de 0 a 20 valores

    public Classificacao(Aluno aluno, Modulo modulo, double nota) {
        // Validações básicas
        if (aluno == null || modulo == null) {
            throw new IllegalArgumentException("Aluno e Módulo são obrigatórios.");
        }

        this.aluno = aluno;
        this.modulo = modulo;
        setNota(nota); // Usa o setter para validar 0-20

    }

    public void setNota(double nota) {
        if (nota < 0 || nota > 20) {
            throw new IllegalArgumentException("A nota deve ser entre 0 e 20.");
        }
        this.nota = nota;
    }

    // Getters and Setters
    public double getNota() { return nota; }
    public Modulo getModulo() { return modulo; }

    public Aluno getAluno() {
        return aluno;
    }
}