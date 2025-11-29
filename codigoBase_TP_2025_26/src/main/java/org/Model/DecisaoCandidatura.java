package org.Model;

import java.time.LocalDate;

public class DecisaoCandidatura {
    private boolean aceite;
    private String textoExplicativo;
    private LocalDate dataDecisao;

    public DecisaoCandidatura(boolean aceite, String textoExplicativo) {
        this.aceite = aceite;
        this.textoExplicativo = textoExplicativo;
        this.dataDecisao = LocalDate.now();
    }


    public boolean isAceite() { return aceite; }
    public String getTextoExplicativo() { return textoExplicativo; }
    public LocalDate getDataDecisao() { return dataDecisao; }

}
