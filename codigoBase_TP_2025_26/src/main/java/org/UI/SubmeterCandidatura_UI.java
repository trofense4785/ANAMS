package org.UI;

import org.Controller.SubmeterCandidatura_Controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class SubmeterCandidatura_UI {

    private final SubmeterCandidatura_Controller controller;
    private final Scanner sc;

    public SubmeterCandidatura_UI() {
        this.controller = new SubmeterCandidatura_Controller();
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("         SUBMETER CANDIDATURA           ");
        System.out.println("========================================");
        System.out.println("Bem-vindo! Preencha os dados para se candidatar.");

        try {
            // 1. Solicitar dados pessoais (Requisitos IT1/IT2)
            System.out.print("Nome Completo: ");
            String nome = sc.nextLine();

            System.out.print("Número de CC (9 dígitos): ");
            String cc = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Contacto: ");
            String contacto = sc.nextLine();

            System.out.print("Habilitações: ");
            String habilitacoes = sc.nextLine();

            System.out.print("Género (M/F/Outro): ");
            String genero = sc.nextLine();

            LocalDate dataNascimento = lerData("Data de Nascimento (AAAA-MM-DD): ");

            // 2. Enviar para o Controller (Criação em memória)
            // O Controller valida logo se o CC ou Email já existem no sistema
            controller.novaCandidatura(nome, dataNascimento, habilitacoes, email, cc, genero);

            // 3. Mostrar Resumo
            System.out.println("\n--- Confirme os seus dados ---");
            System.out.println(controller.getDadosCandidatura());
            System.out.println("------------------------------");

            // 4. Confirmar Submissão
            System.out.print("Confirmar submissão? (S/N): ");
            String resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                // 5. Registar
                if (controller.registarCandidatura()) {
                    System.out.println("\n✅ CANDIDATURA SUBMETIDA COM SUCESSO!");
                    System.out.println("📧 As suas credenciais de acesso foram enviadas para: " + email);
                    System.out.println("ℹ️  Aguarde a validação do Coordenador.");
                } else {
                    System.out.println("\n❌ ERRO: Não foi possível submeter (Candidato já existe?).");
                }
            } else {
                System.out.println("\n⚠️ Operação cancelada.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ ERRO DE DADOS: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro inesperado: " + e.getMessage());
        }

        System.out.println("\nPressione ENTER para voltar ao menu...");
        sc.nextLine();
    }

    // --- Método Auxiliar para Datas ---
    private LocalDate lerData(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return LocalDate.parse(sc.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Data inválida. Use o formato AAAA-MM-DD (ex: 2000-01-31).");
            }
        }
    }
}