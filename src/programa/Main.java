package programa;

import easyaccept.EasyAccept;

public class Main {
	public static void main(String[] args) {
		args = new String[] { "programa.Facade", "testes-aceitacao/use_case_1.txt", "testes-aceitacao/use_case_2.txt", "testes-aceitacao/use_case_3.txt", 
				"testes-aceitacao/use_case_4.txt", "testes-aceitacao/use_case_6.txt"};
		EasyAccept.main(args);
	}
}