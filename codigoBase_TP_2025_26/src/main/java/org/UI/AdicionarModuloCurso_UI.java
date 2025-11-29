package org.UI;



import org.Controller.AdicionarModuloCurso_Controller;
import org.Model.Curso;
import org.Model.Formador;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AdicionarModuloCurso_UI {

    private final AdicionarModuloCurso_Controller controller;
    private final Scanner sc;

    public AdicionarModuloCurso_UI() {
        this.controller = new AdicionarModuloCurso_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("      ADICIONAR MÓDULO A UM CURSO       ");
        System.out.println("========================================");

        try {
            // --- PASSO 1: SELECIONAR CURSO ---
            if (!selecionarCurso()) return;

            // --- PASSO 2: DADOS BÁSICOS DO MÓDULO ---
            recolherDadosBasicos();

            // --- PASSO 3: DEFINIR SESSÕES (Horário) ---
            // O sistema valida salas ocupadas aqui
            recolherSessoes();

            // --- PASSO 4: SELECIONAR FORMADOR ---
            // O sistema valida sobreposição de horários aqui
            if (!selecionarFormador()) return;

            // --- PASSO 5: CONFIRMAÇÃO FINAL ---
            System.out.println("\n--- Resumo do Módulo ---");
            System.out.println("Curso: " + controller.getCursoSelecionado().getTitulo());
            System.out.println("Módulo pronto a gravar.");

            System.out.print("Confirma a adição deste módulo? (S/N): ");
            if (sc.nextLine().equalsIgnoreCase("S")) {
                if (controller.adicionarModuloAoCurso()) {
                    System.out.println("\n✅ SUCESSO: Módulo adicionado ao curso.");
                } else {
                    System.out.println("\n❌ ERRO: Falha na gravação (Verifique ponderações ou regras do curso).");
                }
            } else {
                System.out.println("\n⚠️ Operação cancelada.");
            }

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("\n❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nPressione ENTER para voltar...");
        sc.nextLine();
    }

    // --- MÉTODOS AUXILIARES ---

    private boolean selecionarCurso() {
        List<Curso> cursos = controller.getCursosDisponiveis(); // Só mostra "A Iniciar"

        if (cursos.isEmpty()) {
            System.out.println("ℹ️ Não existem cursos disponíveis para adicionar módulos (Estado 'A Iniciar').");
            return false;
        }

        System.out.println("\nSelecione o Curso:");
        for (int i = 0; i < cursos.size(); i++) {
            System.out.println((i + 1) + ". " + cursos.get(i).toString());
        }

        int op = lerInteiro("Opção: ");
        if (op > 0 && op <= cursos.size()) {
            controller.selecionarCurso(cursos.get(op - 1));
            return true;
        }
        System.out.println("Opção inválida.");
        return false;
    }

    private void recolherDadosBasicos() {
        System.out.println("\n--- Dados do Novo Módulo ---");
        System.out.print("Título: ");
        String titulo = sc.nextLine();

        double cargaHoraria = lerDouble("Carga Horária: ");
        double ponderacao = lerDouble("Ponderação (0-100%): ");

        LocalDate dataInicio = lerData("Data Início (AAAA-MM-DD): ");
        LocalDate dataFim = lerData("Data Fim (AAAA-MM-DD): ");

        // Cria o módulo provisório no Controller
        controller.criarModulo(titulo, cargaHoraria, dataInicio, dataFim, ponderacao);
    }

    private void recolherSessoes() {
        System.out.println("\n--- Agendamento de Sessões (Mínimo 3) ---");
        int contador = 0;
        boolean continuar = true;

        while (continuar || contador < 3) {
            if (contador >= 3) {
                System.out.print("Adicionar outra sessão? (S/N): ");
                if (!sc.nextLine().equalsIgnoreCase("S")) break;
            }

            try {
                System.out.println(">> Sessão #" + (contador + 1));
                LocalDate dia = lerData("Dia: ");
                int hora = lerInteiro("Hora de início (0-23): ");
                int duracao = lerInteiro("Duração (horas): ");
                System.out.print("Sala: ");
                String sala = sc.nextLine();

                LocalDateTime dataHora = dia.atTime(hora, 0);

                // Tenta adicionar ao controller -> Ele valida se a sala está livre!
                controller.adicionarSessao(dataHora, duracao, sala);
                System.out.println("   Sessão registada.");
                contador++;

            } catch (IllegalArgumentException e) {
                System.out.println("   ❌ Erro na sessão: " + e.getMessage());
                System.out.println("   Tente novamente.");
            }
        }
    }

    private boolean selecionarFormador() {
        List<Formador> formadores = controller.getLstFormadores();
        if (formadores.isEmpty()) {
            System.out.println("❌ Não existem formadores registados no sistema.");
            return false;
        }

        System.out.println("\n--- Selecionar Formador Responsável ---");
        for (int i = 0; i < formadores.size(); i++) {
            System.out.println((i + 1) + ". " + formadores.get(i).getNome());
        }

        while (true) {
            int op = lerInteiro("Opção (0 para cancelar): ");
            if (op == 0) return false;

            if (op > 0 && op <= formadores.size()) {
                Formador f = formadores.get(op - 1);
                try {
                    // Tenta definir o formador -> O Controller valida sobreposição de horário!
                    controller.definirFormador(f);
                    return true;
                } catch (IllegalArgumentException e) {
                    System.out.println("❌ " + e.getMessage());
                    System.out.println("Por favor, escolha outro formador.");
                }
            } else {
                System.out.println("Opção inválida.");
            }
        }
    }

    // --- Utilitários de Leitura (Iguais aos anteriores) ---

    private LocalDate lerData(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida.");
            }
        }
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
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido.");
            }
        }
    }
}