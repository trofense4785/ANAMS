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

    // --- Passo 1: Seleção do Curso ---

    /**
     * Retorna apenas os cursos que ainda não iniciaram ou estão ativos,
     * pois não faz sentido adicionar módulos a cursos concluídos.
     */
    public List<Curso> getCursosDisponiveis() {
        List<Curso> cursosFiltrados = new ArrayList<>();

        for (Curso c : instituicao.getLstCursos()) {

            // CORREÇÃO: Usar o Enum em vez de números
            // Requisito 1.1.b: "Só deverá ser possível adicionar módulos a cursos que ainda não iniciaram."
            // Por isso, tecnicamente só deves verificar o A_INICIAR.

            if (c.getEstado() == EstadoCurso.A_INICIAR) {
                cursosFiltrados.add(c);
            }

            // Se quiseres manter o "Ativo" (embora vá contra o requisito 1.1.b), seria:
            // if (c.getEstado() == EstadoCurso.A_INICIAR || c.getEstado() == EstadoCurso.ATIVO)
        }

        return cursosFiltrados;
    }

    public void selecionarCurso(Curso curso) {
        this.curso = curso;
    }

    // --- Passo 2: Definição do Módulo (Sem formador ainda) ---

    public void criarModulo(String codigo, String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao, Formador formadorResponsavel, double ponderacao, List<SessaoModulo>lstSessoes, List<Classificacao>lstClassificacoes) {
        if (this.curso == null) throw new IllegalStateException("Nenhum curso selecionado.");

        // Gerar ID Sequencial baseado na lista atual deste curso
        int sequencial = this.curso.getListaModulos().size() + 1;
        codigo = "M-" + sequencial;

        // O Curso cria o módulo (Creator), mas ainda sem sessões e sem formador definitivo
        // Passamos listas vazias e null no formador para preencher nos passos seguintes
        this.modulo = this.curso.novoModulo(codigo, titulo, cargaHoraria, dataInicio, dataConclusao, null, ponderacao, lstSessoes, lstClassificacoes);
    }

    // --- Passo 3: Adicionar Sessões (Loop na UI) ---

    public void adicionarSessao(LocalDateTime inicio, int duracao, String sala) {
        if (this.modulo == null) return;

        SessaoModulo sessao = new SessaoModulo(inicio, duracao, sala);

        // Validação IT2: Verificar se a SALA está livre
        if (!instituicao.validarDisponibilidadeSala(sala, sessao)) {
            throw new IllegalArgumentException("A sala " + sala + " já está ocupada neste horário.");
        }

        this.modulo.adicionarSessao(sessao);
    }

    // --- Passo 4: Seleção do Formador ---

    public List<Formador> getLstFormadores() {
        return instituicao.getLstFormadores();
    }

    public void definirFormador(Formador formador) {
        if (this.modulo == null) return;

        // Validação IT2: Verificar se o FORMADOR está livre (não tem sobreposição)
        // Percorremos as sessões que acabámos de criar no módulo
        for (SessaoModulo sessao : this.modulo.getLstSessoes()) {
            if (!instituicao.validarDisponibilidadeFormador(formador, sessao)) {
                throw new IllegalArgumentException("O formador " + formador.getNome() + " tem sobreposição de horário.");
            }
        }

        // Se passou, associa
        this.modulo.setFormadorResponsavel(formador);
    }

    // --- Passo 5: Gravação Final ---

    public boolean adicionarModuloAoCurso() {
        if (this.curso != null && this.modulo != null) {
            // O Curso valida soma de ponderações e regras internas
            return this.curso.addModulo(this.modulo);
        }
        return false;
    }
}
