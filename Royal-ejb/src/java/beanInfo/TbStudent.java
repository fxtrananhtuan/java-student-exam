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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author MesutOezil
 */
@Entity
@Table(name = "tbStudent", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbStudent.findAll", query = "SELECT t FROM TbStudent t"),
    @NamedQuery(name = "TbStudent.findByStudentID", query = "SELECT t FROM TbStudent t WHERE t.studentID = :studentID"),
    @NamedQuery(name = "TbStudent.findByPassword", query = "SELECT t FROM TbStudent t WHERE t.password = :password"),
    @NamedQuery(name = "TbStudent.findByFullName", query = "SELECT t FROM TbStudent t WHERE t.fullName = :fullName"),
    @NamedQuery(name = "TbStudent.findByPhone", query = "SELECT t FROM TbStudent t WHERE t.phone = :phone"),
    @NamedQuery(name = "TbStudent.findByEmail", query = "SELECT t FROM TbStudent t WHERE t.email = :email"),
    @NamedQuery(name = "TbStudent.findByStudentIDFullNamePhoneEmail", query = "SELECT t FROM TbStudent t WHERE t.studentID = :studentID AND t.fullName = :fullName AND t.email = :email AND t.phone = :phone"),   
    @NamedQuery(name = "TbStudent.findByStudentIDPassword", query = "SELECT t FROM TbStudent t WHERE t.studentID = :studentID AND t.password = :password"),
    @NamedQuery(name = "TbStudent.findByAddress", query = "SELECT t FROM TbStudent t WHERE t.address = :address")})
public class TbStudent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotEmpty(message="Please Enter StudentID")
    @Size(max=50,message="StudentID have 1->50 characters") 
    @Column(name = "StudentID")  
    private String studentID;
    @Basic(optional = false)
    @NotEmpty(message="Password not null")
    @Size(min=6,max=12,message="Password have 6->12 characters")
    @Column(name = "Password")
    private String password;
    @NotEmpty(message="FullName not null")
    @Size(max=50,message="FullName have 1->50 characters")
    @Column(name = "FullName")
    private String fullName;
    @NotEmpty(message="Phone not null")
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @NotEmpty(message="Email not null")
    @Size(max = 50)
    @Column(name = "Email")
    @Email(message="Invalid Email")
    private String email;
    @NotEmpty(message="Address not null")
    @Size(max=100,message="Address have 1->100 characters")
    @Column(name = "Address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status")
    private boolean status=true;
    @JoinColumn(name = "BatchID", referencedColumnName = "BatchID")
    @ManyToOne(optional = false)
    @NotEmpty(message="Choose 1 Batch")
    private TbBatch batchID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbStudent")
    private List<TbMark> tbMarkList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tbStudent")
    private List<TbStudentAssignment> tbStudentAssignmentList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentID")
    private List<TbFeedback> tbFeedbackList;

   
    
    public TbStudent() {
    }

    public TbStudent(String studentID) {
        this.studentID = studentID;
    }

    public TbStudent(String studentID, String password) {
        this.studentID = studentID;
        this.password = password;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
      
    public TbBatch getBatchID() {
        return batchID;
    }

    public void setBatchID(TbBatch batchID) {
        this.batchID = batchID;
    }

    @XmlTransient
    public List<TbMark> getTbMarkList() {
        return tbMarkList;
    }

    public void setTbMarkList(List<TbMark> tbMarkList) {
        this.tbMarkList = tbMarkList;
    }

    @XmlTransient
    public List<TbStudentAssignment> getTbStudentAssignmentList() {
        return tbStudentAssignmentList;
    }

    public void setTbStudentAssignmentList(List<TbStudentAssignment> tbStudentAssignmentList) {
        this.tbStudentAssignmentList = tbStudentAssignmentList;
    }

    @XmlTransient
    public List<TbFeedback> getTbFeedbackList() {
        return tbFeedbackList;
    }

    public void setTbFeedbackList(List<TbFeedback> tbFeedbackList) {
        this.tbFeedbackList = tbFeedbackList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentID != null ? studentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbStudent)) {
            return false;
        }
        TbStudent other = (TbStudent) object;
        if ((this.studentID == null && other.studentID != null) || (this.studentID != null && !this.studentID.equals(other.studentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbStudent[ studentID=" + studentID + " ]";
    }
    
     public String getConvertStatus(){
        if(this.status)
        {
            return "Active";
        }
        else{
            return "InActive";
        }
        
    }
}
