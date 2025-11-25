package org.Controller;

import org.Model.CA;
import org.Model.Credenciais;
import org.Model.Instituicao;

public class RegistarCA_Controller {
    private final Instituicao instituicao;
    private CA ca; // Entidade em estado

    public RegistarCA_Controller(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public void novoCA() {
        // A verificação é mais simples:
        if (this.instituicao.getCA() != null) {
            throw new IllegalStateException("ERRO: O sistema já possui um Coordenador Académico registado.");
        }
        this.ca = instituicao.novoCA();
    }

    public void setDados(String sigla, String nome, String numeroCC, String email, String contacto) {
        this.ca.setSigla(sigla);
        this.ca.setNome(nome);
        this.ca.setNumeroCC(numeroCC);
        this.ca.setEmail(email);
        this.ca.setContacto(contacto);
    }

    public boolean registarCA() {
        // Gerar e associar credenciais
        String login = this.ca.getSigla();
        String password = gerarPasswordAleatoria();
        Credenciais credenciais = new Credenciais(login, password);
        this.ca.setCredenciais(credenciais);


        // A chamada fica a mesma, mas com a lógica de fundo correta:
        if (this.instituicao.adicionarCA(this.ca)) {
            System.out.println("✅ Coordenador Académico " + ca.getNome() + " registado (ÚNICO NO SISTEMA).");
            return true;
        }
        return false;
    }

    private String gerarPasswordAleatoria() {
        return "pass_" + (int)(Math.random() * 10000);
    }
}