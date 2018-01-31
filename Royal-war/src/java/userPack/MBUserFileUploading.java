/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userPack;

import beanCRUD.TbAssignmentFacade;
import beanCRUD.TbStudentAssignmentFacade;
import beanCRUD.TbSurveyFacade;
import beanInfo.TbAssignment;
import beanInfo.TbStudent;
import beanInfo.TbStudentAssignment;
import beanInfo.TbSurvey;
import helperPack.CreateMessage;
import helperPack.SessionTool;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class MBUserFileUploading implements Serializable {

    @EJB
    private TbSurveyFacade tbSurveyFacade;
    @EJB
    private TbAssignmentFacade tbAssignmentFacade;
    @EJB
    private TbStudentAssignmentFacade tbStudentAssignmentFacade;
    private int assignmentID;

    public int getAssignmentID() {
        return assignmentID;
    }
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }
    private ArrayList<SelectItem> listAssignment;

    public ArrayList<SelectItem> getListAssignment() {
        return listAssignment;
    }

    public void setListAssignment(ArrayList<SelectItem> listAssignment) {
        this.listAssignment = listAssignment;
    }
    private TbAssignment assignment;

    public TbAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(TbAssignment assignment) {
        this.assignment = assignment;
    }
    private UploadItem uploadedFile;

    public UploadItem getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadItem uploadedFile) {
        this.uploadedFile = uploadedFile;
    }
    private TbStudentAssignment updateAssignment;

    public TbStudentAssignment getUpdateAssignment() {
        return updateAssignment;
    }

    public void setUpdateAssignment(TbStudentAssignment updateAssignment) {
        this.updateAssignment = updateAssignment;
    }
    //tao list survey cua assignemnt
    private List<TbSurvey> listSur;

    public List<TbSurvey> getListSur() {
        return listSur;
    }

    public void setListSur(List<TbSurvey> listSur) {
        this.listSur = listSur;
    }

    @PostConstruct
    public void init() {
        TbStudent stud = null;
        if (SessionTool.getSession("student") != null) {
            stud = (TbStudent) SessionTool.getSession("student");
        }
        this.assignment = null;
        this.listAssignment = this.tbStudentAssignmentFacade.listTSA(stud.getStudentID());
        this.updateAssignment = null;
        this.listSur = null;
    }

    /** Creates a new instance of MBUserFileUploading */
    public MBUserFileUploading() {
    }

    public void fetchDetail() {
        if (this.assignmentID == 0) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Choose Assignment!!!");
        }
        this.assignment = this.tbAssignmentFacade.find(this.assignmentID);
        this.listSur = this.tbSurveyFacade.listSur(this.assignmentID);

    }

    //get uploaded file to manipulate 
    public void listener(UploadEvent event) throws Exception {
        this.uploadedFile = event.getUploadItem();
    }

    public void uploadAssignment() {
        Date now = new Date();
        if (this.assignmentID == 0) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Choose Assignment!!!");
        } else if (this.assignment.getEndDate().before(now)) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Your assignment is expired");
        } else if (this.uploadedFile == null) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Choose File Upload!!!");
        } else if (this.uploadedFile.getData() == null) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Your file have not content");
        } else {
            this.updateAssignment = this.tbStudentAssignmentFacade.saFileUpload(this.assignmentID, "ACCP0909B-001");
            if (!this.updateAssignment.getFileUpload().toString().equals("")) {
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Eror", "You can not upload one more time");
            } else {
                String path = SessionTool.getLineExecutePath("/admin/studentUpload/ACCP0909B-001");
                String realPath = SessionTool.getRealPath(path);
                path += "/" + this.assignment.getSubjectID().getSubjectName();
                realPath += "/" + this.assignment.getSubjectID().getSubjectName();
                SessionTool.createFolderPath(path);
                SessionTool.createFolderPath(realPath);
                path += "/" + this.uploadedFile.getFileName();
                realPath += "/" + this.uploadedFile.getFileName();
                SessionTool.createFilePath(path, this.uploadedFile.getData());
                SessionTool.createFilePath(realPath, this.uploadedFile.getData());
                this.updateAssignment.setFileUpload(this.uploadedFile.getFileName());
                this.tbStudentAssignmentFacade.edit(this.updateAssignment);
                CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Your assignment upload success");

                this.assignment = null;
                this.assignmentID = 0;
                this.updateAssignment = null;
                this.error = 1;
            }

        }
    }
}
