package org.Model;

import java.time.LocalDateTime;

public class SessaoModulo {
    private LocalDateTime dataHoraInicio;
    private int duracaoHoras;
    private String sala;

    public SessaoModulo(LocalDateTime dataHoraInicio, int duracaoHoras, String sala) {
        if (duracaoHoras <= 0) throw new IllegalArgumentException("Duração deve ser positiva.");
        if (sala == null || sala.isEmpty()) throw new IllegalArgumentException("Sala obrigatória.");

        this.dataHoraInicio = dataHoraInicio;
        this.duracaoHoras = duracaoHoras;
        this.sala = sala;
    }

    // Getters
    public LocalDateTime getDataHoraInicio() { return dataHoraInicio; }
    public LocalDateTime getDataHoraFim() { return dataHoraInicio.plusHours(duracaoHoras); }
    public String getSala() { return sala; }
}