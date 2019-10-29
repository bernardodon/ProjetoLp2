package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programa.Pesquisador;

class PesquisadorTest {

	Pesquisador p1;
	Pesquisador p2;
	Pesquisador p3;
	
	@Test
	void testHashCode() {
		assertEquals(true, p1.hashCode() == p2.hashCode());
	}

	@BeforeEach
	void testPesquisador() {
		p1 = new Pesquisador("Joao", "Pesquisador", "joao@gmail.com", "www.foto.com.br", "Analisador de pureza");
		p2 = new Pesquisador("Joao", "Pesquisador", "joao@gmail.com", "www.foto.com.br", "Analisador de pureza");
		p3 = new Pesquisador("Maria", "Doutara em Analise", "maria@gmail.com", "mariafoto.com.br", "Determinar estratégia de pesquisa");
	}

	@Test
	void testDesativa() {
		p3.desativa();
		assertThrows(IllegalArgumentException.class, () -> {
			p3.desativa();
		});
	}

	@Test
	void testEhAtiva() {
		testDesativa();
		assertEquals(true, p1.getAtivado());
		assertEquals(false, p3.getAtivado());
	}


	@Test
	void testAtiva() {
		testDesativa();
		p3.ativa();
		assertThrows(IllegalArgumentException.class, () -> {
			p1.ativa();
		});
		assertEquals(true, p3.getAtivado());
	}
	
	@Test
	void testToString() {
		assertEquals("Joao (Analisador de pureza) - Pesquisador - joao@gmail.com - www.foto.com.br", p1.toString());
		assertEquals("Maria (Determinar estratégia de pesquisa) - Doutara em Analise - maria@gmail.com - mariafoto.com.br", p3.toString());
	}

	@Test
	void testEqualsObject() {
		assertEquals(true, p1.equals(p2));
		assertEquals(false, p1.equals(p3));
	}

}
