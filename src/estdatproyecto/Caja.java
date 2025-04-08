package estdatproyecto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author kaaraya
 */
public class Caja {

    /**
     * Arreglo que representa las colas de cada caja del banco.
     */
    private Cola[] cajas;

    /**
     * Número total de cajas en el banco.
     */
    private int cantidadCajas;

    /**
     * Constructor que inicializa las cajas con colas vacías.
     *
     * @param cantidadCajas Número total de cajas disponibles.
     */
    public Caja(int cantidadCajas) {
        this.cantidadCajas = cantidadCajas;
        cajas = new Cola[cantidadCajas + 1]; // Se comienza desde la posición 1
        for (int i = 1; i <= cantidadCajas; i++) {
            cajas[i] = new Cola();
        }
    }

    /**
     * Inserta un tiquete en la caja correspondiente según su tipo ('P', 'A',
     * 'B'). El número de caja es asignado al tiquete antes de insertarlo.
     *
     * @param t El tiquete a insertar
     */
    public void insertarTiquete(Tiquete t) {
        int caja = asignarCaja(t.getTipo());
        t.setNumeroCaja(caja); // Guardamos el número de caja en el tiquete
        cajas[caja].insertar(t);

        JOptionPane.showMessageDialog(null, "Tiquete asignado a caja #" + caja + ". Personas en fila: " + (cajas[caja].contar() - 1));
    }

    /**
     * Atiende el primer tiquete en la cola de la caja indicada. Registra la
     * hora de atención y guarda el tiquete en el reporte.
     *
     * @param numeroCaja Número de la caja que va a atender un tiquete.
     */
    public void atenderTiquete(int numeroCaja) {
        if (numeroCaja < 1 || numeroCaja > cantidadCajas) {
            JOptionPane.showMessageDialog(null, "Número de caja inválido.");
            return;
        }
        Tiquete atendido = cajas[numeroCaja].atender();
        if (atendido != null) {
            atendido.sethAtencion(LocalTime.now());
            guardarReporte(atendido);
            JOptionPane.showMessageDialog(null, "Atendido: " + atendido.getNombre());
        } else {
            JOptionPane.showMessageDialog(null, "No hay tiquetes en la caja #" + numeroCaja);
        }
    }

    /**
     * Asigna una caja según el tipo de tiquete: 'P' preferencial (caja 1), 'A'
     * atención rápida (caja 2), 'B' caja con menor carga desde la 3 en
     * adelante.
     *
     * @param tipo Tipo del tiquete.
     * @return Número de caja asignada.
     */
    private int asignarCaja(char tipo) {
        switch (tipo) {
            case 'P' -> {
                return 1;
            }
            case 'A' -> {
                return 2;
            }
            case 'B' -> {
                int cajaAsignada = 3;
                for (int i = 4; i <= cantidadCajas; i++) {
                    if (cajas[i].contar() < cajas[cajaAsignada].contar()) {
                        cajaAsignada = i;
                    }
                }
                return cajaAsignada;
            }
            default -> {
                return 3; // Por defecto, caja general
            }
        }
    }

    /**
     * Muestra el estado actual de todas las colas de las cajas.
     */
    public void mostrarColas() {
        StringBuilder mensaje = new StringBuilder();
        for (int i = 1; i <= cantidadCajas; i++) {
            mensaje.append("Caja #").append(i).append(":\n");
            cajas[i].mostrarCola();
        }
        JOptionPane.showMessageDialog(null, mensaje.toString(), "Estado de las Cajas", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Guarda un reporte con los datos del tiquete atendido en un archivo de
     * texto.
     *
     * @param t Tiquete atendido.
     */
    private void guardarReporte(Tiquete t) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("reportes.txt", true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String linea = "-----------------------------------\n"
                    + "Nombre: " + t.getNombre() + "\n"
                    + "ID: " + t.getId() + "\n"
                    + "Edad: " + t.getEdad() + "\n"
                    + "Trámite: " + t.getTramite() + "\n"
                    + "Tipo: " + t.getTipo() + "\n"
                    + "Caja: " + t.getNumeroCaja() + "\n"
                    + "Hora de creación: " + t.gethCreacion().format(formatter) + "\n"
                    + "Hora de atención: " + t.gethAtencion().format(formatter) + "\n";
            bw.write(linea);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar el reporte: " + e.getMessage());
        }
    }
}
