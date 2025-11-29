package org.Controller;

import org.Model.Curso;
import org.Model.Formador;
import org.Model.Instituicao;
import org.Model.Sessao;

import java.util.ArrayList;
import java.util.List;

public class ConsultarListaCursosResponsavel_Controller {

    private Instituicao instituicao;
    private Formador formadorLogado;

    public ConsultarListaCursosResponsavel_Controller() {
        this.instituicao = Instituicao.getInstance();
    }


    public List<String> getMeusCursos() {

        String email = Sessao.getInstance().getEmailUsuarioLogado();


        this.formadorLogado = instituicao.getFormadorPorEmail(email);

        if (this.formadorLogado == null) {
            return new ArrayList<>();
        }


        List<Curso> listaCursos = instituicao.getCursosDoFormador(this.formadorLogado);


        List<String> listaFormatada = new ArrayList<>();
        for (Curso c : listaCursos) {
            listaFormatada.add(c.toString());
        }

        return listaFormatada;
    }

    public String getDadosCurso(String sigla) {

        Curso c = instituicao.getCurso(sigla);

        if (c != null) {
            return c.toString();
        }
        return "Curso não encontrado.";
    }


    public String getDetalhesCurso(String sigla) {

        return "";
    }
}