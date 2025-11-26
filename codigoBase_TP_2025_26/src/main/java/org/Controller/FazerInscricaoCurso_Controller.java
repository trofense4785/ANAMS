package org.Controller;

import java.util.List;
import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Inscricao;
import org.Model.Instituicao;

public class FazerInscricaoCurso_Controller {

    private Instituicao instituicao;
    private Aluno alunoLogado; // Assumindo que sabemos quem está logado

    public FazerInscricaoCurso_Controller(String emailAluno) {
        this.instituicao = Instituicao.getInstance();
        this.alunoLogado = instituicao.getAlunoPorEmail(emailAluno);
    }

    public List<Curso> getCursosDisponiveis() {
        return instituicao.getCursosDisponiveisParaInscricao();
    }

    public boolean inscreverNoCurso(Curso curso) {
        if (alunoLogado == null || curso == null) return false;

        // Cria inscrição (O construtor trata do ID, Data e Estado "Ativa")
        Inscricao novaInscricao = new Inscricao(alunoLogado, curso);

        return instituicao.registarInscricao(novaInscricao);
    }
}