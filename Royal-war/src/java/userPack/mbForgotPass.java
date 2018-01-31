/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userPack;

import beanCRUD.TbStudentFacade;
import beanInfo.TbAccount;
import beanInfo.TbStudent;
import helperPack.CreateMessage;
import helperPack.Encrypt;
import helperPack.sendEmail;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author MesutOezil
 */
public class mbForgotPass implements Serializable{
    @EJB
    private TbStudentFacade tbStudentFacade;
    @NotEmpty(message = "Not Empty")
    private String studentID;
    @NotEmpty(message = "Not Empty")
    private String email;
    @NotEmpty(message = "Not Empty")
    private String fullname;
    @NotEmpty(message = "Not Empty")
    private String phone;
    private TbAccount Acc;
    private int error;

    @PostConstruct
    public void init() {
        this.error = 1;
    }

    public TbAccount getAcc() {
        return Acc;
    }

    public void setAcc(TbAccount Acc) {
        this.Acc = Acc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /** Creates a new instance of mbForgotPass */
    public mbForgotPass() {
    }

    //forgot password
    public void forgotPassword() {
        CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, phone, studentID);
        CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, fullname, email);

        TbStudent stud = new TbStudent();
        stud = this.tbStudentFacade.getStudentID(this.studentID);

        if (stud != null) {
            if (stud.getStudentID().equals(this.studentID.trim().toString()) && stud.getFullName().equals(this.fullname.trim().toString()) && stud.getEmail().equals(this.email.trim().toString()) && stud.getPhone().equals(this.phone.trim().toString())) {
                String pass = "123456";
                String ePassword = pass;
                ePassword = Encrypt.hashPassword(ePassword);
                stud.setPassword(ePassword);
                this.tbStudentFacade.edit(stud);
                sendEmail.autoSendEmail(email, studentID, "123456");
                this.error = 0;
                CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Your Password has sent to your email Address", "Your Password has sent to your email Address. Change Password after receive!!");
            } else {
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "ThÃ´ng tin nháº­p khÃ´ng chÃ­nh xÃ¡c", "xin hay nháº­p láº¡i");
                return;
            }
        } //          stud = this.tbStudentFacade.forgotPassword(student, email, fullname, phone);
        //            if(stud == null){
        //                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Stud", "null");
        //            }else{
        //                stud.setPassword(Encrypt.hashPassword("123456"));
        //                this.tbStudentFacade.edit(stud);
        //                
        //            }
        else {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Email Or Username is incorrect!!", "Email Or Username is incorrect!!");
            return;
        }
    }
}

