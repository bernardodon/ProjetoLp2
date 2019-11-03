package programa;

import java.util.Comparator;

public class ObjetivosComparator implements Comparator<Pesquisa> {
	@Override
	public int compare(Pesquisa o1, Pesquisa o2) {
		return o2.getObjetivos().size() - o1.getObjetivos().size();
	}

}
