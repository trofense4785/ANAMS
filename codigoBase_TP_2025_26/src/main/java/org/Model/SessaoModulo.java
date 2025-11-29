package org.Model;

import java.time.LocalDateTime;

public class SessaoModulo {

    private LocalDateTime dataHoraInicio;
    private int duracaoHoras;
    private String sala;

    public SessaoModulo(LocalDateTime dataHoraInicio, int duracaoHoras, String sala) {

        if (dataHoraInicio == null) {
            throw new IllegalArgumentException("A data e hora de início são obrigatórias.");
        }
        if (duracaoHoras <= 0) {
            throw new IllegalArgumentException("A duração da sessão deve ser superior a 0 horas.");
        }
        if (sala == null || sala.trim().isEmpty()) {
            throw new IllegalArgumentException("A sala deve ser especificada.");
        }

        this.dataHoraInicio = dataHoraInicio;
        this.duracaoHoras = duracaoHoras;
        this.sala = sala;
    }



    public LocalDateTime getDataHoraInicio() {
        return dataHoraInicio;
    }


    public LocalDateTime getDataHoraFim() {
        return dataHoraInicio.plusHours(duracaoHoras);
    }

    public int getDuracaoHoras() {
        return duracaoHoras;
    }

    public String getSala() {
        return sala;
    }
}