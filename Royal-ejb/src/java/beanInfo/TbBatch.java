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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author MesutOezil
 */
@Entity
@Table(name = "tbBatch", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbBatch.findAll", query = "SELECT t FROM TbBatch t"),
    @NamedQuery(name = "TbBatch.findByBatchID", query = "SELECT t FROM TbBatch t WHERE t.batchID = :batchID"),
    @NamedQuery(name = "TbBatch.findByBatchName", query = "SELECT t FROM TbBatch t WHERE t.batchName = :batchName"),
    @NamedQuery(name = "TbBatch.findByStartDate", query = "SELECT t FROM TbBatch t WHERE t.startDate = :startDate"),
    @NamedQuery(name = "TbBatch.findByEndDate", query = "SELECT t FROM TbBatch t WHERE t.endDate = :endDate")})
public class TbBatch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "BatchID", nullable = false)
    private Integer batchID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @NotEmpty(message="Batch Name is not null or not empty!")
    @Column(name = "BatchName", nullable = false, length = 50)
    private String batchName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "StartDate", nullable = false, length = 10)
    @Temporal(javax.persistence.TemporalType.DATE)   
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "EndDate", nullable = false, length = 10)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "batchID")
    private List<TbStudent> tbStudentList;
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID", nullable = false)
    @ManyToOne(optional = false)
    private TbCourse courseID;

    public TbBatch() {
    }

    public TbBatch(Integer batchID) {
        this.batchID = batchID;
    }

    public TbBatch(Integer batchID, String batchName, Date startDate, Date endDate) {
        this.batchID = batchID;
        this.batchName = batchName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Integer getBatchID() {
        return batchID;
    }

    public void setBatchID(Integer batchID) {
        this.batchID = batchID;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
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

    @XmlTransient
    public List<TbStudent> getTbStudentList() {
        return tbStudentList;
    }

    public void setTbStudentList(List<TbStudent> tbStudentList) {
        this.tbStudentList = tbStudentList;
    }

    public TbCourse getCourseID() {
        return courseID;
    }

    public void setCourseID(TbCourse courseID) {
        this.courseID = courseID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batchID != null ? batchID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbBatch)) {
            return false;
        }
        TbBatch other = (TbBatch) object;
        if ((this.batchID == null && other.batchID != null) || (this.batchID != null && !this.batchID.equals(other.batchID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbBatch[ batchID=" + batchID + " ]";
    }
    
}
