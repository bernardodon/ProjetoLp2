package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entidades.Item;

class ItemTest {

	Item i1;
	Item i2;
	Item i3;
	Item i4;
	Item i5;

	@BeforeEach
	void testCriaItem() {

		i1 = new Item("bola");
		i2 = new Item("golf");
		i3 = new Item("loucura");
		i4 = new Item("disney");
		i5 = new Item("dale");
	}
	
	@Test
	void testItemToString() {
		assertEquals("PENDENTE - bola", i1.toString());
		assertEquals("PENDENTE - golf", i2.toString());
		assertEquals("PENDENTE - loucura", i3.toString());
	}
	void testExecutaItem() {
		i4.executarItem();
		i5.executarItem();
	}
	
	
	@Test 
	void testItemgetStatus1() {
		assertEquals("PENDENTE",i1.getStatus());
		assertEquals("PENDENTE",i2.getStatus());
		assertEquals("PENDENTE",i3.getStatus());
		assertEquals("REALIZADO",i4.getStatus());
		assertEquals("REALIZADO",i5.getStatus());
	}
	
	@Test 
	void testGetDescricao() {
		assertEquals("bola",i1.getStatus());
		assertEquals("golf",i2.getStatus());
		assertEquals("loucura",i3.getStatus());
		assertEquals("disney",i4.getStatus());
		assertEquals("dale",i5.getStatus());
	}
	
	

}