package dominio.modelos;

public class NodoMovimientoPendiente {
    private final Posicion origen;
    private final Posicion destino;

    private NodoMovimientoPendiente siguiente;

    public NodoMovimientoPendiente(Posicion origen, Posicion destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public String toNotacion() {
        return origen.toNotacion() + "-" + destino.toNotacion();
    }

    public Posicion getOrigen() {
        return origen;
    }
    public Posicion getDestino() {
        return destino;
    }
    public NodoMovimientoPendiente getSiguiente() {
        return siguiente;
    }
    public void setSiguiente(NodoMovimientoPendiente siguiente) {
        this.siguiente = siguiente;
    }
}
