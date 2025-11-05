package dev.krypta.ProjectDataStructure.util;

import jakarta.persistence.Column;
import lombok.Builder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Builder
public class ItemFormula {

    private String nomeItem;
    private String idFormula;
    private String nomeFormula;
    private Integer quantidade;
    private Double valor;

    public ItemFormula(String nomeItem, String idFormula, String nomeFormula, Integer quantidade, Double valor) {
        this.nomeItem = nomeItem;
        this.idFormula = idFormula;
        this.nomeFormula = nomeFormula;
        this.quantidade = quantidade;
        this.valor = valor;
    }

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

    public String getIdFormula() {
        return idFormula;
    }

    public void setIdFormula(String idFormula) {
        this.idFormula = idFormula;
    }
}
