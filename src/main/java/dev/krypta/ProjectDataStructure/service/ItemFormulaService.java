package dev.krypta.ProjectDataStructure.service;

import dev.krypta.ProjectDataStructure.entity.ItemFormulaEntity;
import dev.krypta.ProjectDataStructure.mapper.MapperItemFormula;
import dev.krypta.ProjectDataStructure.repository.ItemFormulaRepository;
import dev.krypta.ProjectDataStructure.util.ItemFormula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemFormulaService {

    private ItemFormulaRepository repository;

    public ItemFormula createFormulaItem(ItemFormula itemFormula) {
        return MapperItemFormula.map(
                repository.save(MapperItemFormula.map(itemFormula))
        );
    }
    public List<ItemFormula> createFormulaItem(List<ItemFormula> itensFormula) {

        List<ItemFormulaEntity> list = itensFormula.stream()
                .map(MapperItemFormula::map)
                .collect(Collectors.toList());

        return repository.saveAll(list).stream()
                .map(MapperItemFormula::map)
                .collect(Collectors.toList());
    }

    public ItemFormula readFormulaItem(String itemFormula) {
        return new ItemFormula();
    }

    public List<ItemFormula> readAllFormulaItens(String nomeFormula) {
        return repository.findByNomeFormula(nomeFormula).stream()
                .map(MapperItemFormula::map)
                .collect(Collectors.toList());
    }

    public void deleteFormulaItem(String nomeItemFormula) {
        Optional<ItemFormulaEntity> itemFormula = repository.findByNome(nomeItemFormula);
        if (itemFormula.isPresent()) {
            repository.delete(itemFormula.get());
        }
    }

    public ItemFormula updateFormulaItem(String nomeItemFormula, ItemFormula itensFormula) {
        Optional<ItemFormulaEntity> itemFormula = repository.findByNome(nomeItemFormula);
        if (itemFormula.isPresent()) {

        }
        return new ItemFormula();
    }

}
