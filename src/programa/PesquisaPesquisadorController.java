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

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		pesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		pesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	public String exibePesquisador(String email) {
		return pesquisadorController.exibePesquisador(email);
	}

	public void ativaPesquisador(String email) {
		pesquisadorController.ativaPesquisador(email);
	}

	public void desativaPesquisador(String email) {
		pesquisadorController.desativaPesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return pesquisadorController.pesquisadorEhAtivo(email);
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		pesquisadorController.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		pesquisadorController.cadastratEspecialidadeProfessor(email, formacao, unidade, data);
	}
	
	public String listaPesquisadores(String tipo) {
		return pesquisadorController.listaPesquisadores(tipo);
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

}
