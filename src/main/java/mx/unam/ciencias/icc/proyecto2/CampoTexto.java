package mx.unam.ciencias.icc.proyecto2;

/**
 * Enumeración para los campos de un {@link Estudiante}.
 */
public enum CampoTexto {

    /** El nombre del estudiante. */
    NOMBRE,
    /** El número de cuenta del estudiante. */
    CUENTA,
    /** El promedio del estudiante. */
    PROMEDIO,
    /** La edad del estudiante. */
    EDAD;

    /**
     * Regresa una representación en cadena del campo para ser usada en
     * interfaces gráficas.
     * @return una representación en cadena del campo.
     */
    @Override public String toString() {
        // Aquí va su código.
	if (this==NOMBRE)
	    return "Nombre";
	return null;
    }
}
