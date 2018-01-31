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
@Table(name = "tbSemester", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSemester.findAll", query = "SELECT t FROM TbSemester t"),
    @NamedQuery(name = "TbSemester.findBySemID", query = "SELECT t FROM TbSemester t WHERE t.semID = :semID"),
    @NamedQuery(name = "TbSemester.findByCourseSem", query = "SELECT t FROM TbSemester t WHERE t.semName = :semName AND t.courseID = :courseID"),
    @NamedQuery(name = "TbSemester.findBySemName", query = "SELECT t FROM TbSemester t WHERE t.semName = :semName")})
public class TbSemester implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "SemID", nullable = false)
    private Integer semID;
    @Basic(optional = false)
    @NotNull
    @NotEmpty(message="Semester Name not null or not Empty!!!")
    @Size(min = 1, max = 50,message="subject name <= 50 char")
    @Column(name = "SemName", nullable = false, length = 50)
    private String semName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semID")
    private List<TbSubject> tbSubjectList;
    @JoinColumn(name = "CourseID", referencedColumnName = "CourseID", nullable = false)
    @ManyToOne(optional = false)
    private TbCourse courseID;

    public TbSemester() {
    }

    public TbSemester(Integer semID) {
        this.semID = semID;
    }

    public TbSemester(Integer semID, String semName) {
        this.semID = semID;
        this.semName = semName;
    }

    public Integer getSemID() {
        return semID;
    }

    public void setSemID(Integer semID) {
        this.semID = semID;
    }

    public String getSemName() {
        return semName;
    }

    public void setSemName(String semName) {
        this.semName = semName;
    }

    @XmlTransient
    public List<TbSubject> getTbSubjectList() {
        return tbSubjectList;
    }

    public void setTbSubjectList(List<TbSubject> tbSubjectList) {
        this.tbSubjectList = tbSubjectList;
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
        hash += (semID != null ? semID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSemester)) {
            return false;
        }
        TbSemester other = (TbSemester) object;
        if ((this.semID == null && other.semID != null) || (this.semID != null && !this.semID.equals(other.semID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbSemester[ semID=" + semID + " ]";
    }
    
}
