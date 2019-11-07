package Repositorios;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import Entidades.Atividade;

public class AtividadesRepositorio {

	private Map<String, Atividade> atividades;

	public AtividadesRepositorio() {
		this.atividades = new HashMap<String, Atividade>();
	}

	public void put(String chave, Atividade atividade) {
		this.atividades.put(chave, atividade);
	}

	public Atividade getAtividade(String chave) {
		if (!atividades.containsKey(chave)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		} else {
			return atividades.get(chave);
		}
	}

	public Collection<Atividade> getValues() {
		return atividades.values();
	}

	public void remove(String chave) {
		if (!atividades.containsKey(chave)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		atividades.remove(chave);
	}

	public void remove(Atividade atv) {
		if (!atividades.containsKey(atv)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
		atividades.remove(atv);
	}
}
