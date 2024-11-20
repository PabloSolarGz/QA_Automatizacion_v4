package pageFactory.dao.sesion;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.*;

public class LoginDao {
    private static final String CSV_PATH = "src/test/resources/features/csv/sesion/datos_sesion.csv";

    public static Map<String, String> cargarDatosSesion() {
        List<String[]> registros = leerCSV(CSV_PATH);

        // Validar si hay datos disponibles para ejecutar
        if (registros == null || registros.isEmpty()) {
            System.err.println("Error: No hay datos en el archivo CSV.");
            return null;
        }

        Map<String, String> datos = new HashMap<>();
        boolean datosEncontrados = false;

        // Iterar por cada registro hasta encontrar uno con csvEnUso = "no"
        for (int i = 1; i < registros.size(); i++) { // Empezar en 1 para omitir el encabezado
            String[] registro = registros.get(i);

            if ("no".equalsIgnoreCase(registro[0])) {
                // Cargar datos en el mapa
                datos.put("csvEnUso", registro[0]);
                datos.put("csvBrowser", registro[1]);
                datos.put("csvAmbiente", registro[2]);
                datos.put("csvUsuario", registro[3]);
                datos.put("csvPass", registro[4]);

                datosEncontrados = true;
                System.out.println("Datos cargados desde el CSV: " + datos);
                break;
            }
        }
        if (!datosEncontrados) {
            System.err.println("Error: Todos los registros del archivo CSV ya fueron utilizados.");
            return null;
        }

        return datos;
    }

    private static List<String[]> leerCSV(String rutaArchivo) {
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            return reader.readAll();
        } catch (Exception e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
            return null;
        }
    }
}