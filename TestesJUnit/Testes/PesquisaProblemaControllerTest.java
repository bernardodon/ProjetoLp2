package Testes;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.PesquisaController;
import Controllers.PesquisaProblemaController;
import Controllers.ProblemaObjetivoController;
import Repositorios.ObjetivosRepositorio;
import Repositorios.PesquisasRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Busca;

class PesquisaProblemaControllerTest {

	private PesquisasRepositorio pesquisasRepositorio = new PesquisasRepositorio();
	private ObjetivosRepositorio objetivosRepositorio = new ObjetivosRepositorio();
	private ProblemasRepositorio problemasRepositorio = new ProblemasRepositorio();

	Busca busca = new Busca();
	private PesquisaController pesquisaController = new PesquisaController(pesquisasRepositorio, busca);
	private ProblemaObjetivoController problemaObjetivoController = new ProblemaObjetivoController(objetivosRepositorio,
			problemasRepositorio, busca);
	private PesquisaProblemaController pesquisaProblemaController = new PesquisaProblemaController(pesquisasRepositorio,
			problemasRepositorio);

	@BeforeEach
	void testCadastraPesquisas() {
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
	void testCadastraProblemas() {
		assertEquals(problemaObjetivoController.cadastraProblema("descricao1", 3), "P1");
		assertEquals(problemaObjetivoController.cadastraProblema("descricao2", 5), "P2");
		assertEquals(problemaObjetivoController.cadastraProblema("descricao3", 1), "P3");
	}
	
	@Test
	void testAssociaPesquisaProblema() {
		assertEquals(true, pesquisaProblemaController.associaProblema("COM1", "P1"));
		assertEquals(false, pesquisaProblemaController.associaProblema("COM1", "P1"));
		assertEquals(true, pesquisaProblemaController.associaProblema("COM2", "P2"));
	}

	@Test
	void testAssociaPesquisaProblemaErro() {
		pesquisaController.enceraPesquisa("FER1");
		pesquisaProblemaController.associaProblema("COM1", "P1");
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaProblemaController.associaProblema("COM1", "P2");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaProblemaController.associaProblema("", "P2");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaProblemaController.associaProblema("COM1", "");
		});

		//assertThrows(IllegalArgumentException.class, () -> {
		//	pesquisaProblemaController.associaProblema("LED7", "P2");
		//});
		
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaProblemaController.associaProblema("FER1", "P2");
		});
	}
	
	@Test
	void testDesassociaProblemaPesquisa() {
		pesquisaProblemaController.associaProblema("COM1", "P1");
		assertEquals(true, pesquisaProblemaController.desassociaProblema("COM1", "P1"));
		assertEquals(false, pesquisaProblemaController.desassociaProblema("COM1", "P1"));
	}

	@Test
	void testDesassociaProblemaPesquisaErro() {
		pesquisaController.enceraPesquisa("FER1");

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaProblemaController.desassociaProblema("COM1", "");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaProblemaController.desassociaProblema("", "P2");
		});

		// assertThrows(IllegalArgumentException.class, () -> {
		// pesquisaProblemaController.desassociaProblema("LED7", "02");
		// });

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaProblemaController.desassociaProblema("FER1", "P2");
		});
	}


}
