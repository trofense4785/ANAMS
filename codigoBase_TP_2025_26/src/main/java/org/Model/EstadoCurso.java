package org.Model;

public class EstadoCurso {

    // 1. Atributo de instância
    private final String descricao;

    // 2. Construtor PRIVADO (para ninguém criar novos estados fora daqui)
    private EstadoCurso(String descricao) {
        this.descricao = descricao;
    }

    // 3. Constantes Estáticas (Substituem os valores do Enum)
    public static final EstadoCurso A_INICIAR = new EstadoCurso("A iniciar");
    public static final EstadoCurso ATIVO = new EstadoCurso("Ativo");
    public static final EstadoCurso SUSPENSO = new EstadoCurso("Suspenso");
    public static final EstadoCurso CANCELADO = new EstadoCurso("Cancelado");
    public static final EstadoCurso CONCLUIDO = new EstadoCurso("Concluído");

    // 4. Método auxiliar para simular o ".values()" dos Enums
    // Isto é necessário para preencheres as ComboBoxes na UI
    public static EstadoCurso[] values() {
        return new EstadoCurso[]{ A_INICIAR, ATIVO, SUSPENSO, CANCELADO, CONCLUIDO };
    }

    // 5. Getter e toString
    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}