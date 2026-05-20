package service;

import dao.InscripcionCursoDAO;
import model.InscripcionCurso;

import java.util.List;

public class InscripcionCursoService {

    // Instancia del DAO
    private final InscripcionCursoDAO dao = new InscripcionCursoDAO();

    // Registrar una nueva inscripción con validaciones
    public boolean registrar(InscripcionCurso i) {
        if (i == null) {
            System.out.println("Error: la inscripción no puede ser nula.");
            return false;
        }

        if (i.getIdEstudiante() <= 0) {
            System.out.println("Error: ID de estudiante no válido.");
            return false;
        }

        if (i.getIdGrupo() <= 0) {
            System.out.println("Error: ID de grupo no válido.");
            return false;
        }

        if (i.getNotaFinal() < 0.0f || i.getNotaFinal() > 5.0f) {
            System.out.println("Error: la nota final debe estar entre 0.0 y 5.0.");
            return false;
        }

        if (i.getEstado() == null || i.getEstado().trim().isEmpty()) {
            System.out.println("Error: el estado no puede estar vacío.");
            return false;
        }

        return dao.crear(i);
    }

    // Obtener todas las inscripciones
    public List<InscripcionCurso> obtenerTodas() {
        return dao.listar();
    }

    // Obtener una inscripción por ID
    public InscripcionCurso obtenerPorId(int id) {
        return dao.buscarPorId(id);
    }

    // Actualizar una inscripción
    public boolean actualizar(InscripcionCurso i) {
        if (i == null) {
            System.out.println("Error: la inscripción no puede ser nula.");
            return false;
        }

        if (dao.buscarPorId(i.getIdInscripcion()) == null) {
            System.out.println("Error: inscripción con ID " + i.getIdInscripcion() + " no encontrada.");
            return false;
        }

        return dao.actualizar(i);
    }

    // Eliminar una inscripción
    public boolean eliminar(int id) {
        if (dao.buscarPorId(id) == null) {
            System.out.println("Error: inscripción con ID " + id + " no encontrada.");
            return false;
        }

        return dao.eliminar(id);
    }
}