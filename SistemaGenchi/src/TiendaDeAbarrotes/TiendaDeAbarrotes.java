package TiendaDeAbarrotes;

import java.io.IOException;
import java.util.Scanner;

public class TiendaDeAbarrotes {//Clase principal y clase padre

    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
    //Colores para la consola (Solo para dar diseno)

    public static Articulo llamador = new Articulo("", "");//Instancia a la clase Articulo
    public static Scanner leer = new Scanner(System.in); //Objeto de tipo Scanner llamado "Leer"
    public static Scanner leerProducto = new Scanner(System.in);//Objeto de tipo
    String codigo; //Variable de tipo entera Publica llamada "codigo"
    String nombre;//Variable de tipo String Publica llamada "nombre"

    public TiendaDeAbarrotes(String Codigo, String Nombre) {//Constructor de la clase padre
        this.codigo = Codigo; //Asignacion de valores privados a las globales de la clase padre
        this.nombre = Nombre;
    }//Fin del constructor de la clase TiendaDeAbarrotes

    static void menuRoles() throws IOException {//Metodo del menu principal
        int respuestaUser = 0;
        do {//Inicio del ciclo repetitivo do while
            System.out.println(ANSI_YELLOW + "Bienvenido a la tienda de abarrotes_-_-_-_-_-_-_-_-_-_-_-_-_-_-        " + "#Menu Principal#" + ANSI_RESET);
            System.out.println("1.- Soy un cliente    2.-Soy el dueno   3.-Salir del sistema");
            respuestaUser = leer.nextInt();//Se asigna la respuesta del usuario a la variable llamada "respuestaUser"
            switch (respuestaUser) {//Inicio de estructura selectiva Switch y Comparacion de la respuesta 
                case 1://Caso numero 1
                    menuCliente(); //Llamada al metodo menuCiente()
                    break; //Se rompe el switch una vez concluida la instruccion anterior
                case 2: //Caso numero 2
                    menuDueno(); //Llamada al metodo menuDueno()
                    break;//Se rompe el switch cuando la instruccion anterior fue hecha
                case 3://Caso numero 2
                    System.out.println(ANSI_YELLOW + "Hasta luego y vuelve pronto!" + ANSI_RESET);//Mensaje de despedida
                    System.exit(0);//Cierre del sistema
                    break;//Ruptura del switch una vez que se ejecuto la instruccion anterior
                default://A partir de esta linea de codigo, las siguientes instrucciones se ejecutaran cuando el usuario elija una opcion invalida
                    System.out.println(ANSI_RED + "Opcion Invalida, Ingresa de nuevo" + ANSI_RESET); //Advertencia de opcion invalida
            }//Cierre de la estructura selectiva Switch
        } while (respuestaUser != 3);//Cierre de la estructura repetitiva do while con la condicion establecida
    }//Fin del metodo del menu principal

    static void menuCliente() throws IOException {//Inicio del metodo del menu del cliente
        int respuestaCliente = 0;//Variables
        do {//Inicio de ciclo repetitivo do while
            //leer.nextLine(); //Limpieza del buffer del scanner
            System.out.println(ANSI_PURPLE + "Que deseas hacer?_-_-_-_-_-_-_-_-_-_-_-_-_-_-       " + "#Cliente#" + ANSI_PURPLE);
            System.out.println("1.-Adquirir articulos     2.-Ver carrito     3.-Pagar carrito       4.- Regresar al menu anterior   5.-Salir del sistema");
            respuestaCliente = leer.nextInt();//Asignacion de valor ingresado por el usuario a la variable respuestacliente
            switch (respuestaCliente) {//inicio de la estructura selectiva Switch y comparacion de la respuesta
                case 1://Caso numero uno
                    llamador.adquirirArticulos();//Llamada del metodo adquirirArticulos de la clase Articulo
                    break;//se rompe la estructura selectiva una vez concluida la intruccion anterior
                case 2://Caso numero 2
                    llamador.verCarrito();//Llamada del metodo verCarrito de la clase Articulo
                    break;//Ruptura de la estructura selectiva
                case 3://Caso numero 3
                    llamador.pagarCarrito();//Llamada del metodo pagarCarrito de la clase Articulo
                    break;//Rompimiento de la estuctura selectiva Switch
                case 4://Caso numero 4
                    menuRoles();//Llamada del metodo miembro llamado menuRoles de la clase TiendaDeAbarrotes (Principal)
                    break;//Ruptura de la estructura selectiva Switch
                case 5://Caso numero 5
                    System.out.println("Hasta luego y vuelve pronto!");//Mensaje de despedida
                    System.exit(0);//Salida del sistema
                    break;//Rompimiento del switch
                default://Lineas de codigo a ejecutar en caso de que el usuario ingrese una respuesta incorrecta
                    System.out.println(ANSI_RED + "Opcion invalida, Intentalo de nuevo" + ANSI_RESET);//Advertencia de opcion invalida
            }//Fin del Switch
        } while (respuestaCliente != 5);//Cierre de la estructura repetitiva do while con la condicion
    }//Fin del metodo del menu del cliente

    public static void menuDueno() throws IOException {//Inicio del metodo menuDueno
        Articulo llamandoArticulos = new Articulo("", "");//Llamada del constructor de la clase Articulo con variables introducidas manualmente (No afecta en nada)
        Proveedor llamandoProveedor = new Proveedor("", "", "");//Llamada del constructor de la clase Proveedor con variables asignadas manualmente (No afecta en nada)
        int respuestaDueno = 0;//Variable
        leer.nextLine(); //Limpieza del buffer del scanner
        do {//Inicio de la estructura repetitiva do while
            System.out.println(ANSI_GREEN + "Con que quieres trabajar?_-_-_-_-_-_-_-_-_-_-_-_-_-_-       " + "#Dueno#" + ANSI_GREEN);
            System.out.println("1. Articulos");
            System.out.println("2. Proveedor");
            System.out.println("3.-Cerrar venta diaria");
            System.out.println("4.-Regresar al menu Anterior");
            respuestaDueno = leer.nextInt();//Asignacion del valor introducido por el usuario a la variable respuesta dueno
            switch (respuestaDueno) {//Inicio de la estructura selectiva switch con la variable respuestadueno
                case 1://Caso numero 1
                    llamandoArticulos.menuArticulosDueno();//Llamada al metodo menuArticulosDueno de la clase Articulo
                    break;//Rompimiento de la estructura selectiva Switch
                case 2://Caso numero 2
                    llamandoProveedor.menuProveedoresDueno();//Llamada al metodo mnuProveedoresDueno
                    break;//Ruptura de la estructura selectiva Switch
                case 3://Caso numero 3
                    llamandoArticulos.cerrarVenta();
                    break;//Rompimiento de la estructura Switch
                case 4://Caso 4
                    menuRoles();//Llamada al metodo miembto llamado menuRoles de la clase TiendaDeAbarrotes (Principal)
                    break;//Rompimiento de la estructura selectiva Siwtch
                default://Lineas de codigo a ejecutar en caso de que el usuario ingrese una opcion invalida
                    System.out.println(ANSI_RED + "Opcion Invalida, ingresa una opcion valida" + ANSI_RESET);//Mensaje de opcion invalida
            }//Fin del Switch
        } while (respuestaDueno != 3);//Fin de la estructura Repetitiva do while
    }//Fin del metodo menuDueno

    public static void main(String[] args) throws IOException {//Metodo MAIN
        menuRoles();//Llamada al metodo miembro menuRoles
    }//Fin del metodo main

}//Fin del metodo principal TiendaDeAbarrotes
