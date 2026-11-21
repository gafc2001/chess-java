package dominio.modelos;

public class NodoRanking {
    private final Usuario usuario;

    private NodoRanking izquierdo;
    private NodoRanking derecho;

    public NodoRanking(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getVictorias() {
        return usuario.getPartidasGanadas();
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public NodoRanking getIzquierdo() {
        return izquierdo;
    }
    public void setIzquierdo(NodoRanking izquierdo) {
        this.izquierdo = izquierdo;
    }
    public NodoRanking getDerecho() {
        return derecho;
    }
    public void setDerecho(NodoRanking derecho) {
        this.derecho = derecho;
    }
}
