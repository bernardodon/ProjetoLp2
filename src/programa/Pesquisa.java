package programa;

public class Pesquisa {

	private String descricao;
	private String campoInterese;
	private String codigo;
	private boolean ativa;

	public Pesquisa(String descricao, String campoInterese, String codigo) {
		this.descricao = descricao;
		this.campoInterese = campoInterese;
		this.codigo = codigo;
		this.ativa = true;
	}

	@Override
	public String toString() {
		return codigo + " - " + this.descricao + " - " + campoInterese + " ";
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
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	public void encerrarPesquisa() {
		if (this.ativa == true) {
			ativa = false;
		} else {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

	public void ativarPesquisa() {
		if (ativa == true) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		} else {
			ativa = false;
		}
	}

	public boolean ehAtiva() {
		return this.ativa;
	}

	public void alteraPesquisa(String campo, String novoValor) {
		if (ativa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (campo.equals("descricao")) {
			if (novoValor.trim().equals("")) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
			this.descricao = novoValor;
		
		} else if (campo.equals("campoDeInteresse")) {
			this.campoInterese = novoValor;
		
		} else {
			throw new IllegalArgumentException("Campo de interesse invalido.");
		}
	}

}
