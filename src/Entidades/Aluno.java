package Entidades;

import java.util.Locale;

import utils.Validador;

/**
 * Representacao de um Aluno (Classe que serve apenas para especializar 
 * um Pesquisador)
 * @author Hiarly Fernandes de Souto
 *
 */
public class Aluno implements Especializacao {

	/**
	 * O semestre de ingresso do alnuo
	 */
	private int semestre;
	/**
	 * O IEA (Indice de Eficiencia Academica) do aluno
	 */
	private double iea;
	/**
	 *Um validador
	 */
	private Validador validador;

	/**
	 * Controi um Aluno a partir do semeestre de ingresso e do IEA do aluno
	 * @param semestre O Semestre de ingresso do aluno
	 * @param iea O IEA(Indice de Eficiencia Academica) do aluno
	 */
	public Aluno(int semestre, double iea) {
		this.validador = new Validador();
		this.semestre = semestre;
		this.iea = iea;
	}

	/**
	 * Altera um atributo do aluno
	 */
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

	/**
	 * Altera o semestre de ingresso do aluno
	 * @param novoValor O novo semestre de ingresso do aluno
	 */
	private void alteraSemestre(int novoValor) {
		validador.validaSemestreAluno(novoValor);
		this.semestre = novoValor;
	}

	/**
	 * Altera o IEA do aluno
	 * @param novoValor O novo valor do IEA do aluno
	 */
	private void alteraIea(double novoValor) {
		validador.validaIeaAluno(novoValor);
		this.iea = novoValor;
	}

	/**
	 * Gera uma representacao em String do aluno.
	 */
	@Override
	public String toString() {
		return " - " + semestre + "o SEMESTRE - " + String.format(Locale.US, "%.1f", this.iea);

	}

}
