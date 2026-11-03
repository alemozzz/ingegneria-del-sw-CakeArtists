package boundary;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import control.GestioneCakeArtistsController;
import exception.OperationException;

public class BoundaryClienteRegistrato extends BoundaryCliente {

	public static void main(String[] args) {
		boolean exit = false;

		while (!exit) {
			System.out.println("Cliente Registrato");
			System.out.println("1. Visualizza catalogo torte");
			System.out.println("2. Inserisci ordine");
			System.out.println("3. Esci");

			String op = scan.nextLine();

			if (op.equals("1")) {
				visualizzaCatalogo();
			} else if (op.equals("2")) {
				inserisciOrdine();
			} else if (op.equals("3")) {
				exit = true;
			} else {
				System.out.println("Operazione non disponibile");
				System.out.println();
			}
		}

		System.out.println("Arrivederci!");
	}

	private static void inserisciOrdine() {

		GestioneCakeArtistsController gestioneCakeArtists = GestioneCakeArtistsController.getInstance();
		String nomeTorta = null;
		int peso = 0;
		String farcitura = null;
		String scritta = null;
		Date giornoConsegna = null;
		String username = "mrossi";
		boolean inputValido = false;

		try {
			while (!inputValido) {
				System.out.println("Inserisci il nome della torta");
				nomeTorta = scan.nextLine();

				if (nomeTorta.length() > 0 && nomeTorta.length() <= 50) {
					inputValido = true;
				} else {
					System.out.println("Nome torta troppo lungo!");
				}
			}

			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci il peso in kg");
					peso = Integer.parseInt(scan.nextLine());

					if (peso >= 1) {
						inputValido = true;
					} else {
						System.out.println("Peso non valido!");
					}
				} catch (NumberFormatException nE) {
					System.out.println("Peso non valido!");
				}
			}

			inputValido = false;
			while (!inputValido) {
				System.out.println("Inserisci la farcitura");
				farcitura = scan.nextLine();

				if (farcitura.length() > 0 && farcitura.length() <= 50) {
					inputValido = true;
				} else {
					System.out.println("Farcitura non valida!");
				}
			}

			inputValido = false;
			while (!inputValido) {
				System.out.println("Inserisci eventuale scritta decorativa (invio per nessuna)");
				scritta = scan.nextLine();

				if (scritta.length() <= 50) {
					inputValido = true;
				} else {
					System.out.println("Scritta troppo lunga!");
				}
			}

			inputValido = false;
			while (!inputValido) {
				try {
					System.out.println("Inserisci il giorno di consegna (gg/mm/aaaa)");
					String dataTemp = scan.nextLine();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate parsedDate = LocalDate.parse(dataTemp, formatter);
					giornoConsegna = Date.valueOf(parsedDate);
					inputValido = true;
				} catch (DateTimeParseException | IllegalArgumentException iE) {
					System.out.println("Data non valida!");
				}
			}

			float prezzoTotale = gestioneCakeArtists.inserisciOrdine(nomeTorta, peso, farcitura,
					scritta, giornoConsegna, username);

			System.out.println("Ordine registrato");
			System.out.println("Prezzo totale: " + prezzoTotale + " euro");
			System.out.println();

		} catch (OperationException oE) {
			System.out.println(oE.getMessage());
			System.out.println("Riprovare..\n");
		} catch (Exception e) {
			System.out.println("Unexpected exception, riprovare..");
			System.out.println();
		}
	}

}