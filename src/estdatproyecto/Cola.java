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

    // Método para insertar un nuevo tiquete en la cola
    public void insertar(Tiquete t) {
        Nodo n = new Nodo(t);
        if (this.esVacia()) {
            frent = ult = n;
        } else {
            ult.setSig(n);
            ult = n;
        }
        JOptionPane.showMessageDialog(null, "Tiquete insertado: " + t); // Mostrar detalles del tiquete insertado
    }

    // Método para atender (desencolar) el primer nodo de la cola
    public Nodo atender() {
        if (this.esVacia()) {
            return null;
        }
        Nodo act = this.frent;
        this.frent = act.getSig();
        act.setSig(null);
        JOptionPane.showMessageDialog(null, "Tiquete atendido: " + act.getTiquete()); // Mostrar detalles del tiquete atendido
        return act;
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
