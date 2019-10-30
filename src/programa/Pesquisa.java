package programa;

import utils.Validador;

/**
 * Representaçao de uma Pesquisa
 * 
 * @author Hiarly Fernandes de Souto
 *
 */

public class Pesquisa {

	/**
	 * Uma classe validador, serve para validar as entradas nos metodos
	 */
	private Validador validador;
	/**
	 * Um texto livre com um resumo da pesquisa a ser realizada.
	 */
	private String descricao;
	/**
	 * Um marcador da area ou tema a ser colocado. Pode ter ate 4 topicos, separados
	 * por vírgula e ter ate 255 caracteres.
	 */
	private String campoInterese;
	
	/**
	 * O codgio de indentificaçao da pesquisa
	 */
	private String codigo;
	/**
	 * Armaza se a pesquisa esta ativa ou nao
	 */
	private boolean ativa;

	/**
	 * Constroi uma pesquisa a partir da descricao, 
	 * do campo de interesse e do codigo de identificaçao da pesquisa
	 * @param descricao Um texto livre com um resumo da pesquisa a ser realizada.
	 * @param campoInterese Um marcador da area ou tema a ser colocado. Pode ter ate 4 topicos, separados
	 * por vírgula e ter ate 255 caracteres.
	 * @param codigo O codigo de idetificaçao da pesquisa
	 */
	public Pesquisa(String descricao, String campoInterese, String codigo) {
		this.validador = new Validador();
		this.descricao = descricao;
		this.campoInterese = campoInterese;
		this.codigo = codigo;
		this.ativa = true;
		validador.validar(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.validar(campoInterese, "Formato do campo de interesse invalido.");
	}

	/**
	 * Representaçao, em String, de uma pesquisa
	 */
	@Override
	public String toString() {
		return codigo + " - " + this.descricao + " - " + campoInterese;
	}

	/**
	 * Gera o hashCode da pesquisa
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Compara duas pesquisas a partir do codigo
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Encerra uma pesquisa que esta ativa
	 */
	public void encerrarPesquisa() {
		if (this.ativa == true) {
			ativa = false;
		} else {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

	/**
	 * Ativa uma pesquisa que estava desativada
	 */
	public void ativarPesquisa() {
		if (ativa == true) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		} else {
			ativa = false;
		}
	}

	/**
	 * Verifica se a pesquisa esta ativada
	 * @return o boolean se uma conta é ativa ou não.
	 */
	public boolean ehAtiva() {
		return this.ativa;
	}

	/**
	 * Altera uma pesquisa
	 * @param campo O campo da pesquisa a ser alterado, 
	 * descricao ou campo de interesse
	 * @param novoValor O novo valor do campo selecionado
	 */
	public void alteraPesquisa(String campo, String novoValor) {
		validador.validar(campoInterese, "Formato do campo de interesse invalido.");
		if (ativa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (campo.equals("DESCRICAO")) {
			if (novoValor.trim().equals("")) {
				throw new IllegalArgumentException("Descricao nao pode ser nula ou vazia.");
			}
			this.descricao = novoValor;

		} else if (campo.equals("CAMPO")) {
			this.campoInterese = novoValor;

		} else {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}

}
