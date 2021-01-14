package mx.unam.ciencias.icc;

import java.util.Random;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
        if (args.length < 1 || args.length>3)
            uso();

        /*int N = -1;
        try {
            N = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            uso();
        }*/

        Lista<Texto> textos = new Lista<Texto>();
        /*textos.agregaFinal(new Texto("José Arcadio Buendía"));
        textos.agregaFinal(new Texto("Úrsula Iguarán"));
        textos.agregaFinal(new Texto("Aureliano Buendía"));*/
        //in(in);
        //IOException e= new IOException();
        //String x=null;
        /*try {
	    	while ((x=in.readLine())!=null){
	    		textos.agregaFinal(new Texto(x));
	    	}
        }catch(IOException ioe) {
        	return;
        }*/
        //in(args[0]);
        //String[] split=args[0].split("\n");
        /*for (int i=0; i<split.length; i++) {
        	textos.agregaFinal(new Texto(split[i]));
        }*/
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
        //System.out.println("Ordenamiento por letra");
        for (Texto texto : textos)
            System.out.println(texto);

        //System.out.println();

        /*textos = textos.mergeSort(
            (e1, e2) -> e1.getCuenta() - e2.getCuenta());
        System.out.println("Ordenamiento por cuenta:");
        for (Texto texto : textos)
            System.out.println(texto);*/

        //System.out.println();

        /*textos = textos.mergeSort((e1, e2) -> {
                double p1 = e1.getPromedio();
                double p2 = e2.getPromedio();
                if (p1 < p2)
                    return -1;
                if (p2 < p1)
                    return 1;
                return 0;
            });
        System.out.println("Ordenamiento por promedio:");
        for (Texto texto : textos)
            System.out.println(texto);*/

        //System.out.println();

        /*textos = textos.mergeSort(
            (e1, e2) -> e1.getEdad() - e2.getEdad());
        System.out.println("Ordenamiento por edad:");
        for (Texto texto : textos)
            System.out.println(texto);*/

        //System.out.println();

        /*Random random = new Random();
        NumberFormat nf = NumberFormat.getIntegerInstance();
        long tiempoInicial, tiempoTotal;

        int[] arreglo = new int[N];
        for (int i = 0; i < N; i++)
            arreglo[i] = random.nextInt();

        Integer[] is = new Integer[N];
        tiempoInicial = System.nanoTime();
        for (int i = 0; i < N; i++)
            is[i] = arreglo[i];
        tiempoTotal = System.nanoTime() - tiempoInicial;
        System.out.printf("%2.9f segundos en llenar un arreglo con %s elementos.\n",
                          (tiempoTotal/1000000000.0), nf.format(N));

        tiempoInicial = System.nanoTime();
        Arreglos.selectionSort(is);
        tiempoTotal = System.nanoTime() - tiempoInicial;
        System.out.printf("%2.9f segundos en ordenar un arreglo con %s elementos " +
                          "usando SelectionSort.\n",
                          (tiempoTotal/1000000000.0), nf.format(N));

        Integer[] qs = new Integer[N];
        for (int i = 0; i < N; i++)
            qs[i] = arreglo[i];

        tiempoInicial = System.nanoTime();
        Arreglos.quickSort(qs);
        tiempoTotal = System.nanoTime() - tiempoInicial;
        System.out.printf("%2.9f segundos en ordenar un arreglo con %s elementos " +
                          "usando QuickSort.\n",
                          (tiempoTotal/1000000000.0), nf.format(N));

        int b = qs[random.nextInt(N)];

        tiempoInicial = System.nanoTime();
        int idx = Arreglos.busquedaBinaria(qs, b);
        tiempoTotal = System.nanoTime() - tiempoInicial;
        System.out.printf("%2.9f segundos en encontrar un elemento en un arreglo " +
                          "con %s elementos usando búsqueda binaria.\n",
                          (tiempoTotal/1000000000.0), nf.format(N));

        Lista<Integer> ms = new Lista<Integer>();
        tiempoInicial = System.nanoTime();
        for (int i = 0; i < N; i++)
            ms.agregaFinal(arreglo[i]);
        tiempoTotal = System.nanoTime() - tiempoInicial;
        System.out.printf("%2.9f segundos en crear una lista con %s elementos.\n",
                          (tiempoTotal/1000000000.0), nf.format(N));

        tiempoInicial = System.nanoTime();
        ms = Lista.mergeSort(ms);
        tiempoTotal = System.nanoTime() - tiempoInicial;
        System.out.printf("%2.9f segundos en ordenar una lista con %s elementos " +
                          "usando MergeSort.\n",
                          (tiempoTotal/1000000000.0), nf.format(N));*/
    }
    /*private Proyecto2 in(BufferedReader in) throws IOException {
    	return null;
    }*/
    private static Lista<Texto> lectura(String nombreArchivo) {
        //BaseDeDatos bdd = new BaseDeDatos();
    	BufferedReader in=null;
        try {
            FileInputStream fileIn = new FileInputStream(nombreArchivo);
            InputStreamReader isIn = new InputStreamReader(fileIn);
            in = new BufferedReader(isIn);
            //bdd.carga(in);
            //in.close();
        } catch (IOException ioe) {
            System.out.printf("No pude cargar del archivo \"%s\".\n",
                              nombreArchivo);
            System.exit(1);
        }

        //System.out.printf("Base de datos cargada exitosamente de \"%s\".\n\n",
          //                nombreArchivo);
        String x=null;
        Lista<Texto> textos = new Lista<Texto>();
        try {
	        while ((x=in.readLine())!=null){	
	        	textos.agregaFinal(new Texto(x));
	    		/*R r=creaRegistro();
	    		r.deserializa(x);
	    		agregaRegistro(r);*/
	    	    
	    	}
        }catch(IOException ioe) {
        	throw new ExcepcionLineaInvalida();
        }
        return textos;
        //return in;
        /*Lista<> r = bdd.getRegistros();
        for ( e : r)
            System.out.println(e + "\n");

        return bdd;*/
        
    }
}
