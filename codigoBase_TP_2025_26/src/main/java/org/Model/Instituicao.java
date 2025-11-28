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


// UC4

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

// UC1

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

// UC3

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

// UC5
    public boolean validarDisponibilidadeFormador(Formador formador, SessaoModulo novaSessao) {
        if (formador == null) return false;

        for (Curso c : lstCursos) {
            for (Modulo m : c.getListaModulos()) {
            // Só verificamos módulos onde este formador é o responsável
                if (m.getFormadorResponsavel() != null &&
                    m.getFormadorResponsavel().equals(formador)) {

                    for (SessaoModulo sessaoExistente : m.getLstSessoes()) {
                        if (haSobreposicao(sessaoExistente, novaSessao)){
                            return false; // Está ocupado!
                        }
                    }
                }
            }
        }
        return true; // Está livre
    }

    public boolean validarDisponibilidadeSala(String sala, SessaoModulo novaSessao) {
        for (Curso c : lstCursos) {
            for (Modulo m : c.getListaModulos()) {
                for (SessaoModulo sessaoExistente : m.getLstSessoes()) {
                    // Se for a mesma sala E houver sobreposição de horas
                    if (sessaoExistente.getSala().equalsIgnoreCase(sala) &&
                            haSobreposicao(sessaoExistente, novaSessao)) {
                        return false; // Sala ocupada!
                    }
                }
            }
        }
        return true;
    }

    private boolean haSobreposicao(SessaoModulo s1, SessaoModulo s2) {
        // Lógica temporal: (Inicio1 < Fim2) E (Inicio2 < Fim1)
        LocalDateTime inicio1 = s1.getDataHoraInicio();
        LocalDateTime fim1 = s1.getDataHoraFim();

        LocalDateTime inicio2 = s2.getDataHoraInicio();
        LocalDateTime fim2 = s2.getDataHoraFim();

        return inicio1.isBefore(fim2) && inicio2.isBefore(fim1);
    }

    // Getter necessário para o Controller
    public List<Curso> getLstCursos() {
        return lstCursos;
    }

// UC6

    /**
     * Passo 2.1.1: getCursosPorEstado(estado)
     * Implementa o LOOP do diagrama para filtrar a lista.
     * * @param estado (0-A iniciar, 1-Ativo, 2-Suspenso, 3-Cancelado, 4-Concluído)
     * Se estado for -1, retorna todos.
     */
    public List<Curso> getCursosPorEstado(EstadoCurso estadoFiltro) {

        List<Curso> listaFiltrada = new ArrayList<>();

        for (Curso cur : this.lstCursos) {
            // 2. Lógica de "Ver Todos":
            // Em vez de usares o número -1, usas 'null' para dizer "sem filtro"
            if (estadoFiltro == null || cur.getEstado() == estadoFiltro) {
                listaFiltrada.add(cur);
            }
        }

        return listaFiltrada;
    }

    /**
     * Passo 3.1.1: cur = obterCurso(sigla)
     * Procura um curso específico pela sigla.
     */
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

    /**
     * Validação Global: Verifica se o CC ou Email já têm candidatura ou são alunos.
     */
    public boolean validaCandidatura(Candidatura cand) {
        if (cand == null) return false;

        // 1. Valida formato interno
        if (!cand.valida()) return false;

        // 2. Valida Unicidade nas Candidaturas
        for (Candidatura c : lstCandidaturas) {
            if (c.getCc().equals(cand.getCc())) throw new IllegalArgumentException("Já existe uma candidatura com este CC.");
            if (c.getEmail().equalsIgnoreCase(cand.getEmail())) throw new IllegalArgumentException("Já existe uma candidatura com este Email.");
        }

        // NOTA: Idealmente, aqui também verificarias a 'lstAlunos' para ver se a pessoa já é aluna da escola.

        return true;
    }

    /**
     * Regista, Gera Credenciais e Envia Email.
     */
    public boolean registarCandidatura(Candidatura cand) {
        if (validaCandidatura(cand)) {

            // Requisito [109]: Enviar credenciais após submissão
            String username = cand.getEmail();
            String password = UUID.randomUUID().toString().substring(0, 8); // Password aleatória

            Credenciais credenciais = new Credenciais(username, password);
            cand.setCredenciais(credenciais);

            // Simulação de envio de email
            System.out.println("Email enviado para " + cand.getEmail() + " | Login: " + username + " Pass: " + password);
            System.out.println("Instruções enviadas: Aguarde validação do Coordenador.");

            return lstCandidaturas.add(cand);
        }
        return false;
    }

