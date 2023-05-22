package tienda_de_abarrotes;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

// Clase base para artículos
class Articulo {//Clase Articulo

    private int codigo; //Variable de tipo entera Publica llamada "codigo"
    private String nombre;//Variable de tipo String Publica llamada "nombre"
    private double precio;//Variable de tipo Dobule Publica llamada "precio"

    public Articulo(int codigo, String nombre, double precio) {//Constructor la clase Articulo que recibe 3 variables,
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String toString() {
        return "Codigo: " + codigo + "\nNombre: " + nombre + "\nPrecio: " + precio;
    }
}

// Clase para gestionar la tienda
class Tienda {

    private ArrayList<Articulo> inventario = new ArrayList<>();
    private ArrayList<Articulo> carrito = new ArrayList<>();
    private String archivoInventario = "inventario.dat";
    Scanner sc = new Scanner(System.in);

    // Constructor de la clase
    public Tienda() {
        // Cargar el inventario desde el archivo
        cargarInventario();
    }

    // Método para mostrar el menú
    public void mostrarMenu() {
        int opcion = 0;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Comprar");
            System.out.println("2. Anadir productos");
            System.out.println("3. Eliminar productos");
            System.out.println("4. Consultar productos");
            System.out.println("5. Modificar productos");
            System.out.println("6. Mostrar Carrito");
            System.out.println("7. Salir");
            System.out.print("Selecciona una opcion: ");

            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    comprar();
                    break;
                case 2:
                    guardarArchivo();
                    break;
                case 3:
                    eliminarProducto();
                    break;
                case 4:
                    consultarProductos();
                    break;
                case 5:
                    modificarProducto();
                    break;
                case 6:
                    System.out.println("Productos en el carrito:");
                    mostrarcarrito();
                    break;
                case 7:
                    guardarInventario();
                    System.out.println("Gracias por usar la tienda! Hasta pronto.");
                    break;
                default:
                    System.out.println("Opcion invalida, Inténtalo de nuevo.");
            }
        } while (opcion != 7);
    }

    // Método para comprar un producto
    public void comprar() {
        Scanner sc = new Scanner(System.in);
boolean x=false;
        while(x==false){
        System.out.println("Para dejar de comprar, Ingrese 'X'");
        System.out.println("Introduce el codigo del producto que deseas comprar:");
        String codigo = sc.nextLine();
        if(codigo.equalsIgnoreCase("X")){
         x=true;  
        }
        else{
        Articulo articulo = buscarArticulo(Integer.parseInt(codigo));

        if (articulo != null) {
            System.out.println("Has comprado el siguiente articulo:");
            System.out.println(articulo);
            carrito.add(articulo);
        } else {
            System.out.println("No se ha encontrado ningun articulo con ese codigo.");
        }
        
        }
        
        }//Fin del While
        System.out.println("Ha dejado de comprar...");
    }

    public void mostrarcarrito(){
        System.out.println(carrito);
    }
    // Método para añadir un producto al inventario
    public void anadirProducto() {

        System.out.println("Introduce el codigo del nuevo producto:");
        int codigo = sc.nextInt();

        // Comprobar si el artículo ya existe
        if (buscarArticulo(codigo) != null) {
            System.out.println("Ya existe un articulo con ese codigo.");

        }

        System.out.println("Introduce el nombre del nuevo producto:");
        String nombre = sc.next();

        System.out.println("Introduce el precio del nuevo producto:");
        double precio = sc.nextDouble();

        Articulo articulo = new Articulo(codigo, nombre, precio);
        inventario.add(articulo);

        System.out.println("Se ha anadido el siguiente articulo al inventario:");
        System.out.println(articulo);
    }

    public int codigo() {

        System.out.println("Introduce el codigo del nuevo producto:");
        int codigo = sc.nextInt();

        // Comprobar si el artículo ya existe
        if (buscarArticulo(codigo) != null) {
            System.out.println("Ya existe un artículo con ese codigo.");
        }
        return codigo;
    }

    public String nombre() {
        System.out.println("Introduce el nombre del nuevo producto:");
        String nombre = sc.next();
        return nombre;
    }

    public double precio() {
        System.out.println("Introduce el precio del nuevo producto:");
        double precio = sc.nextDouble();
        return precio;
    }

    //Guardar en un archivo txt
    public void guardarArchivo() {

        try {

            FileWriter fw = new FileWriter("consulta.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(codigo());
            pw.print("," + nombre());
            pw.print("," + precio()+"\n");

            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println("Se ha anadido el siguiente articulo al inventario:");
        mostarArchivo();
    }

    public void mostarArchivo() {
        File archivo = new File("consulta.txt");
        try {
            FileWriter crear = new FileWriter(archivo, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivo));
            PrintWriter escribir = new PrintWriter(crear);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);

            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            System.out.println(Arreglo[(Arreglo.length-1)]);

        } catch (IOException e) {
        }

    }

// Método para eliminar un producto del inventario
    public void eliminarProducto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el codigo del producto que deseas eliminar:");
        int codigo = sc.nextInt();

        Articulo articulo = buscarArticulo(codigo);

        if (articulo != null) {
            inventario.remove(articulo);
            System.out.println("Se ha eliminado el siguiente articulo del inventario:");
            System.out.println(articulo);
        } else {
            System.out.println("No se ha encontrado ningun articulo con ese codigo.");
        }
    }

// Método para consultar los productos del inventario
    public void consultarProductos() {
        System.out.println("Inventario:");
        File archivo = new File("consulta.txt");
        try {

            FileWriter crear = new FileWriter(archivo, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivo));
            PrintWriter escribir = new PrintWriter(crear);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);

            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            for(int x=0;Arreglo.length>x;x++){
                    System.out.println(Arreglo[x]);
                }
        } catch (IOException e) {
        }
    }

// Método para modificar un producto del inventario
    public void modificarProducto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el codigo del producto que deseas modificar:");
        int codigo = sc.nextInt();

        Articulo articulo = buscarArticulo(codigo);

        if (articulo != null) {
            System.out.println("Introduce el nuevo precio del producto:");
            double precio = sc.nextDouble();

            articulo.setPrecio(precio);

            System.out.println("El producto se ha modificado correctamente:");
            System.out.println(articulo);
        } else {
            System.out.println("No se ha encontrado ningún articulo con ese código.");
        }
    }

// Método para buscar un artículo por su código
    public Articulo buscarArticulo(int codigo) {
        for (Articulo articulo : inventario) {
            if (articulo.getCodigo() == codigo) {
                return articulo;
            }
        }
        return null;
    }

// Método para cargar el inventario desde el archivo
    public void cargarInventario() {
                try {
      FileReader fr = new FileReader("consulta.txt");
      BufferedReader br = new BufferedReader(fr);
      String linea;
      while ((linea = br.readLine()) != null) {
         String[] lineaseparada=linea.split(",");
         int code=Integer.parseInt(lineaseparada[0]);
         String name=lineaseparada[1];
         double price=Double.parseDouble(lineaseparada[2]);
         Articulo articulo = new Articulo(code, name, price);
        inventario.add(articulo);
      }
      br.close();
      fr.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
    }

// Método para guardar el inventario en el archivo
    public void guardarInventario() {
        try {
            FileOutputStream fileOut = new FileOutputStream(archivoInventario);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(inventario);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            System.out.println("No se ha podido guardar el inventario.");
        }
    }
}

// Clase principal del programa
public class TiendaArticulos {

    public static void main(String[] args) {
        Tienda tienda = new Tienda();
        tienda.mostrarMenu();
    }
}
