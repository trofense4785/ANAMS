package org.UI;

import java.io.IOException;

import utils.Utils;
import org.Model.Instituicao;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuAlu_UI
{
    private Instituicao instituicao;
    private String opcao;

    public MenuAlu_UI(Instituicao instituicao)
    {
        this.instituicao = instituicao;
    }
    public void run() throws IOException
    {
        do
        {
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Fazer inscrição num curso");
            System.out.println("2. Anular inscrição num curso");
            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if( opcao.equals("1") )
            {
                // Completar
                System.out.println("Selecionou a opção: Fazer inscrição num curso");

            }
            else
            if( opcao.equals("2") )
            {
                // Completar
                System.out.println("Selecionou a opção: Anular inscrição num curso");

            }

        }
        while (!opcao.equals("0") );
    }
}
