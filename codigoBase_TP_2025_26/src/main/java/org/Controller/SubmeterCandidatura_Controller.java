package org.Controller;

import org.Model.Candidatura;
import org.Model.Instituicao;

import java.time.LocalDate;

public class SubmeterCandidatura_Controller {

    private Instituicao instituicao;
    private Candidatura candidatura;

    public SubmeterCandidatura_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    public void novaCandidatura(String nome, LocalDate dataNasc, String habilitacoes, String email, String cc, String genero) {
        this.candidatura = instituicao.novaCandidatura(nome, dataNasc, habilitacoes, email, cc, genero);

        // Validação imediata
        if (!instituicao.validaCandidatura(this.candidatura)) {
            this.candidatura = null;
        }
    }

    public boolean registarCandidatura() {
        if (this.candidatura != null) {
            return instituicao.registarCandidatura(this.candidatura);
        }
        return false;
    }

    public String getDadosCandidatura() {
        if (this.candidatura != null) {
            return this.candidatura.toString();
        }
        return "";
    }
}