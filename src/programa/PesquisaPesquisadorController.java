package programa;

import utils.Validador;

public class PesquisaPesquisadorController {

	private PesquisaController pesquisaController;
	private PesquisadorController pesquisadorController;
	private Validador validador;

	public PesquisaPesquisadorController(PesquisaController pesquisaController) {
		this.pesquisadorController = new PesquisadorController();
		this.pesquisaController = pesquisaController;
		this.validador = new Validador();
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		checaExistenciaPesquisa(idPesquisa);
		checaAtivacaoPesquisa(idPesquisa);

		Pesquisador pesquisador = pesquisadorController.getPesquisador(emailPesquisador);
		Pesquisa pesquisa = pesquisaController.getPesquisa(idPesquisa);
		return pesquisa.adicionarPesquisador(pesquisador);

	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		checaExistenciaPesquisa(idPesquisa);
		checaAtivacaoPesquisa(idPesquisa);
		Pesquisador pesquisador = pesquisadorController.getPesquisador(emailPesquisador);
		Pesquisa pesquisa = pesquisaController.getPesquisa(idPesquisa);

		return pesquisa.removerPesquisador(pesquisador);

	}

	private void checaExistenciaPesquisa(String idPesquisa) {
		if (pesquisaController.getPesquisa(idPesquisa) == null) {
			throw new IllegalArgumentException("Pesquisador nao encontrado.");
		}
	}

	private void checaAtivacaoPesquisa(String idPesquisa) {
		if (!pesquisaController.getPesquisa(idPesquisa).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}
	
	public String buscaTermoPesquisador(String termo) {
		return pesquisadorController.buscaTermoPesquisadores(termo);
	}
	
	

}
