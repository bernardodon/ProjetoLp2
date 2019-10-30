package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programa.Pesquisa;
class PesquisaTest {
	
	Pesquisa p1;
	Pesquisa p2;
	Pesquisa p3;
	Pesquisa p4;
	Pesquisa p5;
	
	@BeforeEach
	void testCriaPesquisa() {
		p1 = new Pesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", "COM1");
		p2 = new Pesquisa("Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.", "eleicao", "ELE1");
		p3 =  new Pesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto.", "computacao, poo", "COM2");
		p4 = new Pesquisa("Alienacao Parental e o Sistema de Justica Brasileiro.", "psicologia, sistema juridico, alienacao parental, brasil", "PSI1");
		p5 = new Pesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", "COM3");
	}
	
	@Test
	void tesToString() {
		assertEquals("COM1 - Homofobia em mensagens online de alunos de computacao do primeiro periodo. - computacao, homofobia",p1.toString());
		assertEquals("ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao", p2.toString());
	}
	
	@Test
	void testEquals() {
		assertEquals(false, p5.equals(p1));
		assertEquals(false, p1.equals(p2));
	}
}
