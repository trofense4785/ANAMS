package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curso implements Calculavel {
    private String titulo;
    private String sigla;
    private TipoCurso tipo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private EstadoCurso estado;

    private List<Modulo> listaModulos;
    private List<Formador> formadoresResponsaveis;


    public Curso(String titulo, String sigla, TipoCurso tipo, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
        this.titulo = titulo;
        this.sigla = sigla;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.estado = EstadoCurso.A_INICIAR;
        this.listaModulos = new ArrayList<>();
        this.formadoresResponsaveis = new ArrayList<>();
    }


    public boolean addModulo(Modulo m) {
        if (!m.valida()) {
            return false;
        }

        if (m.getDataInicio().isBefore(this.dataInicio) || m.getDataConclusao().isAfter(this.dataTermino)) {
            throw new IllegalArgumentException("As datas do módulo devem estar compreendidas entre "
                    + this.dataInicio + " e " + this.dataTermino);
        }

        double somaAtual = listaModulos.stream().mapToDouble(Modulo::getPonderacao).sum();
        if (somaAtual + m.getPonderacao() > 100) {
            throw new IllegalArgumentException("A soma das ponderações dos módulos não pode exceder 100%.");
        }

        return listaModulos.add(m);
    }

    public Modulo novoModulo(String codigo, String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao, Formador formadorResponsavel, double ponderacao, List<SessaoModulo> lstSessoes, List<Classificacao> lstClassificacoes) {
        return new Modulo(codigo, titulo, cargaHoraria, dataInicio, dataConclusao, formadorResponsavel, ponderacao, lstSessoes, lstClassificacoes);
    }

    public void addResponsavel(Formador formador) {
        if (formador != null && !formadoresResponsaveis.contains(formador)) {
            this.formadoresResponsaveis.add(formador);
        }
    }

    public boolean validarEstado() {
        return !listaModulos.isEmpty();
    }

    public boolean isResponsavel(Formador formador) {
        if (this.formadoresResponsaveis.contains(formador)) {
            return true;
        }

        for (Modulo modulo : this.listaModulos) {
            if (modulo.getFormadorResponsavel().equals(formador)) {
                return true;
            }
        }

        return false;
    }

    private boolean validarTransicaoEstado(EstadoCurso novoEstado) {

        if (this.estado == EstadoCurso.CANCELADO || this.estado == EstadoCurso.CONCLUIDO) {
            return false;
        }

        if (this.estado == EstadoCurso.A_INICIAR && novoEstado == EstadoCurso.CONCLUIDO) {
            return false;
        }
        return true;
    }

    public void setEstado(EstadoCurso novoEstado) {
        if (validarTransicaoEstado(novoEstado)) {
            this.estado = novoEstado;
        } else {
            throw new IllegalStateException("Transição de estado inválida: de " + this.estado + " para " + novoEstado);
        }
    }

    public double calcularNota(Aluno aluno) {
        double notaFinal = 0.0;

        for (Modulo m : listaModulos) {
            Double notaModulo = m.getNotaAluno(aluno);

            if (notaModulo != null) {
                notaFinal += notaModulo * (m.getPonderacao() / 100.0);
            }
        }
        return notaFinal;
    }


    @Override
    public double calcularNota() {
        return 0.0;
    }

    public String getSigla() {
        return sigla;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public String getTitulo() {
        return titulo;
    }


    public List<Modulo> getListaModulos() {
        return listaModulos;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public String toString() {
        return String.format("Curso: %s (%s) | Estado Atual: %s", getTitulo(), getSigla(), estado);
    }

}




