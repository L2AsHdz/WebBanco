package model;

/**
 * @date 10/11/2020
 * @time 21:34:40
 * @author asael
 */
public class Empleado extends Usuario {

    private String codUsuario;
    private Turno turno;

    public Empleado() {
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }
}
