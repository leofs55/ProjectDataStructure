package dev.krypta.ProjectDataStructure.controller;

import dev.krypta.ProjectDataStructure.util.ItemFormula;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item-formula")
public class ItemFormulaController {

    @PostMapping("/create")
    public ItemFormula CreateItemFormulaEndPoint(ItemFormula itemFormula) {
        return new ItemFormula();
    }

    @GetMapping("/read/{id}")
    public ItemFormula ReadItemFormulaEndPoint(@PathVariable Long id) {
        return new ItemFormula();
    }

    @GetMapping("/read-all")
    public List<ItemFormula> ReadAllItensFormulaEndPoint() {
        return List.of( new ItemFormula() );
    }

    @DeleteMapping("/delete/{id}")
    public ItemFormula DeleteItemFormulaEndPoint(@PathVariable Long id) {
        return new ItemFormula();
    }

    @PutMapping("/update/{id}")
    public ItemFormula UpdateItemFormulaEndPoint(@PathVariable Long id ,ItemFormula formula) {
        return new ItemFormula();
    }

}
