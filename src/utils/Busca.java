package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

/**
 * Representação de uma entidade resposável por realizar a busca de um
 * determinado termo nos repositorios do sistema.
 * 
 * @author Ítalo Miguel Castor Diniz Pinheiro
 */
public class Busca {

	/**
	 * Um lista que contem as Strings das entidades do sistema em que foram
	 * encontrado um determinado termo.
	 */
	private List<String> buscas;

	/**
	 * Um validador.
	 */
	private Validador validador;

	/**
	 * O repositorio de objetivos.
	 */
	private ObjetivosRepositorio objetivosRepositorio;

	/**
	 * O repositorio de problemas.
	 */
	private ProblemasRepositorio problemasRepositorio;

	/**
	 * O repositorio de pesquisadores.
	 */
	private PesquisadoresRepositorio pesquisadoresRepositorio;

	/**
	 * O repositorio de pesquisas.
	 */
	private PesquisasRepositorio pesquisasRepositorio;

	/**
	 * O repositorio de atividades.
	 */
	private AtividadesRepositorio atividadeRepositorio;

	/**
	 * Constrói uma busca a partir dos repositorios do sistema.
	 * 
	 * @param objetivosRepositorio     o repositorio de objetivos.
	 * @param problemasRepositorio     o repositorio de problemas.
	 * @param pesquisadoresRepositorio o repositorio de pesquisadores.
	 * @param pesquisasRepositorio     o repositorio de pesquisas.
	 * @param atividadeRepositorio     o repositorio de atividades.
	 */
	public Busca(ObjetivosRepositorio objetivosRepositorio, ProblemasRepositorio problemasRepositorio,
			PesquisadoresRepositorio pesquisadoresRepositorio, PesquisasRepositorio pesquisasRepositorio,
			AtividadesRepositorio atividadeRepositorio) {
		this.buscas = new ArrayList<String>();
		this.validador = new Validador();
		this.problemasRepositorio = problemasRepositorio;
		this.objetivosRepositorio = objetivosRepositorio;
		this.pesquisadoresRepositorio = pesquisadoresRepositorio;
		this.pesquisasRepositorio = pesquisasRepositorio;
		this.atividadeRepositorio = atividadeRepositorio;
	}

	/**
	 * Adiciona a String da entidade a qual foi encontrado o termo da busca.
	 * 
	 * @param msg A string em que foi encontrado o termo da busca.
	 */
	public void adicionaBusca(String msg) {
		buscas.add(msg);
	}

	/**
	 * Busca um determinado termo no repositorio de problemas.
	 * 
	 * @param termo que será buscado nos problemas cadastrados no sistema.
	 */
	private void buscaTermoProblemas(String termo) {
		List<Problema> problemasValues = new ArrayList<Problema>();

		problemasValues.addAll(problemasRepositorio.getValues());

		Collections.sort(problemasValues);
		for (Problema p : problemasValues) {
			if (p.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				adicionaBusca(p.getCodigo() + ": " + p.getDescricao());
			}
		}

	}

	/**
	 * Busca um determinado termo no repositorio de objetivos.
	 * 
	 * @param termo que será buscado nos objetivos cadastrados no sistema.
	 */
	private void buscaTermoObjetivos(String termo) {
		List<Objetivo> objetivosValues = new ArrayList<Objetivo>();
		objetivosValues.addAll(objetivosRepositorio.getValues());

		Collections.sort(objetivosValues);

		for (Objetivo obj : objetivosValues) {
			if (obj.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				adicionaBusca(obj.getCodigo() + ": " + obj.getDescricao());
			}
		}
	}

	/**
	 * Busca um determinado termo no repositorio de pesquisadores.
	 * 
	 * @param termo que será buscado nos pesquisadores cadastrados no sistema.
	 */
	private void buscaTermoPesquisadores(String termo) {
		List<Pesquisador> pesquisadoresValues = new ArrayList<Pesquisador>();
		pesquisadoresValues.addAll(pesquisadoresRepositorio.getPesquisadoresValues());
		Collections.sort(pesquisadoresValues);

		for (Pesquisador p : pesquisadoresValues) {
			if (p.getBiografia().toLowerCase().contains(termo.toLowerCase())) {
				adicionaBusca(p.getEmail() + ": " + p.getBiografia());
			}
		}

	}

	/**
	 * Busca um determinado termo no repositorio de pesquisas.
	 * 
	 * @param termo que será buscado nas pesquisas cadastradas no sistema.
	 */
	private void buscaTermoPesquisa(String termo) {
		List<Pesquisa> pesquisasValues = new ArrayList<Pesquisa>();
		pesquisasValues.addAll(pesquisasRepositorio.getValues());
		Collections.sort(pesquisasValues);
		for (Pesquisa p : pesquisasValues) {
			if (p.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				adicionaBusca(p.getCodigo() + ": " + p.getDescricao());
			}

			if (p.getCampoInterese().toLowerCase().contains(termo.toLowerCase())) {
				adicionaBusca(p.getCodigo() + ": " + p.getCampoInterese());
			}
		}

	}

	/**
	 * Busca um determinado termo no repositorio de atividades.
	 * 
	 * @param termo que será buscado nas atividades cadastradas no sistema.
	 */
	private void buscaTermoAtividades(String termo) {
		List<Atividade> atividadesValues = new ArrayList<Atividade>();
		atividadesValues.addAll(atividadeRepositorio.getValues());
		Collections.sort(atividadesValues);

		for (Atividade atvd : atividadesValues) {
			if (atvd.getDescricaoAtvd().toLowerCase().contains(termo.toLowerCase())) {
				adicionaBusca(atvd.getCodigo() + ": " + atvd.getDescricaoAtvd());
			}

			if (atvd.getDescricaoRisco().toLowerCase().contains(termo.toLowerCase())) {
				adicionaBusca(atvd.getCodigo() + ": " + atvd.getDescricaoRisco());
			}
		}
	}

	/**
	 * Retorna a String que representa aonde foi encontrado nas entidades do sistema
	 * o termo de busca.
	 * 
	 * @param termo o termo que deseja-se buscas nas entidades do sistema.
	 * @return a representação em String da lista que contem as entidades as quais
	 *         foram encontrado o termo da busca
	 */
	public String resultadoDaBusca(String termo) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");
		buscas.clear();

		buscaTermoPesquisa(termo);
		buscaTermoPesquisadores(termo);
		buscaTermoProblemas(termo);
		buscaTermoObjetivos(termo);
		buscaTermoAtividades(termo);

		String str = "";
		if (buscas.size() == 0) {
			return "";
		}

		for (String s : buscas) {
			str += s + " | ";
		}
		return str.substring(0, str.length() - 3);
	}

	/**
	 * Retorna a String que representa um resultado específico.
	 * 
	 * @param termo o termo que deseja-se buscas nas entidades do sistema.
	 * @param numeroDoResultado o numero que especifica o resultado que deseja-se analisar.
	 * @return um resultado específico da busca feita no sistema.
	 */
	public String busca(String termo, int numeroDoResultado) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");

		if (numeroDoResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}

		if (numeroDoResultado > buscas.size()) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		} else {
			return buscas.get(numeroDoResultado - 1);
		}
	}

	/**
	 * Retorna quantas vezes o termo buscado foi encontrado nas entidades do sistema.
	 * 
	 * @param termo o termo buscado.
	 * @return o inteiro que representa o número de vezes em que o termo buscado foi encontrado no sistema.
	 */
	public int contaResultadosBusca(String termo) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");
		int cont = buscas.size();

		if (cont == 0) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		} else {
			return cont;
		}
	}
}
