package view.gui;

import model.InscripcionCurso;
import service.InscripcionCursoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InscripcionPanel extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    public InscripcionPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        
        JLabel titulo = new JLabel("  Gestion de Inscripciones");

        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        titulo.setForeground(new Color(33, 97, 140));

        titulo.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 5, 10)
        );

        add(titulo, BorderLayout.NORTH);

        
        modelo = new DefaultTableModel(
                new String[]{
                        "ID",
                        "ID Estudiante",
                        "ID Grupo",
                        "Nota Final",
                        "Estado"
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

        add(scroll, BorderLayout.CENTER);

        
        JPanel barraBotones = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 15, 10)
        );

        barraBotones.setBackground(Color.WHITE);

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

        barraBotones.add(btnRegistrar);
        barraBotones.add(btnEditar);
        barraBotones.add(btnEliminar);

        add(barraBotones, BorderLayout.SOUTH);

        
        cargarTabla();

        InscripcionCursoService service =
                new InscripcionCursoService();

        

        // Registrar
        btnRegistrar.addActionListener(e ->
                abrirDialogo(null)
        );

        // Editar
        btnEditar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona una inscripcion para editar."
                );

                return;
            }

            int id = (int) modelo.getValueAt(fila, 0);

            InscripcionCurso inscripcion =
                    service.obtenerPorId(id);

            abrirDialogo(inscripcion);
        });

       
        btnEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona una inscripcion para eliminar."
                );

                return;
            }

            int confirmar = JOptionPane.showConfirmDialog(
                    this,
                    "¿Eliminar esta inscripcion?",
                    "Confirmar",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirmar == JOptionPane.YES_OPTION) {

                int id = (int) modelo.getValueAt(fila, 0);

                service.eliminar(id);

                cargarTabla();
            }
        });
    }

    
    private void cargarTabla() {

        modelo.setRowCount(0);

        for (InscripcionCurso i :
                new InscripcionCursoService().obtenerTodas()) {

            modelo.addRow(new Object[]{
                    i.getIdInscripcion(),
                    i.getIdEstudiante(),
                    i.getIdGrupo(),
                    i.getNotaFinal(),
                    i.getEstado()
            });
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

    
    private void abrirDialogo(InscripcionCurso ins) {

        boolean esEdicion = (ins != null);

        JDialog dialogo = new JDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                esEdicion
                        ? "Editar Inscripcion"
                        : "Registrar Inscripcion",
                true
        );

        dialogo.setSize(360, 270);

        dialogo.setLocationRelativeTo(this);

        dialogo.setLayout(new BorderLayout());

        
        JPanel form = new JPanel(new GridBagLayout());

        form.setBackground(Color.WHITE);

        form.setBorder(
                BorderFactory.createEmptyBorder(12, 20, 10, 20)
        );

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        g.fill = GridBagConstraints.HORIZONTAL;

       
        JLabel lbl = new JLabel(
                esEdicion
                        ? "  Editar Inscripcion"
                        : "  Registrar Inscripcion"
        );

        lbl.setFont(new Font("Arial", Font.BOLD, 14));

        lbl.setForeground(new Color(33, 97, 140));

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;

        form.add(lbl, g);

        g.gridwidth = 1;

        // Campos
        JTextField txtIdEstudiante =
                new JTextField(12);

        JTextField txtIdGrupo =
                new JTextField(12);

        JTextField txtNota =
                new JTextField(12);

        JTextField txtEstado =
                new JTextField(12);

        
        if (esEdicion) {

            txtIdEstudiante.setText(
                    String.valueOf(ins.getIdEstudiante())
            );

            txtIdGrupo.setText(
                    String.valueOf(ins.getIdGrupo())
            );

            txtNota.setText(
                    String.valueOf(ins.getNotaFinal())
            );

            txtEstado.setText(ins.getEstado());

        } else {

            txtEstado.setText("Activo");
        }

        
        g.gridy = 1;
        g.gridx = 0;

        form.add(new JLabel("ID Estudiante:"), g);

        g.gridx = 1;

        form.add(txtIdEstudiante, g);

        
        g.gridy = 2;
        g.gridx = 0;

        form.add(new JLabel("ID Grupo:"), g);

        g.gridx = 1;

        form.add(txtIdGrupo, g);

       
        g.gridy = 3;
        g.gridx = 0;

        form.add(new JLabel("Nota Final (0-5):"), g);

        g.gridx = 1;

        form.add(txtNota, g);

        
        g.gridy = 4;
        g.gridx = 0;

        form.add(new JLabel("Estado:"), g);

        g.gridx = 1;

        form.add(txtEstado, g);

        
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

            try {

                int idEstudiante = Integer.parseInt(
                        txtIdEstudiante.getText().trim()
                );

                int idGrupo = Integer.parseInt(
                        txtIdGrupo.getText().trim()
                );

                float nota = Float.parseFloat(
                        txtNota.getText().trim()
                );

                String estado =
                        txtEstado.getText().trim();

                if (nota < 0 || nota > 5) {

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "La nota debe estar entre 0.0 y 5.0."
                    );

                    return;
                }

                if (estado.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "El estado no puede estar vacio."
                    );

                    return;
                }

                InscripcionCursoService service =
                        new InscripcionCursoService();

                // Editar
                if (esEdicion) {

                    ins.setIdEstudiante(idEstudiante);

                    ins.setIdGrupo(idGrupo);

                    ins.setNotaFinal(nota);

                    ins.setEstado(estado);

                    service.actualizar(ins);

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "Inscripcion actualizada correctamente."
                    );

                } else {

                    
                    InscripcionCurso nueva =
                            new InscripcionCurso(
                                    0,
                                    idEstudiante,
                                    idGrupo,
                                    nota,
                                    estado
                            );

                    service.registrar(nueva);

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "Inscripcion registrada correctamente."
                    );
                }

                cargarTabla();

                dialogo.dispose();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        dialogo,
                        "Verifica que ID y Nota sean numeros validos."
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