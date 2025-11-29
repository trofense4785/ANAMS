package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Curso implements Calculavel {
    private String titulo;
    private String sigla;
    private TipoCurso tipo;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataTermino;
    private EstadoCurso estado;   // 0-"A iniciar", 1-"Ativo", 2-"Suspenso", 3-"Cancelado", 4-"Concluído"

    private List<Modulo> listaModulos;
    private List<Formador> formadoresResponsaveis; // Novo requisito 'a'


    public Curso(String titulo, String sigla, TipoCurso tipo, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
        this.titulo = titulo;
        this.sigla = sigla;
        this.tipo = tipo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.estado = EstadoCurso.A_INICIAR;  // Estado inicial "A iniciar"
        this.listaModulos = new ArrayList<>();
        this.formadoresResponsaveis = new ArrayList<>();
    }


    // Método para adicionar módulos (com verificação do estado - requisito 'b')
    public boolean addModulo(Modulo m) {
        // 1. Validar se o módulo cumpre as regras dele (ex: min 3 sessões) [cite: 11]
        if (!m.valida()) {
            return false;
        }

        // 2. Validar Soma de Ponderações (IT2)
        double somaAtual = listaModulos.stream().mapToDouble(Modulo::getPonderacao).sum();
        if (somaAtual + m.getPonderacao() > 100) {
            throw new IllegalArgumentException("A soma das ponderações dos módulos não pode exceder 100%.");
        }

        return listaModulos.add(m);
    }

    public Modulo novoModulo(String codigo, String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao, Formador formadorResponsavel, double ponderacao, List<SessaoModulo> lstSessoes, List<Classificacao> lstClassificacoes) {
        return new Modulo(codigo, titulo, cargaHoraria, dataInicio, dataConclusao, formadorResponsavel, ponderacao, lstSessoes, lstClassificacoes);
    }

    // Método para adicionar formador responsável
    public void addResponsavel(Formador formador) {
        if (formador != null && !formadoresResponsaveis.contains(formador)) {
            this.formadoresResponsaveis.add(formador);
        }
    }

    public boolean validarEstado() {
        return !listaModulos.isEmpty();
    }

    public boolean isResponsavel(Formador formador) {
        return formadoresResponsaveis.contains(formador);
    }

    private boolean validarTransicaoEstado(EstadoCurso novoEstado) {
        // Exemplo: Um curso cancelado ou concluído não pode voltar a ser ativo
        if (this.estado == EstadoCurso.CANCELADO || this.estado == EstadoCurso.CONCLUIDO) {
            return false;
        }
        // Exemplo: Não pode passar de "A iniciar" para "Concluído" sem passar por "Ativo"
        if (this.estado == EstadoCurso.A_INICIAR && novoEstado == EstadoCurso.CONCLUIDO) {
            return false;
        }
        return true;
    }

    public void setEstado(EstadoCurso novoEstado) {
        // Passo 4.1.1.1.1: validarEstado() (Self-call no diagrama)
        if (validarTransicaoEstado(novoEstado)) {
            this.estado = novoEstado;
        } else {
            throw new IllegalStateException("Transição de estado inválida: de " + this.estado + " para " + novoEstado);
        }
    }

    public double calcularNota(Aluno aluno) {
        double notaFinal = 0.0;

        for (Modulo m : listaModulos) {
            Double notaModulo = m.getNotaAluno(aluno);

            // Assumimos que a validação "tudoLancado" já foi feita no Aluno antes de chamar este método
            if (notaModulo != null) {
                notaFinal += notaModulo * (m.getPonderacao() / 100.0);
            }
        }
        return notaFinal;
    }

    // Implementação da interface Calculavel (calcula a nota final do curso)
    @Override
    public double calcularNota() {
        // Método genérico da interface (sem argumentos), pode lançar erro ou retornar 0
        return 0.0;
    }

    public String getSigla() {
        return sigla;
    }

    public EstadoCurso getEstado() {
        return estado;
    }

    public String getTitulo() {
        return titulo;
    }


    public List<Modulo> getListaModulos() {
        return listaModulos;
    }
    public String toString() {
        return String.format("Curso: %s (%s) | Estado Atual: %s", getTitulo(), getSigla(), estado);
    }
}




