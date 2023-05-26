package TiendaDeAbarrotes;

import static TiendaDeAbarrotes.TiendaDeAbarrotes.leer;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Proveedor extends TiendaDeAbarrotes {

    Scanner leerint = new Scanner(System.in);
    Scanner leer = new Scanner(System.in);
    public static File archivoProveedores = new File("Proveedores.txt");

    //NOTA: Mediante estas 3 variables de abajo se accede a la informacion en especifico de cada objeto Proveedores
    String empresaProveedor;

    Proveedor[] arregloProveedores = new Proveedor[100]; // Se puede cambiar el tamaño del arreglo según se necesite
    static int numProveedores = 0;
    int contadorProveedores = 0;

    public Proveedor(int Codigo, String Nombre, String Empresa) {
        super(Codigo, Nombre);
        this.empresaProveedor = Empresa;
    }

    int get(int Codi) {
        System.out.print("Ingrese el ID del proveedor: ");
        codigo = leerint.nextInt();
        return codigo;
    }

    String get(String Nombre) {
        System.out.print("Ingrese el nombre del proveedor: ");
        leer.nextLine();
        nombre = leer.nextLine();
        return nombre;
    }

    String getEmpresa() {
        System.out.print("Ingrese la empresa del proveedor: ");
        empresaProveedor = leer.nextLine();
        return empresaProveedor;
    }

    void agregarProveedores() {
        try {

            FileWriter fw = new FileWriter("Proveedores.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(get(1));
            pw.print("," + get(""));
            pw.print("," + getEmpresa() + "\n");
            numProveedores++;

            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println("Proveedor agregado con éxito");
    }

    void mostrarProveedores() {
        System.out.println("---Proveedores Existentes---");
        try {
            FileWriter crear = new FileWriter(archivoProveedores, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivoProveedores));
            PrintWriter escribir = new PrintWriter(crear);
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);
            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            if (archivoProveedores.length() == 0) {
                System.out.println("No has agregado proveedores aun");
            } else {
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {
                    String[] partesparamostrar = Arreglo[renglon].split(",");
                    System.out.println("\nID del Proveedor: " + partesparamostrar[0] + "    Nombre del proveedor: " + partesparamostrar[1] + "    Empresa del proveedor: " + partesparamostrar[2]);
                }
            }
            System.out.println();

        } catch (IOException e) {
        }
    }

    void eliminarProveedores() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Proveedores.txt"));
            ArrayList<String> lineas = new ArrayList<>();

            String linea;
            int codigoEliminar;

            System.out.println("Introduce el código del proveedor a eliminar:");
            codigoEliminar = leer.nextInt();

            boolean encontrado = false;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                int codigo = Integer.parseInt(partes[0]);

                if (codigo == codigoEliminar) {
                    encontrado = true;
                    System.out.println("El siguiente proveedor ha sido eliminado:");
                    System.out.println(linea);
                    numProveedores--;
                } else {
                    lineas.add(linea);
                }
            }

            br.close();

            if (!encontrado) {
                System.out.println("No se encontró ningún proveedor con el código especificado.");
            } else {
                PrintWriter pw = new PrintWriter(new FileWriter(archivoProveedores));

                for (String articulo : lineas) {
                    pw.println(articulo);
                }

                pw.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void modificarProveedor() {
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
                    String nuevoNombre = leer.nextLine();
                    System.out.println("Introduce el nuevo nombre de la empresa del proveedor:");
                    String nuevoNombreEmpresa = leer.nextLine();
                    linea = codigo + "," + nuevoNombre + "," + nuevoNombreEmpresa;
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

            System.out.println("El proveedor se ha modificado correctamente.");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String get(String lisboa, int popola) {
        String selccionProveedor = "";

        if (numProveedores > 0) {
            mostrarProveedores();
        } else {
            System.out.println("No hay proveedores registrados, por favor registra primero un proveedor");
        }
        try {
            System.out.print("Ingrese el codigo del proveedor, que proporcionara este producto: ");
            int codigoBuscado = leer.nextInt();
            leer.nextLine(); // Limpiar el buffer

            List<String> lineas = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader(archivoProveedores));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int codigo = Integer.parseInt(datos[0]);
//            int codigoProveedor=leerint.nextInt();

                int posicionProveedor = -1;

                if (codigo == codigoBuscado) {
                    mostrarArticuloPorCodigo(codigo);
                    selccionProveedor = empresaProveedor;
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

            System.out.println("El proveedor se ha seleccionado correctamente.");

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
                    System.out.println("Proveedor encontrado:");
                    System.out.println("Código: " + partes[0]);
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
            System.out.println("Que desea hacer con Proveedores?");
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

}
