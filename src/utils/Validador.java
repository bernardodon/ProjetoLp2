package utils;

public class Validador {
	private void verificarVazioString(String nome, String mensagem) {
		if (nome.trim().equals("")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Verifica se um nome está null
	 * 
	 * @param nome     O nome que se dejesa verificar
	 * @param mensagem A mensagem que mostrada caso haja um exceção
	 */
	private void verificarNullString(String nome, String mensagem) {
		if (nome == null) {
			throw new NullPointerException(mensagem);
		}
	}

	/**
	 * Valida um nome, verificando se esse está vazio ou nulo
	 * 
	 * @param nome     O nome que se deseja validar
	 * @param mensagem A mensagem que mostrada caso haja um exceção
	 */
	public void validar(String nome, String mensaegem) {
		verificarNullString(nome, mensaegem);
		verificarVazioString(nome, mensaegem);
	}

	public void validarPontuacao(int valor, String mensagem) {
		if (valor > 5 || valor < 1 || valor == 0) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Valida o URL da foto de um pesquisador, se possui no minimo o "http://" ou o
	 * "https://" no come�o.
	 * 
	 * @param fotoURL URL da foto a ser validado.
	 */
	public void validarFotoPesquisador(String fotoURL) {
		if (fotoURL.length() < 7
				|| (!fotoURL.substring(0, 7).equals("http://") && !fotoURL.substring(0, 8).equals("https://"))) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

	/**
	 * Valida o email de um pesquisador, se possui um @ e pelo menos um caractere
	 * antes e depois do @.
	 * 
	 * @param email Email do pesquisador.
	 */
	public void validarEmailPesquisador(String email) {
		if (String.valueOf(email.charAt(0)).equals("@") || email.substring(email.length() - 1).equals("@")
				|| !email.contains("@")) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
	}

	public void validarDataProfessor(String data) {
		String[] campos = data.split("/");
		if (campos.length != 3) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		} else if (campos[0].length() != 2) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		} else if (Integer.parseInt(campos[0]) > 31 || Integer.parseInt(campos[0]) < 0) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		} else if (Integer.parseInt(campos[1]) > 12 || Integer.parseInt(campos[1]) < 1) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		} else if (campos[1].length() != 2) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		} else if (campos[2].length() != 4) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}
	}

	public void validaSemestreAluno(Integer semestre) {
		if (semestre == null) {
			throw new IllegalArgumentException("Campo semestre nao pode ser nulo ou vazio.");
		}
		
		if (semestre < 1) {
			throw new IllegalArgumentException("Atributo semestre com formato invalido.");
		}
	}

	public void validaIeaAluno(Double IEA) {
		if (IEA == null) {
			throw new IllegalArgumentException("");
		}
		
		if (IEA < 0.0 || IEA > 10.0) {
			throw new IllegalArgumentException("Atributo IEA com formato invalido.");
		}
	}

}
