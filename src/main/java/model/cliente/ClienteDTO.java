package model.cliente;

import java.io.InputStream;

/**
 * @date 16/11/2020
 * @time 12:34:21
 * @author asael
 */
public class ClienteDTO extends Cliente {
    
    private float dineroT;

    public ClienteDTO() {
    }

    public ClienteDTO(float dineroT, String birth, InputStream dpiPDF, String codigo, String nombre, String direccion, String noIdentificacion, String sexo) {
        super(birth, dpiPDF, codigo, nombre, direccion, noIdentificacion, sexo);
        this.dineroT = dineroT;
    }

    public float getDineroT() {
        return dineroT;
    }

    public void setDineroT(float dineroT) {
        this.dineroT = dineroT;
    }
}
