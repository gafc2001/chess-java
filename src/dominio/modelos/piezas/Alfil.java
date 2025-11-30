package dominio.modelos.piezas;

import dominio.enums.Color;
import dominio.enums.TipoPieza;
import dominio.modelos.Posicion;
import dominio.modelos.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Alfil extends Pieza {
    
    public Alfil(Color color, Posicion posicion) {
        super(color, posicion, TipoPieza.ALFIL);
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
