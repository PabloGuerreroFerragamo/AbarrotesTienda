package codigoAyuda;

import java.util.Arrays;
import java.util.Scanner;

class Libros {
    int code, año; // Se declaran variables para el código del libro y el año de publicación
    String title; // Se declara variable para el título del libro
    
    int getCode() {
        return code;
    }
    
    String getTitle() {
        return title;
    }
    
    int getAño() {
        return año;
    }

}

class Revistas {

}

public class PruebaObj {
    

    public static void main(String[] args) {
        PruebaObj polonia = new PruebaObj();
        
        int cantidadLibros = 0;
        int cantidadRevistas = 0;
        Libros arregloLibros[] = new Libros[0];
        Revistas arregloRevistas[] = new Revistas[0];

        int cod, añ;
        String tit;
        Scanner leer = new Scanner(System.in);
        int opc, opc1, opclib, leng;
        cantidadLibros++; // aumenta el número de libros
        System.out.println("-------------AGREGAR LIBRO----------");
        System.out.println("Ingrese el titulo:");
        tit = leer.next();
        System.out.println("Ingrese el año:");
        añ = leer.nextInt();
        System.out.println("Ingrese el codigo:");
        cod = leer.nextInt();
        Libros arregloGuardador[]; // arreglo auxiliar para copiar los datos del arreglo original
        arregloGuardador = Arrays.copyOf(arregloLibros, arregloLibros.length);
        arregloLibros = new Libros[cantidadLibros]; // arreglo original con una posición más
        // Copia los datos del arreglo auxiliar al arreglo original
        for (int i = 0; i < arregloLibros.length - 1; i++) {
            arregloLibros[i] = arregloGuardador[i];
        }
        // Agrega el nuevo libro al arreglo original
        arregloLibros[cantidadLibros - 1] = new Libros();
        System.out.println("Registro guardado...");

        for (int i = 0; i < arregloLibros.length; i++) {
            int sum = i + 1;
            System.out.println("---------------" + "Libro" + sum + "-----------------------");
            System.out.println("Titulo: " + arregloLibros[i]);
            System.out.println("Año: " + arregloLibros[i].año);
            System.out.println("Codigo: " + arregloLibros[i].code);
            System.out.println("-----------------------------------------");
        }
    }

}
