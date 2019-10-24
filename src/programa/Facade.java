package programa;

public class Facade {
	
	private PesquisaController pesquisaController = new PesquisaController();
	
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
	
	public boolean ehAtiva(String codigo) {
		return pesquisaController.ehAtiva(codigo);
	}


	
	
	
}
