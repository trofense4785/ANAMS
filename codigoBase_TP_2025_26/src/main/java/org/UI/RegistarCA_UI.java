package org.UI;

import org.Controller.RegistarCA_Controller;
import org.Model.Instituicao;

import java.util.Scanner;

public class RegistarCA_UI {

    private final RegistarCA_Controller controller;
    private final Scanner sc;

    public RegistarCA_UI() {
        this.controller = new RegistarCA_Controller(Instituicao.getInstance());
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("   REGISTAR COORDENADOR ACADÉMICO (CA)  ");
        System.out.println("========================================");

        try {
            // 1. Solicitar dados ao utilizador
            System.out.print("Nome do CA: ");
            String nome = sc.nextLine();

            System.out.print("Sigla (ex: ADM, JSM): ");
            String sigla = sc.nextLine();

            System.out.print("Número de CC (9 dígitos): ");
            String cc = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();

            System.out.print("Contacto telefónico: ");
            String contacto = sc.nextLine();

            // 2. Enviar dados para o Controller criar o objeto (Criação Provisória)
            // Nota: Este passo pode lançar exceções se os dados forem inválidos ou se já existir um CA
            controller.novoCA(nome, email, cc, sigla, contacto);

            // 3. Mostrar os dados para confirmação
            System.out.println("\n--- Confirmação de Dados ---");
            System.out.println(controller.getDadosCA());
            System.out.println("----------------------------");

            // 4. Pedir confirmação
            System.out.print("Confirma o registo destes dados? (S/N): ");
            String resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                // 5. Registar efetivamente (Gravar na Instituição)
                if (controller.registarCA()) {
                    System.out.println("\n✅ SUCESSO: Coordenador Académico registado.");
                    System.out.println("ℹ️ As credenciais de acesso foram geradas.");
                } else {
                    System.out.println("\n❌ ERRO: Não foi possível registar o CA (verifique se já existe).");
                }
            } else {
                System.out.println("\n⚠️ Operação cancelada pelo utilizador.");
            }

        } catch (IllegalStateException e) {
            // Mensagem mais amigável para o utilizador
            System.out.println("\n❌ ERRO: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            // Captura erros de validação de dados (ex: CC errado, Email inválido)
            System.out.println("\n❌ ERRO DE DADOS: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro inesperado: " + e.getMessage());
        }

        System.out.println("Pressione ENTER para voltar...");
        sc.nextLine();
    }
}
