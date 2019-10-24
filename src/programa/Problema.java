package programa;


public class Problema{

	private int descricao;
	private int viabilidade;

	

	public Problema(int descricao, int viabilidade){
		this.descricao = descricao;
		this.viabilidade = viabilidade;

	}

	public String toString(){
		return this.codigo+ " - " + this.descricao + " - " + this.viabilidade;
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

