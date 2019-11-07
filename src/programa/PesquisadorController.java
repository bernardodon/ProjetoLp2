package programa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.Validador;

/**
 * Representacao de um controlador de pesquisador.
 * 
 * @author Bernard Dantas Odon.
 *
 */
public class PesquisadorController {

	/**
	 * Mapa que armazena pesquisadores como valor e o email deles como chave.
	 */
	private PesquisadorMapController pesquisadorMapController;
	/**
	 * Validador que serve para verificar os parametros dos metodos.
	 */
	private Validador validador;

	/**
	 * Constroi um controlador de pesquisador.
	 */

	private int numeroDoResultadoPesquisador;

	public PesquisadorController(PesquisadorMapController pesquisadorMapContoller) {
		validador = new Validador();
		this.numeroDoResultadoPesquisador = 0;
		this.pesquisadorMapController = pesquisadorMapContoller;
	}

	/**
	 * Cadastra um pesquisador no mapa de pesquisadores.
	 * 
	 * @param nome      Nome do pesquisador.
	 * @param funcao    Funcao do pesquisador.
	 * @param biografia Biografia do pesquisador.
	 * @param email     Email do pesquisador.
	 * @param fotoURL   URL da foto do pesquisador.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		validador.validar(nome, "Campo nome nao pode ser nulo ou vazio.");
		validador.validar(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		validador.validar(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		validador.validar(email, "Campo email nao pode ser nulo ou vazio.");
		validador.validar(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		validador.validarEmailPesquisador(email);
		validador.validarFotoPesquisador(fotoURL);
		pesquisadorMapController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	/**
	 * Altera um atributo de um pesquisador a partir do email dele, do nome do
	 * atributo que sera alterado e do novo valor a ser assumido.
	 * 
	 * @param email     Email do pesquisador.
	 * @param atributo  Atributo do pesquisador.
	 * @param novoValor Novo valor a ser assumido pelo atributo.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {

		validador.validar(atributo, "Atributo nao pode ser vazio ou nulo.");
		validador.validarEmailPesquisador(email);
		Pesquisador pesquisador = pesquisadorMapController.getPesquisador(email);

		switch (atributo) {
		case "EMAIL":
			alteraEmail(email, novoValor);
			break;
		default:
			pesquisador.alteraAtributo(atributo, novoValor);
		}
	}

	/**
	 * Ativa um Pesquisador.
	 * 
	 * @param email Email do pesquisador.
	 */
	public void ativaPesquisador(String email) {
		Pesquisador pesquisador = pesquisadorMapController.getPesquisador(email);
		pesquisador.ativa();
	}

	/**
	 * Desativa um Pesquisador.
	 * 
	 * @param email Email do pesquisador.
	 */
	public void desativaPesquisador(String email) {
		Pesquisador pesquisador = pesquisadorMapController.getPesquisador(email);
		pesquisador.desativa();
	}

	/**
	 * Exibe a representacao de um pesquisador.
	 * 
	 * @param email Email do pesquisador.
	 * @return Retorna a representacao em String de um pesquisador.
	 */
	public String exibePesquisador(String email) {
		validador.validar(email, "Campo email nao pode ser nulo ou vazio.");
		validador.validarEmailPesquisador(email);

		Pesquisador pesquisador = pesquisadorMapController.getPesquisador(email);


		return pesquisador.toString();
	}

	/**
	 * Checa se um pesquisador eh ativo.
	 * 
	 * @param email Email do pesquisador.
	 * @return Retorna true se o pesquisador eh ativo e false caso contrario.
	 */
	public boolean pesquisadorEhAtivo(String email) {
		validador.validar(email, "Email nao pode ser vazio ou nulo.");
		Pesquisador pesquisador = pesquisadorMapController.getPesquisador(email);

		return pesquisador.getAtivado();
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double iEA) {
		validador.validar(email, "Campo email nao pode ser nulo ou vazio.");
		validador.validaSemestreAluno(semestre);
		validador.validaIeaAluno(iEA);
		
		Pesquisador pesquisador = pesquisadorMapController.getPesquisador(email);

		if (!pesquisador.getFuncao().equals("estudante")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		} else {
			pesquisador.adicionarEspecialidadeAluno(semestre, iEA);
		}
	}

	public void cadastratEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		validador.validar(email, "Campo email nao pode ser nulo ou vazio.");
		validador.validar(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validador.validar(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		validador.validar(data, "Campo data nao pode ser nulo ou vazio.");
		validador.validarDataProfessor(data);
		
		Pesquisador pesquisador = pesquisadorMapController.getPesquisador(email);

		if (!pesquisador.getFuncao().equals("professor")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		} else {
			pesquisador.adicionaEspecialidadeProfessor(formacao, unidade, data);
		}

	}

	public String listaPesquisadores(String tipo) {
		validador.validarTipo(tipo);

		String str = "";
		for (Pesquisador pesquisador : pesquisadorMapController.getPesquisadoresValues()) {
			if (pesquisador.getFuncao().equals(tipo.toLowerCase())) {
				str += pesquisador.toString() + " | ";
			}
		}

		str = str.substring(0, str.length() - 3);
		return str;

	}

	private void alteraEmail(String email, String novoValor) {
		validador.validar(novoValor, "Campo email nao pode ser nulo ou vazio.");
		validador.validarEmailPesquisador(novoValor);

		Pesquisador pesquisadorAntigo = pesquisadorMapController.getPesquisador(email);
		Pesquisador novoPesquisador = pesquisadorAntigo.alterarEmail(novoValor);
		pesquisadorMapController.remove(email);
		pesquisadorMapController.put(novoValor, novoPesquisador);
	}

	public String buscaTermoPesquisadores(String termo) {
		String msg = "";

		List<Pesquisador> pesquisadoresValues = new ArrayList<Pesquisador>();
		pesquisadoresValues.addAll(pesquisadorMapController.getPesquisadoresValues());
		Collections.sort(pesquisadoresValues);

		for (Pesquisador p : pesquisadoresValues) {
			if (p.getBiografia().toLowerCase().contains(termo.toLowerCase())) {
				msg += p.getEmail() + ": " + p.getBiografia() + " | ";
				numeroDoResultadoPesquisador++;
			}
		}

		return msg;
	}

	public int getNumeroDoResultadoPesquisador() {
		return numeroDoResultadoPesquisador;
	}

}
