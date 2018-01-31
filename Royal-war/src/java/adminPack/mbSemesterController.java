/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbCourseFacade;
import beanCRUD.TbSemesterFacade;
import beanInfo.TbCourse;
import beanInfo.TbSemester;
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
public class mbSemesterController implements Serializable{
    @EJB
    private TbCourseFacade tbCourseFacade;
    @EJB
    private TbSemesterFacade tbSemesterFacade;

    private TbSemester semester;
    private TbSemester semester1;
    private int error;
    private int CourseID;
    private String equal = "equal";

    
    @PostConstruct
    public void init() {
        this.semester = new TbSemester();
        this.semester1 = new TbSemester();
        this.error = 1;
        this.CourseID = 0;
    }
    
    /** Creates a new instance of mbSemesterController */
    public mbSemesterController() {
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getEqual() {
        return equal;
    }

    public void setEqual(String equal) {
        this.equal = equal;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public TbSemester getSemester() {
        return semester;
    }

    public void setSemester(TbSemester semester) {
        this.semester = semester;
    }

    public TbSemester getSemester1() {
        return semester1;
    }

    public void setSemester1(TbSemester semester1) {
        this.semester1 = semester1;
    }
    
    public List<TbSemester> listSemester(){
        return this.tbSemesterFacade.findAll();
    }
    
    public ArrayList<SelectItem> listCourseName() {
        return this.tbCourseFacade.listCourse();
    }
    
    //kiem tra trung name hay course ko?
    private boolean check(String name){
        boolean result = true;
        List<TbSemester> list = new ArrayList<TbSemester>();
        //láº¥y list semester co name Ä‘Ã³.
        for(TbSemester f : this.tbSemesterFacade.findAll()){
            if(f.getSemName().equals(name)){
                list.add(f);
            }
        }
        //kiem tra co trung course hay ko?
        for(TbSemester f1 : list){
            if(f1.getCourseID().getCourseID() == CourseID){
                result = false;
                break;
            }
        }
        return result;
    }
    
    //insert new Batch
    public void addNew() {
        String name = this.semester1.getSemName();
       
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.check(name) == false) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "insert", "Fail"));
                this.error=1;
            } else if (this.CourseID == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "Chon course"));
                this.error=1;

            } else {
                TbCourse course = this.tbCourseFacade.find(this.CourseID);
                this.semester1.setCourseID(course);
                this.tbSemesterFacade.create(semester1);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert", "successful"));
                this.semester1.setSemName("");
                this.setCourseID(0);
                this.error = 0;
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }

    }
    
    //update semester
    public void updateSemester(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.tbSemesterFacade.edit(semester);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "successful"));
            this.error = 0;
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }

    }
    
    //delete semester
    public void deleteSemester() {
        int id = this.semester.getSemID();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.semester.setSemID(id);
            this.tbSemesterFacade.remove(semester);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "successful"));
            this.error = 0;
            
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }
    }
}

