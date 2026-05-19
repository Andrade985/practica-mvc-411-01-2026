package view;

import model.InscripcionCurso;

public class InscripcionCursoView {
     public void mostrarInscripcion(InscripcionCurso i) {
        System.out.println("=== Inscripción Curso ===");
        System.out.println("ID Inscripción : " + i.getIdInscripcion());
        System.out.println("ID Estudiante  : " + i.getIdEstudiante());
        System.out.println("ID Grupo       : " + i.getIdGrupo());
        System.out.println("Nota Final     : " + i.getNotaFinal());
        System.out.println("Estado         : " + i.getEstado());
    }
}

