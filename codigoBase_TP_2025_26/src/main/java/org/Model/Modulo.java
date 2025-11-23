package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modulo implements Calculavel {
    private String codigo; // Sequencial, gerado automaticamente
    private String titulo;
    private double cargaHoraria;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    private Formador formadorResponsavel;
    private double ponderacao; // Ponderação sobre a nota final do curso (valor percentual)

    private List<SessaoModulo> sessoes; // Horário por sessão (requisito 'c')
    private Classificacao classificacao; // Associação com a classificação do aluno

    public Modulo(String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao, Formador formadorResponsavel, double ponderacao) {
        this.codigo = gerarCodigo(); // Lógica de geração de código
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.formadorResponsavel = formadorResponsavel;
        this.ponderacao = ponderacao;
        this.sessoes = new ArrayList<>();
        this.classificacao = null; // A classificação é definida mais tarde
    }

    private String gerarCodigo() {
        return "MOD" + System.currentTimeMillis();
    }

    // Adiciona uma sessão, garantindo o mínimo de 3 sessões (requisito 'c')
    public boolean adicionarSessao(SessaoModulo sessao) {
        return this.sessoes.add(sessao);
    }

    // Verificação de requisito de mínimo de sessões (pode ser feita no Controller/Service)
    public boolean temSessoesSuficientes() {
        return this.sessoes.size() >= 3;
    }

    // Implementação da interface Calculavel (a nota do módulo é a nota da Classificacao associada)
    @Override
    public double calcularNota() {
        // Se a classificação não foi registrada, retorna um valor indicando isso (e.g., -1)
        if (classificacao == null) {
            return -1.0;
        }
        return classificacao.getNota();
    }

    // Getters and Setters
    public double getPonderacao() { return ponderacao; }
    public void setPonderacao(double ponderacao) { this.ponderacao = ponderacao; }
    public Formador getFormadorResponsavel() { return formadorResponsavel; }
    public Classificacao getClassificacao() { return classificacao; }
    public void setClassificacao(Classificacao classificacao) { this.classificacao = classificacao; }
    // ...
}