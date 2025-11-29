package org.UI;

import java.io.IOException;
import java.util.Scanner;

import org.Model.Aluno;
import org.Model.Sessao;
import utils.Utils;
import org.Model.Instituicao;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuAlu_UI {

    private Instituicao instituicao;
    private Scanner sc;

    public MenuAlu_UI(Instituicao instituicao) {
        this.instituicao = instituicao;
        this.sc = new Scanner(System.in);
    }

    public void run() {
        // --- PASSO 1: LOGIN DO ALUNO ---
        if (!fazerLogin()) {
            return;
        }

        // --- PASSO 2: MENU DE FUNCIONALIDADES ---
        String opcao;
        do {
            System.out.println("\n###### MENU ALUNO ######");
            System.out.println("1. Inscrever em Curso (UC9)");
            System.out.println("2. Anular Inscrição (UC10)");
            System.out.println("3. Consultar Minhas Notas (UC14)");
            // REMOVIDO: 4. Consultar Lista Geral (UC6) - Exclusivo do CA
            System.out.println("------------------------");
            System.out.println("0. Logout / Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    new FazerInscricaoCurso_UI().run();
                    break;
                case "2":
                    new AnularInscricao_UI().run();
                    break;
                case "3":
                    new ConsultarClassificacoes_UI().run();
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

    // --- Lógica de Autenticação de Aluno ---
    private boolean fazerLogin() {
        System.out.println("\n--- Login Aluno ---");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        // Procura na lista de alunos da Instituição
        for (Aluno a : instituicao.getListaAlunos()) {
            if (a.getEmail().equalsIgnoreCase(email)) {

                // Verifica password
                if (a.getCredenciais() != null &&
                        a.getCredenciais().getPassword().equals(pass)) {

                    // Sucesso: Guarda na sessão
                    Sessao.getInstance().login(a.getEmail());
                    System.out.println("✅ Bem-vindo, Aluno " + a.getNome() + "!");
                    return true;
                }
            }
        }

        System.out.println("❌ Credenciais inválidas ou aluno não encontrado.");
        return false;
    }
}