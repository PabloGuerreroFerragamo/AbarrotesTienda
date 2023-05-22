
package codigoAyuda;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuArticulos {
    private Scanner leer;

    public MenuArticulos() {
        leer = new Scanner(System.in);
    }

    public int getCodigo() {
        System.out.println("Introduce el código del producto:");
        int codigo = leer.nextInt();
        return codigo;
    }

    public String getNombre() {
        System.out.println("Introduce el nombre del producto:");
        String nombre = leer.nextLine();
        return nombre;
    }

    public double getPrecio() {
        System.out.println("Introduce el precio del producto:");
        double precio = leer.nextDouble();
        return precio;
    }

    public void agregarArticulo() {
        try {
            FileWriter fw = new FileWriter("consulta.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(getCodigo());
            pw.print("," + getNombre());
            pw.print("," + getPrecio() + "\n");
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Se ha añadido el siguiente artículo al inventario:");
        mostrarArticulos();
    }

    public void mostrarArticulos() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("consulta.txt"));
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modificarArticulo() {
        try {
            System.out.println("Introduce el código del artículo a modificar:");
            int codigoBuscado = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer

            List<String> lineas = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader("consulta.txt"));
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

            FileWriter fw = new FileWriter("consulta.txt", false);
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

    public void mostrarMenu() {
        System.out.println("----- MENÚ -----");
        System.out.println("1. Agregar artículo");
        System.out.println("2. Mostrar artículos");
        System.out.println("3. Modificar artículo");
        System.out.println("0. Salir");
        System.out.print("Ingresa tu opción: ");
        int opcion = leer.nextInt();

        switch (opcion) {
            case 1:
                agregarArticulo();
                break;
            case 2:
                mostrarArticulos();
                break;
            case 3:
                modificarArticulo();
                break;
            case 0:
                System.out.println("¡Hasta luego!");
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }

    public static void main(String[] args) {
        MenuArticulos menu = new MenuArticulos();
        menu.mostrarMenu();
    }
}