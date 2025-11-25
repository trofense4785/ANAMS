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
        // A Instituicao deve ter um método para instanciar o objeto Curso
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
        // Adiciona um formador à lista interna do curso em criação (requisito 'a')
        this.curso.adicionarFormadorResponsavel(formador);
    }

    public boolean registarCurso() {
        // Validação (unicidade da sigla) e Adição ao repositório
        if (this.instituicao.adicionarCurso(this.curso)) {
            System.out.println("✅ Curso " + curso.getTitulo() + " registado com sucesso.");
            return true;
        }
        System.out.println("❌ Falha no registo: Sigla do curso já existe.");
        return false;
    }

    // Métodos auxiliares para a UI
    public List<TipoCurso> getTiposCurso() { return this.instituicao.getTiposCurso(); }
    public List<Formador> getFormadores() { return this.instituicao.getFormadores(); }
}