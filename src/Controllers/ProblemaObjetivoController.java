package Controllers;

import Entidades.Objetivo;
import Entidades.Problema;
import Repositorios.ObjetivosRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Validador;


/**
 * @author Vincius Trindade
 */
public class ProblemaObjetivoController {
	/**
	 * Um Repositório de Objetivos
	 */
	private ObjetivosRepositorio objetivosRepositorio;
	/**
	 * Um Reposit´rio de Problemas
	 */
	private ProblemasRepositorio problemasRepositorio;
	
	/**
	 * Uma classe com as validacoes
	 */
	private Validador validador;

	/**
	 * Representacao de um controllador dos objetivos e problemas
	 * @param objetivosRepositorio O Repositorio de Objetivos
	 * @param problemasRepositorio O Repositorio de Problemas
	 */
	public ProblemaObjetivoController(ObjetivosRepositorio objetivosRepositorio,
			ProblemasRepositorio problemasRepositorio) {
		this.problemasRepositorio = problemasRepositorio;
		this.objetivosRepositorio = objetivosRepositorio;
		this.validador = new Validador();
	}

	/**
	 * Cadastra um Problema no Repositorio de Problemas
	 * @param descricao A descricao do Problema
	 * @param viabilidade A viabilidade do problema
	 * @return Retorna o codigo do problema
	 */
	public String cadastraProblema(String descricao, int viabilidade) {

		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.validarPontuacao(viabilidade, "Valor invalido de viabilidade.");

		String codigo = "P" + String.valueOf(problemasRepositorio.getContadorProblemas());
		Problema problema = new Problema(descricao, viabilidade, codigo);
		problemasRepositorio.put(codigo, problema);
		problemasRepositorio.incrementaContador();
		return codigo;
	}

	/**
	 * Cadastra um Objetivo no Reposotio de Objetivos
	 * @param tipo O Tipo do Objetivo (Geral ou Especifico)
	 * @param descricao A descricao do Objetivo
	 * @param aderencia A aderencia do Objetivo (inteiro de 1 a 5)
	 * @param viabilidade A viabilidade do Objetivo (inteiro de 1 a 5)
	 * @return Retorna o codigo do objetivo
	 */
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		validador.validar(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");

		if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}

		validador.validarPontuacao(aderencia, "Valor invalido de aderencia");

		validador.validarPontuacao(viabilidade, "Valor invalido de viabilidade.");

		String codigo = "O" + objetivosRepositorio.getContadorObjetivos();
		Objetivo objetivo = new Objetivo(tipo, descricao, aderencia, viabilidade, codigo);
		objetivosRepositorio.put("O" + objetivosRepositorio.getContadorObjetivos(), objetivo);
		objetivosRepositorio.incrementaContador();
		return codigo;
	}

	/**
	 * Exibe um Problema do Repositorio de Problemas
	 * @param codigo O Codigo do Problema (sua identificacao)
	 * @return Retorna uma representacao do problema
	 */
	public String exibeProblema(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		Problema problema = problemasRepositorio.getProblema(codigo);
		return problema.toString();

	}


	/**
	 * Exibe um Objetivo do Repositorio de Objetivos
	 * @param codigo O Codigo do Objetivo (sua identificacao)
	 * @return Retorna uma representacao do Objetivo
	 */
	public String exibeObjetivo(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		Objetivo objetivo = objetivosRepositorio.getObjetivo(codigo);
		return objetivo.toString();

	}

	/**
	 * Remove um problema do repositorio
	 * @param codigo O codigo do Problema
	 */
	public void apagarProblema(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		problemasRepositorio.remove(codigo);
	}

	/**
	 * Remove um Objetivo do Repositorio
	 * @param codigo O codigo do Objetivo
	 */
	public void apagarObjetivo(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		objetivosRepositorio.remove(codigo);

	}

}