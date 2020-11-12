package datos.empleado;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;
import model.Turno;

/**
 * @date 11/11/2020
 * @time 09:56:19
 * @author asael
 */
public class EmpleadoDAOImpl implements EmpleadoDAO {

    private static EmpleadoDAOImpl empleadoDAO = null;
    private Connection conexion = Conexion.getConexion();

    private EmpleadoDAOImpl() {
    }

    public static EmpleadoDAOImpl getEmpleadoDAO() {
        if (empleadoDAO == null) {
            empleadoDAO = new EmpleadoDAOImpl();
        }
        return empleadoDAO;
    }

    @Override
    public List<Empleado> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Empleado empl) {
        String sql = "INSERT INTO empleado VALUES (?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, empl.getCodigo());
            ps.setInt(2, empl.getTurno().getId());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Empleado getObject(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Empleado t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigoUsuario FROM empleado where codigoUsuario = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    flag = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return flag;
    }

    @Override
    public List<Empleado> getListGerentes() {
        String sql = "SELECT u.*, e.idTurno, t.nombre turno FROM usuario u INNER JOIN "
                + "empleado e ON u.codigo=e.codigoUsuario INNER JOIN turno t ON "
                + "e.idTurno=t.id WHERE u.tipoUsuario=1";
        List<Empleado> gerentes = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  
                ResultSet rs = declaracion.executeQuery()) {
            gerentes = new ArrayList();

            while (rs.next()) {
                Empleado gerente = new Empleado();
                gerente.setCodigo(rs.getInt("codigo"));
                gerente.setNombre(rs.getString("nombre"));
                gerente.setDireccion(rs.getString("direccion"));
                gerente.setNoIdentificacion(rs.getString("noIdentificacion"));
                gerente.setSexo(rs.getString("sexo"));
                gerente.setTipoUsuario(rs.getInt("tipoUsuario"));
                gerente.setTurno(new Turno(rs.getString("turno")));
                gerentes.add(gerente);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return gerentes;
    }

}
