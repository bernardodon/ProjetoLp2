package Testes;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programa.Objetivo;

class ObjetivoTeste {
	
	Objetivo o1;
	Objetivo o2;
	Objetivo o3;
	Objetivo o4;
	Objetivo o5;
	
	@BeforeEach
	void testCriaObjetivo() {
//		o1 = new Objetivo("O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo", 3, "P1");
//		o2 = new Objetivo("A problematica do aprendizado dos conceitos de programacao orientada a objeto", 5, "P2");
//		o3 = new Objetivo("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P3");
//		
//		o4 = new Objetivo("A dificuldade da predicao do sistema eleitoral brasileiro", 1, "P3");
//		o5 = new Objetivo("A problematica do aprendizado dos conceitos de programacao orientada a objeto", 5, "P2");
	}
	
//	@Test
//	void testObjetivoToString() {
//		assertEquals("P1 - O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo - 3", p1.toString());
//		assertEquals("P2 - A problematica do aprendizado dos conceitos de programacao orientada a objeto - 5", p2.toString());
//		assertEquals("P3 - A dificuldade da predicao do sistema eleitoral brasileiro - 1", p3.toString());
//	}
	
	@Test
	void testHashCodeObjetivo() {
		assertEquals(o3.hashCode(), o4.hashCode());
		assertEquals(o2.hashCode(), o5.hashCode());
	}
	
	@Test
	void testEqualsObjetivo() {
		o3.equals(o4);
		o2.equals(o5);
	}

}
