/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package adminPack;

import beanCRUD.TbAssignmentFacade;
import beanCRUD.TbSurveyFacade;
import beanInfo.TbAccount;
import beanInfo.TbAssignment;
import beanInfo.TbSurvey;
import helperPack.CreateMessage;
import helperPack.SessionTool;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;

/**
 *
 * @author MesutOezil
 */
public class MBRegisterSurvey  implements Serializable {

    @EJB
    private TbSurveyFacade tbSurveyFacade;
    @EJB
    private TbAssignmentFacade tbAssignmentFacade;
    //declare list assignment
    private List<TbAssignment> listAssignment;

    public List<TbAssignment> getListAssignment() {
        return listAssignment;
    }

    public void setListAssignment(List<TbAssignment> listAssignment) {
        this.listAssignment = listAssignment;
    }
    private TbAccount acc; //check role of account

    public TbAccount getAcc() {
        return acc;
    }

    public void setAcc(TbAccount acc) {
        this.acc = acc;
    }
    //declare one assignment to manipulate
    private TbAssignment assignment;

    public TbAssignment getAssignment() {
        return assignment;
    }

    public void setAssignment(TbAssignment assignment) {
        this.assignment = assignment;
    }
    //declare list survey
    private List<TbSurvey> listSurvey;

    public List<TbSurvey> getListSurvey() {
        return listSurvey;
    }

    public void setListSurvey(List<TbSurvey> listSurvey) {
        this.listSurvey = listSurvey;
    }
    //declare map to get list survey
    private Map<TbSurvey, Boolean> check;

    public Map<TbSurvey, Boolean> getCheck() {
        return check;
    }

    public void setCheck(Map<TbSurvey, Boolean> check) {
        this.check = check;
    }

    @PostConstruct
    public void init() {
        this.acc = (TbAccount) SessionTool.getSession("account");
        if (SessionTool.getSession("isAdmin") != null) {
            this.listAssignment = this.tbAssignmentFacade.findAll(); //get all assignment if role is admin
        } else {
            this.listAssignment = this.tbAssignmentFacade.getListAssignmentByUser(acc); //get assignment by id if role is staff
        }
        this.assignment = new TbAssignment();
        this.listSurvey = new ArrayList<TbSurvey>();
        this.check = new HashMap<TbSurvey, Boolean>();
    }

    /** Creates a new instance of MBRegisterSurvey */
    public MBRegisterSurvey() {
    }

    public void registSurvey() {
        List<TbSurvey> list= this.tbSurveyFacade.findAll();
        for(TbSurvey sur:list){
            if(sur.isStatus()){
                this.listSurvey.add(sur);
            }
        }
        this.listSurvey = this.tbSurveyFacade.findAll();
    }

    public void addSurvey() {
        List<TbSurvey> list = new ArrayList<TbSurvey>();
        for (Map.Entry<TbSurvey, Boolean> oneMap : check.entrySet()) {
            if (oneMap.getValue()) {
                TbSurvey sur = oneMap.getKey();
                list.add(sur);
            }
        }
        
        if (list.isEmpty()) {
            CreateMessage.createMessage(FacesMessage.SEVERITY_ERROR, "Error", "Chosse Survey Now!!!S");
        } else {
            this.assignment.setTbSurveyList(list);
            this.tbAssignmentFacade.edit(this.assignment);
            CreateMessage.createMessage(FacesMessage.SEVERITY_INFO, "Success", "Add survey successfully");
            this.listSurvey = new ArrayList<TbSurvey>();
            this.check=new HashMap<TbSurvey, Boolean>();
        }

    }
}
