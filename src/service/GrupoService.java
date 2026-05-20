package service;

import dao.GrupoDAO;
import model.Grupo;

import java.util.List;

public class GrupoService {

    // Instancia del DAO
    private final GrupoDAO dao = new GrupoDAO();

    // Registrar un nuevo grupo con validaciones
    public boolean registrar(Grupo g) {
        if (g == null) {
            System.out.println("Error: el grupo no puede ser nulo.");
            return false;
        }

        if (g.getIdMateria() <= 0) {
            System.out.println("Error: ID de materia no válido.");
            return false;
        }

        if (g.getIdDocente() <= 0) {
            System.out.println("Error: ID de docente no válido.");
            return false;
        }

        if (g.getAula() == null || g.getAula().trim().isEmpty()) {
            System.out.println("Error: el aula no puede estar vacía.");
            return false;
        }

        if (g.getHorario() == null || g.getHorario().trim().isEmpty()) {
            System.out.println("Error: el horario no puede estar vacío.");
            return false;
        }

        return dao.crear(g);
    }

    // Obtener todos los grupos
    public List<Grupo> obtenerTodos() {
        return dao.listar();
    }

    // Obtener un grupo por ID
    public Grupo obtenerPorId(int id) {
        return dao.buscarPorId(id);
    }

    // Actualizar un grupo
    public boolean actualizar(Grupo g) {
        if (g == null) {
            System.out.println("Error: el grupo no puede ser nulo.");
            return false;
        }

        if (dao.buscarPorId(g.getIdGrupo()) == null) {
            System.out.println("Error: grupo con ID " + g.getIdGrupo() + " no encontrado.");
            return false;
        }

        return dao.actualizar(g);
    }

    // Eliminar un grupo
    public boolean eliminar(int id) {
        if (dao.buscarPorId(id) == null) {
            System.out.println("Error: grupo con ID " + id + " no encontrado.");
            return false;
        }

        return dao.eliminar(id);
    }
}