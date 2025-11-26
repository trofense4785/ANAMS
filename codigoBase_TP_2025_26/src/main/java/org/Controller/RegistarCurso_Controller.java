package org.Controller;

import org.Model.Curso;
import org.Model.Formador;
import org.Model.Instituicao;
import org.Model.TipoCurso;

import java.time.LocalDate;
import java.util.List;

public class RegistarCurso_Controller {
    private final Instituicao instituicao;
    private Curso curso; // Entidade em estado

    /**
     * Construtor do Controller.
     * @param instituicao A instância do repositório principal do sistema.
     */
    public RegistarCurso_Controller(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    /**
     * Inicia o processo de registo, instanciando um novo Curso vazio.
     */
    public void novoCurso() {
        this.curso = instituicao.novoCurso();
    }

    /**
     * Define os dados principais do Curso.
     * @param titulo O título do curso.
     * @param sigla A sigla única do curso.
     * @param tipo O TipoCurso selecionado (ex: Técnico).
     * @param descricao A descrição do curso.
     * @param dataInicio A data de início.
     * @param dataTermino A data de término.
     */
    public void setDados(String titulo, String sigla, TipoCurso tipo, String descricao,
                         LocalDate dataInicio, LocalDate dataTermino) {

        this.curso.setTitulo(titulo);
        this.curso.setSigla(sigla);
        this.curso.setTipo(tipo);
        this.curso.setDescricao(descricao);
        this.curso.setDataInicio(dataInicio);
        this.curso.setDataTermino(dataTermino);
    }

    /**
     * Adiciona um Formador como responsável pelo Curso. Pode ser chamado múltiplas vezes.
     * @param formador O Formador a ser adicionado.
     */
    public void adicionarFormadorResponsavel(Formador formador) {
        if (this.curso != null) {
            this.curso.adicionarFormadorResponsavel(formador);
        }
    }

    /**
     * Tenta registar o Curso na Instituição, delegando toda a validação.
     * A Instituicao fará as seguintes verificações:
     * 1. Campos obrigatórios preenchidos.
     * 2. Sigla do curso é única.
     * 3. Data de término é posterior à data de início.
     * 4. Pelo menos um Formador responsável foi adicionado.
     * * @return true se o registo for bem-sucedido, false caso contrário.
     */
    public boolean registarCurso() {
        // Validação e Adição são feitas dentro do método adicionarCurso() da Instituicao
        return this.instituicao.adicionarCurso(this.curso);
    }

    /**
     * Retorna a lista de TiposCurso disponíveis para seleção.
     * @return Lista de TiposCurso.
     */
    public List<TipoCurso> getTiposCurso() {
        return this.instituicao.getTiposCurso();
    }

    /**
     * Retorna a lista de Formadores disponíveis para seleção como responsáveis.
     * @return Lista de Formadores.
     */
    public List<Formador> getFormadores() {
        return this.instituicao.getFormadores();
    }

    /**
     * Retorna o Curso em estado (útil para debug ou UI).
     * @return O objeto Curso a ser registado.
     */
    public Curso getCursoEmRegisto() {
        return this.curso;
    }
}
}