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
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author MesutOezil
 */
@Entity
@Table(name = "tbAccount", catalog = "RoyalEducationalAcademy", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TbAccount.findAll", query = "SELECT t FROM TbAccount t"),
    @NamedQuery(name = "TbAccount.findByUserName", query = "SELECT t FROM TbAccount t WHERE t.userName = :userName"),
    @NamedQuery(name = "TbAccount.findByUserNamePassword", query = "SELECT t FROM TbAccount t WHERE t.userName = :userName and t.password= :password"),
    @NamedQuery(name = "TbAccount.findByPassword", query = "SELECT t FROM TbAccount t WHERE t.password = :password"),
    @NamedQuery(name = "TbAccount.findByFullName", query = "SELECT t FROM TbAccount t WHERE t.fullName = :fullName"),
    @NamedQuery(name = "TbAccount.findByEmail", query = "SELECT t FROM TbAccount t WHERE t.email = :email"),
    @NamedQuery(name = "TbAccount.findByPhone", query = "SELECT t FROM TbAccount t WHERE t.phone = :phone"),
    @NamedQuery(name = "TbAccount.findByAddress", query = "SELECT t FROM TbAccount t WHERE t.address = :address"),
    @NamedQuery(name = "TbAccount.findByGender", query = "SELECT t FROM TbAccount t WHERE t.gender = :gender")})
public class TbAccount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotEmpty(message="Please Enter UserName")
    @Length(min=6,max=50,message="UserName must be more than 6 characters")
    @Column(name = "UserName", nullable = false, length = 50)
    private String userName;
    @Basic(optional = false)
    @NotNull
    @NotEmpty(message="Password is null")
    @Length(min=6,max=12,message="Password have 6->12 characters")
    @Column(name = "Password", nullable = false, length = 1073741823)
    private String password;
    @Basic(optional = false)
    @NotNull
    @NotEmpty(message="FullName is null")
    @Column(name = "FullName", nullable = false, length = 1073741823)
    private String fullName;
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Email", length = 50)
    private String email;
    @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "Phone", length = 50)
    private String phone;
    @Size(max = 1073741823)
    @NotEmpty(message="Address is null")
    @Column(name = "Address", length = 1073741823)
    private String address;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Gender", nullable = false)
    private boolean gender;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Status", nullable = false)
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userName")
    private List<TbSurvey> tbSurveyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userName")
    private List<TbAssignment> tbAssignmentList;
    @JoinColumn(name = "RoleID", referencedColumnName = "RoleID", nullable = false)
    @ManyToOne(optional = false)
    private TbRole roleID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userName")
    private List<TbFAQ> tbFAQList;

    public TbAccount() {
    }

    public TbAccount(String userName) {
        this.userName = userName;
    }

    public TbAccount(String userName, String password, String fullName, boolean gender) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
    @XmlTransient
    public List<TbSurvey> getTbSurveyList() {
        return tbSurveyList;
    }

    public void setTbSurveyList(List<TbSurvey> tbSurveyList) {
        this.tbSurveyList = tbSurveyList;
    }

    @XmlTransient
    public List<TbAssignment> getTbAssignmentList() {
        return tbAssignmentList;
    }

    public void setTbAssignmentList(List<TbAssignment> tbAssignmentList) {
        this.tbAssignmentList = tbAssignmentList;
    }

    public TbRole getRoleID() {
        return roleID;
    }

    public void setRoleID(TbRole roleID) {
        this.roleID = roleID;
    }

    @XmlTransient
    public List<TbFAQ> getTbFAQList() {
        return tbFAQList;
    }

    public void setTbFAQList(List<TbFAQ> tbFAQList) {
        this.tbFAQList = tbFAQList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbAccount)) {
            return false;
        }
        TbAccount other = (TbAccount) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beanInfo.TbAccount[ userName=" + userName + " ]";
    }
    
    public String getConvertStatus(){
        if(this.status){
            return "Active";
        }
        else{
            return "InActive";
        }
    }
    
    public String getConvertGender(){
        if(this.status){
            return "Male";
        }
        else{
            return "Female";
        }
    }
}
