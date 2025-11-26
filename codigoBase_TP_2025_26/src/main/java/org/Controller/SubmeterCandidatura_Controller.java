package org.Controller;

import org.Model.Candidatura;
import org.Model.Instituicao;

public class SubmeterCandidatura_Controller {

    private Instituicao instituicao;
    private Candidatura candidatura;

    public SubmeterCandidatura_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    public void novaCandidatura(String nome, String cc, String email, String contacto, String habilitacoes) {
        this.candidatura = new Candidatura(nome, cc, email, contacto, habilitacoes);
    }

    public boolean registarCandidatura() {
        // Na IT2, apenas registamos a candidatura, não criamos o aluno ainda
        return instituicao.registarCandidatura(this.candidatura);
    }
}