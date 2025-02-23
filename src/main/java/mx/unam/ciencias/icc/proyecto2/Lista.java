package mx.unam.ciencias.icc.proyecto2;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>Clase para listas genéricas doblemente ligadas.</p>
 *
 * <p>Las listas nos permiten agregar elementos al inicio o final de la lista,
 * eliminar elementos de la lista, comprobar si un elemento está o no en la
 * lista, y otras operaciones básicas.</p>
 *
 * <p>Las listas implementan la interfaz {@link Iterable}, y por lo tanto se
 * pueden recorrer usando la estructura de control <em>for-each</em>. Las listas
 * no aceptan a <code>null</code> como elemento.</p>
 *
 * @param <T> El tipo de los elementos de la lista.
 */
public class Lista<T> implements Iterable<T> {

    /* Clase interna privada para nodos. */
    private class Nodo {
        /* El elemento del nodo. */
        private T elemento;
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nodo con un elemento. */
        private Nodo(T elemento) {
            // Aquí va su código.
	    this.elemento=elemento;
        }
    }

    /* Clase Iterador privada para iteradores. */
    private class Iterador implements IteradorLista<T> {
        /* El nodo anterior. */
        private Nodo anterior;
        /* El nodo siguiente. */
        private Nodo siguiente;

        /* Construye un nuevo iterador. */
        private Iterador() {
            // Aquí va su código.
	    start();
        }

        /* Nos dice si hay un elemento siguiente. */
        @Override public boolean hasNext() {
            // Aquí va su código.
	    return siguiente!=null;
        }

        /* Nos da el elemento siguiente. */
        @Override public T next() {
            // Aquí va su código.
	    if (hasNext() == false)
		throw new NoSuchElementException();
	    anterior=siguiente;
	    siguiente=siguiente.siguiente;
	    return anterior.elemento;
        }

        /* Nos dice si hay un elemento anterior. */
        @Override public boolean hasPrevious() {
            // Aquí va su código.
	    return anterior!=null;
        }

        /* Nos da el elemento anterior. */
        @Override public T previous() {
            // Aquí va su código.
	    if (hasPrevious() == false)
		throw new NoSuchElementException();
	    siguiente=anterior;
	    anterior=anterior.anterior;
	    return siguiente.elemento;
        }

        /* Mueve el iterador al inicio de la lista. */
        @Override public void start() {
            // Aquí va su código.
	    anterior=null;
	    siguiente=cabeza;
        }

        /* Mueve el iterador al final de la lista. */
        @Override public void end() {
            // Aquí va su código.
	    siguiente=null;
	    anterior=rabo;
        }
    }

    /* Primer elemento de la lista. */
    private Nodo cabeza;
    /* Último elemento de la lista. */
    private Nodo rabo;
    /* Número de elementos en la lista. */
    private int longitud;

    /**
     * Regresa la longitud de la lista.
     * @return la longitud de la lista, el número de elementos que contiene.
     */
    public int getLongitud() {
        // Aquí va su código.
	return longitud;
    }

    /**
     * Nos dice si la lista es vacía.
     * @return <code>true</code> si la lista es vacía, <code>false</code> en
     *         otro caso.
     */
    public boolean esVacia() {
        // Aquí va su código.
	return (cabeza==null);
    }

