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
        
        JPanel pnlJugador2 = crearPanelJugador("Jugador 2 (Negras)", "assets/images/user.png");
        pnlInfo.add(pnlJugador2);
        
        JPanel pnlJugador1 = crearPanelJugador("Jugador 1 (Blancas)", "assets/images/user.png");
        pnlInfo.add(pnlJugador1);

        gbc.gridx = 1;
        gbc.weightx = 0.33;
        mainPanel.add(pnlInfo, gbc);

        JButton btnSalir = new JButton("Volver al Menú");
        btnSalir.addActionListener(e -> {
            new VistaMenuPrincipal().mostrar();
            dispose();
        });
        JPanel pnlBoton = new JPanel();
        pnlBoton.add(btnSalir);
        pnlInfo.add(pnlBoton, 0);
        pnlInfo.setLayout(new BorderLayout());
        pnlInfo.removeAll();
        
        JPanel pnlTopRight = new JPanel(new BorderLayout());
        pnlTopRight.add(pnlJugador2, BorderLayout.CENTER);
        
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
        panel.setPreferredSize(new Dimension(0, 150));
        
        JLabel lblAvatar = new JLabel();
        lblAvatar.setPreferredSize(new Dimension(50, 50));
        lblAvatar.setBorder(new LineBorder(Color.BLACK));
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
        String[] piezas = {"torre", "caballo", "alfin", "reina", "rey", "alfin", "caballo", "torre"};
        
        for (int i = 0; i < 8; i++) {
            colocarPieza(0, i, piezas[i], "negro");
            colocarPieza(1, i, "peon", "negro");
        }

        for (int i = 0; i < 8; i++) {
            colocarPieza(6, i, "peon", "blanco");
            colocarPieza(7, i, piezas[i], "blanco");
        }
    }

    private void colocarPieza(int fila, int col, String tipo, String color) {
        String ruta = "assets/piezas/" + tipo + "_" + color + ".png";
        JLabel lblPieza = new JLabel();
        lblPieza.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblPieza.setSize(80, 80);
        ImageUtil.escalarImgMantenerProporcion(lblPieza, ruta);
        
        casillas[fila][col].add(lblPieza, BorderLayout.CENTER);
    }

    public void mostrar() {
        this.setVisible(true);
    }
}
