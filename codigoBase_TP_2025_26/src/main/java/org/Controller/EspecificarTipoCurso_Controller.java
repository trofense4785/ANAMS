/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Controller;

import org.Model.Instituicao;
import org.Model.TipoCurso;


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
        return this.instituicao.registarTipoCurso(this.tipoCurso);
    }


    public String getTipoCursoString() {
        if (this.tipoCurso != null) {
            return this.tipoCurso.toString();
        }
        return "";
    }
}
