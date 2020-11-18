package com.example.daniil.SpringBootTestApp.Repo;

import com.example.daniil.SpringBootTestApp.Models.Currency;
import org.springframework.data.repository.CrudRepository;

public interface CurrencyRepo extends CrudRepository<Currency, Long> {
    Currency findByCharCode(String name);
}
