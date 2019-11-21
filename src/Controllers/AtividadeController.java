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
	 * Um validador.
	 */
	private Validador validador;

	/**
	 * ConstrÃ³i um controller de Atividades.
	 */
	public AtividadeController(AtividadesRepositorio atividadeRepositorio) {
		this.atividadesRepositorio = atividadeRepositorio;
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

		String cod = "A" + Integer.toString(atividadesRepositorio.getUnidade());
		Atividade atvd = new Atividade(descricaoAtvd, nivelRisco, descricaoRisco, cod);
		atividadesRepositorio.put(cod, atvd);

		atividadesRepositorio.incrementaUnidade();
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

	/**
	 * Verifica se uma atividade existe
	 * @param codigo O código da atividade que se quer verificar
	 * @return Retorna um boolean. Verdadeiro, caso a atividade exista; falso, caso nao exista;
	 */
	public boolean existeAtividade(String codigo) {
		Atividade atvd = atividadesRepositorio.getAtividade(codigo);
		if (atvd == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Define a proxima Atividade de Uma atividade
	 * @param idPrecedente O Identificador da Atividade Precendente
	 * @param idSubsquente O Identificador da Atividade Subsequente
	 */
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

	/**
	 * Tira a proxima atividade de uma atividade
	 * @param idPrecedente O id da atividade que se deseja remover a proxima Atividade
	 */
	public void tiraProximaAtividade(String idPrecedente) {
		validador.validar(idPrecedente, "Atividade nao pode ser nulo ou vazio.");

		if (atividadesRepositorio.getAtividades().containsKey(idPrecedente)) {
			Atividade atvd = atividadesRepositorio.getAtividade(idPrecedente);
			atvd.tiraProximaAtividade();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}

	/**
	 * Conta quantas atividades existem após uma determinada atividade
	 * @param idPrecedente O id da atividade que se deseja contar os subsequentes
	 * @return Retorna um inteiro com a quantidade de proximas atividade
	 */
	public int contaProximos(String idPrecedente) {
		validador.validar(idPrecedente, "Atividade nao pode ser nulo ou vazio.");

		if(atividadesRepositorio.getAtividades().containsKey(idPrecedente)) {
			Atividade atvd = atividadesRepositorio.getAtividade(idPrecedente);
			return atvd.contaProximos();	
		} else {
			throw new IllegalArgumentException();
		}


	}

	/**
	 * Pega a proxima atividade de uma atividade
	 * @param idAtividade O id da atividade
	 * @param enesimaAtividade Qual a atividade após uma atividade deverá ser retornada
	 * @return Retorna A enesima Atividade de uma Atividade
	 */
	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		if (enesimaAtividade <= 0) {
			throw new IllegalArgumentException("EnesimaAtividade nao pode ser negativa ou zero.");
		}
		validador.validar(idAtividade, "Atividade nao pode ser nulo ou vazio.");

		Atividade atvd = atividadesRepositorio.getAtividade(idAtividade);
		return atvd.pegaProximo(enesimaAtividade).getCodigo();

	}

	/**
	 * Pega atividade de maior risco depois da atual
	 * @param idAtividade O Id da Atividade atual 
	 * @return Retorna o codigo da atividade de maior risco
	 */
	public String pegaMaiorRiscoAtividades(String idAtividade) {
		validador.validar(idAtividade, "Atividade nao pode ser nulo ou vazio.");
		if (atividadesRepositorio.getAtividades().containsKey(idAtividade)) {
			Atividade atvd = atividadesRepositorio.getAtividade(idAtividade);
			return atvd.pegaMaiorRiscoAtividades().getCodigo();
		} else {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
	}

	/**
	 * Executa um item de uma atividade
	 * @param codigoAtividade O codigo da Atividade a ser executa
	 * @param item O numero do item que vai ser executado
	 * @param duracao A duracao da execucao
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validarDuracao(duracao);
		validador.validarItem(item);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		atividade.checaInexistenciaItem(item);
		atividade.checaExecucaoItem(item);
		if(!atividade.isAssociado()) {
			throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
		}
		atividade.executarItem(item, duracao); // Atividade sem associacoes com pesquisas.
	}

	/**
	 * Cadastra um resultado em uma Atividade
	 * @param codigoAtividade O Codigo da Atividade que receberá o resultado
	 * @param resultado O resultado
	 * @return Retorna a quantidade de resultados da atividade
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validar(resultado, "Resultado nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		atividade.adicionarResultado(resultado);
		return atividade.tamanhoDeResultados();
	}
	
	/**
	 * Remove um Resultado de uma Atividade
	 * @param codigoAtividade O codigo da Atividade que tera um resultado removido
	 * @param numeroResultado O numero do resultado a ser removido
	 * @return Retorna a quantidade de resultados da atividade
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validarNumeroResultado(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		return atividade.removerResultado(numeroResultado);

	}
	
	/**
	 * Lista os resultados que há em uma atividade 
	 * @param codigoAtividade O codigo da Atividade
	 * @return Retorna um String com os resultados da atividade
	 */
	public String listaResultados(String codigoAtividade) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		return atividade.listarResultados();
	}

	/**
	 * Pega a duracao total de uma atividade
	 * @param codigoAtividade O codigo da atividade
	 * @return Retorna um inteiro com o total da duracao da atividade
	 */
	public int getDuracao(String codigoAtividade) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		return atividade.getDuracao();
	}
	
}
