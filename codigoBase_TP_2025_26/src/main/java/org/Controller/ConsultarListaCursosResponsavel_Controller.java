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

    /**
     * Passo 1.1: getMeusCursos()
     */
    public List<String> getMeusCursos() {
        // 1. Autenticação (Resolver o email mágico)
        String email = Sessao.getInstance().getEmailUsuarioLogado();

        // 2. Obter o objeto Formador
        this.formadorLogado = instituicao.getFormadorPorEmail(email);

        if (this.formadorLogado == null) {
            return new ArrayList<>(); // Ou lançar erro
        }

        // 3. Obter a lista filtrada
        List<Curso> listaCursos = instituicao.getCursosDoFormador(this.formadorLogado);

        // 4. Converter para String para a UI (Passo não explícito no diagrama, mas necessário na prática)
        List<String> listaFormatada = new ArrayList<>();
        for (Curso c : listaCursos) {
            listaFormatada.add(c.toString());
        }

        return listaFormatada;
    }

    // Se quiseres selecionar um curso para ver detalhes (Passo 2 do diagrama)
    public String getDetalhesCurso(String sigla) {
        // Reutiliza o método de busca da instituição
        // ...
        return "";
    }
}