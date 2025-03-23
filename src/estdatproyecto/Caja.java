package estdatproyecto;

/**
 *
 * @author kaaraya
 */
public class Caja {

    private int numeroCaja;
    private boolean disponible;

    public Caja(int numeroCaja) {
        this.numeroCaja = numeroCaja;
        this.disponible = true; //Inicialmente la caja esta disponible
    }

    public int getNumeroCaja() {
        return numeroCaja;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Caja{" + "numeroCaja=" + numeroCaja + ", disponible=" + disponible + '}';
    }

}
