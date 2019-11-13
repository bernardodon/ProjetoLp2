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
}