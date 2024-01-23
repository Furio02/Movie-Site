package model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "hall")
public class Sala {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "hall_name")
	String nomeSala;
	@OneToMany(mappedBy = "sala", fetch = FetchType.LAZY)
	List<Spettacolo> shows;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Sala() {
	}

	public Sala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	public String getNomeSala() {
		return nomeSala;
	}

	public void setNomeSala(String nomeSala) {
		this.nomeSala = nomeSala;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomeSala);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return Objects.equals(nomeSala, other.nomeSala);
	}

}
