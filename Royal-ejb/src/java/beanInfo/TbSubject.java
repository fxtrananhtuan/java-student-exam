/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanInfo;

import java.io.Serializable;
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
@Table(name = "tbSubject", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSubject.findAll", query = "SELECT t FROM TbSubject t"),
    @NamedQuery(name = "TbSubject.findBySubjectID", query = "SELECT t FROM TbSubject t WHERE t.subjectID = :subjectID"),
    @NamedQuery(name = "TbSubject.findBySubjectName", query = "SELECT t FROM TbSubject t WHERE t.subjectName = :subjectName")})
public class TbSubject implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "SubjectID", nullable = false)
    private Integer subjectID;
    @Basic(optional = false)
    @NotNull
    @NotEmpty(message="SubjectName not null or not Empty!!!")
    @Size(min = 1, max = 50, message="subject name <= 50 char")
    @Column(name = "SubjectName", nullable = false, length = 50)
    private String subjectName;
    @JoinColumn(name = "SemID", referencedColumnName = "SemID", nullable = false)
    @ManyToOne(optional = false)
    private TbSemester semID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectID")
    private List<TbAssignment> tbAssignmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbSubject")
    private List<TbMark> tbMarkList;

    public TbSubject() {
    }

    public TbSubject(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public TbSubject(Integer subjectID, String subjectName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    public Integer getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(Integer subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public TbSemester getSemID() {
        return semID;
    }

    public void setSemID(TbSemester semID) {
        this.semID = semID;
    }

    @XmlTransient
    public List<TbAssignment> getTbAssignmentList() {
        return tbAssignmentList;
    }

    public void setTbAssignmentList(List<TbAssignment> tbAssignmentList) {
        this.tbAssignmentList = tbAssignmentList;
    }

    @XmlTransient
    public List<TbMark> getTbMarkList() {
        return tbMarkList;
    }

    public void setTbMarkList(List<TbMark> tbMarkList) {
        this.tbMarkList = tbMarkList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subjectID != null ? subjectID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSubject)) {
            return false;
        }
        TbSubject other = (TbSubject) object;
        if ((this.subjectID == null && other.subjectID != null) || (this.subjectID != null && !this.subjectID.equals(other.subjectID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbSubject[ subjectID=" + subjectID + " ]";
    }
    
}
