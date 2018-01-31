/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package userPack;

import java.io.Serializable;

/**
 *
 * @author MesutOezil
 */
public class MBLogin implements Serializable {

    private String login;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    
    /** Creates a new instance of MBLogin */
    public MBLogin() {
        this.login=null;
        this.userName="nghia";
    }
}
