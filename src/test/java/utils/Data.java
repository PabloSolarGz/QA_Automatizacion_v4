package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

import com.opencsv.CSVReader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;

public class Data {
    private static final Random random = new Random();
    private static final int MIN_NOMBRE_LENGTH = 5;
    private static final int MAX_NOMBRE_LENGTH = 10;
    private static final int MIN_DOMINIO_LENGTH = 3;
    private static final int MAX_DOMINIO_LENGTH = 8;
    private static final String CARACTERES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String[] EXTENSIONES = {"com", "net", "org", "biz", "info"};

    private WebDriver driver;
    private Actions actions;
    private WebDriverWait wait;


    public static void main(String[] args) {
        System.out.println("Correo electrónico generado: " + generarCorreoElectronico());
    }

    public Data(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static String generateValidRut() {

        int rutNumber = random.nextInt(29000000) + 1000000;
        char dv = calculateDV(rutNumber);
        return rutNumber + "-" + dv;
    }

    private static char calculateDV(int rut) {
        int sum = 0;
        int multiplier = 2;

        for (int i = String.valueOf(rut).length() - 1; i >= 0; i--) {
            sum += (rut % 10) * multiplier;
            rut /= 10;
            multiplier++;
            if (multiplier > 7) {
                multiplier = 2;
            }
        }

        int remainder = sum % 11;
        return (remainder == 0) ? '0' : (remainder == 1) ? 'K' : (char) ('0' + (11 - remainder));
    }

    public static String generarCorreoElectronico() {
        Random random = new Random();
        String nombre = generarStringAleatorio(random, MIN_NOMBRE_LENGTH, MAX_NOMBRE_LENGTH);
        String dominio = generarStringAleatorio(random, MIN_DOMINIO_LENGTH, MAX_DOMINIO_LENGTH);
        String extension = EXTENSIONES[random.nextInt(EXTENSIONES.length)];

        return nombre + "@" + dominio + "." + extension;
    }

    private static String generarStringAleatorio(Random random, int minLength, int maxLength) {
        int length = minLength + random.nextInt(maxLength - minLength + 1);
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }
        return sb.toString();
    }

    public static void cargarArchivo(WebDriver driver, String elementId, String fileName) {
        String rutaArchivo = Paths.get("src", "test", "resources", "archivos", fileName).toAbsolutePath().toString();

        try {
            WebElement inputElement = driver.findElement(By.id(elementId));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.display='block';", inputElement);

            new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(inputElement));

            inputElement.sendKeys(rutaArchivo);
            System.out.println("Archivo cargado: " + rutaArchivo);

            Thread.sleep(500);

            inputElement = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
            js.executeScript("arguments[0].style.display='none';", inputElement);
            Thread.sleep(500);

        } catch (StaleElementReferenceException e) {
            System.err.println("Error: Elemento no encontrado o no está en el DOM actual después de cargar el archivo.");
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }

    public class numeroIntentoInstitucion {
        private static final String Caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private static final int Largo = 10;
        private static final SecureRandom RANDOM = new SecureRandom();

        public static String generadorIdentificador() {
            StringBuilder result = new StringBuilder(Largo);
            for (int i = 0; i < Largo; i++) {
                int index = RANDOM.nextInt(Caracteres.length());
                result.append(Caracteres.charAt(index));
            }
            return result.toString();
        }
    }

    public static String generarNumeroCelular() {
        StringBuilder numeroCelular = new StringBuilder();
        numeroCelular.append("9");
        for (int i = 0; i < 8; i++) {
            numeroCelular.append(random.nextInt(10));
        }
        return numeroCelular.toString();
    }

    public static String generarNumeroFijo() {
        StringBuilder numeroFijo = new StringBuilder();
        numeroFijo.append("9");
        for (int i = 0; i < 8; i++) {
            numeroFijo.append(random.nextInt(10));
        }
        return numeroFijo.toString();
    }

    private static final List<String> nombresHombres = Arrays.asList(
            "Mateo", "Sebastián", "Diego", "Lucas", "Gabriel",
            "Alejandro", "Pablo", "Martín", "Felipe", "Joaquín",
            "Simón", "Rafael", "Ricardo", "Emilio", "Fernando",
            "Hugo", "Iván", "Victor", "Santiago", "Nicolás",
            "Agustín", "Ramiro", "Tomás", "Manuel", "Cristian",
            "Alberto", "Bruno", "Leonardo", "Diego", "Hernán",
            "Javier", "Rodolfo", "Salvador", "Esteban", "Matías",
            "Gonzalo", "Mauricio", "Claudio", "Leonel", "Maximiliano",
            "Andrés", "Osvaldo", "Hernando", "Felipe", "Alonso",
            "Raimundo", "Tomasito", "Ezequiel", "Julio", "Pablo",
            "Agustin", "Sergio", "Cristian", "Eduardo", "Dario",
            "Oscar", "Julio", "Arturo", "Guillermo", "Carlos",
            "Joaquín", "Salomón", "Walter", "Tadeo", "Leandro",
            "Felipe", "Cristóbal", "Ramón", "Ricardo", "Félix",
            "Antonio", "Octavio", "René", "Alfonso", "Benjamín"
    );

    private static final List<String> nombresMujeres = Arrays.asList(
            "Sofía", "Valentina", "Isabella", "Camila", "Ana",
            "María", "Lucía", "Victoria", "Gabriela", "Diana",
            "Renata", "Martina", "Alba", "Claudia", "Laura",
            "Julia", "Paola", "Carla", "Nadia", "Marisol",
            "Florencia", "Emilia", "Ariana", "Sabrina", "Estefanía",
            "Inés", "Dafne", "María José", "Mía", "Valeria",
            "Elena", "Carmen", "Silvana", "Teresa", "Luciana",
            "Julieta", "Patricia", "Lourdes", "Gina", "Verónica",
            "Cristina", "Monserrat", "Irina", "Renata", "Paola",
            "Gabriela", "María Fernanda", "Elisa", "Ana María", "Nadia",
            "Carolina", "Natalia", "Susana", "Rocío", "Miranda",
            "Luna", "Alicia", "Sofía", "Marina", "Vanessa",
            "Maribel", "Cristal", "Bárbara", "Milagros", "Margarita",
            "Dulce", "Patricia", "Margarita", "Elisa", "Lola",
            "Anita", "Tatiana", "Noelia", "Angelina", "Valentina"
    );

    private static final List<String> primerApellido = Arrays.asList(
            "Pérez", "González", "López", "Sánchez", "Rodríguez",
            "Martínez", "García", "Fernández", "Torres", "Hernández",
            "Gómez", "Díaz", "Morales", "Cruz", "Vásquez",
            "Ramírez", "Mendoza", "Reyes", "Ortega", "Rojas",
            "Gonzales", "Núñez", "Ponce", "Salazar", "Castillo",
            "Aguilar", "Jara", "Medina", "Soto", "Paniagua",
            "Ceballos", "Chacón", "Linares", "Quintero", "Zúñiga",
            "Córdova", "López", "Martel", "Carrillo", "Cuéllar",
            "Sarmiento", "Rosales", "Araya", "Aguirre", "Palacios",
            "Ochoa", "Márquez", "Valdés", "Escobar", "Bermúdez",
            "Vega", "Cano", "Castro", "Becerra", "Hernández",
            "Arce", "Paz", "Hidalgo", "Camacho", "Ruiz",
            "Gutiérrez", "Salas", "Téllez", "Salazar", "Alvarado",
            "Pérez", "Peña", "Cáceres", "Sampayo", "Ocampo",
            "Cañete", "Arias", "Pizarro", "Serrano", "Espinoza"
    );

