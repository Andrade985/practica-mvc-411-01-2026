package view.gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel panelCentral;

    public MainFrame() {

        setTitle("Sistema de Gestion Academica - UNIAJC");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // =========================
        // PANEL CENTRAL
        // =========================
        panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);

        JLabel bienvenida = new JLabel(
                "Bienvenido al Sistema de Gestion Academica",
                SwingConstants.CENTER);

        bienvenida.setFont(new Font("Arial", Font.BOLD, 24));
        bienvenida.setForeground(new Color(33, 97, 140));

        panelCentral.add(bienvenida, BorderLayout.CENTER);

        add(panelCentral);

        // =========================
        // MENU BAR
        // =========================
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(33, 97, 140));

        // MENU ARCHIVO
        JMenu menuArchivo = new JMenu("Archivo");

        JMenuItem salir = new JMenuItem("Salir");
        salir.addActionListener(e -> System.exit(0));

        menuArchivo.add(salir);

        // MENU ESTUDIANTE
        JMenu menuEstudiante = new JMenu("Estudiante");

        JMenuItem itemEstudiante = new JMenuItem("Gestion de Estudiantes");

        itemEstudiante.addActionListener(e -> {
            cambiarPanel(new EstudiantePanel());
        });

        menuEstudiante.add(itemEstudiante);

        // MENU DOCENTE
        JMenu menuDocente = new JMenu("Docente");

        JMenuItem itemDocente = new JMenuItem("Gestion de Docentes");

        itemDocente.addActionListener(e -> {
            cambiarPanel(new DocentePanel());
        });

        menuDocente.add(itemDocente);

        // MENU MATERIA
        JMenu menuMateria = new JMenu("Materia");

        JMenuItem itemMateria = new JMenuItem("Gestion de Materias");

        itemMateria.addActionListener(e -> {
            cambiarPanel(new MateriaPanel());
        });

        menuMateria.add(itemMateria);

        // MENU GRUPO
        JMenu menuGrupo = new JMenu("Grupo");

        JMenuItem itemGrupo = new JMenuItem("Gestion de Grupos");

        itemGrupo.addActionListener(e -> {
            cambiarPanel(new GrupoPanel());
        });

        menuGrupo.add(itemGrupo);

        // MENU INSCRIPCION
        JMenu menuInscripcion = new JMenu("Inscripcion");

        JMenuItem itemInscripcion = new JMenuItem("Gestion de Inscripciones");

        itemInscripcion.addActionListener(e -> {
            cambiarPanel(new InscripcionPanel());
        });

        menuInscripcion.add(itemInscripcion);

        // AGREGAR MENUS
        menuBar.add(menuArchivo);
        menuBar.add(menuEstudiante);
        menuBar.add(menuDocente);
        menuBar.add(menuMateria);
        menuBar.add(menuGrupo);
        menuBar.add(menuInscripcion);

        // COLOCAR MENU
        setJMenuBar(menuBar);

        setVisible(true);
    }

    // =========================
    // CAMBIAR PANEL
    // =========================
    private void cambiarPanel(JPanel nuevoPanel) {

        panelCentral.removeAll();

        panelCentral.add(nuevoPanel, BorderLayout.CENTER);

        panelCentral.revalidate();
        panelCentral.repaint();
    }
}