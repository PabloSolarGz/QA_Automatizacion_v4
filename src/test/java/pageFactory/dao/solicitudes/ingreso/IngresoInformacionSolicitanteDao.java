package pageFactory.dao.solicitudes.ingreso;

import utils.Data;
import java.util.List;
import java.util.Map;

public class IngresoInformacionSolicitanteDao {
    private List<Map<String, String>> datos;
    private static final String RUTA_CSV = "src/test/resources/features/csv/solicitudes/ingreso/ingresoInformacionSolicitante/datos_ingreso.csv";

    public IngresoInformacionSolicitanteDao() {
        cargarDatosCSV();
    }

    private void cargarDatosCSV() {
        this.datos = Data.cargarCSV(RUTA_CSV);
    }

    public int obtenerNumeroFilas() {
        return datos.size();
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

            String tipoSolicitud = fila.getOrDefault("csvTipoSolicitud", "").trim();
            if (tipoSolicitud.isEmpty()) {
                System.err.println("Tipo de solicitud no especificado en la fila: " + fila);
                return false;
            }

            switch (tipoSolicitud) {
                case "Calificación de invalidez de beneficiario de un afiliado":
                    return !fila.getOrDefault("csvFechaRecepcionSolicitud", "").isEmpty() &&
                            !fila.getOrDefault("csvFechaNacimiento", "").isEmpty();
                case "Calificación de invalidez para PBSI":
                    return !fila.getOrDefault("csvFechaNacimiento", "").isEmpty() &&
                            !fila.getOrDefault("csvSexo", "").isEmpty();
                case "Calificación de invalidez de un afiliado":
                    return !fila.getOrDefault("csvFechaNacimiento", "").isEmpty() &&
                            !fila.getOrDefault("csvComision", "").isEmpty();
                default:
                    System.err.println("Tipo de solicitud desconocido: " + tipoSolicitud);
                    return false;
            }
        } catch (Exception e) {
            System.err.println("Error al validar datos en índice " + indice + ": " + e.getMessage());
            return false;
        }
    }


}
