package Controllers;

import Entidades.Objetivo;
import Entidades.Pesquisa;
import Repositorios.ObjetivosRepositorio;
import Repositorios.PesquisasRepositorio;
import utils.Validador;

public class PesquisaObjetivoController {
	private PesquisasRepositorio pesquisasRepositorio;
	private ObjetivosRepositorio objetivosRepositorio;
	private Validador validador;
	
	public PesquisaObjetivoController(PesquisasRepositorio pesquisasRepositorio,
			ObjetivosRepositorio objetivosRepositorio) {
		this.pesquisasRepositorio = pesquisasRepositorio;
		this.objetivosRepositorio = objetivosRepositorio;
		this.validador = new Validador();
	}
	
	/**
	 * Associa um objetivo a uma pesquisa, a partir do id da pesquisa e do objetivo.
	 * 
	 * @param idPesquisa Id da pesquisa.
	 * @param idObjetivo Id do objetivo.
	 * @return Retorna false caso a associacao seja mal sucedida e sucesso caso
	 *         contrario.
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaDesativacao(pesquisa);
		Objetivo objetivo = objetivosRepositorio.getObjetivo(idObjetivo);

		boolean retorno = pesquisa.associaObjetivo(objetivo);

		if (retorno == false && objetivo.isAssociado()) {
			return retorno;
		} else if (objetivo.isAssociado()) {
			throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
		}
		objetivo.setAssociado(true);
		return retorno;
	}

	/**
	 * Dessocia um objetivo a uma pesquisa, a partir do id da pesquisa e do
	 * objetivo.
	 * 
	 * @param idPesquisa Id da pesquisa.
	 * @param idObjetivo Id do objetivo.
	 * @return Retorna false caso a desassociacao seja mal sucedida e sucesso caso
	 *         contrario.
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);

		checaDesativacao(pesquisa);
		Objetivo objetivo = objetivosRepositorio.getObjetivo(idObjetivo);

		if (!objetivo.isAssociado()) {
			return false;
		}

		pesquisa.desassociaObjetivo(objetivo);
		objetivo.setAssociado(false);
		return true;
	}


	/**
	 * Confere, a partir de uma pesquisa, se uma pesquisa esta desativada ou nao, lancando uma
	 * excecao caso esteja.
	 * 
	 * @param pesquisa A pesquisa que sera feita a validadcao.
	 */
	private void checaDesativacao(Pesquisa pesquisa) {
		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

	
}
