import controller.DocenteController;
import controller.EstudianteController;
import controller.GrupoController;
import controller.InscripcionCursoController;
import controller.MateriaController;
import model.Docente;
import model.Estudiante;
import model.Grupo;
import model.InscripcionCurso;
import model.Materia;
import view.DocenteView;
import view.EstudianteView;
import view.GrupoView;
import view.InscripcionCursoView;
import view.MateriaView;

public class Main {
     public static void main(String[] args) {

        // --- Estudiante ---
        Estudiante est = new Estudiante(1, "Carlos", "López", "c.lopez@mail.com");
        EstudianteView evista = new EstudianteView();
        EstudianteController ec = new EstudianteController(est, evista);
        ec.actualizarVista();

        // --- Materia ---
        Materia mat = new Materia(1, "Programación Orientada a Objetos", 4);
        MateriaView mvista = new MateriaView();
        MateriaController mc = new MateriaController(mat, mvista);
        mc.actualizarVista();

        // --- Docente ---
        Docente doc = new Docente(1, "Ana Martínez", "Ingeniería de Software");
        DocenteView dvista = new DocenteView();
        DocenteController dc = new DocenteController(doc, dvista);
        dc.actualizarVista();

        // --- Grupo ---
        Grupo grp = new Grupo(1, 1, 1, "Aula 301", "Lunes 8:00-10:00");
        GrupoView gvista = new GrupoView();
        GrupoController gc = new GrupoController(grp, gvista);
        gc.actualizarVista();

        // --- Inscripcion Curso ---
        InscripcionCurso ins = new InscripcionCurso(1, 1, 1, 4.5f, "Activo");
        InscripcionCursoView ivista = new InscripcionCursoView();
        InscripcionCursoController ic =
            new InscripcionCursoController(ins, ivista);
        ic.actualizarVista();
    }
} 

