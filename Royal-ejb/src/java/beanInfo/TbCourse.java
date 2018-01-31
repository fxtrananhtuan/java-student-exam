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
@Table(name = "tbCourse", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbCourse.findAll", query = "SELECT t FROM TbCourse t"),
    @NamedQuery(name = "TbCourse.findByCourseID", query = "SELECT t FROM TbCourse t WHERE t.courseID = :courseID"),
    @NamedQuery(name = "TbCourse.findByCourseName", query = "SELECT t FROM TbCourse t WHERE t.courseName = :courseName")})
public class TbCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "CourseID", nullable = false)
    private Integer courseID;
    @Basic(optional = false)
    @NotNull
    @NotEmpty(message="The Course Name Not Null or Empty")
    @Size(min = 1, max = 30, message="The Course Name Char <=30")
    @Column(name = "CourseName", nullable = false, length = 50)
    private String courseName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseID")
    private List<TbBatch> tbBatchList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseID")
    private List<TbSemester> tbSemesterList;

    public TbCourse() {
    }

    public TbCourse(Integer courseID) {
        this.courseID = courseID;
    }

    public TbCourse(Integer courseID, String courseName) {
        this.courseID = courseID;
        this.courseName = courseName;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @XmlTransient
    public List<TbBatch> getTbBatchList() {
        return tbBatchList;
    }

    public void setTbBatchList(List<TbBatch> tbBatchList) {
        this.tbBatchList = tbBatchList;
    }

    @XmlTransient
    public List<TbSemester> getTbSemesterList() {
        return tbSemesterList;
    }

    public void setTbSemesterList(List<TbSemester> tbSemesterList) {
        this.tbSemesterList = tbSemesterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseID != null ? courseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbCourse)) {
            return false;
        }
        TbCourse other = (TbCourse) object;
        if ((this.courseID == null && other.courseID != null) || (this.courseID != null && !this.courseID.equals(other.courseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbCourse[ courseID=" + courseID + " ]";
    }
    
}
