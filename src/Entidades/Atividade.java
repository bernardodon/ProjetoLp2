package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.Validador;

/**
 * Representacao de uma atividade referentes a uma pesquisa cientifica. Toda
 * atividade deve ter uma descricao, uma duracao, um nivel de risco e uma
 * descricao do risco.
 * 
 * @author Italo Miguel Castor Diniz Pinheiro.
 */
public class Atividade implements Comparable<Atividade>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -169186575795418030L;
	/**
	 * Um validador.
	 */
	private Validador validador;
	/**
	 * O codigo da atividade.
	 */
	private String codigo;

	/*
	 * Descricao da atividade.
	 */
	private String descricaoAtvd;

	/**
	 * O nÃ­vel de risco de uma atividade.
	 */
	private String risco;

	/**
	 * A descricao do risco.
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
	 * Constroi uma atividade a partir de sua descricao, do nÃ­vel do risco dela e da
	 * descricao do risco.
	 * 
	 * @param descricaoAtvd  a descricao da atividade.
	 * @param risco          o nÃ­vel de risco da atividade.cc
	 * @param descricaoRisco a descricao do risco da atividade.
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

	public void checaInexistenciaItem(int numero) {
		if (itens.size() < numero) {
			throw new IllegalArgumentException("Item nao encontrado.");
		}
	}

	public void checaExecucaoItem(int numero) {
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

	/**
	 * Retorna a String que representa a descricao da ativdidade.
	 * 
	 * @return a descricao da atividade.
	 */
	public String getDescricaoAtvd() {
		return descricaoAtvd;
	}

	/**
	 * Retorna a representacao em String da descricao do risco de uma atividade.
	 * 
	 * @return a descricao do risco da atividade.
	 */
	public String getDescricaoRisco() {
		return descricaoRisco;
	}

	/**
	 * Retorna a representacao em String do codigo da atividade.
	 * 
	 * @return o codigo da atividade.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Retorna a representacao em String do risco de uma atividade.
	 * 
	 * @return o risco de uma atividade.
	 */
	public String getRisco() {
		return risco;
	}
	
	public boolean isAssociado() {
		return isAssociado;
	}

	public Boolean temPendentes() {
		for (Item item : itens) {
			if (item.getStatus().equals("PENDENTE")) {
				return true;
			}
		}

		return false;
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
			if (i.getStatus().equals("PENDENTE")) {
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
			if (i.getStatus().equals("REALIZADO")) {
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
	 * @return a representacao em String que representa todos os itens de uma
	 *         atividade.
	 */
	public String exibeItens() {
		String msg = "";

		for (Item i : itens) {
			msg += " | " + i.toString();
		}
		return msg;
	}

	/**
	 * Compara duas atividades a partir do seu codigo.
	 */
	@Override
	public int compareTo(Atividade atvd) {
		return atvd.getCodigo().compareTo(this.getCodigo());
	}

	/**
	 * Retorna a String que representa uma atividade. A representacao segue o
	 * formato "DescricaoAtvd (Risco - DescricaoRisco)
	 */
	@Override
	public String toString() {
		return this.descricaoAtvd + " (" + this.risco + " - " + this.descricaoRisco + ")";
	}

	/**
	 * Impede que ocorram loops na funcao de definir a proxima atividade.
	 * 
	 * @param atividade uma atividade.
	 * @param valor     o valor que representa a distancia entre a atividade atual e
	 *                  atividade que deseja-se pegar.
	 */
	private void impedirLoop(Atividade compara, Atividade atividade) {
		if (atividade.equals(compara)) {
			throw new IllegalArgumentException("Criacao de loops negada.");
		} else if (atividade.proxAtividade != null) {
			impedirLoop(compara, atividade.proxAtividade);
		}

	}

	/**
	 * Definira a proxima atividade na ordem.
	 * 
	 * @param atividade Uma atividade.
	 */

	public void defineProximaAtividade(Atividade atividade) {
		if (proxAtividade != null) {
			throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
		}
		if (atividade.proxAtividade == this && this.proxAtividade == atividade) {
			throw new IllegalArgumentException("Criacao de loops negada.");
		} else {
			impedirLoop(this, atividade);
			this.proxAtividade = atividade;
		}

	}

	/**
	 * Remove a proxima atividade da atividade atual.
	 */
	public void tiraProximaAtividade() {
		this.proxAtividade = null;
	}

	/**
	 * Retorna a representacao em inteiro da quantidade de atividades proximas existentes depois da atividade atual.
	 * 
	 * @return o inteiro que representa a quantidade de atividades proximas depois da atividade atual.
	 */
	public int contaProximos() {
		if (this.proxAtividade == null) {
			return 0;
		} else {
			return 1 + this.proxAtividade.contaProximos();
		}
	}

	/**
	 * Pega a enesima atividade depois da atividade atual. Caso nao, um erro sera lancado explicando o que ocorreu. 
	 * 
	 * @param enesimaAtividade
	 * @return a enesima atividade depois da atividade atual.
	 */
	public Atividade pegaProximo(int enesimaAtividade) {

		if (enesimaAtividade == 0) {
			return this;
		}
		if (this.proxAtividade == null) {
			throw new IllegalArgumentException("Atividade inexistente.");
		}

		if (enesimaAtividade == -1) {
			return null;
		}

		return this.proxAtividade.pegaProximo(enesimaAtividade - 1);
	}

	
	/**
	 * Retorna a atividade proxima da atual com o maior risco.
	 * 
	 * @return a atividade que vem em seguida da atual com maior risco.
	 */
	public Atividade pegaMaiorRiscoAtividades() {
		if (proxAtividade == null) {
			throw new IllegalArgumentException("Nao existe proxima atividade.");
		} else {
			return pegaMaiorRiscoAtividades(this.proxAtividade, this.proxAtividade.proxAtividade);
		}
		 
	}
	
	private Atividade pegaMaiorRiscoAtividades(Atividade atv1, Atividade atv2) {
		if (atv2 == null) {
			return atv1;
		} else if (atv2.risco.equals("ALTO")) {
			return pegaMaiorRiscoAtividades(atv2, atv2.proxAtividade);
		} else if (atv1.risco.equals("ALTO")) {
			return pegaMaiorRiscoAtividades(atv1, atv2.proxAtividade);
		} else if (atv2.risco.equals("MEDIO")) {
			return pegaMaiorRiscoAtividades(atv2, atv2.proxAtividade);
		} else if (atv1.risco.equals("MEDIO")) {
			return pegaMaiorRiscoAtividades(atv1, atv2.proxAtividade);
		} else if (atv2.risco.equals("BAIXO")) {
			return pegaMaiorRiscoAtividades(atv2, atv2.proxAtividade);
		} else if (atv1.risco.equals("BAIXO")) {
			return pegaMaiorRiscoAtividades(atv1, atv2.proxAtividade);
		}
		
		return null;
		
	}

	public String gravarResumo() {
		String str = "- " + this.descricaoAtvd + " (" + risco + " - " + descricaoRisco + ")" + System.lineSeparator();
		for (int i = 0; i < itens.size(); i++) {
			str += "			- " + itens.get(i).getStatus() + " - ITEM" + (i+1) + System.lineSeparator();			
		}
		
		return str;
		
	}

	public String gravarResultado() {
		String str = "		- " + descricaoAtvd + System.lineSeparator();
		int qtdItem = 0;
		for (Item item : itens) {
			if (item.getStatus().equals("REALIZADO")) {
				qtdItem += 1;
			}
		}
		for (int i = 0; i < itens.size() ; i++) {
			
			if (itens.get(i).getStatus().equals("REALIZADO")) {
				str += "			- ITEM" + (i+1) + " - " + (duracao / qtdItem) + System.lineSeparator();				
			}
		}
		

		for (String string : resultados) {
			str += "			- " + string + System.lineSeparator();
		}
		return str;
	}
}
