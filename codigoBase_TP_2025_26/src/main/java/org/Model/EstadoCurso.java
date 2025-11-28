package org.Model;

public enum EstadoCurso {
    A_INICIAR("A iniciar"),
    ATIVO("Ativo"),
    SUSPENSO("Suspenso"),
    CANCELADO("Cancelado"),
    CONCLUIDO("Concluído");

    private final String descricao;

    EstadoCurso(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}