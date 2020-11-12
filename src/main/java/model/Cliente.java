package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @date 10/11/2020
 * @time 21:36:59
 * @author asael
 */
public class Cliente extends Usuario {

    private LocalDate birth;
    private InputStream dpiPDF;

    public Cliente() {
    }

    public Cliente(String codigo) {
        super(codigo);
    }
    public Cliente(String codigo, String birth, InputStream dpiPDF) {
        super(codigo);
        this.birth = LocalDate.parse(birth);
        this.dpiPDF = dpiPDF;
    }

    public Cliente(String birth, String dpiPDF, String codigo) {
        super(codigo);
        
        try {
            this.dpiPDF = new FileInputStream("/tmp/" + dpiPDF);
        } catch (FileNotFoundException e) {
            e.printStackTrace(System.out);
        }
        
        try {
            this.birth = LocalDate.parse(birth);
        } catch (Exception e) {
            try {
                this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-M-d"));
            } catch (Exception ex) {
                try {
                    this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                } catch (Exception exx) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }

    public Cliente(String birth, String codigo, String nombre, String direccion, String noIdentificacion, String sexo) {
        super(codigo, nombre, direccion, noIdentificacion, sexo);
        this.birth = LocalDate.parse(birth);
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public InputStream getDpiPDF() {
        return dpiPDF;
    }

    public void setDpiPDF(InputStream dpiPDF) {
        this.dpiPDF = dpiPDF;
    }
}
