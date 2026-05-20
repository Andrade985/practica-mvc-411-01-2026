package dao;

import conexion.Conexion;
import model.Docente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAO {

    // Crear un nuevo docente
    public boolean crear(Docente d) {
        String sql = "INSERT INTO docente(nombre, especialidad) VALUES(?, ?)";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getEspecialidad());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error crear docente: " + ex.getMessage());
            return false;
        }
    }

    // Listar todos los docentes
    public List<Docente> listar() {
        List<Docente> lista = new ArrayList<>();
        String sql = "SELECT * FROM docente";

        try (Connection c = Conexion.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Docente(
                        rs.getInt("id_docente"),
                        rs.getString("nombre"),
                        rs.getString("especialidad")
                ));
            }

        } catch (SQLException ex) {
            System.err.println("Error listar docentes: " + ex.getMessage());
        }

        return lista;
    }

    // Buscar docente por ID
    public Docente buscarPorId(int id) {
        String sql = "SELECT * FROM docente WHERE id_docente = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Docente(
                            rs.getInt("id_docente"),
                            rs.getString("nombre"),
                            rs.getString("especialidad")
                    );
                }
            }

        } catch (SQLException ex) {
            System.err.println("Error buscar docente: " + ex.getMessage());
        }

        return null;
    }

    // Actualizar docente
    public boolean actualizar(Docente d) {
        String sql = "UPDATE docente SET nombre = ?, especialidad = ? WHERE id_docente = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, d.getNombre());
            ps.setString(2, d.getEspecialidad());
            ps.setInt(3, d.getIdDocente());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error actualizar docente: " + ex.getMessage());
            return false;
        }
    }

    // Eliminar docente
    public boolean eliminar(int id) {
        String sql = "DELETE FROM docente WHERE id_docente = ?";

        try (Connection c = Conexion.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            System.err.println("Error eliminar docente: " + ex.getMessage());
            return false;
        }
    }
}