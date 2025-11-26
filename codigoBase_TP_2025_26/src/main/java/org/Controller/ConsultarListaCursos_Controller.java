package org.Controller;

import java.util.List;
import org.Model.Curso;
import org.Model.Instituicao;

public class ConsultarListaCursos_Controller {

    private Instituicao instituicao;

    public ConsultarListaCursos_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    public List<Curso> getCursosPorEstado(String estado) {
        // Delega para a instituição a filtragem
        return instituicao.getCursosPorEstado(estado);
    }
}
