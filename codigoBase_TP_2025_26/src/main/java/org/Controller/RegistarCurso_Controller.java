package org.Controller;

import org.Model.Curso;
import org.Model.Formador;
import org.Model.Instituicao;
import org.Model.TipoCurso;

import java.time.LocalDate;
import java.util.List;

public class RegistarCurso_Controller {
    private final Instituicao instituicao;
    private Curso curso; // Entidade em estado


    public RegistarCurso_Controller(Instituicao instituicao) {
        this.instituicao = instituicao;
    }


    public void novoCurso() {
        this.curso = instituicao.novoCurso();
    }


    public void setDados(String titulo, String sigla, TipoCurso tipo, String descricao,
                         LocalDate dataInicio, LocalDate dataTermino) {

        this.curso.setTitulo(titulo);
        this.curso.setSigla(sigla);
        this.curso.setTipo(tipo);
        this.curso.setDescricao(descricao);
        this.curso.setDataInicio(dataInicio);
        this.curso.setDataTermino(dataTermino);
    }


    public void adicionarFormadorResponsavel(Formador formador) {
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