/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class Empresa
{
    private final List<TipoCurso> lstTiposCurso;
    // Completar
    public Empresa()
    {
        this.lstTiposCurso = new ArrayList<>();
    }
  
    public TipoCurso novoTipoCurso()
    {
        return new TipoCurso();
    }
    public boolean especificarTipoCurso(TipoCurso tipoCurso)
    {
        if (this.valida(tipoCurso))
        {
           adicionarTipoCurso(tipoCurso);
           return true;
        }
        return false;
    }
        
    private void adicionarTipoCurso(TipoCurso tipoCurso){
        lstTiposCurso.add(tipoCurso);
    }
    // Validação global
    public boolean valida(TipoCurso tipoCurso)
    {
        boolean resp = false;
        if (tipoCurso.valida())
        {
           // Completar        
           //
           resp = true; 
        }
        return resp;
    }
   
    // Completar com outras funcionalidades
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Empresa: \n");
        sb.append("Lista de tipos de cursos: "+ lstTiposCurso.toString()+"\n");
        return sb.toString();
    }
}
    
    
