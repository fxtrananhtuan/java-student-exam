/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helperPack;

/**
 *
 * @author MesutOezil
 */
public class checkSession {

    /** Creates a new instance of checkSession */
    private String check;

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
    
       public checkSession() {
        checkLogin();
    }
    
    public String checkLogin() {
        if (SessionTool.getSession("login") != null) {
            if (!SessionTool.getSession("login").equals("1")) {
                return "login";
            } else {
                return "";
            }
        }
        else{
            return "login";
        }

    }
}
