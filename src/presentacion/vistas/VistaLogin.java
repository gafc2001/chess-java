package presentacion.vistas;

import java.awt.Color;

import presentacion.controladores.ControladorLogin;
import shared.utils.ImageUtil;

public class VistaLogin extends javax.swing.JFrame {
    
    int xMouse, yMouse;
    
    public VistaLogin() {
        initComponents();
        
        String rutaLogo = "assets/images/img_logo.png";
        ImageUtil.escalarImg(jLabel_logo, rutaLogo);
        
        String rutaImagen = "assets/images/img_login.jpg";
        ImageUtil.escalarImgPorAltoYCentrar(jLabel_lateral, rutaImagen);
        
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {

        jPanel_fondo = new javax.swing.JPanel();
        jLabel_logo = new javax.swing.JLabel();
        jLabel_lateral = new javax.swing.JLabel();
        jPanel_encabezado = new javax.swing.JPanel();
        jPanel_cerrar = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_user = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txt_password = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel_ingresar = new javax.swing.JPanel();
        label_ingresar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jPanel_fondo.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_fondo.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_fondo.setMinimumSize(new java.awt.Dimension(800, 500));
        jPanel_fondo.setLayout(null);

        jLabel_logo.setMaximumSize(new java.awt.Dimension(200, 100));
        jLabel_logo.setMinimumSize(new java.awt.Dimension(200, 100));
        jLabel_logo.setPreferredSize(new java.awt.Dimension(200, 100));
        jPanel_fondo.add(jLabel_logo);
        jLabel_logo.setBounds(30, 40, 200, 100);

        jLabel_lateral.setMaximumSize(new java.awt.Dimension(300, 500));
        jLabel_lateral.setMinimumSize(new java.awt.Dimension(300, 500));
        jLabel_lateral.setPreferredSize(new java.awt.Dimension(300, 500));
        jPanel_fondo.add(jLabel_lateral);
        jLabel_lateral.setBounds(500, 0, 300, 500);

        jPanel_encabezado.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_encabezado.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel_encabezadoMouseDragged(evt);
            }
        });
        jPanel_encabezado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_encabezadoMousePressed(evt);
            }
        });

        jPanel_cerrar.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_cerrar.setForeground(new java.awt.Color(255, 255, 255));
        jPanel_cerrar.setMaximumSize(new java.awt.Dimension(30, 30));
        jPanel_cerrar.setMinimumSize(new java.awt.Dimension(30, 30));
        jPanel_cerrar.setRequestFocusEnabled(false);
        jPanel_cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel_cerrarMousePressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Roboto Light", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("X");
        jLabel5.setToolTipText("");
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.setMaximumSize(new java.awt.Dimension(50, 30));
        jLabel5.setMinimumSize(new java.awt.Dimension(50, 30));
        jLabel5.setPreferredSize(new java.awt.Dimension(50, 30));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_cerrarLayout = new javax.swing.GroupLayout(jPanel_cerrar);
        jPanel_cerrar.setLayout(jPanel_cerrarLayout);
        jPanel_cerrarLayout.setHorizontalGroup(
            jPanel_cerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_cerrarLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_cerrarLayout.setVerticalGroup(
            jPanel_cerrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel_encabezadoLayout = new javax.swing.GroupLayout(jPanel_encabezado);
        jPanel_encabezado.setLayout(jPanel_encabezadoLayout);
        jPanel_encabezadoLayout.setHorizontalGroup(
            jPanel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_encabezadoLayout.createSequentialGroup()
                .addComponent(jPanel_cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 750, Short.MAX_VALUE))
        );
        jPanel_encabezadoLayout.setVerticalGroup(
            jPanel_encabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_cerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel_fondo.add(jPanel_encabezado);
        jPanel_encabezado.setBounds(0, 0, 800, 40);

        jLabel1.setFont(new java.awt.Font("Roboto Medium", 1, 36)); // NOI18N
        jLabel1.setText("INICIAR SESIÓN");
        jPanel_fondo.add(jLabel1);
        jLabel1.setBounds(50, 150, 300, 50);

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel2.setText("CONTRASEÑA");
        jPanel_fondo.add(jLabel2);
        jLabel2.setBounds(50, 300, 150, 30);

        txt_user.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        txt_user.setForeground(new java.awt.Color(204, 204, 204));
        txt_user.setText("Ingrese su nombre de usuario");
        txt_user.setBorder(null);
        txt_user.setMaximumSize(new java.awt.Dimension(300, 30));
        txt_user.setMinimumSize(new java.awt.Dimension(300, 30));
        txt_user.setName(""); // NOI18N
        txt_user.setPreferredSize(new java.awt.Dimension(300, 30));
        txt_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_userMousePressed(evt);
            }
        });
        jPanel_fondo.add(txt_user);
        txt_user.setBounds(50, 240, 300, 30);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel_fondo.add(jSeparator1);
        jSeparator1.setBounds(50, 360, 300, 10);

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        jLabel3.setText("USUARIO");
        jPanel_fondo.add(jLabel3);
        jLabel3.setBounds(50, 220, 100, 20);

        txt_password.setFont(new java.awt.Font("Roboto Medium", 0, 14)); // NOI18N
        txt_password.setForeground(new java.awt.Color(204, 204, 204));
        txt_password.setText("***************");
        txt_password.setBorder(null);
        txt_password.setMaximumSize(new java.awt.Dimension(300, 30));
        txt_password.setMinimumSize(new java.awt.Dimension(300, 30));
        txt_password.setPreferredSize(new java.awt.Dimension(300, 30));
        txt_password.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txt_passwordMousePressed(evt);
            }
        });
        jPanel_fondo.add(txt_password);
        txt_password.setBounds(50, 330, 300, 30);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel_fondo.add(jSeparator2);
        jSeparator2.setBounds(50, 270, 300, 10);

        jPanel_ingresar.setBackground(new java.awt.Color(255, 255, 255));
        jPanel_ingresar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_ingresar.setMaximumSize(new java.awt.Dimension(150, 50));
        jPanel_ingresar.setMinimumSize(new java.awt.Dimension(150, 50));

        label_ingresar.setFont(new java.awt.Font("Roboto", 1, 14)); // NOI18N
        label_ingresar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_ingresar.setText("INGRESAR");
        label_ingresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        label_ingresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_ingresarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label_ingresarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label_ingresarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel_ingresarLayout = new javax.swing.GroupLayout(jPanel_ingresar);
        jPanel_ingresar.setLayout(jPanel_ingresarLayout);
        jPanel_ingresarLayout.setHorizontalGroup(
            jPanel_ingresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_ingresar, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        jPanel_ingresarLayout.setVerticalGroup(
            jPanel_ingresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_ingresar, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel_fondo.add(jPanel_ingresar);
        jPanel_ingresar.setBounds(50, 410, 150, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_fondo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_fondo, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }

    private void jPanel_encabezadoMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void jPanel_encabezadoMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x- xMouse, y - yMouse);
    }

    private void jPanel_cerrarMousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void jLabel5MousePressed(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {
        System.exit(0);
    }

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {
        jPanel_cerrar.setBackground(Color.black);
        jLabel5.setForeground(Color.white);
    }

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {
        jPanel_cerrar.setBackground(Color.white);
        jLabel5.setForeground(Color.black);
    }

    private void label_ingresarMouseClicked(java.awt.event.MouseEvent evt) {
        String usuarioIngresado = txt_user.getText();
        String passwordIngresado = new String(txt_password.getPassword());
        
        controlador.login(usuarioIngresado, passwordIngresado);
    }

    private void label_ingresarMouseEntered(java.awt.event.MouseEvent evt) {
        jPanel_ingresar.setBackground(Color.black);
        label_ingresar.setForeground(Color.white);
    }

    private void label_ingresarMouseExited(java.awt.event.MouseEvent evt) {
        jPanel_ingresar.setBackground(Color.white);
        label_ingresar.setForeground(Color.black);
    }

    private void txt_userMousePressed(java.awt.event.MouseEvent evt) {
        if (txt_user.getText().equals("Ingrese su nombre de usuario")){
            txt_user.setText("");
            txt_user.setForeground(Color.black);
        }
        
        if(String.valueOf(txt_password.getPassword()).isEmpty()){
            txt_password.setText("***************");
            txt_password.setForeground(Color.gray);
        }
    }

    private void txt_passwordMousePressed(java.awt.event.MouseEvent evt) {
        if (String.valueOf(txt_password.getPassword()).equals("***************")){
            txt_password.setText("");
            txt_password.setForeground(Color.black);
        }
        
        if(txt_user.getText().isEmpty()){
            txt_user.setText("Ingrese su nombre de usuario");
            txt_user.setForeground(Color.gray);
        } 
    }

    public void mostrar(){
        this.setVisible(true);
    }
    public void cerrar(){
        this.dispose();
    }

    public void setControlador(ControladorLogin controlador) {
        this.controlador = controlador;
    }

    private ControladorLogin controlador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel_lateral;
    private javax.swing.JLabel jLabel_logo;
    private javax.swing.JPanel jPanel_cerrar;
    private javax.swing.JPanel jPanel_encabezado;
    private javax.swing.JPanel jPanel_fondo;
    private javax.swing.JPanel jPanel_ingresar;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel label_ingresar;
    private javax.swing.JPasswordField txt_password;
    private javax.swing.JTextField txt_user;
}