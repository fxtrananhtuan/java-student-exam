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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author MesutOezil
 */
@Entity
@Table(name = "tbSurvey", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSurvey.findAll", query = "SELECT t FROM TbSurvey t"),
    @NamedQuery(name = "TbSurvey.findBySurveyID", query = "SELECT t FROM TbSurvey t WHERE t.surveyID = :surveyID"),
    @NamedQuery(name = "TbSurvey.findByQuestion", query = "SELECT t FROM TbSurvey t WHERE t.question = :question")})
public class TbSurvey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "SurveyID", nullable = false)
    private Integer surveyID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "Question", nullable = false, length = 1073741823)
    private String question;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status", nullable = false)
    private boolean status;
    @ManyToMany(mappedBy = "tbSurveyList")
    private List<TbAssignment> tbAssignmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "surveyID")
    private List<TbSurveyAnswer> tbSurveyAnswerList;
    @JoinColumn(name = "UserName", referencedColumnName = "UserName", nullable = false)
    @ManyToOne(optional = false)
    private TbAccount userName;

    public TbSurvey() {
    }

    public TbSurvey(Integer surveyID) {
        this.surveyID = surveyID;
    }

    public TbSurvey(Integer surveyID, String question,boolean status) {
        this.surveyID = surveyID;
        this.question = question;
        this.status=status;
    }

    public Integer getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Integer surveyID) {
        this.surveyID = surveyID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @XmlTransient
    public List<TbAssignment> getTbAssignmentList() {
        return tbAssignmentList;
    }

    public void setTbAssignmentList(List<TbAssignment> tbAssignmentList) {
        this.tbAssignmentList = tbAssignmentList;
    }

    @XmlTransient
    public List<TbSurveyAnswer> getTbSurveyAnswerList() {
        return tbSurveyAnswerList;
    }

    public void setTbSurveyAnswerList(List<TbSurveyAnswer> tbSurveyAnswerList) {
        this.tbSurveyAnswerList = tbSurveyAnswerList;
    }

    public TbAccount getUserName() {
        return userName;
    }

    public void setUserName(TbAccount userName) {
        this.userName = userName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (surveyID != null ? surveyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSurvey)) {
            return false;
        }
        TbSurvey other = (TbSurvey) object;
        if ((this.surveyID == null && other.surveyID != null) || (this.surveyID != null && !this.surveyID.equals(other.surveyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbSurvey[ surveyID=" + surveyID + " ]";
    }
    
}
