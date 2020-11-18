package com.example.daniil.SpringBootTestApp.Repo;

import com.example.daniil.SpringBootTestApp.Models.History;
import org.springframework.data.repository.CrudRepository;

public interface HistoryRepo extends CrudRepository<History, Long> {
}
