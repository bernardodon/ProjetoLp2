package programa;

import java.util.Map;

public class AtividadeMapController {

	private Map<String, Atividade> atividades;

	public AtividadeMapController(Map<String, Atividade> atividades) {
		this.atividades = atividades;
	}

	public void put(String chave, Atividade atividade) {
		this.atividades.put(chave, atividade);
	}

}
