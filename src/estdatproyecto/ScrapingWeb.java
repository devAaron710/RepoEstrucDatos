package estdatproyecto;

import javax.swing.JOptionPane;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author kaaraya
 */
public class ScrapingWeb {

    /*Jsoup es una libreria para hacer web scraping en Java 
    Metodo para obtener el HTML de una pagina
     */
    public static Document getHTML(String url) { //Metodo estatico que recibe una URL
        Document html = null; //Se inicia la variable nula para almacenar
        try {
            html = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get(); //Usa el Jsoup.connect para conectarse a la URL
            Element body = html.body(); //Obtiene el 'cuerpo' del HTML
        } catch (Exception e) {
            System.out.println("Error al obtener el codigo HTML");
        }
        return html;
    }

    public void scraping() {
        Elements articulos = ScrapingWeb.getHTML("https://servicios.davivienda.cr/master/v1/davicotizador/").select("tbody tr");
        /* 'select ("tbody tr") selecciona todas las filas <tr> dentro del <tbody> de la pagina 
        Almacenamos las filas en la variable 'articulos'*/
        StringBuilder resultado = new StringBuilder("Moneda - Compra - Venta\n");
        for (Element fila : articulos) {
            /*Itera sobre cada fila de la tabla*/
            try {

                String moneda = fila.select("td:nth-child(2)").text();
                String compra = fila.select("td:nth-child(3)").text();
                String venta = fila.select("td:nth-child(4)").text();
                /*Extrae los valores de la tabla: segunda columna es la moneda, tercera es el valor de compra y cuarta es el valor de venta*/

                resultado.append(moneda).append(" - ").append(compra).append(" - ").append(venta).append("\n");
            } catch (Exception e) {
                resultado.append("Error al cargar los datos\n");
            }
        }
        JOptionPane.showMessageDialog(null, resultado.toString(), "Cotizacion de Monedas", JOptionPane.INFORMATION_MESSAGE);
    }
}
