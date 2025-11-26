/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Model;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class TipoCurso
{  
    private String sigla;
    private String descricao;

    private static final String STRING_POR_OMISSAO = "a definir";

    public TipoCurso()
    {
        this.setSigla(STRING_POR_OMISSAO);
        this.setDescricao(STRING_POR_OMISSAO);
    }
    public TipoCurso(String sigla, String descricao)
    {
        this.setSigla(sigla);
        this.setDescricao(descricao);
    }
    public String getSigla()
    {
        return sigla;
    }
    public void setSigla(String sigla)
    {
        this.sigla = sigla;
    }
    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    // Validação local
    public boolean valida() {
        if (sigla == null || sigla.trim().isEmpty()) {
            throw new IllegalArgumentException("A sigla do tipo de curso é obrigatória.");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição é obrigatória.");
        }
        return true;
    }


    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Sigla: "+ sigla +"\n");
        sb.append("Descrição: "+ descricao +"\n");
        return sb.toString();
    }
}
