/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbAccountFacade;
import beanCRUD.TbRoleFacade;
import beanInfo.TbAccount;
import beanInfo.TbRole;
import helperPack.CreateMessage;
import helperPack.Encrypt;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.model.SelectItem;

/**
 *
 * @author MesutOezil
 */
public class MBAccountManager implements Serializable {

    @EJB
    private TbRoleFacade tbRoleFacade;
    @EJB
    private TbAccountFacade tbAccountFacade;
    private List<TbAccount> listAcc;
    private ArrayList<SelectItem> listRole;
    private int roleID=0;

    private TbAccount accUpdate;

    public TbAccount getAccUpdate() {
        return accUpdate;
    }

    public void setAccUpdate(TbAccount accUpdate) {
        this.accUpdate = accUpdate;
    }
    
    
    private int error;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
    
    
    
    
    private String duplicate=null;

    public String getDuplicate() {
        return duplicate;
    }

    public void setDuplicate(String duplicate) {
        this.duplicate = duplicate;
    }
    
    
    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public ArrayList<SelectItem> getListRole() {
        return listRole;
    }

    public void setListRole(ArrayList<SelectItem> listRole) {
        this.listRole = listRole;
    }

    public List<TbAccount> getListAcc() {
        return listAcc;
    }

    public void setListAcc(List<TbAccount> listAcc) {
        this.listAcc = listAcc;
    }
    private TbAccount acc;

    public TbAccount getAcc() {
        return acc;
    }

    public void setAcc(TbAccount acc) {
        this.acc = acc;
    }

    @PostConstruct
    public void init() {
        this.listAcc = this.tbAccountFacade.findAll();
        this.acc = new TbAccount();
        this.listRole = this.tbAccountFacade.getListRole();
        this.accUpdate=new TbAccount();
    }

    /** Creates a new instance of MBAccountManager */
    public MBAccountManager() {
    }

    public void insertAccount() {
        try {
            
            if (this.roleID == 0) {
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Choose Role");
            }
            else if(this.tbAccountFacade.find(this.acc.getUserName())!=null){
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "This UserName is exist");
            }
            else {
                this.acc.setPassword(Encrypt.hashPassword(this.acc.getPassword()));           
                TbRole role = this.tbRoleFacade.find(this.roleID);
                this.acc.setRoleID(role);
                this.tbAccountFacade.create(this.acc);
                CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Insert Successfully");
                this.listAcc=this.tbAccountFacade.findAll();
                this.acc=new TbAccount();
                this.roleID=0;
                this.error=1;
            }
        } catch (Exception ex) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Can not insert at this time");
        }
    }
    
    public void updateAccount(){
        try{
            if(this.roleID==0){
                CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Choose Role");
               this.error=0;
            }
            else{
                TbRole role=this.tbRoleFacade.find(this.roleID);
                this.accUpdate.setRoleID(role);
                this.tbAccountFacade.edit(this.accUpdate);
                CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Update Successfully");
                this.listAcc=this.tbAccountFacade.findAll();
                this.accUpdate=new TbAccount();
                this.roleID=0;
                this.error=1;
            }
        }catch(Exception ex){
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", ex.getMessage());
           this.error=0;
        }
    }
       
}
