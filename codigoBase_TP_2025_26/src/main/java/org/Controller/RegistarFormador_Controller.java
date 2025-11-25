package org.Controller;

import org.Model.Instituicao;
import org.Model.Formador;
import org.Model.Credenciais;

import java.time.LocalDate;

public class RegistarFormador_Controller {
    private final Instituicao instituicao;
    private Formador formador; // Entidade em estado

    public RegistarFormador_Controller(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public void novoFormador() {
        // A Instituicao deve ter um método para instanciar o objeto Formador
        // que, por sua vez, atribui automaticamente o idFormador.
        this.formador = instituicao.novoFormador();
    }

    public void setDados(String nome, LocalDate dataNascimento, String numeroCC,
                         String email, String contacto, String areaEspecializacao) {

        this.formador.setNome(nome);
        this.formador.setDataNascimento(dataNascimento);
        this.formador.setNumeroCC(numeroCC);
        this.formador.setEmail(email);
        this.formador.setContacto(contacto);
        this.formador.setAreaEspecializacao(areaEspecializacao);
    }

    public boolean registarFormador() {
        // Gerar e associar credenciais (lógica de negócio)
        String login = this.formador.getEmail();
        String password = gerarPasswordAleatoria();
        Credenciais credenciais = new Credenciais(login, password);
        this.formador.setCredenciais(credenciais);

        // Validação e Adição à Instituicao
        if (this.instituicao.adicionarFormador(this.formador)) {
            System.out.println("✅ Formador " + formador.getNome() + " registado com sucesso.");
            return true;
        } else {
            System.out.println("❌ Falha no registo: Dados inválidos.");
            return false;
        }
    }

    public Formador getFormadorEmRegisto() { return this.formador; }

    private String gerarPasswordAleatoria() {
        return "pass_" + (int)(Math.random() * 10000);
    }
}