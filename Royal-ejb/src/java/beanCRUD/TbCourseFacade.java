/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbCourse;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MesutOezil
 */
@Stateless
public class TbCourseFacade extends AbstractFacade<TbCourse> {
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbCourseFacade() {
        super(TbCourse.class);
    }
    
    //edit course
    public int editCourse(int courseID, String courseName) {
        String sql = "Update TbCourse set CourseName = '" + courseName + "' where courseID = " + courseID;
        Query q = em.createNativeQuery(sql);
        int result = q.executeUpdate();
        return result;
    }

    //delete course
    public int deleteCourse(int courseID) {
        String sql = "Delete from TbCourse where courseID = " + courseID;
        Query q = em.createNativeQuery(sql);
        int result = q.executeUpdate();
        return result;
    }

    //kiem tra ten course trong Course
    public boolean checkCourseName(String courseName) {
        int total = 0;
        Query q = em.createNamedQuery("TbCourse.findByCourseName");
        q.setParameter("courseName", courseName);
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }

    }

    public List<TbCourse> findByCourseName(String courseName) {
        String sql = "Select * from tbCourse where courseName like '%" + courseName + "%'";
        Query q = em.createNativeQuery(sql, TbCourse.class);
        List<TbCourse> listCourse = new ArrayList<TbCourse>();
        for (TbCourse course : (List<TbCourse>) q.getResultList()) {
            listCourse.add(course);
        }
        return listCourse;

    }

    //kiem tra Xoa course
    public boolean checkDelete(int courseID) {
        int total = 0;
        String sql = "Select s.* from tbBatch b, tbStudent s where b.courseID = " + courseID + " AND s.batchID = b.batchID";
        Query q = em.createNativeQuery(sql);
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }

    }
    
    //cáº£nh bÃ¡o Xoa course
    public boolean checkDeleteCourse(int courseID) {
        int total = 0;
        String sql = "Select * from tbSemester se where se.courseID = " + courseID;
        Query q = em.createNativeQuery(sql);
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }

    }

    //láº¥y tÃªn course thay the cho course id trong Batch
    public ArrayList<SelectItem> listCourse() {
        ArrayList<SelectItem> temp = new ArrayList<SelectItem>();
        List<TbCourse> listCourse = this.findAll();
        for (TbCourse c : listCourse) {
            SelectItem item = new SelectItem(c.getCourseID(), c.getCourseName());
            temp.add(item);
        }
        return temp;
    }

    
}
