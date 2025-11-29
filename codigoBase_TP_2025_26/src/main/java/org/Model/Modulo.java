package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Modulo implements Calculavel {
    private String codigo;
    private String titulo;
    private double cargaHoraria;
    private LocalDate dataInicio;
    private LocalDate dataConclusao;
    private Formador formadorResponsavel;
    private double ponderacao;

    private List<SessaoModulo> lstSessoes;
    private List<Classificacao> lstClassificacoes;
    private static int contador = 1;

    public Modulo(String codigo, String titulo, double cargaHoraria, LocalDate dataInicio, LocalDate dataConclusao, Formador formadorResponsavel, double ponderacao, List<SessaoModulo>lstSessoes, List<Classificacao> lstClassificacoes) {
        this.codigo = gerarCodigo();
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.dataInicio = dataInicio;
        this.dataConclusao = dataConclusao;
        this.formadorResponsavel = formadorResponsavel;
        setPonderacao(ponderacao);
        this.lstSessoes = (lstSessoes != null) ? lstSessoes : new ArrayList<>();
        this.lstClassificacoes = new ArrayList<>();
    }

    private String gerarCodigo() {
        return "MOD" + System.currentTimeMillis();
    }

    public boolean adicionarSessao(SessaoModulo sessao) {

        LocalDate dataSessao = sessao.getDataHoraInicio().toLocalDate();


        if (dataSessao.isBefore(this.dataInicio)) {
            throw new IllegalArgumentException("A data da sessão (" + dataSessao + ") é anterior ao início do módulo.");
        }

        if (dataSessao.isAfter(this.dataConclusao)) {
            throw new IllegalArgumentException("A data da sessão (" + dataSessao + ") é posterior ao fim do módulo.");
        }



        return this.lstSessoes.add(sessao);
    }


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


    @Override
    public double calcularNota() {

        if (lstClassificacoes.isEmpty()) return 0.0;

        double soma = 0;
        for (Classificacao c : lstClassificacoes) {
            soma += c.getNota();
        }
        return soma / lstClassificacoes.size();
    }

    public Double getNotaAluno(Aluno aluno) {
        for (Classificacao c : lstClassificacoes) {
            if (c.getAluno().equals(aluno)) {
                return c.getNota();
            }
        }
        return null;
    }

    public void lancarClassificacao(Aluno aluno, double nota) {

        for (Classificacao c : lstClassificacoes) {
            if (c.getAluno().equals(aluno)) {

                c.setNota(nota);
                return;
            }
        }


        Classificacao novaClassificacao = new Classificacao(aluno, this, nota);

        lstClassificacoes.add(novaClassificacao);
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