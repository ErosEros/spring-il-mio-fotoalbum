package com.corsojava.foto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.corsojava.foto.model.Foto;
import com.corsojava.foto.repo.FotoRepository;

@Controller
@RequestMapping("/foto")
public class FotoController {
	
	
	@Autowired 
	FotoRepository fotoRepo;
	
	@GetMapping
	public String index
	(@RequestParam(name="keyword", required = false) String keyword,
	 @RequestParam(name="tag",required = false)String tag,
		Model model) {
		List<Foto> elencoFoto; 
		
		if(keyword!=null && !keyword.isEmpty())
			elencoFoto = fotoRepo.findByTitoloLike("%"+keyword+"%");
		
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
		Foto foto=new Foto();
		model.addAttribute("foto", foto);
		return "/create";
	}
	
	@PostMapping("/create") // GESTISCO LE RICHIESTE POST /foto/create
	public String store(
			@ModelAttribute("foto") Foto formFoto, 
			Model model ) {		
//		Salvo il nuovo oggetto formFoto
		fotoRepo.save(formFoto);
		return "redirect:/foto";
		
	}
}
