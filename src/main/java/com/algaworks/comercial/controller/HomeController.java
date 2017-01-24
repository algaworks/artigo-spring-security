package com.algaworks.comercial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String index() {
		return "inicio";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/vendas")
	public String vendasLista() {
		return "vendas";
	}
	
	@GetMapping("/vendas/relatorios/custos")
	public String vendasRelatorioMensal() {
		return "vendas-relatorio-custos";
	}
	
	@GetMapping("/vendas/relatorios/equipe")
	public String vendasRelatorioEquipe() {
		return "vendas-relatorio-equipe";
	}
}