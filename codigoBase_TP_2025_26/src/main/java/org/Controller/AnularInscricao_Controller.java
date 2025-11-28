package org.Controller;

import org.Model.Aluno;
import org.Model.Inscricao;
import org.Model.Instituicao;
import org.Model.Sessao;

import java.util.List;

public class AnularInscricao_Controller {

    private Instituicao instituicao;
    private Aluno aluno;
    private Inscricao inscricaoSelecionada;

    public AnularInscricao_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    // --- Passo 1.1: obterAlunoByEmail ---
    public void iniciarAnulacao() {
        // Simulação da UI a enviar o email (ou obter da sessão)
        String email = Sessao.getInstance().getEmailUsuarioLogado();

        // Controller chama Instituição
        this.aluno = instituicao.obterAlunoByEmail(email);
    }

    // --- Passo 1.2: obterListaInscricoesAsString ---
    public List<String> obterListaInscricoesAsString() {
        if (this.aluno != null) {
            // O aluno é que gera a lista filtrada (Expert)
            return this.aluno.obterListaInscricoesAsString();
        }
        return null;
    }

    // --- Passo 2.1: obterInscricao(id) ---
    public void selecionarInscricao(String idInscricao) {
        if (this.aluno != null) {
            this.inscricaoSelecionada = this.aluno.obterInscricao(idInscricao);
        }
    }

    public String getDadosInscricao() {
        return (inscricaoSelecionada != null) ? inscricaoSelecionada.toString() : "";
    }

    // --- Passo 3.1: registaAnulacao ---
    public boolean registaAnulacao() {
        if (this.aluno != null && this.inscricaoSelecionada != null) {
            // O aluno trata da lógica de anular a SUA inscrição
            return this.aluno.registaAnulacao(this.inscricaoSelecionada);
        }
        return false;
    }
}
