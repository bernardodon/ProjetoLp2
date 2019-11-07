package programa;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import utils.Validador;

public class PesquisadorMapController {
	private Map<String, Pesquisador> pesquisadores;
	private Validador validador;

	public PesquisadorMapController() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
		this.validador = new Validador();
	}
	

	/**
	 * Cadastra um pesquisador no mapa de pesquisadores.
	 * 
	 * @param nome      Nome do pesquisador.
	 * @param funcao    Funcao do pesquisador.
	 * @param biografia Biografia do pesquisador.
	 * @param email     Email do pesquisador.
	 * @param fotoURL   URL da foto do pesquisador.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		pesquisadores.put(email, new Pesquisador(nome, biografia, email, fotoURL, funcao));
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
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
		}
	}
	
	public Collection<Pesquisador> getPesquisadoresValues() {
		return pesquisadores.values();
	}
	
	public void put(String chave, Pesquisador pesquisador) {
		pesquisadores.put(chave, pesquisador);
	}
	
	public void remove(String chave) {
		pesquisadores.remove(chave);
	}

	
	
}
