package co.edu.eam.ingesoft.videotienda.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the city database table.
 * 
 */
@Entity
@Table(name="city")
@NamedQuery(name="City.findAll", query="SELECT c FROM City c")
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="city_id", unique=true, nullable=false)
	private int cityId;

	@Column(nullable=false, length=50)
	private String city;


	//bi-directional many-to-one association to Country
	@ManyToOne//(fetch=FetchType.LAZY)
	@JoinColumn(name="country_id", nullable=false)
	private Country country;

	public City() {
	}

	public int getCityId() {
		return this.cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	

	

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return city;
	}

}