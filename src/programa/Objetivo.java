package programa;

public class Objetivo {
	
	private String tipo;
	private String descricao;
	private int aderenciaProblema;
	private int viabilidade;
	private String codigo;
	private boolean associado;

	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade, String codigo) {
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