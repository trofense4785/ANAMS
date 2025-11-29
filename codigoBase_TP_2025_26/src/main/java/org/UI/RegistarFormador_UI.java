package org.UI;

import org.Controller.RegistarFormador_Controller;
import org.Model.Instituicao;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RegistarFormador_UI {

    private final RegistarFormador_Controller controller;
    private final Scanner sc;

    public RegistarFormador_UI() {
        this.controller = new RegistarFormador_Controller(Instituicao.getInstance());
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("          REGISTAR FORMADOR             ");
        System.out.println("========================================");

        try {
            // 1. Solicitar dados pessoais
            System.out.print("Nome: ");
            String nome = sc.nextLine();

            System.out.print("Número de CC (9 dígitos): ");
            String cc = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Contacto: ");
            String contacto = sc.nextLine();

            System.out.print("Área de Formação (ex: Informática, Gestão): ");
            String areaFormacao = sc.nextLine();

            // Tratamento da Data de Nascimento
            LocalDate dataNascimento = lerData("Data de Nascimento (AAAA-MM-DD): ");

            // 2. Enviar para o Controller (Criação em memória)
            controller.novoFormador(nome, dataNascimento, cc, email, contacto, areaFormacao);

            // 3. Mostrar Resumo para Confirmação
            System.out.println("\n--- Confirmação de Dados ---");
            System.out.println(controller.getDadosFormador());
            // Nota: O ID ainda não aparece aqui ou aparece como "Gerado após registo"
            System.out.println("----------------------------");

            // 4. Pedir confirmação
            System.out.print("Confirma o registo deste Formador? (S/N): ");
            String resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                // 5. Registar Efetivamente
                if (controller.registarFormador()) {
                    System.out.println("\n✅ SUCESSO: Formador registado.");
                    System.out.println("ℹ️ O ID e as Credenciais foram gerados e enviados por email.");
                } else {
                    System.out.println("\n❌ ERRO: Não foi possível registar (Email ou CC duplicado?).");
                }
            } else {
                System.out.println("\n⚠️ Operação cancelada.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("\n❌ ERRO DE DADOS: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro inesperado: " + e.getMessage());
        }

        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }

    /**
     * Método auxiliar para ler e validar datas.
     */
    private LocalDate lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String dataStr = sc.nextLine();
                // Formato padrão ISO (Ano-Mês-Dia)
                return LocalDate.parse(dataStr);
            } catch (DateTimeParseException e) {
                System.out.println("⚠️ Formato inválido. Use o formato AAAA-MM-DD (ex: 1990-12-31).");
            }
        }
    }
}