    private static final List<String> segundoApellido = Arrays.asList(
            "Pérez", "González", "López", "Sánchez", "Rodríguez",
            "Martínez", "García", "Fernández", "Torres", "Hernández",
            "Gómez", "Díaz", "Morales", "Cruz", "Vásquez",
            "Ramírez", "Mendoza", "Reyes", "Ortega", "Rojas",
            "Gonzales", "Núñez", "Ponce", "Salazar", "Castillo",
            "Aguilar", "Jara", "Medina", "Soto", "Paniagua",
            "Ceballos", "Chacón", "Linares", "Quintero", "Zúñiga",
            "Córdova", "López", "Martel", "Carrillo", "Cuéllar",
            "Sarmiento", "Rosales", "Araya", "Aguirre", "Palacios",
            "Ochoa", "Márquez", "Valdés", "Escobar", "Bermúdez",
            "Vega", "Cano", "Castro", "Becerra", "Hernández",
            "Arce", "Paz", "Hidalgo", "Camacho", "Ruiz",
            "Gutiérrez", "Salas", "Téllez", "Salazar", "Alvarado",
            "Pérez", "Peña", "Cáceres", "Sampayo", "Ocampo",
            "Cañete", "Arias", "Pizarro", "Serrano", "Espinoza"
    );

    private static final List<String> seleccionarNacionalidad = Arrays.asList(
            "Afgano", "Albanés", "Argelino", "Americano", "Andorrano",
            "Angoleño", "Antiguano", "Argentino", "Armenio", "Australiano",
            "Austriaco", "Azerbaiyano", "Bahamés", "Bareiní", "Bangladesí",
            "Barbadense", "Vasco", "Bielorruso", "Belga", "Beliceño",
            "Beninés", "Butanés", "Boliviano", "Bosnio", "Brasileño",
            "Británico", "Bruneano", "Búlgaro", "Burkinés", "Birmano",
            "Burundés", "Cabo-verdiano", "Camboyano", "Camerunés", "Canadiense",
            "Centroafricano", "Chadiano", "Chileno", "Chino", "Colombiano",
            "Comorense", "Congoleño", "Costarricense", "Croata", "Cubano",
            "Chipriota", "Checo", "Danés", "Yemení", "Dominicano",
            "Holandés", "Ecuatoriano", "Egipcio", "Salvadoreño", "Guineano",
            "Eritreo", "Estonio", "Etíope", "Fiyiano", "Finlandés",
            "Francés", "Gabonés", "Gambiano", "Georgiano", "Alemán",
            "Ghanés", "Griego", "Granadino", "Guatemalteco", "Guineano-Bisauense",
            "Guineano", "Haitiano", "Hondureño", "Húngaro", "Islandés",
            "Indio", "Indonesio", "Iraní", "Iraquí", "Irlandés",
            "Israeli", "Italiano", "Jamaicano", "Japonés", "Jordano",
            "Kazajo", "Keniata", "Kiribatiano", "Kuwaití", "Kirguís",
            "Laosiano", "Letón", "Libanés", "Lesotense", "Liberiano",
            "Libio", "Lituano", "Luxemburgués", "Macedonio", "Malgache",
            "Malawiano", "Malasio", "Maldivo", "Maliense", "Maltés",
            "Marshallés", "Mauriciano", "Mexicano", "Micronesio", "Moldavo",
            "Monaguense", "Mongol", "Montenegrino", "Marroquí", "Mozambiqueño",
            "Namibio", "Nauruano", "Nepalí", "Neozelandés", "Nicaragüense",
            "Nigerino", "Nigeriano", "Norcoreano", "Noruego", "Omaní",
            "Paquistaní", "Palauano", "Palestino", "Panameño", "Papú-Nuevo Guineano",
            "Paraguayo", "Peruano", "Filipino", "Polaco", "Portugués",
            "Qatarí", "Rumano", "Ruso", "Rwandés", "Sanluquense",
            "Vincentino", "Samoano", "Sanmarinense", "Sao Tomeano", "Saudi",
            "Escocés", "Senegalés", "Serbio", "Seychellense", "Sierra Leona",
            "Singapurense", "Eslovaco", "Esloveno", "Salomonense", "Somalí",
            "Sudafricano", "Surcoreano", "Español", "Sri Lankan", "Sudanés",
            "Surinamés", "Sueco", "Suizo", "Sirio", "Taiwanés",
            "Tajikistano", "Tanzano", "Tailandés", "Togolés", "Tonga",
            "Trinitense", "Tunecino", "Turco", "Turkmeno", "Tuvaluano",
            "Ugandés", "Ucraniano", "Emiratense", "Británico", "Americano",
            "Venezolano", "Vietnamita", "Galés", "Zambiano", "Zimbabuense"
    );

    private static final List<String> nombresCalles = Arrays.asList(
            "Av Las Torres", "Calle Los Almendros", "Av Libertador", "Calle San Martín", "Pasaje Los Pinos",
            "Calle Principal", "Camino Real", "Calle 21 de Mayo", "Calle La Serena", "Av Italia",
            "Calle Los Arces", "Av Los Héroes", "Calle La Paz", "Av Independencia", "Pasaje La Luna",
            "Callejón del Sol", "Calle del Bosque", "Av Los Andes", "Pasaje Los Sauces", "Callejón Las Flores",
            "Av Marítima", "Pasaje Nueva Esperanza", "Callejón Los Álamos", "Calle Las Rosas", "Av Argentina",
            "Callejón La Primavera", "Pasaje El Roble", "Av Costanera", "Callejón San Pedro", "Av Republica",
            "Callejón Del Valle", "Calle Los Claveles", "Av Pedro de Valdivia", "Pasaje Las Camelias",
            "Callejón San Diego", "Av El Salto", "Callejón San Carlos", "Av Las Condes", "Calle Los Crisantemos",
            "Callejón del Parque", "Pasaje Las Violetas", "Av OHiggins", "Callejón La Paloma", "Callejón Los Alamos",
            "Av La Reina", "Callejón El Sauce", "Callejón del Lago", "Calle La Viña", "Av La Dehesa",
            "Pasaje Las Hortensias", "Calle Los Aromos", "Pasaje Los Naranjos", "Av Las Palmeras",
            "Callejón Los Olivos", "Callejón de la Paz", "Av El Bosque", "Pasaje del Sol", "Callejón Los Cipreses",
            "Callejón de la Luna", "Pasaje Las Azaleas", "Av Las Flores", "Callejón del Rincón", "Callejón El Peumo",
            "Av Del Mar", "Callejón Los Canelos", "Callejón El Quillay", "Callejón de los Sueños", "Callejón Los Fresnos",
            "Av El Trebol", "Callejón de la Luz", "Callejón del Sauce", "Pasaje Los Boldos", "Callejón Las Magnolias",
            "Av Providencia", "Callejón Los Espinos", "Callejón Los Pinos", "Callejón del Bosque",
            "Pasaje Los Lirios", "Av Prat", "Callejón Las Buganvilias", "Callejón Los Laureles", "Callejón de la Estrella",
            "Av Kennedy", "Pasaje El Cedro", "Callejón Los Nogales", "Callejón La Esperanza",
            "Callejón Las Lilas", "Callejón El Ciprés", "Pasaje Las Palmas", "Callejón del Árbol", "Callejón El Almendro",
            "Callejón de las Rosas", "Av Santa Isabel", "Callejón Los Naranjos", "Callejón Las Gardenias",
            "Callejón Las Moras", "Pasaje Las Dalias", "Callejón Los Pimientos", "Av Holanda", "Pasaje Los Arándanos",
            "Callejón de la Primavera", "Pasaje Las Vías", "Callejón La Montaña", "Callejón Los Aromos"
    );

