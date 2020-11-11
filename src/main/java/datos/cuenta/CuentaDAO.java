/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos.cuenta;

import datos.CRUD;
import model.Cuenta;

/**
 * @date 11/11/2020
 * @time 11:37:46
 * @author asael
 */
public interface CuentaDAO extends CRUD<Cuenta> {

    void create(int codigo, Cuenta c);
}
