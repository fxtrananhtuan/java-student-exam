/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbCourseFacade;
import beanCRUD.TbSemesterFacade;
import beanCRUD.TbSubjectFacade;
import beanInfo.TbCourse;
import beanInfo.TbSemester;
import beanInfo.TbSubject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author MesutOezil
 */
public class mbSubjectController implements Serializable{
    @EJB
    private TbCourseFacade tbCourseFacade;
    @EJB
    private TbSemesterFacade tbSemesterFacade;
    @EJB
    private TbSubjectFacade tbSubjectFacade;

    private ArrayList<SelectItem> listCourse;
    
    private ArrayList<SelectItem> listSemester;
  
    private TbSubject tbSubject;
    private TbSubject tbSubject1;
    private int error;
    private int semesterID;
    private int courseID;
    
    public ArrayList<SelectItem> getListCourse() {
        return listCourse;
    }

    public void setListCourse(ArrayList<SelectItem> listCourse) {
        this.listCourse = listCourse;
    }

    public ArrayList<SelectItem> getListSemester() {
        if(this.courseID!=0){
            listSemester= this.tbSubjectFacade.getListSemester(this.courseID);
            return listSemester;
        }
        else{
            return null;
        }      
    }

    public void setListSemester(ArrayList<SelectItem> listSemester) {
        this.listSemester = listSemester;
    }

    
    
    @PostConstruct
    public void init() {
        this.tbSubject = new TbSubject();
        this.tbSubject1 = new TbSubject();
        this.error = 1;
        this.semesterID = 0;
        this.listCourse = this.tbSubjectFacade.getListCourse();
        this.listSemester = this.tbSubjectFacade.getListSemester(courseID);
        this.courseID=0;
                
        //this.courseID = this.tbCourseFacade.findAll().get(0).getCourseID();
    }
    /** Creates a new instance of mbSubjectController */
    public mbSubjectController() {
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

   
    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int SemesterID) {
        this.semesterID = SemesterID;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public TbSubject getTbSubject() {
        return tbSubject;
    }

    public void setTbSubject(TbSubject tbSubject) {
        this.tbSubject = tbSubject;
    }

    public TbSubject getTbSubject1() {
        return tbSubject1;
    }

    public void setTbSubject1(TbSubject tbSubject1) {
        this.tbSubject1 = tbSubject1;
    }
    
    public List<TbSubject> listSubject(){
        return this.tbSubjectFacade.findAll();
    }
    
    public List<TbCourse> listCourse(){
        return this.tbCourseFacade.findAll();
    }
    
//    public List<SelectItem> listSemesterName() {             
//        return this.tbCourseFacade.listSemester(courseID);
//    }
//    
//    public ArrayList<SelectItem> listCourse(){
//        return this.tbCourseFacade.listCourse();
//    }
    
    //insert new Subject
    public void addNew() {
        String name = this.tbSubject1.getSubjectName();
        
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.tbSubjectFacade.checkSubName(name) == false) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "insert", "Fail"));
                this.error=1;
            } else if (this.semesterID == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "Chon Semester"));
                this.error=1;

            } else {
                TbSemester semester = this.tbSemesterFacade.find(this.semesterID);
                this.tbSubject1.setSemID(semester);
                this.tbSubjectFacade.create(tbSubject1);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert", "successful"));
                this.tbSubject1.setSubjectName("");
                this.setSemesterID(0);
                this.error = 0;
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }

    }
    
    //update Subject
    public void updateSubject(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.tbSubjectFacade.edit(tbSubject);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "successful"));
            this.error = 0;
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }

    }
    
    //delete subject
    public void deleteSubject() {
        int id = this.tbSubject.getSubjectID();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.tbSubject.setSubjectID(id);
            this.tbSubjectFacade.remove(tbSubject);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "successful"));
            this.error = 0;
            
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }
    }
    
   
    
}

