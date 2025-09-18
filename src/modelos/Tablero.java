package modelos;

import enums.Color;
import modelos.piezas.*;

public class Tablero {
    private Pieza[][] tablero;
    
    public Tablero() {
        this.tablero = new Pieza[8][8];
        inicializarTablero();
    }
    
    private void inicializarTablero() {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                tablero[fila][columna] = null;
            }
        }
        
        tablero[0][0] = new Torre(Color.BLANCO, new Posicion(0, 0));
        tablero[0][1] = new Caballo(Color.BLANCO, new Posicion(0, 1));
        tablero[0][2] = new Alfil(Color.BLANCO, new Posicion(0, 2));
        tablero[0][3] = new Reina(Color.BLANCO, new Posicion(0, 3));
        tablero[0][4] = new Rey(Color.BLANCO, new Posicion(0, 4));
        tablero[0][5] = new Alfil(Color.BLANCO, new Posicion(0, 5));
        tablero[0][6] = new Caballo(Color.BLANCO, new Posicion(0, 6));
        tablero[0][7] = new Torre(Color.BLANCO, new Posicion(0, 7));
        
        for (int columna = 0; columna < 8; columna++) {
            tablero[1][columna] = new Peon(Color.BLANCO, new Posicion(1, columna));
        }
        
        tablero[7][0] = new Torre(Color.NEGRO, new Posicion(7, 0));
        tablero[7][1] = new Caballo(Color.NEGRO, new Posicion(7, 1));
        tablero[7][2] = new Alfil(Color.NEGRO, new Posicion(7, 2));
        tablero[7][3] = new Reina(Color.NEGRO, new Posicion(7, 3));
        tablero[7][4] = new Rey(Color.NEGRO, new Posicion(7, 4));
        tablero[7][5] = new Alfil(Color.NEGRO, new Posicion(7, 5));
        tablero[7][6] = new Caballo(Color.NEGRO, new Posicion(7, 6));
        tablero[7][7] = new Torre(Color.NEGRO, new Posicion(7, 7));
        
        for (int columna = 0; columna < 8; columna++) {
            tablero[6][columna] = new Peon(Color.NEGRO, new Posicion(6, columna));
        }
    }
    
    public Pieza getPieza(Posicion posicion) {
        if (!posicion.esValida()) return null;
        return tablero[posicion.getFila()][posicion.getColumna()];
    }
    
    public void setPieza(Posicion posicion, Pieza pieza) {
        if (posicion.esValida()) {
            tablero[posicion.getFila()][posicion.getColumna()] = pieza;
            if (pieza != null) {
                pieza.mover(posicion);
            }
        }
    }
    
    public void removerPieza(Posicion posicion) {
        if (posicion.esValida()) {
            tablero[posicion.getFila()][posicion.getColumna()] = null;
        }
    }
    
    public boolean ejecutarMovimiento(Posicion origen, Posicion destino) {
        Pieza pieza = getPieza(origen);
        if (pieza == null) return false;
        
        if (!pieza.esMovimientoValido(destino, this)) return false;
        
        removerPieza(origen);
        setPieza(destino, pieza);
        
        return true;
    }
}
