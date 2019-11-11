package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Problema;

/**
 * Representacao de um repositorio de problemas
 * @author HIarly Fernandes de Souto
 *
 */
public class ProblemasRepositorio {

	/**
	 * O Map com os problemas
	 */
	private Map<String, Problema> problemas;
	
	/**
	 * Constroi um Repositorio de Problemas
	 */
	public ProblemasRepositorio() {
		this.problemas = new HashMap<String, Problema>();
	}

	/**
	 * Adiciona um repositorio no map de problemas
	 * @param chave A chave do elemento no mapa
	 * @param problema O Problema (Valor no mapa) que sera adicionado
	 */
	public void put(String chave, Problema problema) {
		this.problemas.put(chave, problema);
	}
	
	/**
	 * Remove um problema do map de problemas
	 * @param chave A chave do elemento que sera removido
	 */
	public void remove(String chave) {
		checaInexistenciaProblema(chave);
		
		problemas.remove(chave);
	}

	/**
	 * Pega um problema do map de problemas
	 * @param chave A chave do problema
	 * @return Retorna um Problema, caso a chave existe, caso nao
	 * uma excessao sera lançada
	 */
	public Problema getProblema(String chave) {
		checaInexistenciaProblema(chave);
		return problemas.get(chave);
	}
	
	/**
	 * Pega os valores do Map de problemas
	 * @return Retorna uma collection com os problemas do mapa
	 */
	public Collection<Problema> getValues() {
		return problemas.values();
	}
	
	/**
	 * Verifica se uma determinada chave existe no map de problemas
	 * @param chave A chave que sera verificada
	 */
	private void checaInexistenciaProblema(String chave) {
		if (!problemas.containsKey(chave)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
	}
}
