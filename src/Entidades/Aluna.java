package Entidades;

import java.util.Locale;

import utils.Validador;

public class Aluna implements Especializacao {

	private int semestre;
	private double iea;
	private Validador validador;

	public Aluna(int semestre, double iea) {
		this.validador = new Validador();
		this.semestre = semestre;
		this.iea = iea;
	}

	@Override
	public void alteraAtributo(String atributo, String novoValor) {
		validador.validar(atributo, "Campo atributo nao pode ser vazio ou nulo.");
		if (atributo.equals("SEMESTRE")) {
			int semestre = Integer.parseInt(novoValor);
			alteraSemestre(semestre);
		} else if (atributo.equals("IEA")) {
			double iea = Double.parseDouble(novoValor);
			alteraIea(iea);
		} else {
			throw new IllegalArgumentException("Atributo invalido.");
		}
	}

	private void alteraSemestre(int novoValor) {
		validador.validaSemestreAluno(novoValor);
		this.semestre = novoValor;
	}

	private void alteraIea(double novoValor) {
		validador.validaIeaAluno(novoValor);
		this.iea = novoValor;
	}


	@Override
	public String toString() {
		return " - " + semestre + "o SEMESTRE - " + String.format(Locale.US, "%.1f", this.iea);

	}

}
