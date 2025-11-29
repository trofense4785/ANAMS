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
            // 1. Obter a lista de cursos filtrada pelo Controller
            // (O Controller já verifica a Sessão e aplica a lógica de Coordenador/Professor)
            List<String> meusCursos = controller.getMeusCursos();

            if (meusCursos.isEmpty()) {
                System.out.println("ℹ️ Não é responsável por nenhum curso ou módulo neste momento.");
                System.out.println("Pressione ENTER para voltar...");
                sc.nextLine();
                return;
            }

            // 2. Mostrar a Lista
            System.out.println("Cursos sob a sua responsabilidade:");
            for (int i = 0; i < meusCursos.size(); i++) {
                System.out.println((i + 1) + ". " + meusCursos.get(i));
            }

            // 3. Opção de ver detalhes
            int opcao = lerInteiro("Selecione um curso para ver detalhes (0 para sair): ");

            if (opcao > 0 && opcao <= meusCursos.size()) {
                // Extrair a sigla da linha selecionada para pedir detalhes
                String linha = meusCursos.get(opcao - 1);
                String sigla = extrairSigla(linha);

                // 4. Obter e mostrar detalhes
                String detalhes = controller.getDadosCurso(sigla);
                System.out.println("\n--- Detalhes do Curso ---");
                System.out.println(detalhes);
                System.out.println("-------------------------");
            }

        } catch (IllegalStateException e) {
            // Erro de Sessão (ninguém logado ou não é formador)
            System.out.println("\n❌ ERRO DE ACESSO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nPressione ENTER para voltar...");
        sc.nextLine();
    }

    // --- MÉTODOS AUXILIARES ---

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
     * Usa a lógica simples de split que aprendeste.
     */
    private String extrairSigla(String textoCurso) {
        try {
            // Divide onde está o fecho de parentesis "]"
            String[] partes = textoCurso.split("]");

            // Pega na primeira parte "[MAT" e remove o "["
            if (partes.length > 0) {
                return partes[0].replace("[", "").trim();
            }
        } catch (Exception e) {
            // Se falhar, não faz mal, o controller depois avisa que não encontrou
        }
        return "";
    }
}