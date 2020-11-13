package model;

/**
 * @date 10/11/2020
 * @time 21:34:40
 * @author asael
 */
public class Empleado extends Usuario {

    private Turno turno;

    public Empleado() {
    }

    public Empleado(Turno turno, String codigo, String nombre, String direccion, String noIdentificacion, String sexo) {
        super(codigo, nombre, direccion, noIdentificacion, sexo);
        this.turno = turno;
    }

    public Empleado(String codigo) {
        super(codigo);
    }

    public Empleado(Turno turno, String codigo) {
        super(codigo);
        this.turno = turno;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
