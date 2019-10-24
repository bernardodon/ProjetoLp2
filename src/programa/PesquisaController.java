package programa;

import java.util.HashMap;

import utils.Validador;

public class PesquisaController {

	private HashMap<String, Pesquisa> pesquisas;
	private Validador validador;

	public PesquisaController() {
	}

	public String cadastraPesquisa(String descricao, String campoInteresse) {
		validador.validar(descricao, "Descricao nao pode ser nula ou vazia.");

		validarCampoInteresse(campoInteresse);

		int quantPesquisas = pesquisas.size();
		String codigo = campoInteresse.substring(0, 3) + String.valueOf(quantPesquisas + 1);
		pesquisas.put(codigo, new Pesquisa(descricao, campoInteresse, codigo));
		return codigo;
	}

	public void alteraPesquisa(String codigo, String campo, String novoValor) {
		if (pesquisas.containsKey(codigo)) {

			if (campo.equals("CAMPO")) {
				validarCampoInteresse(campo);
			}
			pesquisas.get(codigo).alteraPesquisa(campo, novoValor);

		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	public void ativaPesquisa(String codigo) {
		if (pesquisas.containsKey(codigo)) {
			pesquisas.get(codigo).ativarPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	public void enceraPesquisa(String codigo, String motivo) {
		if (pesquisas.containsKey(codigo)) {
			pesquisas.get(codigo).encerrarPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	public String exibePesquisa(String codigo) {
		if (pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).toString();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	public boolean ehAtiva(String codigo) {
		if (pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).ehAtiva();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	private void validarCampoInteresse(String campoInteresse) {
		validador.validar(campoInteresse, "Formato do campo de interesse invalido.");

		String[] campos = campoInteresse.split(",");
		if (campos.length < 3) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}

		for (String campo : campos) {
			if (campos.equals("")) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
		}
	}

}
