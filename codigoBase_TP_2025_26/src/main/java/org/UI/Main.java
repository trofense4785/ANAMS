/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.UI;

import org.Model.*;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Dulce Mota <mdm@isep.ipp.pt>
 * Classe de arranque da aplicação
 */
public class Main {

    public static void main(String[] args) {
        // 1. Inicializar a Instituição (Singleton)
        Instituicao instituicao = Instituicao.getInstance();

        // 2. Carregar dados de teste (Bootstrap)
        // Comenta esta linha se quiseres o sistema vazio
        bootstrap(instituicao);

        // 3. Iniciar a Interface Gráfica Principal
        try {
            MenuInicial_UI menuInicial = new MenuInicial_UI(instituicao);
            menuInicial.run();
        } catch (Exception e) {
            System.out.println(" Erro fatal na aplicação: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método auxiliar para criar dados fictícios para teste.
     */
    private static void bootstrap(Instituicao instituicao) {
        System.out.println(">> A carregar dados de teste...");

        try {

            CA ca = new CA("Ana Silva", "ana@isep.ipp.pt", "123456789", "ADM", "912345678");

            instituicao.registarCA(ca);
            ca.setCredenciais(new Credenciais("admin", "admin"));
            System.out.println("   + CA criado (Login: admin / Pass: admin)");

            TipoCurso tipo1 = instituicao.novoTipoCurso("IT", "Tecnologia da Informação");
            instituicao.registarTipoCurso(tipo1);

            TipoCurso tipo2 = instituicao.novoTipoCurso("GEST", "Gestão e Economia");
            instituicao.registarTipoCurso(tipo2);
            System.out.println("   + Tipos de Curso criados (IT, GEST)");


            Formador f1 = new Formador("João Professor", LocalDate.of(1980, 5, 20), "987654321", "joao@isep.ipp.pt", "911222333", "Mestrado em Engenharia");

            instituicao.registarFormador(f1);


            System.out.println("   + Formador criado (Login: joao@isep.ipp.pt / Pass: 1234)");


            Curso c1 = instituicao.novoCurso("Java Avançado", "JAV01", tipo1, "Curso de backend", LocalDate.now().plusDays(10), LocalDate.now().plusDays(40));

            Modulo m1 = c1.novoModulo("M-1", "Intro ao Java", 10, LocalDate.now().plusDays(12), LocalDate.now().plusDays(15), f1, 50, new ArrayList<>(), new ArrayList<>());
            m1.adicionarSessao(new SessaoModulo(java.time.LocalDateTime.now().plusDays(12), 2, "B203"));
            m1.adicionarSessao(new SessaoModulo(java.time.LocalDateTime.now().plusDays(13), 2, "B203"));
            m1.adicionarSessao(new SessaoModulo(java.time.LocalDateTime.now().plusDays(14), 2, "B203"));

            c1.addModulo(m1);


            c1.addResponsavel(f1);

            instituicao.registarCurso(c1);
            System.out.println("   + Curso criado: Java Avançado (JAV01) - Estado: A Iniciar");


            Aluno a1 = new Aluno("Pedro Aluno", "pedro@isep.ipp.pt", "111222333", LocalDate.of(2000, 1, 1));
            a1.setCodigoAluno("AL-2025-1");
            a1.setCredenciais(new Credenciais("pedro@isep.ipp.pt", "1234"));
            instituicao.getListaAlunos().add(a1);
            System.out.println("   + Aluno criado (Login: pedro@isep.ipp.pt / Pass: 1234)");

        } catch (Exception e) {
            System.out.println("Jo Aviso no Bootstrap: " + e.getMessage());
        }
        System.out.println(">> Sistema inicializado.\n");
    }
}
