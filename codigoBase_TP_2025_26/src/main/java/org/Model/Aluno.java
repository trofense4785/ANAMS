package org.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluno {
    private String codigoAluno;
    private String nome;
    private String email;
    private String cc;
    private LocalDate dataNascimento;

    private Credenciais credenciais;


    private List<Inscricao> minhasInscricoes;

    private List<Anulacao> minhasAnulacoes;

    public Aluno(String nome, String email, String cc, LocalDate dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.cc = cc;
        this.dataNascimento = dataNascimento;

        this.minhasInscricoes = new ArrayList<>();
        this.minhasAnulacoes = new ArrayList<>();
    }



    public boolean registarInscricao(Curso curso) {
        if (estaInscrito(curso)) return false;

        Inscricao ins = new Inscricao(this, curso);

        if (ins.getEstado().equals("ativa")) {
            return minhasInscricoes.add(ins);
        }
        return false;
    }


    public boolean inscreverEmCurso(Curso curso) {
        return registarInscricao(curso);
    }

    public boolean estaInscrito(Curso c) {
        for (Inscricao i : minhasInscricoes) {
            if (i.getCurso().equals(c) && i.getEstado().equals("ativa")) return true;
        }
        return false;
    }



    public List<String> obterCursosInscritosAtivos() {
        List<String> listaCursos = new ArrayList<>();
        for (Inscricao ins : minhasInscricoes) {
            if (ins.getEstado().equalsIgnoreCase("ativa")) {
                String dados = String.format("[%s] %s", ins.getCurso().getSigla(), ins.getCurso().getTitulo());
                listaCursos.add(dados);
            }
        }
        return listaCursos;
    }

    public Inscricao obterInscricao(String idInscricao) {
        for (Inscricao ins : minhasInscricoes) {
            if (ins.getIdInscricao().equals(idInscricao)) {
                return ins;
            }
        }
        return null;
    }

    public boolean registarAnulacao(Curso curso) {
        Inscricao inscricaoAlvo = null;
        for (Inscricao ins : minhasInscricoes) {
            if (ins.getCurso().equals(curso) && ins.getEstado().equalsIgnoreCase("ativa")) {
                inscricaoAlvo = ins;
                break;
            }
        }

        if (inscricaoAlvo != null) {
            Anulacao anulacao = new Anulacao(inscricaoAlvo);
            if (anulacao.valida()) {
                this.minhasAnulacoes.add(anulacao);
                inscricaoAlvo.setEstado("cancelada");
                return true;
            }
        }
        return false;
    }



    public boolean temInscricaoNoCurso(Curso c) {
        for (Inscricao ins : minhasInscricoes) {
            if (ins.getCurso().equals(c)) return true;
        }
        return false;
    }

    public List<Curso> obterCursosInscritos() {
        List<Curso> cursos = new ArrayList<>();
        for (Inscricao ins : minhasInscricoes) {
            cursos.add(ins.getCurso());
        }
        return cursos;
    }

    public List<String> getBoletimNotas(Curso curso) {
        List<String> dados = new ArrayList<>();
        boolean tudoLancado = true;

        for (Modulo m : curso.getListaModulos()) {
            Double nota = m.getNotaAluno(this);

            if (nota != null) {
                dados.add(String.format("Módulo %s: %.2f valores", m.getTitulo(), nota));
            } else {
                dados.add("Módulo " + m.getTitulo() + ": Pendente");
                tudoLancado = false;
            }
        }

        if (tudoLancado) {
            double notaFinal = curso.calcularNota(this);
            dados.add(String.format(">>> NOTA FINAL DO CURSO: %.2f valores", notaFinal));
        } else {
            dados.add(">>> Nota final indisponível (existem módulos pendentes).");
        }
        return dados;
    }



    public void setCodigoAluno(String codigo) { this.codigoAluno = codigo; }
    public void setCredenciais(Credenciais c) { this.credenciais = c; }
    public Credenciais getCredenciais() { return credenciais; }

    public String getEmail() { return email; }
    public String getNome() { return nome; }
    public String getCc() { return cc; }
    public LocalDate getDataNascimento() { return dataNascimento; }

    @Override
    public String toString() {
        return String.format("Aluno [%s] %s (%s)", codigoAluno, nome, email);
    }
}