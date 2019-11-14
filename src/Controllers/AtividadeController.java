package Controllers;

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
	private AtividadesRepositorio atividadesRepositorio;

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
	public AtividadeController(AtividadesRepositorio atividadeRepositorio) {
		this.atividadesRepositorio = atividadeRepositorio;
		this.unidade = 1;
		this.validador = new Validador();
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
		atividadesRepositorio.put(cod, atvd);

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
		atividadesRepositorio.remove(codigo);
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
		Atividade atividade = atividadesRepositorio.getAtividade(codigo);
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
		Atividade atividade = atividadesRepositorio.getAtividade(codigo);
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
		Atividade atvd = atividadesRepositorio.getAtividade(codigo);
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
<<<<<<< HEAD
    	Atividade atvd = atividadeRepositorio.getAtividade(idPrecedente);
    	atvd.defineProximaAtividade(idPrecedente, idSubsquente);
=======
    	Atividade atvd = atividadesRepositorio.getAtividade(idPrecedente);
    	atvd.defineProximaAtividade(idPrecedente, idPrecedente);
>>>>>>> b70ae41a8679b29ff6b51845a444cb7ed38e6f13
    }
    
    public void tiraProximaAtividade(String idPrecedente) {
    	Atividade atvd = atividadeRepositorio.getAtividade(idPrecedente);
    	atvd.tiraProximaAtividade(idPrecedente);
    }
    
    public int contaProximos(String idPrecedente) {
    	Atividade atvd = atividadeRepositorio.getAtividade(idPrecedente);
    	return atvd.contaProximos(idPrecedente);
    }
    
    public String pegaProximo(String idAtividade, int enesimaAtividade) {
    	Atividade atvd = atividadeRepositorio.getAtividade(idAtividade);
    	return atvd.pegaProximo(idAtividade, enesimaAtividade);
    }
    
    public String pegaMaiorRiscoAtividades(String idAtividade) {
    	Atividade atvd = atividadeRepositorio.getAtividade(idAtividade);
    	return atvd.pegaMaiorRiscoAtividades(idAtividade);
    }
    

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validarDuracao(duracao);
		validador.validarItem(item);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		atividade.temItem(item);
		atividade.jaExecutado(item);
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
