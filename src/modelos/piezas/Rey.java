package modelos.piezas;

import enums.Color;
import enums.TipoPieza;
import modelos.Posicion;
import modelos.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Rey extends Pieza {
    
    public Rey(Color color, Posicion posicion) {
        super(color, posicion, TipoPieza.REY);
    }
    
    @Override
    public List<Posicion> getMovimientosPosibles(Tablero tablero) {
        List<Posicion> movimientos = new ArrayList<>();
        
        return movimientos;
    }
    
    @Override
    public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
        return true;
    }
}
