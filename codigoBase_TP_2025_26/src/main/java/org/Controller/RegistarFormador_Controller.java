package org.Controller;

import org.Model.Instituicao;
import org.Model.Formador;
import org.Model.Credenciais;

import java.time.LocalDate;

public class RegistarFormador_Controller {
    private final Instituicao instituicao;
    private Formador formador; // Entidade em estado

    public RegistarFormador_Controller(Instituicao instituicao) {
        this.instituicao = Instituicao.getInstance();
    }

    public void novoFormador(String nome, LocalDate dataNascimento, String cc, String email, String contacto, String areaFormacao) {
        this.formador = instituicao.novoFormador(nome, dataNascimento, cc, email, contacto, areaFormacao);

        // Opcional: Validar logo aqui para dar feedback imediato de duplicados
        // instituicao.validaFormador(this.formador);
    }


    public boolean registarFormador() {
        if (this.formador != null) {
            return instituicao.registarFormador(this.formador);
        }
        return false;
    }

    public String getDadosFormador() {
        if (this.formador != null) {
            return this.formador.toString();
        }
        return "";
    }
}