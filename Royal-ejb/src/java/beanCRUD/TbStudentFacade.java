/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbStudent;
import java.util.ArrayList;
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
public class TbStudentFacade extends AbstractFacade<TbStudent> {

    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbStudentFacade() {
        super(TbStudent.class);
    }

    public List<TbStudent> findByStudentID(String studentID) {
        String sql = "Select * from TbStudent where StudentID=" + studentID + "";
        Query q = em.createNativeQuery(sql, TbStudent.class);
        List<TbStudent> listStudent = new ArrayList<TbStudent>();
        for (TbStudent r : (List<TbStudent>) q.getResultList()) {
            listStudent.add(r);
        }
        return listStudent;
    }

    public boolean checkDelete(String studentid) {
        int total = 0;
        String sql = "Select * from TbStudent where StudentID = " + studentid + "";
        Query q = em.createNativeQuery(sql);
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean checkStudentID(String studentID)
    {
        int total=0;
        Query q=em.createNamedQuery("TbStudent.findByStudentID");
        q.setParameter("studentID", studentID);
        total=q.getResultList().size();
        if(total>0)
            return false;
        else
            return true;
    }
    //check login student
    public TbStudent getStudent(String username, String password) {
        Query q = this.em.createNamedQuery("TbStudent.findByStudentIDPassword");
        q.setParameter("studentID", username);
        q.setParameter("password", password);

        try {
            return (TbStudent) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    public String mau(){
        return "string";
    }
    //lay student id de forgot pass
    public TbStudent getStudentID(String username) {
        Query q = this.em.createNamedQuery("TbStudent.findByStudentID");
        q.setParameter("studentID", username);

        try {
            return (TbStudent) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
