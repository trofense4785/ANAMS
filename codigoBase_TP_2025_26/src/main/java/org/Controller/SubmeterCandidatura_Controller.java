package org.Controller;

import org.Model.Candidatura;
import org.Model.Instituicao;

import java.time.LocalDate;

public class SubmeterCandidatura_Controller {

    private Instituicao instituicao;
    private Candidatura candidatura; // Guarda o objeto em memória

    public SubmeterCandidatura_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    /**
     * Passo 1 & 2: Recebe dados e cria o objeto (Creator).
     * Substitui a sequência "novaCandidatura() -> setDados()" por uma chamada única mais segura.
     */
    public void novaCandidatura(String nome, LocalDate dataNasc, String habilitacoes, String email, String cc, String genero) {
        this.candidatura = instituicao.novaCandidatura(nome, dataNasc, habilitacoes, email, cc, genero);

        // Validação imediata
        if (!instituicao.validaCandidatura(this.candidatura)) {
            this.candidatura = null;
        }
    }

    /**
     * Passo 3: Confirmação e Registo.
     */
    public boolean registarCandidatura() {
        if (this.candidatura != null) {
            return instituicao.registarCandidatura(this.candidatura);
        }
        return false;
    }

    /**
     * Auxiliar para UI mostrar resumo antes de confirmar.
     */
    public String getDadosCandidatura() {
        if (this.candidatura != null) {
            return this.candidatura.toString();
        }
        return "";
    }
}