package dev.krypta.ProjectDataStructure.controller;

import dev.krypta.ProjectDataStructure.util.ItemFormula;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/front")
public class DataStructureController {


    @PostMapping("/item-formula/create")
    public ItemFormula CreateItemFormulaEndPoint(ItemFormula itemFormula) {
        return new ItemFormula();
    }

    @GetMapping("/item-formula/read/{id}")
    public ItemFormula ReadItemFormulaEndPoint(@PathVariable Long id) {
        return new ItemFormula();
    }

    @GetMapping("/item-formula/read-all")
    public List<ItemFormula> ReadAllItensFormulaEndPoint() {
        return List.of( new ItemFormula() );
    }

    @DeleteMapping("/item-formula/delete/{id}")
    public ItemFormula DeleteItemFormulaEndPoint(@PathVariable Long id) {
        return new ItemFormula();
    }

    @PutMapping("/item-formula/update/{id}")
    public ItemFormula UpdateItemFormulaEndPoint(@PathVariable Long id ,ItemFormula formula) {
        return new ItemFormula();
    }

}
