package service;

import dao.DocenteDAO;
import model.Docente;

import java.util.List;

public class DocenteService {

    // Instancia del DAO
    private final DocenteDAO dao = new DocenteDAO();

    // Registrar un nuevo docente con validaciones
    public boolean registrar(Docente d) {
        if (d == null) {
            System.out.println("Error: el docente no puede ser nulo.");
            return false;
        }

        if (d.getNombre() == null || d.getNombre().trim().isEmpty()) {
            System.out.println("Error: el nombre del docente no puede estar vacío.");
            return false;
        }

        if (d.getEspecialidad() == null || d.getEspecialidad().trim().isEmpty()) {
            System.out.println("Error: la especialidad no puede estar vacía.");
            return false;
        }

        return dao.crear(d);
    }

    // Obtener todos los docentes
    public List<Docente> obtenerTodos() {
        return dao.listar();
    }

    // Obtener un docente por ID
    public Docente obtenerPorId(int id) {
        return dao.buscarPorId(id);
    }

    // Actualizar un docente
    public boolean actualizar(Docente d) {
        if (d == null) {
            System.out.println("Error: el docente no puede ser nulo.");
            return false;
        }

        if (dao.buscarPorId(d.getIdDocente()) == null) {
            System.out.println("Error: docente con ID " + d.getIdDocente() + " no encontrado.");
            return false;
        }

        return dao.actualizar(d);
    }

    // Eliminar un docente
    public boolean eliminar(int id) {
        if (dao.buscarPorId(id) == null) {
            System.out.println("Error: docente con ID " + id + " no encontrado.");
            return false;
        }

        return dao.eliminar(id);
    }
}