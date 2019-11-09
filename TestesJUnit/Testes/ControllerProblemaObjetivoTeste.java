package Testes;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.ProblemaObjetivoController;
import Repositorios.ObjetivosRepositorio;
import Repositorios.ProblemasRepositorio;
import utils.Busca;

class ControllerProblemaObjetivoTeste {
	ProblemasRepositorio problemasRepositorio;
	ObjetivosRepositorio objetivosRepositorio;
	Busca busca;
	ProblemaObjetivoController cpo;
	
	@BeforeEach
	void inicia() {
		problemasRepositorio = new ProblemasRepositorio();
		objetivosRepositorio = new ObjetivosRepositorio();
		busca = new Busca();
		cpo = new ProblemaObjetivoController(objetivosRepositorio, problemasRepositorio, busca);
	}
	
	@BeforeEach
	void testCadastraProblema() {
		assertEquals(cpo.cadastraProblema("descricao1", 3), "P1");
		assertEquals(cpo.cadastraProblema("descricao2", 5), "P2");
		assertEquals(cpo.cadastraProblema("descricao3", 1), "P3");
	}
	
	@Test
	void testCadastraProblemaDescricaoVaziaOuNula() {
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraProblema("", 3));
		assertThrows(NullPointerException.class, () -> cpo.cadastraProblema(null, 3));
	}
	
	@Test
	void testCadastraProblemaViabilidadeInvalida() {
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraProblema("descricao1", -3));
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraProblema("descricao2", 0));
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraProblema("descricao3", 111));
	}
	
	@BeforeEach
	void testCadastraObjetivos() {
		assertEquals(cpo.cadastraObjetivo("GERAL", "descricao1", 4, 2), "O1");
		assertEquals(cpo.cadastraObjetivo("GERAL", "descricao2", 5, 4), "O2");
		assertEquals(cpo.cadastraObjetivo("ESPECIFICO", "descricao3", 2, 5), "O3");
		assertEquals(cpo.cadastraObjetivo("ESPECIFICO", "descricao4", 4, 4), "O4");
	}
	
	@Test
	void testCadastraObjetivoTipoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraObjetivo("", "descricao1", 4, 2));
		assertThrows(NullPointerException.class, () -> cpo.cadastraObjetivo(null, "descricao2", 5, 4));
	}
	
	@Test
	void testCadastraObjetivoDescricaoVaziaOuNula() {
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraObjetivo("GERAL", "", 4, 2));
		assertThrows(NullPointerException.class, () -> cpo.cadastraObjetivo("ESPECIFICO", null, 5, 4));
	}
	
	@Test
	void testCadastraObjetivoTipoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraObjetivo("outra", "descricao1", 4, 2));
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraObjetivo("0", "descricao2", 4, 2));
	}
	
	@Test
	void testCadastraObjetivoAderenciaInvalida() {
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraObjetivo("GERAL", "descricao1", 0, 2));
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraObjetivo("GERAL", "descricao2", 9, 2));
	}
	
	@Test
	void testCadastraObjetivoViabilidadeInvalida() {
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraObjetivo("GERAL", "descricao1", 4, 0));
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraObjetivo("GERAL", "descricao2", 4, -22));
		assertThrows(IllegalArgumentException.class, () -> cpo.cadastraObjetivo("GERAL", "descricao2", 4, 111));
	}
	
	@BeforeEach
	void testApagarProblemaInvalidoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> cpo.apagarProblema(""));
		assertThrows(IllegalArgumentException.class, () -> cpo.apagarProblema("P33"));
		assertThrows(NullPointerException.class, () -> cpo.apagarProblema(null));
	}
	
	@Test
	void testApagarObjetivoInvalidoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> cpo.apagarObjetivo(""));
		assertThrows(IllegalArgumentException.class, () -> cpo.apagarObjetivo("O33"));
		assertThrows(NullPointerException.class, () -> cpo.apagarObjetivo(null));
	}
	
	@Test
	void testExibirProblema() {
		assertEquals(cpo.exibeProblema("P1"), "P1 - descricao1 - 3");
		assertEquals(cpo.exibeProblema("P2"), "P2 - descricao2 - 5");
	}
	
	@Test
	void testExibirProblemaInvalido() {
		cpo.apagarProblema("P3");
		assertThrows(IllegalArgumentException.class, () -> cpo.exibeProblema("P3"));
		assertThrows(IllegalArgumentException.class, () -> cpo.exibeProblema("P33"));
	}
	
	@Test
	void testExibirObjetivo() {
		assertEquals(cpo.exibeObjetivo("O1"), "O1 - GERAL - descricao1 - 6");
		assertEquals(cpo.exibeObjetivo("O2"), "O2 - GERAL - descricao2 - 9");
	}
	
	@Test
	void testExibirObjetivoInvalido() {
		cpo.apagarObjetivo("O3");
		assertThrows(IllegalArgumentException.class, () -> cpo.exibeObjetivo("O3"));
		assertThrows(IllegalArgumentException.class, () -> cpo.exibeObjetivo("O333"));
	}
}
