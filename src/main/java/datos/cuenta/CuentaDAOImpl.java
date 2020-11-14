package datos.cuenta;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;
import model.Cuenta;

/**
 * @date 11/11/2020
 * @time 11:38:25
 * @author asael
 */
public class CuentaDAOImpl implements CuentaDAO {

    private static CuentaDAOImpl cuentaDAO = null;
    private final Connection conexion = Conexion.getConexion();

    private CuentaDAOImpl() {
    }

    public static CuentaDAOImpl getCuentaDAO() {
        if (cuentaDAO == null) {
            cuentaDAO = new CuentaDAOImpl();
        }

        return cuentaDAO;
    }

    @Override
    public List<Cuenta> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Cuenta cuenta) {
        String sql = "INSERT INTO cuenta VALUES (?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cuenta.getCodigo());
            ps.setInt(2, cuenta.getCliente().getCodigo());
            ps.setString(3, cuenta.getFechaCreacion().toString());
            ps.setFloat(4, cuenta.getSaldo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public String crear(Cuenta cuenta) {
        String sql = "INSERT INTO cuenta(codigoCliente, fechaCreacion, saldo) VALUES (?, ?, ?)";
        Integer codigoGenerado = 0;

        try ( PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cuenta.getCliente().getCodigo());
            ps.setString(2, cuenta.getFechaCreacion().toString());
            ps.setFloat(3, cuenta.getSaldo());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                codigoGenerado = rs.getInt(1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return codigoGenerado.toString();
    }

    @Override
    public Cuenta getObject(String codigo) {
        String sql = "SELECT c.*, u.nombre, u.noIdentificacion FROM cuenta c INNER JOIN "
                + "usuario u ON c.codigoCliente=u.codigo WHERE c.codigo = ?";

        Cuenta cuenta = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setCodigo(rs.getInt("codigoCliente"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setNoIdentificacion(rs.getString("noIdentificacion"));
                    cuenta = new Cuenta(
                            rs.getString("codigo"),
                            cliente,
                            rs.getString("fechaCreacion"),
                            rs.getString("saldo"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return cuenta;
    }

    @Override
    public void update(Cuenta t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM cuenta where codigo = ?";
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
    public void updateSaldo(String codigo, float monto) {
        String sql = "UPDATE cuenta SET saldo = (saldo + ?) WHERE codigo = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setFloat(1, monto);
            ps.setInt(2, Integer.parseInt(codigo));
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
