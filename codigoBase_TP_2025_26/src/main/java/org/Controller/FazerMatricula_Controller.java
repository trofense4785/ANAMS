package org.Controller;

import org.Model.Candidato;
import org.Model.Instituicao;

import java.time.LocalDate;

public class FazerMatricula_Controller {
    private final Instituicao instituicao;
    private Candidato candidato; // Entidade em estado (Candidato)

    public FazerMatricula_Controller(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    public void novoCandidato() {
        // A Instituicao deve ter um método para instanciar o objeto Candidato
        this.candidato = instituicao.novoCandidato();
    }

    public void setDados(String nome, LocalDate dataNascimento, String habilitacoesAcademicas,
                         String email, String numeroCC, String genero) {

        this.candidato.setNome(nome);
        this.candidato.setDataNascimento(dataNascimento);
        this.candidato.setHabilitacoesAcademicas(habilitacoesAcademicas);
        this.candidato.setEmail(email);
        this.candidato.setNumeroCC(numeroCC);
        this.candidato.setGenero(genero);
    }

    public boolean submeterCandidatura() {
        // Assumindo que a validação (e.g., idade mínima, formato de email) é feita aqui.
        if (this.candidato.valida()) {
            this.instituicao.adicionarCandidato(this.candidato);
            System.out.println("✅ Candidatura de " + candidato.getNome() + " submetida com sucesso.");
            return true;
        }
        System.out.println("❌ Falha na submissão: Dados inválidos.");
        return false;
    }

    public Candidato getCandidato() { return this.candidato; }
}