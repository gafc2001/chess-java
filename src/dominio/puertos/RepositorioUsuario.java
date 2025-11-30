package dominio.puertos;

import dominio.modelos.Usuario;

public interface RepositorioUsuario {
    Usuario buscarPorCorreo(String correo);
    void guardar(Usuario usuario);
}
