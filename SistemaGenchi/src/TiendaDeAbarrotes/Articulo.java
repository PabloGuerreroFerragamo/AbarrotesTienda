package TiendaDeAbarrotes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Articulo extends TiendaDeAbarrotes {
    public static File archivoticket= new File ("Ticket.txt");
    public static File archivocarrito = new File("Carrito.txt");
    public static File archivoarticulos = new File("Articulos.txt");
    double precio;//Variable de tipo Dobule Publica llamada "precio"
    int stock;

    public Articulo(int codigo, String nombre) {
        super(codigo, nombre);
    }

    public int get(int Codigo) {
        System.out.println("Introduce el codigo del nuevo producto:");
        codigo = leer.nextInt();
        return codigo;
    }

    public String get(String Nombre) {
        System.out.println("Introduce el nombre del nuevo producto:");
        nombre = leer.next();
        return nombre;
    }

    public double get(double Precio) {
        System.out.println("Introduce el precio del nuevo producto:");
        precio = leer.nextDouble();
        return precio;
    }

    public int get(int Cantidad, String Ola) {
        System.out.println("Introduce la cantidad de nuevo producto:");
        stock = leer.nextInt();
        return stock;
    }

    static void adquirirArticulos() throws IOException {
        String productoelegido = "";
        try {
            FileWriter crear = new FileWriter(archivoarticulos, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivoarticulos));
            FileWriter creaarchivocarrito = new FileWriter(archivocarrito, false);//Para eliminar el archivo y crearlo en blanco, dejar en false
            PrintWriter escrituraarchivocarrito = new PrintWriter(creaarchivocarrito);
            PrintWriter escribir = new PrintWriter(crear);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);
            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            System.out.println("Articulos en venta:" + "\n");
            for (int x = 0; Arreglo.length > x; x++) {
                System.out.println(Arreglo[x]);
            }
            leer.nextLine();
            do {
                System.out.println("\n" + "Elige tus productos escribiendo el nombre (Para dejar de comprar ingrese 'X'):" + "\n");
                productoelegido = leer.nextLine();
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {
                    String[] partes = Arreglo[renglon].split(",");
                    if (partes[1].equalsIgnoreCase(productoelegido)) {
                        escrituraarchivocarrito.print(Arreglo[renglon] + "\n");
                        System.out.println("Producto " + partes[1] + " agregado al carrito" + "\n");
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

    public static void verCarrito() {
        try {
            FileWriter crear = new FileWriter(archivocarrito, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivocarrito));
            PrintWriter escribir = new PrintWriter(crear);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);
            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            if (archivocarrito.length() == 0) {
                System.out.println("No has comprado aun" + "\n");
            } else {
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {
                    String[] partes = Arreglo[renglon].split(",");
                    System.out.println("Codigo: " + partes[0] + " Producto: " + partes[1] + " precio: " + partes[2] + " Cantidad: "+partes[3]+"\n");
                }
            }
        } catch (IOException ex) {

        }
    }

    public static void pagarCarrito() {
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss");
        String fechaimprimir = formatoFecha.format(new Date());
        double totalapagar=0;
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
            FileWriter borrar = new FileWriter(archivocarrito,false);
        } catch (IOException ex) {

        }
    }

    public void anadirArticulo() {

        try {

            FileWriter fw = new FileWriter("Articulos.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(get(1));
            pw.print("," + get(""));
            pw.print("," + get(1.0));
            pw.print("," + get(1, "") + "\n");

            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println("Se ha anadido el siguiente articulo al inventario:");
        mostrarArticulos();
    }

    public static void modificarArticulos() {
        try {
            System.out.println("Introduce el código del artículo a modificar:");
            int codigoBuscado = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer

            List<String> lineas = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("Articulos.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int codigo = Integer.parseInt(datos[0]);

                if (codigo == codigoBuscado) {
                    // Modificar el artículo
                    System.out.println("Introduce el nuevo nombre del artículo:");
                    String nuevoNombre = leer.nextLine();
                    System.out.println("Introduce el nuevo precio del artículo:");
                    double nuevoPrecio = leer.nextDouble();
                    linea = codigo + "," + nuevoNombre + "," + nuevoPrecio;
                }
                lineas.add(linea);
            }

            br.close();

            FileWriter fw = new FileWriter("Articulos.txt", false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            for (String line : lineas) {
                pw.println(line);
            }

            pw.close();

            System.out.println("El artículo se ha modificado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mostrarArticulos() {
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
            System.out.println(Arreglo[(Arreglo.length - 1)] + "\n");

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
            for (int x = 0; Arreglo.length > x; x++) {
                System.out.println(Arreglo[x]);
                if (x == Arreglo.length) {
                    System.out.println("\n");
                }
            }

        } catch (IOException e) {
        }

    }

    public static void eliminarArticulos() {
         try {
        BufferedReader br = new BufferedReader(new FileReader("Articulos.txt"));
        ArrayList<String> lineas = new ArrayList<>();

        String linea;
        int codigoEliminar;

        System.out.println("Introduce el código del artículo a eliminar:");
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

    } catch (IOException e) {
        e.printStackTrace();
    }
    }

    public void menuArticulosDueno() throws IOException {
        int respuestaduenoArticulos = 0;
        do {
            System.out.println("Que desea hacer con Articulos?");
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

}
