package others.validators;

import model.Cliente;
import others.exceptions.FileInputException;

/**
 * @date 11/11/2020
 * @time 14:56:36
 * @author asael
 */
public class ClienteValidator extends Validator {
    
    public static Cliente validarCliente(String codigo, String birth, String dpiFileName, String tipoUser, int i) throws FileInputException {
        
        Cliente cliente = null;
        String etiqueta = "Etiqueta " + tipoUser + " No. " + (i + 1);
        if (birth.trim().isEmpty() || dpiFileName.trim().isEmpty()) {
            throw new FileInputException(etiqueta + ": Hay uno o mas valores vacios");
        } else if (!isFecha(birth)) {
            throw new FileInputException(etiqueta + ": La fecha no tiene un formato correcto");
        } else if (!exists(dpiFileName)) {
            throw new FileInputException(etiqueta + ": No se encontro el archivo del dpi: " + dpiFileName);
        } else {
            cliente = new Cliente(birth, dpiFileName, codigo);
        }
        return cliente;
    }
}
