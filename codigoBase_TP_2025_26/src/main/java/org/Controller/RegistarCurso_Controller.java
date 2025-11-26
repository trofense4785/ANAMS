package org.Controller;

import org.Model.*;

import java.time.LocalDate;
import java.util.List;

public class RegistarCurso_Controller {
    private final Instituicao instituicao;
    private Curso curso; // Entidade em estado


    public RegistarCurso_Controller(Instituicao instituicao) {
        this.instituicao = instituicao;
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

    public boolean adicionarModulo(String codigo, String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao, Formador formadorResponsavel, double ponderacao, List<SessaoModulo> lstSessoes, List<Classificacao> lstClassificacoes) {
        if (this.curso == null) return false;

        // Curso cria o módulo (Creator)
        Modulo m = this.curso.novoModulo(codigo, titulo, cargaHoraria, dataInicio, dataConclusao, formadorResponsavel, ponderacao, lstSessoes, lstClassificacoes);

        // Nota: Aqui a UI deveria chamar outro método para adicionar Sessões ao 'm'
        // antes de o adicionar ao curso, devido à regra "minimo 3 sessoes".
        // Para simplificar, assumimos que as sessões são adicionadas num sub-passo.

        return this.curso.addModulo(m);




    public void adicionarFormadorResponsavel (Formador formador) {
        if (this.curso != null) {
            this.curso.adicionarFormadorResponsavel(formador);
        }
    }


    public boolean registarCurso() {
        // Validação e Adição são feitas dentro do método adicionarCurso() da Instituicao
        return this.instituicao.adicionarCurso(this.curso);
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
}