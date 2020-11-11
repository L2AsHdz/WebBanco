package model;

import java.io.InputStream;
import java.time.LocalDate;

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
