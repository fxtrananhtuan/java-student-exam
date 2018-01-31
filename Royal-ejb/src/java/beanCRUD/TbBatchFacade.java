/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbBatch;
import beanInfo.TbStudent;
import java.util.ArrayList;
import java.util.List;
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
public class TbBatchFacade extends AbstractFacade<TbBatch> {
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbBatchFacade() {
        super(TbBatch.class);
    }
    
    public ArrayList<SelectItem> listBatch()
    {
        ArrayList<SelectItem> temp=new ArrayList<SelectItem>();
        List<TbBatch> listBatch=this.findAll();
        for(TbBatch b : listBatch)
        {
            SelectItem item=new SelectItem(b.getBatchID(), b.getBatchName());
            temp.add(item);
        }
        return temp;
    }

    //kiem tra ten course trong Course
    public boolean checkBatchName(String batchName) {
        int total = 0;
        Query q = em.createNamedQuery("TbBatch.findByBatchName");
        q.setParameter("batchName", batchName);
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }

    }

    public List<TbBatch> findByCourseName(String batchName) {
        String sql = "Select * from tbBatch where batchName like '%" + batchName + "%'";
        Query q = em.createNativeQuery(sql, TbBatch.class);
        List<TbBatch> listBatch = new ArrayList<TbBatch>();
        for (TbBatch batch : (List<TbBatch>) q.getResultList()) {
            listBatch.add(batch);
        }
        return listBatch;

    }
    
   

    //kiem tra Xoa batch
    public boolean checkDelete(int batchID) {
        int total = 0;
        String sql = "Select * from tbStudent t where t.batchID = " + batchID + "";
        Query q = em.createNativeQuery(sql);
        total = q.getResultList().size();
        if (total > 0) {
            return false;
        } else {
            return true;
        }

    }
    
    //láº¥y list student báº±ng batchID
    public List<TbStudent> getStudentByBatchID(int batchID){
        String sql = "Select * from TbStudent t where t.batchID ="+batchID+"";
        Query q = em.createNativeQuery(sql, TbStudent.class);
        List<TbStudent> getStudentList = q.getResultList();        
        return getStudentList;
    }
}


