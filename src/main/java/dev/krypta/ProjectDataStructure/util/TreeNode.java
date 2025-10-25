package dev.krypta.ProjectDataStructure.util;

import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    /**
     * Função auxiliar recursiva para encontrar um nó na árvore pelo nome do item.
     *
     * @param currentNode O nó onde a busca começa (inicialmente, a raiz).
     * @param itemName    O nome do item a ser encontrado.
     * @return O Nó encontrado ou null.
     */
    public static Node findNode(Node currentNode, String itemName) {
        if (currentNode == null) {
            return null;
        }
        // Verifica se o nó atual é o que procuramos
        if (currentNode.getItemFormula().getNomeItem().equals(itemName)) {
            return currentNode;
        }

        // Se não for, busca recursivamente nos filhos
        for (Node child : currentNode.getItemFormulaList()) {
            Node found = findNode(child, itemName);
            if (found != null) {
                return found; // Retorna assim que encontrar
            }
        }

        return null; // Não encontrado nesta subárvore
    }

    /**
     * Constrói a árvore a partir de uma lista de itens.
     * A lista DEVE estar ordenada com o pai aparecendo antes dos filhos.
     */
    public static TreeNode buildTree(List<ItemFormula> itemFormulaList) {
        if (itemFormulaList == null || itemFormulaList.isEmpty()) {
            return null;
        }
        
        Node node; // Este será o nó raiz
        ItemFormula formulaPrincipal = itemFormulaList.getFirst();

        try {
            if (formulaPrincipal.getNomeFormula() == null) {
                node = new Node(formulaPrincipal);
            } else {
                throw new RuntimeException("Erro: O primeiro item da lista não é o item raiz (nomeFormula deve ser nulo).");
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getLocalizedMessage());
        }

        // 2. Itera sobre o restante da lista para adicionar os filhos.
        // Pulamos o índice 0, que já é a raiz.
        for (int i = 1; i < itemFormulaList.size(); i++) {
            ItemFormula itemAtual = itemFormulaList.get(i);
            String nomePai = itemAtual.getNomeFormula();

            // Encontra o nó pai na árvore que estamos construindo
            Node parentNode = findNode(node, nomePai);

            if (parentNode != null) {
                // Adiciona o item atual como um novo nó filho do pai encontrado
                parentNode.getItemFormulaList().add(new Node(itemAtual));
            } else {
                // Isso aconteceria se a lista não estivesse ordenada (filho antes do pai)
                System.err.println("Alerta: Não foi possível encontrar o nó pai '" + nomePai + "' para o item '" + itemAtual.getNomeItem() + "'");
            }
        }

        return new TreeNode(node);
    }

    /**
     * Ponto de entrada público para imprimir a árvore.
     */
    public static void printTree(TreeNode tree, int level) {
        if (tree == null || tree.raiz == null) {
            System.out.println("Árvore está vazia.");
            return;
        }
        // Chama a função recursiva auxiliar
        printNodeRecursive(tree.raiz, level);
    }

    /**
     * Função auxiliar recursiva para imprimir os nós da árvore.
     */
    private static void printNodeRecursive(Node node, int level) {
        if (node == null) {
            return;
        }

        // 1. Cria a indentação
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  "); // 2 espaços por nível
        }

        // 2. Imprime o item atual
        ItemFormula item = node.getItemFormula();
        System.out.printf("%s- %s (Qtd: %d, Valor: R$ %.2f)\n",
                indent.toString(),
                item.getNomeItem(),
                item.getQuantidade(),
                item.getValor());

        // 3. Chama recursivamente para os filhos
        for (Node child : node.getItemFormulaList()) {
            printNodeRecursive(child, level + 1);
        }
    }

    public static Double calcularTotal(TreeNode tree) {
        if (tree == null || tree.raiz == null) {
            return 0.0;
        }
        // O custo total é o custo unitário da raiz * a quantidade da raiz.
        ItemFormula itemRaiz = tree.raiz.getItemFormula();
        return calcularTotalUnitarioRecursive(tree.raiz) * itemRaiz.getQuantidade();
    }

    /**
     * Função auxiliar recursiva que calcula o CUSTO UNITÁRIO (Qtde = 1) de um nó.
     * Custo Unitário = (Valor Próprio do Nó) + (Soma dos Custos Totais dos filhos)
     */
    private static Double calcularTotalUnitarioRecursive(Node node) {
        if (node == null) {
            return 0.0;
        }

        ItemFormula item = node.getItemFormula();

        // 1. Pega o custo próprio (unitário) deste nó.
        // Ex: "Placa-Mãe" = R$ 500,00
        double custoProprioUnitario = item.getValor();

        // 2. Se for nó folha (sem filhos), o custo unitário é apenas o seu valor.
        if (node.getItemFormulaList().isEmpty()) {
            return custoProprioUnitario;
        }

        // 3. Se for nó pai (com filhos), calcula o custo total dos filhos.
        double custoFilhosTotal = 0.0;
        for (Node child : node.getItemFormulaList()) {
            ItemFormula itemFilho = child.getItemFormula();
            
            // O custo deste filho é (Custo Unitário dele) * (Quantidade necessária)
            // Ex: calcUnitario(RAM) * 2
            custoFilhosTotal += calcularTotalUnitarioRecursive(child) * itemFilho.getQuantidade();
        }

        // 4. O custo unitário total deste nó é o seu custo próprio + o custo total dos filhos necessários.
        // Ex: Custo(Placa-Mãe) = R$ 500 (próprio) + R$ 1550 (filhos) = R$ 2050
        return custoProprioUnitario + custoFilhosTotal;
    }


    public static void main(String[] args) {
        List<ItemFormula> itensPC = Arrays.asList(
                new ItemFormula("Computador", null, 1, 2500.00), // Custo de montagem/venda: 3380.00

                new ItemFormula("Gabinete", "Computador", 1, 800.00), // Custo de montagem/venda: 2500.00
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
                new ItemFormula("Caneta", null, 1, 2.50), // Custo de montagem: 2.50
                new ItemFormula("Tinta", "Caneta", 1, 0.80), // Custo de montagem: 0.80
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
