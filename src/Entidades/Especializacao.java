package Entidades;

import java.io.Serializable;

/**
 * Representacao da interface Especializacao
 * @author Hiarly Fernandes de Souto
 *
 */
public interface Especializacao extends Serializable{
	/*
	 * Altera um atributo de uma classe que implementar essa interface
	 * @ atributo O nome do atributo que sera alterado
	 * @ novovalor O novo valor para o atributo escolhido
	 */
	void alteraAtributo(String atributo, String novoValor);
}
