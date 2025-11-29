package org.UI;

import org.Controller.AnularInscricao_Controller;

import java.util.List;
import java.util.Scanner;

public class AnularInscricao_UI {

    private final AnularInscricao_Controller controller;
    private final Scanner sc;

    public AnularInscricao_UI() {
        this.controller = new AnularInscricao_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("       ANULAR INSCRIÇÃO EM CURSO        ");
        System.out.println("========================================");

        try {

            controller.iniciarAnulacao();

            List<String> meusCursos = controller.getListaCursosInscritos();

            if (meusCursos.isEmpty()) {
                System.out.println(" Não tem inscrições ativas para anular.");
                System.out.println("Pressione ENTER para voltar...");
                sc.nextLine();
                return;
            }

            System.out.println("Selecione a inscrição que deseja anular:");
            for (int i = 0; i < meusCursos.size(); i++) {
                System.out.println((i + 1) + ". " + meusCursos.get(i));
            }

            int opcao = lerInteiro("Opção (0 para cancelar): ");
            if (opcao == 0) return;

            if (opcao > 0 && opcao <= meusCursos.size()) {
                String linhaSelecionada = meusCursos.get(opcao - 1);
                String sigla = extrairSigla(linhaSelecionada);

                controller.selecionarCurso(sigla);

                System.out.println("\n--- Atenção ---");
                System.out.println("Vai anular a inscrição no curso: " + controller.getDadosCurso());
                System.out.println("Esta ação é irreversível e ficará registada no histórico.");

                System.out.print("Tem a certeza? (S/N): ");
                if (sc.nextLine().equalsIgnoreCase("S")) {

                    if (controller.registaAnulacao()) {
                        System.out.println("\n SUCESSO: Inscrição anulada.");
                        System.out.println(" O estado passou para 'Cancelada'.");
                    } else {
                        System.out.println("\n ERRO: Não foi possível anular a inscrição.");
                    }
                } else {
                    System.out.println("\n Operação cancelada.");
                }

            } else {
                System.out.println("Opção inválida.");
            }

        } catch (IllegalStateException e) {
            System.out.println("\n ERRO DE SESSÃO: " + e.getMessage());
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

        String[] partes = textoCurso.split("]");


        String siglaComParentesis = partes[0];


        String siglaLimpa = siglaComParentesis.replace("[", "");

        return siglaLimpa.trim();
    }
}