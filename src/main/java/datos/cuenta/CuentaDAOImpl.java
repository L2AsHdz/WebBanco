package datos.cuenta;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
        String sql = "INSERT INTO cuenta(codigoCliente, fechaCreacion, saldo) VALUES (?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cuenta.getCliente().getCodigo());
            ps.setString(2, cuenta.getFechaCreacion().toString());
            ps.setFloat(3, cuenta.getSaldo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
    
    @Override
    public void create(int codigo, Cuenta cuenta) {
        String sql = "INSERT INTO cuenta VALUES (?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            ps.setInt(2, cuenta.getCliente().getCodigo());
            ps.setString(3, cuenta.getFechaCreacion().toString());
            ps.setFloat(4, cuenta.getSaldo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Cuenta getObject(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
