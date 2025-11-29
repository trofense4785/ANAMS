package org.UI;

import org.Controller.RegistarClassificacao_Controller;

import java.util.List;
import java.util.Scanner;

public class RegistarClassificacao_UI {

    private final RegistarClassificacao_Controller controller;
    private final Scanner sc;

    public RegistarClassificacao_UI() {
        this.controller = new RegistarClassificacao_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("      REGISTAR CLASSIFICAÇÃO (NOTA)     ");
        System.out.println("========================================");

        try {

            List<String> meusCursos = controller.getMeusCursos();

            if (meusCursos.isEmpty()) {
                System.out.println(" Não tem cursos associados para lançar notas.");
                return;
            }

            System.out.println("Selecione o Curso:");
            String siglaCurso = selecionarOpcaoDaLista(meusCursos);
            if (siglaCurso == null) return; // Cancelado

            controller.selecionarCurso(siglaCurso);

            List<String> meusModulos = controller.getMeusModulos();

            if (meusModulos.isEmpty()) {
                System.out.println(" Não é responsável por nenhum módulo neste curso.");
                return;
            }

            System.out.println("\nSelecione o Módulo:");

            String codigoModulo = selecionarOpcaoDaLista(meusModulos);

            if (codigoModulo != null && codigoModulo.contains(" - ")) {
                codigoModulo = codigoModulo.split(" - ")[0];
            }

            if (codigoModulo == null) return;

            controller.selecionarModulo(codigoModulo);


            List<String> alunos = controller.getAlunosInscritos();

            if (alunos.isEmpty()) {
                System.out.println(" Não existem alunos inscritos neste curso.");
                return;
            }

            System.out.println("\nSelecione o Aluno:");

            String stringAluno = selecionarOpcaoDaLista(alunos, false); // false = retorna a string toda
            if (stringAluno == null) return;

            String emailAluno = extrairEmail(stringAluno);
            controller.selecionarAluno(emailAluno);


            boolean notaValida = false;
            while (!notaValida) {
                double nota = lerDouble("\nIntroduza a Nota (0 a 20): ");


                if (controller.validarNota(nota)) {
                    notaValida = true;
                } else {
                    System.out.println(" Nota inválida. Deve ser entre 0 e 20.");
                }
            }


            System.out.println("\n--- Resumo do Lançamento ---");
            System.out.println(controller.getResumoConfirmacao());

            System.out.print("Confirma o registo desta nota? (S/N): ");
            if (sc.nextLine().equalsIgnoreCase("S")) {

                if (controller.confirmarRegisto()) {
                    System.out.println("\n SUCESSO: Classificação registada.");
                } else {
                    System.out.println("\n ERRO: Falha ao registar a nota.");
                }
            } else {
                System.out.println("\n Operação cancelada.");
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



    private String selecionarOpcaoDaLista(List<String> lista) {
        return selecionarOpcaoDaLista(lista, true);
    }

    private String selecionarOpcaoDaLista(List<String> lista, boolean extrairCodigo) {
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i));
        }

        int op = lerInteiro("Opção (0 para sair): ");
        if (op == 0) return null;

        if (op > 0 && op <= lista.size()) {
            String texto = lista.get(op - 1);
            if (extrairCodigo) {
                return extrairSigla(texto);
            }
            return texto;
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

    private double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                String input = sc.nextLine().replace(",", "."); // Aceita vírgula ou ponto
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Valor numérico inválido.");
            }
        }
    }

    private String extrairSigla(String texto) {
        if (texto.startsWith("[")) {
            int fim = texto.indexOf("]");
            if (fim != -1) return texto.substring(1, fim);
        }

        return texto;
    }

    private String extrairEmail(String textoAluno) {
        try {
            int inicio = textoAluno.lastIndexOf("(");
            int fim = textoAluno.lastIndexOf(")");
            if (inicio != -1 && fim != -1) {
                return textoAluno.substring(inicio + 1, fim);
            }
        } catch (Exception e) { }
        return textoAluno;
    }
}