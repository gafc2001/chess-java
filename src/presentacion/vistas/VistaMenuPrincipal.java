package presentacion.vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import shared.utils.ImageUtil;

public class VistaMenuPrincipal extends JFrame {

    private JPanel jPanel_fondo;
    private JLabel jLabel_logo;
    private JLabel jLabel_titulo;

    public VistaMenuPrincipal() {
        initComponents();
        
        // Cargar logo (se ajustará al tamaño del label cuando se muestre)
        // Usamos un timer o invokeLater para asegurar que el componente tenga tamaño, 
        // o usamos un tamaño fijo para el logo.
        String rutaLogo = "assets/images/img_logo.png";
        // Damos un tamaño preferido al logo para que ImageUtil tenga dimensiones base
        jLabel_logo.setSize(200, 100); 
        ImageUtil.escalarImg(jLabel_logo, rutaLogo);
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa

        jPanel_fondo = new JPanel();
        jPanel_fondo.setBackground(Color.WHITE);
        jPanel_fondo.setLayout(new BorderLayout());

        // --- Botón Cerrar (Top Right) ---
        JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        pnlTop.setBackground(Color.WHITE);
        
        JLabel lblCerrar = new JLabel("X");
        lblCerrar.setFont(new Font("Roboto Light", Font.BOLD, 24));
        lblCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
            public void mouseEntered(MouseEvent e) { lblCerrar.setForeground(Color.RED); }
            public void mouseExited(MouseEvent e) { lblCerrar.setForeground(Color.BLACK); }
        });
        pnlTop.add(lblCerrar);
        jPanel_fondo.add(pnlTop, BorderLayout.NORTH);

        // --- Contenido Central ---
        JPanel pnlCentro = new JPanel();
        pnlCentro.setBackground(Color.WHITE);
        pnlCentro.setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(15, 0, 15, 0); // Espacio vertical entre elementos
        gbc.anchor = GridBagConstraints.CENTER;

        // 1. Logo
        jLabel_logo = new JLabel();
        jLabel_logo.setPreferredSize(new Dimension(200, 100));
        jLabel_logo.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCentro.add(jLabel_logo, gbc);

        // 2. Título
        jLabel_titulo = new JLabel("MENÚ PRINCIPAL");
        jLabel_titulo.setFont(new Font("Roboto Medium", Font.BOLD, 36));
        jLabel_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        pnlCentro.add(jLabel_titulo, gbc);

        // Espacio extra antes de los botones
        gbc.insets = new Insets(30, 0, 15, 0);

        // 3. Botones Verticales
        pnlCentro.add(crearBoton("JUGAR 1vs1", true, e -> {
            new VistaJuego().mostrar();
            dispose();
        }), gbc);
        
        gbc.insets = new Insets(15, 0, 15, 0); // Restaurar insets normales
        pnlCentro.add(crearBoton("MI PERFIL", false, e -> System.out.println("Perfil")), gbc);
        pnlCentro.add(crearBoton("RANKING", false, e -> System.out.println("Ranking")), gbc);
        pnlCentro.add(crearBoton("CERRAR SESIÓN", false, e -> dispose()), gbc);

        jPanel_fondo.add(pnlCentro, BorderLayout.CENTER);

        this.setContentPane(jPanel_fondo);
    }

    private JPanel crearBoton(String texto, boolean esPrincipal, ActionListener accion) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(300, 60)); // Botones más anchos y altos
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        if (esPrincipal) {
            panel.setBackground(Color.BLACK);
        } else {
            panel.setBackground(Color.WHITE);
            panel.setBorder(new LineBorder(Color.BLACK, 2));
        }

        JLabel label = new JLabel(texto);
        label.setFont(new Font("Roboto", Font.BOLD, 18));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        
        if (esPrincipal) {
            label.setForeground(Color.WHITE);
        } else {
            label.setForeground(Color.BLACK);
        }

        // Eventos del Mouse para efecto Hover y Click
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (accion != null) accion.actionPerformed(null);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (esPrincipal) {
                    panel.setBackground(new Color(50, 50, 50));
                } else {
                    panel.setBackground(Color.BLACK);
                    label.setForeground(Color.WHITE);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (esPrincipal) {
                    panel.setBackground(Color.BLACK);
                } else {
                    panel.setBackground(Color.WHITE);
                    label.setForeground(Color.BLACK);
                }
            }
        });

        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    public void mostrar() {
        this.setVisible(true);
    }
    
    public void cerrar() {
        this.dispose();
    }
}
