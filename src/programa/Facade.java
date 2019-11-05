package programa;

public class Facade {

	private PesquisaController pesquisaController;
	private ProblemaObjetivoController problemaObjetivoController;
	private AtividadeController atividadeController;
	private PesquisaPesquisadorController pesquisaPesquisadorController;
	private ControllerGeral controllerGeral;

	public Facade() {
		this.controllerGeral = new ControllerGeral();
		this.pesquisaController = controllerGeral.getPesquisaController();
		this.problemaObjetivoController = new ProblemaObjetivoController();
		this.atividadeController = new AtividadeController();
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
		atividadeController.cadastraAtividade(descricaoAtvd, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		atividadeController.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		atividadeController.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return atividadeController.exibeAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return atividadeController.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return atividadeController.contaItensRealizados(codigo);
	}

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		pesquisaPesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		pesquisaPesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	public String exibePesquisador(String email) {
		return pesquisaPesquisadorController.exibePesquisador(email);
	}

	public void ativaPesquisador(String email) {
		pesquisaPesquisadorController.ativaPesquisador(email);
	}

	public void desativaPesquisador(String email) {
		pesquisaPesquisadorController.desativaPesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return pesquisaPesquisadorController.pesquisadorEhAtivo(email);
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		pesquisaPesquisadorController.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		pesquisaPesquisadorController.cadastratEspecialidadeProfessor(email, formacao, unidade, data);
	}

	public void associaPesquisador(String idPesquisa, String emailPesquisador) {
		pesquisaPesquisadorController.associaPesquisador(idPesquisa, emailPesquisador);
	}

	public void desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		pesquisaPesquisadorController.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

	public String listaPesquisadores(String tipo) {
		return pesquisadorController.listaPesquisadores(tipo);
	}

}
