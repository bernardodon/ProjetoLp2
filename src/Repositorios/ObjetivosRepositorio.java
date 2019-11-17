package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Objetivo;

/**
 * Representacao de um repositorio de Obejtivos
 * @author Hiarly Fernandes de Souto
 *
 */
public class ObjetivosRepositorio {

	/**
	 * Um map com os objetivos do repositorio
	 */
	private Map<String, Objetivo> objetivos;
	private int contadorObjetivos;
	
	/**
	 * Constori um repositorio de Objetivos
	 */
	public ObjetivosRepositorio() {
		this.objetivos = new HashMap<String, Objetivo>();
		this.contadorObjetivos = 1;
	}

	/**
	 * Coloca um Objetivo no Map do Repositorio
	 * @param chave A chave do elemento (uma string) que sera adicionado
	 * @param objetivo O objetivo do mapa (Value do map)
	 */
	public void put(String chave, Objetivo objetivo) {
		this.objetivos.put(chave, objetivo);
	}

	/**
	 * Pega um objetivo do map de repositorio
	 * @param chave A chave do elemento
	 * @return Retorna um Objetivo caso a chave exista, se nao, uma
	 * excessao sera lançada.
	 */
	public Objetivo getObjetivo(String chave) {
		checaExistenciaObjetivo(chave);
		return objetivos.get(chave);
	}
	
	/**
	 * Remova um elemento do Mapa do Repositorio
	 * @param chave A chave do elemento que se deseja remover
	 */
	public void remove(String chave) {
		checaExistenciaObjetivo(chave);
		objetivos.remove(chave);
	}
	
	/**
	 * Pega os valores (Objetivos) do Map 
	 * @return Retorna uma collection com todos os 
	 * values do Map
	 */
	public Collection<Objetivo> getValues() {
		return objetivos.values();
	}

	/**
	 * Verifica se uma determinada chave existe no mapa
	 * @param chave A chave que se deseja verificar
	 */
	private void checaExistenciaObjetivo(String chave) {
		if (!objetivos.containsKey(chave)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
	}
	
	public void incrementaContador() {
		this.contadorObjetivos++;
	}

	public int getContadorObjetivos() {
		return contadorObjetivos;
	}
	
}
