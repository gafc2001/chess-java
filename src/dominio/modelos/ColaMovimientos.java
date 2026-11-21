package dominio.modelos;

/**
 * Cola (FIFO) enlazada de movimientos recibidos del oponente que aun no se aplicaron al tablero.
 * Se encola por el final y se desencola por el frente, asi se respeta el orden en que se jugaron.
 */
public class ColaMovimientos {
    private NodoMovimientoPendiente frente;
    private NodoMovimientoPendiente fin;
    private int tamanio;

    public void encolar(NodoMovimientoPendiente nodo) {
        if (frente == null) {
            frente = nodo;
        } else {
            fin.setSiguiente(nodo);
        }
        fin = nodo;
        tamanio++;
    }

    public NodoMovimientoPendiente desencolar() {
        if (frente == null) return null;

        NodoMovimientoPendiente eliminado = frente;
        frente = eliminado.getSiguiente();
        if (frente == null) {
            fin = null;
        }
        eliminado.setSiguiente(null);
        tamanio--;
        return eliminado;
    }

    public void vaciar() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return tamanio == 0;
    }
    public NodoMovimientoPendiente getFrente() {
        return frente;
    }
    public int getTamanio() {
        return tamanio;
    }
}
