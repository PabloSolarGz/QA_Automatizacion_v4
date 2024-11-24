package pageFactory.dao.solicitudes.ingreso;

import utils.Data;
import java.util.List;
import java.util.Map;

public class IngresoAdjuntarAntecedentesMedicosDao {

    private List<Map<String, String>> datos;
    private static final String RUTA_CSV = "src/test/resources/features/csv/solicitudes/ingreso/ingresoAdjuntarAntecedentesMedicos/datos_Adjuntar_Antecedentes.csv";

    public IngresoAdjuntarAntecedentesMedicosDao() {
        cargarDatosCSV();
    }

    public int obtenerNumeroFilas() {
        return datos.size();
    }

    private void cargarDatosCSV() {
        this.datos = Data.cargarCSV(RUTA_CSV);
    }

    public String getDato(String clave, int indice) {
        if (indice < datos.size()) {
            return datos.get(indice).getOrDefault(clave, "").trim();
        }
        throw new IndexOutOfBoundsException("El índice " + indice + " está fuera de los límites.");
    }

    public boolean validarDatosPorTipo(int indice) {
        try {
            Map<String, String> fila = datos.get(indice);

            if (fila.isEmpty() || fila.values().stream().allMatch(String::isEmpty)) {
                System.err.println("Fila vacía o sin datos: " + fila);
                return false;
            }

            String opcionAntecedentesMedicos = fila.getOrDefault("csvOpcionAntecedentes", "").trim();
            if (opcionAntecedentesMedicos.isEmpty()) {
                System.err.println("Situación laboral no especificada en la fila: " + fila);
                return false;
            }

            switch (opcionAntecedentesMedicos) {
                case "opcion1":
                    return !fila.getOrDefault("csvIncorporarOtrosAntecedentes", "").isEmpty();
                case "opcion2":
                    return !fila.getOrDefault("csvIncorporarOtrosAntecedentes", "").isEmpty() &&
                            !fila.getOrDefault("csvTipo", "").isEmpty();
                default:
                    System.err.println("Se a seleccionado la opción: " + opcionAntecedentesMedicos);
                    return false;
            }
        } catch (Exception e) {
            System.err.println("Error al validar datos en índice " + indice + ": " + e.getMessage());
            return false;
        }
    }
}
