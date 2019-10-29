package programa;

public class Item {
	private String status;
	private String descricao;
	Item(String descricao) {
		this.status = "PENDENTE";
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	@Override
	public String toString() {

		return this.status + " - "+ this.descricao;
	}
}
