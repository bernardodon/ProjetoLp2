package Controllers;

import Entidades.Atividade;
import Entidades.Pesquisa;
import Repositorios.AtividadesRepositorio;
import Repositorios.PesquisasRepositorio;
import utils.Validador;

public class PesquisaAtividadeController {
	private AtividadesRepositorio atividadesRepositorio;
	private PesquisasRepositorio pesquisaRepositorio;
	private Validador validador;

	public PesquisaAtividadeController(AtividadesRepositorio atividadesRepositorio,
			PesquisasRepositorio pesquisaRepositorio) {
		this.atividadesRepositorio = atividadesRepositorio;
		this.pesquisaRepositorio = pesquisaRepositorio;
		this.validador = new Validador();
	}

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.validar(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisaRepositorio.getPesquisa(codigoPesquisa);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		atividade.associar();
		return pesquisa.adicionaAtividade(atividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.validar(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisaRepositorio.getPesquisa(codigoPesquisa);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		atividade.desassociar();
		return pesquisa.tiraAtividade(atividade);

	}

}
