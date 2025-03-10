package estdatproyecto;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JPasswordField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class EstDatProyecto { 
    
    //credenciales en daatos quemados
    private static final String userName =  "admin";
    private static final String password =  "admin";

    public static void main(String[] args) throws IOException {
        //varible que mantiene el inicio de sesion en bucle hasta que se ingresen credenciales guardadas
        boolean autenticado = false;
        
        //bucle para solicitar credenciales hasta que sean correctas
        while (!autenticado) {
            //Pedir nombre de usuario
            String user = JOptionPane.showInputDialog("Ingrese su nombre de usuario:");
            
            //si el usuario presiona "Cancelar"
            if (user == null) {
                JOptionPane.showMessageDialog(null, "Operación cancelada. El sistema se cerrará.");
                System.exit(0);
            }
            
            //pedir contraseña
            JPasswordField passwordField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(null, passwordField, "Ingrese su contraseña:", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            
            if (option == JOptionPane.OK_OPTION) {
                String pass = new String(passwordField.getPassword());
                //verificar si los datos ingresados coinciden con los datos quemados
                if (userName.equals(user) && password.equals(pass)) {
                    JOptionPane.showMessageDialog(null, "¡Login exitoso!");
                    //autenticado se actualiza como true
                    autenticado = true;}
                
                else{
                    JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrectos. Intente de nuevo.");
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "Operación cancelada. El sistema se cerrará.");
                System.exit(0);
            }
        }
        //se crea la configuración inicial del banco
        Banco banco = new Banco();
        banco.crearTXT();
        
        //instancia de objeto cola
        Cola cola = new Cola();
        //mantiene el menu en bucle hasta que ingrese salir
        boolean continuar = true;
        
        //menu while
        while (continuar) {
            //mostrar menú de opciones
            String opcion = JOptionPane.showInputDialog(
                    "Seleccione una opción:\n" + 
                    "1. Crear tiquete\n" +
                    "2. Atender tiquete\n" +
                    "3. Mostrar cola\n" +
                    "4. Mostrar tipo de cambio\n" +
                    "5. Salir"
            );
            
            //opcion cancelar
            if (opcion == null){
                JOptionPane.showMessageDialog(null, "Operación cancelada");
                System.exit(0);
            }
            
            switch (opcion) {
                case "1": // Crear tiquete
                    //solicitar datos del tiquete al usuario
                    String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
                    //.trim()elimina espacios en blanco en los extremos de la cadena
                    if (nombre == null || nombre.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El nombre no puede quedar vacío.");
                        break;
                    }

                    String idStr = JOptionPane.showInputDialog("Ingrese el ID del cliente: ");
                    if (idStr == null || idStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El ID no puede quedar vacío.");
                        break;
                    }
                    int id;
                    try {
                        id = Integer.parseInt(idStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El ID debe ser un número entero.");
                        break;
                    }

                    String edadStr = JOptionPane.showInputDialog("Ingrese la edad del cliente: ");
                    if (edadStr == null || edadStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "La edad no puede quedar vacía.");
                        break;
                    }
                    int edad;
                    try {
                        edad = Integer.parseInt(edadStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "La edad debe ser un número entero.");
                        break;
                    }

                    String tipoString = JOptionPane.showInputDialog("Ingrese el tipo (P: preferencial, A: un solo trámite, B: dos o más trámites): ");
                    if (tipoString == null || tipoString.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El tipo no puede quedar vacío.");
                        break;
                    }
                    char tipo = tipoString.trim().charAt(0);

                    String tramiteStr = JOptionPane.showInputDialog("Ingrese el trámite (1: Depósitos, 2: Retiros, 3: Cambio de Divisas): ");
                    if (tramiteStr == null || tramiteStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "El trámite no puede quedar vacío.");
                        break;
                    }
                    int tramite;
                    try {
                        tramite = Integer.parseInt(tramiteStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "El trámite debe ser un número entero.");
                        break;
                    }

                    //crear un nuevo tiquete con los datos ingresados
                    Tiquete tiquete = new Tiquete(nombre, id, edad, tipo, tramite);
                    //insertar el tiquete en la cola
                    cola.insertar(tiquete);
                    break;
                    
                case "2": //atender tiquete
                    //atender el primer tiquete y mostrar el estado de la cola
                    cola.atender();
                    break;

                case "3": //mostrar cola
                    //mostrar el estado de la cola
                    cola.mostrarCola();
                    break;
                case "4": //mostrar tipo de cambio con webScraping
                    System.out.println(ScrapingWeb.getHTML("https://servicios.davivienda.cr/master/v1/davicotizador/"));
                    new ScrapingWeb().scraping();
                    break;
                case "5": //salir
                    continuar = false;
                    break;

                default:
                    //mensaje de opción inválida
                    JOptionPane.showMessageDialog(null, "Opción no válida");
                    break;
            }
        }
    }
}
