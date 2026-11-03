package entity;

import java.sql.Date;

import database.OrdineDAO;
import exception.DAOException;
import exception.DBConnectionException;

public class EntityOrdine {
	private int idOrdine;
	private Date dataPrenotazione;
	private int peso;
	private String farcitura;
	private String scritta;
	private Date giornoConsegna;
	private String stato;
	private float prezzoTotale;
	private int idTorta;
	private int idCliente;

	private EntityTorta trt;
	private EntityClienteRegistrato cln;

	public EntityOrdine(Date dataPrenotazione, int peso, String farcitura, String scritta,
			Date giornoConsegna, String stato, float prezzoTotale, int idTorta, int idCliente) {
		super();
		this.dataPrenotazione = dataPrenotazione;
		this.peso = peso;
		this.farcitura = farcitura;
		this.scritta = scritta;
		this.giornoConsegna = giornoConsegna;
		this.stato = stato;
		this.prezzoTotale = prezzoTotale;
		this.idTorta = idTorta;
		this.idCliente = idCliente;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public Date getDataPrenotazione() {
		return dataPrenotazione;
	}

	public void setDataPrenotazione(Date dataPrenotazione) {
		this.dataPrenotazione = dataPrenotazione;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getFarcitura() {
		return farcitura;
	}

	public void setFarcitura(String farcitura) {
		this.farcitura = farcitura;
	}

	public String getScritta() {
		return scritta;
	}

	public void setScritta(String scritta) {
		this.scritta = scritta;
	}

	public Date getGiornoConsegna() {
		return giornoConsegna;
	}

	public void setGiornoConsegna(Date giornoConsegna) {
		this.giornoConsegna = giornoConsegna;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public float getPrezzoTotale() {
		return prezzoTotale;
	}

	public void setPrezzoTotale(float prezzoTotale) {
		this.prezzoTotale = prezzoTotale;
	}

	public int getIdTorta() {
		return idTorta;
	}

	public void setIdTorta(int idTorta) {
		this.idTorta = idTorta;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public void saveOrdine() throws DAOException, DBConnectionException {
		OrdineDAO.createOrdine(this);
	}

}