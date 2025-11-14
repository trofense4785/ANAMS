/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.UI;

import java.io.IOException;
import org.Model.Empresa;
import utils.Utils;
import org.Model.*;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuInicial_UI
{
    private Empresa empresa;
    private String opcao;

    public MenuInicial_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }
    public void run() throws IOException
    {
        do
        {
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Candidato (Cad)");
            System.out.println("2. Administrador (Adm)");
            System.out.println("3. Coordenador Académico (CA)");
            System.out.println("4. Formador (For)");
            System.out.println("5. Aluno (Alu)");
            System.out.println("0. Sair");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if( opcao.equals("1") )
            {
                MenuCad_UI ui = new MenuCad_UI(empresa);
                ui.run();
            }
            else
            if( opcao.equals("2") )
            {
                MenuAdm_UI ui = new MenuAdm_UI(empresa);
                ui.run();
            }
            else
            if( opcao.equals("3") )
            {
                MenuCA_UI ui = new MenuCA_UI(empresa);
                ui.run();
            }
            else
            if( opcao.equals("4") )
            {
                MenuFor_UI ui = new MenuFor_UI(empresa);
                ui.run();
            }
            else
            if( opcao.equals("5") )
            {
                MenuAlu_UI ui = new MenuAlu_UI(empresa);
                ui.run();
            }
        }
        while (!opcao.equals("0") );
    }
}
