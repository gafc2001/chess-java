package aplicacion.casosuso;

import dominio.modelos.Usuario;
import dominio.puertos.RepositorioUsuario;

public class IniciarSesion {
    private final RepositorioUsuario repositorioUsuario;

    public IniciarSesion(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public Usuario ejecutar(String nombreUsuario, String clave) {
        Usuario usuario = repositorioUsuario.buscarPorNombreUsuario(nombreUsuario);
        
        if (usuario != null && usuario.verificarPassword(clave)) {
            return usuario;
        }
        
        return null;
    }
}
