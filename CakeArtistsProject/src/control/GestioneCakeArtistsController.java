package control;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import database.ClienteRegistratoDAO;
import database.TortaDAO;
import entity.EntityClienteRegistrato;
import entity.EntityOrdine;
import entity.EntityTorta;
import exception.DAOException;
import exception.DBConnectionException;
import exception.OperationException;

public class GestioneCakeArtistsController {

	private static GestioneCakeArtistsController gCA = null;

	protected GestioneCakeArtistsController() {
	}

	public static GestioneCakeArtistsController getInstance() {
		if (gCA == null)
			gCA = new GestioneCakeArtistsController();

		return gCA;
	}

	/**
	 * Gestisce l'inserimento di un nuovo ordine di torta personalizzata.
	 * Verifica l'esistenza della torta a catalogo e la disponibilita' della farcitura,
	 * controlla i vincoli di dominio sul peso (>= 1 kg) e sul preavviso (>= 2 giorni),
	 * calcola il prezzo totale e persiste l'ordine nel database tramite il livello DAO.
	 *
	 * @param nomeTorta      Il nome della torta selezionata dal catalogo.
	 * @param peso           Il peso richiesto in kg (intero >= 1).
	 * @param farcitura      La farcitura scelta per la torta.
	 * @param scritta        La scritta decorativa opzionale (max 50 caratteri).
	 * @param giornoConsegna La data di consegna richiesta (almeno 2 giorni dalla data odierna).
	 * @param username       Lo username del cliente registrato che effettua l'ordine.
	 * @return float         Il prezzo totale calcolato (prezzoAlKg * peso).
	 * @throws OperationException Se i vincoli di dominio sono violati, se la torta o la farcitura
	 *                            non sono disponibili, o in caso di errore di persistenza DAO/DB.
	 */
	public float inserisciOrdine(String nomeTorta, int peso, String farcitura, String scritta,
			Date giornoConsegna, String username) throws OperationException {

		EntityTorta eT = null;
		EntityClienteRegistrato eC = null;
		float prezzoTotale = 0;

		try {
			// controllo esistenza torta nel catalogo
			eT = TortaDAO.readTorta(nomeTorta);

			if (eT == null) {
				throw new OperationException("Torta selezionata non presente nel catalogo!");
			}

			// controllo disponibilita farcitura per la torta selezionata
			List<String> farcitureDisponibili = eT.getFarciture();
			boolean farcituraValida = false;

			for (int i = 0; i < farcitureDisponibili.size(); i++) {
				if (farcitureDisponibili.get(i).trim().equalsIgnoreCase(farcitura.trim())) {
					farcituraValida = true;
				}
			}

			if (!farcituraValida) {
				throw new OperationException("Farcitura non disponibile per la torta selezionata!");
			}

			// controllo validita peso e preavviso minimo di 2 giorni
			Date dataOdierna = Date.valueOf(LocalDate.now());
			Date dataMinimaConsegna = Date.valueOf(LocalDate.now().plusDays(2));

			if (peso < 1) {
				throw new OperationException("Peso non valido!");
			}

			if (giornoConsegna.before(dataMinimaConsegna)) {
				throw new OperationException("Giorno di consegna antecedente a 2 giorni!");
			}

			// controllo clienteRegistrato
			eC = ClienteRegistratoDAO.readClienteRegistrato(username);

			if (eC == null) {
				throw new OperationException("Cliente registrato non trovato!");
			}

			// calcolo prezzo totale e salvataggio ordine
			prezzoTotale = calcolaPrezzoTotale(eT.getPrezzoAlKg(), peso);

			EntityOrdine eO = new EntityOrdine(dataOdierna, peso, farcitura, scritta,
					giornoConsegna, "IN_ATTESA", prezzoTotale, eT.getIdTorta(), eC.getIdCliente());

			eO.saveOrdine();

		} catch (DBConnectionException dbEx) {
			throw new OperationException("\nRiscontrato problema interno applicazione!\n");
		} catch (DAOException ex) {
			throw new OperationException("Ops, qualcosa e' andato storto..");
		}

		return prezzoTotale;
	}

	private float calcolaPrezzoTotale(float prezzoAlKg, int peso) {
		return prezzoAlKg * peso;
	}

}