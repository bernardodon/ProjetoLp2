package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Atividade;

/**
 * 
 * @author Hiarly Fernandes de Souto
 *
 */
public class AtividadesRepositorio {

	/**
	 * Um Mapa com as atividade
	 */
	private Map<String, Atividade> atividades;

	/**
	 * Constroi um Repositorio de Atividades
	 */
	public AtividadesRepositorio() {
		this.atividades = new HashMap<String, Atividade>();
	}

	/**
	 * Coloca uma atividade no Repositorio de Ativiadades
	 * 
	 * @param chave     A chave (String) do elemento que sera adicionado
	 * @param atividade A Atividade que se deseja armazenar (Value no mapa)
	 */
	public void put(String chave, Atividade atividade) {
		this.atividades.put(chave, atividade);
	}

	/**
	 * Pega uma atividade a partir da chave do elemento
	 * 
	 * @param chave A chave que do elemento que se deseja obter
	 * @return Retorna uma Atividade caso a chave existe, se nao, uma excessao sera
	 *         lançada.
	 */
	public Atividade getAtividade(String chave) {
		checaExistenciaAtividade(chave); 
			return atividades.get(chave);
		
	}

	/**
	 * Pega os valores do Map de Atividades
	 * 
	 * @return Retorna uma collection com todos os Values (Atividades) do Map
	 */
	public Collection<Atividade> getValues() {
		return atividades.values();
	}

	/**
	 * Remove um elemento do mapa
	 * 
	 * @param chave A chave do elemento que se deseja remover
	 */
	public void remove(String chave) {
		checaExistenciaAtividade(chave);
		atividades.remove(chave);
	}

	/**
	 * Verifica se uma chave existe no mapa.
	 * @param chave A chave que se deseja verificar.
	 */
	private void checaExistenciaAtividade(String chave) {
		if (!atividades.containsKey(chave)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}
}
