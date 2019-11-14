package Entidades;

import java.util.ArrayList;
import java.util.List;

import utils.Validador;

/**
 * Representação de uma atividade referentes a uma pesquisa científica. Toda
 * atividade deve ter uma descricao, uma duração, um nível de risco e uma
 * descrição do risco.
 * 
 * @author Ítalo Miguel Castor Diniz Pinheiro.
 */
public class Atividade implements Comparable<Atividade> {

	/**
	 * Um validador.
	 */
	private Validador validador;
	/**
	 * O codigo da atividade.
	 */
	private String codigo;

	/*
	 * Descrição da atividade.
	 */
	private String descricaoAtvd;

	/**
	 * O nível de risco de uma atividade.
	 */
	private String risco;

	/**
	 * A descrição do risco.
	 */
	private String descricaoRisco;

	/**
	 * Uma lista de itens de uma atividade.
	 */
	private List<Item> itens;

	private boolean isAssociado;

	private int duracao;

	private Atividade proxAtividade;

	private List<String> resultados;

	/**
	 * Constrói uma atividade a partir de sua descrição, do nível do risco dela e da
	 * descrição do risco.
	 * 
	 * @param descricaoAtvd  a descrição da atividade.
	 * @param risco          o nível de risco da atividade.cc
	 * @param descricaoRisco a descrição do risco da atividade.
	 * @param codigo         o codigo da atividade.
	 */
	public Atividade(String descricaoAtvd, String risco, String descricaoRisco, String codigo) {
		this.validador = new Validador();
		this.codigo = codigo;
		this.descricaoAtvd = descricaoAtvd;
		this.descricaoRisco = descricaoRisco;
		this.risco = risco;
		this.itens = new ArrayList<Item>();
		this.isAssociado = false;
		this.duracao = 0;
		this.resultados = new ArrayList<String>();

		validador.validar(descricaoAtvd, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.validar(risco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.validar(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		validador.validar(codigo, "Valor invalido do nivel do risco.");
	}

	public int getDuracao() {
		return this.duracao;
	}

	public String listarResultados() {
		String texto = "";
		for (int i = 0; i <= resultados.size() - 1; i++) {
			texto += resultados.get(i) + " | ";

		}
		texto = texto.substring(0, texto.length() - 3);

		return texto;
	}

	public void temItem(int numero) {
		if (itens.size() < numero) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}
	}

	public void jaExecutado(int numero) {
		if (itens.get(numero - 1).getStatus().equals("REALIZADO")) {
			throw new IllegalArgumentException("Item ja executado.");
		}

	}

	public int tamanhoDeResultados() {
		return resultados.size();
	}

	public void executarItem(int numero, int duracao) {
		itens.get(numero - 1).executarItem();
		this.duracao += duracao;

	}

	public void adicionarResultado(String mensagem) {
		resultados.add(mensagem);
	}

	public boolean removerResultado(int numeroResultado) {
		if (resultados.size() >= numeroResultado) {
			resultados.remove(numeroResultado - 1);
			return true;
		} else {
			throw new IllegalArgumentException("Resultado nao encontrado.");
		}
	}

	public void associar() {
		this.isAssociado = true;
	}

	public void desassociar() {
		this.isAssociado = false;
	}

	/**
	 * Retorna a lista de itens que uma atividade possui.
	 * 
	 * @return a lista de itens de uma atividade.
	 */
	public List<Item> getItens() {
		return itens;
	}

	public String getDescricaoAtvd() {
		return descricaoAtvd;
	}

	public String getDescricaoRisco() {
		return descricaoRisco;
	}

	public String getCodigo() {
		return codigo;
	}

	/**
	 * Adiciona um item na lista de itens.
	 * 
	 * @param i um Item.
	 */
	public void adicionaItem(Item i) {
		itens.add(i);
	}

	/**
	 * Retorna a quantidade de itens pendentes de uma determinada atividade.
	 * 
	 * @return o inteiro que representa a quantidade de itens pendentes.
	 */
	public int quantPendentes() {
		int cont = 0;
		for (Item i : itens) {
			if (i.getStatus() == "PENDENTE") {
				cont++;
			}
		}
		return cont;
	}

	/**
	 * Retorna a quantidade de itens realizados. de uma determinada atividade.
	 * 
	 * @return o inteiro que representa a quantidade de itens realizados.
	 */
	public int quantRealizados() {
		int cont = 0;
		for (Item i : itens) {
			if (i.getStatus() == "REALIZADO") {
				cont++;
			}
		}
		return cont;
	}

	/**
	 * Gera o hashCode de uma atividade.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Compara duas atividades a partir do codigo de cada uma.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atividade other = (Atividade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Retorna a String que representa os itens de uma atividade.
	 * 
	 * @return a representação em String que representa todos os itens de uma
	 *         atividade.
	 */
	public String exibeItens() {
		String msg = "";

		for (Item i : itens) {
			msg += " | " + i.toString();
		}
		return msg;
	}

	@Override
	public int compareTo(Atividade atvd) {
		return atvd.getCodigo().compareTo(this.getCodigo());
	}

	/**
	 * Retorna a String que representa uma atividade. A representação segue o
	 * formato "DescriçãoAtvd (Risco - DescriçãoRisco)
	 */
	@Override
	public String toString() {
		return this.descricaoAtvd + " (" + this.risco + " - " + this.descricaoRisco + ")";
	}

	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {

		String[] precedentes = idPrecedente.split("A");
		String[] subsequentes = idSubsquente.split("A");
		int numPre = Integer.parseInt(precedentes[1]);
		int numSub = Integer.parseInt(subsequentes[1]);
		String novoIdPrecedente = "A" + (numPre + 1);
		String novoIdSubsquente = "A" + (numSub + 1);

		if (this.proxAtividade == null) {
			Atividade atvd = this.proxAtividade;
			this.proxAtividade = atvd;
		} else {
			this.proxAtividade.defineProximaAtividade(novoIdPrecedente, novoIdSubsquente);
		}
	}

	public void tiraProximaAtividade(String idPrecedente) {
		String[] precedentes = idPrecedente.split("A");
		int numPre = Integer.parseInt(precedentes[1]);
		String novoIdPrecedente = "A" + (numPre - 1);
		
		if (idPrecedente.equals("A1")) {
			if(this.proxAtividade == null) {
				throw new RuntimeException();
			}
			this.proxAtividade = this.proxAtividade.proxAtividade;
		}
		this.proxAtividade.tiraProximaAtividade(novoIdPrecedente);
	}

	public int contaProximos(String idPrecedente) {
		String[] precedentes = idPrecedente.split("A");
		int numPre = Integer.parseInt(precedentes[1]);
		String novoIdPrecedente = "A" + (numPre + 1);
		
		if (this.proxAtividade == null) {
			return 1;
		}
		return 1 + this.proxAtividade.contaProximos(novoIdPrecedente);
	}

	public String pegaProximo(String idAtividade, int enesimaAtividade) {	
		if ( enesimaAtividade == -1) {
			return null;
		}
		
		if (enesimaAtividade == 0) {
			return this.getCodigo();
		}
		
		if (this.proxAtividade ==null) {
			return null;
		}
		return this.proxAtividade.pegaProximo(idAtividade, enesimaAtividade -1);
	}

	public String getRisco() {
		return risco;
	}
	
	public String pegaMaiorRiscoAtividades(String idAtividade) {
		if (this.proxAtividade == null) {
			return idAtividade;
		}else { 
			if (this.proxAtividade.risco.length() >this.risco.length()) {
				return this.proxAtividade.getCodigo();
			}
			return idAtividade;
		}
	}
}
