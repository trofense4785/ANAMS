package org.Controller;

import org.Model.*;

import java.util.ArrayList;
import java.util.List;

public class RegistarClassificacao_Controller {

    private Instituicao instituicao;
    private Formador formadorLogado;


    private Curso cursoSelecionado;
    private Modulo moduloSelecionado;
    private Aluno alunoSelecionado;
    private double notaTemporaria; //

    public RegistarClassificacao_Controller() {
        this.instituicao = Instituicao.getInstance();
    }



    public List<String> getMeusCursos() {

        String idSessao = Sessao.getInstance().getEmailUsuarioLogado();
        this.formadorLogado = instituicao.getFormadorPorEmail(idSessao);

        if (this.formadorLogado == null) {
            for (Formador f : instituicao.getLstFormadores()) {
                if (f.getCredenciais() != null && f.getCredenciais().getUsername().equals(idSessao)) {
                    this.formadorLogado = f;
                    break;
                }
            }
        }

        if (this.formadorLogado == null) throw new IllegalStateException("Erro: Acesso não autorizado.");


        List<Curso> cursos = instituicao.getCursosDoFormador(this.formadorLogado);

        List<String> resultado = new ArrayList<>();
        for (Curso c : cursos) resultado.add(c.toString());
        return resultado;
    }

    public void selecionarCurso(String sigla) {
        this.cursoSelecionado = instituicao.getCurso(sigla);
    }




    public List<String> getMeusModulos() {
        List<String> resultado = new ArrayList<>();

        if (this.cursoSelecionado != null) {
            for (Modulo m : this.cursoSelecionado.getListaModulos()) {

                String emailResponsavel = m.getFormadorResponsavel().getEmail();
                String emailLogado = this.formadorLogado.getEmail();

                if (emailResponsavel.equalsIgnoreCase(emailLogado)) {
                    resultado.add(m.getCodigo() + " - " + m.getTitulo());
                }
            }
        }
        return resultado;
    }

    public void selecionarModulo(String codigo) {
        for (Modulo m : this.cursoSelecionado.getListaModulos()) {
            if (m.getCodigo().equals(codigo)) {
                this.moduloSelecionado = m;
                break;
            }
        }
    }



    public List<String> getAlunosInscritos() {
        List<Aluno> alunos = instituicao.getAlunosDoCurso(this.cursoSelecionado);
        List<String> resultado = new ArrayList<>();
        for (Aluno a : alunos) resultado.add(a.toString());
        return resultado;
    }

    public void selecionarAluno(String emailAluno) {
        this.alunoSelecionado = instituicao.getAlunoPorEmail(emailAluno);
    }



    public boolean validarNota(double nota) {
        if (nota < 0 || nota > 20) {
            return false;
        }

        this.notaTemporaria = nota;
        return true;
    }

    public String getResumoConfirmacao() {
        return String.format("Confirmar lançamento?\nAluno: %s\nMódulo: %s\nNota: %.2f",
                alunoSelecionado.getNome(), moduloSelecionado.getTitulo(), notaTemporaria);
    }



    public boolean confirmarRegisto() {
        if (this.moduloSelecionado != null && this.alunoSelecionado != null) {
            this.moduloSelecionado.lancarClassificacao(this.alunoSelecionado, this.notaTemporaria);
            return true;
        }
        return false;
    }
}