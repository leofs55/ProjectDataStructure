package dev.krypta.ProjectDataStructure.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

    public Node root;

    public static class Node {
        private ItemFormula itemFormula;
        private List<ItemFormula> itemFormulaList;

        public Node(ItemFormula itemFormula) {
            this.itemFormula = itemFormula;
            this.itemFormulaList = new ArrayList<>();
        }
    }

    public TreeNode buildTree(List<ItemFormula> itemFormulaList) {
        return new TreeNode();
    }

    public void printTree() {

    }

    public Double calcularTotal(TreeNode raizNode) {
        return 0.0;
    }

    
}
