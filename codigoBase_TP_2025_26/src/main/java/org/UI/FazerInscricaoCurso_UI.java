package org.UI;

import org.Controller.FazerInscricaoCurso_Controller;
import org.Model.Curso;

import java.util.List;
import java.util.Scanner;

public class FazerInscricaoCurso_UI {

    private final FazerInscricaoCurso_Controller controller;
    private final Scanner sc;

    public FazerInscricaoCurso_UI() {
        // Ao instanciar o controller, ele tenta logo obter o aluno da Sessão.
        // Se ninguém estiver logado, pode lançar exceção (tratada no run).
        this.controller = new FazerInscricaoCurso_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("       INSCREVER EM NOVO CURSO          ");
        System.out.println("========================================");

        try {
            // 1. Obter Cursos Disponíveis (Estado "A Iniciar")
            List<Curso> cursosDisponiveis = controller.getCursosDisponiveis();

            if (cursosDisponiveis.isEmpty()) {
                System.out.println("ℹ️ Não existem cursos com inscrições abertas neste momento.");
                System.out.println("Pressione ENTER para voltar...");
                sc.nextLine();
                return;
            }

            // 2. Mostrar Lista
            System.out.println("Selecione o curso que pretende frequentar:");
            for (int i = 0; i < cursosDisponiveis.size(); i++) {
                System.out.println((i + 1) + ". " + cursosDisponiveis.get(i).toString());
            }

            int opcao = lerInteiro("Opção (0 para cancelar): ");
            if (opcao == 0) return;

            if (opcao > 0 && opcao <= cursosDisponiveis.size()) {
                // 3. Selecionar o Curso no Controller
                Curso cursoEscolhido = cursosDisponiveis.get(opcao - 1);
                controller.selecionarCurso(cursoEscolhido);

                // 4. Confirmação
                System.out.println("\nVai inscrever-se no curso: " + cursoEscolhido.getTitulo());
                System.out.print("Confirma a inscrição? (S/N): ");

                if (sc.nextLine().equalsIgnoreCase("S")) {

                    // 5. Executar Inscrição
                    // O Controller delega no Aluno, que valida se já está inscrito e cria o registo
                    if (controller.confirmarInscricao()) {
                        System.out.println("\n✅ SUCESSO: Inscrição realizada!");
                        System.out.println("ℹ️ Pode consultar o estado em 'Minhas Inscrições'.");
                    } else {
                        System.out.println("\n❌ ERRO: Não foi possível realizar a inscrição.");
                        System.out.println("(Verifique se já se encontra inscrito neste curso).");
                    }
                } else {
                    System.out.println("\n⚠️ Operação cancelada.");
                }

            } else {
                System.out.println("Opção inválida.");
            }

        } catch (IllegalStateException e) {
            // Captura erros de sessão (ex: "Nenhum aluno autenticado")
            System.out.println("\n❌ ERRO DE SESSÃO: " + e.getMessage());
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