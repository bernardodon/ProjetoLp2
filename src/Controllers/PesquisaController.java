package Controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Entidades.Atividade;
import Entidades.Objetivo;
import Entidades.Pesquisa;
import Entidades.Pesquisador;
import Entidades.Problema;
import Repositorios.AtividadesRepositorio;
import Repositorios.ObjetivosRepositorio;
import Repositorios.PesquisadoresRepositorio;
import Repositorios.PesquisasRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Validador;

/**
 * Representacao de um controllador da pesquisas
 * 
 * @author Hiarly Fernandes de Souto
 *
 */
public class PesquisaController{

	private PesquisasRepositorio pesquisasRepositorio;

	/**
	 * Um validador que serve para verificar os parâmetros dos metodos
	 */
	private Validador validador;


	/**
	 * Constroi um Controlador dde Pesquisa
	 */
	public PesquisaController(PesquisasRepositorio pesquisasRepositorio) {
		this.pesquisasRepositorio = pesquisasRepositorio;
		this.validador = new Validador();
	}
	public void configuraEstrategia(String estrategia) {
		validador.validar(estrategia, "Estrategia nao pode ser nula ou vazia.");
		if(!estrategia.equals("MAIS_ANTIGA") && !estrategia.equals("MENOS_PENDENCIAS") && !estrategia.equals("MAIOR_RISCO") && !estrategia.equals("MAIOR_DURACAO")) {
			throw new IllegalArgumentException("Valor invalido da estrategia");
		}
		pesquisasRepositorio.setAlgoritmo(estrategia);
	}
	
	public String proximaAtividade(String codigoPesquisa) {
		String texto = "";
		validador.validar(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigoPesquisa);
		if(!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		pesquisa.validadorDePendencia();
		
		
		
		if(pesquisasRepositorio.getAlgoritmo().equals("MAIS_ANTIGA")) {
			texto = pesquisa.getMaisAntigo();
		}else {
			if(pesquisasRepositorio.getAlgoritmo().equals("MENOS_PENDENCIAS")) {
				
				texto = pesquisa.getMenosPendentes();
			}else {
				if(pesquisasRepositorio.getAlgoritmo().equals("MAIOR_RISCO")){
					texto = pesquisa.getMaiorRisco();
				}else {
					if(pesquisasRepositorio.getAlgoritmo().equals("MAIOR_DURACAO")) {
						texto = pesquisa.getAtividadeMaiorDuracao();
					}
					
				}
				
			}
			
		}
		return texto;
		
	}
	

	/**
	 * Cadastra uma pesquisa no HashMap de pesquisas.
	 * 
	 * @param descricao      Um texto livre com um resumo da pesquisa a ser
	 *                       realizada.
	 * @param campoInteresse Um marcador da área ou tema a ser colocado. Pode ter
	 *                       ate 4 topicos, separados por virgula e ter ate 255
	 *                       caracteres.
	 * @return Retorna o codigo de identificacao da pesquisa no HashMap
	 */
	public String cadastraPesquisa(String descricao, String campoInteresse) {
		validador.validar(descricao, "Descricao nao pode ser nula ou vazia.");

		validarCampoInteresse(campoInteresse);

		int num = 1;
		for (String chave : pesquisasRepositorio.getKeys()) {
			if (chave.contains(campoInteresse.toUpperCase().subSequence(0, 3))) {
				num += 1;
			}
		}

		String codigo = campoInteresse.substring(0, 3) + String.valueOf(num);
		codigo = codigo.toUpperCase();
		pesquisasRepositorio.put(codigo, new Pesquisa(descricao, campoInteresse, codigo));
		return codigo;
	}

	/**
	 * Alera uma pesquisa que ja foi cadastrada
	 * 
	 * @param codigo    O codigo da pesquisa que se deseja alterar
	 * @param campo     O campo a ser alterado na pesquisa, campo de Interesse ou
	 *                  descricao
	 * @param novoValor O novo valor do campo selecinado
	 */
	public void alteraPesquisa(String codigo, String campo, String novoValor) {
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigo);
		if (campo.equals("CAMPO")) {
			validarCampoInteresse(novoValor);
		}
		pesquisa.alteraPesquisa(campo, novoValor);

	}

