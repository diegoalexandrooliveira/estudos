package br.com.diego.estudos.ListaVip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone) {

		repository.save(new Convidado(nome, email, telefone));

		return "listaConvidados";
	}
}
