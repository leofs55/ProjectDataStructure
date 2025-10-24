package dev.krypta.ProjectDataStructure.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

public class ItemFormula {

    private String nomeItem;
    private String nomeFormula;
    private Integer quantidade;
    private Double valor;

    public ItemFormula(String nomeItem, String nomeFormula, Integer quantidade, Double valor) {
        this.nomeItem = nomeItem;
        this.nomeFormula = nomeFormula;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ItemFormula() {
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getNomeFormula() {
        return nomeFormula;
    }

    public void setNomeFormula(String nomeFormula) {
        this.nomeFormula = nomeFormula;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
