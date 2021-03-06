package Entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import utils.Validador;

/**
 * Representaçao de uma Pesquisa
 * 
 * @author Hiarly Fernandes de Souto
 *
 */
public class Pesquisa implements Comparable<Pesquisa>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4239769601138046989L;
	/**
	 * Problema associado a uma pesquisa.
	 */
	private Problema problema;
	/**
	 * Lista responsavel por armazenar os objetivos de uma pesquisa.
	 */
	private List<Objetivo> objetivos;
	/**
	 * Lista responsavel por armazenar os pesquisadores que fazem parte de uma
	 * pesquisa.
	 */
	private ArrayList<Pesquisador> pesquisadores;
	/**
	 * Uma classe validador, serve para validar as entradas nos metodos
	 */
	private Validador validador;
	/**
	 * Um texto livre com um resumo da pesquisa a ser realizada.
	 */
	private String descricao;
	/**
	 * Um marcador da area ou tema a ser colocado. Pode ter ate 4 topicos, separados
	 * por vírgula e ter ate 255 caracteres.
	 */
	private String campoInterese;

	/**
	 * O codgio de indentificaçao da pesquisa
	 */
	private String codigo;
	/**
	 * Armaza se a pesquisa esta ativa ou nao
	 */
	private boolean ativa;

	private List<Atividade> atividades;

	/**
	 * Constroi uma pesquisa a partir da descricao, do campo de interesse e do
	 * codigo de identificaçao da pesquisa
	 * 
	 * @param descricao     Um texto livre com um resumo da pesquisa a ser
	 *                      realizada.
	 * @param campoInterese Um marcador da area ou tema a ser colocado. Pode ter ate
	 *                      4 topicos, separados por vírgula e ter ate 255
	 *                      caracteres.
	 * @param codigo        O codigo de idetificaçao da pesquisa
	 */
	public Pesquisa(String descricao, String campoInterese, String codigo) {
		this.validador = new Validador();
		this.descricao = descricao;
		this.campoInterese = campoInterese;
		this.codigo = codigo;
		this.ativa = true;
		validador.validar(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.validar(campoInterese, "Formato do campo de interesse invalido.");
		this.pesquisadores = new ArrayList<Pesquisador>();
		this.objetivos = new ArrayList<Objetivo>();
		this.problema = null;
		this.atividades = new ArrayList<Atividade>();
	}

	/**
	 * Representaçao, em String, de uma pesquisa
	 */
	@Override
	public String toString() {
		return codigo + " - " + this.descricao + " - " + campoInterese;
	}

	public Problema getProblema() {
		return problema;
	}

	public String getCampoInterese() {
		return campoInterese;
	}

	/**
	 * Gera o hashCode da pesquisa
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	/**
	 * Compara duas pesquisas a partir do codigo
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pesquisa other = (Pesquisa) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	/**
	 * Encerra uma pesquisa que esta ativa
	 */
	public void encerrarPesquisa() {
		if (this.ativa == true) {
			ativa = false;
		} else {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
	}

	/**
	 * Ativa uma pesquisa que estava desativada
	 */
	public void ativarPesquisa() {
		if (ativa == true) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		} else {
			ativa = true;
		}
	}

	/**
	 * Verifica se a pesquisa esta ativada
	 * 
	 * @return o boolean se uma conta é ativa ou não.
	 */
	public boolean ehAtiva() {
		return this.ativa;
	}

	/**
	 * Altera uma pesquisa
	 * 
	 * @param campo     O campo da pesquisa a ser alterado, descricao ou campo de
	 *                  interesse
	 * @param novoValor O novo valor do campo selecionado
	 */
	public void alteraPesquisa(String campo, String novoValor) {
		validador.validar(campoInterese, "Formato do campo de interesse invalido.");
		if (ativa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}

		if (campo.equals("DESCRICAO")) {
			if (novoValor.trim().equals("")) {
				throw new IllegalArgumentException("Descricao nao pode ser nula ou vazia.");
			}
			this.descricao = novoValor;

		} else if (campo.equals("CAMPO")) {
			this.campoInterese = novoValor;

		} else {
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}

	/**
	 * Adiciona um pesquisador a lista de pesquisadores dessa pesquisa.
	 * 
	 * @param pesquisador Pesquisador a ser adicionado.
	 */
	public boolean adicionarPesquisador(Pesquisador pesquisador) {
		if (pesquisadores.contains(pesquisador)) {
			return false;
		} else {
			pesquisadores.add(pesquisador);
			return true;
		}

	}

	/**
	 * Remove um pesquisador da lista de pesquisadores dessa pesquisa.
	 * 
	 * @param pesquisador Pesquisador a ser removido.
	 */
	public boolean removerPesquisador(Pesquisador pesquisador) {
		if (!pesquisadores.contains(pesquisador)) {
			return false;
		}
		pesquisadores.remove(pesquisador);
		return true;
	}

	/**
	 * Associa um Problema a pesquisa.
	 * 
	 * @param problema Problema a ser associado.
	 * @return Retorna sucesso caso tenha sucesso e false caso contrario.
	 */
	public boolean associaProblema(Problema problema) {
		if (this.problema == null) {
			this.problema = problema;
			return true;
		}
		if (this.problema.equals(problema)) {
			return false;
		}
		throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
	}

	/**
	 * Desassocia um Problema a pesquisa.
	 * 
	 * @param problema Problema a ser associado.
	 * @return Retorna sucesso caso tenha sucesso e false caso contrario.
	 */
	public boolean desassociaProblema() {
		if (this.problema == null) {
			return false;
		}
		this.problema = null;
		return true;
	}

	/**
	 * Associa um objetivo a pesquisa adicionando o objetivo ao array de objetivos.
	 * 
	 * @param objetivo Objetivo a ser associado.
	 * @return Retorna sucesso caso seja associado e false caso contrario.
	 */
	public boolean associaObjetivo(Objetivo objetivo) {
		for (Objetivo objetivoAtual : objetivos) {
			if (objetivo.equals(objetivoAtual)) {
				return false;
			}
		}

		if (objetivo.isAssociado()) {
			throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");

		}
		objetivos.add(objetivo);
		return true;
	}

	/**
	 * Desassocia um objetivo da pesquisa.
	 * 
	 * @param objetivo Objetivo a ser desassiciado.
	 */
	public void desassociaObjetivo(Objetivo objetivo) {
		objetivos.remove(objetivo);
	}
	/**
	 * verifica se a pesquisa tem atividades com itens pendentes
	 */
	public void validadorDePendencia() {
		int contador = 0;
		for (Atividade atividades : getAtividades()) {
			if (atividades.temPendentes()) {
				contador += 1;
			}
		}

		if (contador == 0) {
			throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
		}

	}
	/**
	 * pega a atividade mais antiga
	 * @return string com o codigo da atividade mais antiga
	 */
	public String getMaisAntigo() {
		for (Atividade atividade : getAtividades()) {
			if (atividade.temPendentes())
				return atividade.getCodigo();
		}
		return "";

	}
	/**
	 * pega a ativvidade com menos pendentes
	 * @return string com o codigo da atividade com menos pendencias 
	 */
	public String getMenosPendentes() {

		Atividade menosPendente = null;
		int menorQuantPendentes = 1000;

		for (int i = 0; i < getAtividades().size(); i++) {
			int numero = getAtividades().get(i).quantPendentes();
			if (numero != 0) {
				if (numero < menorQuantPendentes) {
					menorQuantPendentes = numero;
					menosPendente = getAtividades().get(i);

				}
			}
		}
		return menosPendente.getCodigo();

	}
	/**
	 * pega a atividae com maior risco
	 * @return	string com codigo da atividade com maior risco
	 */
	public String getMaiorRisco() {

		for (int i = 0; i < getAtividades().size(); i++) {
			if (getAtividades().get(i).getRisco().equals("ALTO")) {
				return getAtividades().get(i).getCodigo();
			}
		}

		for (int i = 0; i < getAtividades().size(); i++) {
			if (getAtividades().get(i).getRisco().equals("MEDIO")) {
				return getAtividades().get(i).getCodigo();
			}
		}

		for (int i = 0; i < getAtividades().size(); i++) {
			if (getAtividades().get(i).getRisco().equals("BAIXO")) {
				return getAtividades().get(i).getCodigo();
			}
		}

		return "";

	}
	/**
	 * pega a atividade com a maior duracao total
	 * @return string com o codigo da atividade com maior duracao
	 */
	public String getAtividadeMaiorDuracao() {
		Atividade maiorDuracao = null;
		int numeroMaiorDuracao = 0;
		for (int i = 0; i < getAtividades().size(); i++) {
			int numero = getAtividades().get(i).getDuracao();
			if (numero > numeroMaiorDuracao) {
				maiorDuracao = getAtividades().get(i);
			}
		}
		return maiorDuracao.getCodigo();

	}

	@Override
	public int compareTo(Pesquisa o) {
		return o.getCodigo().compareTo(this.codigo);
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Objetivo> getObjetivos() {
		return objetivos;
	}
	/**
	 * adiciona uma atividade na pesquisa
	 * @param atividade a atividade a ser adcionada
	 * @return true caso seja possivel adicionar , false caso nao
	 */
	public boolean adicionaAtividade(Atividade atividade) {
		if (getAtividades().contains(atividade)) {
			return false;
		} else {
			getAtividades().add(atividade);
			return true;
		}
	}
	/**
	 * tira uma atividade de uma pesquisa
	 * @param atividade a atividade a ser retirada
	 * @return true caso consiga tirar , false caso nao
	 */
	public boolean tiraAtividade(Atividade atividade) {
		if (getAtividades().contains(atividade)) {
			getAtividades().remove(atividade);
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Pega os dados de um Pesquisador para ser gravado
	 * @return Retorna uma string com as informacoes do Pesquisador
	 */
	public String gravarPesquisadores() {
		String toStringPesquisadores = "";
		for (Pesquisador pesquisador : pesquisadores) {
			toStringPesquisadores += "		- " + pesquisador.toString() + System.lineSeparator();
		}
		return toStringPesquisadores;
	}

	/**
	 * Pega os dados de um problema para ser gravado
	 * @return Retorna uma String com as informacoes do Problema
	 */
	public String gravarProblema() {
		return "		- " + problema.toString();
	}

	/**
	 * 
	 * Pega os dados de um Obejtivo para ser gravado
	 * @return Retorna uma String com as informaceos do Objetivo
	 */
	public String gravarObjetivos() {
		String str = "";
		for (Objetivo objetivo : objetivos) {
			str += "		- " + objetivo + System.lineSeparator();
		}

		return str;
	}

	/**
	 * Pega os dados de uma atividade para ser gravado
	 * @return Retorna uma string com os dados da atividade para ser gravado
	 */
	public String gravarAtividades() {
		String str = "";
		for (Atividade atividade : getAtividades()) {
			str += "		" + atividade.gravarResumo();
		}
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * Grava os resultado da Pesquisa
	 * @return Retorna uma string com as informacoes da Pesquisa para ser gravado
	 */
	public String gravarResultado() {
		String str = "	- Resultados:" + System.lineSeparator();
		for (Atividade atv : getAtividades()) {
			str += atv.gravarResultado();
		}

		str = str.substring(0, str.length() - 1) + "\"";
		return str;
	}

	/**
	 * Pega as atividade de uma Pesquisa
	 * @return
	 */
	public List<Atividade> getAtividades() {
		return atividades;
	}
}
