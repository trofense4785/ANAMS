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
        this.instituicao = instituicao;
    }
    public void novoTipoCurso()
    {
        this.tipoCurso = instituicao.novoTipoCurso();
    }
    public void setDados(String sigla, String descricao){
        this.tipoCurso.setSigla(sigla);
        this.tipoCurso.setDescricao(descricao);
    }
    
    public String getSigla()
    {
        return this.tipoCurso.getSigla();
    }
    
    public boolean especificarTipoCurso()
    {
        return this.instituicao.especificarTipoCurso(this.tipoCurso);
    }

    public String getTipoCursoAsString()
    {
        return this.tipoCurso.toString();
    }
}
