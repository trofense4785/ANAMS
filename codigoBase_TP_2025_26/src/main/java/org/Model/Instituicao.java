/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class Instituicao
{
    private static Instituicao instance;
    private List<TipoCurso> lstTiposCurso;
    private CA ca; // Apenas um CA
    private List<Formador> lstFormadores;
    private List<Curso> lstCursos;
    private List<Candidato> lstCandidatos;

    // Completar
    public Instituicao()
    {
        this.lstTiposCurso = new ArrayList<>();
        this.ca = null;
        this.lstCursos = new ArrayList<>();
        this.lstFormadores = new ArrayList<>();
        this.lstCandidatos = new ArrayList<>();
    }

    public static Instituicao getInstance() {
        if (instance == null) {
            instance = new Instituicao();
        }
        return instance;
    }

// TIPO CURSO

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

    public List<TipoCurso> getLstTiposCurso() {
        return lstTiposCurso;
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

// CURSO

    public Curso novoCurso(String titulo, String sigla, TipoCurso tipo, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
        return new Curso(titulo, sigla, tipo, descricao, dataInicio, dataTermino);
    }

    public boolean validaCurso(Curso curso) {
        // Validação de Unicidade (Sigla)
        for (Curso c : lstCursos) {
            if (c.getSigla().equalsIgnoreCase(curso.getSigla())) return false;
        }
        return true;
    }

    public boolean registarCurso(Curso curso) {
        if (validaCurso(curso) && curso.validarEstado()) {
            return lstCursos.add(curso);
        }
        return false;
    }

    public List<Formador> getLstFormadores() {
        return lstFormadores;
    }

// CA

    public CA getCA() {
        return this.ca;
    }

    public CA novoCA(String nome, String email, String cc, String sigla, String contacto) {
        return new CA(nome, email, cc, sigla, contacto);
    }
    public boolean validaCA(CA novoCA) {
        if (novoCA == null) return false;

        // Se já existir um CA atribuído, não deixa criar outro.
        if (this.ca != null) {
            throw new IllegalStateException("Erro: Já existe um Coordenador Académico registado.");
        }

        // Aqui podes adicionar outras validações (ex: verificar se o email já é usado por um Formador)
        return true;
    }
    public boolean registarCA(CA novoCA) {
        if (validaCA(novoCA)) {
            // Gerar credenciais
            String username = "ca1";
            String password = "cone1234";

            Credenciais credenciais = new Credenciais(username, password);
            novoCA.setCredenciais(credenciais);


            // Gravar na variável única
            this.ca = novoCA;
            return true;
        }
        return false;
    }



    // Completar com outras funcionalidades
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Instituição: \n");
        sb.append("Lista de tipos de cursos: "+ lstTiposCurso.toString()+"\n");
        return sb.toString();
    }


}
    
    
