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

    // --- Passo 1: Identificar Aluno ---
    public void iniciarAnulacao() {
        String email = Sessao.getInstance().getEmailUsuarioLogado();
        this.alunoLogado = instituicao.getAlunoPorEmail(email);

        if (this.alunoLogado == null) {
            throw new IllegalStateException("Erro: Aluno não identificado na sessão.");
        }
    }

    // --- Passo 2: Obter Lista (CORRIGIDO) ---
    public List<String> getListaCursosInscritos() {
        if (this.alunoLogado == null) iniciarAnulacao();

        // O erro estava aqui: o método no Aluno chama-se 'obterCursosInscritosAtivos'
        return this.alunoLogado.obterCursosInscritosAtivos();
    }

    // --- Passo 3: Selecionar Curso ---
    public void selecionarCurso(String sigla) {
        // Não selecionamos por ID de inscrição, mas sim pelo Curso (que vem da UI)
        this.cursoSelecionado = instituicao.getCurso(sigla);
    }

    public String getDadosCurso() {
        return (cursoSelecionado != null) ? cursoSelecionado.toString() : "";
    }

    // --- Passo 4: Registar Anulação (CORRIGIDO) ---
    public boolean registaAnulacao() {
        if (this.cursoSelecionado != null && this.alunoLogado != null) {
            // Chamamos diretamente o método que criámos na classe Aluno
            return this.alunoLogado.registarAnulacao(this.cursoSelecionado);
        }
        return false;
    }
}