package dominio.puertos;

import java.util.List;

import dominio.modelos.Usuario;

public interface RepositorioUsuario {
    Usuario buscarPorCorreo(String correo);
    void guardar(Usuario usuario);
    List<Usuario> obtenerUsuarios();
}
