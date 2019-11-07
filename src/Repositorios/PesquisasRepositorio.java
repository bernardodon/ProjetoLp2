package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Pesquisa;

public class PesquisasRepositorio {
	private Map<String, Pesquisa> pesquisas;

	public PesquisasRepositorio() {
		this.pesquisas = new HashMap<String, Pesquisa>();
	}
	
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
	
	public Collection<Pesquisa> getValues() {
		return this.pesquisas.values();
	}
	
	public Collection<String> getKeys() {
		return this.pesquisas.keySet();
	}
	
	
}
