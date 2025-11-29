package org.Model;

import java.time.LocalDate;

public class Formador {
    private String idFormador;
    private String nome;
    private LocalDate dataNascimento;
    private String cc;
    private String email;
    private String contacto;
    private String areaFormacao;
    private Credenciais credenciais;

    public Formador(String nome, LocalDate dataNascimento, String cc, String email, String contacto, String areaFormacao) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cc = cc;
        this.email = email;
        this.contacto = contacto;
        this.areaFormacao = areaFormacao;

    }

    public boolean valida() {

        if (nome == null || nome.isEmpty()) throw new IllegalArgumentException("Nome é obrigatório.");
        if (cc == null || cc.length() != 9) throw new IllegalArgumentException("CC inválido (deve ter 9 dígitos).");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Email inválido.");
        if (dataNascimento == null) throw new IllegalArgumentException("Data de nascimento obrigatória.");


        if (dataNascimento.plusYears(18).isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("O formador deve ser maior de idade.");
        }
        return true;
    }


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
