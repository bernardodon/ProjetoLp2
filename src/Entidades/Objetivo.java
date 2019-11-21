package Entidades;

import java.io.Serializable;

import utils.Validador;

/**
 * 
 * @author Vincius Trindade
 *
 */
public class Objetivo implements Comparable<Objetivo>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3009522878777682723L;
	/**
	 * O tipo do Objetivo (Geral ou Especifico)
	 */
	private String tipo;
	/**
	 * A descricao de uma atividade
	 */
	private String descricao;
	/**
	 * A aderencia do Obejtivo (inteiro de 1 a 5)
	 */
	private int aderenciaProblema;
	/**
	 * A viabilidade do Obejetivo (Inteiro de 1 a 5)
	 */
	private int viabilidade;
	/**
	 * O codigo do Obejtivo
	 */
	private String codigo;
	/**
	 * Um atributo que serve para informar que o obejtivo esta associado (ou nao)
	 */
	private boolean associado;
	/**
	 * Um atributo que serve para fazer as validacoes
	 */
	private Validador validador;

	/**
	 * Representacao de um Objetivo
	 * @param tipo O Tipo de um Objetivo (inteiro de 1 a 5)
	 * @param descricao A descricao do Obejtivo
	 * @param aderencia A aderencia do objetivo
	 * @param viabilidade A vibialidade (inteiro de 1 a 5)
	 * @param codigo O codigo do Objetivo
	 */
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String codigo) {
		this.validador = new Validador();
		validador.validar(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderenciaProblema = aderencia;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
		this.associado = false;
	}

	/**
	 * Altera o atributo associado
	 * @param associado um boolean para ser colocado no atributo associado
	 */
	public void setAssociado(boolean associado) {
		this.associado = associado;
	}

	/**
	 * A representacao de um Objetivo
	 */
	public String toString(){
		int valor = this.aderenciaProblema + this.viabilidade;
		return this.codigo + " - " + this.tipo + " - " + this.descricao + " - " + valor;

	}

	/**
	 * Pega a descricao de um Obejtivo
	 * @return Retorna uma string com a descricao do objetivo
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Pega o codigo do Objetivo
	 * @return Retorna uma String com o codigo da atividade
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Compara dois Objetivos pelo codigo
	 */
	@Override
	public int compareTo(Objetivo o) {
		return o.getCodigo().compareTo(this.codigo);
	}
	
	/**
	 * Gera o HashCode do Obejtivo
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Compara dois Objetivos pelo codigo
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Objetivo other = (Objetivo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Retorna o atributo isAssociado do Obejtivo
	 * @return Retorna um boolean
	 */
	public boolean isAssociado() {
		return associado;
	}
}