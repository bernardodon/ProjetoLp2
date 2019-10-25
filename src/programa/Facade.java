package programa;

public class Facade {
	
	private PesquisaController pesquisaController = new PesquisaController();
	private ControllerProblemaObjetivo problemaObjetivoController = new ControllerProblemaObjetivo();
	
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
	
	public String cadastraProblema(String descricao, int viabilidade) {
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



	
	
	
}
