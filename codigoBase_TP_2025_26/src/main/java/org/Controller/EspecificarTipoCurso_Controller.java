/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Controller;

import org.Model.Empresa;
import org.Model.TipoCurso;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class EspecificarTipoCurso_Controller
{
    private final Empresa empresa;
    private TipoCurso tipoCurso;
    
    public EspecificarTipoCurso_Controller(Empresa empresa)
    {
        this.empresa= empresa;
    }
    public void novoTipoCurso()
    {
        this.tipoCurso = empresa.novoTipoCurso();
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
        return this.empresa.especificarTipoCurso(this.tipoCurso);
    }

    public String getTipoCursoAsString()
    {
        return this.tipoCurso.toString();
    }
}
