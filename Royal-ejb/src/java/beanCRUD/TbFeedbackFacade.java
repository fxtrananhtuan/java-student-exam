/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbFeedback;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MesutOezil
 */
@Stateless
public class TbFeedbackFacade extends AbstractFacade<TbFeedback> {
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbFeedbackFacade() {
        super(TbFeedback.class);
    }
    
}
