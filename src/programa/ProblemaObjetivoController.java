package programa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Validador;

public class ProblemaObjetivoController {
	private Map<String, Problema> problemas;
	private Map<String, Objetivo> objetivos;
	private int contadorProblemas;
	private int contadorObjetivos;
	private Validador validador;
	private int numeroDoResultadoProblemaObjetivo;
	
	public ProblemaObjetivoController() {
		this.problemas = new HashMap<String, Problema>();
		this.objetivos = new HashMap<String, Objetivo>();
		this.contadorProblemas = 1;
		this.contadorObjetivos = 1;
		this.validador = new Validador();
		this.numeroDoResultadoProblemaObjetivo = 0;
	}

	public String cadastraProblema(String descricao, int viabilidade) {
		
		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.validarPontuacao(viabilidade, "Valor invalido de viabilidade.");

		String codigo = "P" + String.valueOf(contadorProblemas);
		Problema problema = new Problema(descricao, viabilidade, codigo);
		problemas.put(codigo, problema);
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
		objetivos.put("O" + contadorObjetivos, objetivo);
		this.contadorObjetivos++;
		return codigo;
	}

	public String exibeProblema(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (problemas.containsKey(codigo)) {
			return problemas.get(codigo).toString();
		} else {
			throw new IllegalArgumentException("Problema nao encontrado");
		}

	}

	public String exibeObjetivo(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (objetivos.containsKey(codigo)) {
			return objetivos.get(codigo).toString();
		} else {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
	}

	public void apagarProblema(String codigo) {
		validador.validar(codigo, "Campo codigo nao pode ser nulo ou vazio.");

		if (problemas.containsKey(codigo)) {
			problemas.remove(codigo);
		} else {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
	}

	public void apagarObjetivo(String codigo) {
		validador.validar(codigo,"Campo codigo nao pode ser nulo ou vazio.");
		if (objetivos.containsKey(codigo)) {
			objetivos.remove(codigo);
		} else {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
	}

	public Problema getProblema(String codigo) {
		return problemas.get(codigo);
	}
	
	public Objetivo getObjetivo(String codigo) {
		return objetivos.get(codigo);
	}
	
	public String buscaTermoProblemas(String termo) {
		String msg = "";
		List<Problema> problemasValues = new ArrayList<Problema>();
		problemasValues.addAll(problemas.values());
		Collections.sort(problemasValues);
		
		for(Problema p: problemasValues) {
			if (p.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				msg += p.getCodigo()+ ": " +  p.getDescricao() + " | ";
				numeroDoResultadoProblemaObjetivo++;
			}
		}
		return msg;
	}
	
	public String buscaTermoObjetivos(String termo) {
		String msg = "";
		List<Objetivo> objetivosValues = new ArrayList<Objetivo>();
		objetivosValues.addAll(objetivos.values());
		Collections.sort(objetivosValues);
		
		for(Objetivo obj: objetivosValues) {
			if (obj.getDescricao().toLowerCase().contains(termo.toLowerCase())) {
				msg += obj.getCodigo()+ ": " +  obj.getDescricao() + " | ";
				numeroDoResultadoProblemaObjetivo++;
			}
		}
		return msg;
	}
}