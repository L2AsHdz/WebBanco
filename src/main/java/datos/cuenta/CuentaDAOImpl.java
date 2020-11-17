package datos.cuenta;

import datos.Conexion;
import datos.transaccion.TransaccionDAO;
import datos.transaccion.TransaccionDAOImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.cliente.Cliente;
import model.cuenta.Cuenta;
import model.cuenta.CuentaDTO;

/**
 * @date 11/11/2020
 * @time 11:38:25
 * @author asael
 */
public class CuentaDAOImpl implements CuentaDAO {

    private static CuentaDAOImpl cuentaDAO = null;
    private final Connection conexion = Conexion.getConexion();
    private final TransaccionDAO transaccionDAO = TransaccionDAOImpl.getTransaccionDAO();

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

    @Override
    public List<Cuenta> getCuentasPropias(int codCliente) {
        String sql = "SELECT * FROM cuenta WHERE codigoCliente = ?";
        List<Cuenta> cuentas = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCliente);
            try ( ResultSet rs = ps.executeQuery()) {

                cuentas = new ArrayList();
                while (rs.next()) {
                    Cuenta cuenta = new Cuenta(
                            rs.getString("codigo"),
                            new Cliente(String.valueOf(codCliente)),
                            rs.getString("fechaCreacion"),
                            rs.getString("saldo"));
                    cuentas.add(cuenta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return cuentas;
    }

    @Override
    public List<Cuenta> getCuentasWithTransacciones(int codCliente) {
        String sql = "SELECT codigo FROM cuenta WHERE codigoCliente = ?";
        List<Cuenta> cuentas = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCliente);
            try ( ResultSet rs = ps.executeQuery()) {

                cuentas = new ArrayList();
                while (rs.next()) {
                    int codCuenta = rs.getInt("codigo");
                    CuentaDTO cuenta = new CuentaDTO(
                            transaccionDAO.getTransaccionesCuenta(codCuenta),
                            rs.getString("codigo"));
                    cuentas.add(cuenta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return cuentas;
    }

    @Override
    public Cuenta getCuentaConMasDinero(int codCliente, String fechaInicial) {
        String sql = "SELECT * FROM cuenta WHERE codigoCliente = ? ORDER BY saldo desc LIMIT 1";
        Cuenta cuenta = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCliente);
            try ( ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int codCuenta = rs.getInt("codigo");
                    cuenta = new CuentaDTO(
                            transaccionDAO.getTransaccionesInteval(codCuenta, fechaInicial),
                            rs.getString("codigo"));
                    cuenta.setSaldo(rs.getFloat("saldo"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return cuenta;
    }

}
