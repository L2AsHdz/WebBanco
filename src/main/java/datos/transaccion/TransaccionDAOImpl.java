package datos.transaccion;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.cuenta.Cuenta;
import model.Empleado;
import model.Transaccion;

/**
 * @date 11/11/2020
 * @time 11:47:55
 * @author asael
 */
public class TransaccionDAOImpl implements TransaccionDAO {
    
    private static TransaccionDAOImpl transaccionDAO = null;
    private final Connection conexion = Conexion.getConexion();
    
    private TransaccionDAOImpl() {
    }
    
    public static TransaccionDAOImpl getTransaccionDAO() {
        if (transaccionDAO == null) {
            transaccionDAO = new TransaccionDAOImpl();
        }
        
        return transaccionDAO;
    }

    @Override
    public List<Transaccion> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Transaccion transc) {
        String sql = "INSERT INTO transaccion(codCuenta, tipo, fecha, hora, monto, "
                + "codCajero, saldoCuenta) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, transc.getCuenta().getCodigo());
            ps.setString(2, transc.getTipo());
            ps.setString(3, transc.getFecha().toString());
            ps.setString(4, transc.getHora().toString());
            ps.setFloat(5, transc.getMonto());
            ps.setInt(6, transc.getCajero().getCodigo());
            ps.setFloat(7, transc.getSaldoCuenta());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void create(String codigo, Transaccion transc) {
        String sql = "INSERT INTO transaccion VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
            ps.setInt(2, transc.getCuenta().getCodigo());
            ps.setString(3, transc.getTipo());
            ps.setString(4, transc.getFecha().toString());
            ps.setString(5, transc.getHora().toString());
            ps.setFloat(6, transc.getMonto());
            ps.setInt(7, transc.getCajero().getCodigo());
            ps.setFloat(8, transc.getSaldoCuenta());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Transaccion getObject(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Transaccion t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM transaccion where codigo = ?";
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
    public List<Transaccion> getRetirosTurno(int codCajero) {
        String sql = "SELECT * FROM transaccion WHERE codCajero = ? AND fecha = CAST(NOW() AS DATE) AND tipo = 'DEBITO'";
        List<Transaccion> retiros = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCajero);
            try ( ResultSet rs = ps.executeQuery()) {
            retiros = new ArrayList();
                while (rs.next()) {
                    Transaccion transaccion = new Transaccion(
                            rs.getString("codigo"),
                            new Cuenta(rs.getString("codCuenta")),
                            "DEBITO",
                            rs.getString("fecha"),
                            rs.getString("hora"),
                            rs.getString("monto"),
                            new Empleado(rs.getString("codCajero")),
                            rs.getString("saldoCuenta"));
                    retiros.add(transaccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return retiros;
    }

    @Override
    public List<Transaccion> getDepositosTurno(int codCajero) {
        String sql = "SELECT * FROM transaccion WHERE codCajero = ? AND fecha = CAST(NOW() AS DATE) AND tipo = 'CREDITO'";
        List<Transaccion> depositos = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCajero);
            try ( ResultSet rs = ps.executeQuery()) {
            depositos = new ArrayList();
                while (rs.next()) {
                    Transaccion transaccion = new Transaccion(
                            rs.getString("codigo"),
                            new Cuenta(rs.getString("codCuenta")),
                            "CREDITO",
                            rs.getString("fecha"),
                            rs.getString("hora"),
                            rs.getString("monto"),
                            new Empleado(rs.getString("codCajero")),
                            rs.getString("saldoCuenta"));
                    depositos.add(transaccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return depositos;
    }

    @Override
    public float getTotalRetiroTurno(int codCajero) {
        String sql = "SELECT SUM(monto) total FROM transaccion WHERE codCajero = ? AND fecha = CAST(NOW() AS DATE) AND tipo = 'DEBITO'";
        float total = 0;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCajero);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    total = rs.getFloat("total");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return total;
    }

    @Override
    public float getTotalDepositoTurno(int codCajero) {
        String sql = "SELECT SUM(monto) total FROM transaccion WHERE codCajero = ? AND fecha = CAST(NOW() AS DATE) AND tipo = 'CREDITO'";
        float total = 0;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCajero);
            try ( ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    total = rs.getFloat("total");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return total;
    }

    @Override
    public List<Transaccion> getTransaccionesCuenta(int codCuenta) {
        String sql = "SELECT t.* FROM transaccion t INNER JOIN cuenta c ON t.codCuenta=c.codigo "
                + "WHERE c.codigo = ? AND (t.fecha BETWEEN DATE_SUB(NOW(), interval 1 YEAR) "
                + "AND NOW()) ORDER BY t.monto DESC LIMIT 15";
        List<Transaccion> transacciones = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codCuenta);
            try ( ResultSet rs = ps.executeQuery()) {
            transacciones = new ArrayList();
                while (rs.next()) {
                    Transaccion transaccion = new Transaccion(
                            rs.getString("codigo"),
                            new Cuenta(rs.getString("codCuenta")),
                            "DEBITO",
                            rs.getString("fecha"),
                            rs.getString("hora"),
                            rs.getString("monto"),
                            new Empleado(rs.getString("codCajero")),
                            rs.getString("saldoCuenta"));
                    transacciones.add(transaccion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return transacciones;
    }

}
