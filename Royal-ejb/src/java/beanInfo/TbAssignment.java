/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MesutOezil
 */
@Entity
@Table(name = "tbAssignment", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbAssignment.findAll", query = "SELECT t FROM TbAssignment t"),
    @NamedQuery(name = "TbAssignment.findByAssignmentID", query = "SELECT t FROM TbAssignment t WHERE t.assignmentID = :assignmentID"),
    @NamedQuery(name = "TbAssignment.findByAssignmentFile", query = "SELECT t FROM TbAssignment t WHERE t.assignmentFile = :assignmentFile"),
    @NamedQuery(name = "TbAssignment.findByStartDate", query = "SELECT t FROM TbAssignment t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "TbAssignment.findByEndDate", query = "SELECT t FROM TbAssignment t WHERE t.endDate = :endDate"),
    @NamedQuery(name = "TbAssignment.findByUser", query = "SELECT t FROM TbAssignment t WHERE t.userName = :userName"),
    @NamedQuery(name = "TbAssignment.findByActive", query = "SELECT t FROM TbAssignment t WHERE t.active = :active")})
public class TbAssignment implements Serializable {
    @Basic(optional =     false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "StartDate", nullable = false, length = 10)
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional =     false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "EndDate", nullable = false, length = 10)
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "AssignmentID", nullable = false)
    private Integer assignmentID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "AssignmentFile", nullable = false, length = 1073741823)
    private String assignmentFile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Active", nullable = false)
    private boolean active;
    @JoinTable(name = "tbAssignment_Survey", joinColumns = {
        @JoinColumn(name = "AssignmentID", referencedColumnName = "AssignmentID", nullable = false)}, inverseJoinColumns = {
        @JoinColumn(name = "SurveyID", referencedColumnName = "SurveyID", nullable = false)})
    @ManyToMany
    private List<TbSurvey> tbSurveyList;
    @JoinColumn(name = "SubjectID", referencedColumnName = "SubjectID", nullable = false)
    @ManyToOne(optional = false)
    private TbSubject subjectID;
    @JoinColumn(name = "UserName", referencedColumnName = "UserName", nullable = false)
    @ManyToOne(optional = false)
    private TbAccount userName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbAssignment")
    private List<TbStudentAssignment> tbStudentAssignmentList;

    public TbAssignment() {
    }

    public TbAssignment(Integer assignmentID) {
        this.assignmentID = assignmentID;
    }

    public TbAssignment(Integer assignmentID, String assignmentFile, Date startDate, Date endDate, boolean active) {
        this.assignmentID = assignmentID;
        this.assignmentFile = assignmentFile;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
    }

    public Integer getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(Integer assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentFile() {
        return assignmentFile;
    }

    public void setAssignmentFile(String assignmentFile) {
        this.assignmentFile = assignmentFile;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @XmlTransient
    public List<TbSurvey> getTbSurveyList() {
        return tbSurveyList;
    }

    public void setTbSurveyList(List<TbSurvey> tbSurveyList) {
        this.tbSurveyList = tbSurveyList;
    }

    public TbSubject getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(TbSubject subjectID) {
        this.subjectID = subjectID;
    }

    public TbAccount getUserName() {
        return userName;
    }

    public void setUserName(TbAccount userName) {
        this.userName = userName;
    }

    @XmlTransient
    public List<TbStudentAssignment> getTbStudentAssignmentList() {
        return tbStudentAssignmentList;
    }

    public void setTbStudentAssignmentList(List<TbStudentAssignment> tbStudentAssignmentList) {
        this.tbStudentAssignmentList = tbStudentAssignmentList;
    }
    
    public String convertStatus(){
        if(this.active==true){
            return "Active";
        }
        else{
            return "InActive";
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (assignmentID != null ? assignmentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbAssignment)) {
            return false;
        }
        TbAssignment other = (TbAssignment) object;
        if ((this.assignmentID == null && other.assignmentID != null) || (this.assignmentID != null && !this.assignmentID.equals(other.assignmentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbAssignment[ assignmentID=" + assignmentID + " ]";
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
