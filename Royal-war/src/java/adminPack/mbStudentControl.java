/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbBatchFacade;
import beanCRUD.TbStudentFacade;
import beanInfo.TbBatch;
import beanInfo.TbStudent;
import helperPack.CreateMessage;
import helperPack.Encrypt;
import helperPack.SessionTool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Dell
 */
public class mbStudentControl implements Serializable {
    
    @EJB
    private TbBatchFacade tbBatchFacade;
    @EJB
    private TbStudentFacade tbStudentFacade;
    private List<TbStudent> liststu;
    private TbStudent student;
    private TbStudent student1;
    private int batchID;
    private int error;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    @PostConstruct
    public void init() {
        this.liststu = this.tbStudentFacade.findAll();
        this.student = new TbStudent();
        this.student1 = new TbStudent();
        this.error = 1;
        
    }

    /** Creates a new instance of mbStudentControl */
    public mbStudentControl() {
    }
    
    public List<TbStudent> getListstu() {
        return liststu;
    }
    
    public void setListstu(List<TbStudent> liststu) {
        this.liststu = liststu;
    }
    
    public TbStudent getStudent() {
        return student;
    }
    
    public void setStudent(TbStudent student) {
        this.student = student;
    }
    
    public TbStudent getStudent1() {
        return student1;
    }
    
    public void setStudent1(TbStudent student1) {
        this.student1 = student1;
    }
    
    public int getError() {
        return error;
    }
    
    public void setError(int error) {
        this.error = error;
    }
    
    public List<TbStudent> getListStudent() {
        return this.tbStudentFacade.findAll();
    }
    
    public ArrayList<SelectItem> getListBatchName() {
        return this.tbBatchFacade.listBatch();
    }
    
    public void insertStudent() {
        TbBatch batch = this.tbBatchFacade.find(this.batchID);
        String text = "";
        try {
            if (this.batchID == 0) {
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Choose Batch!!!");
            } else {
                do {
                    Random ran = new Random();
                    String s = "";
                    for (int i = 1; i < 7; i++) {
                        s = s + ran.nextInt(9);
                    }              
                    text = batch.getCourseID().getCourseName() + batch.getBatchName() + "-" + s;
                } while (this.tbStudentFacade.find(text) != null);
                // TbBatch batch = this.tbBatchFacade.find(this.batchID);
                this.student1.setBatchID(batch);
                this.student1.setStudentID(text);
                this.student1.setPassword(Encrypt.hashPassword(this.password));
                this.tbStudentFacade.create(student1);
                String path=SessionTool.getLineExecutePath("/admin/studentUpload");
                String realPath=SessionTool.getRealPath(path);
                path+="/"+text;
                realPath+="/"+text;
                SessionTool.createFolderPath(path);
                SessionTool.createFolderPath(realPath);
                CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Insert Successfully");
                this.liststu = this.tbStudentFacade.findAll();
                this.student1 = new TbStudent();
                this.student1.setStudentID("");
                this.setBatchID(0);
                this.password="";
                this.student1.setPassword("");
                this.student1.setFullName("");
                this.student1.setPhone("");
                this.student1.setEmail("");
                this.student1.setAddress("");
                this.student1.setStatus(true);
                this.error = 0;
            }
        } catch (Exception ex) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Can not insert at this time");
            this.error = 1;
        }
    }
    
    public void updateStudent() {
        try {
            TbBatch batch = this.tbBatchFacade.find(this.student.getBatchID().getBatchID());
            this.student.setBatchID(batch);
            this.tbStudentFacade.edit(student);
            CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Update Successfully");
            this.error = 0;
        } catch (Exception ex) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Can not Update at this time");
            this.error = 1;
        }
    }
    
    public void deleteStudent() {
        try {
            this.tbStudentFacade.remove(student);
            CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Delete Successfully");
            this.error = 0;
        } catch (Exception ex) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Can not Delete at this time");
            this.error = 1;
        }
    }
    
    public int getBatchID() {
        return batchID;
    }
    
    public void setBatchID(int batchID) {
        this.batchID = batchID;
    }
}
