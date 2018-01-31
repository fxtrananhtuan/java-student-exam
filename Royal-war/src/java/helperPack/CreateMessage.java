/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package helperPack;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author MesutOezil
 */
public class CreateMessage implements Serializable {
    public static void createMessage(FacesMessage.Severity fs,String title,String content){
        FacesContext fc= FacesContext.getCurrentInstance();
        fc.addMessage(null,new FacesMessage(fs, title, content));
    }
}
