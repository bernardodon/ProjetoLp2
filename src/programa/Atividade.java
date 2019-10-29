package programa;

import java.util.ArrayList;
import java.util.List;

public class Atividade {
	private String descricaoAtvd;
	private String duracao;
	private String risco;
	private String descricaoRisco;
	private List<Item> itens;
	
	public Atividade(String descricaoAtvd, String risco, String descricaoRisco) {
		this.descricaoAtvd = descricaoAtvd;
		this.descricaoRisco = descricaoRisco;
		this.risco =risco;
		this.itens = new ArrayList<Item>();
	}
	
	public List<Item> getItens() {
		return itens;
	}

	public void adicionaItem(Item i) {
		itens.add(i);
	}
	
	public int quantPendentes() {
		int cont = 0;
		for(Item i: itens) {
			if (i.getStatus() == "PENDENTE") {
				cont ++ ;
			}
		}
		return cont;
	}
	
	public int quantRealizados() {
		int cont = 0;
		for(Item i: itens) {
			if (i.getStatus() == "REALIZADO") {
				cont ++ ;
			}
		}
		return cont;
	}
	
	public String exibeItens() {
		String msg = "";
		
		for(Item i: itens) {
			msg += " | " + i.toString();
		}
		return msg;
	}
	
	@Override
	public String toString() {
		return this.descricaoAtvd + " (" + this.risco + " - " + this.descricaoRisco + ")";
	}
}
