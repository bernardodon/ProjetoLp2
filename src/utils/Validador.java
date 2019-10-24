package utils;

public class Validador {
	private void verificarVazio(String nome, String mensagem) {
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
	private void verificarNull(String nome, String mensagem) {
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
		verificarNull(nome, mensaegem);
		verificarVazio(nome, mensaegem);
	}
}
