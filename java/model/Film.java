package model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "movie", uniqueConstraints = {@UniqueConstraint(columnNames = {"titolo","durata","trama"})})
public class Film {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "titolo")
	private String titolo;
	@Column(name = "durata")
	private String durata;
	@Column(name = "trama")
	private String trama;
	@ManyToOne
	private Genre description;
	@Column(name = "hidden")
	private boolean hidden;
	@ManyToMany()
	@JoinTable(name = "movie_actor", joinColumns = @JoinColumn(name = "id_movie"), inverseJoinColumns = @JoinColumn(name = "id_actor"))
	private List<Actor> attori;
	@OneToMany(mappedBy = "movie")
	List<Spettacolo> spettacoli;

	public List<Spettacolo> getSpettacoli() {
		return spettacoli;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public void setSpettacoli(List<Spettacolo> spettacoli) {
		this.spettacoli = spettacoli;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public String getTrama() {
		return trama;
	}

	public void setTrama(String trama) {
		this.trama = trama;
	}

	public Genre getDescription() {
		return description;
	}

	public void setDescription(Genre description) {
		this.description = description;
	}

	public List<Actor> getAttori() {
		return attori;
	}

	public void setAttori(List<Actor> attori) {
		this.attori = attori;
	}

	@Override
	public int hashCode() {
		return Objects.hash(titolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(titolo, other.titolo);
	}

}
