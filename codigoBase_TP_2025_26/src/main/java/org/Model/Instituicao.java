/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public TipoCurso novoTipoCurso(String sigla, String descricao) {
        return new TipoCurso(sigla, descricao);
    }

    // 2. Validação Global (Unicidade)
    public boolean validaTipoCurso(TipoCurso tipo) {
        if (tipo == null) return false;

        // Regra: Sigla tem de ser única
        for (TipoCurso t : lstTiposCurso) {
            if (t.getSigla().equalsIgnoreCase(tipo.getSigla())) {
                throw new IllegalArgumentException("Erro: Já existe um tipo de curso com a sigla " + tipo.getSigla());
            }
        }
        return true;
    }

    // 3. Gravação
    public boolean registarTipoCurso(TipoCurso tipo) {
        if (tipo.valida() && validaTipoCurso(tipo)) {
            return lstTiposCurso.add(tipo);
        }
        return false;
    }

    public List<TipoCurso> getLstTiposCurso() {
        return lstTiposCurso;
    }


// CURSO

    public Curso novoCurso(String titulo, String sigla, TipoCurso tipo, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
        return new Curso(titulo, sigla, tipo, descricao, dataInicio, dataTermino);
    }

    public boolean validaCurso(Curso curso) {
        if (curso == null) return false;

        // Validação de Unicidade da Sigla
        for (Curso c : lstCursos) {
            if (c.getSigla().equalsIgnoreCase(curso.getSigla())) {
                return false;
            }
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

// FORMADOR

    public Formador novoFormador(String nome, LocalDate dataNascimento, String cc, String email, String contacto, String areaFormacao) {
        return new Formador(nome, dataNascimento, cc, email, contacto, areaFormacao);
    }

    public boolean validaFormador(Formador formador) {
        if (formador == null) return false;
        if (!formador.valida()) return false; // Validação local

        for (Formador f : lstFormadores) {
            if (f.getCc().equals(formador.getCc())) {
                throw new IllegalArgumentException("Erro: CC já registado noutro formador.");
            }
            if (f.getEmail().equalsIgnoreCase(formador.getEmail())) {
                throw new IllegalArgumentException("Erro: Email já registado noutro formador.");
            }
        }
        return true;
    }

    public boolean registarFormador(Formador formador) {
        if (validaFormador(formador)) {
            // 1. Gerar ID Único Automático (Requisito 99)
            // Exemplo: "F" + número sequencial (F1, F2...)
            String novoId = "FOR" + (lstFormadores.size() + 1);
            formador.setIdFormador(novoId);

            // 2. Gerar Credenciais (Requisito 99)
            String username = formador.getEmail(); // Usar email como login
            String password = UUID.randomUUID().toString().substring(0, 8); // Pass aleatória

            Credenciais creds = new Credenciais(username, password);
            formador.setCredenciais(creds);

            // 3. Adicionar à lista
            boolean adicionou = lstFormadores.add(formador);

            if (adicionou) {
                // Simular envio de email (console log)
                System.out.println(">> Email enviado a " + formador.getEmail() + " | Pass: " + password);
            }
            return adicionou;
        }
        return false;
    }



}
    
    
