package utils;

import java.util.ArrayList;
import java.util.List;

public class Busca {

	private List<String> buscas;
	private Validador validador;

	public Busca() {
		this.buscas = new ArrayList<String>();
		this.validador = new Validador();
	}

	public void clearBuscas() {
		this.buscas.clear();
	}

	public void adicionaBusca(String msg) {
		buscas.add(msg);
	}

	public String resultadoDaBusca() {
		String str = "";
		if (buscas.size() == 0) {
			return "";
		}
		for (String s : buscas) {
			str += s + " | ";
		}	
			return str.substring(0, str.length() - 3);
	}

	public String busca(String termo, int numeroDoResultado) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");

		if (numeroDoResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}

		if (numeroDoResultado > buscas.size()) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		} else {
			return buscas.get(numeroDoResultado - 1);
		}
	}

	public int contaResultadosBusca(String termo) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");
		int cont = buscas.size();

		if (cont == 0) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		} else {
			return cont;
		}
	}
}
