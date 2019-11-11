package Testes;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.PesquisaController;
import Repositorios.PesquisasRepositorio;
import utils.Busca;
class PesquisaControllerTest {
	
	private PesquisasRepositorio pesquisasRepositorio = new PesquisasRepositorio();
	private Busca busca = new Busca();
	PesquisaController controle = new PesquisaController(pesquisasRepositorio, busca);

	@BeforeEach
	void testCadastraPesquisa() {
		assertEquals("COM1", controle.cadastraPesquisa(
				"Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia"));
		assertEquals("ELE1", controle.cadastraPesquisa(
				"Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.",
				"eleicao"));
		assertEquals("COM2", controle.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto.",
				"computacao, poo"));
		assertEquals("FER1",
				controle.cadastraPesquisa(
						"Aspectos da fermentacao do mosto cervejeiro por leveduras nao-Saccharomyces.",
						"fermentacao, cerveja"));
	}

	@Test
	void testCadastraPesquisaErroVazioNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controle.cadastraPesquisa("", "computacao, homofobia");
		});
		assertThrows(NullPointerException.class, () -> {
			controle.cadastraPesquisa(null, "computacao, homofobia");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controle.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto.", "");
		});
		assertThrows(NullPointerException.class, () -> {
			controle.cadastraPesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto.", null);
		});
	}

	@Test
	void testCadastraPesquisaErroCampo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controle.cadastraPesquisa(
					"Sistemas automatizados para acessibilidade de deficientes em postos de trabalho.",
					"Lorem ipsum dolor sit amet consectetur adipiscing elit Integer euismod leo in consequat efficitur. Proin commodo nisi eget ligula consequat imperdiet ac quis turpis In non fringilla orci Pellentesque pellentesque ipsum vel ipsum ultrices scelerisque Nulla facilisi Morbi ut tellus ante Suspendisse malesuada quis quam eu efficitur Ut mollis turpis magna sit amet auctor nunc pulvinar ultricies. Nam pharetra scelerisque magna ut feugiat.");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controle.cadastraPesquisa(
					"Sistemas automatizados para acessibilidade de deficientes em postos de trabalho.",
					"acessibilidade, , ,automatizacao");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controle.cadastraPesquisa(
					"Sistemas automatizados para acessibilidade de deficientes em postos de trabalho.", " , , ,");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controle.cadastraPesquisa(
					"Sistemas automatizados para acessibilidade de deficientes em postos de trabalho.",
					"acessibilidade, automatizacao, sistema,condicoes de trabalho, mundo melhor");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controle.cadastraPesquisa(
					"Sistemas automatizados para acessibilidade de deficientes em postos de trabalho.", "ho");
		});
	}

	@Test
	void testAtivaPesquisaErro() {
		assertThrows(IllegalArgumentException.class, () -> {
			controle.ativaPesquisa("ENE1");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controle.ativaPesquisa("COM1");
		});
	}

	@BeforeEach
	void testEncerraPesquisa() {
		controle.enceraPesquisa("COM2", "Pesquisa concluida");
		controle.enceraPesquisa("FER1");
	}

	@Test
	void testEncerraPesquisaErro() {
		assertThrows(IllegalArgumentException.class, () -> {
			controle.enceraPesquisa("ENE1");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controle.enceraPesquisa("FER1");
		});
	}

	@Test
	void testAlteraPesquisa() {
		assertThrows(IllegalArgumentException.class, () -> {
			controle.alteraPesquisa("ENE1", "DESCRICAO",
					"Modelagem do motor de inducao em estudos de estabilidade de tensao");
			;
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controle.alteraPesquisa("COM1", "CaMpO", "age of aquarius");
			;
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controle.alteraPesquisa("FER1", "CAMPO", "age of aquarius");
			;
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controle.alteraPesquisa("COMP1", "DESCRICAO", "");
			;
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controle.alteraPesquisa("COMP1", "CAMPO", "");
			;
		});
	}

	@Test
	void testExibePesquisa() {
		assertEquals(
				"COM1 - Homofobia em mensagens online de alunos de computacao do primeiro periodo. - computacao, homofobia",
				controle.exibePesquisa("COM1"));
	}

	@Test
	void testExibePesquisaErro() {
		assertThrows(IllegalArgumentException.class, () -> {
			controle.exibePesquisa("ENE1");
			;
		});
	}

	@Test
	void testEhAtiva() {
		assertEquals(true, controle.ehAtiva("COM1"));
		assertEquals(false, controle.ehAtiva("FER1"));
	}

	@Test
	void testEhAtivaErro() {
		assertThrows(IllegalArgumentException.class, () -> {
			controle.ehAtiva("ENE1");
			;
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controle.ehAtiva("");
			;
		});
		assertThrows(NullPointerException.class, () -> {
			controle.ehAtiva(null);
			;
		});
	}

}
