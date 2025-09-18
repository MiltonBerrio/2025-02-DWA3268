package tallerjdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAO {
    public void insertar(Estudiante e) {
        String sql = "INSERT INTO estudiantes(nombre, apellido, correo, edad, estado_civil) VALUES(?,?,?,?,?)";
        try (
            Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getCorreo());
            ps.setInt(4, e.getEdad());
            ps.setString(5, e.getEstadoCivil().name()); 
            ps.executeUpdate();
            System.out.println("Estudiante insertado correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al insertar: " + ex.getMessage());
        }
    }

    public void actualizar(String correo, Estudiante e) {
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setInt(3, e.getEdad());
            ps.setString(4, e.getEstadoCivil().name());
            ps.setString(5, correo);
            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Estudiante actualizado");
            else
                System.out.println("No se encontr estudiante con ese correo");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
        }
    }

    public void eliminar(String correo) {
        String sql = "DELETE FROM estudiantes WHERE correo=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            int filas = ps.executeUpdate();
            if (filas > 0)
                System.out.println("Estudiante eliminado");
            else
                System.out.println("No se encontro estudiante con ese correo");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
        }
    }

    public List<Estudiante> listarTodos() {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        EstadoCivil.valueOf(rs.getString("estado_civil").toUpperCase()) // normaliza a mayúsculas
                ));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }

    public Estudiante buscarPorCorreo(String correo) {
        String sql = "SELECT * FROM estudiantes WHERE correo=?";
        try (Connection con = Conexion.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Estudiante(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getInt("edad"),
                        EstadoCivil.valueOf(rs.getString("estado_civil").toUpperCase())
                );
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar: " + ex.getMessage());
        }
        return null;
    }
}
