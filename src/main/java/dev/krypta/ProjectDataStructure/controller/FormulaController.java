package dev.krypta.ProjectDataStructure.controller;

import dev.krypta.ProjectDataStructure.util.Formula;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formula")
public class FormulaController {

    @PostMapping("/create")
    public Formula CreateFormulaEndPoint(Formula formula) {
        return new Formula();
    }

    @GetMapping("/read/{id}")
    public Formula ReadFormulaEndPoint(@PathVariable Long id) {
        return new Formula();
    }

    @GetMapping("/read-all")
    public List<Formula> ReadAllFormulaEndPoint() {
        return List.of( new Formula() );
    }

    @DeleteMapping("/delete/{id}")
    public Formula DeleteFormulaEndPoint(@PathVariable Long id) {
        return new Formula();
    }

    @PutMapping("/update/{id}")
    public Formula UpdateFormulaEndPoint(@PathVariable Long id ,Formula formula) {
        return new Formula();
    }

}
