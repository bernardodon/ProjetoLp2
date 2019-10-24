package utils;

public class Validador {
	public void validar(String nome, String mensagem) {
		if (nome.trim().equals("")) {
			throw new IllegalArgumentException(mensagem);
		}
	}
}
