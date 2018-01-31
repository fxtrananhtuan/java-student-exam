/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbCourseFacade;
import beanCRUD.TbMarkFacade;
import beanCRUD.TbSemesterFacade;
import beanCRUD.TbStudentFacade;
import beanCRUD.TbSubjectFacade;
import beanInfo.TbBatch;
import beanInfo.TbCourse;
import beanInfo.TbMark;
import beanInfo.TbMarkPK;
import beanInfo.TbStudent;
import beanInfo.TbSubject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.swing.text.StyledEditorKit.BoldAction;

/**
 *
 * @author MesutOezil
 */
public class mbMarkStudentController implements Serializable{
    @EJB
    private TbStudentFacade tbStudentFacade;
    @EJB
    private TbSubjectFacade tbSubjectFacade;
    @EJB
    private TbSemesterFacade tbSemesterFacade;
    @EJB
    private TbCourseFacade tbCourseFacade;
    @EJB
    private TbMarkFacade tbMarkFacade;

   private TbStudent student;
    private TbMark tbmark1;
    private TbMark tbmark;
    private int error;
    private String studentID;
    private int semID;
    private int courseID;
    private int subjectID;
    private ArrayList<SelectItem> listSemester;
    private ArrayList<SelectItem> listSubject;
    private List<TbMark> listMark;

    @PostConstruct
    public void init() {
        this.student = new TbStudent();

        this.error = 1;
        this.tbmark = new TbMark();
        this.tbmark1 = new TbMark();
        this.semID = 0;
        this.subjectID = 0;

    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<TbMark> getListMark() {

        return listMark;

    }

    public void setListMark(List<TbMark> listMark) {
        this.listMark = listMark;
    }

    public ArrayList<SelectItem> getListSemester() {
        if (this.courseID != 0) {
            this.listSemester = this.tbMarkFacade.getListSemester(courseID);
            return listSemester;
        } else {
            return null;
        }
    }

    public void setListSemester(ArrayList<SelectItem> listSemester) {
        this.listSemester = listSemester;
    }

    public ArrayList<SelectItem> getListSubject() {
        if (this.semID != 0) {
            this.listSubject = this.tbMarkFacade.getListSubject(semID);
            return listSubject;
        } else {
            return null;
        }
    }

    public void setListSubject(ArrayList<SelectItem> listSubject) {
        this.listSubject = listSubject;
    }

    public int getSemID() {
        return semID;
    }

    public void setSemID(int semID) {
        this.semID = semID;
    }

    public TbStudent getStudent() {
        return student;
    }

    public void setStudent(TbStudent student) {
        this.student = student;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public TbMark getTbmark() {
        return tbmark;
    }

    public void setTbmark(TbMark tbmark) {
        this.tbmark = tbmark;
    }

    public TbMark getTbmark1() {
        return tbmark1;
    }

    public void setTbmark1(TbMark tbmark1) {
        this.tbmark1 = tbmark1;
    }

    //xá»­ lÃ½ input studentID
    public void doSemAndSubject() {
        FacesContext fc = FacesContext.getCurrentInstance();
        //   this.studentID = this.student.getStudentID();
        //this.student = this.tbMarkFacade.find(this.studentID).getTbStudent();

//        TbBatch batch = this.student.getBatchID();
//        TbCourse course = batch.getCourseID();
//        this.courseID = course.getCourseID();
        if (this.studentID.trim().equals("")) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "StudentID", "Not Null"));
        } else if (this.tbStudentFacade.find(this.studentID) == null) {
            fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "StudentID", "does not exist"));
        } else {
            this.listMark = this.tbMarkFacade.findByStudentID(this.studentID);
            TbStudent stud = this.tbStudentFacade.find(this.studentID);
            System.out.println("StudentID: " + stud.getStudentID());
            TbBatch batch = stud.getBatchID();
            System.out.println("Batch: " + batch.getBatchName());
            TbCourse course = batch.getCourseID();
            System.out.println("Course: " + course.getCourseName());
            this.courseID = course.getCourseID();
            this.listSemester = this.tbMarkFacade.getListSemester(this.courseID);
            fc.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, this.studentID));
        }



        //   this.courseID = this.student.getBatchID().getCourseID().getCourseID();

        //   this.listMark = this.tbMarkFacade.findByStudentID(this.studentID);
    }

    //Add new subjectMark to this student
    public void addNew() {
        FacesContext context = FacesContext.getCurrentInstance();
        System.out.println("subject" + this.subjectID);
        System.out.println("Student" + this.studentID);
        try {
            if (this.semID == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "Chon Semester"));
                this.error = 1;
            } else if (this.subjectID == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "Chon subject"));
                this.error = 1;
            } else if (this.tbmark1.getMark() > 10 || this.tbmark1.getMark() < 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "mark [0-10]"));
                this.error = 1;
            } else {
                TbSubject sub = this.tbSubjectFacade.find(this.subjectID);
                System.out.println("subject" + sub.getSubjectName());
                TbStudent stu = this.tbStudentFacade.find(this.studentID);
                System.out.println("Student" + stu.getStudentID());
                boolean result=true;
                for (TbMark mark : listMark) {
                    if (mark.getTbSubject().getSubjectID() == this.subjectID) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "insert Fail", "This Student had this subject Mark!!!"));
                        result=false;
                        break;
                    }
                }
                
                if(result==true){
                        TbMark markInsert=new TbMark();
                        TbMarkPK markpk = new TbMarkPK();
                        markpk.setStudentID(this.studentID);
                        markpk.setSubjectID(sub.getSubjectID());
                        markInsert.setMark(this.tbmark1.getMark());
                        markInsert.setTbMarkPK(markpk);
                        this.tbMarkFacade.create(markInsert);
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert", "successful"));
                        this.tbmark1=new TbMark();
                        this.setSemID(0);
                        this.listSubject=null;
                        this.setSubjectID(0);
                        this.error = 0;
                        this.listMark = this.tbMarkFacade.findByStudentID(this.studentID);
                }
                else{
                    this.error=1;
                }
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", ex.getMessage()));
            this.error = 1;
        }
    }

    //update Mark
    public void updateMark() {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            if (this.tbmark.getMark() > 10 || this.tbmark.getMark() < 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "mark [0-10]"));
                this.error = 1;
            } else {
                TbMarkPK markpk = new TbMarkPK();
                markpk.setStudentID(this.tbmark.getTbStudent().getStudentID());
                markpk.setSubjectID(this.tbmark.getTbSubject().getSubjectID());
                this.tbmark1.setTbMarkPK(markpk);
                this.tbMarkFacade.edit(tbmark);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "successful"));
                this.error = 0;
                this.listMark = this.tbMarkFacade.findByStudentID(this.studentID);
            }
        } catch (EJBException ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", ex.getMessage()));
            this.error = 1;
        }
    }
    
    
}
