package Entidades;

import java.io.Serializable;

import utils.Validador;

public class Problema implements Comparable<Problema>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5945461472402258381L;
	private String  descricao;
	private int viabilidade;
	private String codigo;
	private Validador validador;
	private int contador;
	
	public Problema(String descricao,int viabilidade, String codigo) {
		this.validador = new Validador();
		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.validarPontuacao(viabilidade, "Valor invalido de viabilidade.");
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.codigo = codigo;
		this.contador = 1;
	}

	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.viabilidade;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	@Override
	public int compareTo(Problema o) {
		return o.getCodigo().compareTo(this.codigo);
	}
	
	public void incrementaContador() {
		this.contador = contador++;
	}
	
	public int getContador() {
		return contador;
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
		Problema other = (Problema) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
