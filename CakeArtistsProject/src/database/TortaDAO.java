package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.EntityTorta;
import exception.DAOException;
import exception.DBConnectionException;

public class TortaDAO {

	public static EntityTorta readTorta(String nome) throws DAOException, DBConnectionException {
		EntityTorta eT = null;

		try {
			Connection conn = DBManager.getConnection();

			String query = "SELECT * FROM TORTA WHERE NOME=?;";

			try {
				PreparedStatement stmt = conn.prepareStatement(query);

				stmt.setString(1, nome);

				ResultSet result = stmt.executeQuery();

				if (result.next()) {
					eT = new EntityTorta(result.getInt(1), result.getString(2), result.getString(3),
							result.getString(4), result.getFloat(5));

					int idTorta = result.getInt(1);
					PreparedStatement stmtF = conn.prepareStatement("SELECT Farcitura FROM FARCITURA WHERE ID_Torta=?;");
					stmtF.setInt(1, idTorta);
					ResultSet rsF = stmtF.executeQuery();
					List<String> farciture = new ArrayList<>();
					while (rsF.next()) {
						farciture.add(rsF.getString(1));
					}
					eT.setFarciture(farciture);
				}
			} catch (SQLException e) {
				throw new DAOException("Errore lettura Torta");
			} finally {
				DBManager.closeConnection();
			}

		} catch (SQLException e) {
			throw new DBConnectionException("Errore connessione database");
		}

		return eT;
	}

}