package org.UI;

import java.io.IOException;
import java.util.Scanner;

import org.Model.Formador;
import org.Model.Sessao;
import utils.Utils;
import org.Model.Instituicao;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuFor_UI {

    private Instituicao instituicao;
    private Scanner sc;

    public MenuFor_UI(Instituicao instituicao) {
        this.instituicao = instituicao;
        this.sc = new Scanner(System.in);
    }

    public void run() {
        // --- PASSO 1: LOGIN DO FORMADOR ---
        if (!fazerLogin()) {
            return;
        }

        // --- PASSO 2: MENU DE FUNCIONALIDADES ---
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
                    // Consultar cursos onde é responsável ou leciona
                    new ConsultarListaCursosResponsavel_UI().run();
                    break;
                case "2":
                    // Ver lista de alunos (turma)
                    new ConsultarListaAlunosCurso_UI().run();
                    break;
                case "3":
                    // Lançar notas nos módulos
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
        System.out.print("Username: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        // Procura na lista de formadores da Instituição
        // (Nota: Tens de ter um getter para a lista ou um método de busca na Instituição)
        for (Formador f : instituicao.getLstFormadores()) {
            if (f.getEmail().equalsIgnoreCase(email)) {
                // Verifica password
                if (f.getCredenciais() != null &&
                        f.getCredenciais().getPassword().equals(pass)) {

                    // Sucesso: Guarda na sessão
                    Sessao.getInstance().login(f.getEmail());
                    System.out.println("✅ Bem-vindo, Formador " + f.getNome() + "!");
                    return true;
                }
            }
        }

        System.out.println("❌ Credenciais inválidas ou formador não encontrado.");
        return false;
    }
}
