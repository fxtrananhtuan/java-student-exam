/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userPack;

import beanCRUD.TbAssignmentFacade;
import beanCRUD.TbStudentAssignmentFacade;
import beanInfo.TbAssignment;
import beanInfo.TbStudent;
import helperPack.CreateMessage;
import helperPack.SessionTool;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;

/**
 *
 * @author MesutOezil
 */
public class MBUserAssignment implements Serializable{
    @EJB
    private TbAssignmentFacade tbAssignmentFacade;

    @EJB
    private TbStudentAssignmentFacade tbStudentAssignmentFacade;
    
    private int assignmentID;

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    
    
    //declare to manipulate with assignment
    private TbAssignment assignment;

    public TbAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(TbAssignment assignment) {
        this.assignment = assignment;
    }
      
    private ArrayList<SelectItem> listAssignment;

    public ArrayList<SelectItem> getListAssignment() {
        return listAssignment;
    }

    public void setListAssignment(ArrayList<SelectItem> listAssignment) {
        this.listAssignment = listAssignment;
    }
    

    private String n;
    private String p;

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    @PostConstruct
    public void init() {
        TbStudent stud=null;
        if (SessionTool.getSession("student") != null) {
            stud =(TbStudent) SessionTool.getSession("student");
        }
        this.assignment=null;
        this.listAssignment = this.tbStudentAssignmentFacade.listTSA(stud.getStudentID());
    }

    /** Creates a new instance of MBUserAssignment */
    public MBUserAssignment() {
    }

    public void fetchDetail() {
        if(this.assignmentID==0){
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Choose Assignment!!!");
        }
        this.assignment=this.tbAssignmentFacade.find(this.assignmentID);
        
    }

    public void toDownload() {
        SessionTool.createSession("path", this.p);
        SessionTool.createSession("name", this.n);
    }
    
}
