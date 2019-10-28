package programa;

import java.util.HashMap;
import java.util.Map;
import utils.Validador;

/**
 * Representacao de um controlador de pesquisador.
 * @author Bernard Dantas Odon.
 *
 */
public class PesquisadorController {
	
	/**
	 * Mapa que armazena pesquisadores como valor e o email deles como chave.
	 */
	private Map<String, Pesquisador> pesquisadores;
	/**
	 * Validador que serve para verificar os parametros dos metodos.
	 */
	private Validador validador;
	
	/**
	 * Constroi um controlador de pesquisador.
	 */
	public PesquisadorController() {
		pesquisadores = new HashMap<>();
		validador = new Validador();
	}

	/**
	 * Cadastra um pesquisador no mapa de pesquisadores.
	 * @param nome Nome do pesquisador.
	 * @param funcao Funcao do pesquisador.
	 * @param biografia Biografia do pesquisador.
	 * @param email Email do pesquisador.
	 * @param fotoURL URL da foto do pesquisador.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		validador.validar(nome, "Campo nome nao pode ser nulo ou vazio.");
		validador.validar(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		validador.validar(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		validador.validar(email, "Campo email nao pode ser nulo ou vazio.");
		validador.validar(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		validador.validarEmailPesquisador(email);
		validador.validarFotoPesquisador(fotoURL);
		pesquisadores.put(email, new Pesquisador(nome, biografia, email, fotoURL, funcao));
	}

	/**
	 * 	Altera um atributo de um pesquisador a partir do email dele, do nome do atributo que sera alterado e do novo valor a ser assumido.
	 * @param email Email do pesquisador.
	 * @param atributo Atributo do pesquisador.
	 * @param novoValor Novo valor a ser assumido pelo atributo.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		
		validador.validarEmailPesquisador(email);
		checaInexistenciaPesquisador(email);
		
		switch (atributo) {
			case "nome":
				alteraNome(email, novoValor);
				break;
				
			case "funcao":
				alteraFuncao(email, novoValor);
				break;
			
			case "biografia":
				alteraBiografia(email, novoValor);
				break;
				
			case "email":
				alteraEmail(email, novoValor);
				break;
				
			case "fotoURL":
				alteraFotoURL(email, novoValor);
				break;
		}
	}
	
	/**
	 * Ativa um Pesquisador.
	 * @param email Email do pesquisador.
	 */
	public void ativaPesquisador(String email){
		checaInexistenciaPesquisador(email);
		getPesquisador(email).ativa();
	}

	/**
	 * Desativa um Pesquisador.
	 * @param email Email do pesquisador.
	 */
	public void desativaPesquisador(String email){
		checaInexistenciaPesquisador(email);
		getPesquisador(email).desativa();
	}

	/**
	 * Exibe a representacao de um pesquisador.
	 * @param email Email do pesquisador.
	 * @return Retorna a representacao em String de um pesquisador.
	 */
	public String exibePesquisador(String email) {
		validador.validar(email, "Campo email nao pode ser nulo ou vazio.");
		validador.validarEmailPesquisador(email);
		
		checaInexistenciaPesquisador(email);
		
		return getPesquisador(email).toString();
	}
	
	/**
	 * Checa se um pesquisador eh ativo.
	 * @param email Email do pesquisador.
	 * @return Retorna true se o pesquisador eh ativo e false caso contrario.
	 */
	public boolean pesquisadorEhAtivo(String email) {
		validador.validar(email, "Email nao pode ser vazio ou nulo.");
		checaInexistenciaPesquisador(email);
		return getPesquisador(email).getAtivado();
	}
	
	/**
	 * Pega um pesquisador a partir do email.
	 * @param email Email do pesquisador.
	 * @return Retorna um pesquisador.
	 */
	private Pesquisador getPesquisador(String email) {
		return pesquisadores.get(email);
	}
	
	/**
	 * Checa se um pesquisador esta cadastrado no mapa de pesquisadores.
	 * @param email Email do pesquisador.
	 */
	private void checaInexistenciaPesquisador(String email) {
		if(!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
	}
	
	/**
	 * Altera o URL da foto de um pesquisador.
	 * @param email Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pelo URL da foto.
	 */
	private void alteraFotoURL(String email, String novoValor) {
		validador.validar(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
		validador.validarFotoPesquisador(novoValor);
		getPesquisador(email).setFotoURL(novoValor);
	}
	/**
	 * Altera o URL da foto de um pesquisador.
	 * @param email Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pelo URL da foto.
	 */
	private void alteraEmail(String email, String novoValor) {
		validador.validar(novoValor, "Campo email nao pode ser nulo ou vazio.");
		validador.validarEmailPesquisador(novoValor);
		pesquisadores.put(novoValor, new Pesquisador(pesquisadores.remove(email), novoValor));
	}
	/**
	 * Altera a biografia de um pesquisador.
	 * @param email Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pela biografia do pesquisador.
	 */
	private void alteraBiografia(String email, String novoValor) {
		validador.validar(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
		getPesquisador(email).setBiografia(novoValor);
	}
	/**
	 * Altera a funcao de um pesquisador.
	 * @param email Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pela funcao do pesquisador.
	 */
	private void alteraFuncao(String email, String novoValor) {
		validador.validar(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
		getPesquisador(email).setFuncao(novoValor);
	}
	/**
	 * Altera o nome de um pesquisador.
	 * @param email Email do pesquisador.
	 * @param novoValor Novo valor a ser assumido pelo nome do pesquisador.
	 */
	private void alteraNome(String email, String novoValor) {
		validador.validar(novoValor, "Campo nome nao pode ser nulo ou vazio.");
		getPesquisador(email).setNome(novoValor);
	}
}
