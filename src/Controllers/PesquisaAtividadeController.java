package Controllers;

import Entidades.Atividade;
import Entidades.Pesquisa;
import Repositorios.AtividadesRepositorio;
import Repositorios.PesquisasRepositorio;
import utils.Validador;

public class PesquisaAtividadeController {
	private AtividadesRepositorio atividadesRepositorio; 
	private PesquisasRepositorio pesquisaRepositorio;
	private Validador validador;
	
	
	public PesquisaAtividadeController(AtividadesRepositorio atividadesRepositorio,PesquisasRepositorio pesquisaRepositorio) {
		this.atividadesRepositorio = atividadesRepositorio;
		this.pesquisaRepositorio = pesquisaRepositorio;
		this.validador = new Validador();
	}



	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.validar(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisaRepositorio.getPesquisa(codigoPesquisa);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		if(!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		atividade.associar();
		return pesquisa.adicionaAtividade(atividade);
	}
	
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.validar(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisaRepositorio.getPesquisa(codigoPesquisa);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		if(!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		atividade.desassociar();
		return pesquisa.tiraAtividade(atividade);

	}
	
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validarDuracao(duracao);
		validador.validarItem(item);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		atividade.temItem(item);
		atividade.jaExecutado(item);
		atividade.executarItem(item, duracao);  //executaAtividade codigoAtividade="A2" item=3 duracao=8
												//mesma atividade itens diferentes
		
		
	
	}
	
	public int cadastraResultado(String codigoAtividade, String resultado) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validar(resultado, "Resultado nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		atividade.adicionarResultado(resultado);
		return atividade.tamanhoDeResultados();
	}
	
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.validarNumeroResultado(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		return atividade.removerResultado(numeroResultado);
		
	}
	
	public String listaResultados(String codigoAtividade) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		return atividade.listarResultados();
		
	}
	
	public int getDuracao(String codigoAtividade) {
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		return atividade.getDuracao();
		
	}
	

}
