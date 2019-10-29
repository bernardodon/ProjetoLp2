package Testes;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import programa.Pesquisa;
class PesquisaTest {
	
	Pesquisa p1;
	Pesquisa p2;
	Pesquisa p3;
	
	@BeforeEach
	void testCriaPesquisa() {
		p1 = new Pesquisa("Homofobia em mensagens online de alunos de computacao do primeiro periodo.", "computacao, homofobia", "COM1");
		p2 = new Pesquisa("Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.", "eleicao", "COM2");
		p3 =  new Pesquisa("Autoavaliacao na Disciplina de Programacao Orientada a Objeto.", "computacao, poo", "COM3");
	}
	
	
	
}
