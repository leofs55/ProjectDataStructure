package dev.krypta.ProjectDataStructure.service;

import dev.krypta.ProjectDataStructure.repository.ItemFormulaRepository;
import dev.krypta.ProjectDataStructure.util.Formula;
import dev.krypta.ProjectDataStructure.util.ItemFormula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemFormulaService {

    private ItemFormulaRepository repository;

    public ItemFormula CreateFormulaItem(ItemFormula formula) {
        return new ItemFormula();
    }

    public ItemFormula ReadFormulaItem(Long id) {
        return new ItemFormula();
    }

    public List<ItemFormula> ReadAllFormulaItens() {
        return List.of(new ItemFormula());
    }

    public void DeleteFormulaItem(Long id) {
    }

    public ItemFormula UpdateFormulaItem(Long id, ItemFormula formula) {
        return new ItemFormula();
    }

}
