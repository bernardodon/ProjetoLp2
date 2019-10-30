package programa;

import java.util.ArrayList;
import java.util.List;

import utils.Validador;

/**
 * Representação de uma atividade referentes a uma pesquisa científica. Toda atividade deve ter uma descricao, uma duração, um nível de risco e uma descrição do risco.
 *	
 * @author Ítalo Miguel Castor Diniz Pinheiro.
 */
public class Atividade {
	
	/**
	 * Um validador.
	 */
	private Validador validador;
	/**
	 * O codigo da atividade.
	 */
	private String codigo;
	
	/*
	 * Descrição da atividade.
	 */
	private String descricaoAtvd;
	
	/**
	 * O nível de risco de uma atividade.
	 */
	private String risco;
	
	/**
	 * A descrição do risco.
	 */
	private String descricaoRisco;
	
	/**
	 * Uma lista de itens de uma atividade.
	 */
	private List<Item> itens;
	
	/**
	 * Constrói uma atividade a partir de sua descrição, do nível do risco dela e da descrição do risco.
	 * 
	 * @param descricaoAtvd a descrição da atividade.
	 * @param risco	o nível de risco da atividade.cc
	 * @param descricaoRisco a descrição do risco da atividade.
	 * @param codigo o codigo da atividade.
	 */
	public Atividade(String descricaoAtvd, String risco, String descricaoRisco, String codigo) {
		this.validador = new Validador();
		this.codigo = codigo;
		this.descricaoAtvd = descricaoAtvd;
		this.descricaoRisco = descricaoRisco;
		this.risco =risco;
		this.itens = new ArrayList<Item>();
		
		validador.validar(descricaoAtvd, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.validar(risco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.validar(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		validador.validar(codigo, "Valor invalido do nivel do risco.");
	}
	
	/**
	 * Retorna a lista de itens que uma atividade possui.
	 * 
	 * @return a lista de itens de uma atividade.
	 */
	public List<Item> getItens() {
		return itens;
	}

	/**
	 * Adiciona um item na lista de itens.
	 * 
	 * @param i um Item.
	 */
	public void adicionaItem(Item i) {
		itens.add(i);
	}
	
	/**
	 * Retorna a quantidade de itens pendentes de uma determinada atividade.
	 * 
	 * @return o inteiro que representa a quantidade de itens pendentes.
	 */
	public int quantPendentes() {
		int cont = 0;
		for(Item i: itens) {
			if (i.getStatus() == "PENDENTE") {
				cont ++ ;
			}
		}
		return cont;
	}
	
	/**
	 * Retorna a quantidade de itens realizados. de uma determinada atividade.
	 * 
	 * @return o inteiro que representa a quantidade de itens realizados.
	 */
	public int quantRealizados() {
		int cont = 0;
		for(Item i: itens) {
			if (i.getStatus() == "REALIZADO") {
				cont ++ ;
			}
		}
		return cont;
	}
	
	
	/**
	 * Gera o hashCode de uma atividade.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Compara duas atividades a partir do codigo de cada uma. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Retorna a String que representa os itens de uma atividade.
	 * 
	 * @return a representação em String que representa todos os itens de uma atividade.
	 */
	public String exibeItens() {
		String msg = "";
		
		for(Item i: itens) {
			msg += " | " + i.toString();
		}
		return msg;
	}
	
	/**
	 * Retorna a String que representa uma atividade. A representação segue o formato "DescriçãoAtvd (Risco - DescriçãoRisco)
	 */
	@Override
	public String toString() {
		return this.descricaoAtvd + " (" + this.risco + " - " + this.descricaoRisco + ")";
	}
}
