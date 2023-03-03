package com.corsojava.foto.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corsojava.foto.model.Foto;


public interface FotoRepository extends JpaRepository<Foto, Integer> {

	List<Foto> findByTitoloLike(String keyword);
//	List<Foto> findByTagLike(String tag);
}
