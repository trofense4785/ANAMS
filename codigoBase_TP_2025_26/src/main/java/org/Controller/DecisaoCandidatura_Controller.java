package org.Controller;

import org.Model.Candidatura;
import org.Model.Instituicao;

import java.time.LocalDate;
import java.util.List;

public class DecisaoCandidatura_Controller {

    private Instituicao instituicao;
    private Candidatura candidaturaSelecionada;

    public DecisaoCandidatura_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    /**
     * Passo 1: Obter lista para a UI mostrar.
     */
    public List<Candidatura> getListaCandidaturasPendentes() {
        return instituicao.getCandidaturasPendentes();
    }

    /**
     * Passo 2: Selecionar uma candidatura da lista.
     */
    public void selecionarCandidatura(Candidatura cand) {
        this.candidaturaSelecionada = cand;
    }

    /**
     * Passo 3: Registar a decisão (Aceite/Rejeitada) e Justificação.
     */
    public boolean registarDecisao(boolean aceite, String justificacao) {
        if (this.candidaturaSelecionada == null) return false;

        // A data da decisão é a data atual
        LocalDate dataDecisao = LocalDate.now();

        // Delega toda a lógica (criar aluno, enviar email) para a Instituição
        return instituicao.registarDecisaoCandidatura(this.candidaturaSelecionada, aceite, justificacao, dataDecisao);
    }

    public String getDadosCandidatura() {
        if (candidaturaSelecionada != null) return candidaturaSelecionada.toString();
        return "";
    }
}
