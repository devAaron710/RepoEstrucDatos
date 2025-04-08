package estdatproyecto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Tiquete {

    //Atributos de la clase
    private String nombre;
    private int id, edad;
    private char tipo; // P:preferencial, A:un solo tramite, B:dos o mas tramites 
    private LocalTime hCreacion; //se crea varible de tipo localTime para almacenar la hora de generacion del ticket
    private LocalTime hAtencion; //se crea varible de tipo localTime para almacenar la hora cuando se atiende el ticket
    private int tramite; //se maneja con int, 1)Depósitos, 2)Retiros y 3)Cambio de Divisas
    private int numeroCaja;

    //constructor de ticket
    public Tiquete(String nombre, int id, int edad, char tipo, int tramite, int numeroCaja) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tipo = tipo;
        this.hCreacion = LocalTime.now(); //Inicializamos la hora de creacion del ticket con el . now()
        this.hAtencion = null; //Inicializamos la hora de atencion del ticket en nulo porque no ha sido atendido
        this.tramite = tramite;
        this.numeroCaja = numeroCaja;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public LocalTime gethCreacion() {
        return hCreacion;
    }

    public void sethCreacion(LocalTime hCreacion) {
        this.hCreacion = hCreacion;
    }

    public LocalTime gethAtencion() {
        return hAtencion;
    }

    public void sethAtencion(LocalTime hAtencion) {
        this.hAtencion = hAtencion;
    }

    public int getTramite() {
        return tramite;
    }

    public void setTramite(int tramite) {
        this.tramite = tramite;
    }

    public int getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(int numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    // Metodo para asignar la prioridad de un tiquete
    public int prioridad() {
        if (tipo == 'P') { //Si su tipo es 'Preferencial' se le asigna la prioridad 3.
            return 3;
        } else if (tipo == 'A') { //Si la primera condicion no se cumpla, verifica si el tipo es 'A: Un solo tramite' y se le asigna la prioridad 2.
            return 2;
        } else { //Si ninguna de las condiciones se cumplen, su prioridad sera 1, que es la de menor prioridad.
            return 1;
        }
    }

    //con el metodo toString podemos hacer prints con objetos
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String aux = "";
        if (hAtencion != null) {
            aux = hAtencion.format(formatter);
        } else {
            aux = "-1";
        }

        String aux2 = "";
        if (tramite == 1) {
            aux2 = "1-Depósitos";
        } else if (tramite == 2) {
            aux2 = "2-Retiros";
        } else if (tramite == 3) {
            aux2 = "3-Cambio de Divisas";
        } else {
            aux2 = "Número invalido!";
        }

        String r = ""
                + "\nNombre:  " + nombre
                + "\nID:  " + id
                + "\nEdad:  " + edad
                + "\nHora de creación:  " + hCreacion.format(formatter)
                + "\nHora de atención:  " + aux
                + "\nTipo ticket:  " + tipo
                + "\nTramite:  " + aux2
                + "\nNumero de caja:  " + numeroCaja
                + "";
        return r;

    }
}

