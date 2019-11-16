package Controllers;

import Entidades.Atividade;
import Entidades.Item;
import Repositorios.AtividadesRepositorio;
import utils.Validador;

/**
 * RepreesentaÃ§Ã£o de um controller de atividades.
 * 
 * @author Ã�talo Miguel Castor Diniz Pinheiro.
 */
public class AtividadeController {

	/**
	 * Um mapa de atividades.
	 */
	private AtividadesRepositorio atividadesRepositorio;

	/**
	 * A unidade que serÃ¡ acrescida a medida que as atividades sÃ£o cadastradas a fim
	 * de formar o codigo de uma atividade. O codigo por sua vez seguira o padrÃ£o
	 * "A" + unidade.
	 */
	private int unidade;

	/**
	 * Um validador.
	 */
	private Validador validador;

	/**
	 * ConstrÃ³i um controller de Atividades.
	 */
	public AtividadeController(AtividadesRepositorio atividadeRepositorio) {
		this.atividadesRepositorio = atividadeRepositorio;
		this.unidade = 1;
		this.validador = new Validador();
	}

	/**
	 * Cadastra uma atividade no sistema. Caso nÃ£o cadastre, um erro serÃ¡ lanÃ§ado
	 * explicando o que ocorreu.
	 * 
	 * @param descricaoAtvd  descriÃ§Ã£o da atividade.
	 * @param nivelRisco     o nivel de risco da atividade.
	 * @param descricaoRisco a descriÃ§Ã£o do risco da atividade.
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
		atividadesRepositorio.put(cod, atvd);

		unidade++;
	}

	/**
	 * Apaga uma atividade do sistema. Caso nÃ£o apague, um erro serÃ¡ lanÃ§ado
	 * explicando o que ocorreu.
	 * 
	 * @param codigo o codigo de uma atividade.
	 */
	public void apagaAtividade(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		atividadesRepositorio.remove(codigo);
	}

	/**
	 * Cadastra um item para uma determinada atividade. Caso nÃ£o cadastre, um erro
	 * serÃ¡ lanÃ§ado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo da atividade.
	 * @param item   a descriÃ§Ã£o do item que serÃ¡ cadastrado.
	 */
	public void cadastraItem(String codigo, String item) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		validador.validar(item, "Item nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigo);
		Item i = new Item(item);
		atividade.adicionaItem(i);

	}

	/**
	 * Retorna a representaÃ§Ã£o em String de uma atividade. Caso nÃ£o retorne, um erro
	 * serÃ¡ lanÃ§ado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo da atividade.
	 * @return a representaÃ§Ã£o em String de uma atividade.Caso nÃ£o retorne a String,
	 *         um erro serÃ¡ lanÃ§ado explicando o que ocorreu.
	 */
	public String exibeAtividade(String codigo) {
		Atividade atividade = atividadesRepositorio.getAtividade(codigo);
		return atividade.toString() + atividade.exibeItens();

	}

	/**
	 * Retorna a quantidade de itens pendentes de uma atividade. Caso nÃ£o, um erro
	 * serÃ¡ lanÃ§ado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo de uma atividade.
	 * @return a representaÃ§Ã£o em inteiro da quantidade de itens pendentes de uma
	 *         atividade.
	 */
	public int contaItensPendentes(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		Atividade atvd = atividadesRepositorio.getAtividade(codigo);
		return atvd.quantPendentes();
	}

	/**
	 * Retorna a quantidade de itens realizados de uma atividade. Caso nÃ£o, um erro
	 * serÃ¡ lanÃ§ado explicando o que ocorreu.
	 * 
	 * @param codigo o codigo de uma atividade.
	 * @return a representaÃ§Ã£o em inteiro da quantidade de itens realizados de uma
	 *         atividade.
	 */
	public int contaItensRealizados(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		Atividade atvd = atividadesRepositorio.getAtividade(codigo);

		return atvd.quantRealizados();

	}

	public boolean existeAtividade(String codigo) {
		Atividade atvd = atividadesRepositorio.getAtividade(codigo);
		if (atvd == null) {
			return false;
		} else {
			return true;
		}
	}

	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		validador.validar(idPrecedente, "Atividade nao pode ser nulo ou vazio.");
		validador.validar(idSubsquente, "Atividade nao pode ser nulo ou vazio.");
		if (atividadesRepositorio.getAtividades().containsKey(idPrecedente)
				&& atividadesRepositorio.getAtividades().containsKey(idSubsquente)) {
			Atividade atividadePrecedente = atividadesRepositorio.getAtividade(idPrecedente);
			Atividade atividadeSubsquente = atividadesRepositorio.getAtividade(idSubsquente);
			atividadePrecedente.defineProximaAtividade(atividadeSubsquente);
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}

	public void tiraProximaAtividade(String idPrecedente) {
		validador.validar(idPrecedente, "Atividade nao pode ser nulo ou vazio.");

		if (atividadesRepositorio.getAtividades().containsKey(idPrecedente)) {
			Atividade atvd = atividadesRepositorio.getAtividade(idPrecedente);
			atvd.tiraProximaAtividade();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}

	public int contaProximos(String idPrecedente) {
		validador.validar(idPrecedente, "Atividade nao pode ser nulo ou vazio.");

		if(atividadesRepositorio.getAtividades().containsKey(idPrecedente)) {
			Atividade atvd = atividadesRepositorio.getAtividade(idPrecedente);
			return atvd.contaProximos();	
		} else {
			throw new IllegalArgumentException();
		}


	}

	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		if (enesimaAtividade <= 0) {
			throw new IllegalArgumentException("EnesimaAtividade nao pode ser negativa ou zero.");
		}
		validador.validar(idAtividade, "Atividade nao pode ser nulo ou vazio.");

		Atividade atvd = atividadesRepositorio.getAtividade(idAtividade);
		return atvd.pegaProximo(enesimaAtividade).getCodigo();

	}

	public String pegaMaiorRiscoAtividades(String idAtividade) {
		validador.validar(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		if (atividadesRepositorio.getAtividades().containsKey(idAtividade)) {
			Atividade atvd = atividadesRepositorio.getAtividade(idAtividade);
			return atvd.pegaMaiorRiscoAtividades().getCodigo();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validarDuracao(duracao);
		validador.validarItem(item);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		atividade.checaInexistenciaItem(item);
		atividade.checaExecucaoItem(item);
		atividade.executarItem(item, duracao); // executaAtividade codigoAtividade="A2" item=3 duracao=8
												// mesma atividade itens diferentes
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validar(resultado, "Resultado nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		atividade.adicionarResultado(resultado);
		return atividade.tamanhoDeResultados();
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validarNumeroResultado(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		return atividade.removerResultado(numeroResultado);

	}

	public String listaResultados(String codigoAtividade) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		return atividade.listarResultados();

	}

	public int getDuracao(String codigoAtividade) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		return atividade.getDuracao();

	}
}
