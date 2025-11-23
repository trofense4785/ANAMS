package org.Model;

import java.time.LocalDate;
import java.time.LocalTime;

public class SessaoModulo {
    private LocalDate dataSessao;
    private double duracaoHoras;
    private String localizacaoSala;

    public SessaoModulo(LocalDate dataSessao, double duracaoHoras, String localizacaoSala) {
        this.dataSessao = dataSessao;
        this.duracaoHoras = duracaoHoras;
        this.localizacaoSala = localizacaoSala;
    }

    // Getters and Setters
    public LocalDate getDataSessao() { return dataSessao; }
    public double getDuracaoHoras() { return duracaoHoras; }
    public String getLocalizacaoSala() { return localizacaoSala; }
    // ...
}