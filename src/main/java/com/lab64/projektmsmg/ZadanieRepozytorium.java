package com.lab64.projektmsmg;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZadanieRepozytorium extends JpaRepository<Zadanie, Long> {
    List<Zadanie> findByUkonczone(boolean ukonczone;
}
