package dev.krypta.ProjectDataStructure.service;

import dev.krypta.ProjectDataStructure.entity.ItemFormulaEntity;
import dev.krypta.ProjectDataStructure.mapper.MapperItemFormula;
import dev.krypta.ProjectDataStructure.repository.ItemFormulaRepository;
import dev.krypta.ProjectDataStructure.util.ItemFormula;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemFormulaService {

    private ItemFormulaRepository repository;

    public ItemFormula createFormulaItem(ItemFormula itemFormula) {
        return MapperItemFormula.map(
                repository.save(MapperItemFormula.map(itemFormula))
        );
    }

    public List<ItemFormula> createFormulaItems(List<ItemFormula> itensFormula) {

        List<ItemFormulaEntity> entidadesParaSalvar = itensFormula.stream()
                .map(dto -> MapperItemFormula.map(dto))
                .collect(Collectors.toList());

        List<ItemFormulaEntity> entidadesSalvas = repository.saveAll(entidadesParaSalvar);

        return entidadesSalvas.stream()
                .map(entidade -> MapperItemFormula.map(entidade))
                .collect(Collectors.toList());
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
        Optional<ItemFormulaEntity> itemFormulaFinded = repository.findByNome(itemFormula);
        if (itemFormulaFinded.isPresent()) {
            return itemFormulaFinded.map(MapperItemFormula::map).get();
        }
        return null;
    }

    public List<ItemFormula> readAllFormulaItens(String idFormula) {
        return repository.findByIdFormula(idFormula).stream()
                .map(MapperItemFormula::map)
                .collect(Collectors.toList());
    }

    public void deleteFormulaItem(String nomeItemFormula) {
        Optional<ItemFormulaEntity> itemFormula = repository.findByNome(nomeItemFormula);
        if (itemFormula.isPresent()) {
            repository.delete(itemFormula.get());
        }
    }

    public ItemFormula updateFormulaItem(String nomeItemFormula, ItemFormula itemFormula) {
        Optional<ItemFormulaEntity> itemFormulaFinded = repository.findByNome(nomeItemFormula);
        if (itemFormulaFinded.isPresent()) {
            ItemFormulaEntity itemFormulaPresent = itemFormulaFinded.get();

            String nomeItem = itemFormula.getNomeItem() != null
                    ? itemFormula.getNomeItem()
                    : itemFormulaPresent.getNome();

            String nomeFormula = itemFormula.getNomeFormula() != null
                    ? itemFormula.getNomeFormula()
                    : itemFormulaPresent.getNomeFormula();

            Integer quantidade = itemFormula.getQuantidade() != null
                    ? itemFormula.getQuantidade()
                    : itemFormulaPresent.getQuantidade();

            Double valor = itemFormula.getValor() != null
                    ? itemFormula.getValor()
                    : itemFormulaPresent.getValor();

            ItemFormulaEntity itemUpdated = ItemFormulaEntity.builder()
                    .id(itemFormulaPresent.getId())
                    .nome(nomeItem)
                    .idFormula(itemFormulaPresent.getIdFormula())
                    .nomeFormula(nomeFormula)
                    .quantidade(quantidade)
                    .valor(valor)
                    .build();

            return MapperItemFormula.map(repository.save(itemUpdated));
        }
        return null;
    }

}
