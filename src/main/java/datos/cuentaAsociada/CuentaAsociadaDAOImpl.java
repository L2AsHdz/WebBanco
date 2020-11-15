package datos.cuentaAsociada;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Cuenta;
import model.CuentaAsociada;

/**
 * @date 14/11/2020
 * @time 08:42:53
 * @author asael
 */
public class CuentaAsociadaDAOImpl implements CuentaAsociadaDAO {

    private static CuentaAsociadaDAOImpl cuentaAsoDAO = null;
    private final Connection conexion = Conexion.getConexion();

    private CuentaAsociadaDAOImpl() {
    }

    public static CuentaAsociadaDAOImpl getCuentaAsoDAO() {
        if (cuentaAsoDAO == null) {
            cuentaAsoDAO = new CuentaAsociadaDAOImpl();
        }

        return cuentaAsoDAO;
    }

    @Override
    public boolean isAsociada(int codCliente, String codCuenta) {
        String sql = "SELECT * FROM cuentaAsociada WHERE codCliente = ? AND codCuenta = ?";
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
    public List<CuentaAsociada> getList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void create(CuentaAsociada cuenta) {
        String sql = "INSERT INTO cuentaAsociada VALUES (?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cuenta.getCliente().getCodigo());
            ps.setInt(2, cuenta.getCuenta().getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public CuentaAsociada getObject(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(CuentaAsociada t) {
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
    public List<CuentaAsociada> getCuentasTerceros(int codCliente) {
        String sql = "SELECT c.*, u.nombre FROM cuentaAsociada c INNER JOIN usuario u "
                + "ON c.codCliente=u.codigo WHERE c.codCliente = ?";
        List<CuentaAsociada> cuentas = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCliente);
            try ( ResultSet rs = ps.executeQuery()) {

                cuentas = new ArrayList();
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(rs.getInt("codCliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    CuentaAsociada cuenta = new CuentaAsociada(
                            cliente,
                            new Cuenta(rs.getString("codCuenta")));
                    cuentas.add(cuenta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return cuentas;
    }

}
