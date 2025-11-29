package org.Model;

import java.time.LocalDate;

public class Formador {
    private String idFormador; // Gerado automaticamente
    private String nome;
    private LocalDate dataNascimento;
    private String cc;
    private String email;
    private String contacto;
    private String areaFormacao; // Licenciatura, Mestrado, etc.
    private Credenciais credenciais;

    public Formador(String nome, LocalDate dataNascimento, String cc, String email, String contacto, String areaFormacao) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cc = cc;
        this.email = email;
        this.contacto = contacto;
        this.areaFormacao = areaFormacao;
        // O ID e Credenciais são definidos depois pela Instituição
    }

    public boolean valida() {
        // Requisito g): Todos os dados são obrigatórios [cite: 24]
        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome é obrigatório.");
        if (cc == null || cc.length() != 9) throw new IllegalArgumentException("CC inválido (deve ter 9 dígitos).");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Email inválido.");
        if (dataNascimento == null) throw new IllegalArgumentException("Data de nascimento obrigatória.");

        // Validar idade mínima (ex: 18 anos) - Opcional mas boa prática
        if (dataNascimento.plusYears(18).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("O formador deve ser maior de idade.");
        }
        return true;
    }

    // Setters especiais (acessíveis pela Instituição)
    public void setIdFormador(String idFormador) {
        this.idFormador = idFormador;
    }

    public void setCredenciais(Credenciais credenciais) {
        this.credenciais = credenciais;
    }

    public Credenciais getCredenciais() {
        return credenciais;
    }

    // Getters
    public String getCc() {
        return cc;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getIdFormador() {
        return idFormador;
    }

    public String getContacto() {
        return contacto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Formador:\n");
        // O ID pode ser null se ainda não tiver sido registado pela Instituição
        sb.append("ID: ").append(idFormador != null ? idFormador : "(Gerado após registo)").append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Data Nasc.: ").append(dataNascimento).append("\n");
        sb.append("CC: ").append(cc).append("\n");
        sb.append("Email: ").append(email).append("\n");
        sb.append("Contacto: ").append(contacto).append("\n");
        sb.append("Área Formação: ").append(areaFormacao);

        return sb.toString();
    }
}
