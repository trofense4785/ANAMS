/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.Model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Instituicao
{
    private static Instituicao instance;
    private List<TipoCurso> lstTiposCurso;
    private CA ca; // Apenas um CA
    private List<Formador> lstFormadores;
    private List<Curso> lstCursos;
    private List<Candidatura> lstCandidaturas;
    private List<Aluno> lstAlunos;

    // Completar
    public Instituicao()
    {
        this.lstTiposCurso = new ArrayList<>();
        this.ca = null;
        this.lstCursos = new ArrayList<>();
        this.lstFormadores = new ArrayList<>();
        this.lstCandidaturas = new ArrayList<>();
        this.lstAlunos=new ArrayList<>();
    }

    public static Instituicao getInstance() {
        if (instance == null) {
            instance = new Instituicao();
        }
        return instance;
    }

// UC2

    public TipoCurso novoTipoCurso(String sigla, String descricao) {
        return new TipoCurso(sigla, descricao);
    }


    public boolean validaTipoCurso(TipoCurso tipo) {
        if (tipo == null) return false;


        for (TipoCurso t : lstTiposCurso) {
            if (t.getSigla().equalsIgnoreCase(tipo.getSigla())) {
                throw new IllegalArgumentException("Erro: Já existe um tipo de curso com a sigla " + tipo.getSigla());
            }
        }
        return true;
    }


    public boolean registarTipoCurso(TipoCurso tipo) {
        if (tipo.valida() && validaTipoCurso(tipo)) {
            return lstTiposCurso.add(tipo);
        }
        return false;
    }

    public List<TipoCurso> getLstTiposCurso() {
        return lstTiposCurso;
    }


// UC4

    public Curso novoCurso(String titulo, String sigla, TipoCurso tipo, String descricao, LocalDate dataInicio, LocalDate dataTermino) {
        return new Curso(titulo, sigla, tipo, descricao, dataInicio, dataTermino);
    }

    public boolean validaCurso(Curso curso) {
        if (curso == null) return false;


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

// UC1

    public CA getCA() {
        return this.ca;
    }

    public CA novoCA(String nome, String email, String cc, String sigla, String contacto) {
        return new CA(nome, email, cc, sigla, contacto);
    }
    public boolean validaCA(CA novoCA) {
        if (novoCA == null) return false;


        if (this.ca != null) {
            throw new IllegalStateException("Erro: Já existe um Coordenador Académico registado.");
        }


        return true;
    }
    public boolean registarCA(CA novoCA) {
        if (validaCA(novoCA)) {

            String username = "admin";
            String password = java.util.UUID.randomUUID().toString().substring(0, 8);

            Credenciais creds = new Credenciais(username, password);
            novoCA.setCredenciais(creds);


            this.ca = novoCA;


            System.out.println("\n*****************************************");
            System.out.println(" Credenciais Geradas:");
            System.out.println(" Login: " + username);
            System.out.println(" Password: " + password);
            System.out.println("*****************************************\n");


            return true;
        }
        return false;
    }

// UC3

    public Formador novoFormador(String nome, LocalDate dataNascimento, String cc, String email, String contacto, String areaFormacao) {
        return new Formador(nome, dataNascimento, cc, email, contacto, areaFormacao);
    }

    public boolean validaFormador(Formador formador) {
        if (formador == null) return false;


        if (!formador.valida()) return false;


        for (Formador f : lstFormadores) {


            if (f.getCc().equals(formador.getCc())) {
                throw new IllegalArgumentException("Erro: CC já registado noutro formador.");
            }


            if (f.getEmail().equalsIgnoreCase(formador.getEmail())) {
                throw new IllegalArgumentException("Erro: Email já registado noutro formador.");
            }


            if (f.getContacto().equals(formador.getContacto())) {
                throw new IllegalArgumentException("Erro: Contacto telefónico já registado noutro formador.");
            }

        }
        return true;
    }

    public boolean registarFormador(Formador formador) {
        if (validaFormador(formador)) {


            String novoId = "FOR" + (lstFormadores.size() + 1);
            formador.setIdFormador(novoId);


            String username = formador.getEmail();
            String password = UUID.randomUUID().toString().substring(0, 8);

            Credenciais creds = new Credenciais(username, password);
            formador.setCredenciais(creds);


            boolean adicionou = lstFormadores.add(formador);

            if (adicionou) {

                System.out.println(">> Email enviado a " + formador.getEmail() + " | Pass: " + password);
            }
            return adicionou;
        }
        return false;
    }

    public boolean existeFormadorComCC(String cc) {
        for (Formador f : lstFormadores) {
            if (f.getCc().equals(cc)) return true;
        }
        return false;
    }

    public boolean existeFormadorComEmail(String email) {
        for (Formador f : lstFormadores) {
            if (f.getEmail().equalsIgnoreCase(email)) return true;
        }
        return false;
    }

    public boolean existeFormadorComContacto(String contacto) {
        for (Formador f : lstFormadores) {
            if (f.getContacto().equals(contacto)) return true;
        }
        return false;
    }

// UC5
    public boolean validarDisponibilidadeFormador(Formador formador, SessaoModulo novaSessao) {
        if (formador == null) return false;

        for (Curso c : lstCursos) {
            for (Modulo m : c.getListaModulos()) {

                if (m.getFormadorResponsavel() != null &&
                    m.getFormadorResponsavel().equals(formador)) {

                    for (SessaoModulo sessaoExistente : m.getLstSessoes()) {
                        if (haSobreposicao(sessaoExistente, novaSessao)){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean validarDisponibilidadeSala(String sala, SessaoModulo novaSessao) {
        for (Curso c : lstCursos) {
            for (Modulo m : c.getListaModulos()) {
                for (SessaoModulo sessaoExistente : m.getLstSessoes()) {

                    if (sessaoExistente.getSala().equalsIgnoreCase(sala) &&
                            haSobreposicao(sessaoExistente, novaSessao)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean haSobreposicao(SessaoModulo s1, SessaoModulo s2) {

        LocalDateTime inicio1 = s1.getDataHoraInicio();
        LocalDateTime fim1 = s1.getDataHoraFim();

        LocalDateTime inicio2 = s2.getDataHoraInicio();
        LocalDateTime fim2 = s2.getDataHoraFim();

        return inicio1.isBefore(fim2) && inicio2.isBefore(fim1);
    }


    public List<Curso> getLstCursos() {
        return lstCursos;
    }

// UC6


    public List<Curso> getCursosPorEstado(EstadoCurso estadoFiltro) {

        List<Curso> listaFiltrada = new ArrayList<>();

        for (Curso cur : this.lstCursos) {

            if (estadoFiltro == null || cur.getEstado() == estadoFiltro) {
                listaFiltrada.add(cur);
            }
        }

        return listaFiltrada;
    }


    public Curso getCurso(String sigla) {
        for (Curso c : lstCursos) {
            if (c.getSigla().equalsIgnoreCase(sigla)) {
                return c;
            }
        }
        return null;
    }

// UC7

    public Candidatura novaCandidatura(String nome, LocalDate dataNascimento, String habilitacoes, String email, String cc, String genero) {
        return new Candidatura(nome, dataNascimento, habilitacoes, email, cc, genero);
    }


    public boolean validaCandidatura(Candidatura cand) {
        if (cand == null) return false;


        if (!cand.valida()) return false;


        for (Candidatura c : lstCandidaturas) {
            if (c.getCc().equals(cand.getCc())) throw new IllegalArgumentException("Já existe uma candidatura com este CC.");
            if (c.getEmail().equalsIgnoreCase(cand.getEmail())) throw new IllegalArgumentException("Já existe uma candidatura com este Email.");
        }



        return true;
    }


    public boolean registarCandidatura(Candidatura cand) {
        if (validaCandidatura(cand)) {


            String username = cand.getEmail();
            String password = UUID.randomUUID().toString().substring(0, 8);
            Credenciais credenciais = new Credenciais(username, password);
            cand.setCredenciais(credenciais);


            System.out.println("Email enviado para " + cand.getEmail() + " | Login: " + username + " Pass: " + password);
            System.out.println("Instruções enviadas: Aguarde validação do Coordenador.");

            return lstCandidaturas.add(cand);
        }
        return false;
    }

// UC8


    public List<Candidatura> getCandidaturasPendentes() {
        List<Candidatura> pendentes = new ArrayList<>();
        for (Candidatura c : lstCandidaturas) {
            if (c.getEstado() == 0) {
                pendentes.add(c);
            }
        }
        return pendentes;
    }


    public boolean registarDecisaoCandidatura(Candidatura cand, boolean aceite, String justificacao, LocalDate dataDecisao) {
        if (cand == null) return false;


        cand.registarDecisao(aceite, justificacao, dataDecisao);


        if (aceite) {
            criarAlunoDeCandidatura(cand);
        }


        String decisao = aceite ? "ACEITE" : "REJEITADA";
        System.out.println("Email para " + cand.getEmail() + ": A sua candidatura foi " + decisao + ".");
        System.out.println("Justificação: " + justificacao);

        return true;
    }


    private void criarAlunoDeCandidatura(Candidatura cand) {

        Aluno novoAluno = new Aluno(cand.getNome(), cand.getEmail(), cand.getCc(), cand.getDataNascimento());


        int sequencial = lstAlunos.size() + 1;
        String codigo = "AL-" + java.time.Year.now().getValue() + "-" + sequencial;
        novoAluno.setCodigoAluno(codigo);


        if (cand.getCredenciais() != null) {
            novoAluno.setCredenciais(cand.getCredenciais());
        } else {

        }


        lstAlunos.add(novoAluno);

        System.out.println("Aluno criado com sucesso: " + codigo);
    }

    public List<Aluno> getListaAlunos() { return lstAlunos; }

// UC9

    public List<String> obterListaCursosAsString(Aluno aluno) {
        if (aluno != null) {
            return aluno.obterCursosInscritosAtivos();
        }
        return new ArrayList<>();
    }




    public boolean registarInscricao(Curso curso, Aluno aluno) {
        if (curso != null && aluno != null) {

            return aluno.registarInscricao(curso);
        }
        return false;
    }

    public Aluno getAlunoPorEmail(String email) {
        for (Aluno a : lstAlunos) {
            if (a.getEmail().equalsIgnoreCase(email)) return a;
        }
        return null;
    }

// UC10

    public Aluno obterAlunoPorEmail(String email) {

        for (Aluno alu : lstAlunos) {

            if (alu.getEmail().equalsIgnoreCase(email)) {
                return alu;
            }
        }
        return null;
    }

    public boolean registaAnulacao(Curso curso, Aluno aluno) {
        if (curso != null && aluno != null) {

            return aluno.registarAnulacao(curso);
        }
        return false;
    }

// UC11

    public Formador getFormadorPorEmail(String email) {
        for (Formador f : lstFormadores) {
            if (f.getEmail().equalsIgnoreCase(email)) {
                return f;
            }
        }
        return null;
    }

    public List<Curso> getCursosDoFormador(Formador formador) {

        List<Curso> meusCursos = new ArrayList<>();

        if (formador == null) return meusCursos;


        for (Curso curso : lstCursos) {
            boolean adicionado = false;


            if (curso.isResponsavel(formador)) {
                meusCursos.add(curso);
                adicionado = true;
            }


            if (!adicionado) {

                for (Modulo m : curso.getListaModulos()) {

                    if (m.getFormadorResponsavel() != null &&
                            m.getFormadorResponsavel().equals(formador)) {


                        meusCursos.add(curso);
                        break;
                    }
                }
            }
        }
        return meusCursos;
    }

// UC12

    public List<Aluno> getAlunosDoCurso(Curso curso) {
        List<Aluno> alunosDoCurso = new ArrayList<>();


        for (Aluno aluno : this.lstAlunos) {

            if (aluno.temInscricaoNoCurso(curso)) {
                alunosDoCurso.add(aluno);
            }
        }
        return alunosDoCurso;
    }
}


    
    
