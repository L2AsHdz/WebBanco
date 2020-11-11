package datos.transaccion;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
    public void create(int codigo, Transaccion transc) {
        String sql = "INSERT INTO transaccion VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
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

}
