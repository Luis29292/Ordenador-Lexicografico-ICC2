package mx.unam.ciencias.icc.proyecto2;

/**
 * Clase para representar textos. Un texto tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede serializarse en una línea de texto y deserializarse de una línea de
 * texto; además de determinar si sus campos cazan valores arbitrarios y
 * actualizarse con los valores de otro texto.
 */
public class Texto implements Registro<Texto, CampoTexto> {

    /* Línea del texto. */
    private String nombre;

    /**
     * Define el estado inicial de un texto.
     * @param nombre el nombre del texto.
     * @param cuenta el número de cuenta del texto.
     * @param promedio el promedio del texto.
     * @param edad la edad del texto.
     */
    public Texto(String nombre) {
        // Aquí va su código.
	this.nombre    = nombre;
    }

    /**
     * Regresa el nombre del texto.
     * @return el nombre del texto.
     */
    public String getNombre() {
        // Aquí va su código.
	return nombre;
    }

    /**
     * Define el nombre del texto.
     * @param nombre el nuevo nombre del texto.
     */
    public void setNombre(String nombre) {
        // Aquí va su código.
	this.nombre=nombre;
    }

    /**
     * Regresa una representación en cadena del texto.
     * @return una representación en cadena del texto.
     */
    @Override public String toString() {
        // Aquí va su código.
	String cadena = String.format("%s\n", nombre);
	return cadena;
    }

    /**
     * Nos dice si el objeto recibido es un texto igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el texto se comparará.
     * @return <code>true</code> si el objeto recibido es un texto con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Texto))
            return false;
        Texto texto = (Texto)objeto;
        // Aquí va su código.
	return ((this.nombre.equals(texto.nombre)));
    }

    /**
     * Regresa el texto serializado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Texto#deserializa}.
     * @return la serialización del texto en una línea de texto.
     */
    @Override public String serializa() {
        // Aquí va su código.
	String linea = String.format("%s\n",
                                     nombre);
	return linea;
    }

    /**
     * Deserializa una línea de texto en las propiedades del texto. La
     * serialización producida por el método {@link Texto#serializa} debe
     * ser aceptada por este método.
     * @param linea la línea a deserializar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una serialización válida de un texto.
     */
    @Override public void deserializa(String linea) {
        // Aquí va su código.
	ExcepcionLineaInvalida e=new ExcepcionLineaInvalida();
	if (linea==null)
	    throw e;
	linea=linea.trim();
	String[] split=linea.split("\t");
	if (split.length!=4)
	    throw e;
	nombre=split[0];
    }

    /**
     * Actualiza los valores del texto con los del texto recibido.
     * @param texto el texto con el cual actualizar los valores.
     * @throws IllegalArgumentException si el texto es <code>null</code>.
     */
    @Override public void actualiza(Texto texto) {
        // Aquí va su código.
	if (texto==null)
	    throw new IllegalArgumentException();
	texto.nombre=this.nombre;
    }

    /**
     * Nos dice si el texto caza el valor dado en el campo especificado.
     * @param campo el campo que hay que cazar.
     * @param valor el valor con el que debe cazar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoTexto#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del texto.</li>
     *           <li><code>campo</code> es {@link CampoTexto#CUENTA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cuenta del
     *              texto.</li>
     *           <li><code>campo</code> es {@link CampoTexto#PROMEDIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al promedio del
     *              texto.</li>
     *           <li><code>campo</code> es {@link CampoTexto#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del
     *              texto.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean caza(CampoTexto campo, Object valor) {
        // Aquí va su código.
	if (campo==null)
	    throw new IllegalArgumentException();
	if (campo.equals(CampoTexto.NOMBRE) && valor instanceof String && nombre.contains((String)valor) && (String)valor!="")
	    return true;
	return false;
    }
}
