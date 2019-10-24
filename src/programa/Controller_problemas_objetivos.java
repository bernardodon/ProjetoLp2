package programa;

public class Controller_problemas_objetivos{
	private Map<Integer,Problema> mapa_de_problemas = new HashMap<Integer,Problema>();
	private Map<Integer,Objetivo> mapa_de_objetivos = new HashMap<Integer,Objetivo>();
	private int contador_problemas = 1;
	private int contador_objetivos = 1;

public String cadastraProblema(String descricao, int viabilidade){
	Problema problema = new Problema(descricao,viabilidade);
	mapa_de_problemas.put("P"+contador,problema);
	this.contador_problemas++;

	}

public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade){
	Objetivo objetivo = new Objetivo(tipo,descricao,aderencia,viabilidade);
	mapa_de_objetivos.put("O"+contador_objetivos,objetivo);
	this.contador_objetivos++;
	}

public String exibeProblema(String codigo){
	texto = "";
	Problema elemento = mapa_de_problemas.get(codigo);
	texto += elemento.toString();
	}


public String exibeObjetivo(String codigo){
	texto = "";
	Objetivo elemento = mapa_de_Objetivos.get(codigo);
	texto += elemento.toString();
	}
public void apagarProblema(String codigo){
	mapa_de_problemas.remove(codigo);
	}

public void apagarObjetivo(String codigo){
	mapa_de_objetivos.remove(codigo);
	}
/* a partir daqui valida*/

	public void validar_campo_vazio(String campo) {
		if (campo.trim().equals("")) {
			throw new IllegalArgumentException("Campo NOME_DO_CAMPO nao pode ser nulo ou vazio.");
		}
	}

	public void validar_nota(int nota){
		if(nota>5 || nota < 1){
			throw new IllegalArgumentException("Valor invalido de NOME_DO_CAMPO")
		}
	}

	public boolean keyProblemaAtiva(String codigo) {
		if (mapa_de_problemas.containsKey(codigo)) {
			return mapa_de_problemas.get(codigo).keyProblemaAtiva();
		} else {
			throw new IllegalArgumentException("Problema nao encontrado.");
		}
	}

	public boolean keyObjetivoAtiva(String codigo) {
		if (mapa_de_ojetivos.containsKey(codigo)) {
			return mapa_de_ojetivos.get(codigo).keyObjetivoAtiva();
		} else {
			throw new IllegalArgumentException("Objetivo nao encontrado.");
		}
	}


}