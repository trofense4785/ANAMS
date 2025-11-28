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

        // 1. Autenticação (Requisito h - [cite: 25])
        String email = Sessao.getInstance().getEmailUsuarioLogado();
        this.formadorLogado = instituicao.getFormadorByEmail(email);

        if (this.formadorLogado == null) {
            throw new IllegalStateException("Erro: Nenhum formador autenticado.");
        }
    }

    /**
     * Passo 1: Obter a lista de cursos onde o formador leciona.
     * (Reutiliza a lógica do UC11 para garantir que ele só vê os seus cursos)
     */
    public List<String> getMeusCursos() {
        //  - Deve incluir cursos onde é responsável ou dá módulos
        List<Curso> cursos = instituicao.getCursosDoFormador(this.formadorLogado);

        List<String> resultado = new ArrayList<>();
        for (Curso c : cursos) {
            resultado.add(c.toString());
        }
        return resultado;
    }

    /**
     * Passo 2: Selecionar o curso.
     */
    public void selecionarCurso(String sigla) {
        this.cursoSelecionado = instituicao.getCurso(sigla);

        // Validação de Segurança Extra:
        // Garantir que o formador tem mesmo permissão para ver este curso
        // (Caso a UI envie uma sigla maliciosa)
        List<Curso> permitidos = instituicao.getCursosDoFormador(this.formadorLogado);
        if (!permitidos.contains(this.cursoSelecionado)) {
            this.cursoSelecionado = null;
            throw new SecurityException("Não tem permissão para consultar este curso.");
        }
    }

    /**
     * Passo 3: Obter a lista de alunos desse curso.
     */
    public List<String> getListaAlunos() {
        if (this.cursoSelecionado == null) return new ArrayList<>();

        // Pede à instituição os alunos relacionados com o curso selecionado
        List<Aluno> alunos = instituicao.getAlunosDoCurso(this.cursoSelecionado);

        List<String> resultado = new ArrayList<>();
        for (Aluno a : alunos) {
            // Formatar a saída (Nome, Email e talvez Estado da Inscrição)
            resultado.add(a.toString());
        }

        if (resultado.isEmpty()) {
            resultado.add("Não existem alunos inscritos neste curso.");
        }

        return resultado;
    }
}
