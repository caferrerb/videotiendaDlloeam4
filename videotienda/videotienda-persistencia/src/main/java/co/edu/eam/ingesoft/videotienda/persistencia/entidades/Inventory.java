package co.edu.eam.ingesoft.videotienda.persistencia.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the inventory database table.
 * 
 */
@Entity
@Table(name="inventory")
@NamedQuery(name="Inventory.findAll", query="SELECT i FROM Inventory i")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="inventory_id", unique=true, nullable=false)
	private int inventoryId;

	@Column(name="last_update", nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdate;

	//bi-directional many-to-one association to Film
	@ManyToOne
	@JoinColumn(name="film_id", nullable=false)
	private Film film;
	
	//bi-directional many-to-one association to Store
	@ManyToOne
	@JoinColumn(name="store_id", nullable=false)
	private Store store;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="inventory",fetch=FetchType.LAZY)
	private List<Rental> rentals;
	
	
	//bi-directional many-to-one association to dedobaja
	@Column(name="DADOBAJA")
	private boolean dadodeBaja;
	
	
	//bi-directional many-to-one association to comentario
	@Column(name="COMMENT")
	private String comentario;
	
	
	

	public Inventory() {
		dadodeBaja=false;
	}

	
	/**
	 * @return the inventoryId
	 */
	public int getInventoryId() {
		return inventoryId;
	}


	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}


	/**
	 * @return the lastUpdate
	 */
	public Date getLastUpdate() {
		return lastUpdate;
	}


	/**
	 * @param lastUpdate the lastUpdate to set
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


	/**
	 * @return the film
	 */
	public Film getFilm() {
		return film;
	}


	/**
	 * @param film the film to set
	 */
	public void setFilm(Film film) {
		this.film = film;
	}


	/**
	 * @return the store
	 */
	public Store getStore() {
		return store;
	}


	/**
	 * @param store the store to set
	 */
	public void setStore(Store store) {
		this.store = store;
	}


	/**
	 * @return the rentals
	 */
	public List<Rental> getRentals() {
		return rentals;
	}


	/**
	 * @param rentals the rentals to set
	 */
	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}


	/**
	 * @return the dadodeBaja
	 */
	public boolean isDadodeBaja() {
		return dadodeBaja;
	}


	/**
	 * @param dadodeBaja the dadodeBaja to set
	 */
	public void setDadodeBaja(boolean dadodeBaja) {
		this.dadodeBaja = dadodeBaja;
	}


	/**
	 * @return the comentario
	 */
	public String getComentario() {
		return comentario;
	}


	/**
	 * @param comentario the comentario to set
	 */
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + (dadodeBaja ? 1231 : 1237);
		result = prime * result + ((film == null) ? 0 : film.hashCode());
		result = prime * result + inventoryId;
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((rentals == null) ? 0 : rentals.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
		return result;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (dadodeBaja != other.dadodeBaja)
			return false;
		if (film == null) {
			if (other.film != null)
				return false;
		} else if (!film.equals(other.film))
			return false;
		if (inventoryId != other.inventoryId)
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (rentals == null) {
			if (other.rentals != null)
				return false;
		} else if (!rentals.equals(other.rentals))
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		return true;
	}


	public Rental addRental(Rental rental) {
		getRentals().add(rental);
		rental.setInventory(this);

		return rental;
	}

	public Rental removeRental(Rental rental) {
		getRentals().remove(rental);
		rental.setInventory(null);

		return rental;
	}


}