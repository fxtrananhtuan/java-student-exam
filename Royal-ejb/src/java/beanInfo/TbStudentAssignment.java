/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanInfo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MesutOezil
 */
@Entity
@Table(name = "tbStudent_Assignment", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbStudentAssignment.findAll", query = "SELECT t FROM TbStudentAssignment t"),
    @NamedQuery(name = "TbStudentAssignment.findByAssignmentID", query = "SELECT t FROM TbStudentAssignment t WHERE t.tbStudentAssignmentPK.assignmentID = :assignmentID"),
    @NamedQuery(name = "TbStudentAssignment.findByStudentID", query = "SELECT t FROM TbStudentAssignment t WHERE t.tbStudentAssignmentPK.studentID = :studentID"),
    @NamedQuery(name = "TbStudentAssignment.findByFileUpload", query = "SELECT t FROM TbStudentAssignment t WHERE t.fileUpload = :fileUpload"),
    @NamedQuery(name = "TbStudentAssignment.findByMark", query = "SELECT t FROM TbStudentAssignment t WHERE t.mark = :mark")})
public class TbStudentAssignment implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TbStudentAssignmentPK tbStudentAssignmentPK;
    @Size(max = 1073741823)
    @Column(name = "FileUpload", length = 1073741823)
    private String fileUpload;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mark", nullable = false)
    private int mark;
    @JoinColumn(name = "StudentID", referencedColumnName = "StudentID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TbStudent tbStudent;
    @JoinColumn(name = "AssignmentID", referencedColumnName = "AssignmentID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TbAssignment tbAssignment;

    public TbStudentAssignment() {
    }

    public TbStudentAssignment(TbStudentAssignmentPK tbStudentAssignmentPK) {
        this.tbStudentAssignmentPK = tbStudentAssignmentPK;
    }

    public TbStudentAssignment(TbStudentAssignmentPK tbStudentAssignmentPK, int mark) {
        this.tbStudentAssignmentPK = tbStudentAssignmentPK;
        this.mark = mark;
    }

    public TbStudentAssignment(int assignmentID, String studentID) {
        this.tbStudentAssignmentPK = new TbStudentAssignmentPK(assignmentID, studentID);
    }

    public TbStudentAssignmentPK getTbStudentAssignmentPK() {
        return tbStudentAssignmentPK;
    }

    public void setTbStudentAssignmentPK(TbStudentAssignmentPK tbStudentAssignmentPK) {
        this.tbStudentAssignmentPK = tbStudentAssignmentPK;
    }

    public String getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(String fileUpload) {
        this.fileUpload = fileUpload;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public TbStudent getTbStudent() {
        return tbStudent;
    }

    public void setTbStudent(TbStudent tbStudent) {
        this.tbStudent = tbStudent;
    }

    public TbAssignment getTbAssignment() {
        return tbAssignment;
    }

    public void setTbAssignment(TbAssignment tbAssignment) {
        this.tbAssignment = tbAssignment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tbStudentAssignmentPK != null ? tbStudentAssignmentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbStudentAssignment)) {
            return false;
        }
        TbStudentAssignment other = (TbStudentAssignment) object;
        if ((this.tbStudentAssignmentPK == null && other.tbStudentAssignmentPK != null) || (this.tbStudentAssignmentPK != null && !this.tbStudentAssignmentPK.equals(other.tbStudentAssignmentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbStudentAssignment[ tbStudentAssignmentPK=" + tbStudentAssignmentPK + " ]";
    }
    
}
