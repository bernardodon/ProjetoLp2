package programa;

import java.util.HashMap;
import java.util.Map;
import utils.Validador;

public class AtividadeController {
	private Map<String, Atividade> atividades;
	private int unidade;
	private Validador validador;

	public AtividadeController() {
		this.atividades = new HashMap<String, Atividade>();
		this.unidade = 1;
		this.validador = new Validador();
	}

	public void cadastraAtividade(String descricaoAtvd, String nivelRisco, String descricaoRisco) {
	
		validador.validar(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.validar(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		validador.validar(descricaoAtvd, "Campo Descricao nao pode ser nulo ou vazio.");

		if (!nivelRisco.equals("ALTO") && !nivelRisco.equals("BAIXO") && !nivelRisco.equals("MEDIO")) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
		
		Atividade atvd = new Atividade(descricaoAtvd, nivelRisco, descricaoRisco);
		String cod = "A" + Integer.toString(unidade);
		atividades.put(cod, atvd);
	
		unidade ++;
	}

	public void apagaAtividade(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (atividades.containsKey(codigo)) {
			atividades.remove(codigo);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	public void cadastraItem(String codigo, String item) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		validador.validar(item, "Item nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			Atividade atvd = atividades.get(codigo);
			Item i = new Item(item);
			atvd.adicionaItem(i);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	public String exibeAtividade(String codigo) {
		if (atividades.containsKey(codigo)) {
			Atividade atvd = atividades.get(codigo);
			return atvd.toString() + atvd.exibeItens();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	 public int contaItensPendentes(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio." );
		if (atividades.containsKey(codigo)) {
    		Atividade atvd = atividades.get(codigo);
    		return atvd.quantPendentes();  		
    	}else {
    		throw new IllegalArgumentException("Atividade nao encontrada");
    	}
    }

	public int contaItensRealizados(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio." );
		if (atividades.containsKey(codigo)) {
			Atividade atvd = atividades.get(codigo);
			return atvd.quantRealizados();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}
}
