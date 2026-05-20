package dao;
import conexion.Conexion;
import model.Estudiante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {

    public boolean crear(Estudiante e) {
        String sql = "INSERT INTO estudiante(nombre, apellido, email) VALUES(?, ?, ?)";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error crear estudiante: " + ex.getMessage());
            return false;
        }
    }

    public List<Estudiante> listar() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiante";
        try (Connection c = Conexion.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(
                    rs.getInt("id_estudiante"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException ex) {
            System.err.println("Error listar estudiantes: " + ex.getMessage());
        }
        return lista;
    }

    public Estudiante buscarPorId(int id) {
        String sql = "SELECT * FROM estudiante WHERE id_estudiante = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                    rs.getInt("id_estudiante"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("email")
                );
            }
        } catch (SQLException ex) {
            System.err.println("Error buscar estudiante: " + ex.getMessage());
        }
        return null;
    }

    public boolean actualizar(Estudiante e) {
        String sql = "UPDATE estudiante SET nombre=?, apellido=?, email=? WHERE id_estudiante=?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getEmail());
            ps.setInt(4, e.getIdEstudiante());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error actualizar estudiante: " + ex.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM estudiante WHERE id_estudiante = ?";
        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.err.println("Error eliminar estudiante: " + ex.getMessage());
            return false;
        }
    }
}