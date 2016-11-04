package co.edu.eam.ingesoft.videotienda.persistencia.entidades;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the film database table.
 * 
 */
@Entity
@Table(name="film")
@NamedQuery(name="Film.findAll", query="SELECT f FROM Film f")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_id", unique=true, nullable=false)
	private int filmId;

	@Lob
	private String description;

	@Column(name="last_update", nullable=false)
	private Timestamp lastUpdate;

	private int length;

	@Column(length=1)
	private String rating;

	@Temporal(TemporalType.DATE)
	@Column(name="release_year")
	private Date releaseYear;
	
	@Column(name="poster")
	@Lob
	private byte[] poster;

	@Column(name="rental_duration", nullable=false)
	private byte rentalDuration;

	@Column(name="rental_rate", nullable=false, precision=10, scale=2)
	private double rentalRate;

	@Column(name="replacement_cost", nullable=false, precision=10, scale=2)
	private double replacementCost;

	@Column(name="special_features")
	private String specialFeatures;

	@Column(nullable=false, length=255)
	private String title;
	
	//Lenguaje original de la pelicula
	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language_id", nullable=false)
	private Language language1;

	//Lenguaje de los subtitulos
	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="original_language_id")
	private Language language2;


	public Film() {
	}
	
	

	/**
	 * @param filmId
	 * @param description
	 * @param lastUpdate
	 * @param length
	 * @param rating
	 * @param releaseYear
	 * @param poster
	 * @param rentalDuration
	 * @param rentalRate
	 * @param replacementCost
	 * @param specialFeatures
	 * @param title
	 * @param language1
	 * @param language2
	 */
	public Film(int filmId, String description, Timestamp lastUpdate, int length, String rating, Date releaseYear,
			byte[] poster, byte rentalDuration, double rentalRate, double replacementCost, String specialFeatures,
			String title, Language language1, Language language2) {
		super();
		this.filmId = filmId;
		this.description = description;
		this.lastUpdate = lastUpdate;
		this.length = length;
		this.rating = rating;
		this.releaseYear = releaseYear;
		this.poster = poster;
		this.rentalDuration = rentalDuration;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.specialFeatures = specialFeatures;
		this.title = title;
		this.language1 = language1;
		this.language2 = language2;
	}



	public int getFilmId() {
		return this.filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public int getLength() {
		return this.length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(Date releaseYear) {
		this.releaseYear = releaseYear;
	}

	public byte getRentalDuration() {
		return this.rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public double getRentalRate() {
		return this.rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public double getReplacementCost() {
		return this.replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getSpecialFeatures() {
		return this.specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Language getLanguage1() {
		return this.language1;
	}

	public void setLanguage1(Language language1) {
		this.language1 = language1;
	}

	public Language getLanguage2() {
		return this.language2;
	}

	public void setLanguage2(Language language2) {
		this.language2 = language2;
	}

	public byte[] getPoster() {
		return poster;
	}


	public void setPoster(byte[] poster) {
		this.poster = poster;
	}



	@Override
	public String toString() {
		return title;
	}

}