package com.corsojava.foto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.corsojava.foto.model.Categoria;

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
		
		categorie=categoriaRepo.findAll();
		
		model.addAttribute("categoria" , categorie);
		return "/indexCategoria";
	}

//	METODO CREATE 
	@GetMapping("/create") // GESTISCO LE RICHIESTE GET /foto/create
	public String create(Model model) {
		Categoria categoria=new Categoria();
		
		
		model.addAttribute("categoria" , categoria);
		
		return "/createCategoria";
		
		
	}
	
//	METODO STORE
	@PostMapping("/create")  
	public String store(
		@Valid @ModelAttribute("categorie") Categoria formCategoria, 
		BindingResult bindingResult,
		Model model){

		if (bindingResult.hasErrors())
			return "createCategoria";

		categoriaRepo.save(formCategoria);

		return "redirect:/categorie"; 
	}
	
//	METODO EDIT 
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		
		Categoria categoria;
		categoria=categoriaRepo.getReferenceById(id);
		model.addAttribute("categoria",categoria);
		return "/editCategoria";
	}
	
//	METODO UPDATE
	@PostMapping("/edit/{id}")
	public String update(@Valid @ModelAttribute Categoria formCategoria,
	BindingResult bindingResult,
	Model model ) {
		
//	VALIDAZIONE
	if(bindingResult.hasErrors())
		return "/editCategoria";
	
//	Salvo il nuovo oggetto formFoto
	categoriaRepo.save(formCategoria);

		return "redirect:/categorie";
	}
	
//	METODO DELETE
	@PostMapping("/delete/{id}") 
	public String delete(@PathVariable("id") Integer id) {
		categoriaRepo.deleteById(id);
		return "redirect:/categorie";
		
	}
}
