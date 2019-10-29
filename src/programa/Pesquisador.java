package programa;

/**
 * Representacao de um pesquisador.
 * @author Bernard Dantas Odon
 * 
 */
public class Pesquisador {
	
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
	
	/**
	 * Constroi um Pesquisador a partir do nome, biografia, email, fotoURL e funcao.
	 * @param nome Nome do pesquisador.
	 * @param biografia Biografia do pesquisador.
	 * @param email Email do pesquisador.
	 * @param fotoURL URL da foto do pesquisador.
	 * @param funcao Funcao exercida pelo pesquisador.
	 */
	public Pesquisador(String nome, String biografia, String email, String fotoURL, String funcao) {
		this.nome = nome;
		this.biografia = biografia;
		this.email = email;
		this.fotoURL = fotoURL;
		this.funcao = funcao;
		this.ativado = true;
	}
	
	/**
	 * Constroi um pesquisador a partir de outro pesquisador, e de um email.
	 * @param pesquisadorAntigo Outro pesquisador que servir� como alicerce para o primeiro.
	 * @param email Novo email a ser utilizado.
	 */
	public Pesquisador(Pesquisador pesquisadorAntigo, String email) {
		this.nome = pesquisadorAntigo.nome;
		this.biografia = pesquisadorAntigo.biografia;
		this.email = email;
		this.funcao = pesquisadorAntigo.funcao;
		this.ativado = pesquisadorAntigo.ativado;
		this.fotoURL = pesquisadorAntigo.fotoURL;
	}
	
	/**
	 * Muda o status de ativacao do pesquisador para true caso seja false, e lanca uma excecao caso ja esteja ativado(true). 
	 */
	public void ativa() {
		if(this.ativado == true) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		this.ativado = true;
	}
	
	/**
	 * Muda o status de ativacao do pesquisador para false caso seja true, e lanca uma excecao caso ja esteja desativado(false). 
	 */
	public void desativa() {
		if(this.ativado == false) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		this.ativado = false;
	}
	
	/**
	 * Retorna a representacao textual de um Pesquisador, no formato: "NOME (FUNÇÃO) - BIOGRAFIA - EMAIL - FOTO".
	 */
	@Override
	public String toString() {
		return nome + " (" + funcao + ") - " + biografia + " - " + email + " - " + fotoURL;
	}
	
	public boolean getAtivado() {
		return this.ativado;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFotoURL() {
		return fotoURL;
	}

	public void setFotoURL(String fotoURL) {
		this.fotoURL = fotoURL;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
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
}