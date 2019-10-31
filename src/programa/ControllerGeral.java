package programa;

public class ControllerGeral {
	
	private AtividadeController atividadeController;
	private PesquisaController pesquisaController;
	private ProblemaObjetivoController problemaObjetivoController;
	private PesquisadorController pesquisadorController;
	
	public ControllerGeral(AtividadeController atividadeController, PesquisaController pesquisaController,
			ProblemaObjetivoController problemaObjetivoController, PesquisadorController pesquisadorController) {
		this.atividadeController = atividadeController;
		this.pesquisaController = pesquisaController;
		this.problemaObjetivoController = problemaObjetivoController;
		this.pesquisadorController = pesquisadorController;
	}
	
	
	
	
}
