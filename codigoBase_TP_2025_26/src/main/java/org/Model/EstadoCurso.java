package org.Model;

public class EstadoCurso {
    private int codigo;
    private String descricao;

    // --- 1. DEFINIÇÃO DAS CONSTANTES (O "Truque" para funcionar) ---
    public static final EstadoCurso A_INICIAR = new EstadoCurso(0, "A iniciar");
    public static final EstadoCurso ATIVO = new EstadoCurso(1, "Ativo");
    public static final EstadoCurso SUSPENSO = new EstadoCurso(2, "Suspenso");
    public static final EstadoCurso CANCELADO = new EstadoCurso(3, "Cancelado");
    public static final EstadoCurso CONCLUIDO = new EstadoCurso(4, "Concluído");

    // --- 2. O TEU CONSTRUTOR ---
    public EstadoCurso(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() { return codigo; }
    public String getDescricao() { return descricao; }

    public static EstadoCurso[] values() {
        return new EstadoCurso[] {
                A_INICIAR,
                ATIVO,
                SUSPENSO,
                CANCELADO,
                CONCLUIDO
        };
    }

    @Override
    public String toString() {
        return descricao;
    }

    // --- 3. EQUALS (Essencial para as comparações funcionarem) ---
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        EstadoCurso other = (EstadoCurso) obj;
        return codigo == other.codigo;
    }
}