    /**
     * Agrega un elemento al final de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaFinal(T elemento) {
        // Aquí va su código.
	if (elemento==null)
	    throw new IllegalArgumentException();
	longitud++;
	Nodo n = new Nodo(elemento);
	if (rabo==null){
	    cabeza = rabo = n;
	}else{
	    rabo.siguiente = n;
	    n.anterior = rabo;
	    rabo = n;
	}
    }

    /**
     * Agrega un elemento al inicio de la lista. Si la lista no tiene elementos,
     * el elemento a agregar será el primero y último.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void agregaInicio(T elemento) {
        // Aquí va su código.
	if (elemento==null)
	    throw new IllegalArgumentException();
	longitud++;
	Nodo n = new Nodo(elemento);
	if (cabeza==null){
	    cabeza = rabo = n;
	}
	else{
	    cabeza.anterior=n;
	    n.siguiente=cabeza;
	    cabeza=n;
	}
    }

    /**
     * Inserta un elemento en un índice explícito.
     *
     * Si el índice es menor o igual que cero, el elemento se agrega al inicio
     * de la lista. Si el índice es mayor o igual que el número de elementos en
     * la lista, el elemento se agrega al fina de la misma. En otro caso,
     * después de mandar llamar el método, el elemento tendrá el índice que se
     * especifica en la lista.
     * @param i el índice dónde insertar el elemento. Si es menor que 0 el
     *          elemento se agrega al inicio de la lista, y si es mayor o igual
     *          que el número de elementos en la lista se agrega al final.
     * @param elemento el elemento a insertar.
     * @throws IllegalArgumentException si <code>elemento</code> es
     *         <code>null</code>.
     */
    public void inserta(int i, T elemento) {
        // Aquí va su código.
	if (elemento==null)
	    throw new IllegalArgumentException();
	else if (i<1){
	    agregaInicio(elemento);
	}
	else if (i>(longitud-1)){
	    agregaFinal(elemento);
	}
	else{
	    Nodo m=cabeza;
	    Nodo s=m;
	    int j=1;
	    s=insertaR(j,m,s,i);
	    longitud++;
	    Nodo n = new Nodo(elemento);
	    Nodo a=s.anterior;
	    n.anterior=a;
	    a.siguiente=n;
	    n.siguiente=s;
	    s.anterior=n;
	}
    }
    private Nodo  insertaR(int j, Nodo m, Nodo s, int i){
        if(j<=i){
		m=m.siguiente;
		s=m;
		j++;
		return insertaR(j,m,s,i);
	    }
	return s;
    }

    /**
     * Elimina un elemento de la lista. Si el elemento no está contenido en la
     * lista, el método no la modifica.
     * @param elemento el elemento a eliminar.
     */
    public void elimina(T elemento) {
        // Aquí va su código.
	if (elemento==null)
	    return;
	Nodo x=cabeza;
	Nodo n=buscaNodo(elemento,x);
	if(n==null)
	    {
		return;
	    }
	else{
	    borraNodo(n);
	}
    }
    private Nodo buscaNodo(T elemento,Nodo n){
	
	if(n!=null){
	    if (n.elemento.equals(elemento)){
		return n;
	    }
	    else{
		n=n.siguiente;
		return buscaNodo(elemento,n);
	    }
	}
	return null;
    }
    private void borraNodo(Nodo n){
	longitud--;
	Nodo s=n.siguiente;
	Nodo a=n.anterior;
	if (cabeza==rabo){
	    cabeza=null;
	    rabo=null;
	}
	else if (n==cabeza){
	    s.anterior=null;
	    cabeza=s;
	}
	else if (n==rabo){
	    a.siguiente=null;
	    rabo=a;
	}
	else{
	    a.siguiente=s;
	    s.anterior=a;
	}
    }

    /**
     * Elimina el primer elemento de la lista y lo regresa.
     * @return el primer elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaPrimero() {
        // Aquí va su código.
	boolean x=esVacia();
	if (x==true)
	    throw new NoSuchElementException();
	else{
	    T priEl=cabeza.elemento;
	    borraNodo(cabeza);
	    return priEl;
	}
    }

    /**
     * Elimina el último elemento de la lista y lo regresa.
     * @return el último elemento de la lista antes de eliminarlo.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T eliminaUltimo() {
        // Aquí va su código.
	boolean x=esVacia();
	if (x==true)
	    throw new NoSuchElementException();
	else{
	    T ulEl=rabo.elemento;
	    borraNodo(rabo);
	    return ulEl;
	}
    }

    /**
     * Nos dice si un elemento está en la lista.
     * @param elemento el elemento que queremos saber si está en la lista.
     * @return <code>true</code> si <code>elemento</code> está en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean contiene(T elemento) {
        // Aquí va su código.
	Nodo n=cabeza;
        return buscaNodo(elemento,n) != null;
    }

    /**
     * Regresa la reversa de la lista.
     * @return una nueva lista que es la reversa la que manda llamar el método.
     */
    public Lista<T> reversa() {
        // Aquí va su código.
	Lista<T> lista = new Lista<T>();
	Nodo n = cabeza;
	return reversaR(n,lista);
    }
    private Lista reversaR(Nodo n,Lista<T> lista){
        if (n!=null){
	    lista.agregaInicio(n.elemento);
	    n=n.siguiente;
	    return reversaR(n,lista);
	}
	return lista;
    }

