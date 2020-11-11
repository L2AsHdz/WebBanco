package others.validators;

import datos.CRUD;
import datos.usuario.UsuarioDAOImpl;
import model.Usuario;
import others.exceptions.FileInputException;

/**
 * @date 11/11/2020
 * @time 12:55:14
 * @author asael
 */
public class UsuarioValidator extends Validator {

    private static final CRUD<Usuario> usuarioDAO = UsuarioDAOImpl.getUsuarioDAO();

    /**
     * Valida los datos obtenidos del archivo de entrada, si no encuentra ningun
     * error devuelve un objeto Usuario
     * @param codigo
     * @param nombre
     * @param dpi
     * @param direccion
     * @param sexo
     * @param password
     * @param tipoUser
     * @param i
     * @return Usuario
     * @throws FileInputException
     */
    public static Usuario validarUsuario(String codigo, String nombre, String dpi,
            String direccion, String sexo, String password, String tipoUser, int i) throws FileInputException {
        
        Usuario usuario = null;
        String etiqueta = "Etiqueta " + tipoUser + " No. " + (i + 1);
        if (codigo.trim().isEmpty() || nombre.trim().isEmpty() || dpi.trim().isEmpty()
                || direccion.trim().isEmpty() || sexo.trim().isEmpty() || password.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isInt(codigo)) {
            throw new FileInputException(etiqueta + ": El codigo debe contener solo numeros");
        } else if (!isInt(dpi)) {
            throw new FileInputException(etiqueta + ": El dpi debe contener solo numeros");
        } else if (usuarioDAO.exists(codigo)) {
            throw new FileInputException(etiqueta + ": El usuario ya esta registrado");
        } else {
            int tipoU;
            if (tipoUser.equalsIgnoreCase("GERENTE")) {
                tipoU = 1;
            } else if (tipoUser.equalsIgnoreCase("CAJERO")) {
                tipoU = 2;
            } else {
                tipoU = 3;
            }
            usuario = new Usuario(codigo, nombre, direccion, dpi, sexo, tipoU, password);
        }
        return usuario;
    }
}
