/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbAccountFacade;
import beanCRUD.TbFAQFacade;
import beanInfo.TbFAQ;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author MesutOezil
 */
public class mbFAQController implements Serializable{
    @EJB
    private TbFAQFacade tbFAQFacade;
    @EJB
    private TbAccountFacade tbAccountFacade;

    private TbFAQ tbfaq;
    private TbFAQ tbfaq1;
    private int error;
    
    @PostConstruct
    public void init(){
        this.tbfaq = new TbFAQ();
        this.tbfaq1 = new TbFAQ();
        this.error = 1;
    }
    /** Creates a new instance of mbFAQController */
    public mbFAQController() {
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public TbFAQ getTbfaq() {
        return tbfaq;
    }

    public void setTbfaq(TbFAQ tbfaq) {
        this.tbfaq = tbfaq;
    }

    public TbFAQ getTbfaq1() {
        return tbfaq1;
    }

    public void setTbfaq1(TbFAQ tbfaq1) {
        this.tbfaq1 = tbfaq1;
    }
    
    public List<TbFAQ> listFAQ(){
        return this.tbFAQFacade.findAll();
    }
    
    
     //insert new FAQ
    public void addNew() {
     
        FacesContext context = FacesContext.getCurrentInstance();
        try {

                this.tbfaq1.setUserName(this.tbAccountFacade.find("admin"));
                this.tbFAQFacade.create(tbfaq1);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Insert", "successful"));
                this.tbfaq1.setAnswer("");
                this.tbfaq1.setQuestion("");
                
                this.error = 0;
           

        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }

    }
    
    //update FAQ
    public void updateFAQ(){
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.tbFAQFacade.edit(tbfaq);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Update", "successful"));
            this.error = 0;
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }

    }
    
    //delete semester
    public void deleteFAQ() {
        int id = this.tbfaq.getFaqid();
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            this.tbfaq.setFaqid(id);
            this.tbFAQFacade.remove(tbfaq);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "successful"));
            this.error = 0;
            
        } catch (Exception ex) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Your request is not accept", "please try in next time"));
            this.error = 1;
        }
    }

}
