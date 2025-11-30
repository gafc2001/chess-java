package presentacion.vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import shared.utils.ImageUtil;

public class VistaJuego extends JFrame {

    private JPanel pnlTablero;
    private JPanel pnlInfo;
    private JPanel[][] casillas;

    private final Color colorClaro = new Color(240, 217, 181);
    private final Color colorOscuro = new Color(181, 136, 99);

    public VistaJuego() {
        initComponents();
        inicializarTablero();
        colocarPiezasIniciales();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        // mainPanel.setBackground(Color.DARK_GRAY);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        pnlTablero = new JPanel(new GridLayout(8, 8));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.66; 
        int padding = 50;
        gbc.insets = new Insets(padding, padding, padding, padding);
        mainPanel.add(pnlTablero, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);

        pnlInfo = new JPanel(new GridLayout(2, 1));
        pnlInfo.setBackground(Color.WHITE);
        
        // Info Jugador 2 (Negras - Arriba)
        JPanel pnlJugador2 = crearPanelJugador("Jugador 2 (Negras)", "assets/images/user.png"); // Placeholder img
        pnlInfo.add(pnlJugador2);
        
        // Info Jugador 1 (Blancas - Abajo)
        JPanel pnlJugador1 = crearPanelJugador("Jugador 1 (Blancas)", "assets/images/user.png"); // Placeholder img
        pnlInfo.add(pnlJugador1);

        gbc.gridx = 1;
        gbc.weightx = 0.33;
        mainPanel.add(pnlInfo, gbc);

        JButton btnSalir = new JButton("Volver al Menú");
        btnSalir.addActionListener(e -> {
            new VistaMenuPrincipal().mostrar();
            dispose();
        });
        // Añadir el botón al panel de info (chapuza rápida para que esté accesible)
        JPanel pnlBoton = new JPanel();
        pnlBoton.add(btnSalir);
        pnlInfo.add(pnlBoton, 0); // Lo metemos arriba del todo o lo gestionamos mejor luego
        // Corrección: GridLayout(2,1) no deja espacio. Mejor usar un BorderLayout en pnlInfo.
        pnlInfo.setLayout(new BorderLayout());
        pnlInfo.removeAll();
        
        JPanel pnlTopRight = new JPanel(new BorderLayout());
        pnlTopRight.add(pnlJugador2, BorderLayout.CENTER);
        
        // Boton cerrar en la esquina superior derecha
        JLabel lblCerrar = new JLabel("X  ");
        lblCerrar.setFont(new Font("Arial", Font.BOLD, 20));
        lblCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCerrar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCerrar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new VistaMenuPrincipal().mostrar();
                dispose();
            }
        });
        pnlTopRight.add(lblCerrar, BorderLayout.NORTH);

        pnlInfo.add(pnlTopRight, BorderLayout.NORTH);
        pnlInfo.add(pnlJugador1, BorderLayout.SOUTH);
        
        // Un panel central en la derecha para historial o chat
        JPanel pnlExtra = new JPanel();
        pnlExtra.setBackground(Color.LIGHT_GRAY);
        pnlExtra.add(new JLabel("Historial de Movimientos"));
        pnlInfo.add(pnlExtra, BorderLayout.CENTER);


        mainPanel.add(pnlInfo, gbc);
        this.setContentPane(mainPanel);
    }

    private JPanel crearPanelJugador(String nombre, String imgPath) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(nombre));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(0, 150)); // Altura fija
        
        JLabel lblAvatar = new JLabel();
        lblAvatar.setPreferredSize(new Dimension(50, 50));
        lblAvatar.setBorder(new LineBorder(Color.BLACK));
        // ImageUtil.escalarImg(lblAvatar, imgPath); // Si existe la imagen
        lblAvatar.setText("IMG");
        
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Roboto", Font.BOLD, 16));
        
        panel.add(lblAvatar);
        panel.add(lblNombre);
        
        return panel;
    }

    private void inicializarTablero() {
        casillas = new JPanel[8][8];
        pnlTablero.removeAll();

        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                JPanel casilla = new JPanel(new BorderLayout());
                if ((fila + col) % 2 == 0) {
                    casilla.setBackground(colorClaro);
                } else {
                    casilla.setBackground(colorOscuro);
                }
                casillas[fila][col] = casilla;
                pnlTablero.add(casilla);
            }
        }
    }

    private void colocarPiezasIniciales() {
        // Orden de piezas: Torre, Caballo, Alfil, Reina, Rey, Alfil, Caballo, Torre
        String[] piezas = {"torre", "caballo", "alfin", "reina", "rey", "alfin", "caballo", "torre"};
        
        // Negras (Fila 0 y 1)
        for (int i = 0; i < 8; i++) {
            colocarPieza(0, i, piezas[i], "negro");
            colocarPieza(1, i, "peon", "negro");
        }

        // Blancas (Fila 6 y 7)
        for (int i = 0; i < 8; i++) {
            colocarPieza(6, i, "peon", "blanco");
            colocarPieza(7, i, piezas[i], "blanco");
        }
    }

    private void colocarPieza(int fila, int col, String tipo, String color) {
        String ruta = "assets/piezas/" + tipo + "_" + color + ".png";
        JLabel lblPieza = new JLabel();
        lblPieza.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Usamos ImageUtil. Como el tamaño de la casilla es dinámico, 
        // idealmente deberíamos reescalar cuando el componente cambie de tamaño.
        // Por simplicidad ahora, forzaremos un tamaño o dejaremos que ImageUtil lo intente.
        // ImageUtil necesita que el label tenga tamaño. Al inicio es 0.
        // Vamos a usar un truco: poner un Icon que cargue la imagen.
        
        // Mejor opción: Crear una clase JLabel personalizada que pinte la imagen escalada
        // O llamar a ImageUtil después de que la ventana sea visible.
        // Vamos a intentar usar ImageUtil con un tamaño estimado o fijo por ahora,
        // ya que en pantalla completa las casillas serán grandes.
        
        // Estimación: Pantalla 1920x1080 -> Tablero ~1000px -> Casilla ~120px
        lblPieza.setSize(80, 80); // Tamaño base para que ImageUtil funcione
        ImageUtil.escalarImgMantenerProporcion(lblPieza, ruta);
        
        casillas[fila][col].add(lblPieza, BorderLayout.CENTER);
    }

    public void mostrar() {
        this.setVisible(true);
        // Forzar repintado de imágenes una vez visible para ajustar tamaños si fuera necesario
        // (Implementación futura para responsive real)
    }
}
