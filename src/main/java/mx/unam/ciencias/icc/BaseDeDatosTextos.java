package mx.unam.ciencias.icc;

/**
 * Clase para bases de datos de estudiantes.
 */
public class BaseDeDatosTextos
    extends BaseDeDatos<Texto, CampoTexto> {

    /**
     * Crea un estudiante en blanco.
     * @return un estudiante en blanco.
     */
    @Override public Texto creaRegistro() {
        // Aquí va su código.
	Texto a=new Texto(null);
	return a;
    }
}
