package TiendaDeAbarrotes;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Proveedor extends TiendaDeAbarrotes {//Clase proveedor hija de la clase TiendaDeAbarrotes

    //Instancias
    Scanner leerint = new Scanner(System.in);
    Scanner leer = new Scanner(System.in);
    public static File archivoProveedores = new File("Proveedores.txt");

    //Declaracion de variables
    String empresaProveedor;
    String ubicacionProveedor;
    String correoElectronico;
    static int numProveedores = 0;
    int contadorProveedores = 0;

    public Proveedor(String Codigo, String Nombre, String Empresa) {//Se define el constructor recibe parametros y asigna valores a las variables de instancia.
        super(Codigo, Nombre);
        this.empresaProveedor = Empresa;//Asigna el valor del parámetro Empresa al atributo empresaProveedor
    }

    int get(int Codi) {//Este método solicita al usuario que ingrese el ID del proveedor y devuelve el valor ingresado.
        System.out.print("Ingrese el ID del proveedor: ");
        codigo = leer.nextLine();
        return Integer.parseInt(codigo);
    }

    String get(String Nombre) {//Este método solicita al usuario que ingrese el nombre del proveedor y devuelve el valor ingresado.
        System.out.print("Ingrese el nombre del proveedor: ");
        leer.nextLine();
        nombre = leer.nextLine();
        return nombre;
    }

    String getEmpresa() {//Este método solicita al usuario que ingrese el nombre de la empresa del proveedor y devuelve el valor ingresado.
        System.out.print("Ingrese la empresa del proveedor: ");
        empresaProveedor = leer.nextLine();
        return empresaProveedor;
    }

    String getCorreoE() {//Este método solicita al usuario que ingrese el correo electrónico del proveedor y devuelve el valor ingresado.
        System.out.print("Ingrese el correo electronico del proveedor: ");
        correoElectronico = leer.nextLine();
        return correoElectronico;
    }

    String getUbicacion() {//Este método solicita al usuario que ingrese la ubicación del proveedor y devuelve el valor ingresado.
        System.out.print("Ingrese la ubicacion del proveedor: ");
        ubicacionProveedor = leer.nextLine();
        return ubicacionProveedor;
    }

    void agregarProveedores() throws IOException {//Este método permite agregar proveedores, en un archivo llamado "Proveedores.txt". 

        try {

            FileWriter fw = new FileWriter("Proveedores.txt", true);// Abre el archivo "Proveedores.txt" en modo de escritura (true indica que se añadirán los datos al final del archivo si existe)
            BufferedWriter bw = new BufferedWriter(fw);// // Crea un buffer de escritura para mejorar el rendimiento al escribir en el archivo
            PrintWriter pw = new PrintWriter(bw);  // Crea un objeto PrintWriter para escribir en el archivo de manera conveniente
            pw.print(get(1));// Escribe en el archivo el resultado de la función get(1)
            if (validarCodigoProveedor(Integer.parseInt(codigo))) {//// Verifica si el código del proveedor ya existe llamando a la función validarCodigoProveedor(codigo)
                System.out.println("El código ingresado ya existe, no se puede agregar el artículo.");
                return;
            }
            // Escribe en el archivo los resultados de las siguientes haciendo uso de polimorfismo mediante las funciones get()
            pw.print("," + get(""));
            pw.print("," + getEmpresa());
            pw.print("," + getCorreoE());
            pw.print("," + getUbicacion() + "\n");
            numProveedores++; // Incrementa el contador de proveedores

            pw.close();// Cierra el objeto PrintWriter para liberar los recursos
        } catch (Exception e) {//Muestra un mensaje de error en caso de que ocurra una excepción
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println("Proveedor agregado con exito:\n");
        FileWriter crear = new FileWriter(archivoProveedores, true);// Abre el archivo "archivoProveedores" en modo de escritura (true indica que se añadirán los datos al final del archivo si existe)
        BufferedReader brCablon = new BufferedReader(new FileReader(archivoProveedores));// Crea un buffer de lectura para mejorar el rendimiento al leer el archivo
        String st;
        Vector<String> v = new Vector(40);// Crea un vector de tamaño 40 para almacenar las líneas del archivo
        for (int i = 0; (st = brCablon.readLine()) != null; i++) {// Lee las líneas del archivo y las agrega al vector
            v.addElement(st);
        }
        String[] Arreglo = v.toArray(new String[v.size()]);// Convierte el vector en un arreglo de cadenas
        String[] partesparamostrar = Arreglo[Arreglo.length - 1].split(",");// Divide la última línea del arreglo en partes utilizando como separador la coma ","
        System.out.println("ID del Proveedor: " + partesparamostrar[0] + "    Nombre del proveedor: " + partesparamostrar[1] + "    Empresa del proveedor: " + partesparamostrar[2] + "    Correo E.:  " + partesparamostrar[3] + "    Ubicacion:  " + partesparamostrar[4] + "\n");

    }

    void mostrarProveedores() {
        try {
            FileWriter crear = new FileWriter(archivoProveedores, true);// Abre el archivo "archivoProveedores" en modo de escritura (true indica que se añadirán los datos al final del archivo si existe)
            BufferedReader brCablon = new BufferedReader(new FileReader(archivoProveedores));// Crea un buffer de lectura para mejorar el rendimiento al leer el archivo
            String st;
            Vector<String> v = new Vector(40);// Crea un vector de tamaño 40 para almacenar las líneas del archivo
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {// Lee las líneas del archivo y las agrega al vector
                v.addElement(st);
            }
            String[] Arreglo = v.toArray(new String[v.size()]);// Convierte el vector en un arreglo de cadenas
            if (!(archivoProveedores.exists()) || archivoProveedores.length() == 0) {//Verifica si se han agregado proveedores
                System.out.println("\n"+ANSI_RED + "No has agregado proveedores aun\n" + ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN + "_-_-_-_-_-_-_-_-_Proveedores Existentes_-_-_-_-_-_-_-_-_");
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {// Muestra en pantalla la información de todos los proveedores exitentes
                    String[] partesparamostrar = Arreglo[renglon].split(",");
                    System.out.println("\nID del Proveedor: " + partesparamostrar[0] + "    Nombre del proveedor: " + partesparamostrar[1] + "    Empresa del proveedor: " + partesparamostrar[2] + "    Correo E.:  " + partesparamostrar[3] + "    Ubicacion:  " + partesparamostrar[4] + "\n");
                }
            }

        } catch (IOException e) {//Muestra un mensaje de error en caso de que ocurra una excepción
        }
        System.out.println("");
    }

    void eliminarProveedores() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Proveedores.txt"));// Crea un buffer de lectura para mejorar el rendimiento al leer el archivo
            ArrayList<String> lineas = new ArrayList<>();//Crea una lista para almacenar las líneas del archivo

            String linea;
            int codigoEliminar;

            mostrarProveedores();//Llama al metodo mostrarProveedores

            System.out.println("Introduce el código del proveedor a eliminar:");
            codigoEliminar = leer.nextInt();

            boolean encontrado = false;

            while ((linea = br.readLine()) != null) {// Lee cada línea del archivo
                String[] partes = linea.split(","); // Divide la línea en partes utilizando como separador la coma ","
                int codigo = Integer.parseInt(partes[0]);// Obtiene el código del proveedor de la primera parte

                if (codigo == codigoEliminar) {//Si se encuentra el proveedor a eliminar, se marca como encontrado
                    encontrado = true;
                    System.out.println("El siguiente proveedor ha sido eliminado:");
                    System.out.println(linea);
                    numProveedores--;// Decrementa el contador de proveedores
                } else {// Si no es el proveedor a eliminar, se agrega a la lista de lineas
                    lineas.add(linea);
                }
            }

            br.close();// Cierra el objeto BufferedReader para liberar los recursos

            if (!encontrado) {
                System.out.println("No se encontró ningún proveedor con el código especificado");
            } else {
                PrintWriter piw = new PrintWriter(new FileWriter(archivoProveedores));// Crea un objeto PrintWriter para escribir en el archivo "archivoProveedores"

                for (String articulo : lineas) {// Escribe en el archivo todas las lineas de proveedores que no fueron eliminadas
                    piw.println(articulo);

                    piw.close();// Cierra el objeto PrintWriter para liberar los recursos

                }

            }
        } catch (IOException e) { // En caso de que ocurra una excepción de tipo IOException, muestra el stack trace del error
            e.printStackTrace();
        }
    }

    void modificarProveedor() {
        String nuevoNombre;
        String nuevoNombreEmpresa;
        String nuevaUbi;
        String nuevoCorreo;
        int elSwichNoSirve = 0;

        mostrarProveedores();

        try {
            System.out.println("Introduce el código del proveedor a modificar:");
            int codigoBuscado = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer
            List<String> lineas = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(archivoProveedores));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int codigo = Integer.parseInt(datos[0]);
                if (codigo == codigoBuscado) {
                    // Modificar el artículo
                    System.out.println("Introduce el nuevo nombre del proveedor:");
                    nuevoNombre = leer.nextLine();
                    System.out.println("Introduce el nuevo nombre de la empresa del proveedor:");
                    nuevoNombreEmpresa = leer.nextLine();
                    System.out.println("Ingrese la ubicacion del proveedor: ");
                    nuevaUbi = leer.nextLine();
                    System.out.println("Ingrese el correo electronico del proveedor: ");
                    nuevoCorreo = leer.nextLine();
                    linea = codigo + "," + nuevoNombre + "," + nuevoNombreEmpresa + "," + nuevaUbi + "," + nuevoCorreo;
                    System.out.println("El proveedor se ha modificado correctamente.");
                } else {
                    System.out.println("No se encontró ningún proveedor con el código especificado");
                    elSwichNoSirve++;
                }
                lineas.add(linea);
            }
            br.close();
            FileWriter fw = new FileWriter(archivoProveedores, false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (String line : lineas) {
                pw.println(line);
            }

            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        elSwichNoSirve = 0;
    }

    String get(String proveedor, int ola) {
        String selccionProveedor = "";
        String codigoBuscado;

        if (!(archivoProveedores.exists()) || archivoProveedores.length() == 0) {
            System.out.println("No hay proveedores registrados, por favor registra primero un proveedor");
        } else {
            mostrarProveedores();
        }
        try {
            System.out.print(ANSI_GREEN+"Ingrese el codigo del proveedor, que proporcionara este producto: "+ANSI_RESET);
            System.out.println("\n(Para cancelar la operacion, ingrese 'X')");
            codigoBuscado = leer.next();
            if (codigoBuscado.equalsIgnoreCase("X")) {
            System.out.println(ANSI_RED + "Operacion cancelada, No se anadio ningun articulo\n" + ANSI_GREEN);
            llamador.menuArticulosDueno();
        }
            leer.nextLine(); // Limpiar el buffer

            List<String> lineas = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(archivoProveedores));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int codigo = Integer.parseInt(datos[0]);
//            int codigoProveedor=leerint.nextInt();
                int posicionProveedor = -1;
                if (codigo == Integer.parseInt(codigoBuscado)) {
                    mostrarArticuloPorCodigo(codigo);
                    selccionProveedor = empresaProveedor;
                }
                else{
                    System.out.println(ANSI_RED+"No existe ese proveedor, intentalo de nuevo\n"+ANSI_RESET);
                }
            }
            br.close();
            FileWriter fw = new FileWriter(archivoProveedores, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (String line : lineas) {
                pw.println(line);
            }
            pw.close();
            System.out.println(ANSI_GREEN+"El proveedor se ha seleccionado correctamente.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selccionProveedor;
    }

    public void mostrarArticuloPorCodigo(int codigo) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivoProveedores));

            String linea;
            boolean encontrado = false;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int codigoArticulo = Integer.parseInt(partes[0]);

                if (codigoArticulo == codigo) {
                    encontrado = true;
                    empresaProveedor = partes[2];
                    System.out.println(ANSI_GREEN+"Proveedor encontrado:"+ANSI_RESET);
                    System.out.println("Codigo: " + partes[0]);
                    System.out.println("Nombre: " + partes[1]);
                    System.out.println("Nombre de la empresa: " + partes[2]);
                    break;
                }
            }

            br.close();

            if (!encontrado) {
                System.out.println("No se encontró ningún artículo con el código especificado.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void menuProveedoresDueno() throws IOException {
        int respuestaduenoProveedores = 0;
        do {
            System.out.println(ANSI_GREEN + "Que desea hacer con Proveedores?_-_-_-_-_-_-_-_-_-_-_-_-_-_-         " + "#Dueno#" + ANSI_GREEN);
            System.out.println("1. Anadir proveedores");
            System.out.println("2. Eliminar proveedores");
            System.out.println("3. Modificar proveedores");
            System.out.println("4. Ver proveedores");
            System.out.println("5.-Regresar al menu anterior");
            respuestaduenoProveedores = leer.nextInt();
            switch (respuestaduenoProveedores) {
                case 1:
                    agregarProveedores();
                    break;
                case 2:
                    eliminarProveedores();
                    break;
                case 3:
                    modificarProveedor();
                    break;
                case 4:
                    mostrarProveedores();
                    break;
                case 5:
                    menuDueno();
                    break;
                default:
                    System.out.println("Opcion Invalida, ingresa una opcion valida");
            }
        } while (respuestaduenoProveedores != 5);

    }

    public boolean validarCodigoProveedor(int codigo) {//Inicio del metodo validarCodigoArticulo que acepta un parametro de tipo int
        int codigoArticulo = 0;//Inicializacion y creacion de una variable de tipo entero llamada codigoArticulo
        try {//Inicio de Try-Catch
            BufferedReader br = new BufferedReader(new FileReader(archivoProveedores));//Creacion de un objeto de tipo BufferedReader para la lectura de un archivo
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

}//Fin de la clase hija llamada "proveedor" 
