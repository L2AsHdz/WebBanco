package datos.usuario;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Usuario;

/**
 * @date 11/11/2020
 * @time 10:00:37
 * @author asael
 */
public class UsuarioDAOImpl implements UsuarioDAO {
    
    private static UsuarioDAOImpl usuarioDAO = null;
    private Connection conexion = Conexion.getConexion();
    
    private UsuarioDAOImpl() {
    }
    
    public static UsuarioDAOImpl getUsuarioDAO(){
        if (usuarioDAO == null) {
            usuarioDAO = new UsuarioDAOImpl();
        }
        
        return usuarioDAO;
    }

    @Override
    public List<Usuario> getList() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(Usuario user) {
        String sql = "INSERT INTO usuario VALUES (?, ?, ?, ?, ?, ?, ?)";

        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, user.getCodigo());
            ps.setString(2, user.getNombre());
            ps.setString(3, user.getDireccion());
            ps.setString(4, user.getNoIdentificacion());
            ps.setString(5, user.getSexo());
            ps.setString(6, user.getEncryptPassword());
            ps.setInt(7, user.getTipoUsuario());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Usuario getObject(String codigo) {
        String sql = "SELECT * FROM usuario WHERE codigo = ?";

        Usuario usuario = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(codigo));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    usuario = new Usuario(
                            rs.getString("codigo"), 
                            rs.getString("nombre"), 
                            rs.getString("direccion"), 
                            rs.getString("noIdentificacion"), 
                            rs.getString("sexo"), 
                            rs.getInt("tipoUsuario"), 
                            rs.getString("password"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return usuario;
    }

    @Override
    public void update(Usuario user) {
        String sql = "UPDATE usuario SET nombre = ?, direccion = ?, noIdentificacion = ?,"
                + "sexo = ? WHERE codigo = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getDireccion());
            ps.setString(3, user.getNoIdentificacion());
            ps.setString(4, user.getSexo());
            ps.setInt(5, user.getCodigo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean exists(String codigo) {
        String sql = "SELECT codigo FROM usuario where codigo = ?";
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
    public String crear(Usuario user) {
        String sql = "INSERT INTO usuario(nombre, direccion, noIdentificacion, sexo, tipoUsuario, password) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        Integer codigoGenerado = 0;

        try ( PreparedStatement ps = conexion.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getNombre());
            ps.setString(2, user.getDireccion());
            ps.setString(3, user.getNoIdentificacion());
            ps.setString(4, user.getSexo());
            ps.setInt(5, user.getTipoUsuario());
            ps.setString(6, user.getEncryptPassword());
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

}
