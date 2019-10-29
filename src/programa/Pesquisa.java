package programa;

/**
 * Representação de uma Pesquisa
 * 
 * @author Hiarly Fernandes de Souto
 *
 */
public class Pesquisa {

	/**
	 * Um texto livre com um resumo da pesquisa a ser realizada.
	 */
	private String descricao;
	/**
	 * Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados
	 * por vírgula e ter até 255 caracteres.
	 */
	private String campoInterese;
	
	/**
	 * O códgio de indentificação da pesquisa
	 */
	private String codigo;
	/**
	 * Armaza se a pesquisa está ativa ou não
	 */
	private boolean ativa;

	/**
	 * Constroi uma pesquisa a partir da descricao, 
	 * do campo de interesse e do código de identificação da pesquisa
	 * @param descricao Um texto livre com um resumo da pesquisa a ser realizada.
	 * @param campoInterese Um marcador da área ou tema a ser colocado. Pode ter até 4 tópicos, separados
	 * por vírgula e ter até 255 caracteres.
	 * @param codigo O código de idetificação da pesquisa
	 */
	public Pesquisa(String descricao, String campoInterese, String codigo) {
		this.descricao = descricao;
		this.campoInterese = campoInterese;
		this.codigo = codigo;
		this.ativa = true;
	}

	/**
	 * Representação, em String, de uma pesquisa
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
	 * Compara duas pesquisas a partir do código
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
	 * Encerra uma pesquisa que estav aativa
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
	 * @return
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
