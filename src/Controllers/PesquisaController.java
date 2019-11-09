package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Entidades.Objetivo;
import Entidades.Pesquisa;
import Repositorios.ObjetivosRepositorio;
import Repositorios.PesquisasRepositorio;
import utils.Busca;
import utils.ObjetivosComparator;
import utils.Validador;

/**
 * Representacao de um controllador da pesquisas
 * 
 * @author Hiarly Fernandes de Souto
 *
 */
public class PesquisaController {

	private PesquisasRepositorio pesquisasRepositorio;

	/**
	 * Um validador que serve para verificar os parâmetros dos metodos
	 */
	private Validador validador;

	private ObjetivosRepositorio objetivosRepositorio;
	private Busca busca;

	/**
	 * Constroi um Controlador dde Pesquisa
	 */
	public PesquisaController(PesquisasRepositorio pesquisasRepositorio, ObjetivosRepositorio objetivosRepositorio,
			Busca busca) {
		this.pesquisasRepositorio = pesquisasRepositorio;
		this.objetivosRepositorio = objetivosRepositorio;

		this.validador = new Validador();
		this.busca = busca;
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
	public void enceraPesquisa(String codigo, String motivo) {
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(codigo);

		pesquisa.encerrarPesquisa();

	}

	/**
	 * Encerra uma pesquisa que está cadastrada no sistema e esta ativada
	 * 
	 * @param codigo O codigo da pesqusia
	 */
	public void enceraPesquisa(String codigo) {
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
	 * Associa um objetivo a uma pesquisa, a partir do id da pesquisa e do objetivo.
	 * 
	 * @param idPesquisa Id da pesquisa.
	 * @param idObjetivo Id do objetivo.
	 * @return Retorna false caso a associacao seja mal sucedida e sucesso caso
	 *         contrario.
	 */
	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);
		checaDesativacao(pesquisa);
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
	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		validador.validar(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.validar(idObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Pesquisa pesquisa = pesquisasRepositorio.getPesquisa(idPesquisa);

		checaDesativacao(pesquisa);
		Objetivo objetivo = objetivosRepositorio.getObjetivo(idObjetivo);

		if (!objetivo.isAssociado()) {
			return false;
		}

		pesquisa.desassociaObjetivo(objetivo);
		objetivo.setAssociado(false);
		return true;
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
	 * Coloca todas pesquisas em uma String ordenadas a partir dos codigos dos
	 * objetivos.
	 * 
	 * @return Retorna a lista de pesquisas ordenadas pelos objetivos.
	 */
	private String listaPesquisasPorCodigoObjetivos() {
		String listaPesquisas = "";
		ArrayList<Pesquisa> listaOrdenada = new ArrayList<>();
		listaOrdenada.addAll(pesquisasRepositorio.getValues());
		Collections.sort(listaOrdenada, new ObjetivosComparator());
		for (Pesquisa pesquisa : listaOrdenada) {
			if (pesquisa.getObjetivos().size() > 0) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		Collections.sort(listaOrdenada);
		for (Pesquisa pesquisa : listaOrdenada) {
			if (!listaPesquisas.contains(pesquisa.toString())) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		listaPesquisas = listaPesquisas.substring(0, listaPesquisas.length() - 3);
		return listaPesquisas;
	}

	/**
	 * Coloca todas pesquisas em uma String ordenadas a partir do codigo dos
	 * problemas.
	 * 
	 * @return Retorna a lista de pesquisas ordenadas pelos problemas.
	 */
	private String listaPesquisasPorCodigoProblema() {
		String listaPesquisas = "";
		ArrayList<Pesquisa> listaOrdenada = new ArrayList<>();
		listaOrdenada.addAll(pesquisasRepositorio.getValues());
		Collections.sort(listaOrdenada);
		for (Pesquisa pesquisa : listaOrdenada) {
			if (pesquisa.getProblema() != null) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		for (Pesquisa pesquisa : listaOrdenada) {
			if (pesquisa.getProblema() == null) {
				listaPesquisas += pesquisa.toString() + " | ";
			}
		}
		listaPesquisas = listaPesquisas.substring(0, listaPesquisas.length() - 3);
		return listaPesquisas;
	}

	/**
	 * Coloca todas pesquisas em uma String ordenadas a partir do codigo das
	 * proprias pesquisas.
	 * 
	 * @return Retorna a lista de pesquisas ordenadas pelo codigo das pesquisas.
	 */
	private String listaPesquisasPorCodigoPesquisa() {
		String listaPesquisas = "";
		ArrayList<Pesquisa> listaOrdenada = new ArrayList<>();
		listaOrdenada.addAll(pesquisasRepositorio.getValues());
		Collections.sort(listaOrdenada);
		for (Pesquisa pesquisa : listaOrdenada) {
			listaPesquisas += pesquisa.toString() + " | ";
		}

		listaPesquisas = listaPesquisas.substring(0, listaPesquisas.length() - 3);
		return listaPesquisas;
	}

	/**
	 * Confere, a partir do id, se uma pesquisa esta desativada ou nao, lancando uma
	 * excecao caso esteja.
	 * 
	 * @param idPesquisa Id da pesquisa.
	 */
	private void checaDesativacao(Pesquisa pesquisa) {
		if (!pesquisa.ehAtiva()) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

	public void buscaTermoPesquisa(String termo) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");

		List<Pesquisa> pesquisasValues = new ArrayList<Pesquisa>();
		pesquisasValues.addAll(pesquisasRepositorio.getValues());
		Collections.sort(pesquisasValues);
		busca.clearBuscas();
		for (Pesquisa p : pesquisasValues) {
			if (p.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				busca.adicionaBusca(p.getCodigo() + ": " + p.getDescricao());
			}
			
			if (p.getCampoInterese().toLowerCase().contains(termo.toLowerCase())) {
				busca.adicionaBusca(p.getCodigo() + ": " + p.getCampoInterese());
			}
		}

	}

}
