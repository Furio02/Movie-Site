package utility;

import java.util.Comparator;

import model.Film;

public class GenreMovieComparator implements Comparator<Film>{

	@Override
	public int compare(Film o1, Film o2) {
		
		if(o1.getDescription().getDescription().compareTo(o2.getDescription().getDescription()) == 0) {
			return o1.getTitolo().compareTo(o2.getTitolo());
		}else {
			return o1.getDescription().getDescription().compareTo(o2.getDescription().getDescription());
		}
	}

}
