package mx.unam.ciencias.icc.proyecto2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;

/**
 * Proyecto 2.
 */
public class Proyecto2{

    /* Imprime el uso del programa y termina. */
    private static void uso() {
        System.err.println("Uso: java -jar target/Proyecto2.jar hombre.txt\n"
        		+ "Usar la bandera -r para imprimir en orden inverso\n"
        		+ "Usar la bandera -o, seguida de un identificador, para guardar la salida en un archivo identificador.");
        System.exit(1);
    }

    public static void main(String[] args) {

        Lista<Texto> textos = new Lista<Texto>();

	    if (args.length>=1) {    
	        textos=lectura(args[0]);
	        textos = textos.mergeSort(
	                (e1, e2) -> e1.getNombre().
	                replaceAll("\\s+", "").
	                replaceAll("á", "a").
	                replaceAll("é", "e").
	                replaceAll("í", "i").
	                replaceAll("ó", "o").
	                replaceAll("ú", "u").
	                replaceAll("ñ", "n").
	                replaceAll("Á", "A").
	                replaceAll("É", "E").
	                replaceAll("Í", "I").
	                replaceAll("Ó", "O").
	                replaceAll("Ú", "U").
	                replaceAll("Ñ", "N").
	                replaceAll("¿", "").
	                replaceAll("¡", "").
	                compareToIgnoreCase(e2.getNombre().
	                replaceAll("\\s+", "").
	                replaceAll("á", "a").
	                replaceAll("é", "e").
	                replaceAll("í", "i").
	                replaceAll("ó", "o").
	                replaceAll("ú", "u").
	                replaceAll("ñ", "n").
	                replaceAll("Á", "A").
	                replaceAll("É", "E").
	                replaceAll("Í", "I").
	                replaceAll("Ó", "O").
	                replaceAll("Ú", "U").
	                replaceAll("Ñ", "N").
	                replaceAll("¿", "").
	                replaceAll("¡", "")));
	        if (args.length==1)
	        	orden(textos);
	        else if (args.length==2 && args[1].equals("-r"))
	        	desorden(textos);
	        else if (args.length==3 && args[1].equals("-o"))
	        	guarda(textos,args[2]);
	        else
	        	uso();
	    }
	    else {
	    	try {
	    		InputStreamReader isr= new InputStreamReader(System.in);
	            BufferedReader in = new BufferedReader(isr);
	            
	            String x=null;
	        	boolean funciona = false;
    	        while ((x=in.readLine())!=null){	
    	        	
    	        	textos.agregaFinal(new Texto(x));
    	        	funciona=true;
    	    	    
    	    	}
    	        in.close();
	            if (funciona==true) {
	    	        textos = textos.mergeSort(
	    	                (e1, e2) -> e1.getNombre().
	    	                replaceAll("\\s+", "").
	    	                replaceAll("á", "a").
	    	                replaceAll("é", "e").
	    	                replaceAll("í", "i").
	    	                replaceAll("ó", "o").
	    	                replaceAll("ú", "u").
	    	                replaceAll("ñ", "n").
	    	                replaceAll("Á", "A").
	    	                replaceAll("É", "E").
	    	                replaceAll("Í", "I").
	    	                replaceAll("Ó", "O").
	    	                replaceAll("Ú", "U").
	    	                replaceAll("Ñ", "N").
	    	                replaceAll("¿", "").
	    	                replaceAll("¡", "").
	    	                compareToIgnoreCase(e2.getNombre().
	    	                replaceAll("\\s+", "").
	    	                replaceAll("á", "a").
	    	                replaceAll("é", "e").
	    	                replaceAll("í", "i").
	    	                replaceAll("ó", "o").
	    	                replaceAll("ú", "u").
	    	                replaceAll("ñ", "n").
	    	                replaceAll("Á", "A").
	    	                replaceAll("É", "E").
	    	                replaceAll("Í", "I").
	    	                replaceAll("Ó", "O").
	    	                replaceAll("Ú", "U").
	    	                replaceAll("Ñ", "N").
	    	                replaceAll("¿", "").
	    	                replaceAll("¡", "")));
	    	        orden(textos);
	            }
	    	}catch (IOException ioe){
	    		uso();
	    	}
	    }
	        
        
        
    }

    private static void orden(Lista<Texto> textos) {
    	
            //System.out.println("Ordenamiento por letra");
    	int n=0;
            for (Texto texto : textos) {
                System.out.println(texto);
                n++;
            }
            
            System.out.println("La complejidad en tiempo fue de: "+Math.log(n)*n); 
    }
    private static void desorden(Lista<Texto> textos) {
    	int n=0;
    	for (int i=textos.getLongitud()-1; i>0; i--) {
    		System.out.println(textos.get(i));
    		n++;
    	}
    	System.out.println("La complejidad en tiempo fue de: "+Math.log(n)*n);
    }
    private static void guarda (Lista<Texto> textos, String nombreArchivo) {
    	try {
    		
	    	FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
	        OutputStreamWriter osOut = new OutputStreamWriter(fileOut);
	        BufferedWriter out = new BufferedWriter(osOut);
	        int n=0;
	        for (Texto texto : textos) {
                System.out.println(texto);
                out.write(texto+"");
                n++;
    		}
	        System.out.println("La complejidad en tiempo fue de: "+Math.log(n)*n);
	        //out.write(textos);
	        out.close();
    	}catch(IOException ioe) {
    		System.out.printf("No pude guardar del archivo \"%s\".\n",
                    nombreArchivo);
  System.exit(1);
    	}
        /*bdd.guarda(out);
        out.close();*/
    }
    private static Lista<Texto> lectura(String nombreArchivo) {
        //BaseDeDatos bdd = new BaseDeDatos();
    	BufferedReader in=null;
        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            in = new BufferedReader(isIn);

        } catch (IOException ioe) {
            System.out.printf("No pude cargar del archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        String x=null;
        Lista<Texto> textos = new Lista<Texto>();
        try {
	        while ((x=in.readLine())!=null){	
	        	textos.agregaFinal(new Texto(x));

	    	}
        }catch(IOException ioe) {
        	throw new ExcepcionLineaInvalida();
        }
        return textos;

        
    }
}
