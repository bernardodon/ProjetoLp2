package programa;

import java.io.IOException;

import Controllers.AtividadeController;
import Controllers.PesquisaController;
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

	public Facade() {
		this.pesquisasRepositorio = new PesquisasRepositorio();
		this.pesquisadoresRepositorio = new PesquisadoresRepositorio();
		this.atividadeRepositorio = new AtividadesRepositorio();
		this.objetivosRepositorio = new ObjetivosRepositorio();
		this.problemasRepositorio = new ProblemasRepositorio();

		this.busca = new Busca(objetivosRepositorio, problemasRepositorio, pesquisadoresRepositorio,
				pesquisasRepositorio, atividadeRepositorio);
		this.pesquisaController = new PesquisaController(pesquisasRepositorio);
		this.pesquisadorController = new PesquisadorController(pesquisadoresRepositorio);
		this.atividadeController = new AtividadeController(atividadeRepositorio);

		this.problemaObjetivoController = new ProblemaObjetivoController(objetivosRepositorio, problemasRepositorio);
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
		return pesquisaController.associaPesquisador(idPesquisa, emailPesquisador, pesquisadoresRepositorio);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return pesquisaController.desassociaPesquisador(idPesquisa, emailPesquisador, pesquisadoresRepositorio);
	}

	public String listaPesquisadores(String tipo) {
		return pesquisadorController.listaPesquisadores(tipo);
	}

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return pesquisaController.associaProblema(idPesquisa, idProblema, problemasRepositorio);
	}

	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		return pesquisaController.desassociaProblema(idPesquisa, idProblema, problemasRepositorio);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return pesquisaController.associaObjetivo(idPesquisa, idObjetivo, objetivosRepositorio);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return pesquisaController.desassociaObjetivo(idPesquisa, idObjetivo, objetivosRepositorio);
	}

	public String listaPesquisas(String ordem) {
		return pesquisaController.listaPesquisas(ordem);
	}

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return pesquisaController.associaAtividade(codigoPesquisa, codigoAtividade, atividadeRepositorio);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return pesquisaController.desassociaAtividade(codigoPesquisa, codigoAtividade, atividadeRepositorio);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		atividadeController.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return atividadeController.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return atividadeController.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {

		return atividadeController.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return atividadeController.getDuracao(codigoAtividade);
	}

	public String busca(String termo) throws IOException {
		return busca.resultadoDaBusca(termo);
	}

	public String busca(String termo, int numeroDoResultado) {
		busca.resultadoDaBusca(termo);
		return busca.busca(termo, numeroDoResultado);
	}

	public int contaResultadosBusca(String termo) {
		busca.resultadoDaBusca(termo);
		return busca.contaResultadosBusca(termo);
	}

	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		atividadeController.defineProximaAtividade(idPrecedente, idSubsquente);
	}

	public void tiraProximaAtividade(String idPrecedente) {
		atividadeController.tiraProximaAtividade(idPrecedente);

	}

	public int contaProximos(String idPrecedente) {
		return atividadeController.contaProximos(idPrecedente);

	}

	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return atividadeController.pegaProximo(idAtividade, enesimaAtividade);
	}

	public String pegaMaiorRiscoAtividades(String idAtividade) {
		return atividadeController.pegaMaiorRiscoAtividades(idAtividade);
	}

	public void configuraEstrategia(String estrategia) {
		pesquisaController.configuraEstrategia(estrategia);
	}

	public String proximaAtividade(String codigoPesquisa) {
		return pesquisaController.proximaAtividade(codigoPesquisa);
	}
	
	public void gravarResumo(String codigoPesquisa) throws IOException {
		pesquisaController.gravarResumo(codigoPesquisa);
	}
	
	public void gravarResultados(String codigoPesquisa) throws IOException {
		pesquisaController.gravarResultado(codigoPesquisa);
	}

}
