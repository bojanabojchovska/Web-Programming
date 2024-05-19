package org.example.cinema.web;

import org.example.cinema.model.Movie;
import org.example.cinema.model.Production;
import org.example.cinema.service.ProductionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productions")
public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping
    public String getProductionPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        List<Production> productions = this.productionService.findAll();
        model.addAttribute("productions", productions);
        model.addAttribute("bodyContent", "listProductions");
        return "master";
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProduction(Model model) {
        model.addAttribute("bodyContent", "formProductions");
        return "master";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("production", this.productionService.findById(id));
        model.addAttribute("bodyContent", "formProductions");
        return "master";
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public String saveMovie(@RequestParam String name,
                            @RequestParam String country,
                            @RequestParam String address) {
        this.productionService.create(name, country, address);
        return "redirect:/productions";
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam String country,
                         @RequestParam String address) {
        this.productionService.update(id,name,country,address);
        return "redirect:/productions";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        this.productionService.delete(id);
        return "redirect:/productions";
    }
}
