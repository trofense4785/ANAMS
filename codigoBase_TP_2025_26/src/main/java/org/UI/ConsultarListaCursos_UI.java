package org.UI;

import org.Controller.ConsultarListaCursos_Controller;
import org.Model.Curso;
import org.Model.EstadoCurso;

import java.util.List;
import java.util.Scanner;

public class ConsultarListaCursos_UI {

    private final ConsultarListaCursos_Controller controller;
    private final Scanner sc;

    public ConsultarListaCursos_UI() {
        this.controller = new ConsultarListaCursos_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("       CONSULTAR LISTA DE CURSOS        ");
        System.out.println("========================================");

        try {
            List<EstadoCurso> estados = controller.getTodosEstadosPossiveis();

            System.out.println("Selecione o estado dos cursos que deseja visualizar:");
            System.out.println("0. Todos (Sem filtro)");
            for (int i = 0; i < estados.size(); i++) {
                System.out.println((i + 1) + ". " + estados.get(i).toString());
            }

            int opcao = lerInteiro("Opção: ");

            EstadoCurso estadoSelecionado = null;// null = Todos

            if (opcao > 0 && opcao <= estados.size()) {
                estadoSelecionado = estados.get(opcao - 1);

            } else if (opcao != 0) {
                System.out.println(" Opção inválida. A mostrar todos por defeito.");
            }

            List<Curso> listaCursos = controller.getLstCursos(estadoSelecionado);

            System.out.println("\n--- Resultados da Pesquisa ---");
            if (listaCursos.isEmpty()) {
                System.out.println(" Não foram encontrados cursos com o critério selecionado.");
            } else {
                for (Curso c : listaCursos) {
                    System.out.println(c.toString());
                }
                System.out.println("Total: " + listaCursos.size() + " cursos encontrados.");
            }

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
                System.out.println("Por favor, introduza um número válido.");
            }
        }
    }
}
