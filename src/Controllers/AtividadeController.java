package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Entidades.Atividade;
import Entidades.Item;
import Repositorios.AtividadesRepositorio;
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
	private AtividadesRepositorio atividadeRepositorio;

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

	private int numeroDoResultadoAtividade;

	/**
	 * Constrói um controller de Atividades.
	 */
	public AtividadeController(AtividadesRepositorio atividadeRepositorio) {
		this.atividadeRepositorio = atividadeRepositorio;
		this.unidade = 1;
		this.validador = new Validador();
		this.numeroDoResultadoAtividade = 0;
	}

	/**
	 * Cadastra uma atividade no sistema. Caso não cadastre, um erro será lançado
	 * explicando o que ocorreu.
	 * 
	 * @param descricaoAtvd  descrição da atividade.
	 * @param nivelRisco     o nivel de risco da atividade.
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
		atividadeRepositorio.put(cod, atvd);

		unidade++;
	}

	/**
	 * Apaga uma atividade do sistema. Caso não apague, um erro será lançado
	 * explicando o que ocorreu.
	 * 
	 * @param codigo o codigo de uma atividade.
	 */
	public void apagaAtividade(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		atividadeRepositorio.remove(codigo);
	}

	/**
	 * Cadastra um item para uma determinada atividade. Caso não cadastre, um erro
	 * será lançado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo da atividade.
	 * @param item   a descrição do item que será cadastrado.
	 */
	public void cadastraItem(String codigo, String item) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		validador.validar(item, "Item nao pode ser nulo ou vazio.");
		Atividade atividade = atividadeRepositorio.getAtividade(codigo);
		Item i = new Item(item);
		atividade.adicionaItem(i);

	}

	/**
	 * Retorna a representação em String de uma atividade. Caso não retorne, um erro
	 * será lançado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo da atividade.
	 * @return a representação em String de uma atividade.Caso não retorne a String,
	 *         um erro será lançado explicando o que ocorreu.
	 */
	public String exibeAtividade(String codigo) {
		Atividade atividade = atividadeRepositorio.getAtividade(codigo);
		return atividade.toString() + atividade.exibeItens();

	}

	/**
	 * Retorna a quantidade de itens pendentes de uma atividade. Caso não, um erro
	 * será lançado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo de uma atividade.
	 * @return a representação em inteiro da quantidade de itens pendentes de uma
	 *         atividade.
	 */
	public int contaItensPendentes(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		Atividade atvd = atividadeRepositorio.getAtividade(codigo);
		return atvd.quantPendentes();
	}

	/**
	 * Retorna a quantidade de itens realizados de uma atividade. Caso não, um erro
	 * será lançado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo de uma atividade.
	 * @return a representação em inteiro da quantidade de itens realizados de uma
	 *         atividade.
	 */
	public int contaItensRealizados(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		Atividade atvd = atividadeRepositorio.getAtividade(codigo);

		return atvd.quantRealizados();

	}

	public String buscaTermoAtividades(String termo) {
		String msg = "";

		List<Atividade> atividadesValues = new ArrayList<Atividade>();
		atividadesValues.addAll(atividadeRepositorio.getValues());
		Collections.sort(atividadesValues);

		for (Atividade atvd : atividadesValues) {
			if (atvd.getDescricaoAtvd().toLowerCase().contains(termo.toLowerCase())) {
				msg += atvd.getCodigo() + ": " + atvd.getDescricaoAtvd() + " | ";
				numeroDoResultadoAtividade++;
			} else if (atvd.getDescricaoRisco().toLowerCase().contains(termo.toLowerCase())) {
				msg += atvd.getCodigo() + ": " + atvd.getDescricaoRisco() + " | ";
				numeroDoResultadoAtividade++;
			}
		}

		return msg;
	}

	public int getNumeroDoResultadoAtividade() {
		return numeroDoResultadoAtividade;
	}

	public boolean existeAtividade(String codigo) {
		Atividade atvd = atividadeRepositorio.getAtividade(codigo);
		if (atvd == null) {
			return false;
		} else {
			return true;
		}
	}

}
