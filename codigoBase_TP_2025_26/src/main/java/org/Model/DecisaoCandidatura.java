package org.Model;

import java.time.LocalDate;

public class DecisaoCandidatura {
    private boolean aceite;
    private String textoExplicativo;
    private LocalDate dataDecisao; // Novo requisito

    public DecisaoCandidatura(boolean aceite, String textoExplicativo) {
        this.aceite = aceite;
        this.textoExplicativo = textoExplicativo;
        this.dataDecisao = LocalDate.now(); // Define a data da decisão
    }

    // Getters and Setters
    public boolean isAceite() { return aceite; }
    public String getTextoExplicativo() { return textoExplicativo; }
    public LocalDate getDataDecisao() { return dataDecisao; }
    // ...
}
