package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Pesquisador;
import utils.Validador;

public class PesquisadoresRepositorio {
	private Map<String, Pesquisador> pesquisadores;
	private Validador validador;

	public PesquisadoresRepositorio() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
		this.validador = new Validador();
	}

	/**
	 * Cadastra um pesquisador no mapa de pesquisadores.
	 */
	public void put(String chave, Pesquisador pesquisador) {
		pesquisadores.put(chave, pesquisador);
	}

	/**
	 * Pega um pesquisador a partir do email.
	 * 
	 * @param email Email do pesquisador.
	 * @return Retorna um pesquisador.
	 */
	public Pesquisador getPesquisador(String email) {
		if (pesquisadores.containsKey(email)) {
			return pesquisadores.get(email);
		} else {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
	}

	public Collection<Pesquisador> getPesquisadoresValues() {
		return pesquisadores.values();
	}

	public void remove(String chave) {
		pesquisadores.remove(chave);
	}

}
