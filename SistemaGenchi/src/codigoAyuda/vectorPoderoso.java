
package codigoAyuda;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Vector;

public class vectorPoderoso {
    public static void main(String[] args) throws IOException{
        File archivo = new File ("C:\\guardar\\ppollokfc.txt");
        FileWriter crear = new FileWriter(archivo,true);
        BufferedReader brCablon = new BufferedReader(new FileReader(archivo));
        PrintWriter escribir = new PrintWriter(crear);
        String st;
        Vector <String> v = new Vector(40);
        for (int i = 0; (st=brCablon.readLine())!=null; i++) {
            v.addElement(st);
            
        }
        String[] Arreglo = v.toArray(new String[v.size()]);
        for (int x = 0; Arreglo.length>x; x++) {
            System.out.println(Arreglo[x]);
            
        }
        
    }
    
}

