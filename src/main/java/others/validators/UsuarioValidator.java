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

    public static void validarUsuario(String codigo, String nombre, String dpi,
            String direccion, String sexo, String password, String tipoUser, int i) throws FileInputException {
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
        }
    }
}
