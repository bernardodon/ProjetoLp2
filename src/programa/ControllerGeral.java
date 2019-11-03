package programa;

public class ControllerGeral {

	private AtividadeController atividadeController;
	private PesquisaController pesquisaController;
	private ProblemaObjetivoController problemaObjetivoController;
	private PesquisadorController pesquisadorController;

	public ControllerGeral() {
		this.atividadeController = new AtividadeController();
		this.pesquisaController = new PesquisaController(this);
		this.problemaObjetivoController = new ProblemaObjetivoController();
		this.pesquisadorController = new PesquisadorController(this);
	}

	public PesquisaController getPesquisaController() {
		return this.pesquisaController;
	}

	public PesquisadorController getPesquisadorController() {
		return this.pesquisadorController;
	}
	
	public ProblemaObjetivoController getProblemaObjetivoController() {
		return this.problemaObjetivoController;
	}

	public Pesquisa getPesquisa(String idPesquisa) {
		return pesquisaController.getPesquisa(idPesquisa);
	}

	public Pesquisador getPesquisador(String email) {
		return pesquisadorController.getPesquisador(email);
	}
	
	public Problema getProblema(String codigo) {
		return problemaObjetivoController.getProblema(codigo);
	}
	
	public Objetivo getObjetivo(String codigo){
		return problemaObjetivoController.getObjetivo(codigo);
	}
}
