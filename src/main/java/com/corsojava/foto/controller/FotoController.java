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
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.foto.model.Categoria;
import com.corsojava.foto.model.Foto;
import com.corsojava.foto.repo.CategoriaRepository;
import com.corsojava.foto.repo.FotoRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/foto")
public class FotoController {
	
	
	@Autowired 
	FotoRepository fotoRepo;
	
	@Autowired
	CategoriaRepository categoriaRepo;
	
	@GetMapping
	public String index
	(@RequestParam(name="keyword", required = false) String keyword,
	 @RequestParam(name="tag",required = false)String tag,
		Model model) {
		List<Foto> elencoFoto; 
		
		if(keyword!=null && !keyword.isEmpty())
			elencoFoto = fotoRepo.findByTitoloLikeOrderByTitolo("%"+keyword+"%");
		
		else if(tag!=null && !tag.isEmpty())
			elencoFoto = fotoRepo.findByTagLike("%"+tag+"%");
		else
			elencoFoto = fotoRepo.findAll();
		
		model.addAttribute("ElencoFoto" , elencoFoto);
		return "index";
	}
	
	@GetMapping("/{id}") // GESTISCO LE RICHIESTE foto/id
	public String detail(@PathVariable("id") Integer id, Model model) {
		Foto foto=fotoRepo.getReferenceById(id);
		model.addAttribute("foto" , foto);
		return "/detail";
	}
	
//	METODO CREATE 
	@GetMapping("/create") // GESTISCO LE RICHIESTE GET /foto/create
	public String create(Model model) {
	
//		LISTA DI CATEGORIE
		List<Categoria> categorieLista = categoriaRepo.findAll();
		model.addAttribute("categorieLista" , categorieLista);
		
		Foto foto=new Foto();
		model.addAttribute("foto", foto);
		return "/create";
	}
	
	@PostMapping("/create") // GESTISCO LE RICHIESTE POST /foto/create
	public String store(
			@Valid@ModelAttribute("foto") Foto formFoto,
			BindingResult bindingResult,
			Model model ) {	
		
//		VALIDAZIONE
		if(bindingResult.hasErrors())
			return "/create";
		
		
//		Salvo il nuovo oggetto formFoto
		fotoRepo.save(formFoto);
		return "redirect:/foto";
		
	}
	
//		METODO EDIT 
		@GetMapping("/edit/{id}")
		public String edit(@PathVariable("id") Integer id, Model model) {
			
//			LISTA DI CATEGORIE
			List<Categoria> categorieLista = categoriaRepo.findAll();
			model.addAttribute("categorieLista" , categorieLista);
			
			Foto foto;
			foto=fotoRepo.getReferenceById(id);
			model.addAttribute("foto",foto);
			return "/edit";
		}
		
//		METODO UPDATE
		@PostMapping("/edit/{id}")
		public String update(@Valid @ModelAttribute Foto formFoto,
		BindingResult bindingResult,
		Model model ) {
			
//		VALIDAZIONE
		if(bindingResult.hasErrors())
			return "/edit";
		
//		Salvo il nuovo oggetto formFoto
		fotoRepo.save(formFoto);

			return "redirect:/foto";
		}
		
//		METODO DELETE
		@PostMapping("/delete/{id}") 
		public String delete(@PathVariable("id") Integer id) {
			fotoRepo.deleteById(id);
			return "redirect:/foto";
			
		}
}
