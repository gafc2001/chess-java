package infraestructura.persistencia;

import dominio.modelos.Usuario;
import dominio.puertos.RepositorioUsuario;
import java.util.ArrayList;
import java.util.List;


public class RepositorioUsuarioMemoria implements RepositorioUsuario {
    private List<Usuario> usuarios;

    public RepositorioUsuarioMemoria() {
        this.usuarios = new ArrayList<>();
        usuarios.add(new Usuario("aramirez", "Alma", "Ramirez Arias", "123456"));
        usuarios.add(new Usuario("andre", "Andre", "", "123456"));
        usuarios.add(new Usuario("gfarfan", "Gustavo", "Farfan Coraje", "123456"));
        usuarios.add(new Usuario("gfarfan2", "Gustavo 2", "Farfan Coraje", "123456"));
        usuarios.add(new Usuario("gfarfan3", "Gustavo 3", "Farfan Coraje", "123456"));
    }

    @Override
    public Usuario buscarPorCorreo(String usuario) {
        return usuarios.stream()
                .filter(u -> u.getNombreUsuario().equals(usuario))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void guardar(Usuario usuario) {
        usuarios.add(usuario);
    }

    @Override
    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }
}
