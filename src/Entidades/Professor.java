package Entidades;

import utils.Validador;

/**
 * Representacao de um Professor (Entidade que serve apenas para personaliazar
 * o Pesquisador)
 * @author Hiarly Fernandes de Souto
 *
 */
public class Professor implements Especializacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4136676275212023371L;
	/**
	 * A formacao do Professor
	 */
	private String formacao;
	/**
	 * A unidade que o Professor foi alocada
	 */
	private String unidade;
	/**
	 * A data de contratacao do Professor
	 */
	private String data;
	/**
	 * Um validador
	 */
	private Validador validador;

	/**
	 * Constroi um professor a partir da formacao dele, a unidade de alocacao e a data
	 * de contratacao.
	 * @param formacao A formacao do professor
	 * @param unidade A unidade de alocacao do professor
	 * @param data A data de contratacao do professor
	 */
	public Professor(String formacao, String unidade, String data) {
		this.validador = new Validador();

		this.formacao = formacao;
		this.data = data;
		this.unidade = unidade;
	}

	/**
	 * Representacao em String de um professor
	 */
	@Override
	public String toString() {
		return " - " + this.formacao + " - " + this.unidade + " - " + this.data;
	}

	/**
	 * Altera algum atributo do professor
	 */
	@Override
	public void alteraAtributo(String atributo, String novoValor) {
		validador.validar(atributo, "Atributo nao pde ser vazio ou nulo");

		if (atributo.equals("FORMACAO")) {
			alteraFormacao(novoValor);
		} else if (atributo.equals("UNIDADE")) {
			alteraUnidade(novoValor);
		} else if (atributo.equals("DATA")) {
			alteraData(novoValor);
		} else {
			throw new IllegalArgumentException("Atributo invalido.");
		}
	}

	/**
	 * Altera a formacao do professor
	 * @param novoValor O novo valor do atributo formacao
	 */
	private void alteraFormacao(String novoValor) {
		validador.validar(novoValor, "Campo formacao nao pode ser vazio ou nulo.");
		this.formacao = novoValor;
	}

	/**
	 * Altera a unidade de alocacao do professor
	 * @param novoValor O novo valor do atributo unidade 
	 */
	private void alteraUnidade(String novoValor) {
		validador.validar(novoValor, "Campo unidade nao pode ser vazio ou nulo.");
		this.unidade = novoValor;
	}

	/**
	 * Altera a data de contratacao do professor
	 * @param novoValor A nova data de contratacao do professor
	 */
	private void alteraData(String novoValor) {
		validador.validar(novoValor, "Campo data nao pode ser vazio ou nulo.");
		validador.validarDataProfessor(novoValor);
		this.data = novoValor;
	}
}
