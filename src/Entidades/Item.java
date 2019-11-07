package Entidades;

/**
 * Representação de um item. Todo item deve ter um status e uma descrição.
 * 
 * @author Ítalo Miguel Castor Diniz Pinheiro.
 */
public class Item {
	
	/**
	 * Status de um item, podendo ele ser "PENDENTE" ou "REALIZADO". 
	 */
	private String status;
	
	/**
	 * A descrição de um item.
	 */
	private String descricao;
	
	/**
	 * Constrói um item a partir da descrição de um determinado item.
	 * 
	 * @param descricao a descrição do item.
	 */
	public Item(String descricao) {
		this.status = "PENDENTE";
		this.descricao = descricao;
	}

	/**
	 * Retorna a String que representa o status de um item.		
	 * @return a representação em String do status de um item.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Retorna a String que representa um item. A String segue o formato "Status - Descrição".
	 */
	@Override
	public String toString() {
		return this.status + " - "+ this.descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

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
	
	
}
