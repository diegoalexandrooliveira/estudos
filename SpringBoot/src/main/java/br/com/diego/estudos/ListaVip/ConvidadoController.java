package br.com.diego.estudos.ListaVip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.diego.estudos.ListaVip.model.Convidado;
import br.com.diego.estudos.ListaVip.repository.ConvidadoRepository;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoRepository repository;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("listaConvidados")
	public String listaConvidados(Model model) {

		Iterable<Convidado> convidados = repository.findAll();

		model.addAttribute("convidados", convidados);

		return "listaConvidados";
	}
}
