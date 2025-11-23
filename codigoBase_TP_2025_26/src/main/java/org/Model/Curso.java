package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Curso implements Calculavel {
    private String titulo;
    private String sigla;
    private TipoCurso tipo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private int estado; // 0-"A iniciar", 1-"Ativo", 2-"Suspenso", 3-"Cancelado", 4-"Concluído"

    private List<Modulo> modulos;
    private List<Formador> formadoresResponsaveis; // Novo requisito 'a'

    public Curso(String titulo, String sigla, TipoCurso tipo, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
        this.titulo = titulo;
        this.sigla = sigla;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.estado = 0; // Estado inicial "A iniciar"
        this.modulos = new ArrayList<>();
        this.formadoresResponsaveis = new ArrayList<>();
    }

    // Método para adicionar módulos (com verificação do estado - requisito 'b')
    public boolean adicionarModulo(Modulo modulo) {
        // Só é possível adicionar módulos a cursos que ainda não iniciaram (estado 0)
        if (this.estado == 0) {
            return modulos.add(modulo);
        }
        return false;
    }

    // Método para adicionar formador responsável
    public void adicionarFormadorResponsavel(Formador formador) {
        if (!formadoresResponsaveis.contains(formador)) {
            formadoresResponsaveis.add(formador);
        }
    }

    // Implementação da interface Calculavel (calcula a nota final do curso)
    @Override
    public double calcularNota() {
        double notaFinal = 0.0;
        double ponderacaoTotal = 0.0;

        for (Modulo modulo : modulos) {
            // Nota do módulo (chamando o calcularNota do Módulo)
            double notaModulo = modulo.calcularNota();

            // Só considera módulos que têm nota e ponderação > 0
            if (notaModulo >= 0 && modulo.getPonderacao() > 0) {
                notaFinal += notaModulo * (modulo.getPonderacao() / 100.0);
                ponderacaoTotal += modulo.getPonderacao();
            } else if (notaModulo < 0) {
                // Se um módulo ainda não tem classificação, a nota final não está disponível.
                return -1.0; // Usar -1 ou um Optional para indicar indisponibilidade
            }
        }

        // A nota final só é disponibilizada se o aluno tiver notas a todos os módulos
        if (modulos.isEmpty() || ponderacaoTotal != 100.0) {
            // Assumindo que 100% de ponderação é obrigatório para um curso concluído/avaliado
            return -1.0;
        }

        return notaFinal;
    }

    // Getters and Setters
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    public List<Modulo> getModulos() { return modulos; }
    public List<Formador> getFormadoresResponsaveis() { return formadoresResponsaveis; }
    // ...
}