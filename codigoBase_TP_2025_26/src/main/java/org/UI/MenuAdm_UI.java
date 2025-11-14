/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.UI;

import java.io.IOException;

import utils.Utils;
import org.Model.Empresa;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuAdm_UI
{
    private Empresa empresa;
    private String opcao;

    public MenuAdm_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }
    public void run() throws IOException
    {
        do
        {
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Registar Coordenador Académico");

            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");
 
            if( opcao.equals("1") )
            {
                 // Completar
                System.out.println("Selecionou a opção: Registar Coordenador Académico");
                
            }

        }
        while (!opcao.equals("0") );
    }
}


