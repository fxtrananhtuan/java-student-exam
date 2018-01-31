/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbCourse;
import beanInfo.TbSemester;
import beanInfo.TbSubject;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
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
public class TbSubjectFacade extends AbstractFacade<TbSubject> {
    @EJB
    private TbCourseFacade tbCourseFacade;
    @EJB
    private TbSemesterFacade tbSemesterFacade;
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbSubjectFacade() {
        super(TbSubject.class);
    }
    
    //kiem tra ten Subject
    public boolean checkSubName(String subName) {
        int total = 0;
        Query q = em.createNamedQuery("TbSubject.findBySubjectName");
        q.setParameter("subjectName", subName);
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }
}
    
     public ArrayList<SelectItem> getListCourse() {
        ArrayList<SelectItem> listCourse = new ArrayList<SelectItem>();
        List<TbCourse> list = this.tbCourseFacade.findAll();
        for (TbCourse course : list) {
            SelectItem item = new SelectItem(course.getCourseID(), course.getCourseName());
            listCourse.add(item);
        }
        return listCourse;
    }

    public ArrayList<SelectItem> getListSemester(int courseID) {
        ArrayList<SelectItem> listSemeter = new ArrayList<SelectItem>();
        List<TbSemester> list = this.tbSemesterFacade.findAll();
        for (TbSemester sem : list) {
            if (sem.getCourseID().getCourseID() == courseID) {
                SelectItem item = new SelectItem(sem.getSemID(), sem.getSemName());
                listSemeter.add(item);
            }
        }
        return listSemeter;
    }

}
