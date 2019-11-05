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
	private String nome;
	/**
	 * Biografia do pesquisador.
	 */
	private String biografia;
	/**
	 * Email do pesquisador.
	 */
	private String email;
	/**
	 * URL da foto do pesquisador.
	 */
	private String fotoURL;
	/**
	 * Funcao exercida pelo pesquisador.
	 */
	private String funcao;
	/**
	 * Status da ativacao do pesquisador.
	 */
	private boolean ativado;

	private Especializacao especializacao;
	
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
		this.especializacao = null;
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
		String str = nome + " (" + funcao + ") - " + biografia + " - " + email + " - " + fotoURL;
		if (especializacao != null) {
			str += especializacao.toString();
		}
		
		return str;
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

	
	public void addPesquisa(Pesquisa pesquisa) {
		pesquisas.add(pesquisa);
	}

	public void adicionaEspecialidadeProfessor(String formacao, String unidade, String data) {
		validador.validar(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validador.validar(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		validador.validarDataProfessor(data);
		this.especializacao = new Professor(formacao, unidade, data);
	}
	
	public void adicionarEspecialidadeAluno(int semestre, double IEA) {
		validador.validaIeaAluno(IEA);
		validador.validaSemestreAluno(semestre);
		this.especializacao = new Aluna(semestre, IEA);
	}
	
	public void removerEspecialidadeProfessor() {
		this.especializacao = null;
	}
	
	public void removerEspecializacaoAluno() {
		this.especializacao = null;
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
		case "FORMACAO":
			especializacao.alteraAtributo(atributo, novoValor);
			break;
		case "UNIDADE":
			especializacao.alteraAtributo(atributo, novoValor);
			break;
		case "DATA":
			especializacao.alteraAtributo(atributo, novoValor);
			break;
		case "SEMESTRE":
			especializacao.alteraAtributo(atributo, novoValor);
			break;
		case "IEA":
			especializacao.alteraAtributo(atributo, novoValor);
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
