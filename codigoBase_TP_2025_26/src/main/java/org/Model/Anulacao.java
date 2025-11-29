package org.Model;

import java.time.LocalDate;

public class Anulacao {
    private LocalDate data;
    private Inscricao inscricao;

    public Anulacao(Inscricao inscricao) {
        this.inscricao = inscricao;
        this.data = LocalDate.now();
    }

    public boolean valida() {
        return inscricao != null;
    }
}
