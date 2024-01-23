package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity(name = "projection")
public class Spettacolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "projection_date")
	LocalDateTime dataSpettacolo;
	@ManyToOne
	Sala sala;
	@ManyToOne
	Film movie;
	@Column(name = "hidden")
	boolean hidden; 
	@OneToMany(mappedBy = "spettacolo", fetch = FetchType.LAZY)
	List<Biglietto> biglietti;

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public LocalDateTime getDataSpettacolo() {
		return dataSpettacolo;
	}

	public void setDataSpettacolo(LocalDateTime dataSpettacolo) {
		this.dataSpettacolo = dataSpettacolo;
	}

	public Sala getS() {
		return sala;
	}

	public void setS(Sala sala) {
		this.sala = sala;
	}

	public Film getF() {
		return movie;
	}

	public void setF(Film movie) {
		this.movie = movie;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<Biglietto> getBiglietti() {
		return biglietti;
	}

	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataSpettacolo, movie, sala);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spettacolo other = (Spettacolo) obj;
		return Objects.equals(dataSpettacolo, other.dataSpettacolo) && Objects.equals(movie, other.movie)
				&& Objects.equals(sala, other.sala);
	}

}
