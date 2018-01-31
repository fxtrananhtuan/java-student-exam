/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbAssignmentFacade;
import beanCRUD.TbStudentAssignmentFacade;
import beanCRUD.TbSubjectFacade;
import beanInfo.TbAccount;
import beanInfo.TbAssignment;
import beanInfo.TbSubject;
import helperPack.CreateMessage;
import helperPack.SessionTool;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

/**
 *
 * @author MesutOezil
 */
public class MBAssignmentManager implements Serializable {

    @EJB
    private TbStudentAssignmentFacade tbStudentAssignmentFacade;
    @EJB
    private TbSubjectFacade tbSubjectFacade;
    @EJB
    private TbAssignmentFacade tbAssignmentFacade;
    //declare item for selectOneMenu control
    private ArrayList<SelectItem> listCourse;
    private ArrayList<SelectItem> listSemeter;
    private ArrayList<SelectItem> listSubject;

    public ArrayList<SelectItem> getListCourse() {
        return listCourse;
    }

    public void setListCourse(ArrayList<SelectItem> listCourse) {
        this.listCourse = listCourse;
    }

    //get all semester with courseID
    public ArrayList<SelectItem> getListSemeter() {
        if (this.courseID != 0) {
            listSemeter = this.tbAssignmentFacade.getListSemester(this.courseID);
            return listSemeter;
        } else {
            return null;
        }
    }

    public void setListSemeter(ArrayList<SelectItem> listSemeter) {
        this.listSemeter = listSemeter;
    }

    //get all subject with subjectID
    public ArrayList<SelectItem> getListSubject() {
        if (this.semID != 0) {
            listSubject = this.tbAssignmentFacade.getListSubject(this.semID);
            return this.listSubject;
        } else {
            return null;
        }
    }

    public void setListSubject(ArrayList<SelectItem> listSubject) {
        this.listSubject = listSubject;
    }
    //declare some variable to manipulate with selectOneMenu control
    private int courseID;
    private int semID;
    private int subjectID;

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getSemID() {
        return semID;
    }

    public void setSemID(int semID) {
        this.semID = semID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }
    //list all assignment
    private List<TbAssignment> listAssignment;

    public List<TbAssignment> getListAssignment() {
        return listAssignment;
    }

    public void setListAssignment(List<TbAssignment> listAssignment) {
        this.listAssignment = listAssignment;
    }
    //declare an assignment
    private TbAssignment assignment;

    public TbAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(TbAssignment assignment) {
        this.assignment = assignment;
    }
    //subject to insert one assignment
    private int newSubjectID;

    public int getNewSubjectID() {
        return newSubjectID;
    }

    public void setNewSubjectID(int newSubjectID) {
        this.newSubjectID = newSubjectID;
    }
    //display error when needed
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
    //declare one upload item to manipulate with upload file
    private UploadItem uploadedFile;

    public UploadItem getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadItem uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    private TbAccount acc; //check role of account

    public TbAccount getAcc() {
        return acc;
    }

