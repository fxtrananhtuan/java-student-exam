/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbBatchFacade;
import beanCRUD.TbCourseFacade;
import beanInfo.TbBatch;
import beanInfo.TbCourse;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class mbBatchController implements Serializable{
    @EJB
    private TbBatchFacade tbBatchFacade;
    @EJB
    private TbCourseFacade tbCourseFacade;
private TbBatch tbBatch;
    private TbBatch tbBatch1;
    private int error;
    private int CourseID;
    private String batchName;
    private String chars;

    public String getChars() {
        return chars;
    }

    public void setChars(String chars) {
        this.chars = chars;
    }

    @PostConstruct
    public void init() {
        this.tbBatch = new TbBatch();
        this.tbBatch1 = new TbBatch();
        this.error = 1;
        this.CourseID = 0;
        this.batchName = "";
        this.chars = "";

    }

    /** Creates a new instance of mbBatchController */
    public mbBatchController() {
    }

    public String getBatchName() {
        DateFormat format = new SimpleDateFormat("yyMM");
        Date start = this.tbBatch1.getStartDate();
        TbCourse course = this.tbCourseFacade.find(this.CourseID);
        this.tbBatch1.setCourseID(course);
        if (CourseID != 0) {
            batchName = this.tbBatch1.getCourseID().getCourseName();
        }
        if (start != null) {
            batchName = format.format(start);
        }
        if (this.chars != null) {
            batchName = chars;
        }
        if (CourseID != 0 && start != null) {
            batchName = this.tbBatch1.getCourseID().getCourseName() + format.format(start);
        }
        if (CourseID != 0 && this.chars != null) {
            batchName = this.tbBatch1.getCourseID().getCourseName() + this.chars;
        }
        if (start != null && this.chars != null) {
            batchName = format.format(start) + this.chars;
        }
        if (CourseID != 0 && start != null && this.chars != null) {
            batchName = this.tbBatch1.getCourseID().getCourseName() + format.format(start) + this.chars;
        }


        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public TbBatch getTbBatch() {
        return tbBatch;
    }

    public void setTbBatch(TbBatch tbBatch) {
        this.tbBatch = tbBatch;
    }

    public TbBatch getTbBatch1() {
        return tbBatch1;
    }

    public void setTbBatch1(TbBatch tbBatch1) {
        this.tbBatch1 = tbBatch1;
    }

    public List<TbBatch> listBatch() {
        return this.tbBatchFacade.findAll();
    }

    public ArrayList<SelectItem> listCourseName() {
        return this.tbCourseFacade.listCourse();
    }

    //insert new Batch
    public void addNew() {
        DateFormat format = new SimpleDateFormat("yyMM");
        FacesContext context = FacesContext.getCurrentInstance();
        Date start = this.tbBatch1.getStartDate();
        Date end = this.tbBatch1.getEndDate();
        Date now = new Date(System.currentTimeMillis());
        try {
            if (this.CourseID == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "Course, select 1 Course"));
                this.error = 1;
            } else if (start == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "StartDate, Not Null"));
                this.error = 1;
            } else if (start.before(now)) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "StartDate, Not less than NowTime"));
                this.error = 1;
            } else if (end == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "EndDate, Not Null"));
                this.error = 1;
            } else if (start.after(end)) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "StartDate, less than Enddate"));
                this.error = 1;
            } else if (this.chars == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "class, select 1 class"));
                this.error = 1;
            } else {
                TbCourse course = this.tbCourseFacade.find(this.CourseID);
                this.tbBatch1.setCourseID(course);
                String text = this.batchName;
//                do {
//                    Random ran = new Random();
//                    String alphabet = "ABCDEFG";
//                    int N = alphabet.length();
//                    String s = "";
//                    for (int i = 1; i < 2; i++) {
//                        s = s + alphabet.charAt(ran.nextInt(N));

////                    }
//                    text = this.batchName;
//                }
                if (this.tbBatchFacade.checkBatchName(text) == false) {
                    context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "this BatchName has been used , please check next class"));
                    this.error = 1;
                } else {

                    this.tbBatch1.setBatchName(text);
                    this.tbBatchFacade.create(tbBatch1);
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert", "successful"));
                    this.error = 0;
                    this.tbBatch1.setBatchName("");
                    this.CourseID = 0;
                    this.tbBatch1.setStartDate(null);
                    this.tbBatch1.setEndDate(null);
                    this.chars = null;
                    this.batchName = null;

                }
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }

    }
    //update Batch

    public void updateBatch() {
        FacesContext context = FacesContext.getCurrentInstance();
        Date start = this.tbBatch.getStartDate();
        Date end = this.tbBatch.getEndDate();
        Date now = new Date(System.currentTimeMillis());
        try {
            if (start == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "StartDate, Not Null"));
                this.error = 1;
            } else if (start.before(now)) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "StartDate, Not less than NowTime"));
                this.error = 1;
            } else if (end == null) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "EndDate, Not Null"));
                this.error = 1;
            } else if (start.after(end)) {
                context.addMessage(null, new FacesMessage(FacesMessage.FACES_MESSAGES, "StartDate, less than Enddate"));
                this.error = 1;
            } else {
                this.tbBatchFacade.edit(tbBatch);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "successful"));
                this.error = 0;
            }
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }

    }

    // Delete Batch
    public void deleteBatch() {

        int id = this.tbBatch.getBatchID();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            if (this.tbBatchFacade.checkDelete(id) == false) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "This Batch Can't Delete", "Please Delete All Student in Batch first"));
                this.error = 1;
            } else {
                this.tbBatch.setBatchID(id);
                this.tbBatchFacade.remove(tbBatch);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "successful"));
                this.error = 0;
            }

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }


    }

}
