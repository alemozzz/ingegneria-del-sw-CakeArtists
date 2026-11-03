package entity;

import java.util.ArrayList;
import java.util.List;

public class EntityTorta {

	private int idTorta;
	private String nome;
	private String foto;
	private String descrizione;
	private List<String> farciture = new ArrayList<>();
	private float prezzoAlKg;

	public EntityTorta() {
		super();
	}

	public EntityTorta(int idTorta, String nome, String foto, String descrizione, float prezzoAlKg) {
		super();
		this.idTorta = idTorta;
		this.nome = nome;
		this.foto = foto;
		this.descrizione = descrizione;
		this.prezzoAlKg = prezzoAlKg;
	}

	public int getIdTorta() {
		return idTorta;
	}

	public void setIdTorta(int idTorta) {
		this.idTorta = idTorta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public List<String> getFarciture() {
		return farciture;
	}

	public void setFarciture(List<String> farciture) {
		this.farciture = farciture;
	}

	public float getPrezzoAlKg() {
		return prezzoAlKg;
	}

	public void setPrezzoAlKg(float prezzoAlKg) {
		this.prezzoAlKg = prezzoAlKg;
	}

}