package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entity.EntityOrdine;
import exception.DAOException;
import exception.DBConnectionException;

public class OrdineDAO {

	public static void createOrdine(EntityOrdine eO) throws DAOException, DBConnectionException {

		try {
			Connection conn = DBManager.getConnection();

			String query = "INSERT INTO ORDINE VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,null);";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setDate(1, eO.getDataPrenotazione());
				stmt.setInt(2, eO.getPeso());
				stmt.setString(3, eO.getFarcitura());
				stmt.setString(4, eO.getScritta());
				stmt.setDate(5, eO.getGiornoConsegna());
				stmt.setString(6, eO.getStato());
				stmt.setFloat(7, eO.getPrezzoTotale());
				stmt.setInt(8, eO.getIdCliente());
				stmt.setInt(9, eO.getIdTorta());

				stmt.executeUpdate();

			} catch (SQLException e) {
				throw new DAOException("Errore scrittura ordine");
			} finally {
				DBManager.closeConnection();
			}

		} catch (SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

	}

}