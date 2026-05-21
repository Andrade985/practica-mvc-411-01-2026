package view.gui;

import model.Materia;
import service.MateriaService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MateriaPanel extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtBuscar;

    public MateriaPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        
        JLabel titulo = new JLabel("  Gestion de Materias");

        titulo.setFont(new Font("Arial", Font.BOLD, 16));
        titulo.setForeground(new Color(33, 97, 140));

        titulo.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 5, 10)
        );

        add(titulo, BorderLayout.NORTH);

        
        JPanel barraTop = new JPanel(
                new FlowLayout(FlowLayout.LEFT)
        );

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
                new String[]{
                        "ID",
                        "Nombre Materia",
                        "Creditos"
                },
                0
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modelo);

        tabla.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        tabla.getTableHeader().setBackground(
                new Color(33, 97, 140)
        );

        tabla.getTableHeader().setForeground(Color.WHITE);

        tabla.setRowHeight(22);

        tabla.setGridColor(new Color(220, 220, 220));

        JScrollPane scroll = new JScrollPane(tabla);

        JPanel centro = new JPanel(new BorderLayout());

        centro.setBackground(Color.WHITE);

        centro.add(barraTop, BorderLayout.NORTH);
        centro.add(scroll, BorderLayout.CENTER);

        add(centro, BorderLayout.CENTER);

        
        JPanel barraBot = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 10)
        );

        barraBot.setBackground(Color.WHITE);

        JButton btnRegistrar = crearBoton(
                "Registrar",
                new Color(39, 174, 96)
        );

        JButton btnEditar = crearBoton(
                "Editar",
                new Color(52, 152, 219)
        );

        JButton btnEliminar = crearBoton(
                "Eliminar",
                new Color(231, 76, 60)
        );

        barraBot.add(btnRegistrar);
        barraBot.add(btnEditar);
        barraBot.add(btnEliminar);

        add(barraBot, BorderLayout.SOUTH);

        
        cargarTabla();

        MateriaService service = new MateriaService();

        

        
        btnRegistrar.addActionListener(e ->
                abrirDialogo(null)
        );

        
        btnEditar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona una materia para editar."
                );

                return;
            }

            int id = (int) modelo.getValueAt(fila, 0);

            Materia materia = service.obtenerPorId(id);

            abrirDialogo(materia);
        });

        
        btnEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona una materia para eliminar."
                );

                return;
            }

            int confirmar = JOptionPane.showConfirmDialog(
                    this,
                    "¿Eliminar esta materia?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmar == JOptionPane.YES_OPTION) {

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

        for (Materia m : new MateriaService().obtenerTodas()) {

            modelo.addRow(new Object[]{
                    m.getIdMateria(),
                    m.getNombreMateria(),
                    m.getCreditos()
            });
        }
    }

    
    private void buscar(String texto) {

        modelo.setRowCount(0);

        for (Materia m : new MateriaService().obtenerTodas()) {

            if (
                    m.getNombreMateria()
                            .toLowerCase()
                            .contains(texto.toLowerCase())
            ) {

                modelo.addRow(new Object[]{
                        m.getIdMateria(),
                        m.getNombreMateria(),
                        m.getCreditos()
                });
            }
        }
    }

    
    private JButton crearBoton(String texto, Color color) {

        JButton boton = new JButton(texto);

        boton.setBackground(color);
        boton.setForeground(Color.WHITE);

        boton.setPreferredSize(
                new Dimension(110, 32)
        );

        boton.setFocusPainted(false);

        return boton;
    }

    
    private void abrirDialogo(Materia mat) {

        boolean esEdicion = (mat != null);

        JDialog dialogo = new JDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                esEdicion
                        ? "Editar Materia"
                        : "Registrar Materia",
                true
        );

        dialogo.setSize(360, 230);

        dialogo.setLocationRelativeTo(this);

        dialogo.setLayout(new BorderLayout());

        
        JPanel form = new JPanel(new GridBagLayout());

        form.setBackground(Color.WHITE);

        form.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 10, 20)
        );

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(6, 5, 6, 5);

        g.fill = GridBagConstraints.HORIZONTAL;

        
        JLabel lbl = new JLabel(
                esEdicion
                        ? "  Editar Materia"
                        : "  Registrar Materia"
        );

        lbl.setFont(new Font("Arial", Font.BOLD, 14));

        lbl.setForeground(new Color(33, 97, 140));

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;

        form.add(lbl, g);

        g.gridwidth = 1;

        
        JTextField txtNombre = new JTextField(15);
        JTextField txtCreditos = new JTextField(15);

        
        if (esEdicion) {

            txtNombre.setText(mat.getNombreMateria());

            txtCreditos.setText(
                    String.valueOf(mat.getCreditos())
            );
        }

        
        g.gridy = 1;
        g.gridx = 0;

        form.add(new JLabel("Nombre Materia:"), g);

        g.gridx = 1;

        form.add(txtNombre, g);

        
        g.gridy = 2;
        g.gridx = 0;

        form.add(new JLabel("Creditos:"), g);

        g.gridx = 1;

        form.add(txtCreditos, g);

        
        JPanel botones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 10, 8)
        );

        botones.setBackground(Color.WHITE);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.setBackground(new Color(39, 174, 96));
        btnGuardar.setForeground(Color.WHITE);

        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setForeground(Color.WHITE);

        botones.add(btnGuardar);
        botones.add(btnCancelar);

        
        btnGuardar.addActionListener(e -> {

            String nombre = txtNombre.getText().trim();
            String creditosTexto = txtCreditos.getText().trim();

            if (
                    nombre.isEmpty()
                            || creditosTexto.isEmpty()
            ) {

                JOptionPane.showMessageDialog(
                        dialogo,
                        "Todos los campos son obligatorios."
                );

                return;
            }

            try {

                int creditos = Integer.parseInt(creditosTexto);

                if (creditos <= 0) {

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "Los creditos deben ser positivos."
                    );

                    return;
                }

                MateriaService service = new MateriaService();

                if (esEdicion) {

                    mat.setNombreMateria(nombre);
                    mat.setCreditos(creditos);

                    service.actualizar(mat);

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "Materia actualizada correctamente."
                    );

                } else {

                    Materia nueva = new Materia(
                            0,
                            nombre,
                            creditos
                    );

                    service.registrar(nueva);

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "Materia registrada correctamente."
                    );
                }

                cargarTabla();

                dialogo.dispose();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        dialogo,
                        "Los creditos deben ser un numero entero."
                );
            }
        });

        
        btnCancelar.addActionListener(e ->
                dialogo.dispose()
        );

        dialogo.add(form, BorderLayout.CENTER);
        dialogo.add(botones, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }
}