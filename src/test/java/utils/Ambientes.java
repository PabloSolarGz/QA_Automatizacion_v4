package utils;

public class Ambientes {
    public static String seleccionarUrl(String environment) {
        switch (environment.toLowerCase()) {
            case "qa":
                return "https://qa.sagcom.cl/acceso?from=/";
            case "uat":
                return "https://uat.sagcom.cl/acceso?from=/";
            case "dev":
                return "https://dev.sagcom.cl/acceso?from=/";
            case "perf":
                return "https://perf.sagcom.cl/acceso?from=/";
            case "int":
                return "https://int.sagcom.cl/acceso?from=/";
            case "cap":
                return "https://capacitacion.sagcom.cl/acceso?from=/";
            case "prd":
                return "https://sagcom.cl/acceso?from=/";
            default:
                throw new IllegalArgumentException("Ambiente no reconocido: " + environment);
        }
    }
}
