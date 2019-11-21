package Entidades;

import java.io.Serializable;

/**
 * RepresentaÃ§Ã£o de um item. Todo item deve ter um status e uma descriÃ§Ã£o.
 * 
 * @author Ã�talo Miguel Castor Diniz Pinheiro.
 */
public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8737329226836568942L;

	/**
	 * Status de um item, podendo ele ser "PENDENTE" ou "REALIZADO".
	 */
	private String status;

	/**
	 * A descriÃ§Ã£o de um item.
	 */
	private String descricao;

	/**
	 * ConstrÃ³i um item a partir da descriÃ§Ã£o de um determinado item.
	 * 
	 * @param descricao a descriÃ§Ã£o do item.
	 */
	public Item(String descricao) {
		this.status = "PENDENTE";
		this.descricao = descricao;
	}

	/**
	 * Retorna a String que representa o status de um item.
	 * 
	 * @return a representaÃ§Ã£o em String do status de um item.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Retorna a String que representa um item. A String segue o formato "Status -
	 * DescriÃ§Ã£o".
	 */
	@Override
	public String toString() {
		return this.status + " - " + this.descricao;
	}

	public void executarItem() {

		if (this.status.equals("REALIZADO")) {
			throw new IllegalArgumentException("Item ja executado.");
		} else {
			this.status = "REALIZADO";
		}
	}

	/**
	 * Gera o hashCode de um item
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	
	/**
	 * Compara dois itens 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	/**
	 * Pega a descricao de uma atividade
	 * @return Retorna uma string da descricao da atividade
	 */
	public String getDescricao() {
		return descricao;
	}

}
