package TiendaDeAbarrotes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Articulo extends TiendaDeAbarrotes {
public static final String ANSI_BLACK = "\u001B[30m";
public static final String ANSI_RED = "\u001B[31m";
public static final String ANSI_GREEN = "\u001B[32m";
public static final String ANSI_YELLOW = "\u001B[33m";
public static final String ANSI_BLUE = "\u001B[34m";
public static final String ANSI_PURPLE = "\u001B[35m";
public static final String ANSI_CYAN = "\u001B[36m";
public static final String ANSI_WHITE = "\u001B[37m";
public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";


    public static File archivoticket= new File ("Ticket.txt");
    public static File archivocarrito = new File("Carrito.txt");
    public static File archivoarticulos = new File("Articulos.txt");
    public static File archivoproveedores = new File("Proveedores.txt");
    Proveedor provee = new Proveedor(1, "", "");
    double precio;//Variable de tipo Dobule Publica llamada "precio"
    int stock;

    public Articulo(int codigo, String nombre) {
        super(codigo, nombre);
    }

    public int get(int Codigo) {
        leer.nextLine();
        System.out.println("Introduce el codigo del nuevo producto:         "+ANSI_GREEN+"#Dueno#"+ANSI_GREEN);
        codigo = leer.nextInt();
        return codigo;
    }

    public String get(String Nombre) {
        leer.nextLine();//limpieza del scanner
        System.out.println("Introduce el nombre del nuevo producto:         "+ANSI_GREEN+"#Dueno#"+ANSI_GREEN);
        nombre = leer.next();
        return nombre;
    }

    public double get(double Precio) {
        leer.nextLine();//limpieza del scanner
        System.out.println("Introduce el precio del nuevo producto:         "+ANSI_GREEN+"#Dueno#"+ANSI_GREEN);
        precio = leer.nextDouble();
        return precio;
    }

    public int get(int Cantidad, String Ola) {
        leer.nextLine();//limpieza del scanner
        System.out.println("Introduce la cantidad de nuevo producto:        "+ANSI_GREEN+"#Dueno#"+ANSI_GREEN);
        stock = leer.nextInt();
        return stock;
    }
    
    static void adquirirArticulos() throws IOException {
        String productoelegido = "",copiarenglon="";
        int cantidadproductoelegido = 0, stockdisponiblearticuloelegido = 0,respuesta = 0;
        try {
            FileWriter creararchivoarticulos = new FileWriter(archivoarticulos, true);
            FileWriter creaarchivocarrito = new FileWriter(archivocarrito, false);//Para eliminar el archivo y crearlo en blanco, dejar en false
            BufferedReader brCablon = new BufferedReader(new FileReader(archivoarticulos));
            PrintWriter escrituraarchivocarrito = new PrintWriter(creaarchivocarrito);
            PrintWriter escrituraarchivoarticulos = new PrintWriter(creararchivoarticulos);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);
            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            System.out.println("Articulos en venta:" + "\n");
            for (int x = 0; Arreglo.length > x; x++) {
                String[] partesparamostrar = Arreglo[x].split(",");
                System.out.println("Codigo: " + partesparamostrar[0] + "      Producto: " + partesparamostrar[1] + "        Precio: " + partesparamostrar[2] + "$         Stock: " + partesparamostrar[3] + "\n");
            }
            leer.nextLine();//limpieza del scanner
            do {
                System.out.println("\n" + "Elige tus productos escribiendo el nombre (Para dejar de comprar ingrese 'X'):" + "\n");
                productoelegido = leer.nextLine();
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {
                    copiarenglon=Arreglo[renglon];
                    String[] partesparaescritura = Arreglo[renglon].split(",");
                    if (partesparaescritura[1].equalsIgnoreCase(productoelegido)) {
                        stockdisponiblearticuloelegido=Integer.parseInt(partesparaescritura[3]);
                        System.out.println("Cuanto(s) " + productoelegido + "(s)" + " desea comprar?");
                        cantidadproductoelegido = leer.nextInt();
                        if (cantidadproductoelegido < stockdisponiblearticuloelegido) {
                            System.out.println("Esta seguro de adquirir "+cantidadproductoelegido+" "+productoelegido+"(s)?:");
                            System.out.println("1.- Si      2.-No");
                            respuesta=leer.nextInt();
                            switch(respuesta){
                                case 1:
                                    FileWriter flasheararchivoarticulos = new FileWriter(archivoarticulos,false);
                                    escrituraarchivocarrito.print(partesparaescritura[0] + "," + partesparaescritura[1] + "," + partesparaescritura[2] + "," + cantidadproductoelegido + "\n");
                                    if(copiarenglon.equals(Arreglo[renglon])){
                                        Arreglo[renglon]=(partesparaescritura[0]+","+partesparaescritura[1]+","+partesparaescritura[2]+","+(Integer.parseInt(partesparaescritura[3])-cantidadproductoelegido)+","+partesparaescritura[4]);
                                        for(int r=0;Arreglo.length>r;r++){
                                            escrituraarchivoarticulos.print(Arreglo[renglon]);
                                        }
                                        escrituraarchivoarticulos.println();
                                        escrituraarchivoarticulos.close();
                                    }
                            System.out.println(cantidadproductoelegido + " " + partesparaescritura[1] + "(s)" + " agregado(s) al carrito" + "\n");
                            renglon = Arreglo.length + 1;
                                    break;
                                case 2:
                                    adquirirArticulos();
                                    break;
                            }
                        }
                    }
                }
            } while (!(productoelegido.equalsIgnoreCase("x")));
            System.out.println("Has dejado de comprar");
            escrituraarchivocarrito.close();
            creaarchivocarrito.close();
            creararchivoarticulos.close();
            menuCliente();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public static void verCarrito() {
        try {
            FileWriter crear = new FileWriter(archivocarrito, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivocarrito));
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);
            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            if (archivocarrito.length() != 0) {
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {
                    String[] partes = Arreglo[renglon].split(",");
                    System.out.println("Codigo: " + partes[0] + " Producto: " + partes[1] + " precio: " + partes[2] + " Cantidad: "+partes[3]+"\n");
                }
            } else {
                System.out.println("No has comprado aun" + "\n");
                
            }
        } catch (IOException ex) {

        }
    }

    public static void pagarCarrito() {
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss");
        String fechaimprimir = formatoFecha.format(new Date());
        double totalapagar=0;
        if((archivocarrito.exists())||archivocarrito.length()!=0){
        try {
            FileWriter creararchivoticket = new FileWriter(archivoticket, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivocarrito));
            PrintWriter escribirarchivoticket = new PrintWriter(creararchivoticket);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);
            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            System.out.println("Productos en carrito a pagar: ");
            for (int renglon = 0; Arreglo.length > renglon; renglon++) {
                    String[] partes = Arreglo[renglon].split(",");
                    escribirarchivoticket.print("\n"+"ABARROTES DON CRETACIO"+"\n");
                    escribirarchivoticket.println(fechaimprimir+"\n");
                    System.out.println("Codigo: " + partes[0] + " Producto: " + partes[1] + " precio: " + partes[2] + "$ Cantidad: "+partes[3]+"\n");
                    escribirarchivoticket.print("Codigo: " + partes[0] + " Producto: " + partes[1] + " precio: " + partes[2] + "$ Cantidad: "+partes[3]+"\n");
                    totalapagar+=(Double.parseDouble(partes[2])*Double.parseDouble(partes[3]));
                    System.out.println("Total a pagar: "+totalapagar+"$");
                }
            escribirarchivoticket.print("Total a pagar: "+totalapagar+"$\n");
            escribirarchivoticket.close();
            creararchivoticket.close();
            FileWriter borrararchivocarrito = new FileWriter(archivocarrito,false);
        } catch (IOException ex) {

        }
        }
    }

    public void anadirArticulo() {
        
        if (provee.numProveedores > 0||archivoproveedores.length()!=0) {

        try {

            FileWriter fw = new FileWriter("Articulos.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(get(1));
            if(validarCodigoArticulo(codigo)){
                System.out.println("El código ingresado ya existe, no se puede agregar el artículo.");
        return;
            }
            pw.print("," + get(""));
            pw.print("," + get(1.0));
            pw.print("," + get(1, ""));
            pw.print("," + provee.get("",1)+ "\n");

            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println("Se ha anadido el siguiente articulo al inventario:");
        mostrarArticulos();
    }else{
            System.out.println("No hay proveedores registrados, por favor registra primero un proveedor");
        }
    }

    public void modificarArticulos() {
        int codigoBuscado=0;
        int codigo =0;
        
        try {
            if(archivoarticulos.exists()&&archivoarticulos.length()!=0){
            System.out.println("Introduce el código del artículo a modificar:");
            codigoBuscado = leer.nextInt();
           

            List<String> lineas = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("Articulos.txt"));
            String linea="";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                codigo = Integer.parseInt(datos[0]);

                if (codigo == codigoBuscado) {
                    // Modificar el artículo
                    System.out.println("Introduce el nuevo nombre del artículo:");
                    String nuevoNombre = leer.nextLine();
                    System.out.println("Introduce el nuevo precio del artículo:");
                    double nuevoPrecio = leer.nextDouble();
                    provee.get("",1);
                    
                    linea = codigo + "," + nuevoNombre + "," + nuevoPrecio;
                    System.out.println("El artículo se ha modificado correctamente.");
                    lineas.add(linea);
                }
                else{
                    System.out.println("No se encontró ningún articulo con el código especificado.");
        
                
                    
                }
                
                
            }

            br.close();

            FileWriter fw = new FileWriter("Articulos.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (String line : lineas) {
                pw.println(line);
            }

            pw.close();

             }else{
            System.out.println("No hay Articulos registrados, por favor registra primero un ");
        }
            

        } catch (IOException e) {
            e.printStackTrace();
        }
       
    }

    public static void mostrarArticulos() {//No se usa :/
        try {
            if (!(archivoarticulos.exists())||archivoarticulos.length() == 0) {
                System.out.println("No has agregado proveedores aun");
            }
            else{
                FileWriter crear = new FileWriter(archivoarticulos, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivoarticulos));
            PrintWriter escribir = new PrintWriter(crear);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);

            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            System.out.println(Arreglo[(Arreglo.length - 1)] + "\n");
            }
            

        } catch (IOException e) {
        }

    }

    public static void consultarArticulos() {
        try {
            FileWriter crear = new FileWriter(archivoarticulos, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivoarticulos));
            PrintWriter escribir = new PrintWriter(crear);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);

            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            if(archivoarticulos.exists()&&archivoarticulos.length()!=0){
            System.out.println("_-_-_-_-PRODUCTOS EN INVENTARIO:_-_-_-_-"+"         #Dueno#"+ANSI_GREEN+"\n");
            for (int x = 0; Arreglo.length > x; x++) {
                String[] partesparamostrar = Arreglo[x].split(",");
                System.out.println("Codigo: " + partesparamostrar[0] + "      Producto: " + partesparamostrar[1] + "        Precio: " + partesparamostrar[2] + "$         Stock: " + partesparamostrar[3] +"    Empresa del proveedor: "+partesparamostrar[4]+"\n");
            }
            }
            else{
                System.out.println("\n"+ANSI_RED + "No hay productos en inventario, agrega algo"+ANSI_GREEN+"\n");
            }

        } catch (IOException e) {
        }

    }

    public static void eliminarArticulos() {
         try {
             if(archivoarticulos.exists()&&archivoarticulos.length()!=0){
        BufferedReader br = new BufferedReader(new FileReader("Articulos.txt"));
        ArrayList<String> lineas = new ArrayList<>();

        String linea;
        int codigoEliminar;

        System.out.println("Introduce el código del artículo a eliminar:            "+ANSI_GREEN+"#Dueno#"+ANSI_GREEN);
        codigoEliminar = leer.nextInt();

        boolean encontrado = false;

        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            int codigo = Integer.parseInt(partes[0]);

            if (codigo == codigoEliminar) {
                encontrado = true;
                System.out.println("El siguiente artículo ha sido eliminado:");
                System.out.println(linea);
            } else {
                lineas.add(linea);
            }
        }

        br.close();

        if (!encontrado) {
            System.out.println("No se encontró ningún artículo con el código especificado.");
        } else {
            PrintWriter pw = new PrintWriter(new FileWriter("Articulos.txt"));

            for (String articulo : lineas) {
                pw.println(articulo);
            }

            pw.close();
        }
         }else{
            System.out.println("No hay articulos registrados, por favor registra primero un ");
        }

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void menuArticulosDueno() throws IOException {
        int respuestaduenoArticulos = 0;
        do {
            leer.nextLine();
            System.out.println("Que desea hacer con Articulos?:             "+"#Dueno#"+ANSI_GREEN);
            System.out.println("1. Anadir articulos");
            System.out.println("2. Eliminar articulos");
            System.out.println("3. Modificar articulos");
            System.out.println("4. Ver articulos");
            System.out.println("5.-Regresar al menu anterior");
            respuestaduenoArticulos = leer.nextInt();
            switch (respuestaduenoArticulos) {
                case 1:
                    anadirArticulo();
                    break;
                case 2:
                    eliminarArticulos();
                    break;
                case 3:
                    modificarArticulos();
                    break;
                case 4:
                    consultarArticulos();
                    break;
                case 5:
                    menuDueno();
                    break;
                default:
                    System.out.println("Opcion Invalida, ingresa una opcion valida");
            }

        } while (respuestaduenoArticulos != 5);

    }
    public boolean validarCodigoArticulo(int codigo) {
        int codigoArticulo=0;
    try {
        BufferedReader br = new BufferedReader(new FileReader(archivoarticulos));

        String linea;

        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            codigoArticulo = Integer.parseInt(partes[0]);

            if (codigoArticulo == codigo) {
                br.close();
                return true; // El código ya existe
            }
        }

        br.close();

    } catch (IOException e) {
        e.printStackTrace();
    }

    return false; // El código no existe
}

}
