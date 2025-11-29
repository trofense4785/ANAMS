package org.Controller;

import org.Model.*;

import java.util.ArrayList;
import java.util.List;

public class RegistarClassificacao_Controller {

    private Instituicao instituicao;
    private Formador formadorLogado;

    // Estado da transação
    private Curso cursoSelecionado;
    private Modulo moduloSelecionado;
    private Aluno alunoSelecionado;
    private double notaTemporaria; // Guarda a nota antes de confirmar

    public RegistarClassificacao_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    // --- Passo 1: Obter Cursos do Formador ---

    public List<String> getMeusCursos() {
        // 1.1.1: Autenticação
        String email = Sessao.getInstance().getEmailUsuarioLogado();
        this.formadorLogado = instituicao.getFormadorPorEmail(email);

        if (this.formadorLogado == null) throw new IllegalStateException("Erro: Acesso não autorizado.");

        // 1.1.3: Filtrar cursos (Reutiliza UC11)
        List<Curso> cursos = instituicao.getCursosDoFormador(this.formadorLogado);

        List<String> resultado = new ArrayList<>();
        for (Curso c : cursos) resultado.add(c.toString());
        return resultado;
    }

    public void selecionarCurso(String sigla) {
        this.cursoSelecionado = instituicao.getCurso(sigla);
    }

    // --- Passo 2: Filtrar Módulos (SEGURANÇA IT2) ---

    /**
     * Passo 2.2 do Diagrama: Retorna APENAS os módulos deste formador.
     */
    public List<String> getMeusModulos() {
        List<String> resultado = new ArrayList<>();

        if (this.cursoSelecionado != null) {
            // Loop para cada módulo no curso
            for (Modulo m : this.cursoSelecionado.getListaModulos()) {

                // Caixa "OPT" do Diagrama: Verifica responsabilidade
                if (m.getFormadorResponsavel().equals(this.formadorLogado)) {
                    // Adiciona à lista de escolha apenas se for dele
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

    // --- Passo 3: Selecionar Aluno ---

    public List<String> getAlunosInscritos() {
        List<Aluno> alunos = instituicao.getAlunosDoCurso(this.cursoSelecionado);
        List<String> resultado = new ArrayList<>();
        for (Aluno a : alunos) resultado.add(a.toString());
        return resultado;
    }

    public void selecionarAluno(String emailAluno) {
        this.alunoSelecionado = instituicao.getAlunoPorEmail(emailAluno);
    }

    // --- Passo 4: Introduzir Nota e Validar (SEM GRAVAR) ---

    public boolean validarNota(double nota) {
        if (nota < 0 || nota > 20) {
            return false;
        }
        // Guarda na variável temporária, NÃO no modelo ainda
        this.notaTemporaria = nota;
        return true;
    }

    public String getResumoConfirmacao() {
        return String.format("Confirmar lançamento?\nAluno: %s\nMódulo: %s\nNota: %.2f",
                alunoSelecionado.getNome(), moduloSelecionado.getTitulo(), notaTemporaria);
    }

    // --- Passo 5: Confirmar e Gravar (Ação Real) ---

    public boolean confirmarRegisto() {
        if (this.moduloSelecionado != null && this.alunoSelecionado != null) {
            // Chama o método do Módulo que faz a lógica "update or create"
            this.moduloSelecionado.lancarClassificacao(this.alunoSelecionado, this.notaTemporaria);
            return true;
        }
        return false;
    }
}