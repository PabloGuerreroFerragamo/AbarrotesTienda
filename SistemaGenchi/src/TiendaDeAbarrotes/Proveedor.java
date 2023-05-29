package TiendaDeAbarrotes;

import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;

public class Proveedor extends TiendaDeAbarrotes {

    Scanner leerint = new Scanner(System.in);
    Scanner leer = new Scanner(System.in);
    public static File archivoProveedores = new File("Proveedores.txt");

    //NOTA: Mediante estas 3 variables de abajo se accede a la informacion en especifico de cada objeto Proveedores
    String empresaProveedor;
    String ubicacionProveedor;
    String correoElectronico;

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

    String getCorreoE() {
        System.out.print("Ingrese el correo electronico del proveedor: ");
        correoElectronico = leer.nextLine();
        return correoElectronico;
    }

    String getUbicacion() {
        System.out.print("Ingrese la ubicacion del proveedor: ");
        ubicacionProveedor = leer.nextLine();
        return ubicacionProveedor;
    }

    void agregarProveedores() throws IOException {

        try {
            FileWriter fw = new FileWriter("Proveedores.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.print(get(1));
            if (validarCodigoProveedor(codigo)) {
                System.out.println("El código ingresado ya existe, no se puede agregar el artículo.");
                return;
            }
            pw.print("," + get(""));
            pw.print("," + getEmpresa());
            pw.print("," + getCorreoE());
            pw.print("," + getUbicacion() + "\n");
            numProveedores++;

            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        System.out.println("Proveedor agregado con exito:\n");
        FileWriter crear = new FileWriter(archivoProveedores, true);
        BufferedReader brCablon = new BufferedReader(new FileReader(archivoProveedores));
        String st;
        Vector<String> v = new Vector(40);
        for (int i = 0; (st = brCablon.readLine()) != null; i++) {
            v.addElement(st);
        }
        String[] Arreglo = v.toArray(new String[v.size()]);
        String[] partesparamostrar = Arreglo[Arreglo.length - 1].split(",");
        System.out.println("ID del Proveedor: " + partesparamostrar[0] + "    Nombre del proveedor: " + partesparamostrar[1] + "    Empresa del proveedor: " + partesparamostrar[2] + "    Correo E.:  " + partesparamostrar[3] + "    Ubicacion:  " + partesparamostrar[4] + "\n");

    }

    void mostrarProveedores() {
        System.out.println(ANSI_GREEN + "_-_-_-_-_-_-_-_-_Proveedores Existentes_-_-_-_-_-_-_-_-_");
        try {
            FileWriter crear = new FileWriter(archivoProveedores, true);
            BufferedReader brCablon = new BufferedReader(new FileReader(archivoProveedores));
            String st;
            Vector<String> v = new Vector(40);
            for (int i = 0; (st = brCablon.readLine()) != null; i++) {
                v.addElement(st);
            }
            String[] Arreglo = v.toArray(new String[v.size()]);
            if (!(archivoProveedores.exists()) || archivoProveedores.length() == 0) {
                System.out.println(ANSI_RED + "No has agregado proveedores aun" + ANSI_RESET);
            } else {
                for (int renglon = 0; Arreglo.length > renglon; renglon++) {
                    String[] partesparamostrar = Arreglo[renglon].split(",");
                    System.out.println("\nID del Proveedor: " + partesparamostrar[0] + "    Nombre del proveedor: " + partesparamostrar[1] + "    Empresa del proveedor: " + partesparamostrar[2] + "    Correo E.:  " + partesparamostrar[3] + "    Ubicacion:  " + partesparamostrar[4] + "\n");
                }
            }

        } catch (IOException e) {
        }
        System.out.println("");
    }

    void eliminarProveedores() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("Proveedores.txt"));
            ArrayList<String> lineas = new ArrayList<>();

            String linea;
            int codigoEliminar;

            mostrarProveedores();

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
                System.out.println("No se encontró ningún proveedor con el código especificado");
            } else {
                PrintWriter piw = new PrintWriter(new FileWriter(archivoProveedores));

                for (String articulo : lineas) {
                    piw.println(articulo);

                    piw.close();

                }

            }
        } catch (IOException e) {
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

    String get(String lisboa, int popola) {
        String selccionProveedor = "";
        int codigoBuscado;

        if (!(archivoProveedores.exists()) || archivoProveedores.length() == 0) {
            System.out.println("No hay proveedores registrados, por favor registra primero un proveedor");
        } else {
            mostrarProveedores();
        }
        try {
            System.out.print("Ingrese el codigo del proveedor, que proporcionara este producto: ");
            codigoBuscado = leer.nextInt();
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
    
     public boolean validarCodigoProveedor(int codigo) {
            int codigoArticulo=0;
    try {
        BufferedReader br = new BufferedReader(new FileReader(archivoProveedores));

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
