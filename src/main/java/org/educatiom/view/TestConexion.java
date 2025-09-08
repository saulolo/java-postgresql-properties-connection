package org.educatiom.view;

import org.educatiom.data.Conexion;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

/**
 * Clase de la vista que implementa una interfaz gráfica de usuario (GUI) simple con Swing.
 */
public class TestConexion extends JFrame{

    private JPanel mainPanel;
    private JButton btnConectarBD;

    /**
     * Constructor de la clase TestConexion.
     * <p>
     * Configura el comportamiento de la ventana y llama a los métodos para
     * inicializar los componentes y los manejadores de eventos.
     * </p>
     */
    public TestConexion() {
        // Asegura que el panel y los componentes se inicialicen primero
        initComponents();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Conexión a la BD usando Properties");
        this.add(mainPanel); // Agrega el panel principal al JFrame
        this.pack();
        this.setVisible(true);
    }


    /**
     * Inicializa y organiza todos los componentes de la interfaz de usuario.
     * <p>
     * Este método se encarga de crear el panel principal, el botón y de
     * añadir los componentes al panel, siguiendo un layout específico.
     * </p>
     */
    private void initComponents() {
        // 1. Inicializa el panel principal con un layout.
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20)); // Centra el botón
        mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // Añade espacio alrededor

        //2. Inicializa el botón.
        btnConectarBD = new JButton("Conectar a la DB");
        btnConectarBD.setPreferredSize(new Dimension(200, 30));

        //3. Agrega el botón al panel.
        mainPanel.add(btnConectarBD);

        // 4. Agregar los ActionListeners
        addEventHandlers();
    }

    /**
     * Configura los manejadores de eventos (ActionListeners) para los componentes de la interfaz.
     * <p>
     * Se asocia un listener al botón de conexión que, al ser presionado, intenta
     * establecer la conexión y muestra el resultado en un cuadro de diálogo.
     * </p>
     */
    private void addEventHandlers() {
        btnConectarBD.addActionListener(e -> {
            try {
                // Llama al métod getConnection de la clase Conexion.
                // Usamos un try-with-resources para asegurar el cierre de la conexión si es exitosa.
                try (Connection connection = Conexion.getInstance().getConnection()) {
                    JOptionPane.showMessageDialog(this, "Conexión Exitosa!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception ex) {
                // Captura la excepción en caso de que la conexión falle y muestra el error.
                JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
