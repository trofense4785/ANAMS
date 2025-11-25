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
public class Instituicao
{
    private final List<TipoCurso> lstTiposCurso;
    private CA ca;

    // Completar
    public Instituicao()
    {
        this.lstTiposCurso = new ArrayList<>();
        this.ca = null;
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
    public boolean valida(TipoCurso tipoCurso) {
        boolean resp = false;

        // 1. Validação Local
        if (tipoCurso.valida()) {

            // 2. Validação Global (Unicidade da Sigla)
            boolean siglaUnica = true;
            for (TipoCurso tc : lstTiposCurso) {
                if (tc.getSigla().equalsIgnoreCase(tipoCurso.getSigla())) {
                    siglaUnica = false;
                    break;
                }
            }

            // Se a validação local passou E a sigla é única
            if (siglaUnica) {
                resp = true;
            }
        }
        return resp;
    }

    public Formador novoFormador() {
        // Retorna uma nova instância (requer construtor vazio na classe Formador)
        return new Formador();
    }

    public CA novoCA() {
        return new CA();
    }

    public CA getCA() {
        return this.ca;
    }


    public boolean adicionarCA(CA ca) {
        // 1. VALIDAÇÃO PRINCIPAL: Checar se o CA já foi registado
        if (this.ca != null) {
            System.out.println("❌ Falha na validação: Já existe um Coordenador Académico registado no sistema.");
            return false;
        }

        // 2. Validação secundária (unicidade de sigla/CC, que já faríamos)
        // (A validação de unicidade de CC/Sigla pode ser ignorada aqui, pois é o primeiro e único)

        // 3. Atribuir o objeto CA
        this.ca = ca;
        return true;
    }

    public Curso novoCurso() {
        return new Curso();
    }

    public Candidato novoCandidato() {
        return new Candidato();
    }
   
    // Completar com outras funcionalidades
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Instituição: \n");
        sb.append("Lista de tipos de cursos: "+ lstTiposCurso.toString()+"\n");
        return sb.toString();
    }


}
    
    
