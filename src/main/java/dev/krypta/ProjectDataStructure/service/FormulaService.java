package dev.krypta.ProjectDataStructure.service;

import dev.krypta.ProjectDataStructure.entity.FormulaEntity;
import dev.krypta.ProjectDataStructure.entity.ItemFormulaEntity;
import dev.krypta.ProjectDataStructure.repository.FormulaRepository;
import dev.krypta.ProjectDataStructure.util.Formula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FormulaService {

    private FormulaRepository repository;

    public Boolean formulaExist(Long id){
        return repository.existesById(id);
    }

    public FormulaEntity createFormula(FormulaEntity formula) {
        return repository.save(formula);
    }

    public FormulaEntity readFormula(Long id) {
        return repository.findById(id).get();
    }

    public List<FormulaEntity> readAllFormula() {
        return repository.findAll();
    }

    public void deleteFormula(Long id) {
        repository.deleteById(id);
    }

    public Boolean validateNewFormulaName(String oldFormulaName, String newName) {
        return newName.isBlank() || newName.isEmpty() && !oldFormulaName.equalsIgnoreCase(newName);
    }

    public Boolean validateNewItemList(List<ItemFormulaEntity> oldList, List<ItemFormulaEntity> newList) {
        return oldList.equals(newList);
    }

    public Boolean validateIfIsSameEntity(FormulaEntity savedFormula, FormulaEntity newFormula) {
        return savedFormula.equals(newFormula);
    }

    public FormulaEntity updateFormula(Long id, FormulaEntity formula) {
        FormulaEntity savedFormula = readFormula(id);

        String newName = (validateNewFormulaName(savedFormula.getNome(), formula.getNome())) ?
                savedFormula.getNome() : formula.getNome();

        List<ItemFormulaEntity> newItens = (validateNewItemList(savedFormula.getItens(), formula.getItens())) ?
                savedFormula.getItens() : formula.getItens();

        return repository.save(new FormulaEntity(savedFormula.getId(), newName, newItens));
    }

}
