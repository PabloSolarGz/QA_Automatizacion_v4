package utils;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageFactory.dao.solicitudes.ingreso.IngresoInformacionSolicitanteDao;
import java.util.HashMap;
import java.util.Map;


public class Comisiones {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private IngresoInformacionSolicitanteDao ingresoDao;

    private String regionComision;
    private String provinciaComision;
    private String comunaComision;

    private static final Map<String, String[]> comisionMap = new HashMap<>();

    static {
        comisionMap.put("CMLANG", new String[]{"Biobío", "Biobío", "Los Ángeles"});
        comisionMap.put("CMMAGL", new String[]{"Magallanes y de la Antártica Chilena", "Magallanes", "Punta Arenas"});
        comisionMap.put("CMRANC", new String[]{"Libertador General Bernardo O'Higgins", "Cachapoal", "Codegua"});
    }

    public boolean setComisionPorCodigo(String codigoComision) {
        if (comisionMap.containsKey(codigoComision)) {
            String[] valores = comisionMap.get(codigoComision);
            regionComision = valores[0];
            provinciaComision = valores[1];
            comunaComision = valores[2];
            return true;
        } else {
            System.err.println("Código de comisión no encontrado: " + codigoComision);
            return false;
        }
    }

    public String getRegionComision() {
        return regionComision;
    }

    public String getProvinciaComision() {
        return provinciaComision;
    }

    public String getComunaComision() {
        return comunaComision;
    }

    public void imprimirValores() {
        System.out.println("Región: " + regionComision);
        System.out.println("Provincia: " + provinciaComision);
        System.out.println("Comuna: " + comunaComision);
    }



    String[] opcionRegion = {"Arica y Parinacota","Tarapacá","Antofagasta","Atacama","Coquimbo","Valparaiso","Metropolitana de Santiago","Libertador General Bernardo O'Higgins","Maule","Ñuble","Biobío","La Araucanía","Los Ríos","Los Lagos","Aysén del General Carlos Ibáñez del Campo","Magallanes y de la Antártica Chilena"};

    String[] listadoProvinciaAricaYParinacota = {"Arica","Parinacota"};

    String[] comunasArica = {"Arica","Camarones"};

    String[] comunasParinacota = {"Putre","General Lagos"};

    String[] listadoProvinciasTarapaca = {"Iquique","Tamarugal"};

    String[] comunasIquique = {"Iquique","Alto Hospicio"};

    String[] comunasTamarugal = {"Pozo Almonte","Camiña","Colchane","Huara","Pica"};

    String[] listadoProvinciasAntofagasta = {"Antofagasta","El Loa","Tocopilla"};

    String[] comunasAntofagasta = {"Antofagasta", "Mejillones", "Sierra Gorda", "Taltal"};

    String[] comunasElLoa = {"Calama", "Ollagüe", "San Pedro de Atacama"};

    String[] comunasTocopilla = {"Tocopilla", "María Elena"};

    String[] listadoProvinciasAtacama = {"Chañaral","Copiapó","Huasco"};

    String[] comunasCopiapo = {"Copiapó","Caldera","Tierra Amarilla"};

    String[] comunasChanaral = {"Chañaral","Diego de Almagro"};

    String[] comunasHuasco = {"Vallenar","Freirina","Huasco","Alto del Carmen"};

    String[] listadoProvinciaCoquimbo = {"Choapa","Elqui","Limarí"};

    String[] comunasElqui = {"La Serena","Coquimbo","Andacollo","La Higuera","Paihuano","Vicuña"};

    String[] comunasLimari = {"Ovalle","Combarbalá","Monte Patria","Punitaqui","Río Hurtado"};

    String[] comunasChoapa = {"Illapel","Canela","Los Vilos","Salamanca"};

    String[] listadoProvinciaValparaiso = {"Isla de Pascua","Los Andes","Petorca","Quillota","San Antonio","San Felipe de Aconcagua","Valparaíso","Marga Marga"};

    String[] comunasValparaiso = {"Valparaíso","Casablanca","Concón","Juan Fernández","Puchuncaví","Quintero","Viña del Mar"};

    String[] comunasMargaMarga = {"Quilpué","Limache","Olmué","Villa Alemana"};

