package utils;

import java.util.ArrayList;
import java.util.List;

public class Busca {

	private List<String> buscas;
	private int numeroDoResultado;
	private Validador validador;

	public Busca() {
		this.buscas = new ArrayList<String>();
		this.numeroDoResultado = 0;
		this.validador = new Validador();
	}

	public void adicionaBusca(String msg) {
		System.out.println(msg);
		if (buscas.get(numeroDoResultado) == null) {
			
			buscas.add(msg);
		}
	}

	public void setNumeroDoResultado(int numeroDoResultado) {
		this.numeroDoResultado = numeroDoResultado;
	}

	public int getNumeroDoResultado() {
		return numeroDoResultado;
	}

	public String resultadoDaBusca() {
		String msg = "";

		for (String b : buscas) {
			msg += b;
		}
		return msg;
	}

	public String busca(String termo, int numeroDoResultado) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");

		if (numeroDoResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}

		if (buscas.get(numeroDoResultado).toLowerCase().contains(termo.toLowerCase())) {
			return buscas.get(numeroDoResultado);
		} else {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
	}

	public int contaResultadosBusca(String termo) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");

		int cont = 0;
		for (String b : buscas) {
			int pos = -1;
			cont = 0;
			while (true) {
				pos = b.indexOf("oc", pos + 1);
				if (pos < 0)
					break;
				cont++;
			}
		}
		if (cont == 0) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		} else {
			return cont;
		}
	}
}
