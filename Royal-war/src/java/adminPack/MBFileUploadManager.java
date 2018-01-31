/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbAssignmentFacade;
import beanCRUD.TbStudentAssignmentFacade;
import beanInfo.TbAccount;
import beanInfo.TbAssignment;
import beanInfo.TbStudentAssignment;
import helperPack.CreateMessage;
import helperPack.SessionTool;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 *
 * @author MesutOezil
 */
public class MBFileUploadManager implements Serializable {
    @EJB
    private TbStudentAssignmentFacade tbStudentAssignmentFacade;

    @EJB
    private TbAssignmentFacade tbAssignmentFacade;
    
    //get all assignment
    private List<TbAssignment> listAssignment;

    public List<TbAssignment> getListAssignment() {
        return listAssignment;
    }

    public void setListAssignment(List<TbAssignment> listAssignment) {
        this.listAssignment = listAssignment;
    }
    private TbAccount acc; //check role of account

    public TbAccount getAcc() {
        return acc;
    }

    public void setAcc(TbAccount acc) {
        this.acc = acc;
    }
    
    private TbAssignment assignment;
    
    public TbAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(TbAssignment assignment) {
        this.assignment = assignment;
    }
    //get all assignment based on assignment id
    private List<TbStudentAssignment> listSA;

    public List<TbStudentAssignment> getListSA() {
        return listSA;
    }

    public void setListSA(List<TbStudentAssignment> listSA) {
        this.listSA = listSA;
    }
    
    
    @PostConstruct
    public void init() {
        this.acc = (TbAccount) SessionTool.getSession("account");
        if (SessionTool.getSession("isAdmin") != null) {
            this.listAssignment = this.tbAssignmentFacade.findAll(); //get all assignment if role is admin
        } else {
            this.listAssignment = this.tbAssignmentFacade.getListAssignmentByUser(acc); //get assignment by id if role is staff
        }
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
    
    private String sub;

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }
    
    
    
    /** Creates a new instance of MBFileUploadManager */
    public MBFileUploadManager() {
    }
    
    public void listUploadFile(){
        this.listSA=this.tbStudentAssignmentFacade.listAssignmentByAssignmentID(this.assignment.getAssignmentID());
        if(this.listSA.isEmpty()){
            CreateMessage.createMessage(FacesMessage.SEVERITY_WARN, "Warning", "This assignment have not any file");
        }
    }
    
    public void toDowndload() {
        SessionTool.createSession("path", this.p);
        SessionTool.createSession("name", this.n);
        SessionTool.createSession("sub", this.sub);
        System.out.println(this.p);
        System.out.println(this.n);
    }
}
