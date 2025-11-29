package org.Controller;

import org.Model.*;

import java.time.LocalDate;
import java.util.List;

public class RegistarCurso_Controller {
    private final Instituicao instituicao;
    private Curso curso; // Entidade em estado


    public RegistarCurso_Controller(Instituicao instituicao) {
        this.instituicao = Instituicao.getInstance();
    }

    public List<TipoCurso> getTiposCurso() {
        return instituicao.getLstTiposCurso();
    }

    public List<Formador> getListaFormadores() {
        return instituicao.getLstFormadores();
    }


    public void novoCurso(String titulo, String sigla, TipoCurso tipo, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
        this.curso = instituicao.novoCurso(titulo, sigla, tipo, descricao, dataInicio, dataTermino);

        if (dataTermino.isBefore(dataInicio)) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }

        if (!instituicao.validaCurso(this.curso)) {
            throw new IllegalArgumentException("Curso com esta sigla já existe.");
        }
    }

    // No ficheiro RegistarCurso_Controller.java

    public boolean adicionarModulo(String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao,
                                   Formador formadorResponsavel, double ponderacao, List<SessaoModulo> lstSessoes) {
        //                                                                                                       ^
        //                                                              Repara: REMOVI a 'List<Classificacao>' daqui

        if (this.curso == null) return false;

        // Gerar código sequencial
        int numeroSequencial = this.curso.getListaModulos().size() + 1;
        String codigoGerado = "M-" + numeroSequencial;

        // Cria uma lista de classificações vazia (porque o módulo é novo)
        List<Classificacao> classificacoesVazias = new java.util.ArrayList<>();

        // Chama o método do Curso (Creator) passando a lista vazia no fim
        Modulo m = this.curso.novoModulo(codigoGerado, titulo, cargaHoraria, dataInicio, dataConclusao, formadorResponsavel, ponderacao, lstSessoes, classificacoesVazias);

        // Adiciona ao curso
        try {
            return this.curso.addModulo(m);
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }



    public void adicionarFormadorResponsavel(Formador formador) {
        if (this.curso != null) {
            this.curso.addResponsavel(formador);
        }
    }


    public boolean registarCurso() {
        // Validação e Adição são feitas dentro do método adicionarCurso() da Instituicao
        return this.instituicao.registarCurso(this.curso);
    }


    public List<TipoCurso> getLstTiposCurso() {
        return this.instituicao.getLstTiposCurso();
    }


    public List<Formador> getFormadores() {
        return this.instituicao.getLstFormadores();
    }



    public Curso getCursoEmRegisto() {
        return this.curso;
    }
}
