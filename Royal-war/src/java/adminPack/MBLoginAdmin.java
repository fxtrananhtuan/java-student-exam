/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbAccountFacade;
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
public class MBLoginAdmin implements Serializable {

    @EJB
    private TbAccountFacade tbAccountFacade;
    boolean remember;
    @NotEmpty(message = "Username is null")
    String username;
    @NotEmpty(message = "Password is null")
    @Length(min = 6, message = "At least 6 characters")
    String password;
    String remember1 = "hi";
    private String isAdmin = null;
    private String isError = "error";
    private String isBigAdmin=null;

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

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public MBLoginAdmin() {
        this.isError = "error";
        checkCookie();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        if (remember == false) {
            username = "";
            return username;
        } else {
            return username;
        }
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        if (remember == false) {
            password = "";
            return password;
        } else {
            return password;
        }
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    public boolean getRemember() {
        return remember;
    }

    public String checkLogin() {

        if (this.tbAccountFacade.getAcc(username, Encrypt.hashPassword(password)) != null) {
            if (!this.tbAccountFacade.getAcc(username, Encrypt.hashPassword(password)).isStatus()) {
                this.isError = null;
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Opp!Wrong", "Can not login to system");
                return null;
            } else {
                FacesContext facesContext = FacesContext.getCurrentInstance();

                // Save the userid and password in a cookie
                Cookie btuser = new Cookie("btuser", username);
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
                int roleID = this.tbAccountFacade.getAcc(username, Encrypt.hashPassword(password)).getRoleID().getRoleID();
                if (roleID == 1) {
                    this.isAdmin = "admin";
                } else {
                    this.isAdmin = null;
                }
                
                if(this.tbAccountFacade.getAcc(username, Encrypt.hashPassword(password)).getUserName().equals("admin")){
                    this.isBigAdmin="bigAdmin";
                }
                else{
                    this.isBigAdmin=null;
                }
                SessionTool.createSession("username", this.username);
                SessionTool.createSession("login", 1);
                SessionTool.createSession("isAdmin", this.isAdmin);
                SessionTool.createSession("account", this.tbAccountFacade.getAcc(username, Encrypt.hashPassword(password)));
                this.isError = "error";
                // CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Opp!Wrong", "Login success"); 
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
                if (cookieName.equals("btuser")) {
                    username = cookie[i].getValue();
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
