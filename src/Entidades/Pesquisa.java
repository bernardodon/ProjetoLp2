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
	public boolean desassociaProblema(Problema problema) {
		if (this.problema == null || !this.problema.equals(problema)) {
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

	public void validadorDePendencia() {
		int contador = 0;
		for (Atividade atividades : atividades) {
			if (atividades.temPendentes()) {
				contador += 1;
			}
		}

		if (contador == 0) {
			throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");
		}

	}

	public String getMaisAntigo() {

		return atividades.get(0).getCodigo();
	}

	public String getMenosPendentes() {

		Atividade menosPendente = null;
		int menorQuantPendentes = 1000;

		for (int i = 0; i < atividades.size(); i++) {
			int numero = atividades.get(i).quantPendentes();
			if (numero < menorQuantPendentes) {
				menosPendente = atividades.get(i);
			}
		}
		return menosPendente.getCodigo();
	}

	public String getMaiorRisco() {

		for (int i = 0; i < atividades.size(); i++) {
			if (atividades.get(i).getRisco().equals("ALTO")) {
				return atividades.get(i).getCodigo();
			}
		}

		for (int i = 0; i < atividades.size(); i++) {
			if (atividades.get(i).getRisco().equals("MEDIO")) {
				return atividades.get(i).getCodigo();
			}
		}

		for (int i = 0; i < atividades.size(); i++) {
			if (atividades.get(i).getRisco().equals("BAIXO")) {
				return atividades.get(i).getCodigo();
			}
		}

		return "";

	}

	public String getAtividadeMaiorDuracao() {
		Atividade maiorDuracao = null;
		int numeroMaiorDuracao = 0;
		for (int i = 0; i < atividades.size(); i++) {
			int numero = atividades.get(i).getDuracao();
			if (numero > numeroMaiorDuracao) {
				maiorDuracao = atividades.get(i);
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

	public boolean adicionaAtividade(Atividade atividade) {
		if (atividades.contains(atividade)) {
			return false;
		} else {
			atividades.add(atividade);
			return true;
		}
	}

	public boolean tiraAtividade(Atividade atividade) {
		if (atividades.contains(atividade)) {
			atividades.remove(atividade);
			return true;
		} else {
			return false;
		}
	}

	public String gravarPesquisadores() {
		String toStringPesquisadores = "";
		for (Pesquisador pesquisador : pesquisadores) {
			toStringPesquisadores += "		- " + pesquisador.toString() + System.lineSeparator();
		}
		return toStringPesquisadores;
	}

	public String gravarProblema() {
		return "		- " + problema.toString();
	}

	public String gravarObjetivos() {
		String str = "";
		for (Objetivo objetivo : objetivos) {
			str += "		- " + objetivo + System.lineSeparator();
		}

		return str;
	}

	public String gravarAtividades() {
		String str = "";
		for (Atividade atividade : atividades) {
			str += "		" + atividade.gravarResumo();
		}

		return str;
	}

	public String gravarResultado() {
		String str = "	- Resultados:" + System.lineSeparator();
		for (Atividade atv : atividades) {
			str += atv.gravarResultado();
		}

		return str;
	}
}
