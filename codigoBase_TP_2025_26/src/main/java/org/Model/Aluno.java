package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String codigoAluno; // Gerado automaticamente (ex: A-2025-1)
    private String nome;
    private String email;
    private String cc;
    private LocalDate dataNascimento;
    private Credenciais credenciais;
    private List<Inscricao> minhasInscricoes;
    private List<Inscricao> inscricoes;

    // Podes adicionar outros campos herdados da candidatura

    public Aluno(String nome, String email, String cc, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cc = cc;
        this.dataNascimento = dataNascimento;
        this.minhasInscricoes = new ArrayList<>();
        this.inscricoes = new ArrayList<>();
        // O código é definido pela Instituição
    }

    public void setCodigoAluno(String codigo) {
        this.codigoAluno = codigo;
    }

    public boolean registarInscricao(Curso curso) {
        // Validação (Passo 3.1.1.1.2 do diagrama: valida)
        if (estaInscrito(curso)) {
            // O aluno já está inscrito
            return false;
        }


        // Criação (Passo 3.1.1.1: create(curso))
        // Nota: O diagrama passa apenas 'curso', mas precisamos do 'this' (aluno) para criar a ligação
        Inscricao ins = new Inscricao(this, curso);

        // Validação interna da inscrição (Passo 3.1.1.1.3: valida(ins))
        // (Pode ser apenas ver se o objeto foi criado corretamente)
        if (ins.getEstado().equals("ativa")) {

        // Adição à lista (Passo 3.1.1.1.4: addInscricao(ins))
        return minhasInscricoes.add(ins);
        }
        return false;
    }

    private boolean estaInscrito(Curso c) {
        for (Inscricao i : minhasInscricoes) {
            if (i.getCurso().equals(c) && i.getEstado().equals("ativa")) return true;
        }
        return false;
    }

    public List<String> obterListaInscricoesAsString() {
        // Passo 1.2.1.1: create lstI
        List<String> lstI = new ArrayList<>();

        // Loop sobre arrIns (Passo 1.2.1.2)
        for (Inscricao ins : this.inscricoes) {
            // Passo 1.2.1.3: estado = getEstado()
            // Opt: estado.equals("ativa")
            if (ins.getEstado().equalsIgnoreCase("ativa")) {
                // Passo 1.2.1.4: toString()
                String str = ins.toString();
                // Passo 1.2.1.5: add(str)
                lstI.add(str);
            }
        }
        return lstI;
    }

    // --- Passo 2.1.1: obterInscricao(idInscricao) ---
    public Inscricao obterInscricao(String idInscricao) {
        for (Inscricao ins : inscricoes) {
            if (ins.getIdInscricao().equals(idInscricao)) {
                return ins;
            }
        }
        return null;
    }

    public boolean temInscricaoNoCurso(Curso c) {
        for (Inscricao ins : minhasInscricoes) {
            if (ins.getCurso().equals(c)) {
                // Se encontrou qualquer inscrição (Ativa, Cancelada, Concluída), retorna true
                return true;
            }
        }
        return false;
    }

    // --- Passo 3.1.1: registaAnulacao(ins) ---
    public boolean registaAnulacao(Inscricao ins) {
        if (ins != null) {
            // Passo 3.1.1.1: setEstado("Cancelada")
            ins.setEstado("cancelada");
            return true;
        }
        return false;
    }


    public String getEmail() { return email; }

    public void setCredenciais(Credenciais credenciais) {
        this.credenciais = credenciais;
    }

    // Getter (necessário para validação de login, se implementares)
    public Credenciais getCredenciais() {
        return credenciais;
    }



    @Override
    public String toString() {
        return String.format("Aluno [%s] %s  %s", codigoAluno, nome, email);
    }
}