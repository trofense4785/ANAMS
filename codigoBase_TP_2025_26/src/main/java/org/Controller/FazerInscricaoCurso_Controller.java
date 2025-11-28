package org.Controller;

import org.Model.*;

import java.util.List;

public class FazerInscricaoCurso_Controller {

    private Instituicao instituicao;
    private Aluno alunoLogado;
    private Curso cursoSelecionado;

    public FazerInscricaoCurso_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    /**
     * Passo 1.1: obterAlunoByEmail(email)
     * No código real, em vez de receber o email da UI (inseguro),
     * vamos buscar o email da Sessão e pedir à Instituição.
     */
    public void iniciarInscricao() {
        String emailLogado = Sessao.getInstance().getEmailUsuarioLogado();
        // Diagrama 1.1: O controller pede à instituição o objeto aluno
        this.alunoLogado = instituicao.getAlunoPorEmail(emailLogado);

        if (this.alunoLogado == null) throw new IllegalStateException("Erro: Aluno não identificado.");
    }

    /**
     * Passo 1.2: obterListaCursosAsString("A iniciar")
     */
    public List<String> getListaCursosAIniciar() {
        // Chama o método da instituição que faz o loop
        return instituicao.obterListaCursosAsString(EstadoCurso.A_INICIAR);
    }

    /**
     * Passo 2.1: obterCurso(sigla)
     */
    public void selecionarCurso(String sigla) {
        this.cursoSelecionado = instituicao.getCurso(sigla);
    }

    /**
     * Passo 3.1: registaInscricao()
     */
    public boolean registaInscricao() {
        if (this.cursoSelecionado != null && this.alunoLogado != null) {
            // Diagrama 3.1.1: Controller chama Instituição
            // A Instituição depois chama o Aluno
            return instituicao.registarInscricao(this.cursoSelecionado, this.alunoLogado);
        }
        return false;
    }

    // Auxiliar para a UI mostrar dados antes de confirmar
    public String getDadosCursoSelecionado() {
        return (cursoSelecionado != null) ? cursoSelecionado.toString() : "";
    }
}