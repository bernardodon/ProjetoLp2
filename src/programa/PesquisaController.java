package programa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import utils.Validador;

/**
 * Representacao de um controllador da pesquisas
 * 
 * @author Hiarly Fernandes de Souto
 *
 */
public class PesquisaController {

	private ControllerGeral controllerGeral;
	/**
	 * Um mapa com todas as pesquisas cadastradas no sistema
	 */
	private Map<String, Pesquisa> pesquisas;

	/**
	 * Um validador que serve para verificar os parâmetros dos metodos
	 */
	private Validador validador;

	/**
	 * Constroi um Controlador dde Pesquisa
	 */
	public PesquisaController(ControllerGeral controller) {
		this.pesquisas = new HashMap<String, Pesquisa>();
		this.validador = new Validador();
		this.controllerGeral = controller;
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
		for (String chave : pesquisas.keySet()) {
			if (chave.contains(campoInteresse.toUpperCase().subSequence(0, 3))) {
				num += 1;
			}
		}

		String codigo = campoInteresse.substring(0, 3) + String.valueOf(num);
		codigo = codigo.toUpperCase();
		pesquisas.put(codigo, new Pesquisa(descricao, campoInteresse, codigo));
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
		if (pesquisas.containsKey(codigo)) {

			if (campo.equals("CAMPO")) {
				validarCampoInteresse(novoValor);
			}
			pesquisas.get(codigo).alteraPesquisa(campo, novoValor);

		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}

	}

