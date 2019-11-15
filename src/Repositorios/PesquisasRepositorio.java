package Repositorios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import Entidades.Pesquisa;
import utils.ObjetivosComparator;

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
	
	/**
	 * Coloca todas pesquisas em uma String ordenadas a partir dos codigos dos
	 * objetivos.
	 * 
	 * @return Retorna a lista de pesquisas ordenadas pelos objetivos.
	 */
	public String listaPesquisasPorCodigoObjetivos() {
		String listaPesquisas = "";
		ArrayList<Pesquisa> listaOrdenada = new ArrayList<>();
		listaOrdenada.addAll(pesquisas.values());
		Collections.sort(listaOrdenada, new ObjetivosComparator());
		for (Pesquisa pesquisa : listaOrdenada) {
			if (pesquisa.getObjetivos().size() > 0) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		Collections.sort(listaOrdenada);
		for (Pesquisa pesquisa : listaOrdenada) {
			if (!listaPesquisas.contains(pesquisa.toString())) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		listaPesquisas = listaPesquisas.substring(0, listaPesquisas.length() - 3);
		return listaPesquisas;
	}
	
	/**
	 * Coloca todas pesquisas em uma String ordenadas a partir do codigo dos
	 * problemas.
	 * 
	 * @return Retorna a lista de pesquisas ordenadas pelos problemas.
	 */
	public String listaPesquisasPorCodigoProblema() {
		String listaPesquisas = "";
		ArrayList<Pesquisa> listaOrdenada = new ArrayList<>();
		listaOrdenada.addAll(pesquisas.values());
		Collections.sort(listaOrdenada);
		for (Pesquisa pesquisa : listaOrdenada) {
			if (pesquisa.getProblema() != null) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		for (Pesquisa pesquisa : listaOrdenada) {
			if (pesquisa.getProblema() == null) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		listaPesquisas = listaPesquisas.substring(0, listaPesquisas.length() - 3);
		return listaPesquisas;
	}
	
	/**
	 * Coloca todas pesquisas em uma String ordenadas a partir do codigo das
	 * proprias pesquisas.
	 * 
	 * @return Retorna a lista de pesquisas ordenadas pelo codigo das pesquisas.
	 */
	public String listaPesquisasPorCodigoPesquisa() {
		String listaPesquisas = "";
		ArrayList<Pesquisa> listaOrdenada = new ArrayList<>();
		listaOrdenada.addAll(pesquisas.values());
		Collections.sort(listaOrdenada);
		for (Pesquisa pesquisa : listaOrdenada) {
			listaPesquisas += pesquisa.toString() + " | ";
		}
		listaPesquisas = listaPesquisas.substring(0, listaPesquisas.length() - 3);		
		return listaPesquisas;
	}
	
}