    private static final List<String> numeroCalle = Arrays.asList(
            "1234", "2345", "3456", "4567", "5678", "6789", "7890", "8901", "9012", "1023",
            "1123", "2234", "3345", "4456", "5567", "6678", "7789", "8890", "9901", "1012",
            "1314", "1415", "1516", "1617", "1718", "1819", "1920", "2021", "2122", "2223",
            "2324", "2425", "2526", "2627", "2728", "2829", "2930", "3031", "3132", "3233",
            "3334", "3435", "3536", "3637", "3738", "3839", "3940", "4041", "4142", "4243",
            "4344", "4445", "4546", "4647", "4748", "4849", "4950", "5051", "5152", "5253",
            "5354", "5455", "5556", "5657", "5758", "5859", "5960", "6061", "6162", "6263",
            "6364", "6465", "6566", "6667", "6768", "6869", "6970", "7071", "7172", "7273",
            "7374", "7475", "7576", "7677", "7778", "7879", "7980", "8081", "8182", "8283",
            "8384", "8485", "8586", "8687", "8788", "8889", "8990", "9091", "9192", "9293",
            "9394", "9495", "9596", "9697", "9798", "9899", "9900", "1001", "1101", "1201"
    );

    private static final List<String> tiposVivienda = Arrays.asList(
            "Casa", "Departamento"
    );

