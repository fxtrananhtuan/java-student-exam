/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbAssignmentFacade;
import beanCRUD.TbStudentAssignmentFacade;
import beanInfo.TbAccount;
import beanInfo.TbAssignment;
import beanInfo.TbCourse;
import beanInfo.TbSemester;
import beanInfo.TbStudent;
import beanInfo.TbStudentAssignment;
import beanInfo.TbStudentAssignmentPK;
import beanInfo.TbSubject;
import helperPack.CreateMessage;
import helperPack.SessionTool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 *
 * @author MesutOezil
 */
public class MBRegisterStudentForAssignment implements Serializable {

    @EJB
    private TbStudentAssignmentFacade tbStudentAssignmentFacade;
    @EJB
    private TbAssignmentFacade tbAssignmentFacade;
    //declare list assignment
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
    //declare one assignment to manipulate
    private TbAssignment assignment;

    public TbAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(TbAssignment assignment) {
        this.assignment = assignment;
    }
    //list student can be added to assignment
    private List<TbStudent> listStudent;

    public List<TbStudent> getListStudent() {
        return listStudent;
    }

    public void setListStudent(List<TbStudent> listStudent) {
        this.listStudent = listStudent;
    }
    //declare to identify method 
    private int method;

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    @PostConstruct
    public void init() {
        this.acc = (TbAccount) SessionTool.getSession("account");
        if (SessionTool.getSession("isAdmin") != null) {
            this.listAssignment = this.tbAssignmentFacade.findAll(); //get all assignment if role is admin
        } else {
            this.listAssignment = this.tbAssignmentFacade.getListAssignmentByUser(acc); //get assignment by id if role is staff
        }
        this.assignment = new TbAssignment();
        this.method = 0;
    }
    private Map<TbStudent, Boolean> checked = new HashMap<TbStudent, Boolean>();

    public Map<TbStudent, Boolean> getChecked() {
        return checked;
    }

    public void setChecked(Map<TbStudent, Boolean> checked) {
        this.checked = checked;
    }

    /** Creates a new instance of MBRegisterStudentForAssignment */
    public MBRegisterStudentForAssignment() {
    }

    public void registerStudent() {
        this.listStudent = this.tbStudentAssignmentFacade.listStudentBySubjecID(this.assignment.getSubjectID().getSubjectID());
        if (this.listStudent.isEmpty()) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Khong Thay Sinh Vien");
        } else {
            CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Co");
        }
    }

   

    //get list student have registed in one assigment
    public void listRegisted() {
        List<TbStudentAssignment> list = this.tbStudentAssignmentFacade.listAssignmentByAssignmentID(this.assignment.getAssignmentID());
        List<TbStudent> listStud = new ArrayList<TbStudent>();
        for (TbStudentAssignment tsa : list) {
            TbStudent stud = tsa.getTbStudent();
            listStud.add(stud);
        }
        this.listStudent = listStud;
    }

   

    public void manipulateAssginment() {
        if (this.method == 0) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Choose your action first!!!");
        } else if (this.method == 1) {
            List<TbStudent> listToInsert = new ArrayList<TbStudent>();
            for (Map.Entry<TbStudent, Boolean> oneMap : checked.entrySet()) {
                if (oneMap.getValue() == true) {
                    listToInsert.add(oneMap.getKey());
                }

            }
            for (TbStudent stud : listToInsert) {
                CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, stud.getStudentID(), stud.getFullName());
            }
            List<TbStudentAssignment> listAByAID = this.tbStudentAssignmentFacade.listAssignmentByAssignmentID(this.assignment.getAssignmentID());
            if (listToInsert.isEmpty()) {
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose student before insert");
            } else {
                List<TbStudent> listDuplicate = new ArrayList<TbStudent>();
                for (TbStudent stud : listToInsert) {
                    for (TbStudentAssignment tsa : listAByAID) {
                        if (stud.getStudentID().equals(tsa.getTbStudent().getStudentID())) {
                            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Duplicate", "This User Is Exist");
                            listDuplicate.add(stud);
                        }
                    }
                }
                for (TbStudent stud : listDuplicate) {
                    listToInsert.remove(stud);
                }
                for (TbStudent stud : listToInsert) {
                    try {
                        TbStudentAssignment sa = new TbStudentAssignment();
                        TbStudentAssignmentPK saPK = new TbStudentAssignmentPK();
                        saPK.setAssignmentID(this.assignment.getAssignmentID());
                        saPK.setStudentID(stud.getStudentID());
                        sa.setMark(0);
                        sa.setFileUpload("");
                        sa.setTbStudentAssignmentPK(saPK);
                        this.tbStudentAssignmentFacade.create(sa);
                        CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Add list student successfully");
                    } catch (Exception ex) {
                        CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
                    }
                }
                this.checked = new HashMap<TbStudent, Boolean>();
                this.listStudent = null;
                this.method=0;
            }
        } else if (this.method == 2) {
            List<TbStudent> listToDel = new ArrayList<TbStudent>();
            for (Map.Entry<TbStudent, Boolean> oneMap : checked.entrySet()) {
                if (oneMap.getValue() == true) {
                    listToDel.add(oneMap.getKey());
                }

            }
            List<TbStudentAssignment> removeList = new ArrayList<TbStudentAssignment>();
            List<TbStudentAssignment> listAByAID = this.tbStudentAssignmentFacade.listAssignmentByAssignmentID(this.assignment.getAssignmentID());
            if (listToDel.isEmpty()) {
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please choose student before edit");
            } else {
                try {
                    for (TbStudent stud : listToDel) {
                        for (TbStudentAssignment tsa : listAByAID) {
                            if (tsa.getTbStudent().getStudentID().equals(stud.getStudentID())) {
                                removeList.add(tsa);
                            }
                        }
                    }
                    for (TbStudentAssignment tsa : removeList) {
                        this.tbStudentAssignmentFacade.remove(tsa);
                    }
                    CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Edit list student success");
                } catch (Exception ex) {
                    CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
                }
                this.checked = new HashMap<TbStudent, Boolean>();
                this.listStudent = null;
                this.method=0;
            }
        }
    }
}
