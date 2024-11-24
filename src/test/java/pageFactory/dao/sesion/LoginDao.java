package pageFactory.dao.sesion;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginDao {
    private static final String CSV_PATH = "src/test/resources/features/csv/sesion/datos_sesion.csv";

    public static Map<String, String> cargarDatosSesion(String perfil, String comision) {
        List<String[]> registros = leerCSV(CSV_PATH);

        if (registros == null || registros.isEmpty()) {
            throw new RuntimeException("Error: No hay datos en el archivo CSV.");
        }

        System.out.println("Registros encontrados en el CSV:");
        for (String[] registro : registros) {
            System.out.println(String.join(", ", registro));
        }

        for (int i = 1; i < registros.size(); i++) {
            String[] registro = registros.get(i);
            if (registro[4].trim().equalsIgnoreCase(perfil.trim()) && registro[5].trim().equalsIgnoreCase(comision.trim())) {
                Map<String, String> datos = new HashMap<>();
                datos.put("csvBrowser", registro[0].trim());
                datos.put("csvAmbiente", registro[1].trim());
                datos.put("csvUsuario", registro[2].trim());
                datos.put("csvPass", registro[3].trim());
                System.out.println("Datos encontrados: " + datos);
                return datos;
            }
        }
        throw new RuntimeException("No se encontraron datos para el perfil '" + perfil + "' y la comisión '" + comision + "'. Verifica el archivo CSV.");
    }

    private static List<String[]> leerCSV(String rutaArchivo) {
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            return reader.readAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al leer el archivo CSV: " + e.getMessage());
        }
    }
}
