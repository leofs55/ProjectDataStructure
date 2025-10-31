package dev.krypta.ProjectDataStructure.mapper;

import dev.krypta.ProjectDataStructure.entity.ItemFormulaEntity;
import dev.krypta.ProjectDataStructure.util.ItemFormula;

public class MapperItemFormula {

    public static ItemFormula map(ItemFormulaEntity item) {
        return new ItemFormula(
                item.getNome(),
                item.getNomeFormula(),
                item.getQuantidade(),
                item.getValor()
        );
    }

    public static ItemFormulaEntity map(ItemFormula item) {
        return ItemFormulaEntity.builder()
                .nome(item.getNomeItem())
                .nomeFormula(item.getNomeFormula())
                .quantidade(item.getQuantidade())
                .valor(item.getValor())
                .build();
    }

}
