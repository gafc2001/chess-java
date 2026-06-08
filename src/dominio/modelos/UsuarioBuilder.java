package dominio.modelos;

public class UsuarioBuilder {

    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String password;
    private boolean esAdmin = false;

    public UsuarioBuilder nombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        return this;
    }

    public UsuarioBuilder nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UsuarioBuilder apellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public UsuarioBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UsuarioBuilder esAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
        return this;
    }

    public Usuario build() {
        if (nombreUsuario == null || nombreUsuario.isBlank()) throw new IllegalStateException("El nombre de usuario es obligatorio.");
        if (nombre == null || nombre.isBlank())              throw new IllegalStateException("El nombre es obligatorio.");
        if (apellido == null || apellido.isBlank())          throw new IllegalStateException("El apellido es obligatorio.");
        if (password == null || password.isBlank())          throw new IllegalStateException("La contraseña es obligatoria.");

        Usuario usuario = new Usuario(nombreUsuario, nombre, apellido, password);
        usuario.setEsAdmin(esAdmin);
        return usuario;
    }
}
