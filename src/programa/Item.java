package programa;

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
	Item(String descricao) {
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
}
