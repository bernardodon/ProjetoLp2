package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entidades.Atividade;

class AtividadeTest{

	Atividade a1;
	Atividade a2;
	Atividade a3;
	Atividade a4;
	Atividade a5;
	Atividade a6;


@BeforeEach
	void testCriaAtividade(){

		a1 = new Atividade("Atividade mt doida", "ALTO", "Risco bem alto ne", "A1");
		a2 = new Atividade("Atividade suave", "MEDIO", "Risco DE BOA", "A2");
		a3 = new Atividade("Atividade chata", "BAIXO", "Risco bem baixo ne", "A3");
		a4 = new Atividade("Atividade mt doida", "ALTO", "Risco perigoso", "A4");
		a5 = new Atividade("Atividade mt doida", "ALTO", "Risco perigoso", "A4");
		a6 = new Atividade("Atividade chata", "BAIXO", "Risco bem baixo ne", "A6");
	}


	@Test
	void testTostring() {
		assertEquals("Atividade mt doida (ALTO - Risco bem alto ne)", a1.toString());
		assertEquals("Atividade suave (MEDIO - Risco DE BOA)", a2.toString());
		assertEquals("Atividade chata (BAIXO - Risco bem baixo ne)", a3.toString());

	}

	@Test
	void testHashCodeAtividade() {
		assertEquals(a4.hashCode(), a5.hashCode());
	
	}


	@Test
	void testEqualsAtividade() {
		a4.equals(a5);
		a3.equals(a6);
	}




}