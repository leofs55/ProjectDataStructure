package dev.krypta.ProjectDataStructure.controller;

import dev.krypta.ProjectDataStructure.service.ItemFormulaService;
import dev.krypta.ProjectDataStructure.util.ItemFormula;
import dev.krypta.ProjectDataStructure.util.TreeNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DataStructureController {

    private final ItemFormulaService itemFormulaService;

    @PostMapping("/item-formula/create")
    public ResponseEntity<Map<String, Object>> CreateItemFormulaEndPoint(@RequestBody ItemFormula itemFormula) {
        // Verifica se o item já existe antes de criar
        ItemFormula itemExistente = itemFormulaService.readFormulaItem(itemFormula.getNomeItem());
        
        if (itemExistente != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("resultado", "Item de fórmula já existe (retornado item existente)");
            response.put("itemFormula", itemExistente);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        
        ItemFormula itemCriado = itemFormulaService.createFormulaItem(itemFormula);
        Map<String, Object> response = new HashMap<>();
        response.put("resultado", "Item de fórmula criado com sucesso");
        response.put("itemFormula", itemCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/item-formula/create-list")
    public ResponseEntity<Map<String, Object>> CreateItemFormulaListEndPoint(@RequestBody List<ItemFormula> itensFormula) {
        List<ItemFormula> itensCriados = itemFormulaService.createFormulaItems(itensFormula);
        Map<String, Object> response = new HashMap<>();
        response.put("resultado", "Lista de itens de fórmula processada com sucesso");
        response.put("itemFormulaList", itensCriados);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/item-formula/read/{nameItemFormula}")
    public ResponseEntity<Map<String, Object>> ReadItemFormulaEndPoint(@PathVariable String nameItemFormula) {
        ItemFormula itemEncontrado = itemFormulaService.readFormulaItem(nameItemFormula);
        Map<String, Object> response = new HashMap<>();
        
        if (itemEncontrado != null) {
            response.put("resultado", "Item de fórmula encontrado com sucesso");
            response.put("itemFormula", itemEncontrado);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("resultado", "Item de fórmula não encontrado");
            response.put("itemFormula", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/item-formula/delete/{nameItemFormula}")
    public ResponseEntity<Map<String, Object>> DeleteItemFormulaEndPoint(@PathVariable String nameItemFormula) {
        ItemFormula itemExistente = itemFormulaService.readFormulaItem(nameItemFormula);
        
        if (itemExistente == null) {
            Map<String, Object> response = new HashMap<>();
            response.put("resultado", "Item de fórmula não encontrado para deleção");
            response.put("nomeItemFormula", nameItemFormula);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        
        itemFormulaService.deleteFormulaItem(nameItemFormula);
        Map<String, Object> response = new HashMap<>();
        response.put("resultado", "Item de fórmula deletado com sucesso");
        response.put("nomeItemFormula", nameItemFormula);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/item-formula/update/{nameItemFormula}")
    public ResponseEntity<Map<String, Object>> UpdateItemFormulaEndPoint(@PathVariable String nameItemFormula, @RequestBody ItemFormula itemFormula) {
        ItemFormula itemAtualizado = itemFormulaService.updateFormulaItem(nameItemFormula, itemFormula);
        Map<String, Object> response = new HashMap<>();
        
        if (itemAtualizado != null) {
            response.put("resultado", "Item de fórmula atualizado com sucesso");
            response.put("itemFormula", itemAtualizado);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            response.put("resultado", "Item de fórmula não encontrado para atualização");
            response.put("itemFormula", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    // Aqui ela irá coletar todos  os itens q possuem
    @GetMapping("/formula-tree/read/{idFormula}")
    public ResponseEntity<Map<String, Object>> ReadFormulaEndPoint(@PathVariable String idFormula) {
        List<ItemFormula> itensFormula = itemFormulaService.readAllFormulaItens(idFormula);

        if (itensFormula.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("resultado", "Nenhum item de fórmula encontrado para a fórmula");
            response.put("idFormula", idFormula);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        TreeNode treeNode = TreeNode.buildTree(itensFormula);
        Map<String, Object> response = new HashMap<>();
        response.put("resultado", "Árvore de fórmula lida com sucesso");
        response.put("total", TreeNode.calcularTotal(treeNode));
        response.put("treeNode", treeNode.getRaiz());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
