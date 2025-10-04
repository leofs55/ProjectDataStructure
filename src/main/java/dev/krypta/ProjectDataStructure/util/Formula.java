package dev.krypta.ProjectDataStructure.util;

import java.util.List;

public class Formula {

    private String nome;
    private List<ItemFormula> itemFormulaList;

    public Formula(String nome, List<ItemFormula> itemFormulaList) {
        this.nome = nome;
        this.itemFormulaList = itemFormulaList;
    }

    public Formula() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<ItemFormula> getItemFormulaList() {
        return itemFormulaList;
    }

    public void setItemFormulaList(List<ItemFormula> itemFormulaList) {
        this.itemFormulaList = itemFormulaList;
    }
}
