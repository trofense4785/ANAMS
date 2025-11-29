package org.UI;

import java.io.IOException;
import java.util.Scanner;

import org.Model.CA;
import org.Model.Sessao;
import utils.Utils;
import org.Model.Instituicao;

public class MenuCA_UI {

    private Instituicao instituicao;
    private Scanner sc;

    public MenuCA_UI(Instituicao instituicao) {
        this.instituicao = instituicao;
        this.sc = new Scanner(System.in);
    }

    public void run() {
        if (!fazerLogin()) {
            return;
        }


        String opcao;
        do {
            System.out.println("\n###### MENU COORDENADOR ACADÉMICO ######");
            System.out.println("--- Gestão de Estrutura ---");
            System.out.println("1. Especificar Tipo de Curso (UC2)");
            System.out.println("2. Registar Formador (UC3)");
            System.out.println("--- Gestão de Cursos ---");
            System.out.println("3. Registar Novo Curso (UC4)");
            System.out.println("4. Adicionar Módulo a Curso (UC5)");
            System.out.println("5. Consultar Lista de Cursos (UC6)");
            System.out.println("6. Alterar Estado de Curso (UC13)");
            System.out.println("--- Gestão de Candidaturas ---");
            System.out.println("7. Decidir Candidaturas Pendentes (UC8)");
            System.out.println("----------------------------------------");
            System.out.println("0. Logout / Voltar");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    new EspecificarTipoCurso_UI().run();
                    break;
                case "2":
                    new RegistarFormador_UI().run();
                    break;
                case "3":
                    new RegistarCurso_UI().run();
                    break;
                case "4":
                    new AdicionarModuloCurso_UI().run();
                    break;
                case "5":
                    new ConsultarListaCursos_UI().run();
                    break;
                case "6":
                    new AlterarEstadoCurso_UI().run();
                    break;
                case "7":
                    new DecisaoCandidatura_UI().run();
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

    private boolean fazerLogin() {
        CA ca = instituicao.getCA();

        if (ca == null) {
            System.out.println(" ERRO: Ainda não existe um Coordenador Académico registado no sistema.");
            System.out.println("Peça ao Administrador para realizar o registo (UC1).");
            return false;
        }

        System.out.println("\n--- Login Coordenador Académico ---");
        System.out.print("Username: ");
        String user = sc.nextLine();
        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (ca.getCredenciais() != null &&
                ca.getCredenciais().getUsername().equals(user) &&
                ca.getCredenciais().getPassword().equals(pass)) {

            Sessao.getInstance().login(ca.getEmail());
            System.out.println(" Bem-vindo, " + ca.getNome() + "!");
            return true;
        }

        System.out.println(" Credenciais inválidas.");
        return false;
    }
}

