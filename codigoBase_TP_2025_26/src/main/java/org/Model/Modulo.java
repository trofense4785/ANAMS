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

    private List<SessaoModulo> lstSessoes; // Horário por sessão (requisito 'c')
    private List<Classificacao> lstClassificacoes; // Associação com a classificação do aluno
    private static int contador = 1;

    public Modulo(String codigo, String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao, Formador formadorResponsavel, double ponderacao, List<SessaoModulo>lstSessoes, List<Classificacao> lstClassificacoes) {
        this.codigo = gerarCodigo(); // Lógica de geração de código
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.formadorResponsavel = formadorResponsavel;
        setPonderacao(ponderacao);
        this.lstSessoes = (lstSessoes != null) ? lstSessoes : new ArrayList<>();
        this.lstClassificacoes = new ArrayList<>(); // A classificação é definida mais tarde
    }

    private String gerarCodigo() {
        return "MOD" + System.currentTimeMillis();
    }

    // Adiciona uma sessão, garantindo o mínimo de 3 sessões (requisito 'c')
    public void adicionarSessao(SessaoModulo sessao) {
        if (sessao != null) {
            this.lstSessoes.add(sessao);
        }
    }

    // Verificação de requisito de mínimo de sessões (pode ser feita no Controller/Service)
    public boolean valida() {
        if (lstSessoes.size()<3){
            throw new IllegalStateException("O módulo deve ter pelo menos 3 sessões.");
        }
        return true;
    }

    public void adicionarClassificacao(Classificacao classificacao) {
        if (classificacao != null) {
            this.lstClassificacoes.add(classificacao);
        }
    }

    // Implementação da interface Calculavel (a nota do módulo é a nota da Classificacao associada)
    @Override
    public double calcularNota() {
        // Exemplo: Retornar a média das notas lançadas (apenas ilustrativo)
        if (lstClassificacoes.isEmpty()) return 0.0;

        double soma = 0;
        for (Classificacao c : lstClassificacoes) {
            soma += c.getNota();
        }
        return soma / lstClassificacoes.size();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public Formador getFormadorResponsavel() {
        return formadorResponsavel;
    }

    public void setFormadorResponsavel(Formador formadorResponsavel) {
        this.formadorResponsavel = formadorResponsavel;
    }

    public double getPonderacao() {
        return ponderacao;
    }

    public List<SessaoModulo> getLstSessoes() {
        return lstSessoes;
    }

    public List<Classificacao> getLstClassificacoes() {
        return lstClassificacoes;
    }

    public void setPonderacao(double ponderacao) {
        if(ponderacao < 0 || ponderacao>100) {
            throw new IllegalArgumentException("Ponderação deve ser entre 0 e 100.");
        }
        this.ponderacao = ponderacao;
    }
}