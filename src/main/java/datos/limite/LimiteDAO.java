package datos.limite;

import datos.CRUD;
import model.Limite;

/**
 * @date 13/11/2020
 * @time 11:51:55
 * @author asael
 */
public interface LimiteDAO extends CRUD<Limite> {
    
    boolean wasSetted();
}
