package BEBuildWeek2.Epic_Energy_Services_CRM.imports;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVImporter {
	private static final String CSV_FILE_PATH = "comuni-italiani.csv";
	private static final String INSERT_QUERY = "INSERT INTO comune (codice_provincia, progressivo_comune, nome) VALUES (?, ?, ?)";

	public static void importCSVData() {
		try (Connection connection = DatabaseConnection.getConnection();
				BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
				PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {

			String line;
			boolean isFirstLine = true;

			while ((line = reader.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue; // Salta la prima riga
				}
				String[] data = line.split(";");

				if (data.length < 3) {
					System.out.println("Formato dati non valido: " + line);
					continue;
				}

				String nomeComune = data[2];
				System.out.println("Nome comune: " + nomeComune);

				if (comuneExists(connection, nomeComune)) {
					System.out.println("Il comune " + nomeComune + " esiste già. Salta l'inserimento.");
					continue;
				}

				try {
					// Imposta i parametri per l'inserimento dei dati nella query preparata
					int codiceProvincia = Integer.parseInt(data[0]);
					int progressivoComune = Integer.parseInt(data[1]);
					System.out.println("Codice provincia: " + codiceProvincia);
					System.out.println("Progressivo comune: " + progressivoComune);

					statement.setInt(1, codiceProvincia);
					statement.setInt(2, progressivoComune);
					statement.setString(3, nomeComune);

					// Esegui l'inserimento dei dati
					statement.executeUpdate();
					System.out.println("Inserimento nel database completato.");
				} catch (NumberFormatException e) {
					System.out.println("Formato numerico non valido: " + line);
				}
			}

			System.out.println("Importazione comuni CSV completata.");

		} catch (IOException | SQLException e) {
			e.printStackTrace();
		}
	}

	private static boolean comuneExists(Connection connection, String nomeComune) throws SQLException {
		String query = "SELECT nome FROM comune WHERE nome = ?";
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setString(1, nomeComune);
			try (ResultSet resultSet = statement.executeQuery()) {
				return resultSet.next();
			}
		}
	}

	public static void main(String[] args) {
		importCSVData();
	}
}
