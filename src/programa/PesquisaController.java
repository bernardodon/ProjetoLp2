package programa;

import java.util.HashMap;
import java.util.Map;

import utils.Validador;

/**
 * Representaçao de um controllador da pesquisas
 * @author Hiarly Fernandes de Souto
 *
 */
public class PesquisaController {

	/**
	 * Um mapa com todas as pesquisas cadastradas no sistema
	 */
	private Map<String, Pesquisa> pesquisas;
	
	/**
	 * Um validador que serve para verificar os parâmetros dos métodos
	 */
	private Validador validador;

	/**
	 * Constroi um Controlador dde Pesquisa
	 */
	public PesquisaController() {
		this.pesquisas = new HashMap<String, Pesquisa>();
		this.validador = new Validador();
	}

	/**
	 * Cadastra uma pesquisa no HashMap de pesquisas
	 * @param descricao Um texto livre com um resumo da pesquisa a ser realizada.
	 * @param campoInterese Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados
	 * por vírgula e ter até 255 caracteres.
	 * @return Retorna o código de identificação da pesquisa no HashMap
	 */
	public String cadastraPesquisa(String descricao, String campoInteresse) {
		validador.validar(descricao, "Descricao nao pode ser nula ou vazia.");

		validarCampoInteresse(campoInteresse);

		int num = 1;
		for (String chave : pesquisas.keySet()) {
			if (chave.contains(campoInteresse.toUpperCase().subSequence(0, 3))) {
				num += 1;
			}
		}

		String codigo = campoInteresse.substring(0, 3) + String.valueOf(num);
		codigo = codigo.toUpperCase();
		pesquisas.put(codigo, new Pesquisa(descricao, campoInteresse, codigo));
		return codigo;
	}

	/**
	 * Alera uma pesquisa que ja foi cadastrada
	 * @param codigo O código da pesquisa que se deseja alterar
	 * @param campo O campo a ser alterado na pesquisa,
	 * campo de Interesse ou descrição
	 * @param novoValor O novo valor do campo selecinado
	 */
	public void alteraPesquisa(String codigo, String campo, String novoValor) {
		if (pesquisas.containsKey(codigo)) {

			if (campo.equals("CAMPO")) {
				validarCampoInteresse(novoValor);
			}
			pesquisas.get(codigo).alteraPesquisa(campo, novoValor);

		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

	}

	/**
	 * Ativa uma pesquisa que já esta cadastrada no sistema e esta
	 * desativada
	 * @param codigo O código da pesquisa que se deseja ativar
	 */
	public void ativaPesquisa(String codigo) {
		if (pesquisas.containsKey(codigo)) {
			pesquisas.get(codigo).ativarPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Encerra uma pesquisa que está cadastrada no sistema e esta
	 * ativada
	 * @param codigo O código da pesqusia
	 * @param motivo O motivo do encerramento
	 */
	public void enceraPesquisa(String codigo, String motivo) {
		if (pesquisas.containsKey(codigo)) {
			pesquisas.get(codigo).encerrarPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	

	/**
	 * Encerra uma pesquisa que está cadastrada no sistema e esta
	 * ativada
	 * @param codigo O código da pesqusia
	 */
	public void enceraPesquisa(String codigo) {
		if (pesquisas.containsKey(codigo)) {
			pesquisas.get(codigo).encerrarPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Exibe uma pesquisa que está cadastrada no sistema
	 * @param codigo O código da pesquisa
	 * @return Retorna uma representação em String 
	 * da pesqusa buscada
	 */
	public String exibePesquisa(String codigo) {
		if (pesquisas.containsKey(codigo)) {
			System.out.println(pesquisas.get(codigo));
			return pesquisas.get(codigo).toString();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Verifica se uma pesquisa cadastrada no sistema esta ativada
	 * @param codigo O código da pesquisa
	 * @return Retorna true, cado a pesquisa esteja ativa. Caso
	 * esteja desativada retorna false
	 */
	public boolean ehAtiva(String codigo) {
		validador.validar(codigo, "Codigo nao pode ser nulo ou vazio.");
		
		if (pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).ehAtiva();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Valida o Campo de interesse de acordo com os critérios definidos
	 * @param campoInteresse O valor, em String, do campo de interesse que se
	 * deseja validar	
	 */
	private void validarCampoInteresse(String campoInteresse) {
		validador.validar(campoInteresse, "Formato do campo de interesse invalido.");

		if (campoInteresse.length() >= 255) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}

		String[] campos = campoInteresse.split(",");
		if (campos.length > 4) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}
		for (String campo : campos) {

			if (campo.length() < 3) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}

			if (campo.equals("")) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
		}
	}

}
