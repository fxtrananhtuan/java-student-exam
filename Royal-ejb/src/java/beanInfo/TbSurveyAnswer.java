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
@Table(name = "tbSurveyAnswer", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbSurveyAnswer.findAll", query = "SELECT t FROM TbSurveyAnswer t"),
    @NamedQuery(name = "TbSurveyAnswer.findByAnswerID", query = "SELECT t FROM TbSurveyAnswer t WHERE t.answerID = :answerID"),
    @NamedQuery(name = "TbSurveyAnswer.findByAnswer", query = "SELECT t FROM TbSurveyAnswer t WHERE t.answer = :answer"),
    @NamedQuery(name = "TbSurveyAnswer.findByCount", query = "SELECT t FROM TbSurveyAnswer t WHERE t.count = :count")})
public class TbSurveyAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "AnswerID", nullable = false)
    private Integer answerID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "Answer", nullable = false, length = 1073741823)
    private String answer;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Count", nullable = false)
    private int count;
    @JoinColumn(name = "SurveyID", referencedColumnName = "SurveyID", nullable = false)
    @ManyToOne(optional = false)
    private TbSurvey surveyID;

    public TbSurveyAnswer() {
    }

    public TbSurveyAnswer(Integer answerID) {
        this.answerID = answerID;
    }

    public TbSurveyAnswer(Integer answerID, String answer, int count) {
        this.answerID = answerID;
        this.answer = answer;
        this.count = count;
    }

    public Integer getAnswerID() {
        return answerID;
    }

    public void setAnswerID(Integer answerID) {
        this.answerID = answerID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TbSurvey getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(TbSurvey surveyID) {
        this.surveyID = surveyID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerID != null ? answerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbSurveyAnswer)) {
            return false;
        }
        TbSurveyAnswer other = (TbSurveyAnswer) object;
        if ((this.answerID == null && other.answerID != null) || (this.answerID != null && !this.answerID.equals(other.answerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbSurveyAnswer[ answerID=" + answerID + " ]";
    }
    
}
