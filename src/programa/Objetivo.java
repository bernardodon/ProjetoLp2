package programa;

public class Objetivo{
	private String tipo;
	private String descricao;
	private int aderencia_ao_problema;
	private int viabilidade;
	private int contador = 1;

public Objetivo(String tipo, String descricao, int aderencia, int viabilidade){
	this.tipo = tipo;
	this.descricao = descricao;
	this.aderencia = aderencia;
	this.viabilidade = viabilidade;

	}

public toString(){
	int soma = this.aderencia + this.viabilidade;
	return this.codigo + " - " + this.tipo + " - " + this.descricao + " - " + soma;

	}

public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

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


}