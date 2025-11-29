package org.UI;

import org.Controller.ConsultarListaAlunosCurso_Controller;
import org.Model.Curso;

import java.util.List;
import java.util.Scanner;

public class ConsultarListaAlunosCurso_UI {

    private final ConsultarListaAlunosCurso_Controller controller;
    private final Scanner sc;

    public ConsultarListaAlunosCurso_UI() {
        this.controller = new ConsultarListaAlunosCurso_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("    CONSULTAR ALUNOS DO MEU CURSO       ");
        System.out.println("========================================");

        try {
            List<String> meusCursos = controller.getMeusCursos();

            if (meusCursos.isEmpty()) {
                System.out.println(" Não está associado a nenhum curso neste momento.");
                System.out.println("Pressione ENTER para voltar...");
                sc.nextLine();
                return;
            }

            System.out.println("Selecione o curso para ver a turma:");
            for (int i = 0; i < meusCursos.size(); i++) {
                System.out.println((i + 1) + ". " + meusCursos.get(i));
            }

            int opcao = lerInteiro("Opção (0 para cancelar): ");
            if (opcao == 0) return;

            if (opcao > 0 && opcao <= meusCursos.size()) {
                String linhaCurso = meusCursos.get(opcao - 1);
                String sigla = extrairSigla(linhaCurso);

                controller.selecionarCurso(sigla);

                List<String> listaAlunos = controller.getListaAlunos();

                System.out.println("\n--- Lista de Alunos (" + sigla + ") ---");
                if (listaAlunos.isEmpty()) {
                    System.out.println(" Não existem alunos inscritos (ou histórico).");
                } else {
                    for (String alunoStr : listaAlunos) {
                        System.out.println("- " + alunoStr);
                    }
                    System.out.println("Total: " + listaAlunos.size() + " alunos.");
                }
            } else {
                System.out.println("Opção inválida.");
            }

        } catch (IllegalStateException e) {
            System.out.println("\n ERRO DE ACESSO: " + e.getMessage());
        } catch (SecurityException e) {
            System.out.println("\n SEGURANÇA: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nPressione ENTER para voltar...");
        sc.nextLine();
    }


    private int lerInteiro(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Número inválido.");
            }
        }
    }

    private String extrairSigla(String textoCurso) {
        try {
            int inicio = textoCurso.indexOf("(");
            int fim = textoCurso.indexOf(")");

            if (inicio != -1 && fim != -1 && fim > inicio) {
                return textoCurso.substring(inicio + 1, fim).trim();
            }
        } catch (Exception e) {
            System.out.println("Erro ao extrair sigla: " + e.getMessage());
        }
        return "";
    }

}