    private static final List<String> bloques = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "Sin Bloque"
    );

    private static final List<String> codigoPostal = Arrays.asList(
            "10001", "10002", "10003", "10004", "10005", "10006", "10007", "10008", "10009", "10010",
            "20001", "20002", "20003", "20004", "20005", "20006", "20007", "20008", "20009", "20010",
            "30001", "30002", "30003", "30004", "30005", "30006", "30007", "30008", "30009", "30010",
            "40001", "40002", "40003", "40004", "40005", "40006", "40007", "40008", "40009", "40010",
            "50001", "50002", "50003", "50004", "50005", "50006", "50007", "50008", "50009", "50010",
            "60001", "60002", "60003", "60004", "60005", "60006", "60007", "60008", "60009", "60010",
            "70001", "70002", "70003", "70004", "70005", "70006", "70007", "70008", "70009", "70010",
            "80001", "80002", "80003", "80004", "80005", "80006", "80007", "80008", "80009", "80010",
            "90001", "90002", "90003", "90004", "90005", "90006", "90007", "90008", "90009", "90010",
            "91001", "91002", "91003", "91004", "91005", "91006", "91007", "91008", "91009", "91010"
    );

    private static final List<String> nombresCondominios = Arrays.asList(
            "Condominio Los Arrayanes", "Condominio Altos de San Miguel", "Condominio Los Robles",
            "Condominio Bella Vista", "Condominio Santa Marta", "Condominio Vista Hermosa",
            "Condominio El Bosque", "Condominio Los Olivos", "Condominio San Rafael",
            "Condominio Las Palmeras", "Condominio Los Cerezos", "Condominio La Floresta",
            "Condominio Colinas del Sol", "Condominio Rincón del Valle", "Condominio Jardines del Sur",
            "Condominio Portal de la Reina", "Condominio Lomas de los Andes", "Condominio Las Brisas",
            "Condominio Camino Real", "Condominio Oasis del Parque", "Condominio Montes del Sol",
            "Condominio Los Jazmines", "Condominio Costa Verde", "Condominio Rincón de la Sierra",
            "Condominio Las Colinas", "Condominio Los Cedros", "Condominio Jardines del Este",
            "Condominio Mirador del Lago", "Condominio Alto Jardín", "Condominio El Refugio",
            "Condominio La Hacienda", "Condominio La Serena", "Condominio Bosques del Norte",
            "Condominio Las Campanas", "Condominio Los Pinares", "Condominio Campos Verdes",
            "Condominio Alto Horizonte", "Condominio Los Miradores", "Condominio Laguna Azul",
            "Condominio Monte Verde", "Condominio Vistas del Mar", "Condominio Portal de la Montaña",
            "Condominio Parque del Sol", "Condominio Rincón Alto", "Condominio Jardines de la Reina",
            "Condominio La Rosaleda", "Condominio Pinares del Este", "Condominio Colinas del Este",
            "Condominio Los Laureles", "Condominio Alturas del Sur", "Condominio Lago Verde",
            "Condominio La Cumbre", "Condominio El Prado", "Condominio Cumbres del Lago",
            "Condominio Altos de la Sierra", "Condominio Jardines del Norte", "Condominio Loma Linda",
            "Condominio San Antonio", "Condominio Las Cascadas", "Condominio Vista Azul",
            "Condominio Campos del Sol", "Condominio Montes Claros", "Condominio Los Sauces",
            "Condominio Mar de Cristal", "Condominio Praderas del Valle", "Condominio La Hacienda Real",
            "Condominio Oasis del Norte", "Condominio Los Tulipanes", "Condominio Puerta del Cielo",
            "Condominio Miradores del Este", "Condominio La Cañada", "Condominio Parque Central",
            "Condominio Vista Bonita", "Condominio Las Amapolas", "Condominio Bosques de la Reina",
            "Condominio Sierra Alta", "Condominio Los Laureles del Sur", "Condominio Alto Bosque",
            "Condominio Las Margaritas", "Condominio Bosques del Sol", "Condominio Puerto Real",
            "Condominio Las Orquídeas", "Condominio Rincón del Lago", "Condominio Cumbres de los Andes",
            "Condominio Mirador del Norte", "Condominio Monte Claro", "Condominio Vista del Sol",
            "Condominio El Roble", "Condominio Puertas de Andalucía", "Condominio El Molino",
            "Condominio Bosque Real", "Condominio Vistas del Norte", "Condominio Colinas del Mar",
            "Condominio Jardines del Lago", "Condominio Los Robles del Sur", "Condominio Puertas de Alcalá",
            "Condominio El Portal", "Condominio Los Pinos", "Condominio La Campiña",
            "Condominio El Rincón", "Condominio La Estancia", "Condominio Altos del Bosque"
    );

    private static final List<String> listadoProfesiones = Arrays.asList(
            "Ingeniero Civil", "Médico", "Abogado", "Contador", "Profesor", "Psicólogo", "Enfermero", "Arquitecto",
            "Ingeniero en Informática", "Desarrollador de Software", "Consultor de Negocios", "Químico", "Biotecnólogo",
            "Economista", "Veterinario", "Odontólogo", "Investigador Científico", "Periodista", "Marketing Digital",
            "Físico", "Analista de Datos", "Administrador de Empresas", "Geólogo", "Trabajador Social", "Chef",
            "Especialista en Recursos Humanos", "Ingeniero en Telecomunicaciones", "Mecánico", "Farmacéutico",
            "Analista Financiero", "Redactor", "Técnico en Laboratorio", "Piloto", "Productor de Cine", "Nutricionista",
            "Ingeniero Ambiental", "Músico", "Biólogo", "Agente de Seguros", "Ingeniero Químico", "Diseñador Gráfico",
            "Matemático", "Estadístico", "Agente de Bienes Raíces", "Terapeuta Físico", "Científico de Datos",
            "Administrador de Sistemas", "Artista Plástico", "Operador de Maquinaria", "Electricista", "Fotógrafo",
            "Ingeniero Electrónico", "Cirujano", "Soldador", "Cocinero", "Maestro de Obra", "Director de Marketing",
            "Programador", "Ingeniero de Producción", "Juez", "Ingeniero Industrial", "Traductor", "Químico Farmacéutico",
            "Fonoaudiólogo", "Investigador de Mercado", "Ingeniero de Redes", "Topógrafo", "Auditor", "Controlador de Calidad",
            "Decorador de Interiores", "Mecánico Automotriz", "Ingeniero Mecatrónico", "Gestor de Proyectos", "Locutor",
            "Asistente Administrativo", "Estilista", "Ingeniero Agrónomo", "Entrenador Personal", "Científico Forense",
            "Editor de Video", "Diseñador de Modas", "Ingeniero Biomédico", "Técnico en Electrónica", "Desarrollador Web",
            "Meteorólogo", "Ingeniero en Seguridad", "Psicopedagogo", "Ingeniero de Software", "Asistente Legal",
            "Jardinero", "Estadístico Actuarial", "Detective", "Ingeniero Naval", "Asistente Dental", "Investigador Forense",
            "Ingeniero en Energía", "Desarrollador de Aplicaciones Móviles", "Piloto Comercial", "Ingeniero de Sonido",
            "Científico de Materiales", "Radiólogo", "Ingeniero en Logística", "Historiador", "Bibliotecario",
            "Ingeniero Petrolero", "Arquitecto Paisajista"
    );

    private static final List<String> listadoRazonesSociales = Arrays.asList(
            "Soluciones Integrales SA", "Innova Tecnología Ltda", "Consultora Empresarial y Asociados",
            "Desarrollo Global SA", "Empresas Unidas SpA", "Red Comercial Internacional", "Grupo Logístico y Transporte",
            "Servicios Profesionales Ltda", "Ingeniería y Construcción SA", "Laboratorios y Ciencia SpA",
            "Distribuidora y Comercio Ltda", "Servicios Médicos Asociados", "Mercado Financiero Global SA",
            "Asesoría Estratégica Ltda", "Alimentos y Bebidas Corp", "Agrícola y Ganadera SpA",
            "Consultores Jurídicos Ltda", "Innovación Educativa SA", "Recursos Humanos Globales",
            "Tecnología Ambiental SpA", "Farmacéutica Integral Ltda", "Producción y Manufactura SA",
            "Servicios de Marketing Digital", "Inversiones y Capital SA", "Arquitectura y Diseño Ltda",
            "Estudios y Proyectos SpA", "Consultoría en Salud", "Educación para el Futuro SA",
            "Asociación de Comercio Internacional", "Agroindustrias Unidas Ltda", "Automotriz Global SA",
            "Biotecnología y Salud SpA", "Energía Renovable y Ambiental", "Finanzas Corporativas SA",
            "Consultoría de Negocios", "Productos Naturales Ltda", "Tecnología y Innovación SA",
            "Laboratorio Clínico Internacional", "Empresas de Seguridad SpA", "Reciclaje y Medio Ambiente",
            "Desarrollos Inmobiliarios Ltda", "Productos Químicos Globales", "Servicios de Transporte SA",
            "Consultores de Datos y Análisis", "Construcciones Sustentables SpA", "Asesoría Contable y Financiera",
            "Eventos y Producciones Ltda", "Equipos de Construcción SA", "Redes y Telecomunicaciones",
            "Instituto de Capacitación Profesional", "Investigación Científica Global", "Soluciones en Recursos Humanos",
            "Consultoría en Energía SA", "Restaurantes y Gastronomía SpA", "Asociados Médicos Internacional",
            "Agrícola y Forestal SA", "Distribución y Logística Ltda", "Consultores Ambientales Global",
            "Servicios de Calidad SpA", "Innovación y Diseño SA", "Automatización y Control Ltda",
            "Servicios Legales Internacionales", "Consultoría en Tecnología", "Consultores en Comercio",
            "Ingeniería Avanzada Ltda", "Desarrollo y Construcción SpA", "Grupo Inversionista SA",
            "Asociación de Productores", "Consultora de Imagen y Branding", "Centro de Estudios Financieros",
            "Industria Química Ltda", "Innovación y Salud SA", "Logística Global SpA",
            "Servicios Médicos Avanzados", "Marketing y Comunicación", "Desarrollos Tecnológicos Ltda",
            "Empresas de Recursos Naturales", "Automatización Industrial SpA", "Centro de Capacitación y Educación",
            "Corporación de Bienes Raíces", "Sistemas de Información SA", "Agronegocios Internacionales",
            "Arquitectura y Urbanismo", "Servicios de Auditoría Ltda", "Innovación Agropecuaria SpA",
            "Comercio Electrónico Global", "Consultoría Jurídica SA", "Ciencia y Tecnología Ltda",
            "Distribución y Venta SpA", "Red de Servicios Profesionales", "Empresas Agrícolas Unidas",
            "Productos Alimenticios SA", "Soluciones Ambientales Globales", "Consultoría Industrial SpA",
            "Comercio y Logística Internacional", "Consultora de Finanzas Globales", "Red Comercial SpA",
            "Consultores Ambientales Asociados", "Desarrollos Científicos Ltda", "Grupo de Servicios Integrales",
            "Empresas de Tecnología Avanzada", "Servicios y Asesoría Jurídica"
    );

    private static final List<String> entidadesAdministradoras = Arrays.asList(
            "Seguros Accidente Laboral SA", "Prevención y Salud Laboral Ltda", "Protección Integral SpA",
            "Salud y Seguridad Global SA", "Asociación de Prevención de Riesgos", "Protección Profesional Ltda",
            "Corporación Laboral y Salud", "Seguro Laboral de Chile SA", "Protección Segura SpA",
            "Prevención y Asistencia Laboral", "Seguridad Global SA", "Asociación de Salud y Trabajo",
            "Bienestar Laboral SpA", "Seguros y Servicios Laborales SA", "Prevención Total Ltda",
            "Protección para el Trabajador SpA", "Seguridad y Bienestar Laboral", "Asociación de Prevención SA",
            "Prevención y Protección Integral", "Seguridad en el Trabajo SA", "Laboral Plus SpA",
            "Protección y Cuidado Ltda", "Prevención Profesional SpA", "Protección Integral Laboral SA",
            "Riesgos y Seguridad Chile", "Salud y Protección en el Trabajo", "Laboral Segura SpA",
            "Seguridad en el Ámbito Laboral", "Prevención y Protección Chile Ltda", "Seguro Laboral y Bienestar SA",
            "Protección Laboral Total SpA", "Prevención Profesional Integral Ltda", "Salud y Bienestar SpA",
            "Prevención Integral y Servicios", "Seguro y Protección Laboral SA", "Prevención en Salud y Trabajo Ltda",
            "Protección Integral para Trabajadores", "Asociación de Seguridad Laboral", "Protección para Todos Ltda",
            "Bienestar en el Trabajo SA", "Salud y Seguridad Chile SpA", "Seguro y Protección Integral Ltda",
            "Asociación de Bienestar Laboral", "Salud y Seguridad en el Trabajo", "Protección Laboral Ltda",
            "Seguridad en Riesgos SA", "Bienestar y Protección Chile", "Prevención y Cuidado SpA",
            "Protección Total en el Trabajo", "Laboral Protegido Ltda", "Salud y Prevención SpA",
            "Protección y Seguridad Chile", "Asociación de Protección Integral", "Prevención Laboral SA",
            "Cuidado y Seguridad Integral Ltda", "Protección en el Ámbito Laboral", "Seguridad Laboral Global SpA",
            "Laboral Seguro y Protegido SA", "Prevención Total Chile Ltda", "Bienestar Laboral Integral SpA",
            "Protección Profesional en el Trabajo", "Seguro y Bienestar Laboral SA", "Prevención y Bienestar Chile",
            "Protección Laboral Integral Ltda", "Seguridad y Protección Laboral SpA", "Asociación de Bienestar Integral",
            "Prevención de Riesgos Chile", "Seguro y Protección Total Ltda", "Salud Laboral Chile SA",
            "Protección y Seguridad Integral", "Asociación de Riesgos y Seguridad", "Bienestar y Protección Integral",
            "Seguro y Prevención Laboral SA", "Protección Laboral Total Chile", "Asociación de Bienestar en el Trabajo",
            "Seguridad y Prevención Profesional", "Protección Integral y Bienestar", "Laboral Protegido Chile Ltda",
            "Prevención y Cuidado Chile", "Protección en el Trabajo SpA", "Asociación de Seguridad y Bienestar",
            "Seguridad Laboral Integral SA", "Prevención Profesional y Bienestar", "Cuidado y Bienestar Integral",
            "Protección Laboral Chile Ltda", "Seguridad y Bienestar Chile SpA", "Asociación de Prevención y Seguridad",
            "Protección Profesional Integral", "Bienestar Integral para Trabajadores", "Seguridad y Protección Total",
            "Prevención en el Trabajo Chile", "Asociación de Seguridad Integral", "Protección Total y Bienestar SpA",
            "Salud y Bienestar Laboral Ltda", "Protección Laboral y Bienestar", "Seguridad Profesional Chile",
            "Prevención y Seguridad Integral", "Protección Integral y Salud", "Asociación de Protección y Bienestar"
    );

    private static final List<String> listadoAfeccionesEnfermedades = Arrays.asList(
            "Lumbalgia", "Hernia discal", "Tendinitis", "Síndrome del túnel carpiano", "Epicondilitis",
            "Fractura de muñeca", "Fractura de tobillo", "Luxación de hombro", "Esguince cervical",
            "Artritis reumatoide", "Artrosis de rodilla", "Bursitis", "Dermatitis de contacto",
            "Asma ocupacional", "Silicosis", "Neumoconiosis", "Bronquitis crónica", "Neumonitis por hipersensibilidad",
            "Enfermedad pulmonar obstructiva crónica EPOC", "Asbestosis", "Cáncer de pulmón", "Cáncer de piel",
            "Sordera ocupacional", "Pérdida de audición inducida por ruido", "Síndrome de visión por computadora",
            "Lesión ocular por radiación UV", "Cataratas", "Conjuntivitis química", "Insomnio crónico",
            "Estrés laboral", "Depresión", "Ansiedad", "Trastorno de estrés postraumático", "Migraña",
            "Hernia inguinal", "Varices", "Fatiga crónica", "Gastritis", "Úlcera péptica",
            "Infección respiratoria aguda", "Hepatitis B", "Hepatitis C", "Dermatitis alérgica",
            "Alergia respiratoria", "Psoriasis", "Hipertensión arterial", "Diabetes tipo 2", "Obesidad",
            "Cálculos renales", "Insuficiencia renal crónica", "Hipotiroidismo", "Hipercolesterolemia",
            "Enfermedad de Parkinson", "Esclerosis múltiple", "Enfermedad de Crohn", "Colitis ulcerosa",
            "Fibromialgia", "Enfermedad pulmonar por COVID 19", "Fatiga posviral", "Osteoporosis",
            "Espondilitis anquilosante", "Síndrome de Raynaud", "Enfermedad de Lyme", "Alzheimer",
            "Demencia", "Aneurisma cerebral", "Esquizofrenia", "Trastorno bipolar", "Epilepsia",
            "Trastornos del sueño", "Trastorno obsesivo compulsivo TOC", "Síndrome de intestino irritable",
            "Reflujo gastroesofágico", "Hernia hiatal", "Enfermedad de MéniEre", "Sindrome de Sjogren",
            "Enfermedad de Wilson", "Insuficiencia cardíaca", "Infarto de miocardio", "Angina de pecho",
            "Arritmia", "Endometriosis", "Cáncer de mama", "Cáncer de próstata", "Cáncer gástrico",
            "Cáncer de colon", "Cáncer de hígado", "Leucemia", "Linfoma", "Mieloma múltiple",
            "Enfermedad de Hodgkin", "Sarcoidosis", "Hemofilia", "Trombosis venosa profunda",
            "Embolia pulmonar", "Enfermedad de Gaucher", "Esclerodermia", "Trastorno del espectro autista",
            "Síndrome de Down", "Trastorno límite de la personalidad", "Enfermedad celíaca", "Acné severo"
    );

    private static final List<String> listadoEntidadesPagadoras = Arrays.asList(
            "AFP Provida", "AFP Habitat", "AFP Capital", "AFP Cuprum", "AFP Modelo",
            "Banco de Chile", "Banco Santander", "Banco BCI", "Banco Estado", "Banco Itaú",
            "Banco Scotiabank", "Banco BBVA", "Banco Falabella", "Banco Security", "Banco Consorcio",
            "Isapre Colmena", "Isapre Consalud", "Isapre Cruz Blanca", "Isapre Banmédica", "Isapre Nueva Masvida",
            "Caja de Compensación Los Andes", "Caja de Compensación 18 de Septiembre", "Caja de Compensación La Araucana",
            "Caja de Compensación Gabriela Mistral", "Caja de Compensación Los Héroes",
            "Fondo Nacional de Salud FONASA", "Mutual de Seguridad", "ACHS Asociación Chilena de Seguridad",
            "ISL Instituto de Seguridad Laboral", "CCHC Cámara Chilena de la Construcción",
            "Dipreca Dirección de Previsión de Carabineros de Chile", "Capredena Caja de Previsión de la Defensa Nacional",
            "Fonasa Nivel A", "Fonasa Nivel B", "Fonasa Nivel C", "Fonasa Nivel D",
            "Corporación de Asistencia Judicial", "Defensoría Laboral", "Superintendencia de Pensiones",
            "Superintendencia de Salud", "Superintendencia de Seguridad Social",
            "Ministerio de Salud", "Ministerio del Trabajo y Previsión Social", "Ministerio de Justicia",
            "Ministerio de Desarrollo Social", "Ministerio de Educación", "Servicio de Salud Metropolitano Central",
            "Servicio de Salud Metropolitano Norte", "Servicio de Salud Metropolitano Sur",
            "Servicio de Salud Metropolitano Occidente", "Servicio de Salud Metropolitano Oriente",
            "Gobierno Regional de Valparaíso", "Gobierno Regional del Biobío", "Gobierno Regional de La Araucanía",
            "Gobierno Regional de Los Ríos", "Gobierno Regional de Los Lagos", "Gobierno Regional de Magallanes",
            "Gobierno Regional de Tarapacá", "Gobierno Regional de Antofagasta", "Gobierno Regional de Coquimbo",
            "Gobierno Regional de Atacama", "Gobierno Regional de Ñuble", "Gobierno Regional de Arica y Parinacota",
            "Fondo de Cesantía Solidario", "Fondo de Cesantía Individual", "Seguro de Desempleo",
            "Corporación Municipal de Educación", "Municipalidad de Santiago", "Municipalidad de Providencia",
            "Municipalidad de Maipú", "Municipalidad de Las Condes", "Municipalidad de La Florida",
            "Municipalidad de Viña del Mar", "Municipalidad de Valparaíso", "Municipalidad de Concepción",
            "Universidad de Chile", "Universidad Católica", "Universidad de Concepción",
            "Universidad Técnica Federico Santa María", "Universidad de Santiago de Chile",
            "Codelco Corporación Nacional del Cobre", "Enap Empresa Nacional del Petróleo", "Aguas Andinas",
            "Essbio", "Esval", "Eliqsa Empresa Eléctrica de Iquique", "Chilectra", "Endesa Chile",
            "Metro de Santiago", "Ferrocarriles del Estado EFE", "Correos de Chile",
            "Latam Airlines", "Sky Airline", "JetSMART", "Tur Bus", "Pullman Bus",
            "Salcobrand", "Farmacias Ahumada", "Farmacias Cruz Verde", "Paris", "Falabella",
            "Ripley", "Cencosud", "Sodimac", "Easy", "Lider"
    );

    private static final List<String> listadoAccidentesEnfermedades = Arrays.asList(
            "Caída desde una escalera al realizar labores de mantenimiento",
            "Exposición prolongada a ruidos fuertes generó pérdida auditiva",
            "Lesión lumbar al levantar una carga pesada en la bodega",
            "Corte en la mano al manipular herramientas sin protección",
            "Fractura de tobillo tras resbalar en superficie mojada",
            "Quemadura por contacto con equipo de soldadura caliente",
            "Golpe en la cabeza por caída de objetos desde altura",
            "Lesión en el hombro por esfuerzo repetitivo en embalaje",
            "Exposición a sustancias químicas causó irritación ocular",
            "Dolor crónico en muñeca debido al uso excesivo de teclado",
            "Tendinitis en codo tras manipular maquinaria repetidamente",
            "Inhalación de gases tóxicos durante proceso de limpieza",
            "Dermatitis por contacto con productos de limpieza",
            "Lesión en la rodilla al tropezar con un objeto en el pasillo",
            "Luxación de hombro al realizar tareas de carga manual",
            "Infección respiratoria por exposición a polvo en la obra",
            "Caída al pisar superficie resbaladiza en área de producción",
            "Golpe en la pierna al operar equipo de carga pesada",
            "Pérdida auditiva por exposición a maquinaria ruidosa",
            "Dolor cervical por mala postura durante largas jornadas",
            "Quemadura al manipular productos químicos corrosivos",
            "Alergia en la piel tras contacto con solventes industriales",
            "Lesión en espalda por levantar objetos sin equipo adecuado",
            "Fractura en brazo al tropezar con cables en el taller",
            "Esfuerzo repetitivo causó inflamación en tendones",
            "Corte en el pie al utilizar equipo de protección inadecuado",
            "Golpe en el hombro por caída de herramientas desde altura",
            "Intoxicación por inhalación de vapores químicos",
            "Dolor de rodilla por posturas forzadas en espacios reducidos",
            "Caída en escalera por falta de barandillas de seguridad",
            "Lesión en el cuello por impacto al manejar maquinaria",
            "Quemadura en brazo al trabajar con equipos de cocina",
            "Fatiga crónica debido a largas jornadas sin descanso",
            "Lesión en muñeca por esfuerzo repetitivo en producción",
            "Luxación en dedo al manipular piezas en ensamblaje",
            "Fractura en el pie al ser aplastado por un equipo pesado",
            "Deshidratación por trabajar en ambientes de alta temperatura",
            "Lesión ocular por exposición a radiación UV sin protección",
            "Dolor lumbar tras permanecer de pie por periodos prolongados",
            "Golpe en la espalda al caer por escaleras sin protección",
            "Dolor de codo por esfuerzo repetido en labores de ensamblaje",
            "Quemadura por vapor al trabajar con calderas industriales",
            "Lesión en pierna por golpe con carretilla de carga",
            "Corte en brazo al manipular láminas de metal sin guantes",
            "Fractura de costilla al caer durante labores en altura",
            "Exposición prolongada al frío causó dolor articular",
            "Intoxicación leve por manipulación de productos químicos",
            "Dolor crónico en la espalda baja por cargar objetos pesados",
            "Lesión en tobillo al tropezar en escalón defectuoso",
            "Dolor en muñeca por uso constante de herramientas manuales",
            "Infección respiratoria por exposición a materiales tóxicos",
            "Fractura de clavícula tras caída desde plataforma elevada",
            "Quemadura en mano por manipulación de líquidos calientes",
            "Golpe en cabeza por falta de casco de protección",
            "Dolor de espalda por postura inadecuada en escritorio",
            "Esfuerzo repetitivo causó tendinitis en hombro derecho",
            "Corte en dedo por manipulación de herramientas afiladas",
            "Dolor de cuello por estar encorvado en escritorio",
            "Lesión en la cadera al levantar peso excesivo",
            "Quemadura en brazo por exposición a llamas en cocina",
            "Deshidratación tras trabajar al sol sin protección adecuada",
            "Dolor en las rodillas por trabajo constante en cuclillas",
            "Golpe en la mano al manejar maquinaria pesada",
            "Fatiga visual por uso prolongado de pantallas",
            "Luxación de dedo al manipular materiales de construcción",
            "Fractura en muñeca tras caída desde escalerilla",
            "Intoxicación leve por contacto con químicos en laboratorio",
            "Lesión en hombro al levantar objetos sin ayuda",
            "Caída en pasillo por suelo mojado sin señalización",
            "Quemadura en rostro por salpicadura de productos químicos",
            "Lesión en la espalda por cargar sacos de cemento",
            "Dolor crónico en las piernas por estar de pie todo el día",
            "Dolor de cabeza constante por exposición a ruidos fuertes",
            "Lesión en el tobillo al resbalar en superficie con grasa",
            "Corte profundo en pierna al manejar equipo de corte",
            "Golpe en la cadera al tropezar en pasillo estrecho",
            "Dolor en hombro por uso constante de herramientas pesadas",
            "Fractura en mano al golpear objeto al trabajar",
            "Infección cutánea por contacto con sustancias corrosivas",
            "Esfuerzo físico causó desgarro muscular en la espalda",
            "Dolor lumbar crónico tras levantar cargas pesadas",
            "Lesión en el cuello por uso constante de auriculares",
            "Quemadura leve en mano al manipular equipo caliente",
            "Fatiga extrema por largas jornadas sin descanso",
            "Dolor en rodilla por trabajo en superficies duras",
            "Golpe en la frente al caer herramienta desde altura",
            "Lesión en la muñeca al trabajar con martillo neumático",
            "Dolor crónico en hombro por tareas repetitivas",
            "Corte en pierna al manipular vidrio sin protección",
            "Desgarro muscular en el brazo al cargar equipos",
            "Caída al pisar superficie irregular en obra",
            "Inhalación de polvo causó problemas respiratorios",
            "Dolor de espalda tras trabajar en posiciones incómodas",
            "Quemadura leve en pierna por salpicadura de aceite",
            "Luxación en el tobillo al tropezar con cables sueltos",
            "Dolor en la columna por mala postura constante"
    );

    private static final List<String> listadoEntidadesEvaluadoras = Arrays.asList(
            "AFP Provida", "AFP Habitat", "AFP Capital", "AFP Cuprum", "AFP Modelo",
            "Banco de Chile", "Banco Santander", "Banco BCI", "Banco Estado", "Banco Itaú",
            "Banco Scotiabank", "Banco BBVA", "Banco Falabella", "Banco Security", "Banco Consorcio",
            "Isapre Colmena", "Isapre Consalud", "Isapre Cruz Blanca", "Isapre Banmédica", "Isapre Nueva Masvida",
            "Caja de Compensación Los Andes", "Caja de Compensación 18 de Septiembre", "Caja de Compensación La Araucana",
            "Caja de Compensación Gabriela Mistral", "Caja de Compensación Los Héroes",
            "Fondo Nacional de Salud FONASA", "Mutual de Seguridad", "ACHS Asociación Chilena de Seguridad",
            "ISL Instituto de Seguridad Laboral", "CCHC Cámara Chilena de la Construcción",
            "Dipreca Dirección de Previsión de Carabineros de Chile", "Capredena Caja de Previsión de la Defensa Nacional",
            "Fonasa Nivel A", "Fonasa Nivel B", "Fonasa Nivel C", "Fonasa Nivel D",
            "Corporación de Asistencia Judicial", "Defensoría Laboral", "Superintendencia de Pensiones",
            "Superintendencia de Salud", "Superintendencia de Seguridad Social",
            "Ministerio de Salud", "Ministerio del Trabajo y Previsión Social", "Ministerio de Justicia",
            "Ministerio de Desarrollo Social", "Ministerio de Educación", "Servicio de Salud Metropolitano Central",
            "Servicio de Salud Metropolitano Norte", "Servicio de Salud Metropolitano Sur",
            "Servicio de Salud Metropolitano Occidente", "Servicio de Salud Metropolitano Oriente",
            "Gobierno Regional de Valparaíso", "Gobierno Regional del Biobío", "Gobierno Regional de La Araucanía",
            "Gobierno Regional de Los Ríos", "Gobierno Regional de Los Lagos", "Gobierno Regional de Magallanes",
            "Gobierno Regional de Tarapacá", "Gobierno Regional de Antofagasta", "Gobierno Regional de Coquimbo",
            "Gobierno Regional de Atacama", "Gobierno Regional de Ñuble", "Gobierno Regional de Arica y Parinacota",
            "Fondo de Cesantía Solidario", "Fondo de Cesantía Individual", "Seguro de Desempleo",
            "Corporación Municipal de Educación", "Municipalidad de Santiago", "Municipalidad de Providencia",
            "Municipalidad de Maipú", "Municipalidad de Las Condes", "Municipalidad de La Florida",
            "Municipalidad de Viña del Mar", "Municipalidad de Valparaíso", "Municipalidad de Concepción",
            "Universidad de Chile", "Universidad Católica", "Universidad de Concepción",
            "Universidad Técnica Federico Santa María", "Universidad de Santiago de Chile",
            "Codelco Corporación Nacional del Cobre", "Enap Empresa Nacional del Petróleo", "Aguas Andinas",
            "Essbio", "Esval", "Eliqsa Empresa Eléctrica de Iquique", "Chilectra", "Endesa Chile",
            "Metro de Santiago", "Ferrocarriles del Estado EFE", "Correos de Chile",
            "Latam Airlines", "Sky Airline", "JetSMART", "Tur Bus", "Pullman Bus",
            "Salcobrand", "Farmacias Ahumada", "Farmacias Cruz Verde", "Paris", "Falabella",
            "Ripley", "Cencosud", "Sodimac", "Easy", "Lider"
    );

    private static final List<String> listadoLugaresReposoEspecificos = Arrays.asList(
            "Clinica Alemana - Reposo en ambiente privado y seguro",
            "Centro de Rehabilitacion Teleton - Cuidados intensivos y fisioterapia",
            "Hospital Militar - Reposo con atencion medica especializada",
            "Centro Medico San Carlos - Tranquilidad y atencion medica personalizada",
            "Clinica Las Condes - Instalaciones comodas y modernas para el descanso",
            "Residencia Vista Hermosa - Reposo con vista al mar",
            "Sanatorio Aleman - Atencion personalizada en un entorno sereno",
            "Casa de Reposo Los Aromos - Ambiente natural y acogedor",
            "Centro de Cuidados El Refugio - Atencion personalizada las 24 horas",
            "Clinica Santa Maria - Reposo en habitaciones privadas",
            "Hogar Los Girasoles - Cuidados geriatricos en un entorno familiar",
            "Casa de Salud La Esperanza - Reposo con atencion medica constante",
            "Centro Medico Los Cedros - Especializado en enfermedades respiratorias",
            "Residencia Los Robles - Cuidados postoperatorios y descanso",
            "Clinica del Maule - Recuperacion en un entorno tranquilo",
            "Casa de Reposo Los Alamos - Especializado en cuidados paliativos",
            "Centro de Salud El Palmar - Reposo con acceso a terapias alternativas",
            "Clinica Regional - Cuidados especializados para adultos mayores",
            "Hospital de Dia El Sol - Reposo diurno con terapias de apoyo",
            "Casa de Reposo Los Pinos - Reposo prolongado en un ambiente seguro",
            "Centro Integral de Salud - Cuidados medicos y rehabilitacion",
            "Residencia La Montana - Entorno natural para un reposo reparador",
            "Clinica Las Brisas - Habitaciones privadas con atencion medica",
            "Centro Medico El Alba - Reposo para enfermedades cronicas",
            "Sanatorio La Paz - Cuidados de largo plazo en un ambiente pacifico",
            "Casa de Reposo El Rincon - Reposo y cuidados paliativos",
            "Centro de Reposo Los Jazmines - Terapias de relajacion y descanso",
            "Clinica Los Andes - Reposo postoperatorio en un ambiente seguro",
            "Hogar de Salud La Union - Cuidados continuos para adultos mayores",
            "Centro de Rehabilitacion La Vida - Reposo y fisioterapia intensiva",
            "Casa de Reposo El Paraiso - Atencion medica y tranquilidad",
            "Clinica San Cristobal - Reposo especializado para pacientes cronicos",
            "Centro Medico La Serena - Instalaciones modernas para el descanso",
            "Hospital de Recuperacion Los Laureles - Cuidados especializados",
            "Hogar Los Tilos - Reposo prolongado para adultos mayores",
            "Centro de Salud El Bosque - Atencion medica en un ambiente relajante",
            "Residencia La Primavera - Cuidados postoperatorios especializados",
            "Casa de Reposo El Mirador - Reposo con vista panoramica",
            "Clinica del Sur - Recuperacion en un ambiente de paz",
            "Centro de Reposo Los Cipreses - Atencion medica constante",
            "Casa de Reposo Los Claveles - Reposo prolongado con atencion medica",
            "Centro Medico El Cielo - Atencion integral para el descanso",
            "Hogar La Nube - Cuidados especializados en un entorno acogedor",
            "Casa de Reposo El Sauce - Atencion continua en un ambiente sereno",
            "Centro de Reposo El Roble - Reposo y rehabilitacion intensiva",
            "Clinica Los Olivos - Reposo en habitaciones privadas",
            "Sanatorio La Luz - Cuidados paliativos en un entorno tranquilo",
            "Hogar La Cumbre - Reposo prolongado con terapias alternativas",
            "Centro Medico El Alamo - Recuperacion en un ambiente natural",
            "Casa de Reposo La Colina - Reposo en un entorno campestre",
            "Clinica Los Pinos - Cuidados intensivos y descanso",
            "Hogar de Salud La Estrella - Reposo prolongado con atencion medica",
            "Residencia El Prado - Reposo en un entorno de paz",
            "Centro Medico El Arrayan - Recuperacion y descanso",
            "Clinica del Norte - Reposo en un entorno de tranquilidad",
            "Hogar Los Angeles - Cuidados continuos para pacientes cronicos",
            "Casa de Reposo El Trigal - Atencion personalizada las 24 horas",
            "Sanatorio Los Eucaliptos - Reposo y rehabilitacion en un ambiente seguro",
            "Centro de Reposo La Cordillera - Reposo especializado en geriatria",
            "Clinica El Faro - Reposo en un entorno maritimo",
            "Hogar La Casona - Cuidados prolongados en un entorno familiar",
            "Centro Medico La Aurora - Recuperacion en un ambiente relajante",
            "Casa de Reposo Los Naranjos - Reposo en habitaciones privadas",
            "Clinica Los Aromos - Reposo postoperatorio y recuperacion",
            "Sanatorio El Valle - Reposo en un entorno natural",
            "Hogar La Rosa - Reposo prolongado con cuidados medicos",
            "Centro de Reposo El Estero - Atencion medica personalizada",
            "Clinica del Valle - Recuperacion en un entorno campestre",
            "Casa de Reposo El Encanto - Cuidados especializados y descanso",
            "Residencia El Sauce - Reposo prolongado para adultos mayores",
            "Centro de Salud El Sol - Atencion continua en un entorno sereno",
            "Clinica La Fuente - Reposo en un entorno de tranquilidad",
            "Hogar El Paraiso - Reposo prolongado con atencion medica",
            "Centro de Reposo Los Alamos - Cuidados y descanso en un entorno natural",
            "Casa de Reposo El Rosedal - Reposo prolongado y rehabilitacion",
            "Clinica La Esperanza - Reposo en un entorno seguro",
            "Sanatorio El Laurel - Cuidados paliativos especializados",
            "Hogar de Salud El Roble - Atencion continua en un entorno relajante",
            "Centro de Rehabilitacion La Luz - Reposo y recuperacion intensiva",
            "Residencia El Refugio - Reposo prolongado en un ambiente seguro",
            "Casa de Reposo El Cipres - Cuidados continuos para adultos mayores",
            "Clinica La Mar - Reposo en un entorno maritimo",
            "Centro Medico La Palma - Reposo especializado en geriatria",
            "Hogar Los Naranjos - Reposo prolongado con atencion medica"
    );

    public static List<Map<String, String>> cargarCSV(String rutaArchivo) {
        List<Map<String, String>> filas = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            String[] cabeceras = reader.readNext(); // Leer encabezados

            if (cabeceras == null) {
                System.err.println("El archivo CSV no tiene encabezados.");
                return filas;
            }

            String[] linea;
            while ((linea = reader.readNext()) != null) {
                if (linea.length != cabeceras.length) {
                    System.err.println("Fila con cantidad incorrecta de columnas: " + Arrays.toString(linea));
                    continue;
                }

                Map<String, String> fila = new HashMap<>();
                for (int i = 0; i < cabeceras.length; i++) {
                    fila.put(cabeceras[i].trim(), linea[i].trim());
                }
                filas.add(fila);
            }
        } catch (Exception e) {
            System.err.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        return filas;
    }


    public static String generarNombreCompuesto() {
        String nombre;

        if (random.nextBoolean()) {
            nombre = getNombresHombres();
        } else {
            nombre = getNombresMujeres();
        }

        String primerApellido = obtenerPrimerApellidoAleatorio();
        String segundoApellido = obtenerSegundoApellidoAleatorio();

        return nombre + " " + primerApellido + " " + segundoApellido;
    }

    public static String obtenerPrimerApellidoAleatorio() {
        int indice = random.nextInt(primerApellido.size());
        return primerApellido.get(indice);
    }

    public static String obtenerSegundoApellidoAleatorio() {
        int indice = random.nextInt(segundoApellido.size());
        return segundoApellido.get(indice);
    }

    public static String obtenerNacionalidad() {
        int indice = random.nextInt(seleccionarNacionalidad.size());
        return seleccionarNacionalidad.get(indice);
    }

    public static String getNombresHombres() {
        return nombresHombres.get(random.nextInt(nombresHombres.size()));
    }

    public static String getNombresMujeres() {
        return nombresMujeres.get(random.nextInt(nombresMujeres.size()));
    }

    public static String getNacionalidad() {
        return seleccionarNacionalidad.get(random.nextInt(seleccionarNacionalidad.size()));
    }

    public static String getObtenerCalle() {
        return nombresCalles.get(random.nextInt(nombresCalles.size()));
    }

    public static String getObtenerNumero() {
        return numeroCalle.get(random.nextInt(numeroCalle.size()));
    }

    public static String getObtenerTipoVivienda() {
        return tiposVivienda.get(random.nextInt(tiposVivienda.size()));
    }

    public static String getObtenerblock() {
        return bloques.get(random.nextInt(bloques.size()));
    }

    public static String getObtenerCodigoPostal() {
        return codigoPostal.get(random.nextInt(codigoPostal.size()));
    }

    public static String getObtenerCondominio() {
        return nombresCondominios.get(random.nextInt(nombresCondominios.size()));
    }

    public static String getObtenerProfesion() {
        return listadoProfesiones.get(random.nextInt(listadoProfesiones.size()));
    }

    public static String getListadoRazonesSociales() {
        return listadoRazonesSociales.get(random.nextInt(listadoRazonesSociales.size()));
    }

    public static String getEntidadesAdministradoras() {
        return entidadesAdministradoras.get(random.nextInt(entidadesAdministradoras.size()));
    }

    public static String getListadoAfeccionesEnfermedades() {
        return listadoAfeccionesEnfermedades.get(random.nextInt(listadoAfeccionesEnfermedades.size()));
    }

    public static String getListadoEntidadesPagadoras() {
        return listadoEntidadesPagadoras.get(random.nextInt(listadoEntidadesPagadoras.size()));
    }

    public static String getListadoAccidentesEnfermedades() {
        return listadoAccidentesEnfermedades.get(random.nextInt(listadoAccidentesEnfermedades.size()));
    }

    public static String getListadoEntidadesEvaluadoras() {
        return listadoEntidadesEvaluadoras.get(random.nextInt(listadoEntidadesEvaluadoras.size()));
    }

    public static String getListadoLugaresReposoEspecificos() {
        return listadoLugaresReposoEspecificos.get(random.nextInt(listadoLugaresReposoEspecificos.size()));
    }
}