    /**
     * Regresa una copia de la lista. La copia tiene los mismos elementos que la
     * lista que manda llamar el método, en el mismo orden.
     * @return una copiad de la lista.
     */
    public Lista<T> copia() {
        // Aquí va su código.
	Lista<T> lista=new Lista<T>();
	Nodo n=cabeza;
	return copiaR(n,lista);
    }
    private Lista copiaR(Nodo n,Lista<T> lista){
        if (n!=null){
	    lista.agregaFinal(n.elemento);
	    n=n.siguiente;
	    return copiaR(n,lista);
	}
	return lista;
    }

    /**
     * Limpia la lista de elementos, dejándola vacía.
     */
    public void limpia() {
        // Aquí va su código.
	cabeza=null;
	rabo=null;
	longitud=0;
    }

    /**
     * Regresa el primer elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getPrimero() {
        // Aquí va su código.
	if (cabeza==null)
	    throw new NoSuchElementException();
	else
	    return cabeza.elemento;
    }

    /**
     * Regresa el último elemento de la lista.
     * @return el primer elemento de la lista.
     * @throws NoSuchElementException si la lista es vacía.
     */
    public T getUltimo() {
        // Aquí va su código.
	if (rabo==null)
	    throw new NoSuchElementException();
	else
	    return rabo.elemento;
    }

    /**
     * Regresa el <em>i</em>-ésimo elemento de la lista.
     * @param i el índice del elemento que queremos.
     * @return el <em>i</em>-ésimo elemento de la lista.
     * @throws ExcepcionIndiceInvalido si <em>i</em> es menor que cero o mayor o
     *         igual que el número de elementos en la lista.
     */
    public T get(int i) {
        // Aquí va su código.
	if (i<0||i>=longitud)
	    throw new ExcepcionIndiceInvalido();
	else{
	    int j=0;
	    Nodo s=cabeza;
	    return getR(s,i,j);
	}
    }
    private T getR(Nodo s,int i,int j){
        if (s!=null){
		if (i==j)
		    return s.elemento;
		s=s.siguiente;
		j++;
		return getR(s,i,j);
	    }
	    return s.elemento;
    }

    /**
     * Regresa el índice del elemento recibido en la lista.
     * @param elemento el elemento del que se busca el índice.
     * @return el índice del elemento recibido en la lista, o -1 si el elemento
     *         no está contenido en la lista.
     */
    public int indiceDe(T elemento) {
        // Aquí va su código.
	int i=0;
	Nodo n=cabeza;
	return indiceR(n,elemento,i);
    }
    private int indiceR(Nodo n, T elemento, int i){
        if (n!=null){
	    if(n.elemento.equals(elemento))
		return i;
	    n=n.siguiente;
	    i++;
	    return indiceR(n,elemento,i);
	}
	return -1;
    }

    /**
     * Regresa una representación en cadena de la lista.
     * @return una representación en cadena de la lista.
     */
    @Override public String toString() {
        // Aquí va su código.
	Nodo n=cabeza;
	if (n==null)
	    return "[]";
	else{
	    String r = "["+n.elemento;
	    n=n.siguiente;
	    return toR(n,r);
	}
    }
    private String toR(Nodo n,String r){
        if(n!=null){
		r+=", "+n.elemento;
		n=n.siguiente;
		return toR(n,r);
	    }
	    r+="]";
	    return r;
    }

