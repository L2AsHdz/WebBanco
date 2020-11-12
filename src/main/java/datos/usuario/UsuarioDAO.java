package datos.usuario;

import datos.CRUD;
import model.Usuario;

/**
 * @date 11/11/2020
 * @time 10:00:14
 * @author asael
 */
public interface UsuarioDAO extends CRUD<Usuario> {

    String crear(Usuario user);
}
