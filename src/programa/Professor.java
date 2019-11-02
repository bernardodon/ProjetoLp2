package programa;

import utils.Validador;

public class Professor extends Pesquisador {

	private String formacao;
	private String unidade;
	private String data;
	private Validador validador;

	public Professor(String nome, String biografia, String email, String fotoURL, String funcao, String formacao,
			String unidade, String data) {
		super(nome, biografia, email, fotoURL, funcao);
		this.validador = new Validador();

		this.formacao = formacao;
		this.data = data;
		this.unidade = unidade;
	}

	@Override
	public String toString() {
		String str = super.toString();
		str += " - " + this.formacao + " - " + this.unidade + " - " + this.data;
		return str;
	}

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
			super.alteraAtributo(atributo, novoValor);
		}
	}

	private void alteraFormacao(String novoValor) {
		validador.validar(novoValor, "Campo formacao nao pode ser vazio ou nulo.");
		this.formacao = novoValor;
	}

	private void alteraUnidade(String novoValor) {
		validador.validar(novoValor, "Campo unidade nao pode ser vazio ou nulo.");
		this.unidade = novoValor;
	}

	private void alteraData(String novoValor) {
		validador.validar(novoValor, "Campo data nao pode ser vazio ou nulo.");
		validador.validarDataProfessor(novoValor);
		this.data = novoValor;
	}
}
