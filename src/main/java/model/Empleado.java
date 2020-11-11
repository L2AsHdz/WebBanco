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

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
