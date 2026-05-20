package dao;

import conexion.Conexion;
import model.Materia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

    // Crear una nueva materia
    public boolean crear(Materia m) {
        String sql = "INSERT INTO materia(nombre_materia, creditos) VALUES(?, ?)";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, m.getNombreMateria());
            ps.setInt(2, m.getCreditos());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error crear materia: " + ex.getMessage());
            return false;
        }
    }

    // Listar todas las materias
    public List<Materia> listar() {
        List<Materia> lista = new ArrayList<>();
        String sql = "SELECT * FROM materia";

        try (Connection c = Conexion.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Materia(
                        rs.getInt("id_materia"),
                        rs.getString("nombre_materia"),
                        rs.getInt("creditos")
                ));
            }

        } catch (SQLException ex) {
            System.err.println("Error listar materias: " + ex.getMessage());
        }

        return lista;
    }

    // Buscar una materia por ID
    public Materia buscarPorId(int id) {
        String sql = "SELECT * FROM materia WHERE id_materia = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Materia(
                            rs.getInt("id_materia"),
                            rs.getString("nombre_materia"),
                            rs.getInt("creditos")
                    );
                }
            }

        } catch (SQLException ex) {
            System.err.println("Error buscar materia: " + ex.getMessage());
        }

        return null;
    }

    // Actualizar una materia
    public boolean actualizar(Materia m) {
        String sql = "UPDATE materia SET nombre_materia = ?, creditos = ? WHERE id_materia = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, m.getNombreMateria());
            ps.setInt(2, m.getCreditos());
            ps.setInt(3, m.getIdMateria());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error actualizar materia: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar una materia
    public boolean eliminar(int id) {
        String sql = "DELETE FROM materia WHERE id_materia = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminar materia: " + ex.getMessage());
            return false;
        }
    }
}