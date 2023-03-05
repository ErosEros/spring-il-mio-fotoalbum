package com.corsojava.foto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.corsojava.foto.model.Categoria;
import com.corsojava.foto.model.Foto;
import com.corsojava.foto.repo.CategoriaRepository;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/categorie")
public class CategoriaController {
	
	@Autowired 
	CategoriaRepository categoriaRepo;
	
//	METODO INDEX
	@GetMapping
	public String index(
		Model model) {
		List<Categoria> categorie; 
		
		categorie=categoriaRepo.findAll(Sort.by("name"));
		
		model.addAttribute("ElencoCategorie" , categorie);
		return "/createCategoria";
	}
	
//	METODO CREATE 
	@GetMapping("/create") // GESTISCO LE RICHIESTE GET /foto/create
	public String create(Model model) {
		Categoria categoria=new Categoria();
		
		
		model.addAttribute("categoria" , categoria);
		
		return "/editCategoria";
		
		
	}
	
//	METODO STORE
	@PostMapping("/create")  // /categorie/create
	public String store(
		@Valid @ModelAttribute("categorie") Categoria formCategoria, 
		BindingResult bindingResult,
		Model model){

		if (bindingResult.hasErrors())
			return "createCat";

		categoriaRepo.save(formCategoria);

		return "redirect:/categorie"; 
	}
}
