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

public class Busca {

	private List<String> buscas;
	private Validador validador;
	private ObjetivosRepositorio objetivosRepositorio;
	private ProblemasRepositorio problemasRepositorio;
	private PesquisadoresRepositorio pesquisadoresRepositorio;
	private PesquisasRepositorio pesquisasRepositorio;
	private AtividadesRepositorio atividadeRepositorio;

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

	public void adicionaBusca(String msg) {
		buscas.add(msg);
	}

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
