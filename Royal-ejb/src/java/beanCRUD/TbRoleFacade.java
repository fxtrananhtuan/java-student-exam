/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbRole;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MesutOezil
 */
@Stateless
public class TbRoleFacade extends AbstractFacade<TbRole> {
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbRoleFacade() {
        super(TbRole.class);
    }
    
    
    
}
