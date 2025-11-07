package dev.krypta.ProjectDataStructure.controller;

import dev.krypta.ProjectDataStructure.service.ItemFormulaService;
import dev.krypta.ProjectDataStructure.util.ItemFormula;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/web")
public class DataStructureWebController {

    private ItemFormulaService itemFormulaService;

    @GetMapping("/principal")
    public String mostrarPaginaPrincipal(Model model) {
        if (!model.containsAttribute("itemAdicionar")) {
            model.addAttribute("itemAdicionar", new ItemFormula());
        }
        return "pagina-principal";
    }

    @PostMapping("/adicionar")
    public String adicionarItem(@ModelAttribute ItemFormula item, RedirectAttributes redirectAttributes) {
        try {
            itemFormulaService.createFormulaItem(item);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Item '" + item.getNomeItem() + "' adicionado com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao adicionar item: " + e.getMessage());
        }
        return "redirect:/web/principal";
    }

    @GetMapping("/alterar")
    public String mostrarPaginaAlterar(Model model) {
        model.addAttribute("itemAlterar", new ItemFormula());
        return "pagina-alterar";
    }

    @PostMapping("/alterar")
    public String alterarItem(@ModelAttribute ItemFormula item, RedirectAttributes redirectAttributes) {
        String nomeItemChave = item.getNomeItem();
        try {
            ItemFormula itemSalvo = itemFormulaService.updateFormulaItem(nomeItemChave, item);
            if (itemSalvo == null) {
                redirectAttributes.addFlashAttribute("mensagemErro", "Item '" + nomeItemChave + "' não encontrado.");
            } else {
                redirectAttributes.addFlashAttribute("mensagemSucesso", "Item '" + nomeItemChave + "' atualizado com sucesso!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao atualizar item: " + e.getMessage());
        }
        return "redirect:/web/principal";
    }

    @GetMapping("/excluir")
    public String mostrarPaginaExcluir(Model model) {
        model.addAttribute("itemExcluir", new ItemFormula());
        return "pagina-excluir";
    }

    @PostMapping("/excluir")
    public String excluirItem(@ModelAttribute ItemFormula item, RedirectAttributes redirectAttributes) {
        try {
            itemFormulaService.deleteFormulaItem(item.getNomeItem());
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Item '" + item.getNomeItem() + "' excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir item: " + e.getMessage());
        }
        return "redirect:/web/principal";
    }

    @GetMapping("/buscar")
    public String mostrarPaginaBuscar() {
        return "pagina-buscar";
    }

    @GetMapping("/resultados")
    public String mostrarPaginaResultados(@RequestParam String idFormula, Model model) {
        List<ItemFormula> itens = itemFormulaService.readAllFormulaItens(idFormula);
        model.addAttribute("itens", itens);
        model.addAttribute("idFormulaBuscada", idFormula);
        return "pagina-resultados";
    }
}