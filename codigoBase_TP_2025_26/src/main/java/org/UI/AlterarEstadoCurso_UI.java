package org.UI;

import org.Controller.AlterarEstadoCurso_Controller;
import org.Model.Curso;
import org.Model.EstadoCurso;

import java.util.List;
import java.util.Scanner;

public class AlterarEstadoCurso_UI {

    private final AlterarEstadoCurso_Controller controller;
    private final Scanner sc;

    public AlterarEstadoCurso_UI() {
        this.controller = new AlterarEstadoCurso_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("       ALTERAR ESTADO DE UM CURSO       ");
        System.out.println("========================================");

        try {
            // 1. Obter Lista de Cursos
            List<Curso> cursos = controller.obterListaCursos();

            if (cursos.isEmpty()) {
                System.out.println("ℹ️ Não existem cursos registados no sistema.");
                System.out.println("Pressione ENTER para voltar...");
                sc.nextLine();
                return;
            }

            // 2. Apresentar Cursos
            System.out.println("Selecione o curso para alterar o estado:");
            for (int i = 0; i < cursos.size(); i++) {
                // Usa o toString() do curso que mostra "Titulo (Sigla) | Estado"
                System.out.println((i + 1) + ". " + cursos.get(i).toString());
            }

            int opcao = lerInteiro("Opção (0 para cancelar): ");
            if (opcao == 0) return;

            if (opcao > 0 && opcao <= cursos.size()) {
                // 3. Selecionar o Curso no Controller
                Curso cursoEscolhido = cursos.get(opcao - 1);

                // O controller guarda o curso e devolve o estado atual
                EstadoCurso estadoAtual = controller.selecionarCurso(cursoEscolhido.getSigla());

                System.out.println("\n--- Estado Atual: " + estadoAtual + " ---");

                // 4. Escolher Novo Estado
                // Nota: O controller deve ter o método getEstadosPossiveis() que retorna o array/lista
                EstadoCurso novoEstado = selecionarNovoEstado();

                if (novoEstado == null) return; // Cancelou na escolha de estado

                // 5. Confirmação
                System.out.println("\nVai alterar o estado de: " + estadoAtual);
                System.out.println("Para: " + novoEstado);
                System.out.print("Confirma a alteração? (S/N): ");

                if (sc.nextLine().equalsIgnoreCase("S")) {

                    // 6. Executar Alteração
                    // O método setEstado() no Modelo vai validar se a transição é permitida
                    controller.alterarEstado(novoEstado);

                    System.out.println("\n✅ SUCESSO: O estado do curso foi atualizado.");
                } else {
                    System.out.println("\n⚠️ Operação cancelada.");
                }

            } else {
                System.out.println("Opção inválida.");
            }

        } catch (IllegalStateException e) {
            // Captura erros de regra de negócio (ex: tentar ativar um curso cancelado)
            System.out.println("\n❌ ERRO DE REGRA: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nPressione ENTER para voltar...");
        sc.nextLine();
    }

    // --- Métodos Auxiliares ---

    private EstadoCurso selecionarNovoEstado() {
        // Obtém as opções do Enum ou Classe EstadoCurso
        EstadoCurso[] estados = controller.getEstadosPossiveis();

        System.out.println("\nSelecione o NOVO estado:");
        for (int i = 0; i < estados.length; i++) {
            System.out.println((i + 1) + ". " + estados[i].toString());
        }

        int op = lerInteiro("Opção: ");
        if (op > 0 && op <= estados.length) {
            return estados[op - 1];
        }
        System.out.println("Opção inválida.");
        return null;
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