package programa;

import utils.Validador;

public class PesquisaAtividadeController {

	private PesquisaController pesquisaController;
	private AtividadeController atividadeController;
	private Validador validador;
	
	
	public PesquisaAtividadeController(PesquisaController pesquisaController) {
		this.atividadeController = new AtividadeController();
		this.pesquisaController = pesquisaController;
		this.validador = new Validador();
	}
	
	public void cadastraAtividade(String descricaoAtvd, String nivelRisco, String descricaoRisco) {
		atividadeController.cadastraAtividade(descricaoAtvd, nivelRisco, descricaoRisco);
	}
	
	public void apagaAtividade(String codigo) {
		atividadeController.apagaAtividade(codigo);
	}
	
	public boolean existeAtividade(String codigo) {
		return atividadeController.existeAtividade(codigo);
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
	

	//---------------
	//Associação pesquisa-atividade abaixo:
	
	
	
	
	
	
	
	
	
	
	
	//-----------------------------------------
	
	//Busca abaixo:
	
	public String busca(String termo) {
		return atividadeController.busca(termo);
	}
	
}
