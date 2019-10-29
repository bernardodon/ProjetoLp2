package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programa.Problema;
class ProblemaTeste {
	
	Problema p1;
	Problema p2;
	Problema p3;
	Problema p4;
	Problema p5;
	
	@BeforeEach
	void testCriaProblema() {
		p1 = new Problema("O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", 3, "P1");
		p2 = new Problema("A problematica do aprendizado dos conceitos de programacao orientada a objeto", 5, "P2");
		p3 = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P3");
		
		p4 = new Problema("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P3");
		p5 = new Problema("A problematica do aprendizado dos conceitos de programacao orientada a objeto", 5, "P2");
	}
	
	@Test
	void testProblemaToString() {
		assertEquals("P1 - O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo - 3", p1.toString());
		assertEquals("P2 - A problematica do aprendizado dos conceitos de programacao orientada a objeto - 5", p2.toString());
		assertEquals("P3 - A dificuldade da predicao do sistema eleitoral brasileiro - 1", p3.toString());
	}
	
	@Test
	void testHashCodeProblema() {
		assertEquals(p3.hashCode(), p4.hashCode());
		assertEquals(p2.hashCode(), p5.hashCode());
	}
	
	@Test
	void testEqualsProblema() {
		p3.equals(p4);
		p2.equals(p5);
	}
}
