package datos.solicitud;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.cliente.Cliente;
import model.Cuenta;
import model.Solicitud;

/**
 * @date 14/11/2020
 * @time 15:25:46
 * @author asael
 */
public class SolicitudDAOImpl implements SolicitudDAO {

    private static SolicitudDAOImpl solicitudDAO = null;
    private final Connection conexion = Conexion.getConexion();

    private SolicitudDAOImpl() {
    }

    public static SolicitudDAOImpl getSolicitudDAO() {
        if (solicitudDAO == null) {
            solicitudDAO = new SolicitudDAOImpl();
        }

        return solicitudDAO;
    }

    @Override
    public boolean isPendiente(int codCliente, String codCuenta) {
        String sql = "SELECT estado FROM solicitudAsociacion WHERE codCliente = ? AND codCuenta = ? AND estado = 0";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCliente);
            ps.setString(2, codCuenta);
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
    public boolean isAvailable(int codCliente, String codCuenta) {
        String sql = "SELECT COUNT(*) solicitudes FROM solicitudAsociacion WHERE codCliente = ? AND codCuenta = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCliente);
            ps.setString(2, codCuenta);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    int solicitudes = rs.getInt("solicitudes");
                    if (solicitudes < 3) {
                        flag = true;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return flag;
    }

    @Override
    public List<Solicitud> getList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(Solicitud solicitud) {
        String sql = "INSERT INTO solicitudAsociacion(codCliente, codCuenta, fecha) VALUES (?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, solicitud.getCliente().getCodigo());
            ps.setInt(2, solicitud.getCuenta().getCodigo());
            ps.setString(3, solicitud.getFecha().toString());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Solicitud getObject(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Solicitud t) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean exists(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Solicitud> getSolicitudesPendientes(String codCliente) {
        String sql = "SELECT s.id, s.codCuenta, u.codigo, u.nombre, u.noIdentificacion, "
                + "s.estado, s.fecha FROM solicitudAsociacion s INNER JOIN usuario u ON "
                + "s.codCliente=u.codigo INNER JOIN cuenta c ON s.codCuenta=c.codigo WHERE "
                + "c.codigoCliente = ? AND s.estado = 0";
        List<Solicitud> solicitudes = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codCliente));
            try ( ResultSet rs = ps.executeQuery()) {
            solicitudes = new ArrayList();
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(rs.getInt("codigo"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setNoIdentificacion(rs.getString("noIdentificacion"));
                    
                    Cuenta cuenta = new Cuenta(rs.getString("codCuenta"));
                    
                    Solicitud solicitud = new Solicitud();
                    solicitud.setId(rs.getInt("id"));
                    solicitud.setFecha(LocalDate.parse(rs.getString("fecha")));
                    solicitud.setEstadoN(rs.getInt("estado"));
                    solicitud.setCliente(cliente);
                    solicitud.setCuenta(cuenta);
                    solicitudes.add(solicitud);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return solicitudes;
    }

    @Override
    public void updateEstado(String id, int estado) {
        String sql = "UPDATE solicitudAsociacion SET estado = ? WHERE id = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, estado);
            ps.setInt(2, Integer.parseInt(id));
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
