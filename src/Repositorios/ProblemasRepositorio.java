package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Problema;

public class ProblemasRepositorio {

	private Map<String, Problema> problemas;
	
	public ProblemasRepositorio() {
		this.problemas = new HashMap<String, Problema>();
	}

	public void put(String chave, Problema problema) {
		this.problemas.put(chave, problema);
	}
	
	public void remove(String chave) {
		checaInexistenciaProblema(chave);
		
		problemas.remove(chave);
	}

	public Problema getProblema(String chave) {
		checaInexistenciaProblema(chave);
		return problemas.get(chave);
	}
	
	public Collection<Problema> getValues() {
		return problemas.values();
	}
	
	private void checaInexistenciaProblema(String chave) {
		if (!problemas.containsKey(chave)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
	}
}
