package com.lab64.projektmsmg;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/zadania")
public class ZadanieKontroler {

    private final ZadanieSerwis zadanieSerwis;

    public ZadanieKontroler(ZadanieSerwis zadanieSerwis) {
        this.zadanieSerwis = zadanieSerwis;
    }

    @GetMapping
    public String listaZadan(@RequestParam(required = false) String status, Model model) {
        List<Zadanie> zadania;
        if ("ukonczone".equals(status)) {
            zadania = zadanieSerwis.pobierzZadaniaWedlugStatusu(true);
        } else if ("nieukonczone".equals(status)) {
            zadania = zadanieSerwis.pobierzZadaniaWedlugStatusu(false);
        } else {
            zadania = zadanieSerwis.pobierzWszystkieZadania();
        }
        model.addAttribute("zadania", zadania);
        return "lista";
    }

    @GetMapping("/nowe")
    public String formularzNowegoZadania(Model model) {
        model.addAttribute("zadanie", new Zadanie());
        return "formularz";
    }

    @PostMapping
    public String dodajZadanie(@Valid @ModelAttribute Zadanie zadanie, BindingResult result) {
        if (result.hasErrors()) {
            return "formularz";
        }
        zadanieSerwis.dodajZadanie(zadanie);
        return "redirect:/zadania";
    }

    @PostMapping("/{id}/usun")
    public String usunZadanie(@PathVariable Long id) {
        zadanieSerwis.usunZadanie(id);
        return "redirect:/zadania";
    }

    @PostMapping("/{id}/zmien-status")
    public String zmienStatus(@PathVariable Long id) {
        zadanieSerwis.zmienStatus(id);
        return "redirect:/zadania";
    }
}
