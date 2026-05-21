package view.gui;

import model.Estudiante;
import service.EstudianteService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class EstudiantePanel extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtBuscar;

    private EstudianteService service = new EstudianteService();

    public EstudiantePanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        
        JLabel titulo = new JLabel("  Gestion de Estudiantes");
        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(new Color(33, 97, 140));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        add(titulo, BorderLayout.NORTH);

  
        JPanel barraTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        barraTop.setBackground(Color.WHITE);

        txtBuscar = new JTextField(20);

        JButton btnBuscar = new JButton("Buscar");
        JButton btnLimpiar = new JButton("Limpiar");

        btnBuscar.setBackground(new Color(52, 152, 219));
        btnBuscar.setForeground(Color.WHITE);

        btnLimpiar.setBackground(Color.GRAY);
        btnLimpiar.setForeground(Color.WHITE);

        barraTop.add(new JLabel("Buscar: "));
        barraTop.add(txtBuscar);
        barraTop.add(btnBuscar);
        barraTop.add(btnLimpiar);

        
        modelo = new DefaultTableModel(
                new String[]{"ID", "Nombre", "Apellido", "Email"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modelo);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setBackground(new Color(33, 97, 140));
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setRowHeight(22);
        tabla.setGridColor(new Color(220, 220, 220));

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(Color.WHITE);

        centro.add(barraTop, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        
        JPanel barraBot = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        barraBot.setBackground(Color.WHITE);

        JButton btnRegistrar = crearBoton("Registrar", new Color(39, 174, 96));
        JButton btnEditar = crearBoton("Editar", new Color(52, 152, 219));
        JButton btnEliminar = crearBoton("Eliminar", new Color(231, 76, 60));

        barraBot.add(btnRegistrar);
        barraBot.add(btnEditar);
        barraBot.add(btnEliminar);

        add(barraBot, BorderLayout.SOUTH);

        
        cargarTabla();

        

       
        btnRegistrar.addActionListener(e -> abrirDialogo(null));

        
        btnEditar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona un estudiante para editar."
                );

                return;
            }

            int id = (int) modelo.getValueAt(fila, 0);

            Estudiante estudiante = service.obtenerPorId(id);

            abrirDialogo(estudiante);
        });

        
        btnEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona un estudiante para eliminar."
                );

                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que deseas eliminar este estudiante?",
                    "Confirmar eliminación",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmacion == JOptionPane.YES_OPTION) {

                int id = (int) modelo.getValueAt(fila, 0);

                service.eliminar(id);

                cargarTabla();
            }
        });

        
        btnBuscar.addActionListener(e ->
                buscar(txtBuscar.getText().trim())
        );

       
        btnLimpiar.addActionListener(e -> {

            txtBuscar.setText("");

            cargarTabla();
        });
    }

    
    private void cargarTabla() {

        modelo.setRowCount(0);

        List<Estudiante> lista = service.obtenerTodos();

        for (Estudiante e : lista) {

            modelo.addRow(new Object[]{
                    e.getIdEstudiante(),
                    e.getNombre(),
                    e.getApellido(),
                    e.getEmail()
            });
        }
    }

    
    private void buscar(String texto) {

        modelo.setRowCount(0);

        List<Estudiante> lista = service.obtenerTodos();

        for (Estudiante e : lista) {

            if (
                    e.getNombre().toLowerCase().contains(texto.toLowerCase())
                            ||
                            e.getApellido().toLowerCase().contains(texto.toLowerCase())
            ) {

                modelo.addRow(new Object[]{
                        e.getIdEstudiante(),
                        e.getNombre(),
                        e.getApellido(),
                        e.getEmail()
                });
            }
        }
    }

    
    private JButton crearBoton(String texto, Color color) {

        JButton boton = new JButton(texto);

        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setPreferredSize(new Dimension(110, 32));
        boton.setFocusPainted(false);

        return boton;
    }

    
    private void abrirDialogo(Estudiante est) {

        boolean esEdicion = (est != null);

        JDialog dialogo = new JDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                esEdicion
                        ? "Editar Estudiante"
                        : "Registrar Estudiante",
                true
        );

        dialogo.setSize(360, 260);
        dialogo.setLocationRelativeTo(this);
        dialogo.setLayout(new BorderLayout());

        
        JPanel formulario = new JPanel(new GridBagLayout());

        formulario.setBackground(Color.WHITE);

        formulario.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 10, 20)
        );

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(6, 5, 6, 5);
        g.fill = GridBagConstraints.HORIZONTAL;

        // Titulo
        JLabel lblTitulo = new JLabel(
                esEdicion
                        ? "  Editar Estudiante"
                        : "  Registrar Estudiante"
        );

        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitulo.setForeground(new Color(33, 97, 140));

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;

        formulario.add(lblTitulo, g);

        g.gridwidth = 1;

       
        JTextField txtNombre = new JTextField(15);
        JTextField txtApellido = new JTextField(15);
        JTextField txtEmail = new JTextField(15);

        
        if (esEdicion) {

            txtNombre.setText(est.getNombre());
            txtApellido.setText(est.getApellido());
            txtEmail.setText(est.getEmail());
        }

        
        g.gridy = 1;
        g.gridx = 0;
        formulario.add(new JLabel("Nombre:"), g);

        g.gridx = 1;
        formulario.add(txtNombre, g);

        
        g.gridy = 2;
        g.gridx = 0;
        formulario.add(new JLabel("Apellido:"), g);

        g.gridx = 1;
        formulario.add(txtApellido, g);

        
        g.gridy = 3;
        g.gridx = 0;
        formulario.add(new JLabel("Email:"), g);

        g.gridx = 1;
        formulario.add(txtEmail, g);

        
        JPanel panelBotones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 8)
        );

        panelBotones.setBackground(Color.WHITE);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.setBackground(new Color(39, 174, 96));
        btnGuardar.setForeground(Color.WHITE);

        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setForeground(Color.WHITE);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);

       
        btnGuardar.addActionListener(e -> {

            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String email = txtEmail.getText().trim();

            
            if (
                    nombre.isEmpty()
                            || apellido.isEmpty()
                            || email.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        dialogo,
                        "Todos los campos son obligatorios."
                );

                return;
            }

            if (!email.contains("@")) {

                JOptionPane.showMessageDialog(
                        dialogo,
                        "Ingresa un email válido."
                );

                return;
            }

            // Editar
            if (esEdicion) {

                est.setNombre(nombre);
                est.setApellido(apellido);
                est.setEmail(email);

                service.actualizar(est);

                JOptionPane.showMessageDialog(
                        dialogo,
                        "Estudiante actualizado correctamente."
                );

            } else {

                
                Estudiante nuevo = new Estudiante(
                        0,
                        nombre,
                        apellido,
                        email
                );

                service.registrar(nuevo);

                JOptionPane.showMessageDialog(
                        dialogo,
                        "Estudiante registrado correctamente."
                );
            }

            cargarTabla();

            dialogo.dispose();
        });

        
        btnCancelar.addActionListener(e ->
                dialogo.dispose()
        );

        dialogo.add(formulario, BorderLayout.CENTER);
        dialogo.add(panelBotones, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }
}