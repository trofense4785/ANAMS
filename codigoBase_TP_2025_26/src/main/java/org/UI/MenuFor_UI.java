package org.UI;

import java.io.IOException;

import utils.Utils;
import org.Model.Empresa;
/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 */
public class MenuFor_UI
{
    private Empresa empresa;
    private String opcao;

    public MenuFor_UI(Empresa empresa)
    {
        this.empresa = empresa;
    }
    public void run() throws IOException
    {
        do
        {
            System.out.println("###### MENU #####\n\n");
            System.out.println("1. Consultar lista de cursos (responsável)");
            System.out.println("2. Consultar lista de alunos de um curso");

            System.out.println("0. Voltar");

            opcao = Utils.readLineFromConsole("Escolha uma opção: ");

            if( opcao.equals("1") )
            {
                // Completar
                System.out.println("Selecionou a opção: Consultar lista de cursos (responsável)");
            }
            else
            if( opcao.equals("2") )
            {
                // Completar
                System.out.println("Consultar lista de Alunos de um curso");
            }
        }
        while (!opcao.equals("0") );
    }
}
