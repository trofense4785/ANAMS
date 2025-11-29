package org.Controller;

import org.Model.*;

import java.util.ArrayList;
import java.util.List;

public class ConsultarListaAlunosCurso_Controller {

    private Instituicao instituicao;
    private Formador formadorLogado;
    private Curso cursoSelecionado;

    public ConsultarListaAlunosCurso_Controller() {
        this.instituicao = Instituicao.getInstance();


        String email = Sessao.getInstance().getEmailUsuarioLogado();
        this.formadorLogado = instituicao.getFormadorPorEmail(email);

        if (this.formadorLogado == null) {
            throw new IllegalStateException("Erro: Nenhum formador autenticado.");
        }
    }

    public List<String> getMeusCursos() {

        List<Curso> cursos = instituicao.getCursosDoFormador(this.formadorLogado);

        List<String> resultado = new ArrayList<>();
        for (Curso c : cursos) {
            resultado.add(c.toString());
        }
        return resultado;
    }


    public void selecionarCurso(String sigla) {
        this.cursoSelecionado = instituicao.getCurso(sigla);


        List<Curso> permitidos = instituicao.getCursosDoFormador(this.formadorLogado);
        if (!permitidos.contains(this.cursoSelecionado)) {
            this.cursoSelecionado = null;
            throw new SecurityException("Não tem permissão para consultar este curso.");
        }
    }


    public List<String> getListaAlunos() {
        if (this.cursoSelecionado == null) return new ArrayList<>();


        List<Aluno> alunos = instituicao.getAlunosDoCurso(this.cursoSelecionado);

        List<String> resultado = new ArrayList<>();
        for (Aluno a : alunos) {
            resultado.add(a.toString());
        }

        if (resultado.isEmpty()) {
            resultado.add("Não existem alunos inscritos neste curso.");
        }

        return resultado;
    }
}
