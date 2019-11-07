package Controllers;

import Entidades.Pesquisa;
import Entidades.Problema;
import Repositorios.PesquisasRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Validador;

public class PesquisaProblemaController {
	
	private PesquisasRepositorio pesquisasRepositorio;
	private ProblemasRepositorio problemasRepositorio;
	private Validador validador;
	
	public PesquisaProblemaController(PesquisasRepositorio pesquisasRepositorio, ProblemasRepositorio problemasRepositorio) {
		this.pesquisasRepositorio = pesquisasRepositorio;
		this.problemasRepositorio = problemasRepositorio;
		this.validador = new Validador();
	}
	
	/**
	 * Associa um Problema a uma Pesquisa a partir do id do problema e da pesquisa.
	 * @param idPesquisa Id da pesquisa.
	 * @param idProblema Id do problema.
	 * @return Retorna sucesso caso tenha associado com sucesso e false caso contrario.
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaDesativacao(pesquisa);
		Problema problema = problemasRepositorio.getProblema(idProblema);
		return pesquisa.associaProblema(problema);
	}
	
	/**
	 * Desassocia um Problema a uma Pesquisa a partir do id do problema e da pesquisa.
	 * @param idPesquisa Id da pesquisa.
	 * @param idProblema Id do problema.
	 * @return Retorna sucesso caso tenha associado com sucesso e false caso contrario.
	 */
	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaDesativacao(pesquisa);
		Problema problema = problemasRepositorio.getProblema(idProblema);
		return pesquisa.desassociaProblema(problema);
	}	
	
	private void checaDesativacao(Pesquisa pesquisa) {
		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}
	
	
}