    String[] comunasQuillota = {"Quillota","La Calera","Hijuelas","La Cruz","Nogales"};

    String[] comunasSanAntonio = {"San Antonio","Algarrobo","Cartagena","El Quisco","El Tabo","Santo Domingo"};

    String[] comunasSanFelipe = {"San Felipe","Catemu","Llaillay","Panquehue","Putaendo","Santa María"};

    String[] comunasLosAndes = {"Los Andes","Calle Larga","Rinconada","San Esteban"};

    String[] comunasIslaDePascua = {"Rapa Nui"};

    String[] listadoProvinciaMetropolitanaDeSantiago = {"Chacabuco","Cordillera","Maipo","Melipilla","Santiago","Talagante"};

    String[] comunasSantiago = {"Cerrillos", "Cerro Navia", "Conchalí", "El Bosque", "Estación Central","Huechuraba", "Independencia", "La Cisterna", "La Florida", "La Granja","La Pintana", "La Reina", "Las Condes", "Lo Barnechea", "Lo Espejo","Lo Prado", "Macul", "Maipú", "Ñuñoa", "Pedro Aguirre Cerda","Peñalolén", "Providencia", "Pudahuel", "Quilicura", "Quinta Normal","Recoleta", "Renca", "San Joaquín", "San Miguel", "San Ramón","Santiago", "Vitacura"};

    String[] comunasCordillera = {"Puente Alto","Pirque","San José de Maipo"};

    String[] comunasChacabuco = {"Colina","Lampa","Tiltil"};

    String[] comunasMaipo = {"Buin","Calera de Tango","Paine","San Bernardo"};

    String[] comunasMelipilla = {"Alhué","Curacaví","María Pinto","Melipilla","San Pedro"};

    String[] comunasTalagante = {"El Monte","Isla de Maipo","Padre Hurtado","Peñaflor","Talagante"};

    String[] listadoProvinciaLibertadorGeneralBernardoOHiggins = {"Cachapoal","Cardenal Caro","Colchagua"};

    String[] comunasCachapoal = {"Rancagua", "Codegua", "Coinco", "Coltauco", "Doñihue", "Graneros", "Las Cabras", "Machalí", "Malloa", "Mostazal", "Olivar", "Peumo", "Pichidegua", "Quinta de Tilcoco", "Rengo", "Requínoa", "San Vicente"};

    String[] comunasColchagua = {"San Fernando", "Chépica", "Chimbarongo", "Lolol", "Nancagua", "Palmilla", "Peralillo", "Placilla", "Pumanque", "Santa Cruz"};

    String[] comunasCardenalCaro = {"Pichilemu", "La Estrella", "Litueche", "Marchigüe", "Navidad", "Paredones"};

    String[] listadoProvinciaMaule = {"Cauquenes","Curicó","Linares","Talca"};

    String[] comunasTalca = {"Talca", "Constitución", "Curepto", "Empedrado", "Maule", "Pelarco", "Pencahue", "Río Claro", "San Clemente", "San Rafael"};

    String[] comunasLinares = {"Linares", "Colbún", "Longaví", "Parral", "Retiro", "San Javier", "Villa Alegre", "Yerbas Buenas"};

    String[] comunasCurico = {"Curicó", "Hualañé", "Licantén", "Molina", "Rauco", "Romeral", "Sagrada Familia", "Teno", "Vichuquén"};

    String[] comunasCauquenes = {"Cauquenes", "Chanco", "Pelluhue"};

    String[] listadoProvinciaNuble = {"Diguillín","Itata","Punilla"};

    String[] comunasDiguillin = {"Chillán", "Chillán Viejo", "Bulnes", "Cobquecura", "Coelemu", "Ninhue", "Pinto", "Quillón", "San Ignacio", "San Nicolás", "Yungay"};

    String[] comunasItata = {"Quirihue", "Cobquecura", "Coelemu", "Ninhue", "Ránquil", "Treguaco"};

    String[] comunasPunilla = {"San Carlos", "Ñiquén", "San Fabián", "San Nicolás"};

    String[] listadoProvinciaBiobio = {"Arauco","Biobío","Concepción"};

