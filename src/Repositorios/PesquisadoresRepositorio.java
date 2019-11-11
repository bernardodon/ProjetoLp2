package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Pesquisador;

/**
 * Representacao de um Repositorio de Pesquisaores
 * @author Hiarly Fernandes de Souto
 *
 */
public class PesquisadoresRepositorio {
	/**
	 * O Map de pesquisadores
	 */
	private Map<String, Pesquisador> pesquisadores;

	/**
	 * Constroi um Repositorio de Pesquisadores
	 */
	public PesquisadoresRepositorio() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
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

	/**
	 * Pega os valores do HashMap de pesquisadores
	 * @return Retorna um Collection (de Pesquisador) dos valores do HashMap de Pesquisadores
	 */
	public Collection<Pesquisador> getPesquisadoresValues() {
		return pesquisadores.values();
	}

	/**
	 * Remove um elemento do hashMap de pesquisadore
	 * @param chave O chave (em string) que sera removida
	 */
	public void remove(String chave) {
		pesquisadores.remove(chave);
	}

}