// UC8

    /**
     * Retorna apenas as candidaturas que ainda não foram tratadas (Estado 0).
     */
    public List<Candidatura> getCandidaturasPendentes() {
        List<Candidatura> pendentes = new ArrayList<>();
        for (Candidatura c : lstCandidaturas) {
            if (c.getEstado() == 0) { // 0 = Submetida/Pendente
                pendentes.add(c);
            }
        }
        return pendentes;
    }

    /**
     * Processa a decisão final.
     * Se for ACEITE -> Cria Aluno.
     * Sempre -> Envia Email.
     */
    public boolean registarDecisaoCandidatura(Candidatura cand, boolean aceite, String justificacao, LocalDate dataDecisao) {
        if (cand == null) return false;

        // 1. Atualizar a Candidatura
        cand.registarDecisao(aceite, justificacao, dataDecisao);

        // 2. Se ACEITE -> Criar Aluno Automaticamente [cite: 20]
        if (aceite) {
            criarAlunoDeCandidatura(cand);
        }

        // 3. Notificar Candidato (Simulação)
        String decisao = aceite ? "ACEITE" : "REJEITADA";
        System.out.println("Email para " + cand.getEmail() + ": A sua candidatura foi " + decisao + ".");
        System.out.println("Justificação: " + justificacao);

        return true;
    }

    /**
     * Método privado para transformar Candidato em Aluno.
     */
    private void criarAlunoDeCandidatura(Candidatura cand) {
        // 1. Criar o objeto Aluno com os dados pessoais
        Aluno novoAluno = new Aluno(cand.getNome(), cand.getEmail(), cand.getCc(), cand.getDataNascimento());

        // 2. Gerar e atribuir o Código de Aluno (Requisito IT2)
        int sequencial = lstAlunos.size() + 1;
        String codigo = "AL-" + java.time.Year.now().getValue() + "-" + sequencial;
        novoAluno.setCodigoAluno(codigo);

        // 3. ⚠️ TRANSFERIR AS CREDENCIAIS (Correção)
        // O aluno herda o login/password que tinha como candidato
        if (cand.getCredenciais() != null) {
            novoAluno.setCredenciais(cand.getCredenciais());
        } else {
            // Caso de segurança: se por algum motivo não tiver, gera novas
            // (Isto não deve acontecer se o fluxo UC7 estiver correto)
            // novoAluno.setCredenciais(new Credenciais(cand.getEmail(), "novaPass123"));
        }

        // 4. Guardar na lista
        lstAlunos.add(novoAluno);

        System.out.println("Aluno criado com sucesso: " + codigo);
    }

    public List<Aluno> getListaAlunos() { return lstAlunos; }

// UC9

    public List<String> obterListaCursosAsString(EstadoCurso estadoFiltro) {
        List<String> listaStrings = new ArrayList<>();

        // Diagrama: loop
        for (Curso curso : lstCursos) {
            // Diagrama: 1.2.1.3: estado = getEstado() e verificação
            if (curso.getEstado() == estadoFiltro) {
                // Diagrama: 1.2.1.4: curso.toString()
                // Diagrama: 1.2.1.5: add(curso) -> Adiciona a String à lista
                listaStrings.add(curso.toString());
            }
        }
        return listaStrings;
    }



    /**
     * Passo 3.1.1: registarInscricao(curso)
     * O diagrama mostra a Instituição a receber o pedido e a passá-lo ao Aluno.
     */
    public boolean registarInscricao(Curso curso, Aluno aluno) {
        if (curso != null && aluno != null) {
            // A Instituição delega no Aluno (como mostra a seta para a direita no diagrama)
            return aluno.registarInscricao(curso);
        }
        return false;
    }

    // Método auxiliar para buscar aluno por email (Passo 1.1 do diagrama)
    public Aluno getAlunoPorEmail(String email) {
        for (Aluno a : lstAlunos) {
            if (a.getEmail().equalsIgnoreCase(email)) return a;
        }
        return null;
    }

// UC10

    public Aluno obterAlunoByEmail(String email) {
        // Loop sobre arrAlu (Passo 1.1.1.1)
        for (Aluno alu : lstAlunos) {
            // Passo 1.1.1.2: getEmail()
            if (alu.getEmail().equalsIgnoreCase(email)) {
                return alu; // setFlag(true) implícito no return
            }
        }
        return null;
    }

// UC11

    public Formador getFormadorByEmail(String email) {
        for (Formador f : lstFormadores) {
            if (f.getEmail().equalsIgnoreCase(email)) {
                return f;
            }
        }
        return null;
    }

    /**
     * Passo 1.1.2: getCursosDoFormador(formador)
     * Implementa o LOOP e o ALT do diagrama.
     */
    public List<Curso> getCursosDoFormador(Formador formador) {
        // 1.1.2.1: create()
        List<Curso> meusCursos = new ArrayList<>();

        if (formador == null) return meusCursos;

        // Loop Principal (Para cada curso na instituição)
        for (Curso curso : lstCursos) {
            boolean adicionado = false;

            // 1.1.2.2: isResponsavel(formador)
            if (curso.isResponsavel(formador)) {
                // 1.1.2.3: add(cur)
                meusCursos.add(curso);
                adicionado = true;
            }

            // Se ainda não foi adicionado, verificamos os módulos (O bloco ELSE/LOOP do diagrama)
            if (!adicionado) {
                // Loop Interno (Para cada módulo)
                for (Modulo m : curso.getListaModulos()) {
                    // 1.1.2.4: getFormadorResponsavel()
                    if (m.getFormadorResponsavel() != null &&
                            m.getFormadorResponsavel().equals(formador)) {

                        // 1.1.2.5: add(cur) - Adiciona e para de procurar neste curso
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

        // Percorre a lista global de alunos
        for (Aluno aluno : this.lstAlunos) {
            // Verifica se o aluno tem alguma inscrição nesse curso
            if (aluno.temInscricaoNoCurso(curso)) {
                alunosDoCurso.add(aluno);
            }
        }
        return alunosDoCurso;
    }

// UC13 - NAO HA NADA PARA METER


}


    
    
