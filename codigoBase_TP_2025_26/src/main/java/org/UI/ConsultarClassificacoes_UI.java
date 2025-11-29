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
            // 1. Obter Cursos onde o aluno está inscrito
            // O controller vai buscar o aluno à Sessão automaticamente
            List<String> meusCursos = controller.getMeusCursos();

            if (meusCursos.isEmpty()) {
                System.out.println("ℹ️ Não está inscrito em nenhum curso.");
                System.out.println("Pressione ENTER para voltar...");
                sc.nextLine();
                return;
            }

            // 2. Apresentar Lista de Cursos
            System.out.println("Selecione o curso para ver as notas:");
            for (int i = 0; i < meusCursos.size(); i++) {
                System.out.println((i + 1) + ". " + meusCursos.get(i));
            }

            int opcao = lerInteiro("Opção (0 para cancelar): ");
            if (opcao == 0) return;

            if (opcao > 0 && opcao <= meusCursos.size()) {
                // 3. Obter Sigla do Curso selecionado
                String linhaCurso = meusCursos.get(opcao - 1);
                String sigla = extrairSigla(linhaCurso);

                // 4. Pedir o Boletim de Notas ao Controller
                List<String> boletim = controller.consultarNotas(sigla);

                // 5. Apresentar Resultados
                System.out.println("\n--- Boletim de Classificações (" + sigla + ") ---");

                if (boletim.isEmpty()) {
                    System.out.println("ℹ️ Sem informação disponível.");
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
            // Erro de Sessão (Ex: tentar aceder sem login ou login de Formador)
            System.out.println("\n❌ ERRO DE ACESSO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nPressione ENTER para voltar...");
        sc.nextLine();
    }

    // --- Métodos Auxiliares ---

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

    /**
     * Extrai a sigla da string formatada (ex: "[MAT] Matematica" -> "MAT")
     */
    private String extrairSigla(String textoCurso) {
        try {
            String[] partes = textoCurso.split("]");
            if (partes.length > 0) {
                return partes[0].replace("[", "").trim();
            }
        } catch (Exception e) {
            // Ignora
        }
        return "";
    }
}