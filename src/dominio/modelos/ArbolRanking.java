package dominio.modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * Arbol binario de busqueda ordenado por victorias.
 * Menos victorias a la izquierda, mas victorias a la derecha; los empates van a la izquierda
 * para que, en el recorrido inorden inverso, se respete el orden de insercion.
 */
public class ArbolRanking {
    private NodoRanking raiz;
    private int tamanio;

    public void insertar(Usuario usuario) {
        raiz = insertar(raiz, new NodoRanking(usuario));
        tamanio++;
    }

    private NodoRanking insertar(NodoRanking actual, NodoRanking nuevo) {
        if (actual == null) return nuevo;

        if (nuevo.getVictorias() > actual.getVictorias()) {
            actual.setDerecho(insertar(actual.getDerecho(), nuevo));
        } else {
            actual.setIzquierdo(insertar(actual.getIzquierdo(), nuevo));
        }
        return actual;
    }

    /**
     * Recorrido inorden inverso (derecho - raiz - izquierdo): devuelve los usuarios de mayor a menor victorias.
     */
    public List<Usuario> aRanking() {
        List<Usuario> ranking = new ArrayList<>();
        inordenInverso(raiz, ranking);
        return ranking;
    }

    private void inordenInverso(NodoRanking actual, List<Usuario> ranking) {
        if (actual == null) return;

        inordenInverso(actual.getDerecho(), ranking);
        ranking.add(actual.getUsuario());
        inordenInverso(actual.getIzquierdo(), ranking);
    }

    public boolean estaVacio() {
        return tamanio == 0;
    }
    public NodoRanking getRaiz() {
        return raiz;
    }
    public int getTamanio() {
        return tamanio;
    }
}
