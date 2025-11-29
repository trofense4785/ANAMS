package org.Controller;

import org.Model.Curso;
import org.Model.EstadoCurso;
import org.Model.Instituicao;

import java.util.List;

public class AlterarEstadoCurso_Controller {

    private Instituicao instituicao;
    private Curso cursoSelecionado;

    public AlterarEstadoCurso_Controller() {
        this.instituicao = Instituicao.getInstance();
    }


    public List<Curso> obterListaCursos() {

        return instituicao.getLstCursos();
    }

    public EstadoCurso selecionarCurso(String sigla) {

        this.cursoSelecionado = instituicao.getCurso(sigla);

        if (this.cursoSelecionado != null) {
            return this.cursoSelecionado.getEstado();
        }
        return null;
    }


    public void alterarEstado(EstadoCurso novoEstado) {
        if (this.cursoSelecionado != null) {

            this.cursoSelecionado.setEstado(novoEstado);
        }
    }

    public String obterDadosCurso() {
        if (this.cursoSelecionado != null) {
            return this.cursoSelecionado.toString();
        }
        return "";
    }

    public EstadoCurso[] getEstadosPossiveis() {
        return new EstadoCurso[] {
                EstadoCurso.A_INICIAR,
                EstadoCurso.ATIVO,
                EstadoCurso.SUSPENSO,
                EstadoCurso.CANCELADO,
                EstadoCurso.CONCLUIDO
        };
    }
}