/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package datos;

import java.util.List;

/**
 * @param <T>
 * @date 11/11/2020
 * @time 09:53:47
 * @author asael
 */
public interface CRUD<T> {

    List<T> getList();
    void create(T t);
    T getObject(String id);
    void update(T t);
    void delete(String id);
    boolean exists(String id);
}
