package model;

/**
 * @date 16/11/2020
 * @time 15:40:22
 * @author asael
 */
public class CajeroDTO extends Empleado {

    private int totalTransacciones;

    public CajeroDTO(int totalTransacciones, Turno turno, String codigo, String nombre, String direccion, String noIdentificacion, String sexo) {
        super(turno, codigo, nombre, direccion, noIdentificacion, sexo);
        this.totalTransacciones = totalTransacciones;
    }

    public int getTotalTransacciones() {
        return totalTransacciones;
    }

    public void setTotalTransacciones(int totalTransacciones) {
        this.totalTransacciones = totalTransacciones;
    }
}
