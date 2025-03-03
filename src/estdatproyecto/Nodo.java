package estdatproyecto;

/**
 *
 * @author Aaron Azofeifa
 */
public class Nodo {
    private Tiquete tiquete;
    private Nodo sig;

    public Nodo(Tiquete tiquete) {
        this.tiquete = tiquete;
        this.sig = null;
    }

    public Tiquete getTiquete() {
        return tiquete;
    }

    public void setTiquete(Tiquete tiquete) {
        this.tiquete = tiquete;
    }

    public Nodo getSig() {
        return sig;
    }

    public void setSig(Nodo sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return "Nodo{" + "tiquete=" + tiquete + '}';
    }
}
