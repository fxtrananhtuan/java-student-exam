/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanCRUD;

import beanInfo.TbBatch;
import beanInfo.TbCourse;
import beanInfo.TbSemester;
import beanInfo.TbStudent;
import beanInfo.TbStudentAssignment;
import beanInfo.TbSubject;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.model.SelectItem;
import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MesutOezil
 */
@Stateless
public class TbStudentAssignmentFacade extends AbstractFacade<TbStudentAssignment> {
    @EJB
    private TbStudentFacade tbStudentFacade;
    @EJB
    private TbBatchFacade tbBatchFacade;
    @EJB
    private TbSubjectFacade tbSubjectFacade;
    
    @PersistenceContext(unitName = "Royal-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public TbStudentAssignmentFacade() {
        super(TbStudentAssignment.class);
    }
    
    
    public List<TbStudentAssignment> listAssignmentByAssignmentID(int assignmentID){
        return this.em.createNamedQuery("TbStudentAssignment.findByAssignmentID").setParameter("assignmentID", assignmentID).getResultList();
    }
    
    public List<TbStudent> listStudentBySubjecID(int subjectID){
        TbSubject subject=this.tbSubjectFacade.find(subjectID);
        TbSemester semester=subject.getSemID();
        TbCourse course=semester.getCourseID();
        List<TbBatch> listBatch=this.tbBatchFacade.findAll();
        List<TbBatch> listBatchCourse=new ArrayList<TbBatch>();
        for(TbBatch batch:listBatch){
            if(batch.getCourseID().getCourseID()==course.getCourseID()){
                listBatchCourse.add(batch);
            }
        }
        List<TbStudent> listStud=this.tbStudentFacade.findAll();
        List<TbStudent> listStudWithSubjecID=new ArrayList<TbStudent>();
        for(TbBatch batch:listBatchCourse){
            for(TbStudent stud:listStud){
                if(stud.getBatchID().getBatchID()==batch.getBatchID()){
                    listStudWithSubjecID.add(stud);
                }
            }
        }
        return listStudWithSubjecID;
    }
    
    
    //manipulate with user assignment 
    public ArrayList<SelectItem> listTSA(String studentID) {
        ArrayList<SelectItem> listAssignment = new ArrayList<SelectItem>();
        List<TbStudentAssignment> list=this.em.createNamedQuery("TbStudentAssignment.findByStudentID").setParameter("studentID", studentID).getResultList();
        List<TbStudentAssignment> result=new ArrayList<TbStudentAssignment>();
        for(TbStudentAssignment sa:list){
            if(sa.getTbAssignment().getActive()){
                result.add(sa);
            }
        }
        for (TbStudentAssignment sa : result) {
            
                SelectItem item = new SelectItem(sa.getTbStudentAssignmentPK().getAssignmentID(),sa.getTbAssignment().getSubjectID().getSubjectName().toString()+sa.getTbAssignment().getAssignmentID().toString()+
                        sa.getTbAssignment().getSubjectID().getSubjectID().toString());
                listAssignment.add(item);
        }
        return listAssignment;
    }
    
    //get student assignment with studentid and assignmentid to upload file fileuploading
    public TbStudentAssignment saFileUpload(int assignmentID,String studentID){
        List<TbStudentAssignment> listSA=listAssignmentByAssignmentID(assignmentID);
        TbStudentAssignment updateSA=null;
        for(TbStudentAssignment sa:listSA){
            if(sa.getTbStudentAssignmentPK().getStudentID().equals(studentID)){
                updateSA=sa;
                break;
            }
        }
        return updateSA;
    }
}
