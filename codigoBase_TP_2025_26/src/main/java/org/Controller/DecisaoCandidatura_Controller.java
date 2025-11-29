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


    public List<Candidatura> getListaCandidaturasPendentes() {
        return instituicao.getCandidaturasPendentes();
    }


    public void selecionarCandidatura(Candidatura cand) {
        this.candidaturaSelecionada = cand;
    }


    public boolean registarDecisao(boolean aceite, String justificacao) {
        if (this.candidaturaSelecionada == null) return false;

        LocalDate dataDecisao = LocalDate.now();

        return instituicao.registarDecisaoCandidatura(this.candidaturaSelecionada, aceite, justificacao, dataDecisao);
    }

    public String getDadosCandidatura() {
        if (candidaturaSelecionada != null) return candidaturaSelecionada.toString();
        return "";
    }
}