	/**
	 * Ativa uma pesquisa que já esta cadastrada no sistema e esta desativada
	 * 
	 * @param codigo O codigo da pesquisa que se deseja ativar
	 */
	public void ativaPesquisa(String codigo) {
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigo);

		pesquisa.ativarPesquisa();
	}

	/**
	 * Encerra uma pesquisa que está cadastrada no sistema e esta ativada
	 * 
	 * @param codigo O codigo da pesqusia
	 * @param motivo O motivo do encerramento
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		validador.validar(motivo, "Motivo nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigo);

		pesquisa.encerrarPesquisa();

	}

	/**
	 * Encerra uma pesquisa que está cadastrada no sistema e esta ativada
	 * 
	 * @param codigo O codigo da pesqusia
	 */
	public void encerraPesquisa(String codigo) {
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigo);

		pesquisa.encerrarPesquisa();

	}

	/**
	 * Exibe uma pesquisa que está cadastrada no sistema
	 * 
	 * @param codigo O codigo da pesquisa
	 * @return Retorna uma representacao em String da pesqusa buscada
	 */
	public String exibePesquisa(String codigo) {
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigo);

		return pesquisa.toString();
	}

	/**
	 * Verifica se uma pesquisa cadastrada no sistema esta ativada
	 * 
	 * @param codigo O codigo da pesquisa
	 * @return Retorna true, cado a pesquisa esteja ativa. Caso esteja desativada
	 *         retorna false
	 */
	public boolean ehAtiva(String codigo) {
		validador.validar(codigo, "Codigo nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigo);

		return pesquisa.ehAtiva();

	}

	/**
	 * Valida o Campo de interesse de acordo com os criterios definidos
	 * 
	 * @param campoInteresse O valor, em String, do campo de interesse que se deseja
	 *                       validar
	 */
	private void validarCampoInteresse(String campoInteresse) {
		validador.validar(campoInteresse, "Formato do campo de interesse invalido.");

		if (campoInteresse.length() >= 255) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}

		String[] campos = campoInteresse.split(",");
		if (campos.length > 4) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}
		for (String campo : campos) {

			if (campo.length() < 3) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}

			if (campo.equals("")) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}

		}
	}

	/**
	 * Lista as pesquisas a partir de uma ordem definida no parametro, podendo ser
	 * por problema, objetivos ou pela propria pesquisa.
	 * 
	 * @param ordem Ordem a ser utilizada.
	 * @return Retorna uma String com as pesquisas ordenadas da forma especificada.
	 */
	public String listaPesquisas(String ordem) {
		validador.validar(ordem, "Valor invalido da ordem");
		switch (ordem) {
		case "PROBLEMA":
			return listaPesquisasPorCodigoProblema();
		case "OBJETIVOS":
			return listaPesquisasPorCodigoObjetivos();
		case "PESQUISA":
			return listaPesquisasPorCodigoPesquisa();
		default:
			throw new IllegalArgumentException("Valor invalido da ordem");
		}
	}

	/**
	 * Retorna uma String com as pesquisas ordenadas a partir objetivos.
	 * 
	 * @return Retorna a lista de pesquisas ordenadas pelos objetivos.
	 */
	private String listaPesquisasPorCodigoObjetivos() {
		return pesquisasRepositorio.listaPesquisasPorCodigoObjetivos();
	}

	/**
	 * Retorna uma String com as pesquisas ordenadas a partir problemas.
	 * 
	 * @return Retorna a lista de pesquisas ordenadas pelos problemas.
	 */
	private String listaPesquisasPorCodigoProblema() {
		return pesquisasRepositorio.listaPesquisasPorCodigoProblema();
	}

	/**
	 * Retorna uma String com as pesquisas ordenadas a partir objetivos.
	 * 
	 * @return Retorna a lista de pesquisas ordenadas pelo codigo das pesquisas.
	 */
	private String listaPesquisasPorCodigoPesquisa() {
		return pesquisasRepositorio.listaPesquisasPorCodigoPesquisa();
	}

	/**
	 * Grava um Resumo de uma Pesquisa - Contem informacoes sobre a Pesquisa, 
	 * os Pesquisadores da Pesquisa, o Problema da Pesquisa, Os Objetivos da Pesquisa
	 * A atividade da Pesquisa
	 * @param codigoPesquisa O codigo da Pesquisa que sera feito o resumo
	 * @throws IOException
	 */
	public void gravarResumo(String codigoPesquisa) throws IOException {
		validador.validar(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigoPesquisa);
		String str = "\"- Pesquisa: " + pesquisa.toString() + System.lineSeparator();
		str += "	- Pesquisadores:" + System.lineSeparator();
		str += pesquisa.gravarPesquisadores();
		str += "	- Problema:" + System.lineSeparator();
		str += pesquisa.gravarProblema() + System.lineSeparator();
		str += "	- Objetivos:" + System.lineSeparator();
		str += pesquisa.gravarObjetivos();
		str += "	- Atividades:" + System.lineSeparator();
		str += pesquisa.gravarAtividades();
		salvarEmArquivo(codigoPesquisa+".txt", str);
	}
	
	/**
	 * Grava O resultado de uma pesquisa - grava informacoes sobre os resultados da pesquisa,
	 * as atividades
	 * @param codigoPesquisa O codigo da pesquisa que sera feita o resultado
	 * @throws IOException
	 */
	public void gravarResultado(String codigoPesquisa) throws IOException {
		validador.validar(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigoPesquisa);
		String str = "\"- Pesquisa: " + pesquisa.toString() + System.lineSeparator();
		str += pesquisa.gravarResultado();
		salvarEmArquivo(codigoPesquisa+"-Resultados.txt", str);
	}
	
	/**
	 * Salva um texto em um arquivo
	 * @param path O caminho do arquivo, caso nao exitsa sera criado, caso exista, sera sobrescrito
	 * @param texto O texto que sera colocado no arquivo
	 * @throws IOException
	 */
	private void salvarEmArquivo(String path, String texto) throws IOException {
		File file = new File(path);
		BufferedWriter bf = new BufferedWriter(new FileWriter(path));
		bf.append(texto);
		bf.flush();
		bf.close();
	}
	
	/**
	 * Associa um Pesquisador a um Pesquisa, ou seja, adiciona uma Pesquisador dentro de uma Pesquisa
	 * @param idPesquisa O Identificador da pesquisa que será carregada com um pesquisador
	 * @param emailPesquisador O email do pesquisador que será colocado dentro da Pesquisa
	 * @return Retorna um boolean sobre a operacao. True caso a operação tenha sido bem sucessida, 
	 * ou false caso a operacao nao tenha sido realizada com sucesso.
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador, PesquisadoresRepositorio 
			pesquisadoresRepositorio) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		Pesquisador pesquisador = pesquisadoresRepositorio.getPesquisador(emailPesquisador);
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaDesativacaoPesquisa(pesquisa);
		return pesquisa.adicionarPesquisador(pesquisador);

	}


	/**
	 * Dessaassocia um Pesquisador a um Pesquisa, ou seja, remove uma Pesquisador dentro de uma Pesquisa
	 * @param idPesquisa O Identificador da pesquisa que tera um Pesquisador removido
	 * @param emailPesquisador O email do pesquisador que sra removido de dentro da Pesquisa
	 * @return Retorna um boolean sobre a operacao. True caso a operação tenha sido bem sucessida, 
	 * ou false caso a operacao nao tenha sido realizada com sucesso.
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador, PesquisadoresRepositorio
			pesquisadoresRepositorio) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");

		Pesquisador pesquisador = pesquisadoresRepositorio.getPesquisador(emailPesquisador);
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaDesativacaoPesquisa(pesquisa);
		return pesquisa.removerPesquisador(pesquisador);

	}
	

	/**
	 * Associa um Problema a uma Pesquisa a partir do id do problema e da pesquisa.
	 * @param idPesquisa Id da pesquisa.
	 * @param idProblema Id do problema.
	 * @return Retorna sucesso caso tenha associado com sucesso e false caso contrario.
	 */
	public boolean associaProblema(String idPesquisa, String idProblema, ProblemasRepositorio
			problemasRepositorio) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaDesativacaoPesquisa(pesquisa);
		Problema problema = problemasRepositorio.getProblema(idProblema);
		return pesquisa.associaProblema(problema);
	}
	
	/**
	 * Desassocia um Problema a uma Pesquisa a partir do id do problema e da pesquisa.
	 * @param idPesquisa Id da pesquisa.
	 * @param idProblema Id do problema.
	 * @return Retorna sucesso caso tenha associado com sucesso e false caso contrario.
	 */
	public boolean desassociaProblema(String idPesquisa, ProblemasRepositorio
			problemasRepositorio) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaDesativacaoPesquisa(pesquisa);
		return pesquisa.desassociaProblema();
	}	

	
	/**
	 * Associa um objetivo a uma pesquisa, a partir do id da pesquisa e do objetivo.
	 * 
	 * @param idPesquisa Id da pesquisa.
	 * @param idObjetivo Id do objetivo.
	 * @return Retorna false caso a associacao seja mal sucedida e sucesso caso
	 *         contrario.
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo, ObjetivosRepositorio
			objetivosRepositorio) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaDesativacaoPesquisa(pesquisa);
		Objetivo objetivo = objetivosRepositorio.getObjetivo(idObjetivo);

		boolean retorno = pesquisa.associaObjetivo(objetivo);

		if (retorno == false && objetivo.isAssociado()) {
			return retorno;
		} else if (objetivo.isAssociado()) {
			throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
		}
		objetivo.setAssociado(true);
		return retorno;
	}

	/**
	 * Dessocia um objetivo a uma pesquisa, a partir do id da pesquisa e do
	 * objetivo.
	 * 
	 * @param idPesquisa Id da pesquisa.
	 * @param idObjetivo Id do objetivo.
	 * @return Retorna false caso a desassociacao seja mal sucedida e sucesso caso
	 *         contrario.
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo, ObjetivosRepositorio
			objetivosRepositorio) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);

		checaDesativacaoPesquisa(pesquisa);
		Objetivo objetivo = objetivosRepositorio.getObjetivo(idObjetivo);

		if (!objetivo.isAssociado()) {
			return false;
		}

		pesquisa.desassociaObjetivo(objetivo);
		objetivo.setAssociado(false);
		return true;
	}

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade, AtividadesRepositorio
			atividadesRepositorio) {
		validador.validar(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigoPesquisa);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		atividade.associar();
		return pesquisa.adicionaAtividade(atividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade, AtividadesRepositorio
			atividadesRepositorio) {
		validador.validar(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.validar(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigoPesquisa);
		Atividade atividade = atividadesRepositorio.getAtividade(codigoAtividade);
		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		atividade.desassociar();
		return pesquisa.tiraAtividade(atividade);

	}

	/**
	 * Confere, a partir de uma pesquisa, se uma pesquisa esta desativada ou nao, lancando uma
	 * excecao caso esteja.
	 * 
	 * @param pesquisa A pesquisa que sera feita a validadcao.
	 */

	private void checaDesativacaoPesquisa(Pesquisa pesquisa) {
		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}
	
	public void salvar() {
		
	}
	
	public void carregar() {
		
	}
}
