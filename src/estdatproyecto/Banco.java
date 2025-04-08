package estdatproyecto;

import java.io.*;
import javax.swing.JOptionPane;

public class Banco {

    //Variables de la clase
    // Variables para almacenar la configuración del banco
    private String nombreBanco;
    private int cantidadCajas;
    private int cajaPreferencial;
    private int cajaTramitesRapidos;
    private int cajasTramitesNoProferencial;

    //Modulo 0 configuracion inicial de banco
    //crear variable tipo file
    //Se declara un objeto File llamado configBanco, el cual representa el archivo de configuracion (prod.txt)
    File configBanco;

    //metodo crear archivo de configuracion inicial del banco
    public void crearTXT() {
        configBanco = new File("prod.txt");// crea el archivo
        try {
            //Si el archivo no existe, lo crea y devuelve true
            if (configBanco.createNewFile()) {
                escribirTXT(); //Solo se escribe el archivo si es nuevo
                JOptionPane.showMessageDialog(null, "prod.txt creado con exito");
            } else {
                //Si el archivo ya existe, muestra un mensaje de error y carga la config existente
                JOptionPane.showMessageDialog(null, "Error al crear el prod.txt, este archivo ya existe!");
                cargarConfiguracion();//cargar config existente
            }
            //IOException maneja excepciones relacionadas con operaciones de entrada/salida (I/O), como leer o escribir archivo
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    //metodo escribir en archivo 
    private void escribirTXT() {
        try {
            //Un ﬂujo de la clase FileWriter permite escribir caracteres (char) en un ﬁchero
            //escritura variable de referencia
            FileWriter escritura = new FileWriter(configBanco);//Se da como parametro el nombre del archivo
            nombreBanco = JOptionPane.showInputDialog(null, "Ingresar nombre de banco: ");
            cantidadCajas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingresar la cantidad de cajas requeridas para la atención de clientes (min 3): "));
            //Si la cantidad de cajas es menor a 3, muestra un mensaje de error y vuelve a solicitar la entrada
            if (cantidadCajas < 3) {
                JOptionPane.showMessageDialog(null, "Error: como mínimo deben haber 3 cajas", "Error", JOptionPane.ERROR_MESSAGE);
                //solicitar nuevamente la entrada del usuario
                escribirTXT();
            }

            //inicializacion de cajas segun los requisitos del proyecto
            cajaPreferencial = 1;
            cajaTramitesRapidos = 1;
            cajasTramitesNoProferencial = cantidadCajas - 2;

            //escritura de datos en el txt con write() de la clase FileWriter.
            escritura.write("Nombre del Banco: " + nombreBanco + "\n");
            escritura.write("Cantidad de cajas en el Banco: " + cantidadCajas + "\n");
            escritura.write("Caja Preferencial: " + cajaPreferencial + "\n");
            escritura.write("Caja Trámites Rápidos: " + cajaTramitesRapidos + "\n");
            escritura.write("Cajas Trámites No Preferenciales: " + cajasTramitesNoProferencial + "\n");
            //se utiliza para cerrar el flujo de escritura de un archivo después de que hayas terminado de escribir en él.

            //Guardar estado de las cajas
            escritura.close();

            JOptionPane.showMessageDialog(null, "Configuracion guardada en prod.txt");
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    //metodo cargarConfig desde el archivo
    private void cargarConfiguracion() {
        try {
            //BufferedReader es como una mejor version de FileWriter
            //la diferencia es que FileWriter es caracter a caracter y BufferedReader es por lineas o renglones
            BufferedReader lectura = new BufferedReader(new FileReader(configBanco));
            //Variable para almacenar cada línea leída del archivo
            String renglon;

            //Mientras el renglon tenga que líneas que leer en el archivo
            while ((renglon = lectura.readLine()) != null) {
                //Si la línea comienza con "Nombre del Banco: ", se asigna el valor a la variable nombreBanco
                //startsWith es parte de la clase String y se utiliza para verificar un prefijo en el inicio de una cadena de texto
                if (renglon.startsWith("Nombre del Banco: ")) {
                    //substring nos proporciona una parte de una cadena de texto (String)
                    //.length() nos devuelve el número de caracteres en esa cadena y ese sera el inidice para substring
                    nombreBanco = renglon.substring("Nombre del Banco: ".length());
                } else if (renglon.startsWith("Cantidad de cajas en el Banco: ")) {
                    cantidadCajas = Integer.parseInt(renglon.substring("Cantidad de cajas en el Banco: ".length()));
                } else if (renglon.startsWith("Caja Preferencial: ")) {
                    cajaPreferencial = Integer.parseInt(renglon.substring("Caja Preferencial: ".length()));
                } else if (renglon.startsWith("Caja Trámites Rápidos: ")) {
                    cajaTramitesRapidos = Integer.parseInt(renglon.substring("Caja Trámites Rápidos: ".length()));
                } else if (renglon.startsWith("Cajas Trámites No Preferenciales: ")) {
                    cajasTramitesNoProferencial = Integer.parseInt(renglon.substring("Cajas Trámites No Preferenciales: ".length()));
                }
            }
            //cerramos el BufferedReader
            lectura.close();
            JOptionPane.showMessageDialog(null, "Configuración cargada con éxito");
        } catch (IOException e) {
            //Imprime la traza de la excepción en la consola
            e.printStackTrace(System.out);
        }
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public int getCantidadCajas() {
        return cantidadCajas;
    }

    public void setCantidadCajas(int cantidadCajas) {
        this.cantidadCajas = cantidadCajas;
    }

    public int getCajaPreferencial() {
        return cajaPreferencial;
    }

    public void setCajaPreferencial(int cajaPreferencial) {
        this.cajaPreferencial = cajaPreferencial;
    }

    public int getCajaTramitesRapidos() {
        return cajaTramitesRapidos;
    }

    public void setCajaTramitesRapidos(int cajaTramitesRapidos) {
        this.cajaTramitesRapidos = cajaTramitesRapidos;
    }

    public int getCajasTramitesNoProferencial() {
        return cajasTramitesNoProferencial;
    }

    public void setCajasTramitesNoProferencial(int cajasTramitesNoProferencial) {
        this.cajasTramitesNoProferencial = cajasTramitesNoProferencial;
    }

    public File getConfigBanco() {
        return configBanco;
    }

    public void setConfigBanco(File configBanco) {
        this.configBanco = configBanco;
    }
}

