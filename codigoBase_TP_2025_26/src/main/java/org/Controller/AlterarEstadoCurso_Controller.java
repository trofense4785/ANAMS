package org.Controller;

import org.Model.Curso;
import org.Model.EstadoCurso;
import org.Model.Instituicao;

import java.util.List;

public class AlterarEstadoCurso_Controller {

    private Instituicao instituicao;
    private Curso cursoSelecionado;

    public AlterarEstadoCurso_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    /**
     * Passo 1.1: obterListaCursos()
     */
    public List<Curso> obterListaCursos() {
        // Passo 1.1.1: Controller pede à Instituição
        return instituicao.getLstCursos();
    }

    /**
     * Passo 2.1 / 3: Selecionar Curso e obter estado atual
     * No diagrama, o passo 3 (getCurso) está misturado aqui.
     */
    public EstadoCurso selecionarCurso(String sigla) {
        // Passo 3: cur = getCurso(sigla)
        this.cursoSelecionado = instituicao.getCurso(sigla);

        if (this.cursoSelecionado != null) {
            // Passo 2.1.1: obterEstadoCurso()
            return this.cursoSelecionado.getEstado();
        }
        return null;
    }

    /**
     * Passo 4.1: alterarEstado(estado)
     */
    public void alterarEstado(EstadoCurso novoEstado) {
        if (this.cursoSelecionado != null) {
            // Passo 4.1.1: alterarEstado(cur, estado) -> setEstado no Objeto
            // Nota: O diagrama mostra o Controller a chamar o setEstado
            this.cursoSelecionado.setEstado(novoEstado);
        }
    }

    /**
     * Passo 4.2: obterDadosCurso()
     * Usado para mostrar a informação antes da confirmação final.
     */
    public String obterDadosCurso() {
        if (this.cursoSelecionado != null) {
            // Passo 4.2.1: toString() implícito ou getters
            return this.cursoSelecionado.toString();
        }
        return "";
    }

    // Método auxiliar para a UI preencher a ComboBox de estados
    public EstadoCurso[] getEstadosPossiveis() {
        return EstadoCurso.values();
    }
}