package modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String id;
    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private LocalDateTime fechaRegistro;
    private List<Partida> partidasJugadas;
    private int partidasGanadas;
    private int partidasPerdidas;
    private int partidasEmpatadas;
    
    public Usuario(String nombreUsuario, String nombre, String apellido) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaRegistro = LocalDateTime.now();
        this.partidasJugadas = new ArrayList<>();
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
        this.partidasEmpatadas = 0;
    }
    
    public void agregarPartida(Partida partida) {
        this.partidasJugadas.add(partida);
    }
    
    public void incrementarVictorias() {
        this.partidasGanadas++;
    }
    
    public void incrementarDerrotas() {
        this.partidasPerdidas++;
    }
    
    public void incrementarEmpates() {
        this.partidasEmpatadas++;
    }
    
    public String getId() {
        return id;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    public List<Partida> getPartidasJugadas() {
        return partidasJugadas;
    }
    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }
    public int getPartidasEmpatadas() {
        return partidasEmpatadas;
    }
    
    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + nombreUsuario + '\'' +
                ", nombre='" + nombre + " " + apellido + '\'' +
                ", victorias=" + partidasGanadas +
                ", derrotas=" + partidasPerdidas +
                ", empates=" + partidasEmpatadas +
                '}';
    }
}
