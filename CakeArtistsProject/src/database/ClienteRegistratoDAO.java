package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.EntityClienteRegistrato;
import exception.DAOException;
import exception.DBConnectionException;

public class ClienteRegistratoDAO {

	public static EntityClienteRegistrato readClienteRegistrato(String username)
			throws DAOException, DBConnectionException {
		EntityClienteRegistrato eC = null;

		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM CLIENTE_REGISTRATO WHERE USERNAME=?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, username);

				ResultSet result = stmt.executeQuery();

				if (result.next()) {
					eC = new EntityClienteRegistrato(result.getInt(1), result.getString(2), result.getString(3),
							result.getString(4), result.getString(5), result.getString(6), username,
							result.getString(8));
				}
			} catch (SQLException e) {
				throw new DAOException("Errore lettura ClienteRegistrato");
			} finally {
				DBManager.closeConnection();
			}

		} catch (SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

		return eC;
	}

}