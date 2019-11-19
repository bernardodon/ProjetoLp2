package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.AtividadeController;
import Repositorios.AtividadesRepositorio;

class AtividadeControllerTest {

	AtividadesRepositorio atividadesRepositorio;
	AtividadeController controller;

	@BeforeEach
	void testAtividadeController() {
		atividadesRepositorio = new AtividadesRepositorio();
		controller = new AtividadeController(atividadesRepositorio);
	}

	@Test
	void testCadastraAtividade() {
		controller.cadastraAtividade("Atividade 1", "ALTO", "Risco alto");
		controller.cadastraAtividade("Atividade 2", "MEDIO", "Risco medio");
		controller.cadastraAtividade("Atividade 3", "BAIXO", "Risco baixo");
		controller.cadastraAtividade("Atividade 4", "ALTO", "Risco alto de novo");
		controller.cadastraAtividade("Atividade 5", "MEDIO", "Risco medio de novo");
	}

	@Test
	void testCadastraAtividadeNivelRiscoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraAtividade("Atividade 5", "INVALIDO", "Risco medio de novo");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraAtividade("Atividade 5", "OUTRO", "Risco medio de novo");
		});
	}

	@Test
	void testCadastraAtividadeNivelRiscoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraAtividade("Atividade 1", "", "Risco alto");
		});
		assertThrows(NullPointerException.class, () -> {
			controller.cadastraAtividade("Atividade 2", null, "Risco medio");
		});
	}

	@Test
	void testCadastraAtividadeDescricaoRiscoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraAtividade("Atividade 1", "ALTO", "");
		});
		assertThrows(NullPointerException.class, () -> {
			controller.cadastraAtividade("Atividade 2", "MEDIO", null);
		});
	}

	@Test
	void testCadastraAtividadeDescricaoAtvdVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraAtividade("", "ALTO", "Risco alto");
		});
		assertThrows(NullPointerException.class, () -> {
			controller.cadastraAtividade(null, "MEDIO", "Risco medio");
		});
	}

	@Test
	void testApagaAtividade() {
		testCadastraAtividade();
		controller.apagaAtividade("A4");
		controller.apagaAtividade("A5");
	}

	@Test
	void testApagaAtividadeCodigoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.apagaAtividade("");
		});
		assertThrows(NullPointerException.class, () -> {
			controller.apagaAtividade(null);
		});
	}

	@Test
	void testCadastraItem() {
		testCadastraAtividade();
		testApagaAtividade();
		controller.cadastraItem("A1", "Item 1 para atividade 1");
		controller.cadastraItem("A1", "Item 2 para atividade 1");
		controller.cadastraItem("A1", "Item 3 para atividade 1");
		controller.cadastraItem("A2", "Item 4 para atividade 2");
	}

	@Test
	void testCadastraItemCodigoAtvdVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraItem("", "Item 3 para atividade 3");
		});
		assertThrows(NullPointerException.class, () -> {
			controller.cadastraItem(null, "Item 3 para atividade 3");
		});
	}

	@Test
	void testCadastraItemItemVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraItem("A1", "");
		});
		assertThrows(NullPointerException.class, () -> {
			controller.cadastraItem("A1", null);
		});
	}

	@Test
	void testExibeAtividade() {
		testCadastraAtividade();
		assertEquals("Atividade 1 (ALTO - Risco alto)", controller.exibeAtividade("A1"));
		assertEquals("Atividade 2 (MEDIO - Risco medio)", controller.exibeAtividade("A2"));
		assertEquals("Atividade 3 (BAIXO - Risco baixo)", controller.exibeAtividade("A3"));
	}

	@Test
	void testExibeAtividadeInexistente() {
		testCadastraAtividade();
		testApagaAtividade();
		assertThrows(IllegalArgumentException.class, () -> {
			controller.exibeAtividade("A5");
		});
	}

	@Test
	void testContaItensPendentes() {
		testCadastraItem();
		assertEquals(3, controller.contaItensPendentes("A1"));
		assertEquals(1, controller.contaItensPendentes("A2"));
	}

	@Test
	void testContaItensPendentesCodigoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.contaItensPendentes("");
		});
		assertThrows(NullPointerException.class, () -> {
			controller.contaItensPendentes(null);
		});
	}

	@Test
	void testContaItensRealizados() {
		testCadastraItem();
		assertEquals(0, controller.contaItensRealizados("A1"));
		assertEquals(0, controller.contaItensRealizados("A2"));
	}

	@Test
	void testContaItensRealizadosCodigoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.contaItensRealizados("");
		});
		assertThrows(NullPointerException.class, () -> {
			controller.contaItensRealizados(null);
		});
	}

	@Test
	void testExecutaAtividade() {
		testCadastraItem();
		controller.executaAtividade("A1", 1, 25);
		controller.executaAtividade("A1", 2, 15);
		controller.executaAtividade("A2", 1, 10);
	}

	@Test
	void testExecutaAtividadeCodigoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> controller.executaAtividade("", 1, 25));
		assertThrows(NullPointerException.class, () -> controller.executaAtividade(null, 1, 25));
	}

	@Test
	void testExecutaAtividadeItemInvalido() {
		assertThrows(IllegalArgumentException.class, () -> controller.executaAtividade("A1", -5, 25));
		assertThrows(IllegalArgumentException.class, () -> controller.executaAtividade("A1", 0, 25));
	}

	@Test
	void testExecutaAtividadeDuracaoInvalida() {
		assertThrows(IllegalArgumentException.class, () -> controller.executaAtividade("A1", 1, -10));
		assertThrows(IllegalArgumentException.class, () -> controller.executaAtividade("A1", 1, 0));
	}

	@Test
	void testCadastraResultado() {
		testExecutaAtividade();
		assertEquals(1, controller.cadastraResultado("A1", "resultado 1"));
		assertEquals(2, controller.cadastraResultado("A1", "resultado 2"));
		assertEquals(3, controller.cadastraResultado("A1", "resultado 3"));
		assertEquals(1, controller.cadastraResultado("A2", "resultado 1"));
		assertEquals(2, controller.cadastraResultado("A2", "resultado 2"));
		assertEquals(3, controller.cadastraResultado("A2", "resultado 3"));
		assertEquals(4, controller.cadastraResultado("A2", "resultado 4"));
	}

	@Test
	void testCadastraResultadoCodigoAtividadeVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> controller.cadastraResultado("", "resultado 1"));
		assertThrows(NullPointerException.class, () -> controller.cadastraResultado(null, "resultado 1"));
	}

	@Test
	void testCadastraResultadoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> controller.cadastraResultado("A1", ""));
		assertThrows(NullPointerException.class, () -> controller.cadastraResultado("A1", null));
	}

	@Test
	void testRemoveResultado() {
		testCadastraResultado();
		assertEquals(true, controller.removeResultado("A1", 3));
		assertEquals(true, controller.removeResultado("A2", 1));
	}

	@Test
	void testRemoveResultadoCodigoAtividadeVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> controller.removeResultado("", 2));
		assertThrows(NullPointerException.class, () -> controller.removeResultado(null, 1));
	}

	@Test
	void testRemoveResultadoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> controller.removeResultado("A1", -5));
		assertThrows(IllegalArgumentException.class, () -> controller.removeResultado("A1", 0));
	}

	@Test
	void testListaResultados() {
		testRemoveResultado();
		assertEquals("resultado 1 | resultado 2", controller.listaResultados("A1"));
		assertEquals("resultado 2 | resultado 3 | resultado 4", controller.listaResultados("A2"));
	}

	@Test
	void testListaResultadosCodigoAtividadeVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> controller.listaResultados(""));
		assertThrows(NullPointerException.class, () -> controller.listaResultados(null));
	}

	@Test
	void testGetDuracao() {
		testListaResultados();
		assertEquals(40, controller.getDuracao("A1"));
		assertEquals(10, controller.getDuracao("A2"));
	}

	@Test
	void testGetDuracaoCodigoAtividadeVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> controller.getDuracao(""));
		assertThrows(NullPointerException.class, () -> controller.getDuracao(null));
	}
	

	@Test
	void testDefineProximaAtividade() {
		testCadastraAtividade();
		controller.defineProximaAtividade("A1", "A2");
		controller.defineProximaAtividade("A2", "A5");
	}
	
	@Test
	void testDefineProximaAtividadeInvalido() {
		testCadastraAtividade();
		testDefineProximaAtividade();
		assertThrows(IllegalArgumentException.class,  () -> {
			controller.defineProximaAtividade("A1", "A4");
		});
		assertThrows(IllegalArgumentException.class,  () -> {
			controller.defineProximaAtividade("", "A2");
		});
		assertThrows(IllegalArgumentException.class,  () -> {
			controller.defineProximaAtividade("A1", "");
		});
		assertThrows(IllegalArgumentException.class,  () -> {
			controller.defineProximaAtividade("", "");
		});
		assertThrows(IllegalArgumentException.class,  () -> {
			controller.defineProximaAtividade("A1", "A50");
		});
		assertThrows(IllegalArgumentException.class,  () -> {
			controller.defineProximaAtividade("A5", "A1");
		});
	}
	
	@Test
	void tiraProximaAtividade() {
		testDefineProximaAtividade();
		controller.tiraProximaAtividade("A2");
		controller.tiraProximaAtividade("A2");
		assertThrows(IllegalArgumentException.class,  () -> {
			controller.tiraProximaAtividade("");
		});
	}
	
	@Test
	void contaProximos() {
		testDefineProximaAtividade();
		assertEquals(1, controller.contaProximos("A2"));
		assertEquals(2, controller.contaProximos("A1"));
		assertEquals(0, controller.contaProximos("A3"));
		assertThrows(IllegalArgumentException.class, () -> {
			controller.contaProximos("");
		});
	}
	
	@Test
	void testPegaProximo() {
		testDefineProximaAtividade();
		assertEquals("A5", controller.pegaProximo("A1", 2));
		assertThrows(IllegalArgumentException.class, () -> {
			controller.pegaProximo("A1", 10);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controller.pegaProximo("", 10);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controller.pegaProximo("A1", 0);
		});
	}
	
	@Test
	void testPegaMaiorRiscoAtividade() {
		testDefineProximaAtividade();
		assertEquals("A5", controller.pegaMaiorRiscoAtividades("A1"));
		assertThrows(IllegalArgumentException.class, () -> {
			controller.pegaMaiorRiscoAtividades("A3");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controller.pegaMaiorRiscoAtividades("");
		});
	}
	
}