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

    private List<Modulo> listaModulos;
    private List<Formador> formadoresResponsaveis; // Novo requisito 'a'


    public Curso(String titulo, String sigla, TipoCurso tipo, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
        this.titulo = titulo;
        this.sigla = sigla;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.estado = 0; // Estado inicial "A iniciar"
        this.listaModulos = new ArrayList<>();
        this.formadoresResponsaveis = new ArrayList<>();
    }


    // Método para adicionar módulos (com verificação do estado - requisito 'b')
    public boolean addModulo(Modulo m) {
        // 1. Validar se o módulo cumpre as regras dele (ex: min 3 sessões) [cite: 11]
        if (!m.valida()) {
            return false;
        }

        // 2. Validar Soma de Ponderações (IT2)
        double somaAtual = listaModulos.stream().mapToDouble(Modulo::getPonderacao).sum();
        if (somaAtual + m.getPonderacao() > 100) {
            throw new IllegalArgumentException("A soma das ponderações dos módulos não pode exceder 100%.");
        }

        return listaModulos.add(m);
    }

    public Modulo novoModulo(String codigo, String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao, Formador formadorResponsavel, double ponderacao, List<SessaoModulo> lstSessoes, List<Classificacao> lstClassificacoes) {
        return new Modulo(codigo, titulo, cargaHoraria, dataInicio, dataConclusao, formadorResponsavel, ponderacao, lstSessoes, lstClassificacoes);
    }

    // Método para adicionar formador responsável
    public void addResponsavel(Formador formador) {
        if (formador != null && !formadoresResponsaveis.contains(formador)) {
            this.formadoresResponsaveis.add(formador);
        }
    }

    public boolean validarEstado() {
        return !listaModulos.isEmpty();
    }

    // Implementação da interface Calculavel (calcula a nota final do curso)
    @Override
    public double calcularNota() {
        // IT2: Nota final calculada automaticamente baseada nos módulos e ponderações [cite: 120]
        double notaFinal = 0;
        for (Modulo m : listaModulos) {
            notaFinal += m.calcularNota() * (m.getPonderacao() / 100.0);
        }
        return notaFinal;
    }

    public String getSigla() {
        return sigla;
    }

    public int getEstado() {
        return estado;
    }

    public List<Modulo> getListaModulos() {
        return listaModulos;
    }
}




