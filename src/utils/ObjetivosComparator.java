package utils;

import java.util.Comparator;

import Entidades.Pesquisa;

public class ObjetivosComparator implements Comparator<Pesquisa> {
	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		return o2.getObjetivos().size() - o1.getObjetivos().size();
	}

}
