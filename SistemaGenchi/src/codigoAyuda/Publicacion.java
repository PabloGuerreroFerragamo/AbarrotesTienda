
package codigoAyuda;
import java.util.Scanner;

public class Publicacion {//Se crea la clase padre llamada Publicación

    //Declaracion de variables utilizadas en la clase
    int codigo;
    String titulo;
    int añoPublicacion;
    Scanner leer = new Scanner(System.in);
    Scanner leerint = new Scanner(System.in);

    //Constructor de la clase Publicacion
    public Publicacion(int codigo1, String titulo1, int añoPublicacion1) {
        codigo = codigo1;
        titulo = titulo1;
        añoPublicacion = añoPublicacion1;
    }

    //Metodos
    void getAnio() {
        System.out.println("Dame el año de publicacion:");
        añoPublicacion = leerint.nextInt();
    }

    void getCodigo() {
        System.out.println("Dame el codigo:");
        codigo = leerint.nextInt();
    }

    void getTitulo() {
        System.out.println("Dame el titulo:");
        titulo = leer.nextLine();
    }

    void tostring() {
        System.out.println("El codigo es: " + codigo + " , su titulo es: " + titulo + " , publicado en: " + añoPublicacion);
        System.out.println("");
    }
}//Cierre de la clase Publicacion

class Libros extends Publicacion { //Clase libros, hija de la clase Publicacion

    //Declaracion de variables utilizadas en la clase
    String textoPrestado = "No";
    int numero=1;
    
    
    Libros(int codigo, String titulo, int añoPublicacion) {
        super(codigo, titulo, añoPublicacion);
    }

    //Metodos
    void setPrestado() {
        System.out.println("El codigo es: " + codigo + " , su titulo es: " + titulo + " , publicado en: " + añoPublicacion + ", prestado: " + textoPrestado);
        System.out.println("");
    }
}//Cierre de la clase hija Libros

class Revistas extends Publicacion {//Clase Revistas, hija de la clase Publicacion
    
    //Declaracion de variables utilizadas en la clase
    int numero = 0;

    Revistas(int codigo, String titulo, int añoPublicacion, int numero) {
        super(codigo, titulo, añoPublicacion);
    }

    //Metodos
    void set() {
        System.out.println("El codigo es: " + codigo + " , su titulo es: " + titulo + " , publicado en: " + añoPublicacion);
        System.out.println("");
    }

}//Cierre de la clase hija Revistas

class Interfaz { //Clase interfaz

    public static void main(String[] args) {//Inicio de main
        
        //Declaracion de variables
        int respuesta;
        int responder;
        int respuesta3;
        int respuesta4 = 0;
        int respuesta5 = 0;
        int contador = 0;
        int contando = 0;
        int contaduria = 0;
        int contadorPrestado = 0;
        int contadorDevuelto=0;
        int utimoContador = 0;

        Libros libros1 = new Libros(1, "", 1);
        Revistas revistas1 = new Revistas(1, "", 1, libros1.numero);

        //Menu
        while (contador < 1) {
            System.out.println("---Interfaz---");
            System.out.println("Presiona 1 para agregar");
            System.out.println("Presiona 2 consultar");
            System.out.println("Presiona 3 para pedir");
            System.out.println("Presiona 4 para devolver");
            System.out.println("Presiona 5 para salir");
            respuesta = libros1.leerint.nextInt();

            if (respuesta == 1) {
                while (contando < 1) {
                    System.out.println("---Menu Agregar---");
                    System.out.println("Presiona 1 para agregar Libro");
                    System.out.println("Presiona 2 para agregar Revista");
                    System.out.println("Presiona 3 para salir");
                    responder = libros1.leerint.nextInt();

                    if (responder == 1) {
                        libros1.getAnio();
                        libros1.getCodigo();
                        libros1.getTitulo();
                        contando++;
                    }
                    if (responder == 2) {
                        revistas1.getAnio();
                        revistas1.getCodigo();
                        revistas1.getTitulo();
                        contando++;
                    }

                    if (responder == 3) {
                        contando++;
                    }
                }
                contando = 0;
            }
            if (respuesta == 2) {
                while (contaduria < 1) {
                    System.out.println("---Menu Consultas---");
                    System.out.println("Presiona 1 para consultar Libro");
                    System.out.println("Presiona 2 para consultar Revista");
                    System.out.println("Presiona 3 para salir");
                    respuesta3 = libros1.leerint.nextInt();

                    if (respuesta3 == 1) {
                        libros1.setPrestado();
                        contaduria++;
                    }
                    if (respuesta3 == 2) {
                        revistas1.tostring();
                        contaduria++;
                    }

                    if (respuesta3 == 3) {
                        contaduria++;
                    }
                }
                contaduria = 0;
            }
            if (respuesta == 3) {
                while (contadorPrestado < 1) {
                    System.out.println("---Menu Prestado---");
                    System.out.println("Deseas que se te preste el libro: " + libros1.titulo + "?");
                    System.out.println("1 -> SI");
                    System.out.println("2 -> NO");
                    respuesta4 = libros1.leerint.nextInt();
                    if (respuesta4 == 1) {
                        libros1.textoPrestado = "SI";
                        contadorPrestado++;
                    } else if (respuesta4 == 2) {
                        libros1.textoPrestado = "NO";
                        contadorPrestado++;
                    } else {
                        System.out.println("Esa no es una opcion");
                        contadorPrestado++;
                    }

                }
                contadorPrestado = 0;
            }
            if(respuesta == 4){
                
                while (contadorDevuelto < 1) {
                    System.out.println("---Menu Devolver---");
                System.out.println("Deseas devolver el libro: "+libros1.titulo+ "?");
                    System.out.println("1 -> SI");
                    System.out.println("2 -> NO");
                    respuesta5 = libros1.leerint.nextInt();
                    if (respuesta5 == 1) {
                        
                        libros1.textoPrestado = "NO";
                        contadorDevuelto++;
                    } else if (respuesta5 == 2) {
                        libros1.textoPrestado = "SI";
                        contadorDevuelto++;
                    } else {
                        System.out.println("Esa no es una opcion");
                        contadorDevuelto++;
                    }

                }
                contadorDevuelto = 0;
            }
            if (respuesta==5){
                contador++;
            }
            
        }//Se acaba el menu

    }//Cierre de main
}