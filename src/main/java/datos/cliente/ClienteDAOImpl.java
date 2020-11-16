package datos.cliente;

import datos.CRUD;
import datos.Conexion;
import datos.limite.LimiteDAOImpl;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Limite;

/**
 * @date 11/11/2020
 * @time 11:32:21
 * @author asael
 */
public class ClienteDAOImpl implements ClienteDAO {
    
    private static ClienteDAOImpl clienteDAO = null;
    private final Connection conexion = Conexion.getConexion();
    private final CRUD<Limite> limiteDAO = LimiteDAOImpl.getLimiteDAO();
    
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
        String sql = "SELECT u.*, c.* FROM usuario u INNER JOIN cliente c ON u.codigo=c.codigoUsuario";
        List<Cliente> clientes = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  
                ResultSet rs = declaracion.executeQuery()) {
            clientes = new ArrayList();

            while (rs.next()) {
                Cliente gerente = new Cliente(
                        rs.getString("birth"),
                        rs.getBinaryStream("pdfDPI"),
                        rs.getString("codigo"), 
                        rs.getString("nombre"), 
                        rs.getString("direccion"), 
                        rs.getString("noIdentificacion"), 
                        rs.getString("sexo"));
                clientes.add(gerente);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return clientes;
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
    public Cliente getObject(String codigo) {
        String sql = "SELECT u.*, c.* FROM usuario u INNER JOIN cliente c ON u.codigo=c.codigoUsuario WHERE u.codigo = ?";

        Cliente cliente = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    cliente = new Cliente(
                            rs.getString("birth"),
                            rs.getBinaryStream("pdfDPI"),
                            rs.getString("codigo"), 
                            rs.getString("nombre"), 
                            rs.getString("direccion"), 
                            rs.getString("noIdentificacion"), 
                            rs.getString("sexo"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return cliente;
    }

    @Override
    public void update(Cliente c) {
        String sql = "UPDATE cliente SET birth = ?, pdfDPI = ? WHERE codigoUsuario = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, c.getBirth().toString());
            if (c.getDpiPDF().available() == 0) {
                ps.setBinaryStream(2, getObject(String.valueOf(c.getCodigo())).getDpiPDF());
            } else {
                ps.setBinaryStream(2, c.getDpiPDF());
                System.out.println("no entra al if");
            }
            ps.setInt(3, c.getCodigo());
            ps.executeUpdate();
        } catch (SQLException | IOException ex) {
            ex.printStackTrace(System.out);
        }
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

    @Override
    public byte[] getPDFdpi(String codigo) {
        String sql = "SELECT pdfDPI FROM cliente WHERE codigoUsuario = ?";
        InputStream dpiPDF;
        byte[] datosPDF = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, codigo);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    dpiPDF = rs.getBinaryStream("pdfDPI");

                    int tamanoInput = dpiPDF.available();
                    datosPDF = new byte[tamanoInput];
                    dpiPDF.read(datosPDF, 0, tamanoInput);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return datosPDF;
    }

    @Override
    public List<Cliente> getClientesWithTrGreaterThanLimite() {
        String sql = "SELECT u.*, c.* from usuario u inner join cliente c on u.codigo=c.codigoUsuario"
                + " inner join cuenta ct on c.codigoUsuario=ct.codigoCliente inner join transaccion t on "
                + "ct.codigo=t.codCuenta where t.monto > ? group by c.codigoUsuario;";
        List<Cliente> clientes = null;

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setFloat(1, limiteDAO.getObject("1").getValor());
            try(ResultSet rs = ps.executeQuery()) {
            clientes = new ArrayList();

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("birth"),
                        rs.getBinaryStream("pdfDPI"),
                        rs.getString("codigo"), 
                        rs.getString("nombre"), 
                        rs.getString("direccion"), 
                        rs.getString("noIdentificacion"), 
                        rs.getString("sexo"));
                clientes.add(cliente);
            }}
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return clientes;
    }

}
