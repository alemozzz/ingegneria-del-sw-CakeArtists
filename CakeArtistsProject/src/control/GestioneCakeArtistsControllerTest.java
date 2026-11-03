package control;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import exception.OperationException;

public class GestioneCakeArtistsControllerTest {

	private GestioneCakeArtistsController controller;
	private Date dataValida;
	private Date dataNonValida;

	@Before
	public void setUp() {
		controller = GestioneCakeArtistsController.getInstance();
		dataValida = Date.valueOf(LocalDate.now().plusDays(2));
		dataNonValida = Date.valueOf(LocalDate.now().plusDays(1));
	}

	// UT1 - Cammino 1 (0): Torta non presente nel catalogo
	@Test
	public void testUT1_TortaNonPresente() {
		try {
			controller.inserisciOrdine("Sacher", 2, "Chantilly", "Auguri", dataValida, "mrossi");
			fail("Attesa OperationException per torta non presente");
		} catch (OperationException e) {
			assertEquals("Torta selezionata non presente nel catalogo!", e.getMessage());
		}
	}

	// UT2 - Cammino 3 (0-1-2-3-5-2-6): Farcitura non disponibile
	@Test
	public void testUT2_FarcituraNonDisponibile() {
		try {
			controller.inserisciOrdine("Millefoglie", 2, "Pistacchio", "Auguri", dataValida, "mrossi");
			fail("Attesa OperationException per farcitura non disponibile");
		} catch (OperationException e) {
			assertEquals("Farcitura non disponibile per la torta selezionata!", e.getMessage());
		}
	}

	// UT3 - Cammino 5 (0-1-2-3-4-5-2-6-7): Peso < 1 kg
	@Test
	public void testUT3_PesoNonValido() {
		try {
			controller.inserisciOrdine("Millefoglie", 0, "Chantilly", "Auguri", dataValida, "mrossi");
			fail("Attesa OperationException per peso non valido");
		} catch (OperationException e) {
			assertEquals("Peso non valido!", e.getMessage());
		}
	}

	// UT4 - Cammino 6 (0-1-2-3-4-5-2-6-7-8): Giorno consegna < 2 giorni
	@Test
	public void testUT4_DataConsegnaAntecedenteDueGiorni() {
		try {
			controller.inserisciOrdine("Millefoglie", 1, "Chantilly", "Auguri", dataNonValida, "mrossi");
			fail("Attesa OperationException per giorno consegna antecedente a 2 giorni");
		} catch (OperationException e) {
			assertEquals("Giorno di consegna antecedente a 2 giorni!", e.getMessage());
		}
	}

	// UT5 - Cammino 7 (0-1-2-3-4-5-2-6-7-8-9): Cliente registrato non trovato
	@Test
	public void testUT5_ClienteNonTrovato() {
		try {
			controller.inserisciOrdine("Millefoglie", 2, "Chantilly", "Auguri", dataValida, "utente_inesistente");
			fail("Attesa OperationException per cliente non trovato");
		} catch (OperationException e) {
			assertEquals("Cliente registrato non trovato!", e.getMessage());
		}
	}

	// UT6 - Cammino 8 (0-1-2-3-4-5-2-6-7-8-9-10): Inserimento ordine valido
	@Test
	public void testUT6_InserimentoOrdineValido() throws OperationException {
		float prezzo = controller.inserisciOrdine("Millefoglie", 2, "Chantilly",
				"Buon Compleanno", dataValida, "mrossi");
		assertEquals(50.0f, prezzo, 0.01f);
	}

}