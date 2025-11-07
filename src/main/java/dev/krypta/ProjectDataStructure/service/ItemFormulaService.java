package dev.krypta.ProjectDataStructure.service;

import dev.krypta.ProjectDataStructure.entity.ItemFormulaEntity;
import dev.krypta.ProjectDataStructure.mapper.MapperItemFormula;
import dev.krypta.ProjectDataStructure.repository.ItemFormulaRepository;
import dev.krypta.ProjectDataStructure.util.ItemFormula;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemFormulaService {

    private ItemFormulaRepository repository;

    public ItemFormula createFormulaItem(ItemFormula itemFormula) {
        // Validação: verificar se já existe um item com o mesmo nome
        Optional<ItemFormulaEntity> itemExistente = repository.findByNome(itemFormula.getNomeItem());
        if (itemExistente.isPresent()) {
            // Retorna o item existente sem criar novo
            return MapperItemFormula.map(itemExistente.get());
        }
        
        return MapperItemFormula.map(
                repository.save(MapperItemFormula.map(itemFormula))
        );
    }

    public List<ItemFormula> createFormulaItems(List<ItemFormula> itensFormula) {
        // Validação: filtrar duplicatas na lista e itens que já existem no banco
        Set<String> nomesProcessados = new HashSet<>();
        List<ItemFormulaEntity> entidadesParaSalvar = new ArrayList<>();
        List<ItemFormulaEntity> entidadesExistentes = new ArrayList<>();

        for (ItemFormula item : itensFormula) {
            String nomeItem = item.getNomeItem();
            
            // Verifica se já foi processado na lista (duplicata na lista enviada)
            if (nomesProcessados.contains(nomeItem)) {
                continue; // Ignora duplicatas na lista
            }
            
            // Verifica se já existe no banco
            Optional<ItemFormulaEntity> itemExistente = repository.findByNome(nomeItem);
            if (itemExistente.isPresent()) {
                entidadesExistentes.add(itemExistente.get());
                nomesProcessados.add(nomeItem);
            } else {
                // Adiciona para salvar apenas se não existe
                entidadesParaSalvar.add(MapperItemFormula.map(item));
                nomesProcessados.add(nomeItem);
            }
        }

        // Salva apenas os itens novos
        List<ItemFormulaEntity> entidadesSalvas = repository.saveAll(entidadesParaSalvar);
        
        // Combina itens existentes com os novos salvos
        List<ItemFormulaEntity> todasEntidades = new ArrayList<>();
        todasEntidades.addAll(entidadesExistentes);
        todasEntidades.addAll(entidadesSalvas);

        return todasEntidades.stream()
                .map(entidade -> MapperItemFormula.map(entidade))
                .collect(Collectors.toList());
    }


    public List<ItemFormula> createFormulaItem(List<ItemFormula> itensFormula) {
        // Validação: filtrar duplicatas na lista e itens que já existem no banco
        Set<String> nomesProcessados = new HashSet<>();
        List<ItemFormulaEntity> entidadesParaSalvar = new ArrayList<>();
        List<ItemFormulaEntity> entidadesExistentes = new ArrayList<>();

        for (ItemFormula item : itensFormula) {
            String nomeItem = item.getNomeItem();
            
            // Verifica se já foi processado na lista (duplicata na lista enviada)
            if (nomesProcessados.contains(nomeItem)) {
                continue; // Ignora duplicatas na lista
            }
            
            // Verifica se já existe no banco
            Optional<ItemFormulaEntity> itemExistente = repository.findByNome(nomeItem);
            if (itemExistente.isPresent()) {
                entidadesExistentes.add(itemExistente.get());
                nomesProcessados.add(nomeItem);
            } else {
                // Adiciona para salvar apenas se não existe
                entidadesParaSalvar.add(MapperItemFormula.map(item));
                nomesProcessados.add(nomeItem);
            }
        }

        // Salva apenas os itens novos
        List<ItemFormulaEntity> entidadesSalvas = repository.saveAll(entidadesParaSalvar);
        
        // Combina itens existentes com os novos salvos
        List<ItemFormulaEntity> todasEntidades = new ArrayList<>();
        todasEntidades.addAll(entidadesExistentes);
        todasEntidades.addAll(entidadesSalvas);

        return todasEntidades.stream()
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
