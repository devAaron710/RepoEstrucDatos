package estdatproyecto;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class EstDatProyecto {

    public static void main(String[] args) throws IOException {
        //Se crea la configuracion inicial del banco
        Banco banco = new Banco();
        banco.crearTXT();
        
       Cola cola = new Cola();

        boolean continuar = true;

        while (continuar) {
            // Mostrar menú de opciones
            String opcion = JOptionPane.showInputDialog(
                "Seleccione una opción:\n" +
                "1. Crear tiquete\n" +
                "2. Atender tiquete\n" +
                "3. Mostrar cola\n" +
                "4. Salir"
            );

            switch (opcion) {
                case "1": // Crear tiquete
                    // Solicitar datos del tiquete al usuario
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el ID del cliente:"));
                    int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente:"));
                    char tipo = JOptionPane.showInputDialog("Ingrese el tipo (P: preferencial, A: un solo trámite, B: dos o más trámites):").charAt(0);
                    int tramite = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el trámite (1: Depósitos, 2: Retiros, 3: Cambio de Divisas):"));

                    // Crear un nuevo tiquete con los datos ingresados
                    Tiquete tiquete = new Tiquete(nombre, id, edad, tipo, tramite);

                    // Insertar el tiquete en la cola
                    cola.insertar(tiquete);
                    break;

                case "2": // Atender tiquete
                    // Atender el primer tiquete y mostrar el estado de la cola
                    cola.atender();
                    break;

                case "3": // Mostrar cola
                    // Mostrar el estado de la cola
                    JOptionPane.showMessageDialog(null, cola.toString());
                    break;

                case "4": // Salir
                    continuar = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        }
        //System.out.println(ScrapingWeb.getHTML("https://servicios.davivienda.cr/master/v1/davicotizador/"));
        //new ScrapingWeb().scraping();
    }
}
