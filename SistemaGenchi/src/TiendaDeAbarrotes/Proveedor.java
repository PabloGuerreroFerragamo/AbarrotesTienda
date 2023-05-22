package TiendaDeAbarrotes;

import java.util.Scanner;

public class Proveedor extends TiendaDeAbarrotes {

    Scanner leerint = new Scanner(System.in);
    Scanner leer = new Scanner(System.in);

    //NOTA: Mediante estas 3 variables de abajo se accede a la informacion en especifico de cada objeto Proveedores
    String empresaProveedor;

    Proveedor[] arregloProveedores = new Proveedor[100]; // Se puede cambiar el tamaño del arreglo según se necesite
    int numProveedores = 0;
    int contadorProveedores = 0;

    public Proveedor(int Codigo, String Nombre, String Empresa) {
        super(Codigo, Nombre);
        empresaProveedor = Empresa;
    }

    void agregarProveedores() {
        //Y con estas variables se obtiene la informacion de cada proveedor
        int codigoProveedorVolatil;
        String nombreProveedorVolatil;
        String empresaProveedorVolatil;

        if (numProveedores < arregloProveedores.length) {
            System.out.print("Ingrese el ID del proveedor: ");
            codigoProveedorVolatil = leerint.nextInt();

            System.out.print("Ingrese el nombre del proveedor: ");
            nombreProveedorVolatil = leer.nextLine();

            System.out.print("Ingrese la empresa del proveedor: ");
            empresaProveedorVolatil = leer.nextLine();

            arregloProveedores[numProveedores] = new Proveedor(codigoProveedorVolatil, nombreProveedorVolatil, empresaProveedorVolatil);//Se crea un objeto de tipo Proveedor1, el cual pide como parametro 3 valores, los cuales se asignan a la posición numProveedores del arregloProveedores
            numProveedores++;
            System.out.println("Proveedor agregado con éxito");
        } else {
            System.out.println("No se pueden agregar más proveedores, el límite ha sido alcanzado");
        }
    }

    void mostrarProveedores() {
        if (numProveedores > 0) {
            System.out.println("\n=== Proveedores registrados ===");
            for (int i = 0; i < numProveedores; i++) {//se repite el numero de proveedores que haya
                System.out.println("ID: " + arregloProveedores[i].codigo + "\tNombre: " + arregloProveedores[i].nombre + "\tEmpresa: " + arregloProveedores[i].empresaProveedor);
            }
        } else {
            System.out.println("No hay proveedores registrados");
        }
    }

    void eliminarProveedores() {
        if (numProveedores > 0) {
            System.out.print("Ingrese el ID del proveedor a eliminar: ");
            int idProveedorEliminar = leerint.nextInt();

            boolean proveedorEncontrado = false;
            int posicionProveedorEliminar = -1;//almacenar la posición del proveedor que se desea eliminar en el arreglo de proveedores

            for (int i = 0; i < numProveedores; i++) {//Se repite mientras i es menor al numero de proveedores existentes
                if (arregloProveedores[i].codigo == idProveedorEliminar) {//si el codigo de proveedor del arreglo en la posicion de i, es igual al codigo a eliminar
                    proveedorEncontrado = true;//si indica que ya se encontro
                    posicionProveedorEliminar = i;//Se reconoce la posicion del arreglo a elimar
                    break;//rompemos el ciclo for
                }//cierre de if
            }//cierre del ciclo for

            if (proveedorEncontrado == true) {//si se encontro el proveedor

                for (int i = posicionProveedorEliminar; i < numProveedores - 1; i++) {
                    arregloProveedores[i] = arregloProveedores[i + 1];
                }
                arregloProveedores[numProveedores - 1] = null;
                numProveedores--;
                System.out.println("Proveedor eliminado con éxito");
            } else {
                System.out.println("No se encontró un proveedor con el ID proporcionado");
            }
        } else {
            System.out.println("No hay proveedores registrados");
        }
    }

    void modificarProveedor() {
        String nombreProveedorVolatil;
        String empresaProveedorVolatil;
        if (numProveedores > 0) {

            System.out.println("\n=== Proveedores registrados ===");
            for (int i = 0; i < numProveedores; i++) {//se repite el numero de proveedores que haya
                System.out.println("ID: " + arregloProveedores[i].codigo + "\tNombre: " + arregloProveedores[i].nombre + "\tEmpresa: " + arregloProveedores[i].empresaProveedor);
            }

            System.out.print("Ingrese el ID del proveedor a modificar: ");
            int idProveedorModificar = leerint.nextInt();

            int posicionProveedorModificar = -1;
            for (int i = 0; i < numProveedores; i++) {
                if (arregloProveedores[i].codigo == idProveedorModificar) {
                    posicionProveedorModificar = i;
                    break;
                }
            }

            if (posicionProveedorModificar != -1) {
                System.out.print("Ingrese el nombre del proveedor: ");
                nombreProveedorVolatil = leer.nextLine();

                System.out.print("Ingrese la empresa del proveedor: ");
                empresaProveedorVolatil = leer.nextLine();

                arregloProveedores[posicionProveedorModificar].nombre = nombreProveedorVolatil;
                arregloProveedores[posicionProveedorModificar].empresaProveedor = empresaProveedorVolatil;

                System.out.println("Proveedor modificado con éxito");
            } else {
                System.out.println("No se encontró un proveedor con el ID proporcionado");
            }
        } else {
            System.out.println("No hay proveedores registrados");
        }
    }

    void menuProveedores() {
        int respuestaProveedores = 0;
        int contadorMenuProveedores = 0;

        while (contadorMenuProveedores < 1) {
            System.out.println("\n=== Menú de opciones ===");
            System.out.println("1. Agregar proveedor");
            System.out.println("2. Mostrar proveedores");
            System.out.println("3. Eliminar proveedor");
            System.out.println("4. Modificar proveedores");
            System.out.print("Ingrese la opción deseada: ");

            respuestaProveedores = leerint.nextInt();

            if (respuestaProveedores == 1) {
                agregarProveedores();
            } else if (respuestaProveedores == 2) {
                mostrarProveedores();
            } else if (respuestaProveedores == 3) {
                eliminarProveedores();
            } else if (respuestaProveedores == 4) {
                modificarProveedor();
            } else if (respuestaProveedores == 5) {
                contadorMenuProveedores++;
            }
        }

    }

   
}
