/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbAccount;
import beanInfo.TbAssignment;
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
public class TbAssignmentFacade extends AbstractFacade<TbAssignment> {

    @EJB
    private TbSubjectFacade tbSubjectFacade;
    @EJB
    private TbSemesterFacade tbSemesterFacade;
    @EJB
    private TbCourseFacade tbCourseFacade;
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbAssignmentFacade() {
        super(TbAssignment.class);
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

    public ArrayList<SelectItem> getListSubject(int semID) {
        ArrayList<SelectItem> listSubject = new ArrayList<SelectItem>();
        List<TbSubject> list = this.tbSubjectFacade.findAll();
        for (TbSubject subject : list) {
            if (subject.getSemID().getSemID() == semID) {
                SelectItem item = new SelectItem(subject.getSubjectID(), subject.getSubjectName());
                listSubject.add(item);
            }
        }
        return listSubject;
    }
    
    public List<TbAssignment> getListAssignmentByUser(TbAccount userName){
        Query q=this.em.createNamedQuery("TbAssignment.findByUser").setParameter("userName", userName);
        return q.getResultList();
    }
}
