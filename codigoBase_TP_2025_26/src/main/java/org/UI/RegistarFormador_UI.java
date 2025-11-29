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

            // --- VALIDAÇÃO DE CC (Formato + Unicidade) ---
            String cc = "";
            while (true) {
                System.out.print("Número de CC (9 dígitos): ");
                cc = sc.nextLine();


                if (cc.length() != 9 || !cc.matches("\\d+")) {
                    System.out.println(" Erro de Formato: O CC deve ter exatamente 9 dígitos numéricos.");
                }

                else if (!controller.validarCCUnico(cc)) {
                    System.out.println(" Erro de Dados: Esse CC já está registado noutro formador.");
                }
                else {
                    break;
                }
            }


            String email = "";
            while (true) {
                System.out.print("Email: ");
                email = sc.nextLine();


                if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
                    System.out.println(" Erro de Formato: Insira um email válido (ex: nome@isep.ipp.pt).");
                }

                else if (!controller.validarEmailUnico(email)) {
                    System.out.println(" Erro de Dados: Esse email já está associado a outra conta.");
                }
                else {
                    break;
                }
            }


            String contacto = "";
            while (true) {
                System.out.print("Contacto (9 dígitos): ");
                contacto = sc.nextLine();

                if (contacto.length() != 9 || !contacto.matches("\\d+")) {
                    System.out.println(" Erro de Formato: O contacto deve ter 9 dígitos numéricos.");
                }
                else if (!controller.validarContactoUnico(contacto)) {
                    System.out.println(" Erro de Dados: Esse contacto já está registado no sistema.");
                }
                else {
                    break;
                }
            }

            System.out.print("Área de Formação (ex: Informática, Gestão): ");
            String areaFormacao = sc.nextLine();

            LocalDate dataNascimento = lerData("Data de Nascimento (AAAA-MM-DD): ");

            controller.novoFormador(nome, dataNascimento, cc, email, contacto, areaFormacao);


            System.out.println("\n--- Confirmação de Dados ---");
            System.out.println(controller.getDadosFormador());
            System.out.println("----------------------------");

            System.out.print("Confirma o registo deste Formador? (S/N): ");
            String resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                if (controller.registarFormador()) {
                    System.out.println("\n SUCESSO: Formador registado.");
                    System.out.println(" O ID e as Credenciais foram gerados e enviados por email.");
                } else {
                    System.out.println("\n ERRO: Não foi possível registar (Erro inesperado).");
                }
            } else {
                System.out.println("\n️Operação cancelada.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("\n ERRO DE DADOS: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n Ocorreu um erro inesperado: " + e.getMessage());
        }

        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }


    private LocalDate lerData(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String dataStr = sc.nextLine();
                return LocalDate.parse(dataStr);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use o formato AAAA-MM-DD (ex: 1990-12-31).");
            }
        }
    }
}