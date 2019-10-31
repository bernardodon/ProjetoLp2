package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programa.Item;

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

}