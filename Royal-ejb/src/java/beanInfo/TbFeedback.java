/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beanInfo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tbFeedback", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbFeedback.findAll", query = "SELECT t FROM TbFeedback t"),
    @NamedQuery(name = "TbFeedback.findByFeedbackID", query = "SELECT t FROM TbFeedback t WHERE t.feedbackID = :feedbackID"),
    @NamedQuery(name = "TbFeedback.findByFeedbackDate", query = "SELECT t FROM TbFeedback t WHERE t.feedbackDate = :feedbackDate"),
    @NamedQuery(name = "TbFeedback.findByFeedbackContent", query = "SELECT t FROM TbFeedback t WHERE t.feedbackContent = :feedbackContent"),
    @NamedQuery(name = "TbFeedback.findByStatus", query = "SELECT t FROM TbFeedback t WHERE t.status = :status")})
public class TbFeedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "FeedbackID", nullable = false)
    private Integer feedbackID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "FeedbackDate", nullable = false, length = 10)
    private String feedbackDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "FeedbackContent", nullable = false, length = 1073741823)
    private String feedbackContent;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status", nullable = false)
    private boolean status;
    @JoinColumn(name = "StudentID", referencedColumnName = "StudentID", nullable = false)
    @ManyToOne(optional = false)
    private TbStudent studentID;

    public TbFeedback() {
    }

    public TbFeedback(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public TbFeedback(Integer feedbackID, String feedbackDate, String feedbackContent, boolean status) {
        this.feedbackID = feedbackID;
        this.feedbackDate = feedbackDate;
        this.feedbackContent = feedbackContent;
        this.status = status;
    }

    public Integer getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(String feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TbStudent getStudentID() {
        return studentID;
    }

    public void setStudentID(TbStudent studentID) {
        this.studentID = studentID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackID != null ? feedbackID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbFeedback)) {
            return false;
        }
        TbFeedback other = (TbFeedback) object;
        if ((this.feedbackID == null && other.feedbackID != null) || (this.feedbackID != null && !this.feedbackID.equals(other.feedbackID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbFeedback[ feedbackID=" + feedbackID + " ]";
    }
    
}
