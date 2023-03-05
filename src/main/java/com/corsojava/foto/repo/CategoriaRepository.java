package com.corsojava.foto.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.foto.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

	
}
