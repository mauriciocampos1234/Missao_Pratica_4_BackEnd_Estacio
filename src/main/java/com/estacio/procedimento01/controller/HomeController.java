package com.estacio.procedimento01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/") 
	public String index(Model model) {
		model.addAttribute("msnBemVindo", "Servlet ServletProduto at /CadastroEE-war");
		return "publica-index";
	}

}
