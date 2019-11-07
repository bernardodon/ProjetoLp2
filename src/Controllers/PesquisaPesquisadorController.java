package Controllers;

import Entidades.Pesquisa;
import Entidades.Pesquisador;
import Repositorios.PesquisadoresRepositorio;
import Repositorios.PesquisasRepositorio;
import utils.Validador;

public class PesquisaPesquisadorController {

	private PesquisadoresRepositorio pesquisadoresRepositorio;
	private PesquisasRepositorio pesquisasRepositorio;
	private Validador validador;

	public PesquisaPesquisadorController(PesquisadoresRepositorio pesquisadoresRepositorio,
			PesquisasRepositorio pesquisasRepositorio) {
		this.pesquisadoresRepositorio = pesquisadoresRepositorio;
		this.pesquisasRepositorio = pesquisasRepositorio;
		this.validador = new Validador();
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		Pesquisador pesquisador = pesquisadoresRepositorio.getPesquisador(emailPesquisador);
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		return pesquisa.adicionarPesquisador(pesquisador);

	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		Pesquisador pesquisador = pesquisadoresRepositorio.getPesquisador(emailPesquisador);
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);

		return pesquisa.removerPesquisador(pesquisador);

	}

}
