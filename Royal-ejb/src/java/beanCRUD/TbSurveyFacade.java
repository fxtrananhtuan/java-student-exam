/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbAssignment;
import beanInfo.TbSurvey;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MesutOezil
 */
@Stateless
public class TbSurveyFacade extends AbstractFacade<TbSurvey> {
    @EJB
    private TbAssignmentFacade tbAssignmentFacade;
    
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbSurveyFacade() {
        super(TbSurvey.class);
    }
    
    public List<TbSurvey> listSur(int assignmentID){
        TbAssignment assignment=this.tbAssignmentFacade.find(assignmentID);
        return assignment.getTbSurveyList();
    }
}
