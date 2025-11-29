/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.UI;

import java.io.IOException;
import java.util.Scanner;

import utils.Utils;
import org.Model.Instituicao;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuAdm_UI {

    private Instituicao instituicao;
    private Scanner sc;

    public MenuAdm_UI(Instituicao instituicao) {
        this.instituicao = instituicao;
        this.sc = new Scanner(System.in);
    }

    public void run() {
        System.out.println("\n--- Autenticação de Administrador ---");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String pass = sc.nextLine();

        if (!username.equals("admin") || !pass.equals("admin")) {
            System.out.println(" Credenciais incorretas. Acesso negado.");
            return;
        }

        System.out.println(" Bem-vindo, Administrador.");

        String opcao;
        do {
            System.out.println("\n###### MENU ADMINISTRADOR ######");
            System.out.println("1. Registar Coordenador Académico (UC1)");
            System.out.println("0. Voltar ao Menu Inicial");
            System.out.println("################################");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    RegistarCA_UI uiRegistarCA = new RegistarCA_UI();
                    uiRegistarCA.run();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }
}

