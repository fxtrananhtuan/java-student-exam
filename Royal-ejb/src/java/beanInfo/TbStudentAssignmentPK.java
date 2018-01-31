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
public class TbStudentAssignmentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "AssignmentID", nullable = false)
    private int assignmentID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "StudentID", nullable = false, length = 50)
    private String studentID;

    public TbStudentAssignmentPK() {
    }

    public TbStudentAssignmentPK(int assignmentID, String studentID) {
        this.assignmentID = assignmentID;
        this.studentID = studentID;
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
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
        hash += (int) assignmentID;
        hash += (studentID != null ? studentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbStudentAssignmentPK)) {
            return false;
        }
        TbStudentAssignmentPK other = (TbStudentAssignmentPK) object;
        if (this.assignmentID != other.assignmentID) {
            return false;
        }
        if ((this.studentID == null && other.studentID != null) || (this.studentID != null && !this.studentID.equals(other.studentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbStudentAssignmentPK[ assignmentID=" + assignmentID + ", studentID=" + studentID + " ]";
    }
    
}
