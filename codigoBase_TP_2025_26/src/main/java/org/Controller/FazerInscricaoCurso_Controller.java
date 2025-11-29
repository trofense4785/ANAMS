package org.Controller;

import org.Model.*;

import java.util.List;

public class FazerInscricaoCurso_Controller {

    private Instituicao instituicao;
    private Aluno alunoLogado;
    private Curso cursoSelecionado;

    public FazerInscricaoCurso_Controller() {
        this.instituicao = Instituicao.getInstance();
        String emailLogado = Sessao.getInstance().getEmailUsuarioLogado();
        this.alunoLogado = instituicao.getAlunoPorEmail(emailLogado);

        if (this.alunoLogado == null) {
            throw new IllegalStateException("Erro: Nenhum aluno autenticado na sessão.");
        }
    }



    public List<Curso> getCursosDisponiveis() {
        return instituicao.getCursosPorEstado(EstadoCurso.A_INICIAR);
    }


    public void selecionarCurso(Curso curso) {
        this.cursoSelecionado = curso;
    }


    public boolean confirmarInscricao() {

        if (this.alunoLogado == null || this.cursoSelecionado == null) return false;

        return instituicao.registarInscricao(this.cursoSelecionado, this.alunoLogado);
    }


    public String getDadosCursoSelecionado() {
        return (cursoSelecionado != null) ? cursoSelecionado.toString() : "";
    }
}