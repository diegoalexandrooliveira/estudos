package br.com.diego.estudos.ListaVip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.diego.estudos.ListaVip.model.Convidado;
import br.com.diego.estudos.ListaVip.repository.ConvidadoRepository;

@Service
public class ConvidadoService {

	@Autowired
	private ConvidadoRepository repository;

	public Iterable<Convidado> listarTodosConvidados() {
		return repository.findAll();
	}

	public void salvarConvidado(Convidado convidado) {

		repository.save(convidado);

	}

}
