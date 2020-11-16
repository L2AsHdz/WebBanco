package datos.limite;

import datos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Limite;

/**
 * @date 13/11/2020
 * @time 11:55:59
 * @author asael
 */
public class LimiteDAOImpl implements LimiteDAO {

    private static LimiteDAOImpl limiteDAO = null;
    private final Connection conexion = Conexion.getConexion();

    private LimiteDAOImpl() {
    }

    public static LimiteDAOImpl getLimiteDAO() {
        if (limiteDAO == null) {
            limiteDAO = new LimiteDAOImpl();
        }

        return limiteDAO;
    }

    @Override
    public List<Limite> getList() {
        String sql = "SELECT * FROM limite";
        List<Limite> limites = null;

        try ( PreparedStatement declaracion = conexion.prepareStatement(sql);  ResultSet rs = declaracion.executeQuery()) {
            limites = new ArrayList();

            while (rs.next()) {
                Limite limite = new Limite(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getFloat("valor"));
                limites.add(limite);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return limites;
    }

    @Override
    public void create(Limite t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Limite getObject(String id) {
        String sql = "SELECT valor FROM limite WHERE id = ?";

        Limite limite = null;
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    limite = new Limite();
                    limite.setValor(rs.getFloat("valor"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return limite;
    }

    @Override
    public void update(Limite limite) {
        String sql = "UPDATE limite SET valor = ? WHERE id = ?";
        try ( PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setFloat(1, limite.getValor());
            ps.setInt(2, limite.getId());
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
    public boolean exists(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean wasSetted() {
        String sql = "SELECT valor FROM limite";
        boolean flag = true;
        try ( PreparedStatement ps = conexion.prepareStatement(sql);  
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                float valor = rs.getFloat("valor");
                if (valor == 0) {
                    flag = false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return flag;
    }

}
