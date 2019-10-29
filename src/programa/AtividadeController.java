package programa;

import java.util.HashMap;
import java.util.Map;
import utils.Validador;

/**
 * Repreesentação de um controller de atividades.
 * 
 * @author Ítalo Miguel Castor Diniz Pinheiro.
 */
public class AtividadeController {

	/**
	 * Um mapa de atividades.
	 */
	private Map<String, Atividade> atividades;

	/**
	 * A unidade que será acrescida a medida que as atividades são cadastradas a fim
	 * de formar o codigo de uma atividade. O codigo por sua vez seguira o padrão
	 * "A" + unidade.
	 */
	private int unidade;
	
	/**
	 * Um validador.
	 */
	private Validador validador;

	/**
	 * Constrói um controller de Atividades.
	 */
	public AtividadeController() {
		this.atividades = new HashMap<String, Atividade>();
		this.unidade = 1;
		this.validador = new Validador();
	}

	/**
	 * Cadastra uma atividade no sistema. Caso não cadastre, um erro será lançado explicando o que ocorreu.
	 * 
	 * @param descricaoAtvd descrição da atividade.
	 * @param nivelRisco o nivel de risco da atividade.
	 * @param descricaoRisco a descrição do risco da atividade.
	 */
	public void cadastraAtividade(String descricaoAtvd, String nivelRisco, String descricaoRisco) {

		validador.validar(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.validar(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		validador.validar(descricaoAtvd, "Campo Descricao nao pode ser nulo ou vazio.");

		if (!nivelRisco.equals("ALTO") && !nivelRisco.equals("BAIXO") && !nivelRisco.equals("MEDIO")) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}

		String cod = "A" + Integer.toString(unidade);
		Atividade atvd = new Atividade(descricaoAtvd, nivelRisco, descricaoRisco, cod);
		atividades.put(cod, atvd);

		unidade++;
	}
	
	/**
	 * Apaga uma atividade do sistema. Caso não apague, um erro será lançado explicando o que ocorreu. 
	 * 
	 * @param codigo o codigo de uma atividade.
	 */
	public void apagaAtividade(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (atividades.containsKey(codigo)) {
			atividades.remove(codigo);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Cadastra um item para uma determinada atividade. Caso não cadastre, um erro será lançado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo da atividade.
	 * @param item a descrição do item que será cadastrado.
	 */
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
	
	/**
	 * Retorna a representação em String de uma atividade. Caso não retorne, um erro será lançado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo da atividade.
	 * @return a representação em String de uma atividade.Caso não retorne a String, um erro será lançado explicando o que ocorreu.
	 */
	public String exibeAtividade(String codigo) {
		if (atividades.containsKey(codigo)) {
			Atividade atvd = atividades.get(codigo);
			return atvd.toString() + atvd.exibeItens();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Retorna a quantidade de itens pendentes de uma atividade. Caso não, um erro será lançado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo de uma atividade.
	 * @return a representação em inteiro da quantidade de itens pendentes de uma atividade.
	 */
	public int contaItensPendentes(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			Atividade atvd = atividades.get(codigo);
			return atvd.quantPendentes();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Retorna a quantidade de itens realizados de uma atividade. Caso não, um erro será lançado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo de uma atividade.
	 * @return a representação em inteiro da quantidade de itens realizados de uma atividade.
	 */
	public int contaItensRealizados(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (atividades.containsKey(codigo)) {
			Atividade atvd = atividades.get(codigo);
			return atvd.quantRealizados();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}
}
