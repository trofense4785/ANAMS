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
        if (m.valida()) { // Valida regras do módulo (ex: min 3 sessões)
            // Valida se soma das ponderações não passa 100%
            double totalPonderacao = listaModulos.stream().mapToDouble(Modulo::getPonderacao).sum();
            if (totalPonderacao + m.getPonderacao() > 100) {
                throw new IllegalArgumentException("Soma das ponderações excede 100%.");
            }
            return listaModulos.add(m);
        }
        return false;
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

    public String getTitulo() {
        return titulo;
    }

    public String getSigla() {
        return sigla;
    }

    public TipoCurso getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public int getEstado() {
        return estado;
    }

    public List<Modulo> getListaModulos() {
        return listaModulos;
    }

    public List<Formador> getFormadoresResponsaveis() {
        return formadoresResponsaveis;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public void setTipo(TipoCurso tipo) {
        this.tipo = tipo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataTermino(LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setListaModulos(List<Modulo> listaModulos) {
        this.listaModulos = listaModulos;
    }

    public void setFormadoresResponsaveis(List<Formador> formadoresResponsaveis) {
        this.formadoresResponsaveis = formadoresResponsaveis;
    }
}




