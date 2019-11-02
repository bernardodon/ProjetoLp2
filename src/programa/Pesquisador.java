package programa;

import java.util.ArrayList;

import utils.Validador;

/**
 * Representacao de um pesquisador.
 * 
 * @author Bernard Dantas Odon
 * 
 */
public class Pesquisador {

	private Validador validador;
	/**
	 * Nome do pesquisador.
	 */
	protected String nome;
	/**
	 * Biografia do pesquisador.
	 */
	protected String biografia;
	/**
	 * Email do pesquisador.
	 */
	protected String email;
	/**
	 * URL da foto do pesquisador.
	 */
	protected String fotoURL;
	/**
	 * Funcao exercida pelo pesquisador.
	 */
	protected String funcao;
	/**
	 * Status da ativacao do pesquisador.
	 */
	protected boolean ativado;

	
	private ArrayList<Pesquisa> pesquisas;
	/**
	 * Constroi um Pesquisador a partir do nome, biografia, email, fotoURL e funcao.
	 *
	 * @param nome      Nome do pesquisador.
	 * @param biografia Biografia do pesquisador.
	 * @param email     Email do pesquisador.
	 * @param fotoURL   URL da foto do pesquisador.
	 * @param funcao    Funcao exercida pelo pesquisador.
	 */
	public Pesquisador(String nome, String biografia, String email, String fotoURL, String funcao) {
		this.validador = new Validador();
		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.fotoURL = fotoURL;
		this.funcao = funcao;
		this.ativado = true;
		this.pesquisas = new ArrayList<Pesquisa>();
	}

	/**
	 * Muda o status de ativacao do pesquisador para true caso seja false, e lanca
	 * uma excecao caso ja esteja ativado(true).
	 */
	public void ativa() {
		if (this.ativado == true) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		this.ativado = true;
	}

	/**
	 * Muda o status de ativacao do pesquisador para false caso seja true, e lanca
	 * uma excecao caso ja esteja desativado(false).
	 */
	public void desativa() {
		if (this.ativado == false) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		this.ativado = false;
	}

	/**
	 * Retorna a representacao textual de um Pesquisador, no formato: "NOME (FUNÇÃO)
	 * - BIOGRAFIA - EMAIL - FOTO".
	 */
	@Override
	public String toString() {
		return nome + " (" + funcao + ") - " + biografia + " - " + email + " - " + fotoURL;
	}

	public boolean getAtivado() {
		return this.ativado;
	}

	/**
	 * Retorna a representacao em inteiro da classe.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		return result;
	}

	/**
	 * Compara dois pesquisadores a partir do email.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisador other = (Pesquisador) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

	public Pesquisador alterarEmail(String novoEmail) {
		Pesquisador novoPesquisador = new Pesquisador(this.nome, this.biografia, novoEmail, this.fotoURL, this.funcao);
		return novoPesquisador;
	}

	public String getFuncao() {
		return this.funcao;
	}

	public Aluna especializarAluna(int semestre, double iea) {
		if (!this.funcao.equals("estudante")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}

		Aluna aluna = new Aluna(this.nome, this.biografia, this.email, this.fotoURL, this.funcao, semestre, iea);
		return aluna;
	}

	public Professor especializarProfessor(String formacao, String unidade, String data) {
		if (!this.funcao.equals("professor")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		} else {
			Professor professor = new Professor(this.nome, this.biografia, this.email, this.fotoURL, this.funcao,
					formacao, unidade, data);
			return professor;
		}
	}
	
	public void addPesquisa(Pesquisa pesquisa) {
		pesquisas.add(pesquisa);
	}

	public void alteraAtributo(String atributo, String novoValor) {
		validador.validar(atributo, "Atributo nao pode ser vazio ou nulo.");
		switch (atributo) {
		case "NOME":
			alteraNome(novoValor);
			break;

		case "FUNCAO":
			alteraFuncao(novoValor);
			break;

		case "BIOGRAFIA":
			alteraBiografia(novoValor);
			break;

		case "EMAIL":
			alteraEmail(novoValor);
			break;

		case "FOTO":
			alteraFotoURL(novoValor);
			break;
		default:
			throw new IllegalArgumentException("Atributo invalido.");
		}
	}

	/**
	 * Altera o URL da foto de um pesquisador.
	 * 
	 * @param email     Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pelo URL da foto.
	 */
	private void alteraFotoURL(String novoValor) {
		validador.validar(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
		validador.validarFotoPesquisador(novoValor);
		this.fotoURL = novoValor;
	}

	/**
	 * Altera o URL da foto de um pesquisador.
	 * 
	 * @param email     Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pelo URL da foto.
	 */
	private void alteraEmail(String novoValor) {
		validador.validar(novoValor, "Campo email nao pode ser nulo ou vazio.");
		validador.validarEmailPesquisador(novoValor);
		this.email = novoValor;

	}

	/**
	 * Altera a biografia de um pesquisador.
	 * 
	 * @param email     Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pela biografia do pesquisador.
	 */
	private void alteraBiografia(String novoValor) {
		validador.validar(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
		this.biografia = novoValor;
	}

	/**
	 * Altera a funcao de um pesquisador.
	 * 
	 * @param email     Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pela funcao do pesquisador.
	 */
	private void alteraFuncao(String novoValor) {
		validador.validar(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
		this.funcao = novoValor;
	}

	/**
	 * Altera o nome de um pesquisador.
	 * 
	 * @param email     Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pelo nome do pesquisador.
	 */
	private void alteraNome(String novoValor) {
		validador.validar(novoValor, "Campo nome nao pode ser nulo ou vazio.");
		this.nome = novoValor;
	}

}
