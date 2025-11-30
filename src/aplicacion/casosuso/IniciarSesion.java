package aplicacion.casosuso;

import dominio.modelos.Usuario;
import dominio.puertos.RepositorioUsuario;

public class IniciarSesion {
    private final RepositorioUsuario repositorioUsuario;

    public IniciarSesion(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public Usuario ejecutar(String correo, String clave) {
        Usuario usuario = repositorioUsuario.buscarPorCorreo(correo);
        
        if (usuario != null && usuario.getPassword().equals(clave)) {
            return usuario;
        }
        
        return null;
    }
}
