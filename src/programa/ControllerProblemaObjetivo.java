package programa;

import java.util.HashMap;
import java.util.Map;

import utils.Validador;

public class ControllerProblemaObjetivo {
	private Map<String, Problema> problemas;
	private Map<String, Objetivo> objetivos;
	private int contadorProblemas;
	private int contadorObjetivos;
	private Validador validador;

	public ControllerProblemaObjetivo() {
		this.problemas = new HashMap<String, Problema>();
		this.objetivos = new HashMap<String, Objetivo>();
		this.contadorProblemas = 1;
		this.contadorObjetivos = 1;
		this.validador = new Validador();
	}

	public String cadastraProblema(String descricao, Integer viabilidade) {
		if (viabilidade.equals(null)) {
			throw new IllegalArgumentException("Campo viabilidade nao pode ser nulo ou vazio.");
		}
		
		validador.validar(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.validarPontuacao(viabilidade, "Valor invalido de viabilidade.");

		String codigo = "P" + String.valueOf(contadorProblemas);
		Problema problema = new Problema(descricao, viabilidade, codigo);
		problemas.put(codigo, problema);
		this.contadorProblemas++;
		return codigo;
	}

	public String cadastraProblema(String descricao, String viabilidade) {
		throw new IllegalArgumentException("Campo viabilidade nao pode ser nulo ou vazio.");
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
		if (objetivos.containsKey(codigo)) {
			objetivos.remove(codigo);
		} else {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
	}
}