    public void setAcc(TbAccount acc) {
        this.acc = acc;
    }
    private String p;

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }
    private String n;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }
    //declare assigment to delete
    private TbAssignment assignmentToDel;

    public TbAssignment getAssignmentToDel() {
        return assignmentToDel;
    }

    public void setAssignmentToDel(TbAssignment assignmentToDel) {
        this.assignmentToDel = assignmentToDel;
    }

    @PostConstruct
    public void init() {
        this.listCourse = this.tbAssignmentFacade.getListCourse();//Get list Course
        this.acc = (TbAccount) SessionTool.getSession("account");
        if (SessionTool.getSession("isAdmin") != null) {
            this.listAssignment = this.tbAssignmentFacade.findAll(); //get all assignment if role is admin
        } else {
            this.listAssignment = this.tbAssignmentFacade.getListAssignmentByUser(acc); //get assignment by id if role is staff
        }
        this.assignment = new TbAssignment();//create instance of assignment
        this.assignmentToDel = new TbAssignment();
    }

    /** Creates a new instance of MBAssignmentManager */
    public MBAssignmentManager() {
    }

    //get uploaded file to manipulate 
    public void listener(UploadEvent event) throws Exception {
        this.uploadedFile = event.getUploadItem();
    }

    public void insertOneAssignment() {
        Date now = new Date();
        if (this.courseID == 0) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose Course!!!");
        } else if (this.semID == 0) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose Semester!!!");
        } else if (this.subjectID == 0) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose Subject!!!");
        } else if (this.assignment.getStartDate() == null) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose start date!!!");
        } else if (this.assignment.getEndDate() == null) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose end date!!!");
        } else if (this.assignment.getStartDate().compareTo(now) < 0) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Start date must be after current date!!!");
        } else if (this.assignment.getEndDate().before(this.assignment.getStartDate())) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "End date must be after start date");
        } else if (this.uploadedFile == null) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose file to upload!!!");
        }
        else if(this.uploadedFile.getData()==null)
        {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Your file is not have content!!!");  
        }
        else {
            try {
                System.out.println("Course ID:" + this.courseID);
                System.out.println("Semester ID:" + this.semID);
                System.out.println("Subject ID:" + this.subjectID);
                TbSubject subject = this.tbSubjectFacade.find(this.subjectID);
                this.assignment.setSubjectID(subject);
                this.assignment.setUserName(this.acc);
                Date date = new Date();
                this.assignment.setAssignmentFile(date.getTime() + this.uploadedFile.getFileName());
                String path = SessionTool.getLineExecutePath("/admin/assignment");
                String realPath = SessionTool.getRealPath(path);
                path += "/" + date.getTime() + this.uploadedFile.getFileName();
                System.out.println("path:" + path);
                realPath += "/" + date.getTime() + this.uploadedFile.getFileName();
                System.out.println("real path:" + realPath);
                SessionTool.createFilePath(path, this.uploadedFile.getData());
                SessionTool.createFilePath(realPath, this.uploadedFile.getData());
                this.tbAssignmentFacade.create(this.assignment);
                updateAll();
                CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Create Assignment Successfully!!!");
                this.error = 1;
                this.assignment = new TbAssignment();
                this.courseID = 0;
                this.semID = 0;
                this.subjectID = 0;
                this.uploadedFile = null;
            } catch (Exception ex) {
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
            }
        }

    }

    public void updateAssignment() {
        Date now = new Date();
        if (this.assignment.getStartDate() == null) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose start date!!!");
        } else if (this.assignment.getEndDate() == null) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Pleas choose end date!!!");
        } else if (this.assignment.getEndDate().compareTo(this.assignment.getStartDate()) < 0) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "End date must be after start date!!!");
        } else {
            if (this.uploadedFile == null) {
                try {
                    this.tbAssignmentFacade.edit(this.assignment);
                    CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Update assignment success!!!");
                    this.error = 1;
                    this.assignment = new TbAssignment();
                    this.uploadedFile = null;
                } catch (Exception ex) {
                    CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
                }
            } else {
                try {
                    String path = SessionTool.getLineExecutePath("/admin/assignment");
                    String pathToDelete = SessionTool.getRealPath(path);
                    pathToDelete += "/" + this.assignment.getAssignmentFile();
                    File fileToDelete = new File(pathToDelete);
                    fileToDelete.delete();

                    String pathNew = SessionTool.getLineExecutePath("/admin/assignment");
                    String realPathNew = SessionTool.getRealPath(pathNew);
                    String newPathFile = pathNew + "/" + now.getTime() + this.uploadedFile.getFileName();
                    String newRealPathFile = realPathNew + "/" + now.getTime() + this.uploadedFile.getFileName();
                    System.out.println("newPAthFile:" + newPathFile);
                    System.out.println("real" + newRealPathFile);
                    SessionTool.createFilePath(newPathFile, this.uploadedFile.getData());
                    SessionTool.createFilePath(newRealPathFile, this.uploadedFile.getData());
                    this.assignment.setAssignmentFile(now.getTime() + this.uploadedFile.getFileName());
                    this.tbAssignmentFacade.edit(this.assignment);
                    CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Update assignment success");
                    this.error = 1;
                    this.assignment = new TbAssignment();
                    this.uploadedFile = null;
                } catch (Exception ex) {
                    CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
                }
            }
        }

    }

    //check admin and update
    public void updateAll() {
        if (SessionTool.getSession("isAdmin") != null) {
            this.listAssignment = this.tbAssignmentFacade.findAll(); //get all assignment if role is admin
        } else {
            this.listAssignment = this.tbAssignmentFacade.getListAssignmentByUser(acc); //get assignment by id if role is staff
        }
    }

    public void toDowndload() {
        SessionTool.createSession("path", this.p);
        SessionTool.createSession("name", this.n);
    }

    //delete assginment
    public void toDelete() {
        if (!this.tbStudentAssignmentFacade.listAssignmentByAssignmentID(this.assignmentToDel.getAssignmentID()).isEmpty()) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Can not delete this assignment");
        } else {
         //   try {
                TbAssignment ta = this.tbAssignmentFacade.find(this.assignmentToDel.getAssignmentID());
                this.tbAssignmentFacade.remove(ta);
                updateAll();
                String path = SessionTool.getLineExecutePath("/admin/assignment");
                String pathToDelete = SessionTool.getRealPath(path);
                pathToDelete += "/" + this.assignmentToDel.getAssignmentFile();
                File fileToDelete = new File(pathToDelete);
                fileToDelete.delete();
                CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Delete success!!!");
                this.error = 1;
        //    } catch (Exception ex) {
      //          CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
          //  }
        }
    }
}
