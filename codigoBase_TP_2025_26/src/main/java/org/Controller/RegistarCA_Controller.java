package org.Controller;

import org.Model.CA;
import org.Model.Credenciais;
import org.Model.Instituicao;

public class RegistarCA_Controller {
    private final Instituicao instituicao;
    private CA ca;

    public RegistarCA_Controller(Instituicao instituicao) {
        this.instituicao = Instituicao.getInstance();
    }

    public void novoCA(String nome, String email, String cc, String sigla, String contacto) {
        if (instituicao.getCA() != null) {
            throw new IllegalStateException("Operação impossível: O Coordenador Académico já está definido.");
        }

        this.ca = instituicao.novoCA(nome, email, cc, sigla, contacto);
    }

    public boolean registarCA() {
        if (this.instituicao.registarCA(this.ca)) {
            System.out.println("Coordenador Académico registado com sucesso.");
            return true;
        }
        return false;
    }

    public String getDadosCA() {
        if (this.ca != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("--- Dados do Coordenador Académico ---\n");
            sb.append("Nome: ").append(ca.getNome()).append("\n");
            sb.append("Sigla: ").append(ca.getSigla()).append("\n");
            sb.append("Email: ").append(ca.getEmail()).append("\n");
            sb.append("CC: ").append(ca.getCc()).append("\n");
            sb.append("Contacto: ").append(ca.getContacto());
            return sb.toString();
        }
        return "Nenhum CA em memória.";
    }


}