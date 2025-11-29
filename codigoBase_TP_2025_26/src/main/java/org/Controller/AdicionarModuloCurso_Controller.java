package org.Controller;

import org.Model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdicionarModuloCurso_Controller {
    private Instituicao instituicao;
    private Curso curso;
    private Modulo modulo;


    public AdicionarModuloCurso_Controller() {
        this.instituicao = Instituicao.getInstance();
    }




    public List<Curso> getCursosDisponiveis() {
        List<Curso> cursosFiltrados = new ArrayList<>();

        for (Curso c : instituicao.getLstCursos()) {


            if (c.getEstado() == EstadoCurso.A_INICIAR) {
                cursosFiltrados.add(c);
            }

        }

        return cursosFiltrados;
    }

    public void selecionarCurso(Curso curso) {
        this.curso = curso;
    }

    public Curso getCursoSelecionado() {
        return this.curso;
    }



    public void criarModulo(String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao, double ponderacao) {

        if (this.curso == null) throw new IllegalStateException("Nenhum curso selecionado.");

        int sequencial = this.curso.getListaModulos().size() + 1;
        String codigo = "M-" + sequencial;

        List<SessaoModulo> sessoesVazias = new ArrayList<>();
        List<Classificacao> classificacoesVazias = new ArrayList<>();


        this.modulo = this.curso.novoModulo(codigo, titulo, cargaHoraria, dataInicio, dataConclusao, null, ponderacao, sessoesVazias, classificacoesVazias);
    }



    public void adicionarSessao(LocalDateTime inicio, int duracao, String sala) {
        if (this.modulo == null) return;

        SessaoModulo sessao = new SessaoModulo(inicio, duracao, sala);

        if (!instituicao.validarDisponibilidadeSala(sala, sessao)) {
            throw new IllegalArgumentException("A sala " + sala + " já está ocupada neste horário.");
        }

        this.modulo.adicionarSessao(sessao);
    }


    public List<Formador> getLstFormadores() {
        return instituicao.getLstFormadores();
    }

    public void definirFormador(Formador formador) {
        if (this.modulo == null) return;


        for (SessaoModulo sessao : this.modulo.getLstSessoes()) {
            if (!instituicao.validarDisponibilidadeFormador(formador, sessao)) {
                throw new IllegalArgumentException("O formador " + formador.getNome() + " tem sobreposição de horário.");
            }
        }

        this.modulo.setFormadorResponsavel(formador);
    }



    public boolean adicionarModuloAoCurso() {
        if (this.curso != null && this.modulo != null) {
            // O Curso valida soma de ponderações e regras internas
            return this.curso.addModulo(this.modulo);
        }
        return false;
    }
}
