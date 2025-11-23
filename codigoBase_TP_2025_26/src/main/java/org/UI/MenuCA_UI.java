package org.UI;

import java.io.IOException;

import utils.Utils;
import org.Model.Instituicao;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuCA_UI
{
    private Instituicao instituicao;
    private String opcao;

    public MenuCA_UI(Instituicao instituicao)
    {
        this.instituicao = instituicao;
    }
    public void run() throws IOException
    {
        do
        {
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Especificar tipo de curso");
            System.out.println("2. Registar formador");
            System.out.println("3. Registar curso");
            System.out.println("4. Adicionar módulo a um curso");
            System.out.println("5. Tomar decisão sobre candidatura");

            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if( opcao.equals("1") )
            {
                EspecificarTipoCurso_UI ui = new EspecificarTipoCurso_UI(instituicao);
                ui.run();
            }
            else
            if( opcao.equals("2") )
            {
                // Completar
                System.out.println("Selecionou a opção: Registar formador");
            }
            else
            if( opcao.equals("3") )
            {
                // Completar
                System.out.println("Selecionou a opção: Registar curso");
            }
            else
            if( opcao.equals("4") )
            {
                // Completar
                System.out.println("Selecionou a opção: Adicionar módulo a um curso");
            }
            else
            if( opcao.equals("5") )
            {
                // Completar
                System.out.println("Selecionou a opção: Tomar decisão sobre candidatura");
            }

        }
        while (!opcao.equals("0") );
    }
}