	/**
	 * Ativa uma pesquisa que já esta cadastrada no sistema e esta desativada
	 * 
	 * @param codigo O codigo da pesquisa que se deseja ativar
	 */
	public void ativaPesquisa(String codigo) {
		if (pesquisas.containsKey(codigo)) {
			pesquisas.get(codigo).ativarPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Encerra uma pesquisa que está cadastrada no sistema e esta ativada
	 * 
	 * @param codigo O codigo da pesqusia
	 * @param motivo O motivo do encerramento
	 */
	public void enceraPesquisa(String codigo, String motivo) {
		if (pesquisas.containsKey(codigo)) {
			pesquisas.get(codigo).encerrarPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Encerra uma pesquisa que está cadastrada no sistema e esta ativada
	 * 
	 * @param codigo O codigo da pesqusia
	 */
	public void enceraPesquisa(String codigo) {
		if (pesquisas.containsKey(codigo)) {
			pesquisas.get(codigo).encerrarPesquisa();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Exibe uma pesquisa que está cadastrada no sistema
	 * 
	 * @param codigo O codigo da pesquisa
	 * @return Retorna uma representacao em String da pesqusa buscada
	 */
	public String exibePesquisa(String codigo) {
		if (pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).toString();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
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

		if (pesquisas.containsKey(codigo)) {
			return pesquisas.get(codigo).ehAtiva();
		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
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

	public void associaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		
		if (pesquisas.containsKey(idPesquisa)) {

			Pesquisador pesquisador = controllerGeral.getPesquisador(emailPesquisador);
			Pesquisa pesquisa = pesquisas.get(idPesquisa);

			if (pesquisa.ehAtiva()) {
				pesquisa.adicionarPesquisador(pesquisador);
			} else {
				throw new IllegalArgumentException("Pesquisa desativada.");
			}

		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	public void desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		
		if (pesquisas.containsKey(idPesquisa)) {

			Pesquisador pesquisador = controllerGeral.getPesquisador(emailPesquisador);
			Pesquisa pesquisa = pesquisas.get(idPesquisa);

			if (pesquisa.ehAtiva()) {
				pesquisa.removerPesquisador(pesquisador);
			} else {
				throw new IllegalArgumentException("Pesquisa desativada.");
			}

		} else {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
		
	}

	public Pesquisa getPesquisa(String idPesquisa) {
		checaInexistenciaPesquisa(idPesquisa);
		return pesquisas.get(idPesquisa);
	}

	/**
	 * Associa um Problema a uma Pesquisa a partir do id do problema e da pesquisa.
	 * @param idPesquisa Id da pesquisa.
	 * @param idProblema Id do problema.
	 * @return Retorna sucesso caso tenha associado com sucesso e false caso contrario.
	 */
	public boolean associaProblema(String idPesquisa, String idProblema) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		checaInexistenciaPesquisa(idPesquisa);
		checaDesativacao(idPesquisa);
		Pesquisa pesquisa = pesquisas.get(idPesquisa);
		Problema problema = controllerGeral.getProblema(idProblema);
		return pesquisa.associaProblema(problema);
	}
	
	/**
	 * Desassocia um Problema a uma Pesquisa a partir do id do problema e da pesquisa.
	 * @param idPesquisa Id da pesquisa.
	 * @param idProblema Id do problema.
	 * @return Retorna sucesso caso tenha associado com sucesso e false caso contrario.
	 */
	public boolean desassociaProblema(String idPesquisa, String idProblema) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		checaInexistenciaPesquisa(idPesquisa);
		checaDesativacao(idPesquisa);
		Pesquisa pesquisa = pesquisas.get(idPesquisa);
		Problema problema = controllerGeral.getProblema(idProblema);
		return pesquisa.desassociaProblema(problema);
	}	
	
	/**
	 * Associa um objetivo a uma pesquisa, a partir do id da pesquisa e do objetivo.
	 * @param idPesquisa Id da pesquisa.
	 * @param idObjetivo Id do objetivo.
	 * @return Retorna false caso a associacao seja mal sucedida e sucesso caso contrario.
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		checaInexistenciaPesquisa(idPesquisa);
		checaDesativacao(idPesquisa);
		Pesquisa pesquisa = pesquisas.get(idPesquisa);
		Objetivo objetivo = controllerGeral.getObjetivo(idObjetivo);
		
		boolean retorno = pesquisa.associaObjetivo(objetivo);
		
		if (retorno == false && objetivo.isAssociado()) {
			return retorno;
		} else if(objetivo.isAssociado()) {
			throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
		}
		objetivo.setAssociado(true);
		return retorno;
	}
	/**
	 * Dessocia um objetivo a uma pesquisa, a partir do id da pesquisa e do objetivo.
	 * @param idPesquisa Id da pesquisa.
	 * @param idObjetivo Id do objetivo.
	 * @return Retorna false caso a desassociacao seja mal sucedida e sucesso caso contrario.
	 */
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		checaInexistenciaPesquisa(idPesquisa);
		checaDesativacao(idPesquisa);
		Pesquisa pesquisa = pesquisas.get(idPesquisa);
		Objetivo objetivo = controllerGeral.getObjetivo(idObjetivo);
		
		
		if(!objetivo.isAssociado()) {
			return false;
		}
		
		pesquisa.desassociaObjetivo(objetivo);
		objetivo.setAssociado(false);
		return true;
	}
	
	/**
	 * Lista as pesquisas a partir de uma ordem definida no parametro, podendo ser por problema, objetivos ou pela propria pesquisa.
	 * @param ordem Ordem a ser utilizada.
	 * @return Retorna uma String com as pesquisas ordenadas da forma especificada.
	 */
	public String listaPesquisas(String ordem) {
		validador.validar(ordem, "Valor invalido da ordem");
		switch(ordem) {
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
	 * Coloca todas pesquisas em uma String ordenadas a partir dos codigos dos objetivos.
	 * @return Retorna a lista de pesquisas ordenadas pelos objetivos.
	 */
	private String listaPesquisasPorCodigoObjetivos() {
		String listaPesquisas = "";
		ArrayList<Pesquisa> listaOrdenada = new ArrayList<>();
		listaOrdenada.addAll(pesquisas.values());
		Collections.sort(listaOrdenada, new ObjetivosComparator());
		for(Pesquisa pesquisa:listaOrdenada) {
			if(pesquisa.getObjetivos().size() > 0) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		Collections.sort(listaOrdenada);
		for(Pesquisa pesquisa:listaOrdenada) {
			if(!listaPesquisas.contains(pesquisa.toString())) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		listaPesquisas = listaPesquisas.substring(0, listaPesquisas.length() - 3);
		return listaPesquisas;
	}
	/**
	 * Coloca todas pesquisas em uma String ordenadas a partir do codigo dos problemas.
	 * @return Retorna a lista de pesquisas ordenadas pelos problemas.
	 */
	private String listaPesquisasPorCodigoProblema() {
		String listaPesquisas = "";
		ArrayList<Pesquisa> listaOrdenada = new ArrayList<>();
		listaOrdenada.addAll(pesquisas.values());
		Collections.sort(listaOrdenada);
		for(Pesquisa pesquisa:listaOrdenada) {
			if(pesquisa.getProblema() != null) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		for(Pesquisa pesquisa:listaOrdenada) {
			if(pesquisa.getProblema() == null) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		listaPesquisas = listaPesquisas.substring(0, listaPesquisas.length() - 3);
		return listaPesquisas;
	}
	/**
	 * Coloca todas pesquisas em uma String ordenadas a partir do codigo das proprias pesquisas.
	 * @return Retorna a lista de pesquisas ordenadas pelo codigo das pesquisas.
	 */
	private String listaPesquisasPorCodigoPesquisa() {
		String listaPesquisas = "";
		ArrayList<Pesquisa> listaOrdenada = new ArrayList<>();
		listaOrdenada.addAll(pesquisas.values());
		Collections.sort(listaOrdenada);
		for (Pesquisa pesquisa:listaOrdenada) {
			listaPesquisas += pesquisa.toString() + " | ";
		}
		
		listaPesquisas = listaPesquisas.substring(0, listaPesquisas.length() - 3);
		return listaPesquisas;
	}

	/**
	 * Confere, a partir do id, se uma pesquisa existe ou nao, lancando uma excecao caso nao exista.
	 * @param idPesquisa Id da pesquisa.
	 */
	private void checaInexistenciaPesquisa(String idPesquisa) {
		if(!pesquisas.containsKey(idPesquisa)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}
	
	/**
	 * Confere, a partir do id, se uma pesquisa esta desativada ou nao, lancando uma excecao caso esteja.
	 * @param idPesquisa Id da pesquisa.
	 */
	private void checaDesativacao(String idPesquisa) {
		if(!pesquisas.get(idPesquisa).ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

}
