package Entidades;

import java.io.Serializable;

import utils.Validador;

public class Objetivo implements Comparable<Objetivo>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3009522878777682723L;
	private String tipo;
	private String descricao;
	private int aderenciaProblema;
	private int viabilidade;
	private String codigo;
	private boolean associado;
	private Validador validador;

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

	public void setAssociado(boolean associado) {
		this.associado = associado;
	}

	public String toString(){
		int valor = this.aderenciaProblema + this.viabilidade;
		return this.codigo + " - " + this.tipo + " - " + this.descricao + " - " + valor;

	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	@Override
	public int compareTo(Objetivo o) {
		return o.getCodigo().compareTo(this.codigo);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Objetivo other = (Objetivo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public boolean isAssociado() {
		return associado;
	}
}