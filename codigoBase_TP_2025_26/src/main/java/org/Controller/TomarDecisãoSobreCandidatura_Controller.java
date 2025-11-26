package org.Controller;


import org.Model.Instituicao;

public class TomarDecisãoSobreCandidatura_Controller {

    private Instituicao instituicao;

    public TomarDecisãoSobreCandidatura_Controller() {
        this.instituicao = Instituicao.getInstance();
    }

    public List<Candidatura> getCandidaturasPendentes() {
        return instituicao.getCandidaturasPendentes();
    }

    public boolean registarDecisao(Candidatura candidatura, boolean aceita, String justificacao) {
        if (candidatura == null) return false;

        // 1. Atualiza o estado da candidatura e data de decisão
        candidatura.setDecisao(aceita, justificacao);

        // 2. Se aceite, cria e regista automaticamente o Aluno
        if (aceita) {
            Aluno novoAluno = instituicao.criarAlunoDeCandidatura(candidatura);
            // Aqui poderia enviar o email com o código de aluno gerado
            System.out.println("Aluno criado com sucesso: " + novoAluno.getCodigoAluno());
        }

        return true;
    }
}