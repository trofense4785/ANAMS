/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Controller;

import org.Model.Instituicao;
import org.Model.TipoCurso;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class EspecificarTipoCurso_Controller
{
    private final Instituicao instituicao;
    private TipoCurso tipoCurso;
    
    public EspecificarTipoCurso_Controller(Instituicao instituicao)
    {
        this.instituicao = Instituicao.getInstance();
    }
    public void novoTipoCurso(String sigla, String descricao)
    {
        this.tipoCurso = instituicao.novoTipoCurso(sigla, descricao);
        instituicao.validaTipoCurso(this.tipoCurso);
    }


    public boolean registarTipoCurso() {
        // Valida unicidade e guarda
        return this.instituicao.registarTipoCurso(this.tipoCurso);
    }

    // Auxiliar para a UI mostrar o que vai ser gravado
    public String getTipoCursoString() {
        if (this.tipoCurso != null) {
            return this.tipoCurso.toString();
        }
        return "";
    }
}
