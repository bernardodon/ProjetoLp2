package Repositorios;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Pesquisador;

/**
 * Representacao de um Repositorio de Pesquisaores
 * @author Hiarly Fernandes de Souto
 *
 */
public class PesquisadoresRepositorio implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6646108745654065138L;
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
	 * Pega um pesquisador do Map de pesquisadores
	 * @param email O email do pesquisador
	 * @param mensagem A mensaegm que sera laçada na excessao caso o pesquisador nao exista
	 * @return Retorna um pesquisador
	 */
	public Pesquisador getPesquisador(String email, String mensagem) {
		if (pesquisadores.containsKey(email)) {
			return pesquisadores.get(email);
		} else {
			throw new IllegalArgumentException(mensagem);
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
