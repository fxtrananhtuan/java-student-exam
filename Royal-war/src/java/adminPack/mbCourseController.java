/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbCourseFacade;
import beanInfo.TbCourse;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author MesutOezil
 */
public class mbCourseController implements Serializable{
    @EJB
    private TbCourseFacade tbCourseFacade;
    private TbCourse tbCourse;
    private TbCourse tbCourse1;
    private String coursename = "";
    private int error;

    @PostConstruct
    public void init() {
        this.tbCourse = new TbCourse();
        this.tbCourse1 = new TbCourse();
        this.error = 1;
    }

    /** Creates a new instance of mbCourseController */
    public mbCourseController() {
    }

    public TbCourse getTbCourse1() {
        return tbCourse1;
    }

    public void setTbCourse1(TbCourse tbCourse1) {
        this.tbCourse1 = tbCourse1;
    }

    public TbCourse getTbCourse() {
        return tbCourse;
    }

    public void setTbCourse(TbCourse tbCourse) {
        this.tbCourse = tbCourse;
    }

    public List<TbCourse> listCourse() {
        return this.tbCourseFacade.findAll();
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    //insert new course
    public void addNew() {
        String name = this.tbCourse1.getCourseName();
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            if (this.tbCourseFacade.checkCourseName(name) == false) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Insert", "Fail"));
                this.error = 1;
            } else {
                this.tbCourseFacade.create(tbCourse1);
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert", "successful"));
                this.error = 0;
                this.tbCourse1.setCourseName("");
            }
        } catch (Exception ex) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }

    }

    //Update Course
    public void updateCourse() {
        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
//        int id = Integer.parseInt(session.getAttribute("idCourse").toString());
//
//        //System.out.println("UpdateID:"+ id);
//        this.tbCourse.setCourseID(id);
//
        String name = this.tbCourse.getCourseName();
//        //System.out.println("UpdateCourse:"+coursename);

        try {
            if (name == null) {
                fc.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "CourseName Not Null"));
                this.error = 1;
            } else {
                this.tbCourseFacade.edit(tbCourse);
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "update", "successful"));
                this.error = 0;
            }
        } catch (EJBException e) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
        }


    }

//    //láº¥y session lÃªn cho edit and delete
//    public void dualenSession() {
//        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
//        session.setAttribute("idCourse", this.tbCourse.getCourseID());
//        session.setAttribute("courseName", this.tbCourse.getCourseName());
//        session.setAttribute("tbCourse", this.tbCourse);
//
//    }
    // Delete Course
    public void deleteCourse() {
        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);

        try {
            int id = this.tbCourse.getCourseID();
//            int id = Integer.parseInt(session.getAttribute("idCourse").toString());
//            this.tbCourse = (TbCourse) session.getAttribute("tbCourse");
            if (this.tbCourseFacade.checkDelete(id) == false) {
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This Course Can't Delete", "Please Delete All Student in Batch of Course first"));
                this.error = 1;
//                session.removeAttribute("tbCourse");
//                session.removeAttribute("idCourse");
//                session.removeAttribute("courseName");
            } else if (this.tbCourseFacade.checkDeleteCourse(id) == false) {
                this.error = 2;
            } else {
                this.tbCourse.setCourseID(id);
                this.tbCourseFacade.remove(tbCourse);
                this.error = 0;
//                session.removeAttribute("tbCourse");
//                session.removeAttribute("idCourse");
//                session.removeAttribute("courseName");
                fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "delete", "successful"));
            }
        } catch (EJBException ex) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }
    }

    //xoa khi há»i
    public void askdeleteCourse() {
        FacesContext fc = FacesContext.getCurrentInstance();
//        HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
        try {
//            this.tbCourse = (TbCourse) session.getAttribute("tbCourse");
            int id = this.tbCourse.getCourseID();
            this.tbCourse.setCourseID(id);
            this.tbCourseFacade.remove(tbCourse);
            this.error = 0;
//            session.removeAttribute("tbCourse");
//            session.removeAttribute("idCourse");
//            session.removeAttribute("courseName");
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "delete", "successful"));

        } catch (EJBException ex) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }
    }

}
