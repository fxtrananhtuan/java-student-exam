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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MesutOezil
 */
@Entity
@Table(name = "tbMark", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbMark.findAll", query = "SELECT t FROM TbMark t"),
    @NamedQuery(name = "TbMark.findBySubjectID", query = "SELECT t FROM TbMark t WHERE t.tbMarkPK.subjectID = :subjectID"),
    @NamedQuery(name = "TbMark.findByStudentID", query = "SELECT t FROM TbMark t WHERE t.tbMarkPK.studentID = :studentID"),
    @NamedQuery(name = "TbMark.findByMark", query = "SELECT t FROM TbMark t WHERE t.mark = :mark")})
public class TbMark implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TbMarkPK tbMarkPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mark", nullable = false)
    private int mark;
    @JoinColumn(name = "SubjectID", referencedColumnName = "SubjectID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TbSubject tbSubject;
    @JoinColumn(name = "StudentID", referencedColumnName = "StudentID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private TbStudent tbStudent;

    public TbMark() {
    }

    public TbMark(TbMarkPK tbMarkPK) {
        this.tbMarkPK = tbMarkPK;
    }

    public TbMark(TbMarkPK tbMarkPK, int mark) {
        this.tbMarkPK = tbMarkPK;
        this.mark = mark;
    }

    public TbMark(int subjectID, String studentID) {
        this.tbMarkPK = new TbMarkPK(subjectID, studentID);
    }

    public TbMarkPK getTbMarkPK() {
        return tbMarkPK;
    }

    public void setTbMarkPK(TbMarkPK tbMarkPK) {
        this.tbMarkPK = tbMarkPK;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public TbSubject getTbSubject() {
        return tbSubject;
    }

    public void setTbSubject(TbSubject tbSubject) {
        this.tbSubject = tbSubject;
    }

    public TbStudent getTbStudent() {
        return tbStudent;
    }

    public void setTbStudent(TbStudent tbStudent) {
        this.tbStudent = tbStudent;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tbMarkPK != null ? tbMarkPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbMark)) {
            return false;
        }
        TbMark other = (TbMark) object;
        if ((this.tbMarkPK == null && other.tbMarkPK != null) || (this.tbMarkPK != null && !this.tbMarkPK.equals(other.tbMarkPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbMark[ tbMarkPK=" + tbMarkPK + " ]";
    }
    
}
