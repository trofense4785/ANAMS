package org.Controller;

import org.Model.Instituicao;
import org.Model.Formador;
import org.Model.Credenciais;

import java.time.LocalDate;

public class RegistarFormador_Controller {
    private final Instituicao instituicao;
    private Formador formador;

    public RegistarFormador_Controller(Instituicao instituicao) {
        this.instituicao = Instituicao.getInstance();
    }

    public void novoFormador(String nome, LocalDate dataNascimento, String cc, String email, String contacto, String areaFormacao) {
        this.formador = instituicao.novoFormador(nome, dataNascimento, cc, email, contacto, areaFormacao);

    }


    public boolean registarFormador() {
        if (this.formador != null) {
            return instituicao.registarFormador(this.formador);
        }
        return false;
    }

    public boolean validarCCUnico(String cc) {
        return !instituicao.existeFormadorComCC(cc);
    }

    public boolean validarEmailUnico(String email) {
        return !instituicao.existeFormadorComEmail(email);
    }

    public boolean validarContactoUnico(String contacto) {
        return !instituicao.existeFormadorComContacto(contacto);
    }

    public String getDadosFormador() {
        if (this.formador != null) {
            return this.formador.toString();
        }
        return "";
    }
}