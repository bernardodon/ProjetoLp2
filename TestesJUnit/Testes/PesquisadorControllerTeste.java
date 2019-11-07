package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controllers.ControllerGeral;
import Controllers.PesquisadorController;

class PesquisadorControllerTeste {

	ControllerGeral controllerGeral = new ControllerGeral();
	PesquisadorController controller;

	@BeforeEach
	void testPesquisadorController() {
		controller = controllerGeral.getPesquisadorController();
	}

	@Test
	void testCadastraPesquisador() {
		controller.cadastraPesquisador("Joao", "Buscar melhorias para o programa", "Doutor em sistemas",
				"joao@gmail.com", "http://joaogmail.com");
		controller.cadastraPesquisador("Maria", "Modelar o site", "Especialista em Web Desing", "maria@gmail.com",
				"https://www.mariafoto.com.br");
	}

	@Test
	void testCadastraPesquisadorErro() {
		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador("", "Buscar melhorias para o programa", "Doutor em sistemas",
					"joao@gmail.com", "http://joaogmail.com");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador("Joao", "", "Doutor em sistemas", "joao@gmail.com", "http://joaogmail.com");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador("Joao", "Buscar melhorias para o programa", "", "joao@gmail.com",
					"http://joaogmail.com");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador("Joao", "Buscar melhorias para o programa", "Doutor em sistemas", "",
					"http://joaogmail.com");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controller.cadastraPesquisador("Joao", "Buscar melhorias para o programa", "Doutor em sistemas",
					"joao@gmail.com", "");
		});
	}

	@Test
	void testAlteraPesquisador() {
		testCadastraPesquisador();
		controller.alteraPesquisador("joao@gmail.com", "EMAIL", "joao@hotmail.com");
		controller.alteraPesquisador("maria@gmail.com", "FOTO", "https://foto.com.br");
		controller.alteraPesquisador("maria@gmail.com", "NOME", "Maria da Silva");
	}

	@Test
	void alterarPesquisadorErrors() {
		testCadastraPesquisador();
		assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("awdwadwd@gmail.com", "NOME", "BLAA");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("joao@gmail.com", "ESTADO", "SP");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("joao@gmail.com", "", "João Silva");
		});

		assertThrows(IllegalArgumentException.class, () -> {
			controller.alteraPesquisador("joao@gmail.com", "NOME", "");
		});
	}

	@Test
	void testDesativaPesquisador() {
		testCadastraPesquisador();
		controller.desativaPesquisador("joao@gmail.com");
		assertThrows(IllegalArgumentException.class, () -> {
			controller.desativaPesquisador("adawdawd@gmail.com");
		});
		assertThrows(IllegalArgumentException.class, () -> {
			controller.desativaPesquisador("joao@gmail.com");
		});

	}

	@Test
	void testAtivaPesquisador() {
		testDesativaPesquisador();
		controller.ativaPesquisador("joao@gmail.com");
		assertThrows(IllegalArgumentException.class, () -> {
			controller.ativaPesquisador("joao@gmail.com");
		});
	}

	@Test
	void testExibePesquisador() {
		testCadastraPesquisador();
		assertEquals(
				"Joao (Buscar melhorias para o programa) - Doutor em sistemas - joao@gmail.com - http://joaogmail.com",
				controller.exibePesquisador("joao@gmail.com"));
	}

	@Test
	void testPesquisadorEhAtivo() {
		testDesativaPesquisador();
		assertEquals(false, controller.pesquisadorEhAtivo("joao@gmail.com"));
		testAtivaPesquisador();
		assertEquals(true, controller.pesquisadorEhAtivo("joao@gmail.com"));
		assertThrows(IllegalArgumentException.class, () -> {
			controller.pesquisadorEhAtivo("adawdaw@gmail.com");
		});
	}

}
