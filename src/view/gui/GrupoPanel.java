package view.gui;

import model.Grupo;
import service.GrupoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GrupoPanel extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    public GrupoPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // ───────────── TITULO ─────────────
        JLabel titulo = new JLabel("  Gestion de Grupos");

        titulo.setFont(new Font("Arial", Font.BOLD, 16));

        titulo.setForeground(new Color(33, 97, 140));

        titulo.setBorder(
                BorderFactory.createEmptyBorder(10, 10, 5, 10)
        );

        add(titulo, BorderLayout.NORTH);

        // ───────────── TABLA ─────────────
        modelo = new DefaultTableModel(
                new String[]{
                        "ID",
                        "ID Materia",
                        "ID Docente",
                        "Aula",
                        "Horario"
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

        // ───────────── BOTONES ─────────────
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

        // ───────────── CARGAR TABLA ─────────────
        cargarTabla();

        GrupoService service = new GrupoService();

        // ───────────── EVENTOS ─────────────

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
                        "Selecciona un grupo para editar."
                );

                return;
            }

            int id = (int) modelo.getValueAt(fila, 0);

            Grupo grupo = service.obtenerPorId(id);

            abrirDialogo(grupo);
        });

        // Eliminar
        btnEliminar.addActionListener(e -> {

            int fila = tabla.getSelectedRow();

            if (fila == -1) {

                JOptionPane.showMessageDialog(
                        this,
                        "Selecciona un grupo para eliminar."
                );

                return;
            }

            int confirmar = JOptionPane.showConfirmDialog(
                    this,
                    "¿Eliminar este grupo?",
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

    // ───────────── CARGAR TABLA ─────────────
    private void cargarTabla() {

        modelo.setRowCount(0);

        for (Grupo g : new GrupoService().obtenerTodos()) {

            modelo.addRow(new Object[]{
                    g.getIdGrupo(),
                    g.getIdMateria(),
                    g.getIdDocente(),
                    g.getAula(),
                    g.getHorario()
            });
        }
    }

    // ───────────── CREAR BOTON ─────────────
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

    // ───────────── ABRIR DIALOGO ─────────────
    private void abrirDialogo(Grupo grp) {

        boolean esEdicion = (grp != null);

        JDialog dialogo = new JDialog(
                (Frame) SwingUtilities.getWindowAncestor(this),
                esEdicion
                        ? "Editar Grupo"
                        : "Registrar Grupo",
                true
        );

        dialogo.setSize(360, 270);

        dialogo.setLocationRelativeTo(this);

        dialogo.setLayout(new BorderLayout());

        // ───────────── FORMULARIO ─────────────
        JPanel form = new JPanel(new GridBagLayout());

        form.setBackground(Color.WHITE);

        form.setBorder(
                BorderFactory.createEmptyBorder(12, 20, 10, 20)
        );

        GridBagConstraints g = new GridBagConstraints();

        g.insets = new Insets(5, 5, 5, 5);

        g.fill = GridBagConstraints.HORIZONTAL;

        // Titulo
        JLabel lbl = new JLabel(
                esEdicion
                        ? "  Editar Grupo"
                        : "  Registrar Grupo"
        );

        lbl.setFont(new Font("Arial", Font.BOLD, 14));

        lbl.setForeground(new Color(33, 97, 140));

        g.gridx = 0;
        g.gridy = 0;
        g.gridwidth = 2;

        form.add(lbl, g);

        g.gridwidth = 1;

        // Campos
        JTextField txtIdMateria = new JTextField(12);
        JTextField txtIdDocente = new JTextField(12);
        JTextField txtAula = new JTextField(12);
        JTextField txtHorario = new JTextField(12);

        // Cargar datos
        if (esEdicion) {

            txtIdMateria.setText(
                    String.valueOf(grp.getIdMateria())
            );

            txtIdDocente.setText(
                    String.valueOf(grp.getIdDocente())
            );

            txtAula.setText(grp.getAula());

            txtHorario.setText(grp.getHorario());
        }

        // ID Materia
        g.gridy = 1;
        g.gridx = 0;

        form.add(new JLabel("ID Materia:"), g);

        g.gridx = 1;

        form.add(txtIdMateria, g);

        // ID Docente
        g.gridy = 2;
        g.gridx = 0;

        form.add(new JLabel("ID Docente:"), g);

        g.gridx = 1;

        form.add(txtIdDocente, g);

        // Aula
        g.gridy = 3;
        g.gridx = 0;

        form.add(new JLabel("Aula:"), g);

        g.gridx = 1;

        form.add(txtAula, g);

        // Horario
        g.gridy = 4;
        g.gridx = 0;

        form.add(new JLabel("Horario:"), g);

        g.gridx = 1;

        form.add(txtHorario, g);

        // ───────────── BOTONES ─────────────
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

        // Guardar
        btnGuardar.addActionListener(e -> {

            try {

                int idMateria = Integer.parseInt(
                        txtIdMateria.getText().trim()
                );

                int idDocente = Integer.parseInt(
                        txtIdDocente.getText().trim()
                );

                String aula = txtAula.getText().trim();

                String horario = txtHorario.getText().trim();

                if (
                        aula.isEmpty()
                                || horario.isEmpty()
                ) {

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "Aula y horario son obligatorios."
                    );

                    return;
                }

                GrupoService service = new GrupoService();

                // Editar
                if (esEdicion) {

                    grp.setIdMateria(idMateria);

                    grp.setIdDocente(idDocente);

                    grp.setAula(aula);

                    grp.setHorario(horario);

                    service.actualizar(grp);

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "Grupo actualizado correctamente."
                    );

                } else {

                    // Registrar
                    Grupo nuevo = new Grupo(
                            0,
                            idMateria,
                            idDocente,
                            aula,
                            horario
                    );

                    service.registrar(nuevo);

                    JOptionPane.showMessageDialog(
                            dialogo,
                            "Grupo registrado correctamente."
                    );
                }

                cargarTabla();

                dialogo.dispose();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        dialogo,
                        "ID Materia e ID Docente deben ser numeros enteros."
                );
            }
        });

        // Cancelar
        btnCancelar.addActionListener(e ->
                dialogo.dispose()
        );

        dialogo.add(form, BorderLayout.CENTER);

        dialogo.add(botones, BorderLayout.SOUTH);

        dialogo.setVisible(true);
    }
}