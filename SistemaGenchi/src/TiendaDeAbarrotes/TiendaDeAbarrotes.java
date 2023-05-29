package TiendaDeAbarrotes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;

public class TiendaDeAbarrotes {//Daddy

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

    public static Articulo llamador = new Articulo(1, "");
    public static Scanner leer = new Scanner(System.in);
    public static Scanner leerProducto = new Scanner(System.in);
    int codigo; //Variable de tipo entera Publica llamada "codigo"
    String nombre;//Variable de tipo String Publica llamada "nombre"

    public TiendaDeAbarrotes(int Codigo, String Nombre) {//Constructor la clase papá
        this.codigo = Codigo;
        this.nombre = Nombre;
    }

    static void menuRoles() throws IOException {
        int respuestaUser = 0;
        do {
            System.out.println(ANSI_YELLOW + "Bienvenido a la tienda de abarrotes_-_-_-_-_-_-_-_-_-_-_-_-_-_-        " + "#Menu Principal#" + ANSI_RESET);
            System.out.println("1.- Soy un cliente    2.-Soy el dueno   3.-Salir del sistema");
            respuestaUser = leer.nextInt();
            switch (respuestaUser) {
                case 1:
                    menuCliente();
                    break;
                case 2:
                    menuDueno();
                    break;
                case 3:
                    System.out.println("Hasta luego y vuelve pronto!");
                    System.exit(0);
                    break;
                default:
                    System.out.println(ANSI_RED + "Opcion Invalida, Ingresa de nuevo" + ANSI_RESET);
            }
        } while (respuestaUser != 3);
    }

    static void menuCliente() throws IOException {
        int respuestaCliente = 0;
        do {
            //leer.nextLine(); //Limpieza del buffer del scanner
            System.out.println(ANSI_PURPLE + "Que deseas hacer?_-_-_-_-_-_-_-_-_-_-_-_-_-_-       " + "#Cliente#" + ANSI_PURPLE);
            System.out.println("1.-Adquirir articulos     2.-Ver carrito     3.-Pagar carrito       4.- Regresar al menu anterior   5.-Salir del sistema");
            respuestaCliente = leer.nextInt();
            switch (respuestaCliente) {
                case 1:
                    llamador.adquirirArticulos();
                    break;
                case 2:
                    llamador.verCarrito();
                    break;
                case 3:
                    llamador.pagarCarrito();
                    break;
                case 4:
                    menuRoles();
                    break;
                case 5:
                    System.out.println("Hasta luego y vuelve pronto!");
                    System.exit(0);
                    break;
                default:
                    System.out.println(ANSI_RED + "Opcion invalida, Intentalo de nuevo" + ANSI_RESET);
            }
        } while (respuestaCliente != 5);
    }

    
    
    public static void menuDueno() throws IOException {
        Articulo llamandoArticulos = new Articulo(1, "");
        Proveedor llamandoProveedor = new Proveedor(1, "", "");
        int respuestaDueno = 0;
        leer.nextLine(); //Limpieza del buffer del scanner
        do {
            System.out.println(ANSI_GREEN + "Con que quieres trabajar?_-_-_-_-_-_-_-_-_-_-_-_-_-_-       " + "#Dueno#" + ANSI_GREEN);
            System.out.println("1. Articulos");
            System.out.println("2. Proveedor");
            System.out.println("3.-Cerrar venta diaria");
            System.out.println("4.-Regresar al menu Anterior");
            respuestaDueno = leer.nextInt();
            switch (respuestaDueno) {
                case 1:
                    llamandoArticulos.menuArticulosDueno();
                    break;
                case 2:
                    llamandoProveedor.menuProveedoresDueno();
                    break;
                case 3:
                    
                    break;
                case 4:
                    menuRoles();
                    break;
                default:
                    System.out.println(ANSI_RED + "Opcion Invalida, ingresa una opcion valida" + ANSI_RESET);
            }
        } while (respuestaDueno != 3);
    }

    public static void main(String[] args) throws IOException {
        menuRoles();
    }

}
