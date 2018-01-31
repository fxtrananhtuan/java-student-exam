/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbSemester;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MesutOezil
 */
@Stateless
public class TbSemesterFacade extends AbstractFacade<TbSemester> {
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbSemesterFacade() {
        super(TbSemester.class);
    }
    
    //kiem tra ten Semester
    public boolean checkSemName(String semName, int course) {
        int total = 0;
        Query q = em.createNamedQuery("TbSemester.findByCourseSem");
        q.setParameter("semName", semName);
        q.setParameter("courseID", course);
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }
    }
   
    public List<TbSemester> listSemesterByCourse(int courseID){
        String sql="Select * from TbSemester where courseID = "+ courseID +"";
        Query q = em.createNativeQuery(sql, TbSemester.class);
        List<TbSemester> listSemester = q.getResultList();
        return listSemester;
    }

}
