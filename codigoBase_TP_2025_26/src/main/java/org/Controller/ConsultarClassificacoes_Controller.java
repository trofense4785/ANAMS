package org.Controller;

import org.Model.Aluno;
import org.Model.Curso;
import org.Model.Instituicao;
import org.Model.Sessao;

import java.util.ArrayList;
import java.util.List;

public class ConsultarClassificacoes_Controller {

    private Instituicao instituicao;
    private Aluno alunoLogado;
    private Curso cursoSelecionado;

    public ConsultarClassificacoes_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    /**
     * Passo 1.1: getMeusCursos()
     */
    public List<String> getMeusCursos() {
        // Passo 1.1.1: getEmailUsuarioLogado()
        String email = Sessao.getInstance().getEmailUsuarioLogado();

        // Passo 1.1.2: getAlunoByEmail(email)
        this.alunoLogado = instituicao.getAlunoPorEmail(email);

        if (this.alunoLogado == null) {
            throw new IllegalStateException("Erro: Aluno não autenticado.");
        }

        // Passo 1.1.3: obterCursosInscritos()
        List<Curso> cursos = alunoLogado.obterCursosInscritos();

        // Converter para String para a UI
        List<String> resultado = new ArrayList<>();
        for (Curso c : cursos) {
            resultado.add(c.toString());
        }
        return resultado;
    }

    /**
     * Passo 2.1: consultarNotas(sigla)
     */
    public List<String> consultarNotas(String sigla) {
        // Passo 2.1.1: getCurso(sigla)
        this.cursoSelecionado = instituicao.getCurso(sigla);

        if (this.cursoSelecionado != null && this.alunoLogado != null) {
            // Passo 2.1.2: getBoletimNotas(curso)
            return this.alunoLogado.getBoletimNotas(this.cursoSelecionado);
        }

        return new ArrayList<>(); // Retorna vazio se algo falhar
    }
}