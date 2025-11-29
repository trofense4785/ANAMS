package org.UI;

import org.Controller.ConsultarListaCursosResponsavel_Controller;

import java.util.List;
import java.util.Scanner;

public class ConsultarListaCursosResponsavel_UI {

    private final ConsultarListaCursosResponsavel_Controller controller;
    private final Scanner sc;

    public ConsultarListaCursosResponsavel_UI() {
        this.controller = new ConsultarListaCursosResponsavel_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("    CONSULTAR CURSOS (MINHA RESP.)      ");
        System.out.println("========================================");

        try {

            List<String> meusCursos = controller.getMeusCursos();

            if (meusCursos.isEmpty()) {
                System.out.println("ℹ️ Não é responsável por nenhum curso ou módulo neste momento.");
                System.out.println("Pressione ENTER para voltar...");
                sc.nextLine();
                return;
            }

            System.out.println("Cursos sob a sua responsabilidade:");
            for (int i = 0; i < meusCursos.size(); i++) {
                System.out.println((i + 1) + ". " + meusCursos.get(i));
            }

            int opcao = lerInteiro("Selecione um curso para ver detalhes (0 para sair): ");

            if (opcao > 0 && opcao <= meusCursos.size()) {
                String linha = meusCursos.get(opcao - 1);
                String sigla = extrairSigla(linha);

                String detalhes = controller.getDadosCurso(sigla);
                System.out.println("\n--- Detalhes do Curso ---");
                System.out.println(detalhes);
                System.out.println("-------------------------");
            }

        } catch (IllegalStateException e) {
            System.out.println("\n ERRO DE ACESSO: " + e.getMessage());
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
            int inicio = textoCurso.lastIndexOf("(");
            int fim = textoCurso.lastIndexOf(")");

            if (inicio != -1 && fim != -1 && inicio < fim) {
                return textoCurso.substring(inicio + 1, fim);
            }
        } catch (Exception e) {
            System.out.println("Erro ao ler sigla: " + e.getMessage());
        }
        return "";
    }
}