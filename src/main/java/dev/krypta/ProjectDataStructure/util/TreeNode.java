package dev.krypta.ProjectDataStructure.util;

import org.springframework.stereotype.Component;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TreeNode {

    Node raiz;

    public static class Node {
        private ItemFormula itemFormula;
        private List<Node> itemFormulaList;

        public Node(ItemFormula itemFormula) {
            this.itemFormula = itemFormula;
            this.itemFormulaList = new ArrayList<>();
        }

        // Getters necessários para as funções auxiliares
        public ItemFormula getItemFormula() {
            return itemFormula;
        }

        public List<Node> getItemFormulaList() {
            return itemFormulaList;
        }
    }

    public TreeNode(Node raiz) {
        this.raiz = raiz;
    }

    public static Node findNode(Node currentNode, String itemName) {
        if (currentNode == null) {
            return null;
        }
        
        if (currentNode.getItemFormula().getNomeItem().equals(itemName)) {
            return currentNode;
        }

        for (Node child : currentNode.getItemFormulaList()) {
            Node found = findNode(child, itemName);
            if (found != null) {
                return found;
            }
        }

        return null;
    }

    public static TreeNode buildTree(List<ItemFormula> itemFormulaList) {
        if (itemFormulaList == null || itemFormulaList.isEmpty()) {
            return null;
        }
        
        Node node; // Este será o nó raiz
        ItemFormula formulaPrincipal = itemFormulaList.get(0);

        try {
            if (formulaPrincipal.getNomeFormula() == null) {
                node = new Node(formulaPrincipal);
            } else {
                throw new RuntimeException("Erro: O primeiro item da lista não é o item raiz (nomeFormula deve ser nulo).");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

        for (int i = 1; i < itemFormulaList.size(); i++) {
            ItemFormula itemAtual = itemFormulaList.get(i);
            String nomePai = itemAtual.getNomeFormula();

            Node parentNode = findNode(node, nomePai);

            if (parentNode != null) {
                parentNode.getItemFormulaList().add(new Node(itemAtual));
            } else {
                System.err.println("Alerta: Não foi possível encontrar o nó pai '" + nomePai + "' para o item '" + itemAtual.getNomeItem() + "'");
            }
        }

        return new TreeNode(node);
    }

    public static void printTree(TreeNode tree, int level) {
        if (tree == null || tree.raiz == null) {
            System.out.println("Árvore está vazia.");
            return;
        }

        printNodeRecursive(tree.raiz, level);
    }

    private static void printNodeRecursive(Node node, int level) {
        if (node == null) {
            return;
        }

        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  ");
        }

        ItemFormula item = node.getItemFormula();
        System.out.printf("%s- %s (Qtd: %d, Valor: R$ %.2f)\n",
                indent.toString(),
                item.getNomeItem(),
                item.getQuantidade(),
                item.getValor());

        for (Node child : node.getItemFormulaList()) {
            printNodeRecursive(child, level + 1);
        }
    }

    public static Double calcularTotal(TreeNode tree) {
        if (tree == null || tree.raiz == null) {
            return 0.0;
        }
        ItemFormula itemRaiz = tree.raiz.getItemFormula();
        return calcularTotalUnitarioRecursive(tree.raiz) * itemRaiz.getQuantidade();
    }


    private static Double calcularTotalUnitarioRecursive(Node node) {
        if (node == null) {
            return 0.0;
        }

        ItemFormula item = node.getItemFormula();

        double custoProprioUnitario = item.getValor();

        if (node.getItemFormulaList().isEmpty()) {
            return custoProprioUnitario;
        }

        double custoFilhosTotal = 0.0;
        for (Node child : node.getItemFormulaList()) {
            ItemFormula itemFilho = child.getItemFormula();

            custoFilhosTotal += calcularTotalUnitarioRecursive(child) * itemFilho.getQuantidade();
        }
        return custoProprioUnitario + custoFilhosTotal;
    }


    public static void main(String[] args) {
        List<ItemFormula> itensPC = Arrays.asList(
                new ItemFormula("Computador", null, 1, 0.0), // Custo de montagem/venda: 3380.00

                new ItemFormula("Gabinete", "Computador", 1, 900.00), // Custo de montagem/venda: 2500.00
                new ItemFormula("Placa-Mãe", "Gabinete", 1, 500.00), // Custo de montagem/venda: 1550.00
                new ItemFormula("CPU", "Placa-Mãe", 1, 1000.00),
                new ItemFormula("Memória RAM", "Placa-Mãe", 2, 200.00), // 2 x 200 = 400
                new ItemFormula("Chipset", "Placa-Mãe", 1, 150.00),
                new ItemFormula("Fonte de Alimentação", "Gabinete", 1, 300.00),
                new ItemFormula("Armazenamento", "Gabinete", 1, 0.00), // Custo de montagem/venda: 650.00
                new ItemFormula("HDD", "Armazenamento", 1, 250.00),
                new ItemFormula("SSD", "Armazenamento", 1, 400.00),

                new ItemFormula("Monitor", "Computador", 1, 900.00), // Custo de montagem/venda: 700.00
                new ItemFormula("Tela LCD", "Monitor", 1, 600.00),
                new ItemFormula("Carcaça", "Monitor", 1, 100.00),

                new ItemFormula("Periféricos", "Computador", 1, 0.00), // Custo de montagem/venda: 180.00
                new ItemFormula("Teclado", "Periféricos", 1, 100.00),
                new ItemFormula("Mouse", "Periféricos", 1, 80.00)
        );

        TreeNode raiz = buildTree(itensPC);

        System.out.println("Árvore da fórmula do computador:");
        printTree(raiz, 0);

        double total = calcularTotal(raiz);
        // Corrigido o texto da impressão
        System.out.printf("\nValor total do computador: R$ %.2f\n", total);
        System.out.println("------------------------------------");


        // Lista de itens da fórmula da caneta
        List<ItemFormula> itens = Arrays.asList(
                new ItemFormula("Caneta", null, 1, 0.0),
                new ItemFormula("Tinta", "Caneta", 1, 0.80), //
                new ItemFormula("Pigmento", "Tinta", 1, 0.30),
                new ItemFormula("Solvente", "Tinta", 1, 0.50),
                new ItemFormula("Corpo", "Caneta", 1, 1.00), // Custo de montagem: 1.00
                new ItemFormula("Plástico", "Corpo", 1, 0.70),
                new ItemFormula("Clip", "Corpo", 1, 0.30),
                new ItemFormula("Tampa", "Caneta", 1, 0.70), // Custo de montagem: 0.70
                new ItemFormula("Plástico", "Tampa", 1, 0.50),
                new ItemFormula("Vedação", "Tampa", 1, 0.20)
        );

        TreeNode raiz1 = buildTree(itens);

        System.out.println("Árvore da fórmula da caneta:");
        printTree(raiz1, 0);

        // Corrigido de 'raiz' para 'raiz1'
        double total1 = calcularTotal(raiz1);
        System.out.printf("\nValor total da caneta: R$ %.2f\n", total1);
    }
}