    /**
     * Nos dice si la lista es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la lista es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Lista<T> lista = (Lista<T>)objeto;
        // Aquí va su código.
	if(lista==null)
	    return false;
	else if (longitud!=lista.longitud)
	    return false;
	else{
	    Nodo n=cabeza;
	    Nodo l=lista.cabeza;
	    return equalsR(n,l);
	}
    }
    private boolean equalsR(Nodo n,Nodo l){
        if (n!=null){
		if (!n.elemento.equals(l.elemento))
		    return false;
		else{
		    n=n.siguiente;
		    l=l.siguiente;
		    return equalsR(n,l);
		}
	    }
	    return true;
    }

    /**
     * Regresa un iterador para recorrer la lista en una dirección.
     * @return un iterador para recorrer la lista en una dirección.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
     * Regresa un iterador para recorrer la lista en ambas direcciones.
     * @return un iterador para recorrer la lista en ambas direcciones.
     */
    public IteradorLista<T> iteradorLista() {
        return new Iterador();
    }

    /**
     * Regresa una copia de la lista, pero ordenada. Para poder hacer el
     * ordenamiento, el método necesita una instancia de {@link Comparator} para
     * poder comparar los elementos de la lista.
     * @param comparador el comparador que la lista usará para hacer el
     *                   ordenamiento.
     * @return una copia de la lista, pero ordenada.
     */
    public Lista<T> mergeSort(Comparator<T> comparador) {
        // Aquí va su código.
	return mergeSort(copia(), comparador);
    }
    private Lista<T> mergeSort(Lista<T> l, Comparator<T> comparador){
      if(l.esVacia() || l.getLongitud() == 1) return l;
      int mitad = l.getLongitud() / 2 ;
      Lista<T> l1 = new Lista<T>();
      Lista<T> l2;
      while(l.getLongitud() != mitad){
        l1.agregaFinal(l.getPrimero());
        if(l.getLongitud() != 0)
          l.eliminaPrimero();
      }
      l2 = l.copia();
      return mezcla(mergeSort(l1, comparador), mergeSort(l2, comparador), comparador);
    }

    private Lista<T> mezcla(Lista<T> a, Lista<T> b, Comparator<T> comparador){
        Lista<T> listaOrdenada = new Lista<T>();
        while(a.cabeza != null && b.cabeza != null){
            int i = comparador.compare(a.cabeza.elemento, b.cabeza.elemento);
            if(i <= 0){
              listaOrdenada.agregaFinal(a.getPrimero());
              a.eliminaPrimero();
            }else{
              listaOrdenada.agregaFinal(b.getPrimero());
              b.eliminaPrimero();
            }
        }
        while(a.cabeza != null){
          listaOrdenada.agregaFinal(a.getPrimero());
          a.eliminaPrimero();
        }
        while(b.cabeza != null){
          listaOrdenada.agregaFinal(b.getPrimero());
          b.eliminaPrimero();
        }
        return listaOrdenada;
    }


    /**
     * Regresa una copia de la lista recibida, pero ordenada. La lista recibida
     * tiene que contener nada más elementos que implementan la interfaz {@link
     * Comparable}.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista que se ordenará.
     * @return una copia de la lista recibida, pero ordenada.
     */
    public static <T extends Comparable<T>>
    Lista<T> mergeSort(Lista<T> lista) {
        return lista.mergeSort((a, b) -> a.compareTo(b));
    }

    /**
     * Busca un elemento en la lista ordenada, usando el comparador recibido. El
     * método supone que la lista está ordenada usando el mismo comparador.
     * @param elemento el elemento a buscar.
     * @param comparador el comparador con el que la lista está ordenada.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public boolean busquedaLineal(T elemento, Comparator<T> comparador) {
        // Aquí va su código.
	return busquedaLineal(copia(), elemento);
    }
    private boolean busquedaLineal(Lista<T> lista, T elemento){
        return lista.contiene(elemento);
    }

    /**
     * Busca un elemento en una lista ordenada. La lista recibida tiene que
     * contener nada más elementos que implementan la interfaz {@link
     * Comparable}, y se da por hecho que está ordenada.
     * @param <T> tipo del que puede ser la lista.
     * @param lista la lista donde se buscará.
     * @param elemento el elemento a buscar.
     * @return <code>true</code> si el elemento está contenido en la lista,
     *         <code>false</code> en otro caso.
     */
    public static <T extends Comparable<T>>
    boolean busquedaLineal(Lista<T> lista, T elemento) {
        return lista.busquedaLineal(elemento, (a, b) -> a.compareTo(b));
    }
}
