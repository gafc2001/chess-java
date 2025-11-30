import aplicacion.casosuso.IniciarSesion;
import infraestructura.persistencia.RepositorioUsuarioMemoria;
import presentacion.controladores.ControladorLogin;
import presentacion.vistas.VistaLogin;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RepositorioUsuarioMemoria repositorio = new RepositorioUsuarioMemoria();
            IniciarSesion iniciarSesion = new IniciarSesion(repositorio);
            VistaLogin vista = new VistaLogin();
            ControladorLogin controlador = new ControladorLogin(vista, iniciarSesion);
            controlador.iniciar();
        });
    }
}
