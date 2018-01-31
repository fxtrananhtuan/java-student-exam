/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helperPack;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author MesutOezil
 */
public class SessionTool {

    public static void createSession(String sessionName, Object value) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(sessionName, value);
    }

    public static Object getSession(String sessionName) {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(sessionName);
    }

    public static void removeSession(String sessionName) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(sessionName);
    }

    public static void removeAllSession() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
    }

    //lấy đường dẫn thực
    public static String getLineExecutePath(String tenFolder) {
        FacesContext con = FacesContext.getCurrentInstance();
        HttpSession s = (HttpSession) con.getExternalContext().getSession(false);
        ServletContext sc = s.getServletContext();
        String path = sc.getRealPath(tenFolder);
        return path;
    }

    //Ham tao file theo vi tri
    public static boolean createFilePath(String duongDan, byte[] data) {
        File file = new File(duongDan);
        try {
            file.createNewFile();
            FileOutputStream fo = new FileOutputStream(file);
            fo.write(data);
            fo.flush();
            fo.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static void createFolderPath(String path) {
        File file = new File(path);
        file.mkdirs();
    }

    public static String getRealPath(String path) {
        //  return path.substring(0,path.lastIndexOf("\\dist\\gfdeploy\\Royal\\Royal-war_war"))+"\\Royal-war\\web\\";
        String cut = "\\dist\\gfdeploy\\Royal\\Royal-war_war";
        return path.substring(0, path.lastIndexOf("\\dist\\gfdeploy\\Royal\\Royal-war_war"))
                + "\\Royal-war\\web" + path.substring(path.lastIndexOf(cut) + cut.length(), path.length());
    }
}
