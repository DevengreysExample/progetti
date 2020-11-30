package com.example;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UtenteRepository extends JpaRepository<Utente, Integer>{

	Utente findByUsername(String username);
}
