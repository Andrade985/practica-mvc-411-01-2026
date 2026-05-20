package dao;

import conexion.Conexion;
import model.InscripcionCurso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionCursoDAO {

    // Crear una nueva inscripción
    public boolean crear(InscripcionCurso i) {
        String sql = "INSERT INTO inscripcion_curso(id_estudiante, id_grupo, nota_final, estado) VALUES(?, ?, ?, ?)";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, i.getIdEstudiante());
            ps.setInt(2, i.getIdGrupo());
            ps.setFloat(3, i.getNotaFinal());
            ps.setString(4, i.getEstado());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error crear inscripción: " + ex.getMessage());
            return false;
        }
    }

    // Listar todas las inscripciones
    public List<InscripcionCurso> listar() {
        List<InscripcionCurso> lista = new ArrayList<>();
        String sql = "SELECT * FROM inscripcion_curso";

        try (Connection c = Conexion.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new InscripcionCurso(
                        rs.getInt("id_inscripcion"),
                        rs.getInt("id_estudiante"),
                        rs.getInt("id_grupo"),
                        rs.getFloat("nota_final"),
                        rs.getString("estado")
                ));
            }

        } catch (SQLException ex) {
            System.err.println("Error listar inscripciones: " + ex.getMessage());
        }

        return lista;
    }

    // Buscar una inscripción por ID
    public InscripcionCurso buscarPorId(int id) {
        String sql = "SELECT * FROM inscripcion_curso WHERE id_inscripcion = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new InscripcionCurso(
                            rs.getInt("id_inscripcion"),
                            rs.getInt("id_estudiante"),
                            rs.getInt("id_grupo"),
                            rs.getFloat("nota_final"),
                            rs.getString("estado")
                    );
                }
            }

        } catch (SQLException ex) {
            System.err.println("Error buscar inscripción: " + ex.getMessage());
        }

        return null;
    }

    // Actualizar una inscripción
    public boolean actualizar(InscripcionCurso i) {
        String sql = "UPDATE inscripcion_curso " +
                     "SET id_estudiante = ?, id_grupo = ?, nota_final = ?, estado = ? " +
                     "WHERE id_inscripcion = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, i.getIdEstudiante());
            ps.setInt(2, i.getIdGrupo());
            ps.setFloat(3, i.getNotaFinal());
            ps.setString(4, i.getEstado());
            ps.setInt(5, i.getIdInscripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error actualizar inscripción: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar una inscripción
    public boolean eliminar(int id) {
        String sql = "DELETE FROM inscripcion_curso WHERE id_inscripcion = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminar inscripción: " + ex.getMessage());
            return false;
        }
    }
}