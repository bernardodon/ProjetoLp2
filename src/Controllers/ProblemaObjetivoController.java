package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Entidades.Objetivo;
import Entidades.Problema;
import Repositorios.ObjetivosRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Busca;
import utils.Validador;

public class ProblemaObjetivoController {
	private ObjetivosRepositorio objetivosRepositorio;
	private ProblemasRepositorio problemasRepositorio;
	private int contadorProblemas;
	private int contadorObjetivos;
	private Validador validador;
	private Busca busca;

	public ProblemaObjetivoController(ObjetivosRepositorio objetivosRepositorio,
			ProblemasRepositorio problemasRepositorio, Busca busca) {
		this.problemasRepositorio = problemasRepositorio;
		this.objetivosRepositorio = objetivosRepositorio;
		this.contadorProblemas = 1;
		this.contadorObjetivos = 1;
		this.validador = new Validador();
		this.busca = new Busca();
	}

	public String cadastraProblema(String descricao, int viabilidade) {

		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.validarPontuacao(viabilidade, "Valor invalido de viabilidade.");

		String codigo = "P" + String.valueOf(contadorProblemas);
		Problema problema = new Problema(descricao, viabilidade, codigo);
		problemasRepositorio.put(codigo, problema);
		this.contadorProblemas++;
		return codigo;
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		validador.validar(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");

		if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}

		validador.validarPontuacao(aderencia, "Valor invalido de aderencia");

		validador.validarPontuacao(viabilidade, "Valor invalido de viabilidade.");

		String codigo = "O" + contadorObjetivos;
		Objetivo objetivo = new Objetivo(tipo, descricao, aderencia, viabilidade, codigo);
		objetivosRepositorio.put("O" + contadorObjetivos, objetivo);
		this.contadorObjetivos++;
		return codigo;
	}

	public String exibeProblema(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		Problema problema = problemasRepositorio.getProblema(codigo);
		return problema.toString();

	}

	public String exibeObjetivo(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		Objetivo objetivo = objetivosRepositorio.getObjetivo(codigo);
		return objetivo.toString();

	}

	public void apagarProblema(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		problemasRepositorio.remove(codigo);
	}

	public void apagarObjetivo(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		objetivosRepositorio.remove(codigo);

	}

	public void buscaTermoProblemas(String termo) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");
		List<Problema> problemasValues = new ArrayList<Problema>();
		problemasValues.addAll(problemasRepositorio.getValues());
		Collections.sort(problemasValues);

		for (Problema p : problemasValues) {
			if (p.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				busca.adicionaBusca(p.getCodigo() + ": " + p.getDescricao() + " | ");
				busca.setNumeroDoResultado(busca.getNumeroDoResultado() + 1);
			}
		}

	}

	public void buscaTermoObjetivos(String termo) {
		validador.validar(termo, "Campo termo nao pode ser nulo ou vazio.");
		List<Objetivo> objetivosValues = new ArrayList<Objetivo>();
		objetivosValues.addAll(objetivosRepositorio.getValues());
		Collections.sort(objetivosValues);

		for (Objetivo obj : objetivosValues) {
			if (obj.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				busca.adicionaBusca(obj.getCodigo() + ": " + obj.getDescricao() + " | ");
				busca.setNumeroDoResultado(busca.getNumeroDoResultado() + 1);
			}
		}

	}
}