package br.com.diego.estudos.ListaVip;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ConvidadoController {

	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
