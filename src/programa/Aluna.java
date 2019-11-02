package programa;

import java.util.Locale;

import utils.Validador;

public class Aluna extends Pesquisador {

	private int semestre;
	private double iea;
	private Validador validador;

	public Aluna(String nome, String biografia, String email, String fotoURL, String funcao, int semestre, double iea) {
		super(nome, biografia, email, fotoURL, funcao);
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
			super.alteraAtributo(atributo, (String) novoValor);
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

	public Pesquisador removeEspecialidade() {
		Pesquisador pesquisador = new Pesquisador(super.nome, super.biografia, super.email, super.fotoURL,
				super.funcao);
		return pesquisador;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str += " - " + semestre + "o SEMESTRE - " + String.format(Locale.US, "%.1f", this.iea);
		return str;
	}

}
