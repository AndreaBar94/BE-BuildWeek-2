package BEBuildWeek2.Epic_Energy_Services_CRM.imports;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVImporter {
    private static final String CSV_FILE_PATH = "comuni-italiani.csv";
    private static final String INSERT_QUERY = "INSERT INTO comune (codice_provincia, progressivo_comune, nome) VALUES (?, ?, ?)";

    public static void importCSVData() {
        try (Connection connection = DatabaseConnection.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
             PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");

                // Imposta i parametri per l'inserimento dei dati nella query preparata
                statement.setString(1, data[0]);
                statement.setString(2, data[1]);
                statement.setString(3, data[2]);

                // Esegui l'inserimento dei dati
                statement.executeUpdate();
            }

            System.out.println("Importazione comuni CSV completata.");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //importCSVData();
    }
}
