package TiendaDeAbarrotes;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Articulo extends TiendaDeAbarrotes {//Clase hija de la clase padre TiendaDeAbarrotes

    public static File archivoticket = new File("Ticket.txt");//Variables publicas y estaticas de tipo File Archivo de la clase Articulo
    public static File archivocarrito = new File("Carrito.txt");
    public static File archivoarticulos = new File("Articulos.txt");
    public static File archivoproveedores = new File("Proveedores.txt");
    public static File archivoventafinal = new File("VentaFinal.txt");

    Proveedor provee = new Proveedor(1, "", "");//Objeto de la clase Proveedor llamada "provee" con valores introducidos manualmente
    double precio;//Variable de tipo Double Publica llamada "precio"
    int stock;//Variable de tipo int Publica llamada "Stock"

    public Articulo(int codigo, String nombre) {//Constructor de la clase Articulo
        super(codigo, nombre);//Acceso a las variables padre de la clase padre "TiendaDeAbarrotes"
    }//Fin del constructor
//Los gets y polimorfismo

    public int get(int Codigo) {//Inicio del metodo get de tipo entero, recibe una variable de tipo entero
        leer.nextLine();//Limpieza del buffer del Scanner leer
        System.out.println("Introduce el codigo del nuevo producto:         " + ANSI_GREEN + "#Dueno#" + ANSI_GREEN);//Solicitud de introduccion del codigo
        codigo = leer.nextInt();//Asignacion de la respuesta del usuario a la variable codigo
        return codigo;//Devolucion del valor de la variable codigo
    }//Fin del metodo get de tipo entero que recibe una variable de tipo entera 

    public String get(String Nombre) {//Inicio del metodo get de tipo String, recibe una variable de tipo String
        leer.nextLine();//limpieza del scanner
        System.out.println("Introduce el nombre del nuevo producto:         " + ANSI_GREEN + "#Dueno#" + ANSI_GREEN);//Solicitud de introduccion del nombre
        nombre = leer.next();//Asignacion de la respuesta del usuario a la variable nombre
        return nombre;//Devolucion de la variable nombre
    }//Fin del metodo get de tipo String que recibe una variable de tipo String

    public double get(double Precio) {//inicio del metodo get de tipo double, recibe una variable de tipo Double
        leer.nextLine();//Limpieza del scanner
        System.out.println("Introduce el precio del nuevo producto:         " + ANSI_GREEN + "#Dueno#" + ANSI_GREEN);//Solicitud de introduccion del precio
        precio = leer.nextDouble();//Se asigna el valor ingresado por el usuario a la variable precio
        return precio;//Devolucion de la variable precio
    }//Fin del metodo get de tipo Double que recibe una variable de tipo Double

    public int get(int Cantidad, String Ola) {//Inicio del metodo get de tipo int, recibe dos variables de tipo int y String respectivamente
        leer.nextLine();//limpieza del scanner
        System.out.println("Introduce la cantidad de nuevo producto:        " + ANSI_GREEN + "#Dueno#" + ANSI_GREEN);//Solicitud de introduccion de la cantidad
        stock = leer.nextInt();//Se asigna el valor introducido por el usuario a la variable stock
        return stock;//Se devuelve la variable stock
    }//Fin del metodo get de tipo int que recibe dos variables, uno de tipo int y otro de tipo string

    public static void cerrarVenta() throws IOException {//Inicio del metodo cerrarVenta
        double ganancias = 0;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMMM-yyyy");//Se crea un objeto de tipo SimpleDateFormat llamado "formatofecha" y se insertan parametros de formato de fecha a su constructor de dicho objeto
        String fechaimprimir = formatoFecha.format(new Date());//Variable de tipo String llamado "fechaimprimir" que recibe como parametro una fecha nueva con el formato previamente insertado
        FileWriter creararchivoventafinal = new FileWriter(archivoventafinal, true);//Se crea un objeto de tipo FileWriter llamado crear archivoventafinal
        //acepta dos parametros, la direccion del fichero "archivoventafinal" y un valor booleano que definira si borra el archivo y lo crea en blanco o mantiene sus datos
        PrintWriter escrituraarchivoventafinal = new PrintWriter(creararchivoventafinal);
        BufferedReader lecturaarchivoticket = new BufferedReader(new FileReader(archivoticket));//Creacion de un objeto de tipo BufferedReader llamado "lecturaarchivoicket"
        BufferedReader lecturaarchivoventafinal = new BufferedReader(new FileReader(archivoventafinal));//Creacion de un objeto de tipo BufferedReader llamado "lecturaarchivoicket"
        Vector<String> vticket = new Vector(200);
        Vector<String> vventafinal = new Vector(2);
        String st;
        for (int i = 0; (st = lecturaarchivoticket.readLine()) != null; i++) {
            vticket.addElement(st);
        }
        for (int i = 0; (st = lecturaarchivoventafinal.readLine()) != null; i++) {
            vventafinal.addElement(st);
        }

        if (archivoticket.length() != 0||archivoticket.exists()) {
            String[] Arregloticket = vticket.toArray(new String[vticket.size()]);
            String[] partesrenglonfechaticket = Arregloticket[1].split(" ");
            if (archivoventafinal.length() != 0 || archivoventafinal.exists()) {
                String[] Arregloventafinal = vventafinal.toArray(new String[vventafinal.size()]);
                String[] partesrenglonfechaVentafinal = Arregloventafinal[0].split(":");
                if (partesrenglonfechaticket[0].equals(partesrenglonfechaVentafinal[1])||partesrenglonfechaVentafinal[1].equals(formatoFecha.format(new Date()))) {
                    System.out.println("\n" + ANSI_RED + "Ya se cerro la venta para este dia, se podra cerrar la venta el dia de manana\n" + ANSI_RESET);
                } else {
                    System.out.println("Venta Final del dia: " + fechaimprimir + "\n");//Mensaje
                    escrituraarchivoventafinal.print("Venta Final del día: " + fechaimprimir + "\n---------------------------------------------------------");
                    for (int T = 0; Arregloticket.length > T; T++) {
                        String[] partesticket = Arregloticket[T].split(":");
                        if (partesticket[0].equalsIgnoreCase("Total a pagar $")) {
                            ganancias += Double.parseDouble(partesticket[1]);
                        }
                    }
                    System.out.println(ANSI_RESET + "GANANCIAS TOTALES: " + ANSI_GREEN + ganancias + "$\n" + ANSI_RESET);
                    escrituraarchivoventafinal.println("\nGANANCIAS TOTALES: " + ganancias + "$" + "\n---------------------------------------------------------");
                    escrituraarchivoventafinal.close();
                    creararchivoventafinal.close();
                    FileWriter borrararchivoticket = new FileWriter(archivoticket, false);//Se crea un objeto de tipo FileWriter llamado creararchivoticket

                }
            }else{
                
            }
        } else {
            System.out.println("\n" + ANSI_RED + "No hay tickets guardados para cerrar la venta, no han llegado clientes :c\n" + ANSI_RESET);
        }
    }//Fin del metodo cerrarVenta

    static void adquirirArticulos() throws IOException {//Inicio del metodo adquirirArticulos
        String productoelegido = "", copiarenglon = "";//Variables privadasde tipo String
        int cantidadproductoelegido = 0, stockdisponiblearticuloelegido = 0, respuesta = 0;//Variables privadas de tipo entero
        try {//Inicio del Try-catch
            FileWriter creararchivoarticulos = new FileWriter(archivoarticulos, true);//Creacion de un objeto de tipo "FileWriter" llamado "creararchivoaticulos",mantiene los datos del fichero
            FileWriter creaarchivocarrito = new FileWriter(archivocarrito, true);//Creacion de un objeto de tipo "FileWriter llamado "crearacrhivocarrito"
            BufferedReader lecturaarchivoarticulos = new BufferedReader(new FileReader(archivoarticulos));//Creacion de un objeto de tipo BufferedReader llamado "lecturaarchivoarticulos"
            PrintWriter escrituraarchivocarrito = new PrintWriter(creaarchivocarrito);//Creacion de un objeo de tipo PrintWriter llamado "escrituraarchivocarrito"
            PrintWriter escrituraarchivoarticulos = new PrintWriter(creararchivoarticulos);//Creacion de un objeto de tipo PrintWriter llamado "escrituraarchivoarticulos"
            String st;//Creacion de una variable de tipo String
            Vector<String> v = new Vector(40);//Creacion de un vector llamado v que almacenara datos de tipo String con un tamano inicial de 40 posiciones
            for (int i = 0; (st = lecturaarchivoarticulos.readLine()) != null; i++) {//Inicio de una estructura repetitiva for
                v.addElement(st);//Se anade un renglon al vector "v" del archivo "archivoarticulos"
            }//Fin de la estructura repetitiva for
            String[] Arreglo = v.toArray(new String[v.size()]);//Se migran los renglones recopilados del archivoarticulos de tipo String del vector "v" a un arreglo de tipo String llamado "Arreglo"
            System.out.println("Articulos en venta:" + "\n");//Mensaje

            leer.nextLine();//limpieza del scanner
            do {//Inicio de una estructura repetitiva do while
                for (int x = 0; Arreglo.length > x; x++) {//Inicio de una estructura repetitiva for
                    String[] partesparamostrar = Arreglo[x].split(",");//Se divide el renglon contenido en el arreglo de tipo String con ayuda de los delimitadores ","
                    //Una vez dividido el renglon, se separa en 5 espacios y se almacenan en otro arreglo llamado "partesparamostrar"
                    System.out.println("Codigo: " + partesparamostrar[0] + "      Producto: " + partesparamostrar[1] + "        Precio: " + partesparamostrar[2] + "$         Stock: " + partesparamostrar[3] + "\n");//Se concatenan las partes divididas del arreglo partes para mostrar indicando que es cada parte y se muestra en un mensaje
                }//Fin del ciclo for
                System.out.println("\n" + "Elige tus productos escribiendo el nombre (Para dejar de comprar ingrese 'X'):" + "\n");//Mensaje solicitando al usuario que ingrese el nombre del producto a adquirir
                productoelegido = leerProducto.nextLine();//El nombre introducido por el usuario se asigna a la variable "productoelegido"
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {//Inicio de una estructura repetitiva for
                    copiarenglon = Arreglo[renglon];//Del arreglo "Arreglo" se copia el valor o renglon a una vaiable de tipo String llamado "copiarrenglon"
                    String[] partesparaescritura = Arreglo[renglon].split(",");//Se crea un arreglo de tipo string llamado "partesparaescritura" el cual, se asigna el renglon dividido con ayuda de los delimitadores ","
                    if (partesparaescritura[1].equalsIgnoreCase(productoelegido)) {//Estructura if que tiene una condicion: si el 2ndo registro del renglon dividido (nombre del producto) es igual (sin importar las mayusculas) al producto elegido por el usuario
                        stockdisponiblearticuloelegido = Integer.parseInt(partesparaescritura[3]);//Se obtiene el valor numero del registro 4 del renglon dividido usando el metodo "parseInt" y se asigna a la variable llamada "stockdisponiblearticuloelegido"
                        System.out.println("Cuanto(s) " + productoelegido + "(s)" + " desea comprar?");//Se pregunta al usuario la cantidad de producto elegido a comprar
                        cantidadproductoelegido = leer.nextInt();//Se asigna la respuesta del usuario a la variable llamada "cantidadproductoelegido"
                        if (cantidadproductoelegido <= stockdisponiblearticuloelegido) {//Estructura if con condicion: Si la cantidad del producto elegido es menor o igual al stock del producto elegido
                            System.out.println("Esta seguro de adquirir " + cantidadproductoelegido + " " + productoelegido + "(s)?:");//Preguntar al usuario si esta seguro de adquirir la cantidad elegida por el del producto elegido
                            System.out.println("1.- Si      2.-No");//Mensaje desplegando las respuestas disponibles
                            respuesta = leer.nextInt();//Se asigna la respuesta del usuario a la variable llamada respesta de tipo int
                            switch (respuesta) {//Inicio de una estructura selectiva Switch
                                case 1://Caso uno
                                    FileWriter flasheararchivoarticulos = new FileWriter(archivoarticulos, false);//Objeto de tipo FileWriter que elimina los datos del archivo "Articulos"
                                    //Este proceso se puede realizar sin riesgo de perder los articulos anadidos, pues el arreglo llamado "Arreglo" de tipo string contiene una copia fiel del archivo "Articulos"
                                    escrituraarchivocarrito.print(partesparaescritura[0] + "," + partesparaescritura[1] + "," + partesparaescritura[2] + "," + cantidadproductoelegido + "\n");//Se escribe un renglon concatenado al archivo "Carrito "que contiene el codigo, el nombre, el precio y el stock, todos estos provenientes del arreglo llamado "partes para escritura"
                                    if (copiarenglon.equals(Arreglo[renglon])) {//Estructura if con una condicion: Si la copiarenglon es igual a la posicion indicada del arreglo "Arreglo" (Copia fiel del archivoArticulos)
                                        Arreglo[renglon] = (partesparaescritura[0] + "," + partesparaescritura[1] + "," + partesparaescritura[2] + "," + (Integer.parseInt(partesparaescritura[3]) - cantidadproductoelegido) + "," + partesparaescritura[4]);
                                        //Se sobreescribe en la posicion indicada del arreglo "Arreglo" el renglon concatenado del producto elegido del usuario, el cual se realiza la resta de la cantidaddelproductoelegido por el usuario al stockdisponibledelproductoelegido
                                    }//Fin de la estructura if
                                    System.out.println(cantidadproductoelegido + " " + partesparaescritura[1] + "(s)" + " agregado(s) al carrito" + "\n");//Mensaje avisando al usuario la cantidad y el nombre del producto elegido por el
                                    break;//Ruptura del Switch
                                case 2://Caso 2
                                    adquirirArticulos();//Se llama al metodo Adquirir articulos, Esto anula la resta del stock con el producto que eligio el usuario
                                    break;//Ruptura del Switch
                            }//Fin del Switch
                        }//Fin del if secundario
                    }//Fin del if primario
                }//Fin del for
            } while (!(productoelegido.equalsIgnoreCase("x")));//Fin del do while con una condicion: si el productoelegido NO es igual al caracter 'x'
            System.out.println("Has dejado de comprar");//Se avisa al usuario que dejo de comprar
            for (int r = 0; Arreglo.length > r; r++) {//Inicio de la estructura repetitiva for
                escrituraarchivoarticulos.println(Arreglo[r]);//Se imprime la copia fiel del archivo articulos con el articulo elegido ya actualizado con su stock restado por la cantidad elegida por el usuario
            }//Fin de la estructura repetitiva for
            escrituraarchivocarrito.close();//Se cierra el objeto llamado "escrituraarchivocarrito"
            escrituraarchivoarticulos.close();//Se cierra el objeto llamado "escrituraarchivoarticulos"
            creaarchivocarrito.close();//Se cierra el objeto llamado "creaarchivocarrito"
            creararchivoarticulos.close();//Se cierra el objeto llamado "creararchivoarticulos"
            menuCliente();//Se llama el metodo menuCliente

        } catch (Exception e) {//Se cierra el try y se inicia el catch para atrapar el error que surga al ejecutar las lineas anteriores
            JOptionPane.showMessageDialog(null, e);
        }//Se cierra el catch

    }//Fin del metodo adquirirArticulos

    public static void verCarrito() {//Inicio del metodo verCarrito
        try {//Inicio del Try
            BufferedReader lecturaarchivocarrito = new BufferedReader(new FileReader(archivocarrito));//Creacion de un objeto de tipo BufferedReader llamado "lecturaarchivoarticulos"
            String st;//Creacion de una variable de tipo String
            Vector<String> v = new Vector(40);//Creacion de un vector llamado v que almacenara datos de tipo String con un tamano inicial de 40 posiciones
            for (int i = 0; (st = lecturaarchivocarrito.readLine()) != null; i++) {//Inicio de una estructura repetitiva for
                v.addElement(st);//Se anade un renglon al vector "v" del archivo "archivocarrito"
            }//Fin de la estructura repetitiva for
            String[] Arreglo = v.toArray(new String[v.size()]);//Se migran los renglones recogidos del archivocarrito de tipo String del vector "v" a un arreglo de tipo String llamado "Arreglo"
            if (archivocarrito.length() != 0) {//Estructura if con condicion: si el tamano del archivocarrito es diferente a 0 cero
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {//Inicio de una estructura repetitiva for
                    String[] partes = Arreglo[renglon].split(",");//Se divide el renglon y se almacena cada registro dividido a un arreglo llamado "partes"
                    System.out.println("Codigo: " + partes[0] + " Producto: " + partes[1] + " precio: " + partes[2] + " Cantidad: " + partes[3] + "\n");//Se muestra en forma de mensaje el renglon dividido
                    //y concatenando su registro al tipo correspondiente para su interpretacion
                }//Fin de la estructura repetitiva for
            } else if (archivocarrito.length() == 0) { //Si la condicion mencionada no es cumplida:
                System.out.println(ANSI_RED + "NO has comprado aun" + ANSI_RESET);//Se notifica al usuario que no ha comprado aun
            }//Fin de else del if
        } catch (IOException ex) {//Fin del Try e inicio del catch paa atrapar el error que llege a surgir al ejecutar las lineas de codigo anteriores

        }//Fin del catch
    }//Fin del metodo vercarrito

    public static void pagarCarrito() {//Inicio del metodo pagarCarrito
        Date fecha = new Date();//Se crea un objeto de tipo Date llamado "fecha"
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMMM-yyyy hh:mm:ss");//Se crea un objeto de tipo SimpleDateFormat llamado "formatofecha" y se insertan parametros de formato de fecha a su constructor de dicho objeto
        String fechaimprimir = formatoFecha.format(new Date());//Variable de tipo String llamado "fechaimprimir" que recibe como parametro una fecha nueva con el formato previamente insertado
        double totalapagar = 0;//Variable de tipo double llamado totalapagar
        if (!(archivocarrito.exists()) || archivocarrito.length() != 0) {//Primer estructura if con condicion: Si el archivocarrito NO existe Y si el tamano del archivocarrito es diferente a 0 cero
            try {//Inicio del try
                FileWriter creararchivoticket = new FileWriter(archivoticket, true);//Creacion de un objeto de tipo "FileWriter" llamado "creararchivoticket",mantiene los datos del fichero
                BufferedReader lecturaarchivocarrito = new BufferedReader(new FileReader(archivocarrito));//Creacion de un objeto de tipo BufferedReader llamado "lecturaarchivocarrito"
                PrintWriter escribirarchivoticket = new PrintWriter(creararchivoticket);//Creacion de un objeto de tipo PrintWriter llamado "escribirarchivoticket"
                String st;//Creacion de una variable de tipo String
                Vector<String> v = new Vector(40);//Creacion de un vector llamado v que almacenara datos de tipo String con un tamano inicial de 40 posiciones
                for (int i = 0; (st = lecturaarchivocarrito.readLine()) != null; i++) {//Inicio de una estructura repetitiva for
                    v.addElement(st);//Se anade un renglon al vector "v" del archivo "archivocarrito"
                }//Fin de la estructura repetitiva for
                String[] Arreglo = v.toArray(new String[v.size()]);//Se migran los renglones recopilados del archivocarrito de tipo String del vector "v" a un arreglo de tipo String llamado "Arreglo"
                System.out.println("Productos en carrito a pagar: ");//Mensaje
                escribirarchivoticket.print("ABARROTES DON CRETACIO" + "\n");//Se imprime la cabecera del ticket al archivo ticket
                escribirarchivoticket.println(fechaimprimir + "\n" + "---------------------------------------------------------");//Se imprime la variable "fechaimprimir" que contiene la fecha en la que fue impreso el ticket
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {//Inicio de un ciclo for
                    String[] partes = Arreglo[renglon].split(",");//Se crea un arreglo de tipo string llamado "partesparaescritura" el cual, se asigna el renglon dividido con ayuda de los delimitadores ","
                    System.out.println("Codigo: " + partes[0] + "   Producto: " + partes[1] + " Precio: " + partes[2] + "$  Cantidad: " + partes[3] + "\n");//Se muestra un renglon con la informacion del producto adquirido por el cliente 
                    escribirarchivoticket.print("Codigo: " + partes[0] + " Producto: " + partes[1] + " precio: " + partes[2] + "$ Cantidad: " + partes[3] + "\n");//Se imprime un renglon con la informacion del producto adquirido por el cliente al archivoticket
                    totalapagar += (Double.parseDouble(partes[2]) * Double.parseDouble(partes[3]));//Se va concatenando y almacenando a la variable totalapagar el valor de la multiplicacion del precio de cada producto por la cantidadelegida por el usuario
                }//Se termina la estructura for hasta que ya no haya productos en carrito
                escribirarchivoticket.print("---------------------------------------------------------" + "\n" + "Total a pagar $: " + totalapagar + "\n" + "_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\n");//Se imprime el precio total al archivoticket
                System.out.println("Total a pagar: " + totalapagar + "$");//Se muestra el precio total
                escribirarchivoticket.close();//Se cierra el objeto llamado "escribirarchivoticket"
                creararchivoticket.close();//Se cierra el objeto llamado "creararchivoticket"
                FileWriter borrararchivocarrito = new FileWriter(archivocarrito, false);//Se crea un objeto de tipo FileWriter llamado "borrararchivocarrito", su funcion es borrar los datos del archivocarrito una vez que el carrito fue pagado
            } catch (IOException ex) {// Fin del Try y se atrapa el error que pueda surgir al ejecutar las lineas de codigo previas
            }
        } else {//Fin del if y comienzo del else del primer if
            System.out.println(ANSI_RED + "NO has comprado aun" + ANSI_RESET);//Mensaje al usuario advirtiendo que no ha comprado aun
        }//Fin del else

    }//Fin del metodo pagarCarrito

    public void anadirArticulo() {//Inicio del metodo anadirArticulo
        if (provee.numProveedores > 0 || archivoproveedores.length() != 0) {//Primer estructura If con condicion: Si la variable "numProveedores" es mayor a cero 0 Y si el tamano del "archivoproveedores" es diferente a cero 0

            try {//Abertura del Try

                FileWriter fw = new FileWriter(archivoarticulos, true);//Creacion de un objeto de tipo "FileWriter" llamado "fw",mantiene los datos del fichero llamado "archivoarticulos"
                BufferedWriter bw = new BufferedWriter(fw);//Creacion de un objeto de tipo BufferedWriter llamado "bw"
                PrintWriter pw = new PrintWriter(bw);//Creacion de un objeo de tipo PrintWriter llamado "pw"
                pw.print(get(1));//Llamada al metodo get de tipo entero que recibe un parametro de tipo entero, al terminar de llamar escribe al archivo articulos lo que devuelve el metodo, es decir, el codigo
                if (validarCodigoArticulo(codigo)) {//Segunda estructura if con condicion: Si la validacion del codigo es verdadera
                    System.out.println("El código ingresado ya existe, no se puede agregar el artículo.");//Mensaje al usuario
                    return;//Se regresa
                }//Fin de la segunda estructura if
                pw.print("," + get(""));//Llamada al metodo get de tipo String que recibe un parametro de tipo String, al terminar el metodo, se imprime al archivoarticulos lo que devuelve el metodo
                pw.print("," + get(1.0));//Llamada al metodo get de tipo Double que recibe un parametro de tipo Double, al terminar el metodo, se imprime al archivoarticulos lo que devuelve el metodo
                pw.print("," + get(1, ""));//Llamada al metodo get de tipo int que recibe dos parametros de tipo int y String respectivamente, al terminar el metodo, se imprime al archivoarticulos lo que devuelve el metodo
                pw.print("," + provee.get("", 1) + "\n");//Llamada al metodo get de tipo String que recibe dos parametros de tipo String y entero respectivamente, al terminar el metodo, se imprime al archivoarticulos lo que devuelve el metodo

                pw.close();//Se cierra el objeto llamado "pw"
            } catch (Exception e) {//Fin del Try y apertura del catch
                JOptionPane.showMessageDialog(null, e);
            }//Fin del catch
            System.out.println("Se ha anadido el siguiente articulo al inventario:");//Mensaje de confirmacion
            mostrarArticulos();//Se llama al metodo mostrarArticulos
        } else {//Fin de la primera estructura if y abertura del else de dciha estructura
            System.out.println(ANSI_RED + "No hay proveedores registrados, por favor registra primero un proveedor" + ANSI_GREEN);//Mensaje de advertencia
        }//Fin del else
    }//Fin del metodo anadirArticulo

    public void modificarArticulos() {//Inicio del metodo modificarArticulos
        int codigoBuscado = 0;//Variables
        int codigo = 0;

        try {//Inicio del Try
            if (archivoarticulos.exists() && archivoarticulos.length() != 0) {//Primer estructura if con condicion: si el archivoarticulos existe O el tamano del archivoarticulos es diferente a cero 0
                System.out.println("Introduce el código del artículo a modificar:");//Mensaje de solicitud de entrada de datos
                codigoBuscado = leer.nextInt();//Se asigna la respuesta del usuario a la variable llamada codigobuscado

                List<String> lineas = new ArrayList<>();//Creacion de una lista de tipo String llamada "lineas"

                BufferedReader br = new BufferedReader(new FileReader("Articulos.txt"));//Creacion de un objeto de tipo BufferedReader llamado "br"
                String linea = "";//Variable de tipo String llamada "linea"
                while ((linea = br.readLine()) != null) {//Inicio de estructura repetitiva while con condicion: mientras la linea leida sea diferente a "nulo " o "vacio"
                    String[] datos = linea.split(",");//Se crea un arreglo de tipo string llamado "datos" el cual, se asigna el renglon dividido con ayuda de los delimitadores ","
                    codigo = Integer.parseInt(datos[0]);//El primer registro dividido del renglon se parsea de String a entero y se asigna a la variable llamada "codigo"

                    if (codigo == codigoBuscado) {//Segunda Estructura if con condicion: si el codigo es igual al codigobuscado
                        // Se modifica el articulo
                        System.out.println("Introduce el nuevo nombre del artículo:");//Mensaje de solicitud del nuevo nombre del articulo a modificar
                        String nuevoNombre = leer.nextLine();//Se crea y se asigna la respuesta del usuario a una variable llamada "nuevoNombre"
                        System.out.println("Introduce el nuevo precio del articulo:");//Mensaje de solicitud del nuevo precio del articulo a modificar
                        double nuevoPrecio = leer.nextDouble();//Se crea y se asigna la respuesta del usuario a una variable llamada "nuevoPrecio" 
                        provee.get("", 1);//Se llama al metodo get de la clase proveedor

                        linea = codigo + "," + nuevoNombre + "," + nuevoPrecio;//Se asigna un nuevo valor a la variable "linea" que concatena el codigo, el nuevoNombre y el nuevoPrecio separados por ","
                        System.out.println("El articulo se ha modificado correctamente.");//Mensaje de aviso al usuario
                        lineas.add(linea);//Se anade la linea modificada a la lista "lineas"
                    } else {//Fin de la segunda estructura if y apertura del else de dicha estructura
                        System.out.println("No se encontró ningún articulo con el codigo especificado.");//Mensaje de advetencia al usuario
                    }//Fin el else

                }//Fin del ciclo while

                br.close();//Se cierra el Objeto llamado "br"
                FileWriter fw = new FileWriter("Articulos.txt", false);//Se crea un objeto de tipo FileWriter la cual su funcion es borrar y crear en blanco el archivoarticulos
                BufferedWriter bw = new BufferedWriter(fw);//Se crea un objeto de tipo BufferedWriter llamado "bw"
                PrintWriter pw = new PrintWriter(bw);//Se crea un objeto de tipo PrintWriter llamado "pw"
                for (String line : lineas) {//Inicio de un ciclo for 
                    pw.println(line);//Imprime al archivoarticulos cada linea de la lista llamada "lineas" hasta su fin de dicha lista
                }//Fin del ciclo for

                pw.close();//Se cierra el objeto llamado "pw"

            } else {//Fin de la primer estructura if y apertura del else de dicha estructura if
                System.out.println("No hay Articulos registrados, por favor registra primero uno");//Mensaje de aviso al usuario
            }//Fin del else
        } catch (IOException e) {//Fin del Try y apertura del catch para atrapar el error que llegue a surgir al ejecutar las lineas de codigo anteriores
            e.printStackTrace();
        }//Fin del catch

    }//Fin del metodo modificarArticulos

    public static void mostrarArticulos() {//Inicio del metodo mostrarArticulos
        try {//Inicio del Try
            FileWriter creararchivoarticulos = new FileWriter(archivoarticulos, true);//Creacion de un objeto de tipo "FileWriter" llamado "creararchivoarticulos",mantiene los datos del fichero
            if (!(archivoarticulos.exists()) || archivoarticulos.length() == 0) {//Primer estructura if con condicion: Si el archivoarticulos NO existe Y el tamano del archivoarticulos es igual a cero 0
                System.out.println("No has agregado proveedores aun");//Mensaje de advertencia al usuario
            } else {//Fin de la primer estructura if y apertura al else de dicha estructura
                BufferedReader lecturaarchivoarticulos = new BufferedReader(new FileReader(archivoarticulos));//Creacion de un objeto de tipo BufferedReader llamado "lecturaarchivoarticulos"
                String st;//Variable de tipo String llamada "st"
                Vector<String> v = new Vector(40);//Creacion de un vector llamado "v" que almacenara datos de tipo String con un tamano inicial de 40 posiciones
                for (int i = 0; (st = lecturaarchivoarticulos.readLine()) != null; i++) {//Inicio de una estructura repetitiva for
                    v.addElement(st);//Se anade un renglon al vector "v" del archivo "archivoarticulos"
                }//Fin del ciclo for
                String[] Arreglo = v.toArray(new String[v.size()]);//Se migran los renglones recopilados del archivoarticulos de tipo String del vector "v" a un arreglo de tipo String llamado "Arreglo"
                System.out.println(Arreglo[(Arreglo.length - 1)] + "\n");//Se muestra el ultimo renglon de la copia fiel del archivoarticulos
            }//Fin del else de la primer estructura

        } catch (IOException e) {//Fin del Try y abertura del catch
        }//Fin del catch
    }//Fin del metodo mostrarArticulos

    public static void consultarArticulos() {//Inicio del metodo consultarArticulos
        try {//Inicio del Try
            BufferedReader lecturaarchivoarticulos = new BufferedReader(new FileReader(archivoarticulos));//Creacion de un objeto de tipo BufferedReader llamado "lecturaarchivoarticulos"
            String st;//Creacion de una variable de tipo String
            Vector<String> v = new Vector(40);//Creacion de un vector llamado v que almacenara datos de tipo String con un tamano inicial de 40 posiciones
            for (int i = 0; (st = lecturaarchivoarticulos.readLine()) != null; i++) {//Inicio de una estructura repetitiva for
                v.addElement(st);//Se anade un renglon al vector "v" del archivo "archivoarticulos"
            }//Fin de la estructura repetitiva for
            String[] Arreglo = v.toArray(new String[v.size()]);//Se migran los renglones recopilados del archivoarticulos de tipo String del vector "v" a un arreglo de tipo String llamado "Arreglo"
            if (archivoarticulos.exists() && archivoarticulos.length() != 0) {//Primer estructura If con condicion: Si el archivoarticulos existe O el tamano del archivoarticulos es diferente de cero 0
                System.out.println("_-_-_-_-PRODUCTOS EN INVENTARIO:_-_-_-_-" + "         #Dueno#" + ANSI_GREEN + "\n");//Cabecera
                for (int x = 0; Arreglo.length > x; x++) {//Inicio de ciclo for 
                    String[] partesparamostrar = Arreglo[x].split(",");//Se divide el renglon contenido en el arreglo de tipo String con ayuda de los delimitadores ","
                    System.out.println("Codigo: " + partesparamostrar[0] + "      Producto: " + partesparamostrar[1] + "        Precio: " + partesparamostrar[2] + "$         Stock: " + partesparamostrar[3] + "    Empresa del proveedor: " + partesparamostrar[4] + "\n");
                    //Se muestra cada producto concatenado con su respectiva informacion
                }//Fin del ciclo for
            } else {//Fin d la primer estructura if e inicio de else de dicha estructura if
                System.out.println("\n" + ANSI_RED + "No hay productos en inventario, agrega algo" + ANSI_GREEN + "\n");//Advertencia al usuario 
            }//Fin del else

        } catch (IOException e) {//Fin del Try e inicio del catch
        }//Fin del catch

    }//Fin del metodo consultar Articulos

    public static void eliminarArticulos() {//Inicio del metodo eliminarArticulos
        try {//Inicio del Try-Catch
            if (archivoarticulos.exists() && archivoarticulos.length() != 0) {//Primer estructura if con condicion: Si el archivoarticulos existe O el tamano del archivoarticulos es diferente de cero 0
                BufferedReader br = new BufferedReader(new FileReader("Articulos.txt"));//Creacion de un objeto de tipo BufferedReader llamado "br"
                ArrayList<String> lineas = new ArrayList<>();//Creacion de un ArrayList de tipo String llamada "Lineas"

                String linea;//Variable de tipo String llamada "linea"
                int codigoEliminar;//Variable de tipo int llamada "codigoEliminar"
                System.out.println("Introduce el código del artículo a eliminar:            " + ANSI_GREEN + "#Dueno#" + ANSI_GREEN);//Mensaje de solicitud del codigo del articulo a eliminar
                codigoEliminar = leer.nextInt();//Se asigna la respuesta del usuario a la variable codigoEliminar
                boolean encontrado = false;//Creacion e inicializacion de una variable de tipo boolean llamada "encontrado" en false
                while ((linea = br.readLine()) != null) {//Inicio de estructura repetitiva while con condicion: mientras la linea leida sea diferente a "nulo " o "vacio" 
                    String[] partes = linea.split(",");//Se crea un arreglo de tipo string llamado "partes" el cual, se asigna el renglon dividido con ayuda de los delimitadores ","
                    int codigo = Integer.parseInt(partes[0]);//Creacion y asignacion de valor a la variable llamada "codigo" que convierte de Sring a entero el primer registro del renglon dividido que representa el codigo del producto a eliminar
                    if (codigo == codigoEliminar) {//Segunda estuctura If con condicion: Si el codigo es igual al codigoeliminar
                        encontrado = true;//Asignacion de valor "True" a la variable "encontrado"
                        System.out.println("El siguiente artículo ha sido eliminado:");//Mensaje de aviso al usuario
                        System.out.println(linea);//Se imprime el renglon que fue eliminado
                    } else {//Fin de la segunda estructura if y apertura del else de dicha estructura if
                        lineas.add(linea);//Se anade la linea o renglon al ArrayList llamado lineas
                    }//Fin del else
                }//Fin de la estructura repetitiva while
                br.close();//Cierre del objeto llamado "br"
                if (!encontrado) {//Tercer estructura if con la condicion: Si no es encontrado el codigo proporcionado
                    System.out.println("No se encontró ningún artículo con el código especificado.");//Aviso al usuario
                } else {//Cierre del if e inicio del else de esta estructura if
                    PrintWriter pw = new PrintWriter(new FileWriter("Articulos.txt"));//Creacion de un objeto llamado "pw" de tipo PrintWriter
                    for (String articulo : lineas) {//Inicio de un ciclo for 
                        pw.println(articulo);//Imprime al archivoarticulos cada linea del archivoarticulos
                    }//Cierre del ciclo for

                    pw.close();//Cierre del objeto "pw"
                }//Fin del else de la tercer estructura if
            } else {//Inicio del else de la primer estructura if 
                System.out.println("No hay articulos registrados, por favor registra primero uno");//Mensaje de aviso al usuario
            }//Cierre del else de la primer estructura if

        } catch (IOException e) {//Cierre del Try-Catch
            e.printStackTrace();
        }//Cierre del Catch
    }//Fin del metodo eliminarArticulo

    public void menuArticulosDueno() throws IOException {//Inicio del metodo menuArticulosDueno
        int respuestaduenoArticulos = 0;//Creacion e inicializacion de una variable llamada "respuestaduenoarticulos" de tipo entero
        do {//Inicio de un ciclo do while
            leer.nextLine();//Limpieza del buffer del Scanner leer
            System.out.println("Que desea hacer con Articulos?:             " + "#Dueno#" + ANSI_GREEN); //Menu de articulos
            System.out.println("1. Anadir articulos");
            System.out.println("2. Eliminar articulos");
            System.out.println("3. Modificar articulos");
            System.out.println("4. Ver articulos");
            System.out.println("5.-Regresar al menu anterior");
            respuestaduenoArticulos = leer.nextInt();//Asignacion de la respuesta del dueno a la variable respuestaduenoarticulos
            switch (respuestaduenoArticulos) {//Inicio del Switch
                case 1://Caso uno
                    anadirArticulo();//Llamada al metodo anadirArticulo
                    break;//Ruptura de la estructura selectiva Switch
                case 2://Caso dos
                    eliminarArticulos();//Llamada al metodo eliminarArticulos
                    break;//Ruptura de la estructura selectiva Switch
                case 3://Caso tres
                    modificarArticulos();//Llamada al metodo modificarArticulos
                    break;//Rompimiento del Switch
                case 4://Caso cuatro
                    consultarArticulos();//Llamada al metodo consultarArticulos
                    break;//Rompimiento del Switch
                case 5://Caso cinco
                    menuDueno();//Llamada al menudueno
                    break;//Rompimiento del Switch
                default://Lineas de codigo a ejecutar en caso de que el usuario ingrese una opcion incorrecta
                    System.out.println("Opcion Invalida, ingresa una opcion valida");//Mensaje de advertencia al usuario
            }//Fin del Switch

        } while (respuestaduenoArticulos != 5);//Fin del ciclo Do While

    }//Fin del metodo menuArticulosDueno

    public boolean validarCodigoArticulo(int codigo) { //Inicio del metodo validarCodigoArticulo que acepta un parametro de tipo int
        int codigoArticulo = 0;//Inicializacion y creacion de una variable de tipo entero llamada codigoArticulo
        try {//Inicio de Try-Catch
            BufferedReader br = new BufferedReader(new FileReader(archivoarticulos));//Creacion de un objeto de tipo BufferedReader para la lectura de un archivo
            String linea;//Creacion de una variable llamada "linea" de tipo String

            while ((linea = br.readLine()) != null) {//Inicio de estructura repetitiva while con condicion: mientras la linea leida sea diferente a "nulo " o "vacio"
                String[] partes = linea.split(",");//Se crea un arreglo de tipo string llamado "partes" el cual, se asigna el renglon dividido con ayuda de los delimitadores ","
                codigoArticulo = Integer.parseInt(partes[0]);//Asignacion de valor a la variable llamada "codigoArticulo" que convierte de String a entero el primer registro del renglon dividido que representa el codigo del producto a validar

                if (codigoArticulo == codigo) {//Primera estructura if con la condicion: Si el codigoArticulo es igual al codigo
                    br.close();//El objeto llamado "br" se cierra
                    return true; // Si el codigo existe, se devuelve un booleano "True"
                }//Cierre del primer if
            }//Ciere del ciclo while

            br.close();//Ciere del objeto llamado "br"

        } catch (IOException e) {//Cierre del Try-Catch
            e.printStackTrace();
        }//Cierre del Try-Catch

        return false; // Si el codigo no existe, se devuelve un booleano "False"
    }//Fin del metodo validarcodigoArticulo

}//Fin de la clase hija llamada Articulo
