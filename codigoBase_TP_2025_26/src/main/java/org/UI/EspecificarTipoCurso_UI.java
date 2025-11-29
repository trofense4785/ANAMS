/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.UI;

import utils.Utils;
import org.Model.Instituicao;
import org.Controller.EspecificarTipoCurso_Controller;



import java.util.Scanner;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class EspecificarTipoCurso_UI {

    private final EspecificarTipoCurso_Controller controller;
    private final Scanner sc;

    public EspecificarTipoCurso_UI() {
        this.controller = new EspecificarTipoCurso_Controller(Instituicao.getInstance());
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n========================================");
        System.out.println("      ESPECIFICAR TIPO DE CURSO         ");
        System.out.println("========================================");

        try {
            // 1. Solicitar dados (Passo 2 do Diagrama de Sequência)
            System.out.print("Introduza a Sigla (ex: LIC, POS, MEST): ");
            String sigla = sc.nextLine();

            System.out.print("Introduza a Descrição (ex: Licenciatura, Pós-Graduação): ");
            String descricao = sc.nextLine();

            // 2. Enviar para o Controller (Passo 2.1)
            // Cria o objeto em memória. Se os dados estiverem vazios, a classe TipoCurso lança logo erro.
            controller.novoTipoCurso(sigla, descricao);

            // 3. Apresentar dados para confirmação (Passo 2.3)
            System.out.println("\n--- Resumo do Novo Tipo ---");
            System.out.println(controller.getTipoCursoString());
            System.out.println("---------------------------");

            // 4. Confirmar (Passo 3)
            System.out.print("Deseja registar este Tipo de Curso? (S/N): ");
            String resposta = sc.nextLine();

            if (resposta.equalsIgnoreCase("S")) {
                // 5. Registar (Passo 3.1)
                // Aqui é que a Instituição verifica se a SIGLA é única
                if (controller.registarTipoCurso()) {
                    System.out.println("\n✅ SUCESSO: Tipo de Curso registado com êxito.");
                } else {
                    System.out.println("\n❌ ERRO: Não foi possível registar (Operação falhou na Instituição).");
                }
            } else {
                System.out.println("\n⚠️ Operação cancelada pelo utilizador.");
            }

        } catch (IllegalArgumentException e) {
            // Captura erros de validação (Sigla vazia, ou Sigla Duplicada se a validação for imediata)
            System.out.println("\n❌ ERRO: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n❌ Ocorreu um erro inesperado: " + e.getMessage());
        }

        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }
}

