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
    public static Articulo llamador = new Articulo(1, "");
    public static Scanner leer = new Scanner(System.in);
    int codigo; //Variable de tipo entera Publica llamada "codigo"
    String nombre;//Variable de tipo String Publica llamada "nombre"

    public TiendaDeAbarrotes(int Codigo, String Nombre) {//Constructor la clase papá
        this.codigo = Codigo;
        this.nombre = Nombre;
    }

    static void menuRoles() throws IOException {
        int respuestaUser = 0;
        do {
            System.out.println("Bienvenido a la tienda de abarrotes");
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
                    System.out.println("Opcion Invalida, Ingresa de nuevo");
            }
        } while (respuestaUser != 3);
    }
    
    static void menuCliente() throws IOException {
        int respuestaCliente = 0;
        leer.nextLine(); //Limpieza del buffer del scanner
        do {
            System.out.println("Que deseas hacer?");
            System.out.println("1.-Adquirir articulos     2.-Ver carrito     3.-Pagar carrito       4.- Regresar al menu anterior   5.-Salir del sistema");
            respuestaCliente = leer.nextInt();
            switch (respuestaCliente) {
                case 1:
                    adquirirArticulos();
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
                    System.out.println("Opcion invalida, Intentalo de nuevo");
            }
        } while (respuestaCliente != 5);
    }
    
    public static void menuDueno() throws IOException {
        Articulo llamandoArticulos = new Articulo(1, "");
        Proveedor llamandoProveedor = new Proveedor(1, "", "");
        int respuestaDueno = 0;
        leer.nextLine(); //Limpieza del buffer del scanner
        do {
            System.out.println("Con que quieres trabajar?");
            System.out.println("1. Articulos");
            System.out.println("2. Proveedor");
            System.out.println("3.-Regresar al menu Anterior");
            respuestaDueno = leer.nextInt();
            switch (respuestaDueno) {
                case 1:
                    llamandoArticulos.menuArticulosDueno();
                    break;
                case 2:
                    llamandoProveedor.menuProveedoresDueno();
                    break;
                case 3:
                    menuRoles();
                    break;
                default:
                    System.out.println("Opcion Invalida, ingresa una opcion valida");
            }
        } while (respuestaDueno != 3);
    }
    
    static void adquirirArticulos() throws IOException {
        String productoelegido = "";
        int cantidadproductoelegido = 0;
        try {
            FileWriter crear = new FileWriter(llamador.archivoarticulos, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(llamador.archivoarticulos));
            FileWriter creaarchivocarrito = new FileWriter(llamador.archivocarrito, false);//Para eliminar el archivo y crearlo en blanco, dejar en false
            PrintWriter escrituraarchivocarrito = new PrintWriter(creaarchivocarrito);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);
            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            System.out.println("Articulos en venta:" + "\n");
            for (int x = 0; Arreglo.length > x; x++) {
                String[] partesparamostrar = Arreglo[x].split(",");
                System.out.println("Codigo: "+partesparamostrar[0]+"      Producto: "+partesparamostrar[1]+"        Precio: "+partesparamostrar[2]+"$         Stock: "+partesparamostrar[3]+"\n");
            }
            leer.nextLine();//limpieza del scanner
            do {
                System.out.println("\n" + "Elige tus productos escribiendo el nombre (Para dejar de comprar ingrese 'X'):" + "\n");
                productoelegido = leer.nextLine();
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {
                    String[] partesparaescritura = Arreglo[renglon].split(",");
                    if (partesparaescritura[1].equalsIgnoreCase(productoelegido)) {
                        System.out.println("Cuanto(s) "+productoelegido +"(s)"+" desea comprar?");
                        cantidadproductoelegido=leer.nextInt();
                        escrituraarchivocarrito.print(partesparaescritura[0]+","+partesparaescritura[1]+","+partesparaescritura[2]+","+cantidadproductoelegido+ "\n");
                        System.out.println(cantidadproductoelegido +" "+partesparaescritura[1]+"(s)"+ " agregado(s) al carrito" + "\n");
                        renglon=Arreglo.length+1;
                    }
                }
            } while (!(productoelegido.equalsIgnoreCase("x")));
            System.out.println("Has dejado de comprar");
            escrituraarchivocarrito.close();
            creaarchivocarrito.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
 
    public static void main(String[] args) throws IOException {
        menuRoles();
    }
    
}

