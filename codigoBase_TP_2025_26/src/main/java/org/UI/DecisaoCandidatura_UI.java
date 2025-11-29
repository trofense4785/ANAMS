package org.UI;

import org.Controller.DecisaoCandidatura_Controller;
import org.Model.Candidatura;

import java.util.List;
import java.util.Scanner;

public class DecisaoCandidatura_UI {

    private final DecisaoCandidatura_Controller controller;
    private final Scanner sc;

    public DecisaoCandidatura_UI() {
        this.controller = new DecisaoCandidatura_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("      TOMAR DECISÃO SOBRE CANDIDATURA   ");
        System.out.println("========================================");

        try {
            // 1. Obter lista de pendentes
            List<Candidatura> pendentes = controller.getListaCandidaturasPendentes();

            if (pendentes.isEmpty()) {
                System.out.println("ℹ️ Não existem candidaturas pendentes para análise.");
                System.out.println("Pressione ENTER para voltar...");
                sc.nextLine();
                return;
            }

            // 2. Mostrar Lista
            System.out.println("Selecione a candidatura para avaliar:");
            for (int i = 0; i < pendentes.size(); i++) {
                System.out.println((i + 1) + ". " + pendentes.get(i).toString());
            }

            int opcao = lerInteiro("Opção (0 para cancelar): ");
            if (opcao == 0) return;

            if (opcao > 0 && opcao <= pendentes.size()) {
                // 3. Selecionar
                Candidatura candSelecionada = pendentes.get(opcao - 1);
                controller.selecionarCandidatura(candSelecionada);

                // 4. Mostrar Detalhes Completos
                System.out.println("\n--- Detalhes do Candidato ---");
                System.out.println(controller.getDadosCandidatura());
                System.out.println("-----------------------------");

                // 5. Tomar Decisão
                System.out.println("Qual a sua decisão?");
                System.out.println("1. ACEITAR (Criar Aluno)");
                System.out.println("2. REJEITAR");
                int decisao = lerInteiro("Opção: ");

                boolean aceitar = (decisao == 1);

                // 6. Pedir Justificação (Obrigatório)
                System.out.print("Introduza a justificação para a decisão: ");
                String justificacao = sc.nextLine();

                // 7. Confirmação
                String textoDecisao = aceitar ? "ACEITAR" : "REJEITAR";
                System.out.print("Confirma " + textoDecisao + " esta candidatura? (S/N): ");

                if (sc.nextLine().equalsIgnoreCase("S")) {
                    // 8. Registar (O Controller trata de criar o Aluno se for aceite)
                    if (controller.registarDecisao(aceitar, justificacao)) {
                        System.out.println("\n✅ SUCESSO: Decisão registada.");
                        if (aceitar) {
                            System.out.println("ℹ️ O Candidato foi convertido em ALUNO e recebeu o código.");
                        }
                        System.out.println("📧 Email de notificação enviado.");
                    } else {
                        System.out.println("\n❌ ERRO: Não foi possível registar a decisão.");
                    }
                } else {
                    System.out.println("\n⚠️ Operação cancelada.");
                }

            } else {
                System.out.println("Opção inválida.");
            }

        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro: " + e.getMessage());
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
}