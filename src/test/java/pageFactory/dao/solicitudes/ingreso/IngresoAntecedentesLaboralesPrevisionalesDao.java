package pageFactory.dao.solicitudes.ingreso;

import utils.Data;
import java.util.List;
import java.util.Map;

public class IngresoAntecedentesLaboralesPrevisionalesDao {
    private List<Map<String, String>> datos;
    private static final String RUTA_CSV = "src/test/resources/features/csv/solicitudes/ingreso/ingresoAntecedentesLaboralesPrevisionales/datos_antecedentes.csv";

    public IngresoAntecedentesLaboralesPrevisionalesDao() {
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

            String situacionLaboral = fila.getOrDefault("csvSituacionLaboral", "").trim();
            if (situacionLaboral.isEmpty()) {
                System.err.println("Situación laboral no especificada en la fila: " + fila);
                return false;
            }

            switch (situacionLaboral) {
                case "Trabajador dependiente - Sector Público":
                case "Trabajador dependiente - Sector Privado":
                    return !fila.getOrDefault("csvFechaAfiliacionAfp", "").isEmpty() &&
                            !fila.getOrDefault("csvCambioAfp", "").isEmpty() &&
                            !fila.getOrDefault("csvPensionado16744", "").isEmpty() &&
                            !fila.getOrDefault("csvLicenciaMedica", "").isEmpty();
                case "Trabajador Independiente":
                default:
                    System.err.println("Situación laboral desconocida: " + situacionLaboral);
                    return false;
            }
        } catch (Exception e) {
            System.err.println("Error al validar datos en índice " + indice + ": " + e.getMessage());
            return false;
        }
    }
}
