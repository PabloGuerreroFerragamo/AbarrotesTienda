package codigoAyuda;

import java.util.Scanner;

public class Proveedor1 {

    String idProveedorGlobal;
    String nombreProveedor;
    String empresaProveedor;

    public Proveedor1(String idProveedor1, String nombreProveedor1, String empresaProveedor1) {
        idProveedorGlobal = idProveedor1;
        nombreProveedor = nombreProveedor1;
        empresaProveedor = empresaProveedor1;
    }

}

class Mainer {

    public static void main(String[] args) {
        Scanner leerint = new Scanner(System.in);
        Scanner leer = new Scanner(System.in);
        Proveedor1[] arregloProveedores = new Proveedor1[100]; // Se puede cambiar el tamaño del arreglo según se necesite
        int opcion = 0;
        int numProveedores = 0;
        int contadorProveedores = 0;

        while (contadorProveedores < 1) {
            System.out.println("\n=== Menú de opciones ===");
            System.out.println("1. Agregar proveedor");
            System.out.println("2. Mostrar proveedores");
            System.out.println("3. Eliminar proveedor");
            System.out.println("4. Salir");
            System.out.print("Ingrese la opción deseada: ");

            opcion = leerint.nextInt();

            if (opcion == 1) {
                if (numProveedores < arregloProveedores.length) {
                    System.out.print("Ingrese el ID del proveedor: ");
                    String idProveedor = leer.nextLine();
                    System.out.print("Ingrese el nombre del proveedor: ");
                    String nombreProveedor = leer.nextLine();
                    System.out.print("Ingrese la empresa del proveedor: ");
                    String empresaProveedor = leer.nextLine();

                    arregloProveedores[numProveedores] = new Proveedor1(idProveedor, nombreProveedor, empresaProveedor);//Se crea un objeto de tipo Proveedor1, el cual pide como parametro 3 valores, los cuales se asignan a la posición numProveedores del arregloProveedores
                    numProveedores++;
                    System.out.println("Proveedor agregado con éxito");
                } else {
                    System.out.println("No se pueden agregar más proveedores, el límite ha sido alcanzado");
                }
            } else if (opcion == 2) {
                if (numProveedores > 0) {
                    System.out.println("\n=== Proveedores registrados ===");
                    for (int i = 0; i < numProveedores; i++) {//se repite el numero de proveedores que haya
                        System.out.println("ID: " + arregloProveedores[i].idProveedorGlobal + "\tNombre: " + arregloProveedores[i].nombreProveedor + "\tEmpresa: " + arregloProveedores[i].empresaProveedor);
                    }
                } else {
                    System.out.println("No hay proveedores registrados");
                }
            } else if (opcion == 3) {
                if (numProveedores > 0) {
                    System.out.print("Ingrese el ID del proveedor a eliminar: ");
                    String idProveedorEliminar = leer.nextLine();

                    boolean proveedorEncontrado = false;
                    int posicionProveedorEliminar = -1;//almacenar la posición del proveedor que se desea eliminar en el arreglo de proveedores

                    for (int i = 0; i < numProveedores; i++) {//Se repite mientras i es menor al numero de proveedores existentes
                        if (arregloProveedores[i].idProveedorGlobal.equals(idProveedorEliminar)) {//si el codigo de proveedor del arreglo en la posicion de i, es igual al codigo a eliminar
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
            } else if (opcion == 4) {
                System.out.println("Programa finalizado");
                contadorProveedores++;
            } else {
                System.out.println("Opción inválida");
            }
        }

    }
}
