package programa;

public class Facade {

	private PesquisaController pesquisaController;
	private ProblemaObjetivoController problemaObjetivoController;
	private PesquisaAtividadeController pesquisaAtividadeController;
	private PesquisadorMapController pesquisadorMapController;
	private PesquisaPesquisadorController pesquisaPesquisadorController;

	private PesquisaMapController pesquisaMapController;
	private PesquisadorController pesquisadorController;

	public Facade() {
		this.pesquisaMapController = new PesquisaMapController();
		this.pesquisadorMapController = new PesquisadorMapController();
		this.pesquisaController = new PesquisaController(pesquisaMapController);
		this.pesquisadorController = new PesquisadorController(pesquisadorMapController);
		this.pesquisaAtividadeController = new PesquisaAtividadeController(pesquisaController);
		this.pesquisaPesquisadorController = new PesquisaPesquisadorController(pesquisaController);
	}

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return pesquisaController.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteúdo) {
		pesquisaController.alteraPesquisa(codigo, conteudoASerAlterado, novoConteúdo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		pesquisaController.enceraPesquisa(codigo, motivo);
	}

	public void encerraPesquisa(String codigo) {
		pesquisaController.enceraPesquisa(codigo);
	}

	public void ativaPesquisa(String codigo) {
		pesquisaController.ativaPesquisa(codigo);
	}

	public String exibePesquisa(String codigo) {
		return pesquisaController.exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return pesquisaController.ehAtiva(codigo);
	}

	public String cadastraProblema(String descricao, Integer viabilidade) {
		return problemaObjetivoController.cadastraProblema(descricao, viabilidade);
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return problemaObjetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		problemaObjetivoController.apagarProblema(codigo);

	}

	public void apagarObjetivo(String codigo) {
		problemaObjetivoController.apagarObjetivo(codigo);
	}

	public String exibeProblema(String codigo) {
		return problemaObjetivoController.exibeProblema(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return problemaObjetivoController.exibeObjetivo(codigo);
	}

	public void cadastraAtividade(String descricaoAtvd, String nivelRisco, String descricaoRisco) {
		pesquisaAtividadeController.cadastraAtividade(descricaoAtvd, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		pesquisaAtividadeController.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		pesquisaAtividadeController.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return pesquisaAtividadeController.exibeAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return pesquisaAtividadeController.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return pesquisaAtividadeController.contaItensRealizados(codigo);
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
		pesquisadorController.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return pesquisaPesquisadorController.associaPesquisador(idPesquisa, emailPesquisador);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return pesquisaPesquisadorController.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

	public String listaPesquisadores(String tipo) {
		return pesquisadorController.listaPesquisadores(tipo);
	}

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return pesquisaController.associaProblema(idPesquisa, idProblema);
	}

	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		return pesquisaController.desassociaProblema(idPesquisa, idProblema);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return pesquisaController.associaObjetivo(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return pesquisaController.desassociaObjetivo(idPesquisa, idObjetivo);
	}

	public String listaPesquisas(String ordem) {
		return pesquisaController.listaPesquisas(ordem);
	}

	public String busca(String termo) {
		return pesquisaController.busca(termo) + pesquisaPesquisadorController.buscaTermoPesquisador(termo)
				+ pesquisaProblemaObjetivoController.busca(termo) + pesquisaAtividade.busca(termo);
	}
}
