package com.corsojava.foto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.corsojava.foto.model.Foto;
import com.corsojava.foto.repo.FotoRepository;

@Controller
@RequestMapping("/foto")
public class FotoController {
	
	
	private @Autowired FotoRepository fotoRepo;
	
	@GetMapping
	public String index(Model model) {
		List<Foto> elencoFoto = fotoRepo.findAll();
		model.addAttribute("ElencoFoto" , elencoFoto);
		return "index";
	}
}
