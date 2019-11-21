package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entidades.Atividade;
import Entidades.Item;
import Entidades.Pesquisa;
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
	
	@Test
	void testAdicionaAtividade() {
		p1.adicionaAtividade(new Atividade("Descricao 1", "BAIXO", "Risco baixo", "A1"));
		p1.adicionaAtividade(new Atividade("Descricao 2", "MEDIO", "Risco medio", "A2"));
		p1.adicionaAtividade(new Atividade("Descricao 3", "ALTO", "Risco alto", "A3"));
	}
	
	@Test
	void testConfiguraAtividadesRepositorio() {
		testAdicionaAtividade();
		
		Atividade A1 = p1.getAtividades().get(0);
		Atividade A2 = p1.getAtividades().get(1);
		Atividade A3 = p1.getAtividades().get(2);
		
		A1.adicionaItem(new Item("Item 1 para atividade 1"));
		A1.adicionaItem(new Item("Item 2 para atividade 1"));
		A1.adicionaItem(new Item("Item 3 para atividade 1"));
		
		A2.adicionaItem(new Item("Item 1 para atividade 2"));
		A2.adicionaItem(new Item("Item 2 para atividade 2"));
		A2.adicionaItem(new Item("Item 3 para atividade 2"));
		A2.adicionaItem(new Item("Item 4 para atividade 2"));
		
		A3.adicionaItem(new Item("Item 1 para atividade 3"));
		A3.adicionaItem(new Item("Item 2 para atividade 3"));
		A3.adicionaItem(new Item("Item 3 para atividade 3"));
		
		A1.getItens().get(0).executarItem();
		A2.getItens().get(0).executarItem();
		A2.getItens().get(1).executarItem();
		A2.getItens().get(2).executarItem();
	}
	
	@Test
	void testGetMaisAntigo() {
		testConfiguraAtividadesRepositorio();
		assertEquals("A1", p1.getMaisAntigo());
	}
	
	@Test
	void testGetMenosPendentes() {
		testConfiguraAtividadesRepositorio();
		assertEquals("A2", p1.getMenosPendentes());
	}
	
	@Test
	void testGetMaiorRisco() {
		testConfiguraAtividadesRepositorio();
		assertEquals("A3", p1.getMaiorRisco());
	}
}
