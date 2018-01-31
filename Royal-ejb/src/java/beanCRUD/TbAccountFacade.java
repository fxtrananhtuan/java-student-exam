/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbAccount;
import beanInfo.TbRole;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author MesutOezil
 */
@Stateless
public class TbAccountFacade extends AbstractFacade<TbAccount> {
    @EJB
    private TbRoleFacade tbRoleFacade;

    
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbAccountFacade() {
        super(TbAccount.class);
    }

    public TbAccount getAcc(String username, String password) {
        Query q = this.em.createNamedQuery("TbAccount.findByUserNamePassword");
        q.setParameter("userName", username);
        q.setParameter("password", password);

        try {
            return (TbAccount) q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
    
    public ArrayList<SelectItem> getListRole(){
        ArrayList<SelectItem> listRole=new ArrayList<SelectItem>();
        
        List<TbRole> list=this.tbRoleFacade.findAll();
        
        for(TbRole role:list){
            SelectItem item=new SelectItem(role.getRoleID(), role.getRoleName());
            listRole.add(item);
        }
        return listRole;
    }
    //forgot password acc
    public TbAccount forgotPassword(String userName, String email, String fullName, String phone) {
        String sql = "SELECT a.UserName, a.Email, a.FullName, a.Phone from TbAccount a where a.UserName = '" + userName + "'"
                + "AND a.Email = '" + email + "' AND a.FullName = '"+fullName+"' AND a.Phone = '"+ phone+"'";
        Query q = this.em.createNativeQuery(sql, TbAccount.class);
        try {
            return (TbAccount) q.getSingleResult();
        }
        catch(Exception ex){
            return null;
        }
    }

}
