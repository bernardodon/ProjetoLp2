package programa;

import Controllers.AtividadeController;
import Controllers.PesquisaAtividadeController;
import Controllers.PesquisaController;
import Controllers.PesquisaPesquisadorController;
import Controllers.PesquisaProblemaController;
import Controllers.PesquisadorController;
import Controllers.ProblemaObjetivoController;
import Repositorios.AtividadesRepositorio;
import Repositorios.ObjetivosRepositorio;
import Repositorios.PesquisadoresRepositorio;
import Repositorios.PesquisasRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Busca;

public class Facade {

	private PesquisasRepositorio pesquisasRepositorio;
	private PesquisadoresRepositorio pesquisadoresRepositorio;
	private AtividadesRepositorio atividadeRepositorio;
	private ObjetivosRepositorio objetivosRepositorio;
	private ProblemasRepositorio problemasRepositorio;
	
	private Busca busca;
	private PesquisaController pesquisaController;
	private PesquisadorController pesquisadorController;
	private AtividadeController atividadeController;
	
	private ProblemaObjetivoController problemaObjetivoController;
	private PesquisaPesquisadorController pesquisaPesquisadorController;
	private PesquisaProblemaController pesquisaProblemaController;
	
	private PesquisaAtividadeController pesquisaAtividadeController ;
	
	public Facade() {
		this.pesquisasRepositorio = new PesquisasRepositorio();
		this.pesquisadoresRepositorio = new PesquisadoresRepositorio();
		this.atividadeRepositorio = new AtividadesRepositorio();
		this.objetivosRepositorio = new ObjetivosRepositorio();
		this.problemasRepositorio = new ProblemasRepositorio();
		
		this.pesquisaController = new PesquisaController(pesquisasRepositorio, objetivosRepositorio, busca);
		this.pesquisadorController = new PesquisadorController(pesquisadoresRepositorio, busca);
		this.atividadeController = new AtividadeController(atividadeRepositorio, busca);
		
		this.pesquisaPesquisadorController = new PesquisaPesquisadorController(pesquisadoresRepositorio, pesquisasRepositorio);
		this.problemaObjetivoController = new ProblemaObjetivoController(objetivosRepositorio, problemasRepositorio, busca);
		this.pesquisaProblemaController = new PesquisaProblemaController(pesquisasRepositorio, problemasRepositorio);
		this.pesquisaAtividadeController = new PesquisaAtividadeController(atividadeRepositorio, pesquisasRepositorio);
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
		return pesquisaProblemaController.associaProblema(idPesquisa, idProblema);
	}

	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		return pesquisaProblemaController.desassociaProblema(idPesquisa, idProblema);
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
	
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return pesquisaAtividadeController.associaAtividade(codigoPesquisa,codigoAtividade);
	}
	
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return pesquisaAtividadeController.desassociaAtividade(codigoPesquisa,codigoAtividade);
	}
	
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		pesquisaAtividadeController.executaAtividade(codigoAtividade,item,duracao);	
	}
	
	public int cadastraResultado(String codigoAtividade, String resultado) {
		return pesquisaAtividadeController.cadastraResultado(codigoAtividade,resultado);
	}
	
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return pesquisaAtividadeController.removeResultado(codigoAtividade,numeroResultado);
	}
	
	public String listaResultados(String codigoAtividade) {
		
		return pesquisaAtividadeController.listaResultados(codigoAtividade);
	}
	
	public int getDuracao(String codigoAtividade) {
		return pesquisaAtividadeController.getDuracao(codigoAtividade);
	}
	
	
	
	
	

	public String busca(String termo) {
		pesquisaController.buscaTermoPesquisa(termo);
		pesquisadorController.buscaTermoPesquisadores(termo);
		atividadeController.buscaTermoAtividades(termo);
		problemaObjetivoController.buscaTermoProblemas(termo);
		problemaObjetivoController.buscaTermoObjetivos(termo);
		return busca.resultadoDaBusca();
	}
	
	public String busca(String termo, int numeroDoResultado) {
		return busca.busca(termo, numeroDoResultado);
	}
	
	public int contaResultadosBusca(String termo) {
		return busca.contaResultadosBusca(termo);
	}
	
	
}
