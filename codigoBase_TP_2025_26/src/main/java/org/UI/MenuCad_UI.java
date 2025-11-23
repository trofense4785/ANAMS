/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.UI;

import java.io.IOException;

import utils.Utils;
import org.Model.Instituicao;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuCad_UI
{
    private Instituicao instituicao;
    private String opcao;

    public MenuCad_UI(Instituicao instituicao)
    {
        this.instituicao = instituicao;
    }
    public void run() throws IOException
    {
        do
        {
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Fazer matricula na Instituição");

            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if( opcao.equals("1") )
            {
                // Completar
                System.out.println("Selecionou a opção: Fazer matricula na Instituição");
            }
        }
        while (!opcao.equals("0") );
    }
}
