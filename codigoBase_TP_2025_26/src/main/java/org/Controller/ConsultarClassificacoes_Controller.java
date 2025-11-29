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


    public List<String> getMeusCursos() {

        String email = Sessao.getInstance().getEmailUsuarioLogado();


        this.alunoLogado = instituicao.getAlunoPorEmail(email);

        if (this.alunoLogado == null) {
            throw new IllegalStateException("Erro: Aluno não autenticado.");
        }


        List<Curso> cursos = alunoLogado.obterCursosInscritos();

        // Converter para String para a UI
        List<String> resultado = new ArrayList<>();
        for (Curso c : cursos) {
            resultado.add(c.toString());
        }
        return resultado;
    }


    public List<String> consultarNotas(String sigla) {

        this.cursoSelecionado = instituicao.getCurso(sigla);

        if (this.cursoSelecionado != null && this.alunoLogado != null) {

            return this.alunoLogado.getBoletimNotas(this.cursoSelecionado);
        }

        return new ArrayList<>();
    }
}