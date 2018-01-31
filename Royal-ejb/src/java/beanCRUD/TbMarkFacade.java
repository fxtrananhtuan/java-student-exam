/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbMark;
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
public class TbMarkFacade extends AbstractFacade<TbMark> {
    @EJB
    private TbSemesterFacade tbSemesterFacade;
    @EJB
    private TbSubjectFacade tbSubjectFacade;
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbMarkFacade() {
        super(TbMark.class);
    }
    
    //láº¥y List Mark cá»§a student nhap vÃ o
    public List<TbMark> findByStudentID(String studentID) {
//        String sql = "Select * from tbMark where studentID = '" + StudentID + "'";
//        Query q = em.createNativeQuery(sql, TbMark.class);
        Query q = em.createNamedQuery("TbMark.findByStudentID");
        q.setParameter("studentID", studentID);
        List<TbMark> listMark = new ArrayList<TbMark>();
        for (TbMark mark : (List<TbMark>) q.getResultList()) {

            listMark.add(mark);
        }
        return listMark;

    }
    // lay list semester
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
    // lay list subject
    public ArrayList<SelectItem> getListSubject(int semesterID) {
        ArrayList<SelectItem> listSubject = new ArrayList<SelectItem>();
        List<TbSubject> list = this.tbSubjectFacade.findAll();
        for (TbSubject sub : list) {
            if (sub.getSemID().getSemID() == semesterID) {
                SelectItem item = new SelectItem(sub.getSubjectID(), sub.getSubjectName());
                listSubject.add(item);
            }
        }
        return listSubject;
    }

    //kiemtra studentID
    public boolean checkStudentID(String studentID) {
        int total = 0;
        String sql = "Select * from TbStudent where studentID = " + studentID + "";
        Query q = em.createNativeQuery(sql);
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }
    }

    
}
