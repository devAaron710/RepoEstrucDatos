
package estdatproyecto;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Tiquete {
    //atributos del ticket
    private String nombre;
    private int id, edad;
    private char tipo; // sea A, P O B 
    private LocalTime hCreacion; //se crea varible de tipo localTime para almacenar la hora de generacion del ticket
    private LocalTime hAtencion; //se crea varible de tipo localTime para almacenar la hora cuando se atiende el ticket
    private int tramite; //se maneja con int, 1)Depósitos, 2)Retiros y 3)Cambio de Divisas
    // hacer private los atributos
    
    //constructor de ticket
    public Tiquete(String nombre, int id, int edad, char tipo, int tramite) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.tipo = tipo;
        this.hCreacion = LocalTime.now();//inicializamos la hora de creacion del ticket con el . now()
        this.hAtencion = null; //inicializamos la hora de atencion del ticket en nulo porque no ha sido atendido 
        this.tramite = tramite;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public void sethCreacion(LocalTime hCreacion) {
        this.hCreacion = hCreacion;
    }

    public void sethAtencion(LocalTime hAtencion) {
        this.hAtencion = hAtencion;
    }

    public void setTramite(int tramite) {
        this.tramite = tramite;
    }
    
    //se retorna el tipo del ticket
    public char getTipo() {
        return tipo;
    }
    
    //se retorna la hora de creacion del ticket
    public LocalTime gethCreacion() {
        return hCreacion;
    }
    
    //se actuializa la hora de atencion del ticket 
    public void atender(){
        this.hAtencion = LocalTime.now();
    }

    //con el metodo toString podemos hacer prints con objetos
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String aux="";
        if(hAtencion != null){
            aux=hAtencion.format(formatter);
        }else{
            aux= "-1";
        }
        
        String aux2 = "";
        if(tramite == 1){
            aux2 = "1-Depósitos";
        }else if(tramite == 2){
            aux2= "2-Retiros";
        }else if(tramite == 3){
            aux2= "3-Cambio de Divisas";
        }else{
           aux2= "Número invalido!";
        }
        
        String r="Tiquete" +
               "\n[Nombre:  " + nombre +
               "\nID:  " + id +
               "\nEdad:  " + edad +
               "\nHora de creación:  " + hCreacion.format(formatter) +
               "\nHora de atención:  " + aux+
               "\nTipo ticket:  " + tipo +
               "\nTramite:  " + aux2 +
                "]";
        return r;
                
    }
}
