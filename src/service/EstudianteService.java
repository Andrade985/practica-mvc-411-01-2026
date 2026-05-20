package service;

import dao.EstudianteDAO;
import model.Estudiante;

import java.util.List;

public class EstudianteService {

    // Instancia del DAO
    private final EstudianteDAO dao = new EstudianteDAO();

    // Registrar un nuevo estudiante con validaciones
    public boolean registrar(Estudiante e) {
        if (e == null) {
            System.out.println("Error: el estudiante no puede ser nulo.");
            return false;
        }

        if (e.getNombre() == null || e.getNombre().trim().isEmpty()) {
            System.out.println("Error: el nombre no puede estar vacío.");
            return false;
        }

        if (e.getApellido() == null || e.getApellido().trim().isEmpty()) {
            System.out.println("Error: el apellido no puede estar vacío.");
            return false;
        }

        if (e.getEmail() == null || !e.getEmail().contains("@")) {
            System.out.println("Error: el email no es válido.");
            return false;
        }

        return dao.crear(e);
    }

    // Obtener todos los estudiantes
    public List<Estudiante> obtenerTodos() {
        return dao.listar();
    }

    // Obtener un estudiante por ID
    public Estudiante obtenerPorId(int id) {
        return dao.buscarPorId(id);
    }

    // Actualizar un estudiante
    public boolean actualizar(Estudiante e) {
        if (e == null) {
            System.out.println("Error: el estudiante no puede ser nulo.");
            return false;
        }

        if (dao.buscarPorId(e.getIdEstudiante()) == null) {
            System.out.println("Error: estudiante con ID " + e.getIdEstudiante() + " no encontrado.");
            return false;
        }

        return dao.actualizar(e);
    }

    // Eliminar un estudiante
    public boolean eliminar(int id) {
        if (dao.buscarPorId(id) == null) {
            System.out.println("Error: estudiante con ID " + id + " no encontrado.");
            return false;
        }

        return dao.eliminar(id);
    }
}