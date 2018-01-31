/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbFAQ;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MesutOezil
 */
@Stateless
public class TbFAQFacade extends AbstractFacade<TbFAQ> {
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbFAQFacade() {
        super(TbFAQ.class);
    }
    //kiem tra Question
    public boolean checkQuestion(String question) {
        int total = 0;
        Query q = em.createNamedQuery("TbFAQ.findByQuestion");
        q.setParameter("question", question);        
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }
    }

}
