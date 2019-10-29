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
	Objetivo o6;
	Objetivo o7;
	
	@BeforeEach
	void testCriaObjetivo() {
		o1 = new Objetivo("GERAL", "Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao.", 4, 2, "O1");
		o2 = new Objetivo("ESPECIFICO", "Realizar rodas de conversa entre alunos do curso, incluindo especialmente aqueles que emitem mensagens homofobicas e alunos ou pessoas homoafetivas.", 5, 4, "O2");
		o3 = new Objetivo("ESPECIFICO", "Detectar, atraves de bot eletronico, a presenca de mensagens eletronicas com conteudo homofobico.", 2, 5, "O3");
		o4 = new Objetivo("GERAL", "Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", 4, 4, "O4");
		o5 = new Objetivo("GERAL", "Objetivo criado para ser deletado", 1, 5, "O5");
		
		o6 = new Objetivo("GERAL", "Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2.", 4, 4, "O4");
		o7 = new Objetivo("GERAL", "Objetivo criado para ser deletado", 1, 5, "O5");
	}
	
	@Test
	void testObjetivoToString() {
		assertEquals("O1 - GERAL - Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao. - 6", o1.toString());
		assertEquals("O2 - ESPECIFICO - Realizar rodas de conversa entre alunos do curso, incluindo especialmente aqueles que emitem mensagens homofobicas e alunos ou pessoas homoafetivas. - 9", o2.toString());
		assertEquals("O3 - ESPECIFICO - Detectar, atraves de bot eletronico, a presenca de mensagens eletronicas com conteudo homofobico. - 7", o3.toString());
		assertEquals("O4 - GERAL - Aumentar a porcentagem de pessoas que entregam os laboratorios na disciplina de LP2. - 8", o4.toString());
		assertEquals("O5 - GERAL - Objetivo criado para ser deletado - 6", o5.toString());
		
	}
	
	@Test
	void testHashCodeObjetivo() {
		assertEquals(o4.hashCode(), o6.hashCode());
		assertEquals(o5.hashCode(), o7.hashCode());
	}
	
	@Test
	void testEqualsObjetivo() {
		o4.equals(o6);
		o5.equals(o7);
	}

}
