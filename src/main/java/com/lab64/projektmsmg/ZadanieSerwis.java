package com.lab64.projektmsmg;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZadanieSerwis {

    private final ZadanieRepozytorium zadanieRepozytorium;

    public ZadanieSerwis(ZadanieRepozytorium zadanieRepozytorium) {
        this.zadanieRepozytorium = zadanieRepozytorium;
    }

    public List<Zadanie> pobierzWszystkieZadania() {
        return zadanieRepozytorium.findAll();
    }

    public List<Zadanie> pobierzZadaniaWedlugStatusu(boolean ukonczone) {
        return zadanieRepozytorium.findByUkonczone(ukonczone);
    }

    public void dodajZadanie(Zadanie zadanie) {
        zadanieRepozytorium.save(zadanie);
    }

    public void usunZadanie(Long id) {
        zadanieRepozytorium.deleteById(id);
    }

    public void zmienStatus(Long id) {
        Zadanie zadanie = zadanieRepozytorium.findById(id).orElseThrow();
        zadanie.setUkonczone(!zadanie.isUkonczone());
        zadanieRepozytorium.save(zadanie);
    }
}
