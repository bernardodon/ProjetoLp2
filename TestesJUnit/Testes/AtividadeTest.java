package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entidades.Atividade;
import Entidades.Item;

class AtividadeTest{

	Atividade a1;
	Atividade a2;
	Atividade a3;
	Atividade a4;
	Atividade a5;
	Atividade a6;


	@BeforeEach
	void testCriaAtividade() {
		a1 = new Atividade("Descricao 1", "ALTO", "Risco alto", "A1");
		a2 = new Atividade("Descricao 2", "MEDIO", "Risco medio", "A2");
		a3 = new Atividade("Descricao 3", "BAIXO", "Risco baixo", "A3");
		a4 = new Atividade("Descricao 4", "ALTO", "Risco alto", "A4");
		a5 = new Atividade("Descricao 3", "BAIXO", "Risco baixo", "A3");
		a6 = new Atividade("Descricao 4", "ALTO", "Risco alto", "A4");	
	}
	
	@Test
	void testCriaAtividadeDescricaoVaziaOuNula() {
		assertThrows(IllegalArgumentException.class, () -> new Atividade("", "ALTO", "Risco alto", "A1"));
		assertThrows(NullPointerException.class, () -> new Atividade(null, "MEDIO", "Risco alto", "A2"));
	}
	
	@Test
	void testCriaAtividadeRiscoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> new Atividade("Descricao 1", "", "Risco alto", "A1"));
		assertThrows(NullPointerException.class, () -> new Atividade("Descricao 2", null, "Risco medio", "A2"));
	}
	
	@Test
	void testCriaAtividadeDescricaoRiscoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> new Atividade("Descricao 1", "ALTO", "", "A1"));
		assertThrows(NullPointerException.class, () -> new Atividade("Descricao 2", "MEDIO", null, "A2"));
	}
	
	@Test
	void testCriaAtividadeCodigoVazioOuNulo() {
		assertThrows(IllegalArgumentException.class, () -> new Atividade("Descricao 1", "ALTO", "Risco alto", ""));
		assertThrows(NullPointerException.class, () -> new Atividade("Descricao 2", "MEDIO", "Risco medio", null));
	}
	
	@Test
	void testAdicionaResultados() {
		a1.adicionarResultado("Resultado 1");
		a1.adicionarResultado("Resultado 2");
		a1.adicionarResultado("Resultado 3");
		a1.adicionarResultado("Resultado 4");
		a2.adicionarResultado("Resultado a");
		a2.adicionarResultado("Resultado b");
		a2.adicionarResultado("Resultado c");
	}
	
	@Test
	void testRemoverResultados() {
		testAdicionaResultados();
		a1.removerResultado(4);
		a2.removerResultado(3);
	}
	
	@Test
	void testRemoverResultadosInvalidos() {
		testAdicionaResultados();
		assertThrows(IllegalArgumentException.class, () -> a1.removerResultado(8));
		assertThrows(IllegalArgumentException.class, () -> a2.removerResultado(10));
	}
	
	@Test
	void testListarResultados() {
		testRemoverResultados();
		assertEquals("Resultado 1 | Resultado 2 | Resultado 3", a1.listarResultados());
		assertEquals("Resultado a | Resultado b", a2.listarResultados());
	}
	
	@Test
	void testTamanhoDeResultados() {
		testRemoverResultados();
		assertEquals(3, a1.tamanhoDeResultados());
		assertEquals(2, a2.tamanhoDeResultados());
	}
	
	@Test
	void testAdicionaItem() {
		a1.adicionaItem(new Item("item 1"));
		a1.adicionaItem(new Item("item 2"));
		a1.adicionaItem(new Item("item 3"));
		a1.adicionaItem(new Item("item 4"));
		a2.adicionaItem(new Item("item a"));
		a2.adicionaItem(new Item("item b"));
		a2.adicionaItem(new Item("item c"));
		a3.adicionaItem(new Item("itenzinho"));
		a3.adicionaItem(new Item("item"));
		a3.adicionaItem(new Item("itenzao"));
	}
	
	@Test
	void testExecutaItem() {
		testAdicionaItem();
		a1.executarItem(2, 25);
		a1.executarItem(1, 10);
		a2.executarItem(1, 30);
		a3.executarItem(1, 15);
		a3.executarItem(2, 15);
		a3.executarItem(3, 15);
	}
	
	@Test
	void testExibeItens() {
		testExecutaItem();
		assertEquals(" | REALIZADO - item 1 | REALIZADO - item 2 | PENDENTE - item 3 | PENDENTE - item 4", a1.exibeItens());
		assertEquals(" | REALIZADO - item a | PENDENTE - item b | PENDENTE - item c", a2.exibeItens());
		assertEquals(" | REALIZADO - itenzinho | REALIZADO - item | REALIZADO - itenzao", a3.exibeItens());
	}
	
	@Test
	void testAtividadeHashCode() {
		assertEquals(a4.hashCode(), a6.hashCode());
		assertEquals(a3.hashCode(), a5.hashCode());
	}
	
	@Test
	void testAtividadeEquals() {
		assertEquals(true, a4.equals(a6));
		assertEquals(false, a1.equals(a5));
	}
	
	@Test
	void testQuantidadeItensRealizados() {
		testExecutaItem();
		assertEquals(2, a1.quantRealizados());
		assertEquals(1, a2.quantRealizados());
	}
	
	@Test
	void testQuantidadeItensPendentes() {
		testExecutaItem();
		assertEquals(2, a1.quantPendentes());
		assertEquals(2, a2.quantPendentes());
	}
	
	@Test
	void testAtividadeTemPendentes() {
		testExecutaItem();
		assertEquals(true, a1.temPendentes());
		assertEquals(false, a3.temPendentes());
	}
	
	@Test
	void testChecaInexistenciaItem() {
		testExecutaItem();
		assertThrows(IllegalArgumentException.class, () -> a1.checaInexistenciaItem(6));
		assertThrows(IllegalArgumentException.class, () -> a2.checaInexistenciaItem(4));
	}
	
	@Test
	void testChecaStatusItem() {
		testExecutaItem();
		assertThrows(IllegalArgumentException.class, () -> a1.checaExecucaoItem(2));
		assertThrows(IllegalArgumentException.class, () -> a2.checaExecucaoItem(1));
	}
	
	@Test
	void testDefineProximaAtividade() {
		a1.defineProximaAtividade(a2);
		a2.defineProximaAtividade(a4);
		a4.defineProximaAtividade(a3);
	}
	
	@Test
	void testDefineProximaAtividadeInvalido() {
		testDefineProximaAtividade();
		assertThrows(IllegalArgumentException.class, () -> {
			a1.defineProximaAtividade(a3);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			a3.defineProximaAtividade(a1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			a1.defineProximaAtividade(null);
		});
	}
	
	@Test
	void tiraProximaAtividade() {
		testDefineProximaAtividade();
		a1.tiraProximaAtividade();
		a4.tiraProximaAtividade();
		a5.tiraProximaAtividade();
	}
	
	@Test
	void testContaProximos() {
		testDefineProximaAtividade();
		assertEquals(3, a1.contaProximos());
		assertEquals(0, a3.contaProximos());
	}
	
	@Test
	void testPegaProximo() {
		testDefineProximaAtividade();
		assertEquals("Descricao 2 (MEDIO - Risco medio)", a1.pegaProximo(1).toString());
		assertEquals("Descricao 3 (BAIXO - Risco baixo)", a1.pegaProximo(3).toString());
		assertThrows(IllegalArgumentException.class, () -> {
			a1.pegaProximo(5);
		});
	}
	
	@Test
	void testPegaMaiorRisco() {
		testDefineProximaAtividade();
		assertEquals("A4", a1.pegaMaiorRiscoAtividades().getCodigo());
		assertThrows(IllegalArgumentException.class, () -> {
			a3.pegaMaiorRiscoAtividades();
		});
	}
	
}