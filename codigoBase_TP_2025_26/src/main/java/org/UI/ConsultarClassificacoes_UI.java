package org.UI;

import org.Controller.ConsultarClassificacoes_Controller;

import java.util.List;
import java.util.Scanner;

public class ConsultarClassificacoes_UI {

    private final ConsultarClassificacoes_Controller controller;
    private final Scanner sc;

    public ConsultarClassificacoes_UI() {
        this.controller = new ConsultarClassificacoes_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("      CONSULTAR AS MINHAS NOTAS         ");
        System.out.println("========================================");

        try {

            List<String> meusCursos = controller.getMeusCursos();

            if (meusCursos.isEmpty()) {
                System.out.println(" Não está inscrito em nenhum curso.");
                System.out.println("Pressione ENTER para voltar...");
                sc.nextLine();
                return;
            }

            System.out.println("Selecione o curso para ver as notas:");
            for (int i = 0; i < meusCursos.size(); i++) {
                System.out.println((i + 1) + ". " + meusCursos.get(i));
            }

            int opcao = lerInteiro("Opção (0 para cancelar): ");
            if (opcao == 0) return;

            if (opcao > 0 && opcao <= meusCursos.size()) {
                String linhaCurso = meusCursos.get(opcao - 1);
                String sigla = extrairSigla(linhaCurso);

                List<String> boletim = controller.consultarNotas(sigla);

                System.out.println("\n--- Boletim de Classificações (" + sigla + ") ---");

                if (boletim.isEmpty()) {
                    System.out.println(" Sem informação disponível.");
                } else {
                    for (String linha : boletim) {
                        System.out.println(linha);
                    }
                }

                System.out.println("----------------------------------------------");

            } else {
                System.out.println("Opção inválida.");
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
            String[] partes = textoCurso.split("]");
            if (partes.length > 0) {
                return partes[0].replace("[", "").trim();
            }
        } catch (Exception e) {

        }
        return "";
    }
}