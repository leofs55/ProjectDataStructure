package dev.krypta.ProjectDataStructure.controller;

import dev.krypta.ProjectDataStructure.service.ItemFormulaService;
import dev.krypta.ProjectDataStructure.util.ItemFormula;
import dev.krypta.ProjectDataStructure.util.TreeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/front")
@RequiredArgsConstructor
public class DataStructureController {

    private final ItemFormulaService itemFormulaService;

    @PostMapping("/item-formula/create")
    public ItemFormula CreateItemFormulaEndPoint(@RequestBody ItemFormula itemFormula) {
        return itemFormulaService.createFormulaItem(itemFormula);
    }

    @PostMapping("/item-formula/create-list")
    public List<ItemFormula> CreateItemFormulaListEndPoint(@RequestBody List<ItemFormula> itensFormula) {
        return itemFormulaService.createFormulaItems(itensFormula);
    }

    @GetMapping("/item-formula/read/{nameItemFormula}")
    public ItemFormula ReadItemFormulaEndPoint(@PathVariable String nameItemFormula) {
        return itemFormulaService.readFormulaItem(nameItemFormula);
    }

    @DeleteMapping("/item-formula/delete/{nameItemFormula}")
    public void DeleteItemFormulaEndPoint(@PathVariable String nameItemFormula) {
        itemFormulaService.deleteFormulaItem(nameItemFormula);
    }

    @PutMapping("/item-formula/update/{nameItemFormula}")
    public ItemFormula UpdateItemFormulaEndPoint(@PathVariable String nameItemFormula ,@RequestBody ItemFormula itemFormula) {
        return itemFormulaService.updateFormulaItem(nameItemFormula, itemFormula);
    }

    // Aqui ela irá coletar todos  os itens q possuem
    @GetMapping("/formula-tree/read/{idFormula}")
    public TreeNode ReadFormulaEndPoint(@PathVariable String idFormula) {
        return new TreeNode();
    }

}
