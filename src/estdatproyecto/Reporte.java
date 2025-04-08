package estdatproyecto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 * Clase reporte encargada de consultar y mostrar información relacionada con
 * los tiquetes atendidos. Los datos se obtienen desde un archivo de texto
 * llamado "reportes.txt", donde se almacenan los reportes generados.
 */
public class Reporte {

    /**
     * Consulta y muestra todos los reportes de tiquetes atendidos almacenados
     * en el archivo "reportes.txt". Si el archivo está vacío o no existen
     * reportes, se notifica al usuario.
     *
     * @param cantidadCajas Número total de cajas en el banco.
     */
    public void consultar(int cantidadCajas) {
        StringBuilder mensaje = new StringBuilder();
        try {
            //Abrir el archivo de reportes
            BufferedReader br = new BufferedReader(new FileReader("reportes.txt"));
            String linea;

            //Leer el archivo línea por línea
            while ((linea = br.readLine()) != null) {
                mensaje.append(linea).append("\n");
            }

            if (mensaje.length() == 0) {
                JOptionPane.showMessageDialog(null, "No hay reportes disponibles.", "Reportes", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, mensaje.toString(), "Reporte de tiquetes", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al consultar los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Cuenta y muestra la cantidad total de líneas en el archivo "reportes.txt"
     * que presentan los tiquetes atendidos.
     */
    public void totalClientesAtendidos() {
        int totalClientes = 0;

        try {
            //Abrir el archivo de reportes
            BufferedReader br = new BufferedReader(new FileReader("reportes.txt"));
            String linea;

            //Contar todas las líneas no vacías
            while ((linea = br.readLine()) != null) {
                if (linea.length() > 0) {
                    totalClientes++;
                }
            }
            JOptionPane.showMessageDialog(null, "Total de clientes atendidos: " + totalClientes, "Informacion", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer los datos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
