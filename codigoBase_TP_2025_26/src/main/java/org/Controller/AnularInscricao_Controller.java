package org.Controller;

import org.Model.*;

import java.util.List;

public class AnularInscricao_Controller {

    private Instituicao instituicao;
    private Aluno alunoLogado;
    private Curso cursoSelecionado;

    public AnularInscricao_Controller() {
        this.instituicao = Instituicao.getInstance();
    }


    public void iniciarAnulacao() {
        String email = Sessao.getInstance().getEmailUsuarioLogado();
        this.alunoLogado = instituicao.getAlunoPorEmail(email);

        if (this.alunoLogado == null) {
            throw new IllegalStateException("Erro: Aluno não identificado na sessão.");
        }
    }


    public List<String> getListaCursosInscritos() {
        if (this.alunoLogado == null) iniciarAnulacao();

        return this.alunoLogado.obterCursosInscritosAtivos();
    }


    public void selecionarCurso(String sigla) {

        this.cursoSelecionado = instituicao.getCurso(sigla);
    }

    public String getDadosCurso() {
        return (cursoSelecionado != null) ? cursoSelecionado.toString() : "";
    }


    public boolean registaAnulacao() {
        if (this.cursoSelecionado != null && this.alunoLogado != null) {
            return this.alunoLogado.registarAnulacao(this.cursoSelecionado);
        }
        return false;
    }
}