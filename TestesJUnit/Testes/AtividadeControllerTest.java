package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

		controller.cadastraAtividade("Atividade mt doida", "ALTO", "Risco bem alto ne");
		controller.cadastraAtividade("Atividade suave", "MEDIO", "Risco DE BOA");

	}

	@Test

	void testCadastraItem() {
		testCadastraAtividade();
		controller.cadastraItem("A1", "pular corda");
		controller.cadastraItem("A2", "nadar");

	}

	@Test
	void testExibeAtividades() {
		testCadastraAtividade();

		assertEquals("Atividade mt doida (ALTO - Risco bem alto ne)", controller.exibeAtividade("A1"));

	}

}