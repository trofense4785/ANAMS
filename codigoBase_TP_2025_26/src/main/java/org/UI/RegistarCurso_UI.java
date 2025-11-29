package org.UI;


import org.Controller.RegistarCurso_Controller;
import org.Model.Formador;
import org.Model.Instituicao;
import org.Model.TipoCurso;
import org.Model.SessaoModulo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistarCurso_UI {

    private final RegistarCurso_Controller controller;
    private final Scanner sc;

    public RegistarCurso_UI() {
        this.controller = new RegistarCurso_Controller(Instituicao.getInstance());
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("           REGISTAR NOVO CURSO          ");
        System.out.println("========================================");

        try {
            // --- FASE 1: DADOS GERAIS DO CURSO ---
            System.out.print("Título do Curso: ");
            String titulo = sc.nextLine();

            System.out.print("Sigla (Única): ");
            String sigla = sc.nextLine();

            System.out.print("Descrição: ");
            String descricao = sc.nextLine();

            LocalDate dataInicio = lerData("Data de Início (AAAA-MM-DD): ");
            LocalDate dataFim = lerData("Data de Fim (AAAA-MM-DD): ");

            // Seleção do Tipo de Curso
            TipoCurso tipoSelecionado = selecionarTipoCurso();
            if (tipoSelecionado == null) return; // Cancelou ou não existem tipos

            // Cria o cabeçalho do curso em memória
            controller.novoCurso(titulo, sigla, tipoSelecionado, descricao, dataInicio, dataFim);
            System.out.println(">> Cabeçalho do curso criado com sucesso.");

            // --- FASE 2: ADICIONAR RESPONSÁVEIS (IT2) ---
            adicionarResponsaveisCurso();

            // --- FASE 3: ADICIONAR MÓDULOS (LOOP) ---
            boolean continuar = true;
            while (continuar) {
                System.out.println("\n--- Adicionar Módulo ---");
                adicionarModulo();

                System.out.print("\nDeseja adicionar outro módulo? (S/N): ");
                String resp = sc.nextLine();
                if (!resp.equalsIgnoreCase("S")) {
                    continuar = false;
                }
            }

            // --- FASE 4: CONFIRMAÇÃO FINAL ---
            System.out.println("\n--- Resumo do Registo ---");
            System.out.println("Curso: " + titulo + " (" + sigla + ")");
            System.out.println("Módulos adicionados. Validação de estado pendente.");

            System.out.print("Confirma o registo do curso? (S/N): ");
            if (sc.nextLine().equalsIgnoreCase("S")) {
                if (controller.registarCurso()) {
                    System.out.println("\n✅ SUCESSO: Curso registado e pronto a iniciar.");
                } else {
                    System.out.println("\n❌ ERRO: Falha ao registar (Verifique se tem pelo menos 1 módulo).");
                }
            } else {
                System.out.println("\n⚠️ Operação cancelada.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // --- MÉTODOS AUXILIARES ---

    private TipoCurso selecionarTipoCurso() {
        List<TipoCurso> tipos = controller.getTiposCurso();
        if (tipos.isEmpty()) {
            System.out.println("❌ Erro: Não existem Tipos de Curso definidos. Crie um primeiro (UC2).");
            return null;
        }

        System.out.println("\nSelecione o Tipo de Curso:");
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println((i + 1) + ". " + tipos.get(i).toString());
        }

        int opcao = lerInteiro("Opção: ");
        if (opcao > 0 && opcao <= tipos.size()) {
            return tipos.get(opcao - 1);
        }
        System.out.println("Opção inválida.");
        return null;
    }

    private void adicionarResponsaveisCurso() {
        System.out.println("\n--- Definir Coordenadores/Responsáveis do Curso ---");
        boolean adicionar = true;
        while (adicionar) {
            Formador f = selecionarFormador("Selecione um Responsável:");
            if (f != null) {
                controller.adicionarFormadorResponsavel(f);
                System.out.println(">> Responsável adicionado.");
            }

            System.out.print("Adicionar outro responsável? (S/N): ");
            if (!sc.nextLine().equalsIgnoreCase("S")) adicionar = false;
        }
    }

    private void adicionarModulo() {
        try {
            System.out.print("Título do Módulo: ");
            String titulo = sc.nextLine();

            double cargaHoraria = lerDouble("Carga Horária Total: ");
            double ponderacao = lerDouble("Ponderação (0-100%): ");

            LocalDate dataInicio = lerData("Data Início Módulo: ");
            LocalDate dataFim= lerData("Data Fim Módulo: ");

            // Selecionar Formador do Módulo
            Formador formadorResponsavel = selecionarFormador("Selecione o Formador do Módulo:");
            if (formadorResponsavel == null) return;

            // Recolher Sessões (Requisito: Minimo 3)
            List<SessaoModulo> lstSessoes = recolherSessoes();

            // Enviar para o Controller
            // Nota: O código sequencial é gerado automaticamente no Controller, não pedimos aqui
            boolean sucesso = controller.adicionarModulo(
                    titulo, cargaHoraria, dataInicio, dataFim, formadorResponsavel, ponderacao, lstSessoes
            );

            if (sucesso) System.out.println(">> Módulo adicionado com sucesso.");

        } catch (Exception e) {
            System.out.println("❌ Erro ao adicionar módulo: " + e.getMessage());
        }
    }

    private List<SessaoModulo> recolherSessoes() {
        List<SessaoModulo> lista = new ArrayList<>();
        System.out.println(">> Definição de Sessões (Mínimo 3 obrigatório)");

        boolean continuar = true;
        while (continuar || lista.size() < 3) {
            if (lista.size() >= 3) {
                System.out.print("Deseja adicionar mais sessões? (S/N): ");
                if (!sc.nextLine().equalsIgnoreCase("S")) break;
            }

            System.out.println("SESSION #" + (lista.size() + 1));
            LocalDate dia = lerData("Dia da aula: ");
            int hora = lerInteiro("Hora (0-23): ");
            int duracao = lerInteiro("Duração (horas): ");
            System.out.print("Sala: ");
            String sala = sc.nextLine();

            LocalDateTime dataHora = dia.atTime(hora, 0);
            lista.add(new SessaoModulo(dataHora, duracao, sala));
        }
        return lista;
    }

    private Formador selecionarFormador(String msg) {
        List<Formador> lista = controller.getListaFormadores();
        if (lista.isEmpty()) {
            System.out.println("❌ Não existem formadores registados.");
            return null;
        }
        System.out.println("\n" + msg);
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).getNome() + " (" + lista.get(i).getEmail() + ")");
        }
        int op = lerInteiro("Opção: ");
        if (op > 0 && op <= lista.size()) return lista.get(op - 1);
        return null;
    }

    // Utilitários de Leitura
    private LocalDate lerData(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use AAAA-MM-DD.");
            }
        }
    }

    private int lerInteiro(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro.");
            }
        }
    }

    private double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numérico (use ponto para decimais).");
            }
        }
    }
}