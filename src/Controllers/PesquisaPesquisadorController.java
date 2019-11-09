package Controllers;

import Entidades.Pesquisa;
import Entidades.Pesquisador;
import Repositorios.PesquisadoresRepositorio;
import Repositorios.PesquisasRepositorio;
import utils.Validador;


/**
 * Representação de um controller que irá trabalhar com pesquisa e pesquisador
 * em conjunto
 * @author Hiarly Fernandes de Souto
 *
 */
public class PesquisaPesquisadorController {

	/**
	 * O repositorio de pesquisadores
	 */
	private PesquisadoresRepositorio pesquisadoresRepositorio;
	/**
	 * O repositorio de pesquiass
	 */
	private PesquisasRepositorio pesquisasRepositorio;
	/**
	 * Um validador
	 */
	private Validador validador;

	/**
	 * Controi um PesquisaPesquisadorController a partir a parir do repositorio de pesquisas
	 *  e de pesquisadores
	 * @param pesquisadoresRepositorio O repositorio de pesquisadores (Local onde se armazena os
	 * pesquisadores)
	 * @param pesquisasRepositorio O reposiorio de pesquisas (Local onde se armazena as pesquisas)
	 */
	public PesquisaPesquisadorController(PesquisadoresRepositorio pesquisadoresRepositorio,
			PesquisasRepositorio pesquisasRepositorio) {
		this.pesquisadoresRepositorio = pesquisadoresRepositorio;
		this.pesquisasRepositorio = pesquisasRepositorio;
		this.validador = new Validador();
	}

	/**
	 * Associa um Pesquisador a um Pesquisa, ou seja, adiciona uma Pesquisador dentro de uma Pesquisa
	 * @param idPesquisa O Identificador da pesquisa que será carregada com um pesquisador
	 * @param emailPesquisador O email do pesquisador que será colocado dentro da Pesquisa
	 * @return Retorna um boolean sobre a operacao. True caso a operação tenha sido bem sucessida, 
	 * ou false caso a operacao nao tenha sido realizada com sucesso.
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		Pesquisador pesquisador = pesquisadoresRepositorio.getPesquisador(emailPesquisador);
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaPesquisa(pesquisa);
		return pesquisa.adicionarPesquisador(pesquisador);

	}


	/**
	 * Dessaassocia um Pesquisador a um Pesquisa, ou seja, remove uma Pesquisador dentro de uma Pesquisa
	 * @param idPesquisa O Identificador da pesquisa que tera um Pesquisador removido
	 * @param emailPesquisador O email do pesquisador que sra removido de dentro da Pesquisa
	 * @return Retorna um boolean sobre a operacao. True caso a operação tenha sido bem sucessida, 
	 * ou false caso a operacao nao tenha sido realizada com sucesso.
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		Pesquisador pesquisador = pesquisadoresRepositorio.getPesquisador(emailPesquisador);
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaPesquisa(pesquisa);
		return pesquisa.removerPesquisador(pesquisador);

	}
	

	/**
	 * Confere, a partir de uma pesquisa, se uma pesquisa esta desativada ou nao, lancando uma
	 * excecao caso esteja.
	 * 
	 * @param pesquisa A pesquisa que sera feita a validadcao.
	 */

	private void checaPesquisa(Pesquisa pesquisa) {
		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

}
