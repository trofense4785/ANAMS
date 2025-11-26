package org.Controller;

import java.util.List;
import org.Model.Aluno;
import org.Model.Inscricao;
import org.Model.Instituicao;

public class AnularInscricao_Controller {

    private Instituicao instituicao;
    private Aluno alunoLogado;

    public AnularInscricao_Controller(String emailAluno) {
        this.instituicao = Instituicao.getInstance();
        this.alunoLogado = instituicao.getAlunoPorEmail(emailAluno);
    }

    public List<Inscricao> getInscricoesAtivas() {
        return instituicao.getInscricoesAtivasDoAluno(alunoLogado);
    }

    public boolean anularInscricao(Inscricao inscricao) {
        if (inscricao != null) {
            // Requisito IT2: Alterar estado para cancelada (1)
            inscricao.setEstado(EstadoInscricao.CANCELADA);
            return true;
        }
        return false;
    }
}