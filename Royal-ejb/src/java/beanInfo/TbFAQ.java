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
@Table(name = "tbFAQ", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbFAQ.findAll", query = "SELECT t FROM TbFAQ t"),
    @NamedQuery(name = "TbFAQ.findByFaqid", query = "SELECT t FROM TbFAQ t WHERE t.faqid = :faqid"),
    @NamedQuery(name = "TbFAQ.findByQuestion", query = "SELECT t FROM TbFAQ t WHERE t.question = :question"),
    @NamedQuery(name = "TbFAQ.findByAnswer", query = "SELECT t FROM TbFAQ t WHERE t.answer = :answer")})
public class TbFAQ implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "FAQID", nullable = false)
    private Integer faqid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "Question", nullable = false, length = 1073741823)
    private String question;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "Answer", nullable = false, length = 1073741823)
    private String answer;
    @JoinColumn(name = "UserName", referencedColumnName = "UserName", nullable = false)
    @ManyToOne(optional = false)
    private TbAccount userName;

    public TbFAQ() {
    }

    public TbFAQ(Integer faqid) {
        this.faqid = faqid;
    }

    public TbFAQ(Integer faqid, String question, String answer) {
        this.faqid = faqid;
        this.question = question;
        this.answer = answer;
    }

    public Integer getFaqid() {
        return faqid;
    }

    public void setFaqid(Integer faqid) {
        this.faqid = faqid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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
        hash += (faqid != null ? faqid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbFAQ)) {
            return false;
        }
        TbFAQ other = (TbFAQ) object;
        if ((this.faqid == null && other.faqid != null) || (this.faqid != null && !this.faqid.equals(other.faqid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbFAQ[ faqid=" + faqid + " ]";
    }
    
}
