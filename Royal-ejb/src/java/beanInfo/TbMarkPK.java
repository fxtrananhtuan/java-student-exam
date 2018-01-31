/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanInfo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author MesutOezil
 */
@Embeddable
public class TbMarkPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "SubjectID", nullable = false)
    private int subjectID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "StudentID", nullable = false, length = 50)
    private String studentID;

    public TbMarkPK() {
    }

    public TbMarkPK(int subjectID, String studentID) {
        this.subjectID = subjectID;
        this.studentID = studentID;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) subjectID;
        hash += (studentID != null ? studentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbMarkPK)) {
            return false;
        }
        TbMarkPK other = (TbMarkPK) object;
        if (this.subjectID != other.subjectID) {
            return false;
        }
        if ((this.studentID == null && other.studentID != null) || (this.studentID != null && !this.studentID.equals(other.studentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbMarkPK[ subjectID=" + subjectID + ", studentID=" + studentID + " ]";
    }
    
}
