package model;

import java.io.Serializable;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * @date 10/11/2020
 * @time 21:19:31
 * @author asael
 */
public class Usuario implements Serializable {
    
    private int codigo;
    private String nombre;
    private String direccion;
    private String noIdentificacion;
    private String sexo;
    private int tipoUsuario;
    private String password;

    public Usuario() {
    }

    public Usuario(String codigo, String nombre, String direccion, String noIdentificacion, String sexo) {
        this.codigo = Integer.parseInt(codigo);
        this.nombre = nombre;
        this.direccion = direccion;
        this.noIdentificacion = noIdentificacion;
        this.sexo = sexo;
    }

    public Usuario(String nombre, String direccion, String noIdentificacion, String sexo, int tipoUsuario, String password) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.noIdentificacion = noIdentificacion;
        this.sexo = sexo;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
    }

    public Usuario(String codigo, String nombre, String direccion, String noIdentificacion, String sexo, int tipoUsuario, String password) {
        this.codigo = Integer.parseInt(codigo);
        this.nombre = nombre;
        this.direccion = direccion;
        this.noIdentificacion = noIdentificacion;
        this.sexo = sexo;
        this.tipoUsuario = tipoUsuario;
        this.password = password;
    }

    public Usuario(String codigo) {
        this.codigo = Integer.parseInt(codigo);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEncryptPassword() {
        return DigestUtils.sha1Hex(password);
    }
}
