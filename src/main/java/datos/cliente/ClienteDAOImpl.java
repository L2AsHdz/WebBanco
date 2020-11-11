package datos.cliente;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Cliente;

/**
 * @date 11/11/2020
 * @time 11:32:21
 * @author asael
 */
public class ClienteDAOImpl implements ClienteDAO {
    
    private static ClienteDAOImpl clienteDAO = null;
    private final Connection conexion = Conexion.getConexion();
    
    private ClienteDAOImpl() {
    }
    
    public static ClienteDAOImpl getClienteDAO() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteDAOImpl();
        }
        
        return clienteDAO;
    }

    @Override
    public List<Cliente> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Cliente cliente) {
        String sql = "INSERT INTO cliente VALUES (?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cliente.getCodigo());
            ps.setString(2, cliente.getBirth().toString());
            ps.setBinaryStream(3, cliente.getDpiPDF());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Cliente getObject(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Cliente t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigoUsuario) {
        String sql = "SELECT codigoUsuario FROM cliente where codigoUsuario = ?";
        boolean flag = false;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigoUsuario);
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