    String[] comunasConcepcion = {"Concepción", "Coronel", "Chiguayante", "Florida", "Hualqui", "Lota", "Penco", "San Pedro de la Paz", "Santa Juana", "Talcahuano", "Tomé", "Hualpén"};

    String[] comunasArauco = {"Arauco", "Cañete", "Contulmo", "Curanilahue", "Lebu", "Los Álamos", "Tirúa"};

    String[] comunasBiobio = {"Los Ángeles", "Antuco", "Cabrero", "Laja", "Mulchén", "Nacimiento", "Negrete", "Quilaco", "Quilleco", "San Rosendo", "Santa Bárbara", "Tucapel", "Yumbel"};

    String[] listadoProvinciaLaAraucania = {"Cautín","Malleco"};

    String[] comunasCautin = {"Temuco", "Carahue", "Cholchol", "Cunco", "Curarrehue", "Freire", "Galvarino", "Gorbea", "Lautaro", "Loncoche", "Melipeuco", "Nueva Imperial", "Padre Las Casas", "Perquenco", "Pitrufquén", "Puerto Saavedra", "Teodoro Schmidt", "Toltén", "Vilcún", "Villarrica"};

    String[] comunasMalleco = {"Angol", "Collipulli", "Curacautín", "Ercilla", "Lonquimay", "Los Sauces", "Lumaco", "Purén", "Renaico", "Traiguén", "Victoria"};

    String[] listadoProvinciaLosRios = {"Ranco","Valdivia"};

    String[] comunasValdivia = {"Valdivia", "Corral", "Lanco", "Los Lagos", "Máfil", "Mariquina", "Paillaco", "Panguipulli"};

    String[] comunasRanco = {"La Unión", "Futrono", "Lago Ranco", "Río Bueno"};

    String[] listadoProvinciaLosLagos = {"Chiloé","Llanquihue","Osorno","Palena"};

    String[] comunasLlanquihue = {"Puerto Montt", "Calbuco", "Cochamó", "Fresia", "Frutillar", "Los Muermos", "Llanquihue", "Maullín", "Puerto Varas"};

    String[] comunasChiloe = {"Castro", "Ancud", "Chonchi", "Curaco de Vélez", "Dalcahue", "Puqueldón", "Queilén", "Quellón", "Quemchi", "Quinchao"};

    String[] comunasOsorno = {"Osorno", "Puerto Octay", "Purranque", "Puyehue", "Río Negro", "San Juan de la Costa", "San Pablo"};

    String[] comunasPalena = {"Chaitén", "Futaleufú", "Hualaihué", "Palena"};

    String[] listadoProvinciaAysenDelGeneralCarlosIbANezDelCampo = {"Aisén","Capitán Prat","Coihaique","General Carrera"};

    String[] comunasCoyhaique = {"Coyhaique", "Lago Verde"};

    String[] comunasAysen = {"Aysén", "Cisnes", "Guaitecas"};

    String[] comunasGeneralCarrera = {"Chile Chico", "Río Ibáñez"};

    String[] comunasCapitanPrat = {"Cochrane", "O’Higgins", "Tortel"};

    String[] listadoProvinciaMagallanesYDeLaAntarticaChilena = {"Antártica","Magallanes","Tierra del Fuego","Última Esperanza"};

    String[] comunasMagallanes = {"Punta Arenas", "Laguna Blanca", "Río Verde", "San Gregorio"};

    String[] comunasUltimaEsperanza = {"Natales", "Torres del Paine"};

    String[] comunasTierraDelFuego = {"Porvenir", "Primavera", "Timaukel"};

    String[] comunasAntartica = {"Cabo de Hornos", "Antártica"};

    public String ComisionLosAngeles;

    public void comisionLosAngeles() {
        regionComision = "Biobío";
        provinciaComision = "Biobío";
        comunaComision = "Los Ángeles";
    }

    public void comisionMagallanes() {
        regionComision = "Magallanes y de la Antártica Chilena";
        provinciaComision = "Magallanes";
        comunaComision = "Punta Arenas";
    }

    public void comisionRancagua() {
        regionComision = "Libertador General Bernardo O'Higgins";
        provinciaComision = "Cachapoal";
        comunaComision = "Rancagua";
    }
}
