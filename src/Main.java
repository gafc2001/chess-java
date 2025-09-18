import modelos.*;
import modelos.piezas.*;
import enums.*;

public class Main {
    public static void main(String[] args) {
        
        Usuario gustavo = new Usuario("gafc2001", "Gustavo", "Farfan");

        Tablero tablero = new Tablero();

        System.out.println(gustavo.toString());
    }
}
