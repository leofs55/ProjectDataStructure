package dev.krypta.ProjectDataStructure.util;

import java.util.Arrays;
import java.util.List;

public class Testes {


    static TreeNode buildTree(List<ItemFormula> items) {
        return new TreeNode();
    }

    static void printTree(TreeNode raiz, int val) {

    }

    static Double calcularTotal(TreeNode raiz) {
        return 0.0;
    }

    static void segundoTeste() {

        // Lista de itens da fórmula de um Computador

        List<ItemFormula> itensPC = Arrays.asList(

                new ItemFormula("Computador", null, 1, 2500.00),

                new ItemFormula("Gabinete", "Computador", 1, 800.00),
                new ItemFormula("Placa-Mãe", "Gabinete", 1, 500.00),
                new ItemFormula("CPU", "Placa-Mãe", 1, 1000.00),
                new ItemFormula("Memória RAM", "Placa-Mãe", 2, 200.00),
                new ItemFormula("Chipset", "Placa-Mãe", 1, 150.00),
                new ItemFormula("Fonte de Alimentação", "Gabinete", 1, 300.00),
                new ItemFormula("Armazenamento", "Gabinete", 1, 0.00),
                new ItemFormula("HDD", "Armazenamento", 1, 250.00),
                new ItemFormula("SSD", "Armazenamento", 1, 400.00),


                new ItemFormula("Monitor", "Computador", 1, 900.00),
                new ItemFormula("Tela LCD", "Monitor", 1, 600.00),
                new ItemFormula("Carcaça", "Monitor", 1, 100.00),


                new ItemFormula("Periféricos", "Computador", 1, 0.00),
                new ItemFormula("Teclado", "Periféricos", 1, 100.00),
                new ItemFormula("Mouse", "Periféricos", 1, 80.00)

        );

        TreeNode raiz = buildTree(itensPC);

        System.out.println("Árvore da fórmula do computador:");
        printTree(raiz, 0);

        double total = calcularTotal(raiz);
        System.out.printf("\nValor total da caneta: R$ %.2f\n", total);
    }

    static void primeiroTeste() {

        // Lista de itens da fórmula da caneta

        List<ItemFormula> itens = Arrays.asList(

                new ItemFormula("Caneta", null, 1, 2.50),
                new ItemFormula("Tinta", "Caneta", 1, 0.80),
                new ItemFormula("Pigmento", "Tinta", 1, 0.30),
                new ItemFormula("Solvente", "Tinta", 1, 0.50),
                new ItemFormula("Corpo", "Caneta", 1, 1.00),
                new ItemFormula("Plástico", "Corpo", 1, 0.70),
                new ItemFormula("Clip", "Corpo", 1, 0.30),
                new ItemFormula("Tampa", "Caneta", 1, 0.70),
                new ItemFormula("Plástico", "Tampa", 1, 0.50),
                new ItemFormula("Vedação", "Tampa", 1, 0.20)

        );

        TreeNode raiz = buildTree(itens);

        System.out.println("Árvore da fórmula da caneta:");
        printTree(raiz, 0);

        double total = calcularTotal(raiz);
        System.out.printf("\nValor total da caneta: R$ %.2f\n", total);

    }

}
