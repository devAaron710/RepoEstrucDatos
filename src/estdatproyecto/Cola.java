package estdatproyecto;

import javax.swing.JOptionPane;

/**
 *
 * @author Aaron Azofeifa
 */
public class Cola {
    private Nodo frent, ult;

    // Método para verificar si la cola está vacía
    public boolean esVacia() {
        return frent == null;
    }

    /*kaaraya212*/
    // Método para insertar un nuevo tiquete en la cola
    public void insertar(Tiquete t) {
        Nodo nuevo = new Nodo(t); //Se crea un nuevo nodo con el tiquete

        if (this.esVacia() || t.prioridad() > frent.tiquete.prioridad()) { //Condicion verifica 1. Si la cola esta vacia y 2. Verifica si el tiquete insertado tiene una prioridad mayor que el primero de la cola(frent). Si el tiquete tiene mayor prioridad, debe colocarse al principio de la cola.
            nuevo.sig = frent; //Nuevo nodo apunta al nodo que era el primero
            frent = nuevo; //Nuevo nodo pasa a ser el primer nodo de la cola(frent apunta a nuevo nodo)
        } else {
            Nodo actual = frent;
            while (actual.sig != null && actual.sig.tiquete.prioridad() >= t.prioridad()) { //Se recorre la cola mientras el siguiente nodo no sea nulo
                actual = actual.sig; //Si la prioridad del siguiente nodo es mayor o igual a la del nuevo tiquete, se avanza al siguiente nodo.
            }
            nuevo.sig = actual.sig;
            actual.sig = nuevo;//Se conectan, es nodo actual ahora apunta al nuevo nodo.
        }
        JOptionPane.showMessageDialog(null, "Tiquete insertado: " + t); // Mostrar detalles del tiquete insertado
    }

    // Método para atender (desencolar) el primer nodo de la cola
    public Tiquete atender() {
        if (this.esVacia()) {
            JOptionPane.showMessageDialog(null, "No hay clientes en espera.");
            return null; //Si la cola esta vacia, no se puede atender
        }
        //Obtenemos el tiquete que sera atendido
        Tiquete atendido = frent.tiquete;

        String mensaje = "Tiquete atendido: \n"
                + "Nombre: " + atendido.getNombre() + "\n"
                + "ID: " + atendido.getId() + "\n"
                + "Edad: " + atendido.getEdad() + "\n"
                + "Tipo: " + atendido.getTipo() + "\n"
                + "Tramite: " + atendido.getTramite() + "\n"
                + "Prioridad: " + atendido.prioridad();

        JOptionPane.showMessageDialog(null, mensaje);
        frent = frent.sig;
        return atendido;
    }

    public void mostrarCola() {
        Nodo actual = frent;
        while (actual != null) {
            JOptionPane.showMessageDialog(null, "Clientes en espera: " + actual.tiquete);
            actual = actual.sig;
        }
    }

    @Override
    public String toString() {
        String r = "Cola{\n";
        if (this.esVacia()) {
            r += "Vacia\n}";
        } else {
            Nodo aux = frent;
            while (aux != null) {
                r += aux + "\n";
                aux = aux.getSig();
            }
            r += "}";
        }
        return r;
    }
}
