package dev.krypta.ProjectDataStructure.service;

import dev.krypta.ProjectDataStructure.repository.FormulaRepository;
import dev.krypta.ProjectDataStructure.util.Formula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FormulaService {

    private FormulaRepository repository;

    public Formula CreateFormula(Formula formula) {
        return new Formula();
    }

    public Formula ReadFormula(Long id) {
        return new Formula();
    }

    public List<Formula> ReadAllFormula() {
        return List.of( new Formula());
    }

    public void DeleteFormula(Long id) {
    }

    public Formula UpdateFormula(Long id, Formula formula) {
        return new Formula();
    }

}
