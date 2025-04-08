package estdatproyecto;

import javax.swing.JOptionPane;

/**
 * Clase Cola que implementa una estructura de datos tipo FIFO. Almacena
 * elementos de tipo Nodo, donde cada Nodo contiene un objeto de la clase
 * Tiquete.
 */
public class Cola {

    /**
     * Nodo frente representa el frente (inicio) de la cola. Nodo último
     * representa el ultimo (final) de la cola.
     */
    private Nodo frente, ultimo;

    /**
     * Constructor que inicializa una cola vacía.
     */
    public Cola() {
        frente = null;
        ultimo = null;
    }

    /**
     * Verifica si la cola está vacía.
     *
     * @return true si la cola está vacía, false en caso contrario
     */
    public boolean esVacia() {
        return frente == null;
    }

    /**
     * Inserta (encola) un nuevo tiquete al final de la cola.
     *
     * @param t Objeto de tipo Tiquete a insertar.
     */
    public void insertar(Tiquete t) {
        Nodo nuevo = new Nodo(t); //Se crea un nuevo nodo con el tiquete
        if (this.esVacia()) {
            frente = ultimo = nuevo;
        } else {
            ultimo.setSig(nuevo);
            ultimo = nuevo;
        }
    }

    /**
     * Atiende (desencola) el primer tiquete de la cola.
     *
     * @return El tiquete atendido, o null sila cola está vacía.
     */
    public Tiquete atender() {
        if (this.esVacia()) {
            JOptionPane.showMessageDialog(null, "No hay clientes en espera.");
            return null;
        }
        Tiquete t = frente.getTiquete();
        frente = frente.getSig();
        if (frente == null) {
            ultimo = null; //Si la cola queda vacía despues de atender
        }
        return t;
    }

    /**
     * Muestra todos los tiquetes actualmente en la cola usando JOptionPane.
     */
    public void mostrarCola() {
        Nodo actual = frente;
        while (actual != null) {
            JOptionPane.showMessageDialog(null, "Clientes en espera: " + actual.getTiquete());
            actual = actual.getSig();
        }
    }

    /**
     * Cuenta la cantidad de tiquetes (nodos) presentes en la cola.
     *
     * @return Número total de elementos en la cola.
     */
    public int contar() {
        int count = 0;
        Nodo aux = frente;
        while (aux != null) {
            count++;
            aux = aux.getSig();
        }
        return count;
    }

    /**
     * Retorna una representación en texto del estado actual de la cola.
     *
     * @return Cadena con los tiquetes presentes o "Vacía" si no hay ninguno.
     */
    @Override
    public String toString() {
        String r = "Cola{\n";
        if (this.esVacia()) {
            r += "Vacia\n}";
        } else {
            Nodo aux = frente;
            while (aux != null) {
                r += aux + "\n";
                aux = aux.getSig();
            }
            r += "}";
        }
        return r;
    }
}
