package dev.krypta.ProjectDataStructure.controller;

import dev.krypta.ProjectDataStructure.service.ItemFormulaService;
import dev.krypta.ProjectDataStructure.util.ItemFormula;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class DataStructureWebController {
    private ItemFormulaService ItemFormulaService;

    @GetMapping("/formula/{idFormula}")
    public String verPaginaFormula(@PathVariable String idFormula, Model model){
        List<ItemFormula> itens = ItemFormulaService.readAllFormulaItens(idFormula);

        ItemFormula itemForm = new ItemFormula();
        itemForm.setIdFormula(idFormula);

        model.addAttribute("itensFormula", itens);
        model.addAttribute("idFormula", idFormula);
        model.addAttribute("novoItem", itemForm);

        return "pagina-formula";
    }
}

@PostMapping("/item/adicionar")
public String adicionarItem(@ModelAttribute ItemFormula novoItem){
    ItemFormulaService.createFormulaItem(novoItem);
    return "redirect:/formula/" + novoItem.getIdFormula();
}

@GetMapping("/item/editar/{nomeItem}")
public String verPaginaEditar(@PathVariable String nomeItem, Model model){
    ItemFormula item = ItemFormulaService.readFormulaItem(nomeItem);
    if (item == null){
        return "redirect:/";
    }
    model.addAttribute("itemParaEditar", item);
    return "pagina-editar";
}

@PostMapping("/item/atualizar/{nomeItemOriginal}")
public String atualizarItem(@PathVariable String nomeItemOriginal, @ModelAttribute ItemFormula itemAtualizado){
    ItemFormula itemSalvo = ItemFormulaService.updateFormulaItem(nomeItemOriginal, itemAtualizado);

    if(itemSalvo != null){
        return "redirect:/formula/" + nomeItemOriginal;
    }
    return "redirect:/item/editar/";
}

@GetMapping("/item/deletar/{nomeItem}")
public String deletarItem(@PathVariable String nomeItem){
    ItemFormula item = itemFormulaService.readFormulaItem(nomeItem);
    String idFormula = item != null ? item.getIdFormula() : null;

    itemFormulaService.deleteFormulaItem(nomeItem);

    if(idFormula != null){
        return "redirect:/formula/" + idFormula;
    }

    return "redirect:/";
}


