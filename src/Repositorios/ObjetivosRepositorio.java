package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Objetivo;

public class ObjetivosRepositorio {

	private Map<String, Objetivo> objetivos;
	
	public ObjetivosRepositorio() {
		this.objetivos = new HashMap<String, Objetivo>();
	}

	public void put(String chave, Objetivo objetivo) {
		this.objetivos.put(chave, objetivo);
	}

	public Objetivo getObjetivo(String chave) {
		checaExistenciaObjetivo(chave);
		return objetivos.get(chave);
	}

	public void remove(String chave) {
		checaExistenciaObjetivo(chave);
		objetivos.remove(chave);
	}
	
	public Collection<Objetivo> getValues() {
		return objetivos.values();
	}

	private void checaExistenciaObjetivo(String chave) {
		if (!objetivos.containsKey(chave)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
	}
}
