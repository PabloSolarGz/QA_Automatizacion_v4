package pageFactory.dao.solicitudes.ingreso;

import utils.Data;
import java.util.List;
import java.util.Map;

public class IngresoOtrosDatosSolicitudDao {

    private List<Map<String, String>> datos;
    private static final String RUTA_CSV = "src/test/resources/features/csv/solicitudes/ingreso/IngresoOtrosDatosSolicitud/datos_otros_datos.csv";

    public IngresoOtrosDatosSolicitudDao() {
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

            String seleccionOtros = fila.getOrDefault("csvSeleccion", "").trim();
            if (seleccionOtros.isEmpty()) {
                System.err.println("Selección no especificada en la fila: " + fila);
                return false;
            }

            switch (seleccionOtros) {
                case "sel1":
                    return !fila.getOrDefault("csvFechaRecepcionCapri", "").isEmpty() &&
                            !fila.getOrDefault("csvQuienVerifica", "").isEmpty() &&
                            !fila.getOrDefault("csvRequiereTercero", "").isEmpty() &&
                            !fila.getOrDefault("csvHaSufridoAccidenteEnfermedad", "").isEmpty() &&
                            !fila.getOrDefault("csvPorcentajeCargoAfiliado", "").isEmpty();
                case "sel2":
                    return !fila.getOrDefault("csvFechaRecepcionCapri", "").isEmpty() &&
                            !fila.getOrDefault("csvQuienVerifica", "").isEmpty() &&
                            !fila.getOrDefault("csvPensionado16744", "").isEmpty() &&
                            !fila.getOrDefault("csvHaSufridoAccidenteEnfermedad", "").isEmpty();
                default:
                    System.err.println("Selección desconocida: " + seleccionOtros);
                    return false;
            }
        } catch (Exception e) {
            System.err.println("Error al validar datos en índice " + indice + ": " + e.getMessage());
            return false;
        }
    }
}
