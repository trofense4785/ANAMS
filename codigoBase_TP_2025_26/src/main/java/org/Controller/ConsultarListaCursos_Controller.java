package org.Controller;

import org.Model.Curso;
import org.Model.EstadoCurso;
import org.Model.Instituicao;

import java.util.ArrayList;
import java.util.List;

public class ConsultarListaCursos_Controller {

    private Instituicao instituicao;

    public ConsultarListaCursos_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    /**
     * Passo 2.1: getLstCursos(estado)
     */
    public List<Curso> getTodosCursos() {
        // Passamos null para indicar que não queremos filtrar nada
        return instituicao.getCursosPorEstado(null);
    }

    // Para ver FILTRADOS (ex: A iniciar)
    public List<Curso> getLstCursos(EstadoCurso estado) {
        return instituicao.getCursosPorEstado(estado);
    }

    /**
     * Passo 3.1: obterCurso(sigla)
     * Opcional: Caso o utilizador selecione um curso específico da lista.
     */
    public String getDadosCurso(String sigla) {
        // Passo 3.1.1 no diagrama corrigido (Instituição procura o curso)
        Curso curso = instituicao.getCurso(sigla);

        if (curso != null) {
            // Passo 3.2.1: obterDescricao() / toString()
            return curso.toString();
        }
        return "Curso não encontrado.";
    }

    public List<EstadoCurso> getTodosEstadosPossiveis() {
        List<EstadoCurso> estados = new ArrayList<>();

        // Criar manualmente as opções que o enunciado define
        estados.add(new EstadoCurso(0, "A iniciar"));
        estados.add(new EstadoCurso(1, "Ativo"));
        estados.add(new EstadoCurso(2, "Suspenso"));
        estados.add(new EstadoCurso(3, "Cancelado"));
        estados.add(new EstadoCurso(4, "Concluído"));

        return estados;
    }
}