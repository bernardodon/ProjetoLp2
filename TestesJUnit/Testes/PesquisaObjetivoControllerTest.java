package Testes;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Controllers.PesquisaController;
import Controllers.PesquisaObjetivoController;
import Controllers.ProblemaObjetivoController;
import Repositorios.ObjetivosRepositorio;
import Repositorios.PesquisasRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Busca;

class PesquisaObjetivoControllerTest {

	private PesquisasRepositorio pesquisasRepositorio = new PesquisasRepositorio();
	private ObjetivosRepositorio objetivosRepositorio = new ObjetivosRepositorio();
	private ProblemasRepositorio problemasRepositorio = new ProblemasRepositorio();

	Busca busca = new Busca();
	private PesquisaController pesquisaController = new PesquisaController(pesquisasRepositorio, busca);
	private ProblemaObjetivoController problemaObjetivoController = new ProblemaObjetivoController(objetivosRepositorio,
			problemasRepositorio, busca);
	private PesquisaObjetivoController pesquisaObjetivoController = new PesquisaObjetivoController(pesquisasRepositorio,
			objetivosRepositorio);

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
	void testCadastraObjetivos() {
		assertEquals(problemaObjetivoController.cadastraObjetivo("GERAL", "descricao1", 4, 2), "O1");
		assertEquals(problemaObjetivoController.cadastraObjetivo("GERAL", "descricao2", 5, 4), "O2");
		assertEquals(problemaObjetivoController.cadastraObjetivo("ESPECIFICO", "descricao3", 2, 5), "O3");
		assertEquals(problemaObjetivoController.cadastraObjetivo("ESPECIFICO", "descricao4", 4, 4), "O4");
	}

	@Test
	void testAssociaPesquisaObjetivo() {
		assertEquals(true, pesquisaObjetivoController.associaObjetivo("COM1", "O1"));
		assertEquals(false, pesquisaObjetivoController.associaObjetivo("COM1", "O1"));
	}

	@Test
	void testAssociaPesquisaProblemaErro() {
		pesquisaController.enceraPesquisa("FER1");
		pesquisaObjetivoController.associaObjetivo("COM1", "O1");

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaObjetivoController.associaObjetivo("COM2", "01");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaObjetivoController.associaObjetivo("COM1", "");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaObjetivoController.associaObjetivo("", "O2");
		});

		// assertThrows(IllegalArgumentException.class, () -> {
		// pesquisaObjetivoController.associaObjetivo("LED7", "02");
		// });

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaObjetivoController.associaObjetivo("FER1", "O2");
		});
	}

	@Test
	void testDesassociaObjetivosPesquisa() {
		pesquisaObjetivoController.associaObjetivo("COM1", "O1");
		assertEquals(true, pesquisaObjetivoController.desassociaObjetivo("COM1", "O1"));
		assertEquals(false, pesquisaObjetivoController.desassociaObjetivo("COM1", "O1"));
	}

	@Test
	void testDesassociaObjetivosPesquisaErro() {
		pesquisaController.enceraPesquisa("FER1");

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaObjetivoController.desassociaObjetivo("COM2", "01");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaObjetivoController.desassociaObjetivo("COM1", "");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaObjetivoController.desassociaObjetivo("", "O2");
		});

		// assertThrows(IllegalArgumentException.class, () -> {
		// pesquisaObjetivoController.desassociaObjetivo("LED7", "02");
		// });

		assertThrows(IllegalArgumentException.class, () -> {
			pesquisaObjetivoController.desassociaObjetivo("FER1", "O2");
		});
	}

	
	

}
