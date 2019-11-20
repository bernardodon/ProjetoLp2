package Testes;

import static org.junit.Assert.assertEquals;


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

import utils.Persistencia;

class PersistenciaTest {
	
	Persistencia persistencia = new Persistencia();	
	
	private PesquisasRepositorio pesquisasRepositorio = new PesquisasRepositorio();
	private PesquisadoresRepositorio pesquisadoresRepositorio = new PesquisadoresRepositorio();
	private AtividadesRepositorio atividadeRepositorio = new AtividadesRepositorio();
	private ObjetivosRepositorio objetivosRepositorio = new ObjetivosRepositorio();
	private ProblemasRepositorio problemasRepositorio = new ProblemasRepositorio();

	private PesquisaController pesquisaController = new PesquisaController(pesquisasRepositorio);
	private PesquisadorController pesquisadorController = new PesquisadorController(pesquisadoresRepositorio);
	private AtividadeController atividadeController = new AtividadeController(atividadeRepositorio);
	private ProblemaObjetivoController problemaObjetivoController = new ProblemaObjetivoController(objetivosRepositorio,
			problemasRepositorio);

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
	void testSalvarPesquisas() throws Exception{
		persistencia.salvarPesquisas(pesquisasRepositorio, "ArquivoPesquisa");
	}


	@Test 
	void testCarregarPesquisas() throws Exception{
		persistencia.carregarPesquisas();
	}

	@Test
	void testSalvarPesquisadores() throws Exception{
		persistencia.salvarpesquisadores(pesquisadoresRepositorio, "ArquivoPesquisadores");
	}

	@Test 
	void testCarregarPesquisadores() throws Exception{
		persistencia.carregarpesquisadores();
	}
	
	@Test
	void testSalvarProblemas() throws Exception {
		persistencia.salvarProblemas(problemasRepositorio, "ArquivoProblemas");
	}
	
	@Test 
	void testCarregarProblemas() throws Exception{
		persistencia.carregarProblemas();
	}
	
	@Test
	void testSalvarObjetivos() throws Exception{
		persistencia.salvarObjetivos(objetivosRepositorio, "ArquivoObjetivos");
		
	}
	
	@Test
	void testCarregarObjetivos() throws Exception{
		persistencia.carregarObjetivos();
	}
	
	@Test 
	void testSalvarAtividades() throws Exception{
		persistencia.salvarAtividades(atividadeRepositorio, "ArquivoAtividades");
	}
	
	@Test
	void testCarregarAtividades() throws Exception{
		persistencia.carregarAtividades();
	}
}
