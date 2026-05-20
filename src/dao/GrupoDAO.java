package dao;

import conexion.Conexion;
import model.Grupo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDAO {

    // Crear un nuevo grupo
    public boolean crear(Grupo g) {
        String sql = "INSERT INTO grupo(id_materia, id_docente, aula, horario) VALUES(?, ?, ?, ?)";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, g.getIdMateria());
            ps.setInt(2, g.getIdDocente());
            ps.setString(3, g.getAula());
            ps.setString(4, g.getHorario());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error crear grupo: " + ex.getMessage());
            return false;
        }
    }

    // Listar todos los grupos
    public List<Grupo> listar() {
        List<Grupo> lista = new ArrayList<>();
        String sql = "SELECT * FROM grupo";

        try (Connection c = Conexion.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Grupo(
                        rs.getInt("id_grupo"),
                        rs.getInt("id_materia"),
                        rs.getInt("id_docente"),
                        rs.getString("aula"),
                        rs.getString("horario")
                ));
            }

        } catch (SQLException ex) {
            System.err.println("Error listar grupos: " + ex.getMessage());
        }

        return lista;
    }

    // Buscar un grupo por ID
    public Grupo buscarPorId(int id) {
        String sql = "SELECT * FROM grupo WHERE id_grupo = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Grupo(
                            rs.getInt("id_grupo"),
                            rs.getInt("id_materia"),
                            rs.getInt("id_docente"),
                            rs.getString("aula"),
                            rs.getString("horario")
                    );
                }
            }

        } catch (SQLException ex) {
            System.err.println("Error buscar grupo: " + ex.getMessage());
        }

        return null;
    }

    // Actualizar un grupo
    public boolean actualizar(Grupo g) {
        String sql = "UPDATE grupo SET id_materia = ?, id_docente = ?, aula = ?, horario = ? WHERE id_grupo = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, g.getIdMateria());
            ps.setInt(2, g.getIdDocente());
            ps.setString(3, g.getAula());
            ps.setString(4, g.getHorario());
            ps.setInt(5, g.getIdGrupo());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error actualizar grupo: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar un grupo
    public boolean eliminar(int id) {
        String sql = "DELETE FROM grupo WHERE id_grupo = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminar grupo: " + ex.getMessage());
            return false;
        }
    }
}