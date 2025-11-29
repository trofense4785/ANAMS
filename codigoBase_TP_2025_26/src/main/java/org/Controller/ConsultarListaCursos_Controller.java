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


    public List<Curso> getTodosCursos() {
        return instituicao.getCursosPorEstado(null);
    }


    public List<Curso> getLstCursos(EstadoCurso estado) {
        return instituicao.getCursosPorEstado(estado);
    }


    public String getDadosCurso(String sigla) {

        Curso curso = instituicao.getCurso(sigla);

        if (curso != null) {

            return curso.toString();
        }
        return "Curso não encontrado.";
    }

    public List<EstadoCurso> getTodosEstadosPossiveis() {
        List<EstadoCurso> estados = new ArrayList<>();

        estados.add(new EstadoCurso(0, "A iniciar"));
        estados.add(new EstadoCurso(1, "Ativo"));
        estados.add(new EstadoCurso(2, "Suspenso"));
        estados.add(new EstadoCurso(3, "Cancelado"));
        estados.add(new EstadoCurso(4, "Concluído"));

        return estados;
    }
}