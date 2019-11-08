package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Pesquisa;

/**
 * Representação de um Repostorio de Pesquisas
 * @author Hiarly Fernandes de Souto
 *
 */
public class PesquisasRepositorio {
	/**
	 * Um Map de pesquisas
	 */
	private Map<String, Pesquisa> pesquisas;

	/**
	 * Constro um repositorio de Pesquisas
	 */
	public PesquisasRepositorio() {
		this.pesquisas = new HashMap<String, Pesquisa>();
	}
	
	/**
	 * Adiciona um novo elemento ao hashMap pesquisas
	 * @param chave A chave do elemento, uma string
	 * @param pesquisa O valor do elemento, uma pesquisa
	 */
	public void put(String chave, Pesquisa pesquisa) {
		this.pesquisas.put(chave, pesquisa);
	}
	
	public Pesquisa getPesquisa(String idPesquisa) {
		checaInexistenciaPesquisa(idPesquisa);
		return pesquisas.get(idPesquisa);
	}

	/**
	 * Confere, a partir do id, se uma pesquisa existe ou nao, lancando uma excecao caso nao exista.
	 * @param idPesquisa Id da pesquisa.
	 */
	private void checaInexistenciaPesquisa(String idPesquisa) {
		if(!pesquisas.containsKey(idPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Pega os valores do HashMap de pesquisas
	 * @return Retorna uma colleciton com as pesquisas do HashMap (Pesquisas)
	 */
	public Collection<Pesquisa> getValues() {
		return this.pesquisas.values();
	}
	
	/**
	 * Pega as chaves do HashMao de pesquisas
	 * @return Retorna uma colleciton com as chaves do hashMao (Stirngs)
	 */
	public Collection<String> getKeys() {
		return this.pesquisas.keySet();
	}
	
	
}
