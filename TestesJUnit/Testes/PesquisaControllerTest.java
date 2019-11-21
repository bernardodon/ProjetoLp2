package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.PesquisaController;
import Entidades.Atividade;
import Entidades.Item;
import Repositorios.AtividadesRepositorio;
import Repositorios.PesquisasRepositorio;
class PesquisaControllerTest{
	
	PesquisasRepositorio pesquisasRepositorio = new PesquisasRepositorio();
	PesquisaController controle = new PesquisaController(pesquisasRepositorio);
	AtividadesRepositorio atividadesRepositorio = new AtividadesRepositorio();
	
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
		controle.encerraPesquisa("COM2", "Pesquisa concluida");
		controle.encerraPesquisa("FER1");
	}

	@Test
	void testEncerraPesquisaErro() {
		assertThrows(IllegalArgumentException.class, () -> {
			controle.encerraPesquisa("ENE1");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controle.encerraPesquisa("FER1");
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
	
	@Test
	void testConfiguraAtividadesRepositorio() {
		atividadesRepositorio.put("A1", new Atividade("Descricao 1", "BAIXO", "Risco alto", "A1"));
		atividadesRepositorio.put("A2", new Atividade("Descricao 2", "MEDIO", "Risco medio", "A2"));
		atividadesRepositorio.put("A3", new Atividade("Descricao 3", "ALTO", "Risco baixo", "A3"));
		
		Atividade A1 = atividadesRepositorio.getAtividade("A1");
		Atividade A2 = atividadesRepositorio.getAtividade("A2");
		Atividade A3 = atividadesRepositorio.getAtividade("A3");
		
		A1.adicionaItem(new Item("Item 1 para atividade 1"));
		A1.adicionaItem(new Item("Item 2 para atividade 1"));
		A1.adicionaItem(new Item("Item 3 para atividade 1"));
		
		A2.adicionaItem(new Item("Item 1 para atividade 2"));
		A2.adicionaItem(new Item("Item 2 para atividade 2"));
		A2.adicionaItem(new Item("Item 3 para atividade 2"));
		A2.adicionaItem(new Item("Item 4 para atividade 2"));
		
		A3.adicionaItem(new Item("Item 1 para atividade 3"));
		A3.adicionaItem(new Item("Item 2 para atividade 3"));
		A3.adicionaItem(new Item("Item 3 para atividade 3"));
		
		A1.getItens().get(0).executarItem();
		A2.getItens().get(0).executarItem();
		A2.getItens().get(1).executarItem();
		A2.getItens().get(2).executarItem();
	}
	
	@Test
	void testAssociaAtividade() {
		testConfiguraAtividadesRepositorio();
		assertEquals(true, controle.associaAtividade("COM1", "A1", atividadesRepositorio));
		assertEquals(true, controle.associaAtividade("COM1", "A2", atividadesRepositorio));
		assertEquals(true, controle.associaAtividade("COM1", "A3", atividadesRepositorio));
	}
	
	@Test
	void testConfiguraEstrategia() {
		testAssociaAtividade();
		controle.configuraEstrategia("MENOS_PENDENCIAS");
		controle.configuraEstrategia("MAIOR_RISCO");
		controle.configuraEstrategia("MAIOR_DURACAO");
	}

	@Test
	void testConfiguraEstrategiaInvalida() {
		testAssociaAtividade();
		assertThrows(IllegalArgumentException.class, () -> controle.configuraEstrategia("invalido"));
		assertThrows(IllegalArgumentException.class, () -> controle.configuraEstrategia("nao_possivel"));
		assertThrows(IllegalArgumentException.class, () -> controle.configuraEstrategia("MENO_PEMDENCI"));
	}
	
	@Test
	void testConfiguraEstrategiaVaziaOuNula() {
		testAssociaAtividade();
		assertThrows(IllegalArgumentException.class, () -> controle.configuraEstrategia(""));
		assertThrows(NullPointerException.class, () -> controle.configuraEstrategia(null));
	}
	
	@Test
	void testProximaAtividadeEstrategiaMaiorDuracao() {
		testAssociaAtividade();
		assertEquals("A1", controle.proximaAtividade("COM1"));
	}
	
	@Test
	void testProximaAtividadeEstrategiaMenosPendencias() {
		testAssociaAtividade();
		controle.configuraEstrategia("MENOS_PENDENCIAS");
		assertEquals("A2" ,controle.proximaAtividade("COM1"));
	}
	
	@Test
	void testProximaAtividadeEstrategiaMaiorRisco() {
		testAssociaAtividade();
		controle.configuraEstrategia("MAIOR_RISCO");
		assertEquals("A3" ,controle.proximaAtividade("COM1"));
	}
}
