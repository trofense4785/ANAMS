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

        if (!instituicao.validaCurso(this.curso)) {
            throw new IllegalArgumentException("Curso com esta sigla já existe.");
        }
    }

    public boolean adicionarModulo(String codigo, String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao,
                                   Formador formadorResponsavel, double ponderacao, List<SessaoModulo> lstSessoes, List<Classificacao>lstClassificacoes) {

        if (this.curso == null) return false;

        // --- OPÇÃO RECOMENDADA (SEQUENCIAL) ---
        // Vai à lista de módulos do curso, vê quantos tem e soma 1.
        // Exemplo: Se tem 0 módulos, gera "M-1". Se tem 1, gera "M-2".
        int numeroSequencial = this.curso.getListaModulos().size() + 1;
        codigo = "M-" + numeroSequencial;

        // --- OPÇÃO UUID (Se preferires arriscar) ---
        // String codigo = "M-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        // 1. Curso cria o módulo (Creator)
        Modulo m = this.curso.novoModulo(codigo, titulo, cargaHoraria, dataInicio, dataConclusao, formadorResponsavel, ponderacao, lstSessoes, lstClassificacoes);

        // 2. Tenta adicionar
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
