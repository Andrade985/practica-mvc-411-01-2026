package service;

import dao.MateriaDAO;
import model.Materia;

import java.util.List;

public class MateriaService {

    // Instancia del DAO
    private final MateriaDAO dao = new MateriaDAO();

    // Registrar una nueva materia con validaciones
    public boolean registrar(Materia m) {
        if (m == null) {
            System.out.println("Error: la materia no puede ser nula.");
            return false;
        }

        if (m.getNombreMateria() == null || m.getNombreMateria().trim().isEmpty()) {
            System.out.println("Error: el nombre de la materia no puede estar vacío.");
            return false;
        }

        if (m.getCreditos() <= 0) {
            System.out.println("Error: los créditos deben ser un número positivo.");
            return false;
        }

        return dao.crear(m);
    }

    // Obtener todas las materias
    public List<Materia> obtenerTodas() {
        return dao.listar();
    }

    // Obtener una materia por ID
    public Materia obtenerPorId(int id) {
        return dao.buscarPorId(id);
    }

    // Actualizar una materia
    public boolean actualizar(Materia m) {
        if (m == null) {
            System.out.println("Error: la materia no puede ser nula.");
            return false;
        }

        if (dao.buscarPorId(m.getIdMateria()) == null) {
            System.out.println("Error: materia con ID " + m.getIdMateria() + " no encontrada.");
            return false;
        }

        return dao.actualizar(m);
    }

    // Eliminar una materia
    public boolean eliminar(int id) {
        if (dao.buscarPorId(id) == null) {
            System.out.println("Error: materia con ID " + id + " no encontrada.");
            return false;
        }

        return dao.eliminar(id);
    }
}