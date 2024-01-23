package utility;

import java.util.Comparator;

import model.Film;

public class TitleMovieComparator implements Comparator<Film>{

	@Override
	public int compare(Film o1, Film o2) {
		return o1.getTitolo().compareTo(o2.getTitolo());
	}

}
