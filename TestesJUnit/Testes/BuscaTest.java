package Testes;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.AtividadeController;
import Controllers.PesquisaController;
import Controllers.PesquisadorController;
import Controllers.ProblemaObjetivoController;
import Repositorios.AtividadesRepositorio;
import Repositorios.ObjetivosRepositorio;
import Repositorios.PesquisadoresRepositorio;
import Repositorios.PesquisasRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Busca;

class BuscaTest {

	private PesquisasRepositorio pesquisasRepositorio = new PesquisasRepositorio();
	private PesquisadoresRepositorio pesquisadoresRepositorio = new PesquisadoresRepositorio();
	private AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
	private ObjetivosRepositorio objetivosRepositorio = new ObjetivosRepositorio();
	private ProblemasRepositorio problemasRepositorio = new ProblemasRepositorio();

	private Busca busca = new Busca();
	private PesquisaController pesquisaController = new PesquisaController(pesquisasRepositorio, busca);
	private PesquisadorController pesquisadorController = new PesquisadorController(pesquisadoresRepositorio, busca);
	private AtividadeController atividadeController = new AtividadeController(atividadeRepositorio, busca);
	private ProblemaObjetivoController problemaObjetivoController = new ProblemaObjetivoController(objetivosRepositorio,
			problemasRepositorio, busca);

	@BeforeEach
	void cadastraAtividades() {
		atividadeController.cadastraAtividade("Atividade mt doida", "ALTO", "Risco bem alto ne");
		atividadeController.cadastraAtividade("Atividade suave", "MEDIO", "Risco DE BOA");
	}

	@BeforeEach
	void testCadastraProblema() {
		assertEquals(problemaObjetivoController.cadastraProblema("descricao1", 3), "P1");
		assertEquals(problemaObjetivoController.cadastraProblema("descricao2", 5), "P2");
		assertEquals(problemaObjetivoController.cadastraProblema("descricao3", 1), "P3");
	}

	@BeforeEach
	void testCadastraObjetivos() {
		assertEquals(problemaObjetivoController.cadastraObjetivo("GERAL", "descricao1", 4, 2), "O1");
		assertEquals(problemaObjetivoController.cadastraObjetivo("GERAL", "descricao2", 5, 4), "O2");
		assertEquals(problemaObjetivoController.cadastraObjetivo("ESPECIFICO", "descricao3", 2, 5), "O3");
		assertEquals(problemaObjetivoController.cadastraObjetivo("ESPECIFICO", "descricao4", 4, 4), "O4");
	}

	@BeforeEach
	void testCadastraPesquisa() {
		assertEquals("COM1", pesquisaController.cadastraPesquisa(
				"Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia"));
		assertEquals("ELE1", pesquisaController.cadastraPesquisa(
				"Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.",
				"eleicao"));
		assertEquals("COM2", pesquisaController
				.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto.", "computacao, poo"));
		assertEquals("FER1",
				pesquisaController.cadastraPesquisa(
						"Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces.",
						"fermentacao, cerveja"));
	}

	@BeforeEach
	void testCadastraPesquisador() {
		pesquisadorController.cadastraPesquisador("Joao", "Buscar melhorias para o programa", "Doutor em sistemas",
				"joao@gmail.com", "http://joaogmail.com");
		pesquisadorController.cadastraPesquisador("Maria", "Modelar o site", "Especialista em Web Desing",
				"maria@gmail.com", "https://www.mariafoto.com.br");
	}

	@Test
	void buscaTudo() {
		buscaTermo("men");
		assertEquals("FER1: Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces. "
				+ "| FER1: fermentacao, cerveja | COM1: Homofobia em mensagens online de alunos de computacao do primeiro periodo.",
				busca.resultadoDaBusca());
		assertThrows(IllegalArgumentException.class, () -> {
			buscaTermo("");
		});
	}

	@Test
	void contabilizarResultados() {
		buscaTermo("men");
		assertEquals(3, busca.contaResultadosBusca("men"));

		buscaTermo("afsenkfneklgnklrngklnaerklg");

		assertThrows(IllegalArgumentException.class, () -> {
			busca.contaResultadosBusca("afsenkfneklgnklrngklnaerklg");
		});
	}

	@Test
	void buscarResultadoEspecifico() {

		buscaTermo("men");
		assertEquals("COM1: Homofobia em mensagens online de alunos de computacao do primeiro periodo.",
				busca.busca("men", 3));

		assertThrows(IllegalArgumentException.class, () -> {
			busca.busca("", 36);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			busca.busca("men", 36);
		});
	}

	private void buscaTermo(String termo) {
		busca.clearBuscas();
		pesquisaController.buscaTermoPesquisa(termo);
		pesquisadorController.buscaTermoPesquisadores(termo);
		problemaObjetivoController.buscaTermoProblemas(termo);
		problemaObjetivoController.buscaTermoObjetivos(termo.toLowerCase());
		atividadeController.buscaTermoAtividades(termo.toUpperCase());

	}
}
