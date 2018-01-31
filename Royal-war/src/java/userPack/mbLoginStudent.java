/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userPack;

import beanCRUD.TbStudentFacade;
import beanInfo.TbStudent;
import helperPack.CreateMessage;
import helperPack.Encrypt;
import helperPack.SessionTool;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author MesutOezil
 */
public class mbLoginStudent implements Serializable{
    @EJB
    private TbStudentFacade tbStudentFacade;
    
    

   boolean remember;
    @NotEmpty(message = "StudentID is null")
    String studentID;
    @NotEmpty(message = "Password is null")
    @Length(min = 6, message = "At least 6 characters")
    String password;
    String remember1 = "hi";
    private String isAdmin = null;
    private String isError = "error";
    private String isBigAdmin=null;
    
    @NotEmpty(message = "Not Empty")
    private String student;
    @NotEmpty(message = "Not Empty")
    private String email;
    @NotEmpty(message = "Not Empty")
    private String fullname;
    @NotEmpty(message = "Not Empty")
    private String phone;
    private TbStudent stu;
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
    

    public TbStudent getStu() {
        return stu;
    }

    public void setStu(TbStudent stu) {
        this.stu = stu;
    }
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }
    
    

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getIsBigAdmin() {
        return isBigAdmin;
    }

    public void setIsBigAdmin(String isBigAdmin) {
        this.isBigAdmin = isBigAdmin;
    }

    public String getIsError() {
        return isError;
    }

    public void setIsError(String isError) {
        this.isError = isError;
    }

    public String getPassword() {
        if (remember == false) {
            password = "";
            return password;
        } else {
            return password;
        }

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public String getRemember1() {
        return remember1;
    }

    public void setRemember1(String remember1) {
        this.remember1 = remember1;
    }

    public String getStudentID() {
        if (remember == false) {
            studentID ="";
        return studentID;
        }else{
            return studentID;
        }
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    


    
    /** Creates a new instance of mbLoginStudent */
    public mbLoginStudent() {
        this.isError = "error";
 
        checkCookie();
    }
    
    public String checkLogin() {

        if (this.tbStudentFacade.getStudent(studentID, Encrypt.hashPassword(password)) != null) {
            if (!this.tbStudentFacade.getStudent(studentID, Encrypt.hashPassword(password)).isStatus()) {
                this.isError = null;
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Opp!Wrong", "Can not login to system");
                return null;
            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();

                // Save the userid and password in a cookie
                Cookie btuser = new Cookie("btstudentid", studentID);
                Cookie btpasswd = new Cookie("btpasswd", password);
                if (remember == false) {
                    remember1 = "false";
                } else {
                    remember1 = "true";
                }
                Cookie btremember = new Cookie("btremember", remember1);
                btuser.setMaxAge(3600);
                btpasswd.setMaxAge(3600);

                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btuser);
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btpasswd);
                ((HttpServletResponse) facesContext.getExternalContext().getResponse()).addCookie(btremember);
                SessionTool.createSession("student", this.tbStudentFacade.find(studentID.trim().toString()));
                this.isError = "error";
                return "success";
            }

        } else {
            this.isError = null;
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Opp!Wrong", "Can not login to system");
            return null;
        }
    }

    
    public void checkCookie() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String cookieName = null;
        Cookie cookie[] = ((HttpServletRequest) facesContext.getExternalContext().
                getRequest()).getCookies();
        if (cookie != null && cookie.length > 0) {
            for (int i = 0; i < cookie.length; i++) {
                cookieName = cookie[i].getName();
                if (cookieName.equals("btstudentid")) {
                    studentID = cookie[i].getValue();
                } else if (cookieName.equals("btpasswd")) {
                    password = cookie[i].getValue();
                } else if (cookieName.equals("btremember")) {
                    remember1 = cookie[i].getValue();
                    if (remember1.equals("false")) {
                        remember = false;
                    } else if (remember1.equals("true")) {
                        remember = true;
                    }
                }
            }
        }

    }
    
    public String logOut() {
        SessionTool.removeAllSession();
        return "login";
    }

}
