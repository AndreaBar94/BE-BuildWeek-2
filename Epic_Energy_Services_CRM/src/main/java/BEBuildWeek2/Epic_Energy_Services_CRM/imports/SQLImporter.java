package BEBuildWeek2.Epic_Energy_Services_CRM.imports;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLImporter {
    private static final String SQL_FILE_PATH = "epicode_be_backup.sql";

    public static void importSQLFile() {
        try (Connection connection = DatabaseConnection.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(SQL_FILE_PATH));
             Statement statement = connection.createStatement()) {

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            String sqlScript = sb.toString();

            statement.executeUpdate(sqlScript);

            System.out.println("Importazione file SQL completata.");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
    	//scommentare per vedere il database popolato con i dati del file fornito da traccia
       // importSQLFile();
    }
}
