package programa;

import easyaccept.EasyAccept;

public class Main {
	public static void main(String[] args) {
		args = new String[] { "programa.Facade", "testes-aceitacao/use_case_1.txt", "testes-aceitacao/use_case_2.txt", "testes-aceitacao/use_case_3.txt", 
				"testes-aceitacao/use_case_4.txt", "testes-aceitacao/use_case_5.txt", "testes-aceitacao/use_case_7.txt","testes-aceitacao/use_case_6.txt", 
				"testes-aceitacao/use_case_8.txt", "testes-aceitacao/use_case_9.txt", "testes-aceitacao/use_case_10.txt", "testes-aceitacao/use_case_11.txt"};
		EasyAccept.main(args);
	}
}