package org.Model;

public class Classificacao {
    private Aluno aluno;
    private Modulo modulo;
    private double nota; // Nota de 0 a 20 valores

    public Classificacao(Aluno aluno, Modulo modulo, double nota) {
        // Validação da nota (0 a 20) deve ser feita no Controller/Service
        this.aluno = aluno;
        this.modulo = modulo;
        this.nota = nota;

        // ** Importante **: Associa a classificação ao módulo para que o Módulo possa calcularNota()
        modulo.setClassificacao(this);
    }

    // Getters and Setters
    public double getNota() { return nota; }
    public Modulo getModulo() { return modulo; }
    // ...
}