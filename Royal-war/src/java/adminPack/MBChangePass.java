/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbAccountFacade;
import beanInfo.TbAccount;
import helperPack.CreateMessage;
import helperPack.Encrypt;
import helperPack.SessionTool;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author MesutOezil
 */
public class MBChangePass implements Serializable{
    
    @EJB
    private TbAccountFacade tbAccountFacade;    
    
    @NotEmpty(message="Old Password is null")
    private String oldPassword;
    
    @NotEmpty(message="New Password is null")
    @Length(min=6,max=12,message="Password is have 6->12 characters")
    private String newPassword;
    
    @NotEmpty(message="Confirm Password is null")
    private String confirmPassword;    
    
    //used to check confirm password
    private String equal="equal";

    //used to check old password
    private String incorrect="correct";
    
    //used to check change password
    private String success=null;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
    
    public String getIncorrect() {
        return incorrect;
    }

    public void setIncorrect(String incorrect) {
        this.incorrect = incorrect;
    }
    
    public String getEqual() {
        return equal;
    }

    public void setEqual(String equal) {
        this.equal = equal;
    }
      
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }   
    
    /** Creates a new instance of changePass */
    public MBChangePass() {
        
    }
    
    //Method to change password
    public void change(){
        TbAccount acc= ((TbAccount)SessionTool.getSession("account"));
        if(!this.newPassword.equals(this.confirmPassword)){
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Confirm password not match");
            this.equal=null;
        }
        else if(!acc.getPassword().equals(Encrypt.hashPassword(this.oldPassword))){
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Old Password is incorrect");
            this.incorrect=null;  
        }
        else{
            this.success="success";
            this.equal="equal";
            this.incorrect="correct";
            acc.setPassword(Encrypt.hashPassword(this.newPassword));
            this.tbAccountFacade.edit(acc);
            
            CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Change Password is successful");
            
        }
    }
    
    //Method to redirect login
    public String toLogin(){
       return "/templateAdmin/login.jsp?faces-redirect=true";
    }
}
