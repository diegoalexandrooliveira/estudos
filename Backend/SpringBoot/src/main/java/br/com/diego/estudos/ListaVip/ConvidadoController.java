package br.com.diego.estudos.ListaVip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import br.com.diego.estudos.Email.Email.EmailService;
import br.com.diego.estudos.ListaVip.model.Convidado;
import br.com.diego.estudos.ListaVip.service.ConvidadoService;

@Controller
public class ConvidadoController {

	@Autowired
	private ConvidadoService service;

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("listaConvidados")
	public String listaConvidados(Model model) {

		model.addAttribute("convidados", service.listarTodosConvidados());

		return "listaConvidados";
	}

	@RequestMapping(value = "salvar", method = RequestMethod.POST)
	public String salvar(@RequestParam("nome") String nome, @RequestParam("email") String email,
			@RequestParam("telefone") String telefone, Model model) {

		service.salvarConvidado(new Convidado(nome, email, telefone));

		new EmailService().enviarEmail(nome, email);

		model.addAttribute("convidados", service.listarTodosConvidados());

		return "listaConvidados";
	}
}
