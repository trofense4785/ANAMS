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
public class MenuCad_UI {

    private Instituicao instituicao;
    private Scanner sc;

    public MenuCad_UI(Instituicao instituicao) {
        this.instituicao = instituicao;
        this.sc = new Scanner(System.in);
    }

    public void run() {
        String opcao;
        do {
            System.out.println("\n###### MENU CANDIDATO ######");
            System.out.println("1. Submeter Nova Candidatura (UC7)");
            System.out.println("0. Voltar ao Menu Inicial");
            System.out.println("############################");
            System.out.print("Escolha uma opção: ");

            opcao = sc.nextLine();

            switch (opcao) {
                case "1":
                    // Chama a UI do UC7
                    SubmeterCandidatura_UI ui = new SubmeterCandidatura_UI();
                    ui.run();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (!opcao.equals("0"));
    }
}
