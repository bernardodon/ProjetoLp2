package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programa.AtividadeController;


class AtividadeControllerTest{
	 AtividadeController controller;

	 @BeforeEach
	void testAtividadeController() {
		controller = new AtividadeController();


	 @Test
	 void testCadastraAtividade() {

	 	controller.CadastraAtividade("Atividade mt doida", "ALTO", "Risco bem alto ne", "A1");
	 	controller.CadastraAtividade("Atividade suave", "MEDIO", "Risco DE BOA", "A2");

	}

	@Test

	void testCadastraItem(){

		controller.cadastraItem("A1","pular corda");
		controller.cadastraItem("A2","nadar");

	}



	@Test
	void testExibeAtividades() {
		assertEquals("Atividade mt doida (ALTO - Risco bem alto ne) | PENDENTE - ITEM1 |", controller.exibeAtividade("A1"));


	}









}