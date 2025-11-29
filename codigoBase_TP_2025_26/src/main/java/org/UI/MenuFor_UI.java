package org.UI;

import java.io.IOException;
import java.util.Scanner;

import org.Model.Formador;
import org.Model.Sessao;
import utils.Utils;
import org.Model.Instituicao;

public class MenuFor_UI {

    private Instituicao instituicao;
    private Scanner sc;

    public MenuFor_UI(Instituicao instituicao) {
        this.instituicao = instituicao;
        this.sc = new Scanner(System.in);
    }

    public void run() {
        if (!fazerLogin()) {
            return;
        }

        String opcao;
        do {
            System.out.println("\n###### MENU FORMADOR ######");
            System.out.println("1. Consultar Meus Cursos (UC11)");
            System.out.println("2. Consultar Alunos de um Curso (UC12)");
            System.out.println("3. Registar Classificações (UC15)");
            System.out.println("---------------------------");
            System.out.println("0. Logout / Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    new ConsultarListaCursosResponsavel_UI().run();
                    break;
                case "2":
                    new ConsultarListaAlunosCurso_UI().run();
                    break;
                case "3":
                    new RegistarClassificacao_UI().run();
                    break;
                case "0":
                    Sessao.getInstance().logout();
                    System.out.println("Sessão terminada.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }

    // --- Lógica de Autenticação de Formador ---
    private boolean fazerLogin() {
        System.out.println("\n--- Login Formador ---");
        System.out.print("Username : ");
        String inputID = sc.nextLine(); // Aqui fica "joao1458"
        System.out.print("Password: ");
        String inputPass = sc.nextLine();

        for (Formador f : instituicao.getLstFormadores()) {
            if (f.getCredenciais() != null) {
                // Verifica se bate certo com Username OU Email
                boolean match = f.getCredenciais().getUsername().equals(inputID)
                        || f.getEmail().equalsIgnoreCase(inputID);

                if (match && f.getCredenciais().getPassword().equals(inputPass)) {

                    // --- O SEGREDO ESTÁ AQUI ---
                    // NÃO FAÇA ISTO: Sessao.getInstance().login(inputID); <- ERRADO! Guardaria "joao1458"

                    // FAÇA ISTO:
                    Sessao.getInstance().login(f.getEmail()); //  CORRETO! Guarda "joao@isep.ipp.pt"

                    System.out.println(" Bem-vindo, " + f.getNome());
                    return true;
                }
            }
        }
        System.out.println(" Login falhou.");
        return false;
    }
}
