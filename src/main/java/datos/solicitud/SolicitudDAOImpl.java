package datos.solicitud;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        String sql = "INSERT INTO solicitudAsociacion(codCliente, codCuenta) VALUES (?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, solicitud.getCliente().getCodigo());
            ps.setInt(2, solicitud.getCuenta().getCodigo());
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

}
